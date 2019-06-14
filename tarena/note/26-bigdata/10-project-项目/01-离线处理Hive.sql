CREATE DATABASE fluxdb;


CREATE EXTERNAL TABLE flux(
 url String, urlname String, title String, chset String, scr String, 
 col String, lg String, je String, ec String, fv String, cn String, 
 ref String, uagent String, stat_uv String, stat_ss String, cip String)
PARTITIONED BY(reportTime String)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '|'
LOCATION '/flux';


# 增加今日分区
ALTER TABLE flux ADD PARTITION(reportTime='2019-06-13') 
LOCATION '/flux/reportTime=2019-06-13';

# 创建数据清洗表
CREATE TABLE dataclear(
 url String, urlname String, ref String, uagent String, uvid String, 
 ssid String, sscoutn String, sstime String, cip String)
PARTITIONED BY (reportTime String) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|';

# 从flux 表向dataclear 表导入数据
INSERT INTO dataclear PARTITION(reportTime='2019-06-13')
  SELECT url, urlname, ref, uagent, stat_uv, 
  split(stat_ss, '_')[0], split(stat_ss, '_')[1], split(stat_ss, '_')[2], cip
  FROM flux WHERE reportTime = '2019-06-13';

  
  
# PV 
#    一天之内访问的总量，有多少条日志就是多少个访问量。
SELECT COUNT(*) AS pv FROM dataclear WHERE reportTime='2019-06-13';

# UV 
#    独立访客数，一天之内用户的总数，将一天内所有日志的uvid去重后计数。
SELECT COUNT(DISTINCT uvid) AS uv FROM dataclear WHERE reportTime='2019-06-13';

# VV
#    会话总数，一天之内会话的总的数量，将一天内所有的日志的ssid去重后计数。
SELECT COUNT(DISTINCT ssid) AS vv FROM dataclear WHERE reportTime='2019-06-13';

# BR 
#    跳出率，一天之内跳出的会话占总的会话的比率。一天内跳出会话的总数/会话的总数。

#       跳出的会话总数
SELECT COUNT(br_tab.ssid) FROM (
  SELECT ssid FROM dataclear WHERE reportTime='2019-06-13' 
  GROUP BY ssid HAVING COUNT(*) = 1) AS br_tab;
#       会话的总数就是VV:
 ... 查看上面 VV 的 HQL
#       计算跳出率
SELECT ROUND(br_left_tab.br_count / br_right_tab.vv_count,4) AS br FROM
 (SELECT COUNT(br_tab.ssid) AS br_count FROM
  (SELECT ssid FROM dataclear WHERE reportTime='2019-06-13' GROUP BY ssid HAVING COUNT(*) = 1) AS br_tab) AS br_left_tab,
  (SELECT COUNT(DISTINCT ssid) AS vv_count FROM dataclear WHERE reportTime='2019-06-13') AS br_right_tab;

  
# NewIP
#   新增IP总数，一天之内新IP的数量
SELECT COUNT(DISTINCT dataclear.cip) AS newip FROM dataclear 
 WHERE dataclear.reportTime='2019-06-13' AND dataclear.cip NOT IN (
  SELECT DISTINCT inner_dataclear_tab.cip FROM dataclear AS inner_dataclear_tab WHERE DATEDIFF(
   '2019-06-13', inner_dataclear_tab.reportTime) > 0);

# NewCust
#   新增客户总数，一天之内新用户的数量。
SELECT COUNT(DISTINCT dataclear.uvid) AS newcust FROM dataclear WHERE dataclear.reportTime='2019-06-13' AND dataclear.uvid NOT IN 
 (SELECT inner_dataclear_tab.uvid FROM dataclear AS inner_dataclear_tab WHERE DATEDIFF('2019-06-13', inner_dataclear_tab.reportTime) > 0);

# AvgTime
#   平均访问时长，一天之内所有会话访问时长的平均值
select avg(avgtime_tab.use_time) as avgtime from 
 (select max(sstime) - min(sstime) as use_time from dataclear where reportTime='2019-06-13' group by ssid) as avgtime_tab;

# AvgDeep
#   平均访问深度，一天内所有会话访问深度的平均值。
#   将一天内所有日志按照会话分组后，统计每个会话访问的页面去重后的总数为会话的访问深度，再求这些会话访问深度的平均值。
select round(avg(avgdeep_tab.deep),4) as avgdeep from 
 (select count(distinct urlname) as deep from dataclear where reportTime='2019-06-13' group by ssid) as avgdeep_tab;





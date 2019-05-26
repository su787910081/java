- # hive 建表语句

1.数据外部表建表语句：
create EXTERNAL table zebra (a1 string,a2 string,a3 string,a4 string,a5 string,a6 string,a7 string,a8 string,a9 string,a10 string,a11 string,a12 string,a13 string,a14 string,a15 string,a16 string,a17 string,a18 string,a19 string,a20 string,a21 string,a22 string,a23 string,a24 string,a25 string,a26 string,a27 string,a28 string,a29 string,a30 string,a31 string,a32 string,a33 string,a34 string,a35 string,a36 string,a37 string,a38 string,a39 string,a40 string,a41 string,a42 string,a43 string,a44 string,a45 string,a46 string,a47 string,a48 string,a49 string,a50 string,a51 string,a52 string,a53 string,a54 string,a55 string,a56 string,a57 string,a58 string,a59 string,a60 string,a61 string,a62 string,a63 string,a64 string,a65 string,a66 string,a67 string,a68 string,a69 string,a70 string,a71 string,a72 string,a73 string,a74 string,a75 string,a76 string,a77 string) partitioned by (reportTime string) row format delimited fields terminated by '|' stored as textfile location '/zebra';


2.增加分区操作
执行：ALTER TABLE zebra add  PARTITION (reportTime='2018-03-04') location '/zebra/reportTime=2018-03-04';

自动分区: msck repair table zebra;


3.执行查询，看是否能查出数据
可以通过抽样语法来检验：select * from zebra TABLESAMPLE (1 ROWS);


4.清洗数据，从原来的77个字段变为23个字段
建表语句：
create table dataclear(reporttime string,appType bigint,appSubtype bigint,userIp string,userPort bigint,appServerIP string,appServerPort bigint,host string,cellid string,appTypeCode bigint,interruptType String,transStatus bigint,trafficUL bigint,trafficDL bigint,retranUL bigint,retranDL bigint,procdureStartTime bigint,procdureEndTime bigint)row format delimited fields terminated by '|';

5.从zebra表里导出数据到dataclear表里（23个字段的值）
建表语句：
insert overwrite table dataclear select concat(reportTime,' ','00:00:00'),a23,a24,a27,a29,a31,a33,a59,a17,a19,a68,a55,a34,a35,a40,a41,a20,a21 from zebra;

6.处理业务逻辑，得到dataproc表
建表语句：
create table dataproc (reporttime string,appType bigint,appSubtype bigint,userIp string,userPort bigint,appServerIP string,appServerPort bigint,host string,cellid string,attempts bigint,accepts bigint,trafficUL bigint,trafficDL bigint,retranUL bigint,retranDL bigint,failCount bigint,transDelay bigint)row format delimited fields terminated by '|';

7.根据业务规则，做字段处理
建表语句：
insert overwrite table dataproc select reporttime,appType,appSubtype,userIp,userPort,appServerIP,appServerPort,host,
if(cellid == '',"000000000",cellid),if(appTypeCode == 103,1,0),if(appTypeCode == 103 and find_in_set(transStatus,"10,11,12,13,14,15,32,33,34,35,36,37,38,48,49,50,51,52,53,54,55,199,200,201,202,203,204,205,206,302,304,306")!=0 and interruptType == 0,1,0),if(apptypeCode == 103,trafficUL,0), if(apptypeCode == 103,trafficDL,0), if(apptypeCode == 103,retranUL,0), if(apptypeCode == 103,retranDL,0), if(appTypeCode == 103 and transStatus == 1 and interruptType == 0,1,0),if(appTypeCode == 103, procdureEndTime - procdureStartTime,0) from dataclear;

8.查询关心的信息，以应用受欢迎程度表为例：
建表语句：
create table D_H_HTTP_APPTYPE(hourid string,appType int,appSubtype int,attempts bigint,accepts bigint,succRatio double,trafficUL bigint,trafficDL bigint,totalTraffic bigint,retranUL bigint,retranDL bigint,retranTraffic bigint,failCount bigint,transDelay bigint) row format delimited fields terminated by '|';

9.根据总表dataproc,按条件做聚合以及字段的累加
建表语句：
insert overwrite table D_H_HTTP_APPTYPE select reporttime,apptype,appsubtype,sum(attempts),sum(accepts),round(sum(accepts)/sum(attempts),2),sum(trafficUL),sum(trafficDL),sum(trafficUL)+sum(trafficDL),sum(retranUL),sum(retranDL),sum(retranUL)+sum(retranDL),sum(failCount),sum(transDelay)from dataproc group by reporttime,apptype,appsubtype;




- # zebra 建表语句

===================================================================
#创建数据库
create database zebra;
use zebra;
#创建数据总表
create table F_HTTP_APP_HOST(
	reporttime datetime,
	apptype int,
	appsubtype int,
	userip varchar(20),
	userport int,
	appserverip varchar(20),
	appserverport int,
	host varchar(255),
	cellid varchar(20),
	attempts bigint,
	accepts bigint,
	trafficul bigint,
	trafficdl bigint,
	retranul bigint,
	retrandl bigint,
	failcount bigint,
	transdelay bigint
);

#创建应用欢迎度表
create table D_H_HTTP_APPTYPE(
	hourid datetime,
	apptype int,
	appsubtype int,
	attempts bigint,
	accepts bigint,
	succratio bigint,
	trafficul bigint,
	trafficdl bigint,
	totaltraffic bigint,
	retranul bigint,
	retrandl bigint,
	retrantraffic bigint,
	failcount bigint,
	transdelay bigint
);

#创建各网站表现表
create table D_H_HTTP_HOST(
	hourid datetime,
	host varchar(255),
	appserverip varchar(20),
	attempts bigint,
	accepts bigint,
	succratio bigint,
	trafficul bigint,
	trafficdl bigint,
	totaltraffic bigint,
	retranul bigint,
	retrandl bigint,
	retrantraffic bigint,
	failcount bigint,
	transdelay bigint
);

#创建小区HTTP上网能力表
create table D_H_HTTP_CELLID(
	hourid datetime,
	cellid varchar(20),
	attempts bigint,
	accepts bigint,
	succratio bigint,
	trafficul bigint,
	trafficdl bigint,
	totaltraffic bigint,
	retranul bigint,
	retrandl bigint,
	retrantraffic bigint,
	failcount bigint,
	transdelay bigint
);

#创建小区上网喜好表
create table D_H_HTTP_CELLID_HOST(
	hourid datetime,
	cellid varchar(20),
	host varchar(255),
	attempts bigint,
	accepts bigint,
	succratio bigint,
	trafficul bigint,
	trafficdl bigint,
	totaltraffic bigint,
	retranul bigint,
	retrandl bigint,
	retrantraffic bigint,
	failcount bigint,
	transdelay bigint
);
===================================================================
insert into D_H_HTTP_APPTYPE
	select 
		reporttime as hourid,
		apptype,
		appsubtype,
		sum(attempts) as attempts,
		sum(accepts) as accepts,
		sum(accepts)/sum(attempts) as succRatio,
		sum(trafficul) as trafficul,
		sum(trafficdl) as trafficdl,
		sum(trafficul)+sum(trafficdl) as totaltraffic,
		sum(retranul) as retranul,
		sum(retrandl) as retrandl,
		sum(retranul)+sum(retrandl) as retrantraffic,
		sum(failcount) as failcount,
		sum(transdelay) as transdelay
	from 
		F_HTTP_APP_HOST
	group by 
		reporttime,apptype,appsubtype
;

insert into D_H_HTTP_HOST
	select 
		reporttime as hourid,
		host,
		appserverip,
		sum(attempts) as attempts,
		sum(accepts) as accepts,
		sum(accepts)/sum(attempts) as succRatio,
		sum(trafficul) as trafficul,
		sum(trafficdl) as trafficdl,
		sum(trafficul)+sum(trafficdl) as totaltraffic,
		sum(retranul) as retranul,
		sum(retrandl) as retrandl,
		sum(retranul)+sum(retrandl) as retrantraffic,
		sum(failcount) as failcount,
		sum(transdelay) as transdelay
	from 
		F_HTTP_APP_HOST
	group by 
		reporttime,host,appserverip
;

insert into D_H_HTTP_CELLID
	select 
		reporttime as hourid,
		cellid,
		sum(attempts) as attempts,
		sum(accepts) as accepts,
		sum(accepts)/sum(attempts) as succRatio,
		sum(trafficul) as trafficul,
		sum(trafficdl) as trafficdl,
		sum(trafficul)+sum(trafficdl) as totaltraffic,
		sum(retranul) as retranul,
		sum(retrandl) as retrandl,
		sum(retranul)+sum(retrandl) as retrantraffic,
		sum(failcount) as failcount,
		sum(transdelay) as transdelay
	from 
		F_HTTP_APP_HOST
	group by 
		reporttime,cellid
;

insert into D_H_HTTP_CELLID_HOST
	select 
		reporttime as hourid,
		cellid,
		host,
		sum(attempts) as attempts,
		sum(accepts) as accepts,
		sum(accepts)/sum(attempts) as succRatio,
		sum(trafficul) as trafficul,
		sum(trafficdl) as trafficdl,
		sum(trafficul)+sum(trafficdl) as totaltraffic,
		sum(retranul) as retranul,
		sum(retrandl) as retrandl,
		sum(retranul)+sum(retrandl) as retrantraffic,
		sum(failcount) as failcount,
		sum(transdelay) as transdelay
	from 
		F_HTTP_APP_HOST
	group by 
		reporttime,cellid,host
;

===================================================================
#总应用欢迎度前10
	select 
		apptype,
		DATE_FORMAT(hourid,'%Y%m%d') dateid,
		sum(totalTraffic) 
	from 
		D_H_HTTP_APPTYPE 
	group by 
		apptype,dateid
	having
		dateid ='20150615'
	order by
		sum(totaltraffic) desc
	limit 0,10
	;
	
	
#子应用欢迎度前10
	select 
		apptype,
		appsubtype,
		DATE_FORMAT(hourid,'%Y%m%d') dateid,
		sum(totalTraffic) 
	from 
		D_H_HTTP_APPTYPE 
	group by 
		apptype,appsubtype,dateid
	having
		dateid='20150615'
		and
		apptype=15
	order by
		sum(totaltraffic) desc
	limit 0,10
	;

#网站表现前10
	select 
		host,
		DATE_FORMAT(hourid,'%Y%m%d') dateid,
		sum(attempts) 
	from 
		D_H_HTTP_HOST 
	group by 
		host,dateid
	having
		dateid='20150615'
	order by
		sum(attempts) desc
	limit 0,10
	;

#单网站一天表现
	select 
		host,
		hourid,
		sum(attempts)
	from 
		D_H_HTTP_HOST 
	group by 
		host,hourid
	having
		host='apilocate.amap.com'
	order by
		attempts desc
	;

#总小区上网能力
	select 
		DATE_FORMAT(hourid,'%Y%m%d') dateid,
		cellid,
		sum(totaltraffic)
	from	
		D_H_HTTP_CELLID
	group by
		dateid,cellid
	having
		dateid='20150615'
	limit 0,10;

#指定小区上网能力
	select 
		hourid,
		cellid,
		sum(totaltraffic)
	from	
		D_H_HTTP_CELLID
	group by
		cellid,hourid
	having
		DATE_FORMAT(hourid,'%Y%m%d')='20150615'
		and
		cellid='131432478';

============================================================


# web前台可视化数据表
create table D_H_HTTP_APPTYPE(
	hourid datetime,
	apptype int,
	appsubtype int,
	attempts bigint,
	accepts bigint,
	succratio double,
	trafficul bigint,
	trafficdl bigint,
	totaltraffic bigint,
	retranul bigint,
	retrandl bigint,
	retrantraffic bigint,
	failcount bigint,
	transdelay bigint
);
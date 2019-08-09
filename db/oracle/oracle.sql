

日期处理：

当前日期：
	SELECT sysdate  FROM dual; 
日期加减：
	前一天: SELECT TO_CHAR(sysdate - 1, 'yyyy-MM-dd') FROM dual;
与当前表字段的处理:
	SELECT * FROM t WHERE col_time > trunc(sysdate); 










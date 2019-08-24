第一种 
这种方法传入的 参数应该是个时间戳带格式的 

and TO_CHAR(列名, ‘YYYY-MM-DD’) >= TO_CHAR(#{leaveBeginDate,jdbcType=TIMESTAMP},’YYYY-MM-DD’) 


and TO_CHAR(列名, ‘YYYY-MM-DD’) <= TO_CHAR(#{leaveEndDate,jdbcType=TIMESTAMP},’YYYY-MM-DD’) 
第二种 
这种方式传入的参数类型是字符串类型的 

and 列名 <= to_date(#{parameDate},’yyyy-mm-dd’) 

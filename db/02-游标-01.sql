T-SQL中的游标定义在MSDN中如下:

DECLARE cursor_name CURSOR [ LOCAL | GLOBAL ] 
     [ FORWARD_ONLY | SCROLL ] 
     [ STATIC | KEYSET | DYNAMIC | FAST_FORWARD ] 
     [ READ_ONLY | SCROLL_LOCKS | OPTIMISTIC ] 
     [ TYPE_WARNING ] 
     FOR select_statement 
     [ FOR UPDATE [ OF column_name [ ,...n ] ] ]
[;]

=============================================





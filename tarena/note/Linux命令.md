find 查找文件
    find /etc -name init*
    -name   -- 搜文件名，通配符 *、?
    -iname  -- 忽略大小写
    -size   按文件大小查找(块大小)
        +块数，大于...
        -块数，小于...
            一个块等于512 字节
        -size +20800 大于20800 * 512 字节
        -size  20800 等于20800 * 512 字节
        -size -20800 小于20800 * 512 字节
    -user  --  按r所有者查找
        find -user root
    -group  -- 按所属组查找
        find -group xxx
    -amin   access minutes, 按访问时间查找
    -cmin  change 属性更改
    -mmin  modify, 内容修改
    -type  f(普通文件)、d(目录)、l(软链接)
    -a    and  逻辑组合
    -o  or 逻辑组合
    -exec 
    -ok 对查找结果直接执行操作命令
        ... -exec ls -l {} \;
        ... -ok ls -l {} \;
            需要用户确认
            "{} \;"  最后的这一段是固定格式，{} 表示find 出来的结果

su 切换用户
    su root     使用原来用户的环境变量
    su - root 使用root 用户的环境变量


tar 打包文件
		tar -zcf file.tar.gz dir 
    压缩目录
		tar -zxvf file.tar.gz
    解压缩目录
	    -c 创建打包文件
	    -C 指定将文件解压到哪个目录
	    -v 显示详细信息
	    -f 指定文件名
	    -z 打包的同时压缩成 gz 格式
	    -j 打包同时压缩成 bzip2 格式
	    -x 解包
	    -z 解压缩

zip 压缩与解压缩 
	zip file.zip file
	zip -r dir.zip dir
	unzip file.zip
	
gzip 压缩 
    a 压缩生成a.gz, 同时删除原文件



grep
	文件内容过滤，找到符合条件的那一行
    	-i  忽略大小写
    	-v 排除 ... -v ^#
	可以处理其他命令的处理结果，通过 “通道”给grep  处理。
    	grep /etc/passwd root
    	ls -l /etc | grep ^d            将ls -l 的结果，交给grep ，grep 查找以d 开头(^)的行
    	ls -l /etc | grep ^d | wc -l    wc -l 统计行数
    	ls -l /etc | wc -l          	统计所有行数


ping -c 4 192.168.xxx.xxx  指定发送次数
last 
	列出用户登录信息

traceroute
	数据包到主机间的路径
	traceroute www.baidu.com
netstat
	显示网络信息
	-t tcp
	-u udp
	-l 监听
	-r 路由
	-n ip、端口
	-tlun 本机监听的端口
	-an  本机所有网络连接
	-rn 本机路由表
mount
	挂载
	-t 文件系统
	mout -t iso9660 /dev/cdrom(硬件)  /mnt/cdrom(目录)
umount
	卸载 设备
	umount /dev/cdrom
shutdown
	关机
	-h 指定时间 -h now 或者 -h 20:00
	-r 重启 -r now 或者 -r 20:00
		reboot  立即重启
	-c 取消预订的关机命令
vim 
	:%s/aaa/bbb/g 不询问
	:%s/aaa/bbb/c  询问确认
			全文替换，aaa 替换成bbb
	:n1,n2s/aaa/bbb/g
	:n1,n2s/aaa/bbb/c
		指定行范围内替换
	:set ic 不区分大小写
	:set noic 区分大小写





- ## 防炎墙
	- > 临时关闭
		>> service iptables stop
	- > 永久关闭
		>> chkconfig iptables off




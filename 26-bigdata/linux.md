



- # 修改设置时区
    - > `date -R` 可以查看当前系统时区
    - > 使用命令: `tzselect`
        > - 依次选择： `5) Asia` -> `9) China`  ->  `1) Beijing Time`  -> `1) Yes`
        > - 此时会有提示
        >>      You can make this change permanent for yourself by appending the line
        >>      	TZ='Asia/Shanghai'; export TZ
        >>      to the file '.profile' in your home directory; then log out and log in again.
        >>      
        >>      Here is that TZ value again, this time on standard output so that you
        >>      can use the /usr/bin/tzselect command in shell scripts:
        >>      Asia/Shanghai
        > - `TZ='Asia/Shanghai'; export TZ`
        > - 我这里把它放到了`/etc/profile` 文件中
    - > 如果时间还不一致，那么可以使用如下命令来与上海时间进行同步。但是似乎需要联网
        > - `ntpdate -u ntp.api.bz`


- # NTP
    - > 同步时间
        > - 设置开机启动: `chkconfig ntpd on`
        > - 设置时间同步：`ntpdate -u ntp.api.bz`

- ## 免密登录
    - > 为每台机器配置ssh免秘钥登录
        > - `ssh-keygen`
        > - `ssh-copy-id root@hadoop01`  （分别发送到其他节点上）



- 关闭防火墙
    - > CentOS 6
        > - `service iptables stop`     临时停止
        > - `chkconfig iptables off`    永久禁止
    - > CentOS 7
        > - `systemctl stop firewalld.service`  临时停止
        > - `systemctl disable firewalld.service`   永久禁止

nc -lk 9999




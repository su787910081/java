






- 关闭防火墙
    - > CentOS 6
        > - `service iptables stop`     临时停止
        > - `chkconfig iptables off`    永久禁止
    - > CentOS 7
        > - `systemctl stop firewalld.service`  临时停止
        > - `systemctl disable firewalld.service`   永久禁止

nc -lk 9999




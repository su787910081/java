


- ## NGINX 是一个高性能的HTTP 的反向代理服务器
    > - 启动测试
    >> - 当我们将nginx 启动起来之后，我们可以在浏览器中输入服务的IP 地址(`localhost`)，它默认监听80 端口，也会返回一个默认页面，只要返回了一个正常的页面就说明启动成功了。
    > - 使用: 
    >> - 通常情况下，我们通过浏览器去访问`nginx` 服务，然后，`nginx` 服务会根据配置，将请求转到了内部实际的tomcat 服务，然后由tomcat 处理请求，最后响应请求。
    >>> <details>
    >>> <summary><mark><font color=darkred>图示</font></mark></summary>
    >>> 
    >>> ![](./img/nginx-01.jpg)
    >>>
    >>> </details>

- ### NGINX 的安装
    > - windows 下，有一个压缩文件，直接解压就可以使用了。
    > - linux 下, 使用yum 
    >> - `yum install nginx`
    >> - 使用`whereis nginx` 可以查看它所在的位置

- ### 配置文件
    > - 在根目录下面的`conf/nginx.conf`
    >> - `upstream` 它为一个域名指定负载均衡的目标tomcat 地址，以及权重值
    >> - 下面为"jt1803" 的域管理三台tomcat 服务喊叫的负载均衡
    >>> <details>
    >>> <summary><mark><font color=darkred>upstream</font></mark></summary>
    >>> 
	>>>     upstream jt1803 {
	>>>     	server 192.168.142.128:8091 weight=1;
	>>>     	server 192.168.142.128:8092 weight=2;
	>>>     	server 192.168.142.128:8093 weight=5;
	>>>     }
    >>> </details>
    >>> 
    >> - `server` 配置监听浏览器上的访问域名以及端口
    >> - 当访问`www.tomcat.suyh.com:80/index` 时，将会转向`http://jt1803/index` 这个`jt1803` 就是`upstream` 中定义的域名，它将从`upstream` 中的多个中选择一个。
    >>> <details>
    >>> <summary><mark><font color=darkred>server</font></mark></summary>
    >>> 
    >>>     server {
    >>>         listen 80;
    >>>         server_name www.tomcat.suyh.com;
    >>>         location / {
    >>>             proxy_pass http://jt1803;
    >>>             proxy_connect_timeout 600;
    >>>             proxy_read_timeout 600;
    >>>         }
    >>>     }
    >>> </details>
    >>>
    >> - `server 中的 location` 域名后所有携带的字符串,都是配置数据,当满足监听条件时,location是路径匹配规则,满足匹配规则,将会进入location内容部大括号内容, / 表示任何地址
    >>> <details>
    >>> <summary><mark><font color=darkred>location</font></mark></summary>
    >>> 
    >>> - / 表示所有路径都可以匹配;
    >>> - 多种匹配规则,就明确顺序关系,有明确优先级关系,一个server的虚拟机服务器中,可以配置多个location
    >>> - • 精确匹配 =
    >>> - • 字符串前缀匹配 ^~
    >>> - • 正则匹配 ~
    >>> - 按照配置顺序的正则匹配(AB条件都是正则,同时满足条件时,上面的配置优先于下边)
    >>> - 
    >>> - • 不带任何修饰的前缀匹配 /images/
    >>> - • 当所有匹配成功时,停止匹配,按照当前匹配优先级处理
    >>> - • 前缀匹配,有包含关系时,按照最大匹配长度原则确定
    >>> - • 最低优先级的匹配符就是 "/
    >>>>        server{
    >>>>            location = /images{
    >>>>                return 205;
    >>>>            }
    >>>>            location ^~ /images{ //域名后的地址以/images开始
    >>>>                return 200;
    >>>>            }
    >>>>            location ^~/images/test{
    >>>>                return 201;
    >>>>            }
    >>>>        
    >>>>            location ~ *.png${
    >>>>            	return 203
    >>>>            }
    >>>>            location ~ *.(gif|png|jpg)${
    >>>>                return 202
    >>>>            }
    >>>>            location /{
    >>>>                return 204;
    >>>>            }
    >>>>        }
    >>>> - http://locahost/ 返回204
    >>>> - http://locahost/images 返回205
    >>>> - http://locahost/images/id 返回200
    >>>> - http://locahost/images/tes 返回200
    >>>> - http://locahost/images/test 返回201
    >>>> - http://locahost/test.png; 返回203
    >>> </details>
    >>>





- ## Linux 下的Nginx 的启动
    - > 直接到bin 目录下面运行 nginx 就可以了。






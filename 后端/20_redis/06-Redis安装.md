
# Redis 安装

- ## 安装
	- > ### 安装redis
	- > ### 安装ruby
		>> - `# yum install centos-release-scl-rh`
		>>> - 会在/etc/yum.repos.d/目录下多出一个CentOS-SCLo-scl-rh.repo源
		>> - `# yum list rh-ruby*`
		>>> - 可以查看到有哪些版本
		>> - `# yum install rh-ruby26  -y`
		>>> - 直接yum安装
		>> - `# scl  enable  rh-ruby26 bash`  这种模式安装的ruby 每次打开bash 都需要执行一次。否则ruby 的版本就是 较低的
		>>> - 必要一步，很重要
		>>> - 软件集合的软件包安装在/opt目录下， 要使用它，需要加载环境变量
		>> - `# ruby -v`
		>>> - 查看安装版本
		>> - `# vim /etc/bashrc` 添加如下内容，否则ruby 的版本会降低
		>>> - source /opt/rh/rh-ruby26/enable
		>>> - export X_SCLS="`scl enable rh-ruby26 'echo $X_SCLS'`"
		>>> - export PATH=$PATH:/opt/rh/rh-ruby26/root/usr/local/bin
	- > ### 安装 rubygems
		>> - `# yum -y install rubygems`
		>> - `# gem sources --add https://gems.ruby-china.com/ --remove https://rubygems.org/`
		>>> - 修改默认下载源为 Ruby-China 不然在国外的网络我们有时候访问不了。
		>> - `# gem sources -l`
		>>> - 查看并确保是否只有一个结果: `https://gems.ruby-china.com`
	- > ### 最后一步
		>> - `gem install redis`
	- > ### 接下来就可以使用redis集群了


- ## 安装redis
	- > ### 下载对应的源码
		>> - `$ redis]# tar -xvf redis-3.2.11` 解压
		>> - `$ cd redis-3.2.11`
		>> - `$ make -j4` 编译，编译好的程序都被放在了 src/ 目录下面。
		>>> - 在src/ 目录下面有几个可执行程序是有用的，以及在外面目录的 redis.conf 配置文件有用
		>>> - 所以我们可以把它们拷贝出来备用
		>>> - `$ mkdir -p /usr/local/redis/etc`
		>>> - `$ mkdir -p /usr/local/redis/bin`
		>>> - `$ cp mkreleasehdr.sh redis-benchmark redis-check-aof redis-check-rdb redis-cli redis-sentinel redis-server redis-trib.rb /usr/local/redis/bin/`
		>>>> - 将src/ 目录下可执行程序和脚本拷贝到bin目录
		>>> - `$ cp redis.conf /usr/local/redis/etc`
		>>>> - 将源目录下面的 redis.conf 配置文件拷贝到 etc 目录
		>> - `$ cd src/`
		>> - `$ make install`  在src/ 目录进行安装
		>> - `$ cp src/redis-trib.rb /usr/local/bin/`
		>>> - 将集群所需要用到的脚本放到PATH 路径下面

- ## 安装Ruby(非必须-但是后面集群好像要用到) 这个最好还是源码安装一个较高版本，否则版本过低。gem 不能使用。
    - ### Redis集群命令文件需要Ruby 语言的支持
    - > 下载Ruby 安装文件
        > - `ruby-2.3.1.tar.gz`
    - > 解压并安装
        > - `tar -xvf ruby-2.3.1.tar.gz`  解压
        > - `cd ruby-2.3.1` 进入目录
        > - `./configure`   自动配置
        > - `make -j4`      使用4 线程 编译
        > - `make install`  安装

- ## 安装gems(未成功) 集群时需要
    - > ### RubyGems（简称 gems）是一个用于对 Ruby组件进行打包的 Ruby打包系统。 
        >> - 它提供一个分发 Ruby 程序和库的标准格式，还提供一个管理程序包安装的工具
        >> - 简单理解就是ruby运行时,需要的各种插件都在gems里
    - > `# yum -y install rubygems`
	- > `# gem sources --add https://gems.ruby-china.com/ --remove https://rubygems.org/`
		>> - 修改默认下载源为 Ruby-China 不然在国外的网络我们有时候访问不了。
	- > `# gem sources -l`
		>> - 查看并确保是否只有一个结果: `https://gems.ruby-china.com`
    - > `gem install redis`
		- ### 报错ruby 版本过低
		> - 至少我们要使用 ruby 2.3 以上的才可以
        - ### 报错: cannot load such file -- zlib
        > - `yum -y install zlib zlib-devel`







安装rubygem redis依赖

wget http://rubygems.org/downloads/redis-3.3.0.gem

gem install -l redis-3.3.0.gem










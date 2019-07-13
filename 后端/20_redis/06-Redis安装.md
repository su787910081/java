
# Redis 安装

- ## 安装Ruby
    - ### Redis集群命令文件需要Ruby 语言的支持
    - > 下载Ruby 安装文件
        > - `ruby-2.3.1.tar.gz`
    - > 解压并安装
        > - `tar -xvf ruby-2.3.1.tar.gz`  解压
        > - `cd ruby-2.3.1` 进入目录
        > - `./configure`   自动配置
        > - `make -j4`      使用4 线程 编译
        > - `make install`  安装

- ## 安装gems(未成功)
    - > ### RubyGems（简称 gems）是一个用于对 Ruby组件进行打包的 Ruby打包系统。 
        >> - 它提供一个分发 Ruby 程序和库的标准格式，还提供一个管理程序包安装的工具
        >> - 简单理解就是ruby运行时,需要的各种插件都在gems里
    - > `yum -y install rubygems`
    - > `gem install redis`
        - ### 报错: cannot load such file -- zlib
        > - `yum -y install zlib zlib-devel`

安装rubygem redis依赖

wget http://rubygems.org/downloads/redis-3.3.0.gem

gem install -l redis-3.3.0.gem










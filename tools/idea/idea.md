
# maven 更新
## 更新失败，长时间更新不下来
- > 清空缓存，然后全部重新拉取
    > - `file` -> `Invalidate Caches/Restarts...`

# maven 项目识别
## 有时候项目没有被识别为maven 或者maven 项目没有三角形的启动图标时
- > 以maven 项目来识别
	> - 右键项目中的`pom.xml` -> `Add as Maven Project`

# 常用
## 将非项目文件排除
- > 排除".idea" 目录
    > - `file` -> `settings` -> `File Types` -> 在编辑框中添加
## 自动添加 `serialVersionUID` 的值
- > `file -> settings...`
    > - 搜索 ` inspections`
    > - 在右边框中继续搜索 `serialVersionUID`
    >> - 搜索结果会是: java -> Serialization issues
    >>> - 然后下面有4 个可选项
    >>>> 
    >>>>     Non-Serializable class with serialVersionUID
    >>>>     Serializable class without serialVersionUID
    >>>>     Serializable non-'static' inner class without serialVersionUID
    >>>>     serialVersionUID field not declared 'private static final long'
    >>> - 在上面的四个选项中勾选第二和第四个即可
    >>> - 然后在使用的时候，只要继承了`Serializable` 类，就可以使用快捷键`Alt + Enter` 就会弹出"Add 'serialVersionUID' field"

# 快捷键

## 修改快捷键
- > 代码提示快捷键
    > - `file -> Settings...`
    > - 搜索 `Keymap` 并选中
    > - 搜索 `Cyclic Expand Word` 先将这个快捷键空出来
    > - 右击 选择 `Remove Alt+/`
    > - 重新搜索 `Basic`
    > - 找到 `Code -> Completion -> Basic` 
    > - 并右键选择 `Add Keyboard Shortcut`

- > 代码提示补充大小写忽略
    > - `file -> Settings...`
    > - 搜索 `Code Completion`
    > - `Match case` 取消勾选

- > 自动补全代码快捷键
    > - `Ctrl + Alt + t`
    >> - `if ... else `
    >> - `try ... catch ...`
	
- > 查看调用链
	> - `Alt + F7` 或者 `Ctrl + Alt + h`


# 插件
## 翻译
- > 添加翻译插件
    > -  `file -> Settings...`
    > - `Plugins`
    > - 搜索安装: `translation`
- > `p3c`
    > - `NR Coding Style`
- > `mybatis plus`
    > - `iBATIS/MyBatis mini-plugin`

# 导出jar

## 参考博客
- > `https://blog.csdn.net/chenpuzhen/article/details/82252449`
- > 步骤
    > - `file -> Project Structure...`
    > - 左侧选中 `Artifacts`
    > - 中间点击 `+` -> `Jar -> From modules with dependencies...`
    > - 在弹出框中选择要导出的`Module` 和`Main Class` 以及`META-INF/MANIFEST.MF` 这个指定为项目的根目录
    > - 指定打好的包的输出目录位置以及项目名称
    > - 勾选`Build on make`
    > - 将一些不需要的第三方包删除
    > - 点击OK结束配置
    > - 退出配置之后，构建项目  `Build  ->  Build Artifacts...` 
    > - 选择指定的构建项目名，然后选择`Build`即可

## install打包
- > 打包输出路径
    > - 这个路径我不知道在哪里配置，但是使用 install 之后在下面的日志中会有提示打包在哪个位置去了。









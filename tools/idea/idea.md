

# 快捷键

## 修改快捷键
- > 代码提示快捷键
    > - `file -> Settings...`
    > - 搜索 `Keymap` 并选中
    > - 搜索 `Cyclic Expand Word` 
    > - 右击 选择 `Remove Alt+/`
    > - 重新搜索 `Basic`
    > - 找到 `Code -> Completion -> Basic` 
    > - 并右键选择 `Add Keyboard Shortcut`



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









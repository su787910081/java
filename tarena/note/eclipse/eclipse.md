## eclipse 的一些使用及技巧

### eclipse 中的一些常用快捷键
- `alt + /`
	> API 类等的提示
- `ctrl + /`
	> 单行注释与取消
- `ctrl + shift + /`
	> 多行注释
- `ctrl + shift + \`
	> 取消多行注释
- `ctrl + shift + t`
	> 快速查找类对象
- `ctrl + t `
	> 查看类的详细信息，然后可以搜索这个类的所有属性和方法<br>
	> 查看类的方法及属性<br>
	> 查看此类的子类及父类<br>
	> 选中一个类然后使用 `ctrl + t `<br>
- `ctrl+o`
	> 查看类中的方法，假如想看类中父类的方法也可以继续`ctrl+o`
- `ctrl+n`
	> 调出新建窗口
- `ctrl+shift+o`
	> 快速导入包
	
## 如何导入一个已存在的工程到eclipse
> `file --> import...`<br>
> 搜索出: `Existing Projects into Workspace`


- 修改eclipse 中项目的JDK 版本
	> `右键项目 -->  Build Path  -> Configure Build Path...`  --> `Libraries  -->  JRE System Library [xxx]  --> Edit...` <br>

- 修改eclipse 中jsp 文件的默认编码格式
	> `Window --> Preferences --> JSP Files` 

- 项目有小红叉，但是找不到问题的时候。
	1. `Project --> Clean...`  强制清理，然后让其重新编译
	2. `右键项目  -->  Maven  -->  Update Project...  -->  Update dependencies  -->  Force project Of Snapshots/Release ` 强制更新依赖
	3. `右键项目  -->  Run As  -->  Maven clean`; 将编译的目标文件全部清理，然后eclipse 将会重新为我们生成





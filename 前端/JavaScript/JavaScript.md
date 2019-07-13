

- 匿名函数
    > - 匿名函数的定义
    >>      (function(){ console.log("hello world")});
    > - 但是上面这样来定义一个匿名函数我们定义了却访问不到所以我们基本不会这样去定义一个匿名函数。
    > - 正常情况下定义一个匿名函数之后都是直接使用。
    > - 直接使用的时候，只是比定义时多了一个小括号而以。
    >>      (function(){ console.log("hello world")}());

- 将一个局部 变量提升为全局变量
    > - 定义一个局部变量，并提升到全局使用`window`
    >>      (function() {
    >>          var tree = function(id, name) {
    >>              this.id = id; this.name=name;
    >>          }; 
    >>          window.tree=tree;
    >>      }())
    > - 然后我们就可以像这样来使用它
    >>     new tree(10, 'a');
    > - 如果我们需要为`tree` 这个类创建一些方法的话则需要借助`tree.prototype = {}` 然后在`{}` 中添加属性方法，如下:
    >>     (function() { 
    >>          var tree = function(id, name) { 
    >>              this.id = id; this.name = name; 
    >>          }; 
    >>          tree.prototype = { 
    >>              setId: function(id) { this.id = id; },
    >>              setName: function(name) { this.name = name; }
    >>          }; 
    >>          window.tree = tree; 
    >>      }())








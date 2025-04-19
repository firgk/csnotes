

## json
轻量级数据交换格式
完全独立于编程语言
广泛运用与前后端交互



## xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<bookstore>
  <book category="children">
    <title>Harry Potter</title>
    <author>J.K. Rowling</author>
    <year>2005</year>
  </book>
  <book category="web">
    <title>Learning XML</title>
    <author>Erik T. Ray</author>
    <year>2003</year>
  </book>
</bookstore>
```
类似于html语法，但是可以自定义标签
用于 数据传输 数据交换 和 数据存储



## 编码
gbk和utf-8这两种编码的区别
utf-8编码包含全世界所有国家需要用的字符，它比较灵活，长度在1-6个字节，utf-8编码格式很强大，支持所有国家的语言，正是因为它的强大，才会导致它占用的空间大小要比gbk大，对于网站打开速度而言，也是有一定影响的。
gbk编码主要用中文编码，包含全部中文字符，gbk的长度为2个字节，所以和gbk相比，utf-8会占用更多的数据库储存空间。对于gbk编码格式，虽然它的功能少，仅限于中文字符，但它所占用的空间大小会随着它的功能而减少，打开网页的速度比较快。







## maven
最短路径优先
A -> B -> C -> X(1.0)
A -> D -> X(2.0)
由于 X(2.0) 路径最短，所以使用 X(2.0)。

声明顺序优先
A -> B -> X(1.0)
A -> C -> X(2.0)
在 POM 中最先声明的优先，上面的两个依赖如果先声明 B，那么最后使用 X(1.0)。

覆写优先原则 (特殊优先原则)
子 POM 内声明的依赖优先于父 POM 中声明的依赖。



构件 
每一个构件都可以使用 Maven 坐标唯一标识
groupId
artifactId maven项目名
version
同一个 groupId 同一个 artifactId 下，只能使用一个 version

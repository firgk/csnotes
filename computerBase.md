



###  二进制无法精确地表示某些十进制分数，

一个经典的例子是 1/10
二进制表示为1/2+1/4+1/8+...位数够多的话可以十分接近，但是不能完全精确



### json

轻量级数据交换格式
完全独立于编程语言
广泛运用与前后端交互

### xml

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

类似于html语法，
用于 数据传输 数据交换 和 数据存储







### gbk和utf-8这两种编码的区别

utf-8编码包含全世界所有国家需要用的字符，它比较灵活，长度在1-6个字节，utf-8编码格式很强大，支持所有国家的语言，正是因为它的强大，才会导致它占用的空间大小要比gbk大，对于网站打开速度而言，也是有一定影响的。

gbk编码主要用中文编码，包含全部中文字符，gbk的长度为2个字节，所以和gbk相比，utf-8会占用更多的数据库储存空间。对于gbk编码格式，虽然它的功能少，仅限于中文字符，但它所占用的空间大小会随着它的功能而减少，打开网页的速度比较快。




### 正则表达式



> http://www.cyc2018.xyz/%E5%85%B6%E5%AE%83/%E7%BC%96%E7%A0%81%E5%AE%9E%E8%B7%B5/%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F.html#%E4%B8%80%E3%80%81%E6%A6%82%E8%BF%B0
>
> csnotes github





```
[]中的任意一个
    特殊写法
    t[abcd]n 
    [a-z] [A-Z] [a-zA-Z] [0-9] [a-z0-9]

    
() 称为组
    t（a|e|i|io）n  除了“tan”“ten”和“tin”外，还可以匹配“tion”
```





```
“^”符号可以匹配一行的开始。
    “^Spring.*”匹配“Spring MVC”，而不匹配“a Spring MVC”。
    “^”符号在中括号内，则表示不需要参与匹配的字符。例如，正则表达式“[a-z&&[^bc]]”表示，可以匹配除b和c之外的小写字母等价于“[ad-z]”，



“$”符号可以匹配一行的结束
    “.*App$”中的“$”符号表示匹配以App结尾的字符串，可以匹配“Andriod App”



“\”符号表示其后的字符是普通字符而非元字符(正则表达式所用符号)。
    例如，正则表达式“\$”用来匹配“$”字符而非结束“\.”用来匹配“.”字符而非任一字符。
次数
```





```

X* 匹配X出现零次或多次，如Y，YXXXY
X+ 匹配X出现一次或多次，如YXY，YXX
X? 匹配X出现零次或一次，如Y，YXY
X{n} 匹配X出现恰好n次
X{n,} 匹配X出现至少出现n次
X{n,m} n<=m，匹配X出现至少n次，最多m次

```



```
\d	数字：[0-9]
\D	非数字： [^0-9]
\s	空白字符：[ \t\n\x0B\f\r]
\S	非空白字符：[^\s]
\w	单词字符：[a-zA-Z_0-9]
\b	单词边界
\B	非单词边界
\A	输入的开头
\G	上一个匹配的结尾
```


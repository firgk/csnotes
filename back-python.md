
# python2




大纲
重点：
1、三种基本程序结构的实现方法
2、各种数据类型的熟练使用、函数的定义与使用
3、Python数据库应用、Tkinter图形界面
难点：
1、函数的定义与使用
2、Tkinter图形界面
3、Python数据库应用


## 目录

第1章  Python语言介绍
1.1  Python语言简介
1.2  安装与配置Python环境
1.3  Python开发环境IDLE简介
1.4  Python基本输入输出
1.5  Python代码规范
第2章  Python语法基础
2.1  Python数据类型
2.2  常量和变量
2.3  运算符与表达式
2.4  序列数据结构
第3章  Python控制语句
3.1  选择结构
3.2  循环结构
3.3  常用算法及应用实例
3.4  游戏初步——猜单词游戏
第4章  Python函数与模块
4.1  函数的定义和使用
4.2  函数参数
4.3  闭包和函数的递归调用
4.4 内置函数
4.5  模块
第5章  Python文件的使用
5.1  文件
5.2 文件的访问
5.3 文件夹的操作
5.4  文件应用案例——游戏地图存储
第6章  面向对象程序设计
6.1  面向对象程序设计基础
6.2  类和对象
6.3  类的继承和多态
6.4  面向对象应用案例——扑克牌类设计
第7章  Tkinter图形界面设计
7.1  Python图形开发库
7.2  常用Tkinter 组件的使用
7.3  图形绘制
7.4  Tkinter字体
7.5  Python事件处理
7.6  图形界面程序设计的应用
第8章  Python数据库应用
8.1  数据库基础
8.2  结构化查询语言SQL
8.3  SQLite数据库简介
8.4  Python的SQLite3数据库编程
8.5  Python数据库应用案例
第9章 网络编程和多线程
第10章 科学计算和可视化应用







## 1认识python

Web开发，基于python的优秀web框架如Django
科学计算，各种实验数据的处理以及相关实验模拟等，机器学习等
网络编程，网页解析，如爬虫程序开发
数据库应用
多媒体开发
电子游戏开发



1．Web开发
      Python语言支持Web网站开发，比较流行的开发框架有web2py、django等。许多大型网站就是用Python开发的，例如YouTube、Instagram，还有国内的豆瓣。很多大公司，包括Google、Yahoo等，甚至NASA（美国航空航天局）都大量地使用Python。
      利用py2exe模块可以将Python程序转换为.exe可执行程序，使得Python程序可以脱离Python系统环境来运行。
    2．网络编程
　Python语言提供了socket模块，对Socket接口进行了二次封装，支持Socket接口的访问；还提供了urllib、cookielib、httplib、scrapy等大量模块，用于对网页内容进行读取和处理，并结合多线程编程以及其他有关模块可以快速开发网页爬虫之类的应用程序。
3．科学计算与数据可视化
　　Python中用于科学计算与数据可视化的模块有很多，例如NumPy、SciPy、SymPy、Matplotlib、Traits、TraitsUI、Chaco、TVTK、Mayavi、VPython、OpenCV等，涉及的应用领域包括数值计算、符号计算、二维图表、三维数据可视化、三维动画演示、图像处理以及界面设计等。　　　
4．数据库应用
　Python数据库模块有很多，例如，可以通过内置的sqlite3模块访问SQLite数据库，使用pywin32模块访问Access数据库，使用pymysql模块访问MySQL数据库，使用pywin32和pymssql模块来访问SQL Sever数据库。
5．多媒体开发
　　PyMedia模块是一个用于多媒体操作的Python模块，可以对包括WAV、MP3、AVI等多媒体格式文件进行编码、解码和播放；PyOpenGL模块封装了OpenGL应用程序编程接口，通过该模块可在Python程序中集成二维或三维图形；PIL（Python Imaging Library，Python图形库）为Python提供了强大的图像处理功能，并提供广泛的图像文件格式支持。
6．电子游戏应用
　　Pygame就是用来开发电子游戏软件的Python模块，在SDL库的基础上开发，可以支持多个操作系统。使用Pygame模块，可以在Python程序中创建功能丰富的游戏和多媒体程序。





Google 在其网络搜索系统中广泛应用了Python ，并且聘用了Python 的创作者。
YouTube 视频分享服务大部分是由Python 编写的。
流行的P2P 文件分享系统Bittorrent 是一个Python 程序。
Intel 、Cisco 、Hewlett-Packard 、Seagate 、Qualcomm 和IBM 使用Python 进行硬件测试。
Industrial Light & Magic 、Pixar 等公司使用Python 制作动画电影。
NASA 、Los Alamos 、Fermilab 、JPL 等使用Python 实现科学计算任务。
IRobot 使用Python 开发了商业机器人真空吸尘器。
ESRI 在其流行的GIS 地图产品中使用Python 作为终端用户的定制工具。
NSA 在加密和智能分析中使用Python 。



任何编程语言都有缺点，Python缺点主要有：
（1）运行速度慢。
（2）代码不能加密。  你发行的exe程序可以容易被得到python程序
（3）用缩进来区分语句关系的方式还是给很多初学者带来了困惑。即便是很有经验的Python程序员也可能陷入陷阱当中。最常见的情况是tab和空格的混用会导致错误。





常用的IDE有Notepad++，PyScripter，PyCharm，Eclipse with PyDev，Komodo，Wing IDE
可能需要设置utf-8中文编码，否则肯能会出现异常




### python基本输入输出

输入是通过input( )函数来实现的，input( )的一般格式为：

	a=input('提示：')

在Python 2.7中，使用print语句进行输出，而Python 3.5中使用print()函数进行输出。

	print(123,'abc',45,'book')
	123 abc 45 book

	print() 输出一个空格
	print(123,'abc',45,'book',sep='$')
	123$abc$45$book

print函数默认以回车换行符号为输出结尾，即在最后会换行，end可以指定最末尾

	print('price',end='=');print(100)
	price=100

3. 输出到文件
   print函数默认输出到标准输出流，输出到windows终端，可以用file参数指定输出到特定文件。
   ```python
   file1=open('pythontest.txt','w')
   print(123,'pythontest',45,'book',file=file1)
   file1.close()
   print(open('pythontest.txt').read())
   ```


```
for i in range(10): 
  print (i, end=' ')
```

Python程序是依靠代码块的缩进来体现代码之间的逻辑关系的




### 注释

包含在一对三引号'''...'''或"""..."""之间且不属于任何语句的内容将被解释器认为是注释。

每个import只导入一个模块。而不要一次导入多个模块

如果一行语句太长，可以在行尾加上反斜杠“\”来换行分成多行，但是更建议使用括号来包含多行内容。

运算符两侧、函数参数之间、逗号两侧建议使用空格分开。不同功能的代码块之间、不同的函数定义之间建议增加一个空行以增加可读性。

常量名所有字母大写，由下划线连接各个单词。类名首字母大写。






### 使用帮助

1. 查看内置函数和类型的帮助信息
    `>>> help(max)`
2. `>>> import os `
   `>>> help(os.fdopen)`
3. 查看整个模块的帮助信息
   `help(math)`



## 2python语法教程

### 基本数据类型

1. 整型(int) ：通常被称为是整型或整数，是正或负整数，不带小数点。
2. 长整型(long) ：无限大小的整数，整数最后是一个大写或小写的L。在Python3里，只有一种整数类型 int，没有Python2 中的 Long。
3. 浮点型(float)浮点型也可以使用科学计数法表示（2.78e2就是2.78 x 102 = 278）
4. 复数(complex)：复数由实数部分和虚数部分构成，可以用a + bj,或者complex(a,b)表示，复数的虚部以字母j或J结尾。如：2+3j。
5. 字符串
`var1 = 'Hello World!'`
使用特殊字符时，Python用反斜杠(`\`)(转义字符)来表示


	字符串运算符
	+  字符串连接
	*  重复输出字符串 a*2
	a[1]  索引字符串
	in  成员运算符,字符串中给定字符,则返回True
	not in 字符中不包含
	r 或者 R   原始字符
	`print(R"\n print \n")`



	字符串格式化
	在 Python 中，字符串格式化使用与 C语言中printf函数一样的语法
	print ("我的名字是 %s 年龄是 %d " % ('xmj', 41))


6. 布尔类型
7. 
只有True和False两种值。

布尔运算

	and与运算：只有两个布尔值都为 True 时，计算结果才为True。
	or或运算：只要有一个布尔值为 True，计算结果就是 True。
	not非运算：把True变为False，或者把False变为True：


在Python中，逻辑值True和False作为数值，则分别是整型值1和0参与运算。例如：

	x=False
    a=x+(5>4)    # 0+1 结果a是1
    b=x+5   # 9+5 结果b是5


用None表示。它不支持任何运算也没有任何内置函数方法。None和任何其他的数据类型比较永远返回False。**在Python 中未指定返回值的函数会自动返回None。**



python强制类型转换

```python
x=20					#八进制为24
y=345.6
print(oct(x)) 				#打印结果是0o24
print(int(y)) 				#打印结果是345
print(float(x)) 				#打印结果是20.0
print(chr(65)) 				# A的ASCII为65，打印结果是A
print(ord('B')) 				# B的ASCII为66，打印结果是66
```


变量名必须是大小写英文、数字和_的组合，且不能用数字开头
在Python中，等号=是赋值语句，可以把任意数据类型赋值给变量，同一个变量可以反复赋值，而且可以是不同类型的变量，例如：
a = 123		# a是整数
a = 'ABC'		# a变为字符串




PI = 3.14159265359
但事实上PI仍然是一个变量，Python根本没有任何机制保证PI不会被改变，所以，用全部大写的变量名表示常量只是一个习惯上的用法，实际上是可以改变变量PI的值


print(5/2) 2.5
`**指数`
`//整数`



### 运算符

比较运算符

	==
	!=
	<> 类似!=



逻辑运算符


Python中提供了三种逻辑运算符，它们是：
and（逻辑与，二元运算符）
or  （逻辑或，二元运算符）
not （逻辑非，一元运算符）




位运算符

```
按位与（&）、按位或（|）、按位异或（＾）、按位求反（~）、左移（<<）、右移（>>）。位运算符是对其操作数按其二进制形式逐位进行运算。
a = 	0011 1100
b = 	0000 1101
a&b =	0000 1100
a|b = 	0011 1101
a^b =	0011 0001
~a =	1100 0011
```



python成员运算

```
in
not in
```



比较两个对象的内存位置。 
```
is
is not
```




### 序列 数据容器


Python内置序列类型最常见的是**列表、元组、字典**
序列中的每个元素都分配一个数字即它的位置或索引，第一个索引是0，第二个索引是1，依此类推。序列都可以进行的操作包括**索引，截取（切片），加，乘，成员检查**。此外，Python已经内置确定序列的**长度以及确定最大和最小**的元素的方法。
Python提供了字典和集合这样的数据结构，它们属于无顺序的数据集合体，不能通过位置索引号来访问数据元素



#### 列表

创建
```
a=[]
a=list()
```
索引
```
支持反向索引
name_list=['Tom','Lily','Rose']  
print(name_list[-1])
out:Rose
```
嵌套
```
mylist=[[1,2,3],[4,5,6]]  
print(mylist[0])  
print(mylist[1])
```





只要把逗号分隔的不同的数据项使用方括号括起来即可。实例如下：
```
list1 = ['中国', '美国', 1997, 2000];
list2 = [1, 2, 3, 4, 5 ];
```
1．访问列表中的值
```
list1 = ['中国', '美国', 1997, 2000];
list2 = [1, 2, 3, 4, 5, 6, 7 ];
print ("list1[0]: ", list1[0] )
print ("list2[1:5]: ", list2[1:5] )
```
2.修改
```
list[2] = 2001;
print ( "New value available at index 2 : ")
print (list[2] )
```
3．删除列表元素
方法一：使用 del 语句来删除列表的的元素
方法二：使用 remove()方法来删除列表的元素
方法三：使用 pop()方法方法来删除列表的指定位置的元素，无参数时删除最后一个元素
4．添加列表元素
可以使用 append()方法在列表末尾添加元素，如下实例：
```
list1 = ['中国', '美国', 1997, 2000]
list1.append(2003)
print (list1)
```
5．定义多维列表
可以将多维列表视为列表的嵌套，即多维列表的元素值也是一个列表，只是维度比父列表小一。
例如：定义1个二维列表。
`list2 = [["CPU", "内存"], ["硬盘","声卡"]]`
二维列表比一维列表多一个索引，可以如下获取元素：
列表名`[索引1][索引2]`
例如：定义3行6列的二维列表，打印出元素值。
```python
rows=3
cols=6
matrix = [[0 for col in range(cols)] for row in range(rows)]   #列表生成式
for i in range(rows):  
    for j in range(cols):
        matrix[i][j]=i*3+j
        print (matrix[i][j],end=",")  
    print ('\n') 

>>> print [x*x for x in range(1 , 11)] 
[1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
```








#### 元组
Python的元组（tuple）与列表类似，不同之处在于**元组的元素不能修改**。元组使用小括号()
3．元组连接
元组中的元素值是不允许修改的，但可以对元组进行连接组合，实例如下：
```
tup1 = (12, 34,56)
tup2 = (78, 90)
#tup1[0] = 100			#修改元组元素操作是非法的。
tup3 = tup1 + tup2		#连接元组，创建一个新的元组
print (tup3)
```
以上实例输出结果：(12, 34,56, 78, 90)  **列表也可以连接**
4．删除元组
元组中的元素值是不允许删除的，但可以使用del语句来删除整个元组。





元组与列表转换
可以使用下面方法将元祖转换为列表：
列表对象=list(元组对象)
```python
tup=(1, 2, 3, 4, 5) 
list1= list(tup) 					#元组转为列表
print (list1) 					#返回[1, 2, 3, 4, 5]
```
可以使用下面方法将列表转换为元祖：
元组对象= tuple (列表对象)
```python
nums=[1, 3, 5, 7, 8, 13, 20]
print (tuple(nums)) 				#列表转为元组，返回(1, 3, 5, 7, 8, 13, 20)
```






#### 字典

Python字典（dict）是一种可变容器模型，且可存储任意类型对象，如字符串、数字、元组等。字典也被称作关联数组或哈希表。
1．创建字典
键值对key-value
一个简单的字典实例：
```python
dict = {'zhangsan' : 91 , 'wangwu' : 80}
也可如此创建字典：
dict1 = { 'abc': 456 };
dict2 = { 'abc': 123, 98.6: 37 }; //!
```
2．访问字典里的值
访问字典里的值时把相应的键放入方括号里，如下实例：
```python
dict = {'Name': '王海', 'Age': 17, 'Class': '计算机一班'}
print ("dict['Name']: ", dict['Name'])
print ("dict['Age']: ", dict['Age'])
```
3．修改字典
```python
dict = {'Name': '王海', 'Age': 17, 'Class': '计算机一班'}
dict['Age'] = 18 			# 更新键/值对（update existing entry）
dict['School'] = "中原工学院" 	# 增加新的键/值对（add new entry）
print ("dict['Age']: ", dict['Age'] )
print ( "dict['School']: ", dict['School'];
```




#### 集合

集合（set）是一个无序不重复元素的序列。集合基本功能是进行成员关系测试和删除重复元素。
1．创建集合
可以使用大括号({})或者 set()函数创建集合，注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典。
student = {'Tom', 'Jim', 'Mary', 'Tom', 'Jack', 'Rose'}
print(student)   # 输出集合，重复的元素被自动去掉
2.集合运算
可以使用“-”、“|”、“&”运算符进行集合的差集、并集、交集运算。




##### 进行数学集合运算
```python
a = set('abcd')        #a= {'a', 'b', 'c', 'd' }
b = set('cdef')
print(a)
print("a和b的差集：", a - b)     # a和b的差集{'a', 'b' }
print("a和b的并集：", a | b)     # a和b的并集 {'a', 'b', 'c', 'd' , 'e' }
print("a和b的交集：", a & b)    # a和b的交集{'c', 'd' }
print("a和b中不同时存在的元素：", a ^ b)     # a和b中不同时存在的元素
```









## 3Python控制语句

```
if a > 6:
    print ( a, "大于6")


if…else语句的语法形式如下所示：
if 表达式 ：
    语句1
else ：
    语句2


if 表达式1 ：
    语句1
elif 表达式2 ： //else if
    语句2
    ……
elif 表达式n ：
    语句n
else ：
    语句n+1



count = 0
while count < 9:
   print ('Hello')
   count = count + 1
print ("Good bye!" )

```





for语句可以遍历任何序列的项目，如一个列表、元祖或者一个字符串。

for循环把列表中元素遍历出来。

```
fruits = ['banana', 'apple',  'mango']
for fruit in fruits:        # 第二个实例
   print ( '元素 :', fruit)
print( "Good bye!" )
q
元素：banana
元素：apple
元素：mango
Good bye!
q
```
for循环把字符串中字符遍历出来。
```
for letter in 'Python':     # 第一个实例
   print ( '当前字母 :', letter )
q
程序运行结果：
当前字母：P
当前字母：y
当前字母：t
当前字母：h
当前字母：o
当前字母：n
```

计算1~10的整数之和，可以用一个sum变量做累加。

```
sum = 0
for x in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
    sum = sum + x
print(sum)
Python提供一个range()内置函数，可以生成一个整数序列。
range(1, 101)就可以生成1~100的整数序列，计算1~100的整数之和如下：
sum = 0
for x in range(1,101):
    sum = sum + x
print(sum)
```



break语句在while循环和for循环中都可以使用，一般放在if选择结构中，一旦break语句被执行，将使得整个循环提前结束。

continue语句的作用是终止当前循环，**并忽略continue之后的语句**，然后回到循环的顶端，提前进入下一次循环。



递推与迭代



## 4Python函数与模块

```python
def  函数名(函数参数):
    函数体
    return 表达式或者值


def printHello():	#打印'hello'字符串
    print ('hello')
    
def printNum():	#输出0--9数字
    for i in range(0,10):
        print (i)
    return
        
def add(a,b): 	#实现两个数的和
    return a+b


```



### lambda表达式

```python
f=lambda x,y,z:x+y+z
print (f(1,2,3))
等价于定义：
def f(x,y,z):
    return x+y+z
print (f(1,2,3))
函数使用return返回值，也可以将Lambda 表达式作为函数的返回值。
最后需要补充一点：Python中函数是可以返回多个值的，如果返回多个值，会将多个值放在一个元组或者其他类型的集合中来返回。

```


函数多个返回值
```python
def demo(s):
    result = [0,0]
    for ch in s:
        if 'a'<=ch<='z':
            result[1] += 1
        elif 'A'<=ch<='Z':
            result[0] += 1
    return result		#返回列表
print(demo('aaaabbbbC'))

```






### 参数的传递


函数形参和实参的区别
```python
#这里的a和b就是形参
def add(a,b):
    return a+b
#下面是调用函数
add(1,2) 			#这里的1和2是实参
```

在Python中参数传递采用的是值传递，这个和C语言有点类似。对于绝大多数情况下，在函数内部直接修改形参的值不会影响实参。

```python
n=100
L=[1,2,3]
print (n,L)
```


1.默认值参数
不指定参数的时候使用的默认参数
```python

def display(a='hello',b='red'):
    print (a+b)
#主程序
display()
display(b='world')
display(a='china')
display('world')

# hellored
# helloworld
# chinared
# worldred
```



2.关键值参数
```python
def display(a,b):
    print (a)
    print (b)
# 下面2句达到的效果是相同的。
display(a='world',b='hello')
display(b='hello',a='world')

```




3．任意个数参数
一般情况下我们在定义函数时，函数参数的个数是确定的，然而某些情况下我们是不能确定参数的个数的，比如要存储某个人的名字和它的小名，某些人小名可能有2个或者更多个，此时无法确定参数的个数，只需在参数前面加上'*'或者'**'。
```python
def storename(name,*nickName):
    print ('real name is %s' %name)
    for nickname in nickName:
        print ('小名',nickname)
#主程序
storename('张海')
storename('张海','小海')
storename('张海','小海','小豆豆')

```
```
'*'和'**'表示能够接受0到任意多个参数，'*'表示将没有匹配的值都放在同一个元组中，'**'表示将没有匹配的值都放在一个字典中。
```





局部变量和全局变量

1. 局部变量

2. 全局变量
但是如果要在函数内部改变全局变量值，必须使用global关键字进行声明
```python
x=2		#全局变量
def fun1():
    print (x, end=" ") 
def fun2():
    global x	#在函数内部改变全局变量值必须使用global关键字
    x=x+1
    print (x, end=" ")
fun1()
fun2()
print (x, end=" ")

```




### 闭包（closure）
在Python中，闭包指函数的嵌套。可以在函数内部定义一个嵌套函数。

```python
def func_lib():
    def add(x, y):
        return x+y
    return add       # 返回函数对象
 
fadd = func_lib()
print(fadd(1, 2))
```



递归调用
```python
def f(x):
   if x==1: 		#递归调用结束的条件
      return 1
   else:
      return(f(x-1)+x*x)	#调用f( ) 函数本身
print(f(5))


```




### 内置函数
内置函数(built-in functions)又称系统函数，或内建函数，是指Python本身所提供的函数，任何时候都可以使用。

Python常用的内置函数有数学运算函数、类型转换函数和反射函数等。想要查所有内置函数名可以在python命令行方式中如下输入：


### 模块
模块（module）能够有逻辑地组织你的Python代码段。把相关的代码分配到一个模块里能让代码更好用，更易懂。简单地说，模块就是一个保存了Python代码的文件。模块里能定义函数，类和变量。


```python
import math        #导入math模块
print ("50的平方根：", math.sqrt(50))
y=math.hex (50) 
print ("50的16进制：",y)

```

1．导入模块方式
在Python中用关键字import来导入某个模块。方式如下：
import 模块名			# 导入模块
有时候我们只需要用到模块中的某个函数，只需要引入该函数即可，此时可以通过语句：
from 模块名 import 函数名1,函数名2....
通过这种方式引入的时候，调用函数时只能给出函数名，不能给出模块名


2  定义自己的模块

在Python中，每个Python文件都可以作为一个模块，模块的名字就是文件的名字。
比如有这样一个文件fibo.py，在fibo.py中定义了3个函数add()，fib()，fib2()：
那么在其他文件（如test.py）中就可以如下使用： 



```python
#test.py
import fibo
加上模块名称来调用函数： 
fibo.fib(1000)     #结果是1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987
fibo.fib2(100)     #结果是[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
test.add(2,3)      #结果是5

```


## 5文件操作
文件的概念
简单地说，文件是存储在辅助存储器上的数据序列，在逻辑上具有完整意义。
文件是数据的集合和抽象；相对的，函数是程序的集合和抽象。
用文件组织数据更为灵活和有效。
Windows系统的数据文件按照编码方式分为两大类：
文本文件
二进制文件

文本文件
文本文件由单一特定编码的字符组成，如UTF-8编码。
文本文件可以处理各种语言所需的字符。文件中只包含基本文本字符，不包括诸如字体、字号、颜色等信息。它可以在文本编辑器和浏览器中显示。即在任何情况下，文本文件都是可读的。
文本文件可以看做存储在存储器上的一个长字符串。




二进制文件
二进制文件由比特0和比特1组成，文件数据的组织格式与文件用途有关。二进制文件是信息按照非字符的特定格式形成的文件，如jpg格式的图像文件、avi格式的视频文件。
二进制文件与文本文件最大的区别就是有没有统一字符编码。二进制文件没有统一字符编码，使用其它编码方式。
二进制文件可以看作字节流，而不是字符串。



读文本文件
`file1=open("c:\ly\and.txt","rt")`
读二进制文件
`file1=open("c:\ly\and.txt","rb")`




文件的访问

在Python中对文件的操作通常按照以下三个步骤进行：
（1）使用open()函数打开（或建立）文件，返回一个file对象。
（2）使用file对象的读/写方法对文件进行读/写的操作。其中，将数据从外存传输到内存的过程称为读操作，将数据从内存传输到外存的过程称为写操作。
（3）使用file对象的close()方法关闭文件。






```python
Fiel.write(s)
File.writelines(lines)
File.seek(offset) 下边有解释

Fiel.readall()
File.read(size)
File.readline(size)
File.readlines(hint)

```




r 只读模式，若文件不存在则返回异常，默认值
w 覆盖写模式，文件不存在则创建，存在则覆盖
x 创建写模式，文件不存在则创建，存在则返回异常
a 追加写模式，文件不存在则创建，存在则追加
b 二进制文件模式
t 文本文件模式
`+ `与r/w/x/a一起使用，在原功能基础上追加读写功能


默认rt模式
`helloFile=open("d:\\python\\hello.txt")`


字符串或字节流取决于文件的打开方式。文件以文本方式打开则返回字符串；以二进制方式打开则返回字节流。




操作方法	含义
```
<file>.readall()	读入整个文件内容，返回一个字符串或一个字节流
<file>.read(size=-1)	读入整个文件内容，若给出参数size，则读入前size长度的字符串或字节流
<file>.readline(size=-1)	从文件读入一行内容，若给出参数size，则读入该行前size长度的字符串或字节流
<file>.readlines(hint=-1)	从文件读入所有行内容，以每行内容为元素形成列表，若给出参数hint，则读入hint行
```




### 读取文本文件
1．read()方法


```python
helloFile=open("d:\\python\\hello.txt")
fileContent=helloFile.read() //!
helloFile.close()
print(fileContent)	

```
2．readline()方法
readline()方法从文件中获取一个字符串，每个字符串就是文件中的每一行。

```python
helloFile=open("d:\\python\\hello.txt")
fileContent=""
while True:
   line=helloFile.readline()
   if line=="":    # 或者 if not line
      break
   fileContent+=line
helloFile.close()
print(fileContent)
```
3．readlines()方法
readlines()方法从文件中获取全部内容，并形成一个列表，列表的每一个元素就是文件里每一行的内容。
当文件过大时，一次读入所有内容会占用过多内存，造成执行效率下降

```python
helloFile=open("hello.txt")  
files=helloFile.readlines()  
print(files)  
helloFile.close()

```

### 写文本文件
写文件与读文件相似，都需要先使用open()函数创建文件对象连接。所不同的是，打开文件时是以“写”模式或“添加”模式打开。如果文件不存在，则创建该文件。
与读文件时不能添加或修改数据类似，写文件时也不允许读取数据。

```python
方法	含义
<file>.write(s)	向文件写入一个字符串或一个字节流
<file>.writelines(lines)	将一个元素全为字符串的列表写入文件
```

文件复制

```python
def copy_file(oldfile,newfile):
    oldFile=open(oldfile,"r")
    newFile=open(newfile,"w")
    while True:
        fileContent=oldFile.read(50)
        if fileContent=="":
            break
        newFile.write(fileContent)
    oldFile.close()
    newFile.close()
    return
copy_file("pythontest.txt","newpytontest.txt")
```

writelines()方法向文件写入一个列表，列表的每一个元素都是字符串。

```python
hmldFile=open("pythontest.txt", "a+")
ls=["我最亲爱的祖国\n","我永远紧依着你的心窝\n","你用你那母亲的脉搏\n","和我诉说\n"]
hmldFile.writelines(ls)
hmldFile.seek(0,0) //!
for line in hmldFile:
    print(line)
hmldFile.close()	
```

### seek
`fileObject.seek(offset[, whence])`
offset – 开始的偏移量，也就是代表需要移动偏移的字节数
whence：可选，默认值为 0。给offset参数一个定义，表示要从哪个位置开始偏移；0代表从文件开头开始算起，1代表从当前位置开始算起，2代表从文件末尾算起。
seek(0,0)//从开头偏移0 的位置

当文件以文本文件方式打开时，只能默认从文件头计算偏移量，即whence参数为1或2时，offset参数只能取0，Python解释器不接受非零当前偏移。

以二进制方式打开文件时，文件指针可以参数offset的值从参数whence指定的位置向前/向后偏移。



### 文件的关闭
try finally 有效防止忘记关闭文件:
```python
helloFile=open("C:\\lynn \\python\\hello.txt","w")
try :
	helloFile.write("Hello,Sunny Day!")
finally:
	helloFile.close()
```

也可以使用with语句自动关闭文件:
```python
with open("d:\\python\\hello.txt") as helloFile:
	s=helloFile.read()
print(s)

```



### 二进制文件的读写
Python没有二进制类型，但是可以用string字符串类型来存储二进制类型数据，因为string是以字节为单位的。

1. 数据转换成字节串（以字节为单位的字符串）
pack()方法可以把数据转换成字节串。
格式：pack(格式化字符串，数据)
格式化字符串中可用的格式字符见表5-1中格式字符。
```python
import struct
a=20
ex1File=open("a.txt","wb")
bytes=struct.pack('i',a)	#将a变为string字节串
ex1File.write(bytes)
ex1File.close
print(bytes)

```
2. 字节串（以字节为单位的字符串）还原成数据
unpack()方法可以把相应数据的字节串还原成数据。
bytes=struct.pack('i',20)	# 将20变为string字符串
再进行反操作，现有二进制数据bytes（其实就是字符串），将它反过来转换成Python的数据类型：
`a=struct.unpack('i',bytes)`
通过struct.unpack()解码成变量或元组

```python
import struct
binfile=open("a.txt","rb")
bytes=binfile.read()
(a,b,c,d)=struct.unpack('5s6sif',bytes)
t=struct.unpack('5s6sif',bytes) 
print(t)

```

```python
import struct
s1="binary"
s2="file"
pi=3.1415
i=10
ex1File=open("a.txt","wb")
bytes=struct.pack("6s4sfi",s1.encode("utf-8"),s2.encode("utf-8"),pi,i)
ex1File.write(bytes)
ex1File.close()
ex1File=open("b.txt","rb")
(s1,s2,pi,i)=struct.unpack("6s4sfi",ex1File.read())
ex1File.close()
print(s1,s2,pi,i)
```



###  文件夹的操作

当前工作目录
在交互式环境中输入以下代码：
```python
import os
print(os.getcwd())
```
路径
相对路径
..\
.\

	1．创建新目录
	程序可以用os.mkdir()函数创建新目录。在交互式环境中输入以下代码：
			import os
			os.mkdir("e:\\python1\\ch5files")
	2．删除目录
	当目录不再使用，可以将它删除。使用rmdir()函数删除目录：
			import os
			os.rmdir("e:\\python1\\ch5files")
			os.rmdir("e:\\python1")
			os.path.exists("e:\\python1") 	# 运行结果为False
	3．列出目录内容
	使用os.listdir()函数可以返回给出路径中文件名及文件夹名的字符串列表：
			os.mkdir("e:\\python1")
			os.listdir(“e:\\python1”)	#返回空列表
	4．修改当前目录
	使用os.chdir()函数可以更改当前工作目录：//!
			os.chdir("e:\\python1")
			os.listdir(".")   	#.代表当前工作目录


```python
1．获取路径和文件名
os.path.split(path)：返回参数的路径名称和文件名组成的字符串元组。
os.path.dirname(path)：返回path参数中的路径名称字符串。
os.path.basename(path)：返回path参数中的文件名。
2．检查路径有效性
os.path.exists(path)：判断参数path的文件或文件夹是否存在。存在返回true，否则返回false。
os.path.isfile(path)：判断参数path存在且是一个文件，则返回true，否则返回false。
os.path.isdir(path)：判断参数path存在且是一个文件夹，则返回true，否则返回false。
3．查看文件大小
os.path模块中的os.path.getsize()函数可以查看文件大小。
4．重命名文件
os.rename()函数可以帮助我们重命名文件。
os.rename("d:\\python\\hello.txt","d:\\python\\helloworld.txt")
5．复制文件和文件夹
shutil模块中提供一些函数，帮助我们复制、移动、改名和删除文件夹。可以实现文件的备份。
6．文件和文件夹的移动和改名
shutil.move(source,destination)：shutil.move()函数与shutil.copy()函数用法相似，参数destination 既可以是一个包含新文件名的路径，也可以仅包含文件夹。
7．删除文件和文件夹
os模块和shutil模块都有函数可以删除文件或文件夹。
os.remove(path)/os.unlink(path)：删除参数path指定的文件。
os.remove("e:\\python-backup\\data-backup.txt")
os.path.exists("e:\\python-backup\\data-backup.txt")   #False
```

统计指定文件夹下所有文件的大小。

```python
import os
totalSize=0
os.chdir("C:\\lynn\\Python")
for fileName in os.listdir(os.getcwd()):
    totalSize+=os.path.getsize(fileName)
print( totalSize)
```


os.walk()函数返回参数路径下所有文件及子目录信息元组，即遍历目录树。

```python
import os
list_dirs = os.walk("C:\\lynn\\Python")                 #返回一个元组
for folderName,subFolders,fileNames in list_dirs:
    print("当前目录：" + folderName + "\n")
    for subFolder in subFolders:
        print(folderName +"的子目录" + " 是--" + subFolder + "\n")
        for fileName in fileNames:
            print(subFolder +"的文件 " +  " 是--" + fileName + "\n")



```


文件的应用
![](/python/src/文件的应用.jpg)

```python
import os
#地图信息写入文件
helloFile=open("map1.txt","w")
helloFile.write("0,0,0,3,3,0,0\n")
helloFile.write("3,3,0,3,4,0,0\n")
helloFile.write("1,3,3,2,3,3,0\n")
helloFile.write("4,2,0,3,3,3,0\n")
helloFile.write("3,3,3,0,3,3,0\n")
helloFile.write("3,3,3,0,0,3,0\n")
helloFile.write("3,0,0,0,0,0,0\n")
helloFile.close()

从文件中读取地图信息
#读文件
helloFile=open("map1.txt","r")
myArray1=[]
while True:
   line=helloFile.readline()
   if line=="":  			# 或者 if not line
      break
   line=line.replace("\n","")   	#将读取的1行中最后的换行符去掉
   myArray1.append(line.split(","))
helloFile.close()
print(myArray1)


```
案例2、文章词频统计

```python
def getFileText():		
	 with open("C:\\lynn\\Python\\letter.txt","r") as letterFile:
        		filTxt=letterFile.read()
	filTxt=filTxt.lower()
	for ch in '!"#$%&()*+-*/,.:;<=>?@[]\\^_{}|~':
		filTxt=filTxt.replace(ch," ")
	return filTxt
letterTxt=getFileText()




使用filTxt.lower()函数将所有单词转为小写形式，保证同一个单词不同大小写形式统计的一致
用filTxt.replace()方法将特殊字符统一替换为空格，为后面的分词做准备


words=letterTxt.split()
wdCountDict={}
excludes={"the","of","you","your","that","will","this","don't"}
for word in words:
	wdCountDict[word]=wdCountDict.get(word,0)+1
for word in excludes:
        del(wdCountDict[word])


分词并使用字典统计每一个单词的词频
去掉对“停止词”的统计


对结果排序并输出

将字典转换为列表，为排序做准备
按列表的第2项使用lambda函数排序
将单词长度小于3（停止词）和出现次数过少的单词排除掉，
最后将结果按单词出现次数从大到小输出

items=list(wdCountDict.items())	
items.sort(key=lambda x:x[1],reverse=True)
print("{0:<10}{1:>5}".format("word","count"))
print(“*"*21)
for key,val in items:
	if len(key)>3 and val>2:
		print("{0:<10}{1:>5}".format(key,val))



```


allcode

```python
def getFileText():		
	filTxt=open("C:\\lynn\\Python\\letter.txt","r").read()
	filTxt=filTxt.lower()
	for ch in '!"#$%&()*+-*/,.:;<=>?@[]\\^_{}|~':
		filTxt=filTxt.replace(ch," ")
	return filTxt
letterTxt=getFileText()
words=letterTxt.split()
wdCountDict={}
excludes={"the","of","you","your","that","will","this","don't"}
for word in words:
	wdCountDict[word]=wdCountDict.get(word,0)+1
for word in excludes:
        del(wdCountDict[word])
items=list(wdCountDict.items())	
items.sort(key=lambda x:x[1],reverse=True)
print("{0:<10}{1:>5}".format("word","count"))
print("_"*21)
for key,val in items:
	if len(key)>3 and val>2:
		print("{0:<10}{1:>5}".format(key,val))

```
























































































































































































































































































































































































































































































































































































































































































































































```
def readInput():
    res=""
    word=input()
    while word.count('#')==0:
        res+=word
        word=input()
    res+=(word.split('#'))[0]
    return res
```


```python

read=readInput()
# print("打印输入:",read)
specialChar='!"#$%&()*+,-./:;<=>?@[\\]^`{|}~'
for ch in specialChar:
    read=read.replace(ch,' ')
read=read.lower()
words=read.split(' ')
# for word in words:
#     print(word,end=" ")
 
counts={}   #字典counts
for word in words:
    counts[word]=counts.get(word,0)+1
 
items=list(counts.items())
items.sort(key=lambda x:x[1],reverse=True)
uniqueSum=len(items)
span=int(0.1*uniqueSum)
print(uniqueSum)
for i in range(span):
    word,count=items[i]
    print(str(count)+":"+word)

```


### 没有++概念

python中一切都是对象
刚开始学Python，当想要自增运算的时候很自然的 a++，结果发现编译器是不认识 ++ 的，涉及到了 Python 这个语言的设计原理

问题无外乎就是 python 没有自增运算符，自增操作是如何实现的

回答中有人介绍了关于自增操作，python 不使用 ++ 的哲学逻辑：编译解析上的简洁与语言本身的简洁，就不具体翻译了

后面还有老外回答并附带了一个例子非常的精彩，指出了 python 与 c 语言概念上的一些差异，语言描述的可能未必准确，直接上例子：

```python
>>> b = 5  
>>> a = 5  
>>>  id(a)  162334512 
>>>   id(b)  162334512  
>>>   a is b  True  
```

  
可以看出， python 中，变量是以内容为基准而不是像 c 中以变量名为基准，所以只要你的数字内容是5，不管你起什么名字，这个变量的 ID 是相同的，同时也就说明了 python 中一个变量可以以多个名称访问


因此，正确的自增操作应该 a = a + 1 或者 a += 1，当此 a 自增后，通过 id() 观察可知，id 值变化了，即 a 已经是新值的名称




### tls
sort
```python
lst=[2,3,1,4,0]
lst.sort()
print(lst)
lst.sort(reverse=True) # 默认为False
print(lst)
```
out
```
[0, 1, 2, 3, 4]
[4, 3, 2, 1, 0]
```




### pyton differ from c++
python differ from c++
 

缩进代替中括号

冒号代替()
```
score = 63

if score >= 60:
    print('You passed!')
 

  while a != 0:  #a不等于0就一直做
       print "please input"
       a = int(input())
   print "over"
 ```

Python pass 语句
类似于c++中;
保持结构的完整性
 

del和GC
垃圾回收器 Garbage Collector 的进程完成的。（GC）机制
GC的优缺点
好处是
开发者无需过问内存管理，可以专注于解决实际问题。虽然内存泄露仍有可能会发生，但发生的概率比较小。

GC的智能算法可以在后台自动的进行内存管理，且这种管理在大多数时候是最佳的。

坏处是
当垃圾回收发生时，它会影响程序的性能，甚至会暂停程序的执行。这个被称为“Stop the world”垃圾回收机制，整个程序进程会被暂停以等待垃圾回收执行完。对某些应用而言，这可能是无法接受的。

开发者并不能指定何时或使用何种方法执行GC机制

 

python的del不同于C的free和C++的delete。

由于python都是引用，而python有GC机制，所以，del语句作用在变量上，而不是数据对象上。

```
if __name__=='__main__':
    a=1       # 对象 1 被 变量a引用，对象1的引用计数器为1
    b=a       # 对象1 被变量b引用，对象1的引用计数器加1
    c=a       #1对象1 被变量c引用，对象1的引用计数器加1
    del a     #删除变量a，解除a对1的引用
    del b     #删除变量b，解除b对1的引用
    print(c)  #最终变量c仍然引用1
```

拷贝列表，也不会有数据对象的复制，而是创建新的变量引用

python类型转换
```
y=int(x)
import math
dir(math)
```




### 引号问题

python中最常用的字符串表示方式是单引号（''）和双引号（""）

 
内容带有单引号，就用双引号表示
"It's good"
反之亦然
‘You are a "BAD" man’
还有一种在字符串中表示引号的方法，就是用\，可以不受引号的限制
\'表示单引号，\"表示双引号
'I\'m a \"good\" teacher'

还有个用处，就是用来在代码中换行，而不影响输出的结果：
"this is the\
same line"

在三个引号中，你可以方便地使用单引号和双引号，并且可以直接换行

'''
"What's your name?" I asked.
"I'm Han Meimei."
'''


对文件操作 文件名字是中文的时候报错...暂时还是不知到原因

对文件操作 写入到 usrs文件夹 报错 不知为什么 也许是权限问题







# python 数据容器

## 大纲

列表list
元组tuple
字符串string
集合set
字典key value



## 列表list

```python
a=[1,2,3,'qq']
a=[]
a=list()

a=[[1,2,3],[4,5,6]]
print(a[0]) #[1,2,3]
print(a[1]) #[4,5,6]
print(a[0][0]) # 1

支持反向索引
a=[1,2,3]
print()
a.clear

len()

a.remove() 
删除元素在列表中的第一个匹配项

my_list=[1,2,3,1,1]
mylist.count(1) #3
某个元素的数量


a.index()
查找元素对应的下标

a.insert(下标,元素)
指定位置插入元素

a.append()
追加

a.extend(list)
扩充:二合一

del a[0]
a.pop(0)


可以容纳多个元素（上限为2**63-1、9223372036854775807个）
可以容纳不同类型的元素（混装）
数据是有序存储的（有下标序号）
允许重复数据存在
可以修改（增加或删除元素等）



index=0
while index<len(a):
    b=a[index]
    b=b+1
    index+=1


for i in 容器:
    handle


my_list=[1,2,3,4]
for i in my_list:
    print(i)

while循环和for循环，都是循环语句，但细节不同：
在循环控制上：
while循环可以自定循环条件，并自行控制
for循环不可以自定循环条件，只可以一个个从容器内取出数据
在无限循环上：
while循环可以通过条件控制做到无限循环
for循环理论上不可以，因为被遍历的容器容量不是无限的
在使用场景上：
while循环适用于任何想要循环的场景
for循环适用于，遍历数据容器的场景或简单的固定次数循环场景


for循环更简单，while更灵活
```

## 元组

```python
a=()
a=tuple()
a=(1,2,"ww")


注意：元组只有一个数据，这个数据后面要添加逗号,负责不是元组类型,是单个元素的类型


元组不可修改

.index()
.count()
len()

可以修改元组内的list的内容（修改元素、增加、删除、反转等）

t1=(1,2,3,[1,2,3,4])
t1[3][2]=99
print(t1)

(1, 2, 3, [1, 2, 99, 4])


总结

可以容纳多个数据
可以容纳不同类型的数据（混装）
数据是有序存储的（下标索引）
允许重复数据存在
不可以修改（增加或删除元素等）
支持for循环

```

## 字符串

```python

同元组一样，字符串是一个：无法修改的数据容器。
name='itheima'
print(name[0])
print(name[1])

name2='itheima and ithtima'
print(name2.index('and'))


# 字符串的替换
#      语法：字符串.replace(字符串1，字符串2）
#      功能：将字符串内的全部：字符串1，替换为字符串2
#      注意：不是修改字符串本身，而是得到了一个新字符串哦


name3=name2.replace('it','replaced_flag')
print(name3)



name4=name2.split(" ")
print(name4)  # 得到了一个列表对象

# 字符串规整操作

# 去前后空格
name5="  it黑马  "
print(name5.strip())
# 去前后指定字符
name6="12  itheima12"
print(name6.strip("12"))
print(name6.strip("21"))
# 注意，传入的是“12” 其实就是：”1”和”2”都会移除，是按照单个字符

# 统计个数
name7="it heima 666"
print(name7.count('6'))

name8="  123"
print(len(name8)) # 数字字符空格都算作一个

# 支持while 和for

# 例子:分割字符串
name9="itheima itcast boxuegu"
name10=name9.replace(" ","|")
print(name10)







```



## 序列
列表、元组、字符串，均可以可以视为序列。




```python


mylist=[1,2,3,4,5]
print(mylist)
newlist=mylist[1:4] #步长默认的1
print(newlist)
# [2, 3, 4]

mytuple=(1,2,3,4,5)
newtuple=mytuple[:] # 从头开始,最后结束
print(newtuple)

newlist=mylist[::2]
print(newlist)

newlist=mylist[:4:2] #从头开始,到下标4结束(不含)
print(newlist)

newlist=mylist[::-1]
print(newlist)

newlist=mylist[:1:-2] # 从最后开始,到下标1结束,步长-2(不含)
print(newlist)







str="万过薪月,员序程马黑来,nohtyP学"
newstr=str[::-1]
print(newstr)
finalstr=newstr[9:14:]
print(finalstr)

newstr2=str[::-1]
print(newstr)
finalstr2=newstr2.split(",")
print(finalstr2)
finalstr3=finalstr2[1].replace("来"," ")
print(finalstr3)

```


## set集合

集合，最主要的特点就是：不支持元素的重复（自带去重功能）、并且内容无序




```python
# 从集合中随机取出元素   @ 随机
my_set={"hello","world","itheima"}
element=my_set.pop()
print(my_set)
print(element)



# clear
my_set={"hello","world","itheima"}
my_set.clear()
print(my_set)
# set() 空集合



# difference
# 取出2个集合的差集

set1={1,2,3}
set2={4,5,6}
set3=set1.difference(set2)  #set1-set2
print(set1)
print(set2)
print(set3)


# 消除2个集合的差集

# 在集合1内，删除和集合2相同的元素。
set1={1,2,3}
set2={1,5,6}
set1.difference_update(set2)  # 在集合1内，删除和集合2相同的元素
print("在集合1内，删除和集合2相同的元素")
print("set1_fixed",set1)
print("set2",set2)


# 合集

set1={1,2,3}
set2={1,5,6}
set3=set1.union(set2)
print(set3)




特点

可以容纳多个数据
可以容纳不同类型的数据（混装）
数据是无序存储的（不支持下标索引）
不允许重复数据存在
可以修改（增加或删除元素等）
支持for循环







练习

my_list = ['黑马程序员', '传智播客', '黑马程序员', '传智播客', 'itheima', 'itcast', 'itheima', 'itcast', 'best']
for i in my_list:
    print(i)
print()
my_set=set(my_list)
for i in my_set:
    print(i)



```



## 字典
key value


字典同集合一样，不可以使用下标索引



```python

my_dict={}
my_dict2=dict()


mydict={1:2,2:3}

stu_score={
    "王力宏":{"语文":77,"数学":99,"英语":89},
    "吴彦祖":{"语文":20,"数学":67,"英语":34}
}

print(stu_score)
print(stu_score["王力宏"])
print(stu_score["王力宏"]["数学"])





my_dict={}
my_dict["nihao"]='shijie'
my_dict[1]=2
print(my_dict)
my_dict.clear()
my_dict.keys() #获取字典的所有key
len(my_dict) #得到键值对数量


总结

可以容纳多个数据
可以容纳不同类型的数据
每一份数据是KeyValue键值对
可以通过Key获取到Value，Key不可重复（重复会覆盖）
不支持下标索引
可以修改（增加或删除更新元素等）
支持for循环，不支持while循环



作业
对所有级别为1级的员工，级别上升1级，薪水增加1000


my_form={
    "王力宏":{"部门":"科技部","工资":3000,"级别":1},
    "周杰伦":{"部门":"市场部","工资":4000,"级别":2},
    "张雪有":{"部门":"市场部","工资":5000,"级别":3}
}
print(my_form)
print()
for i in my_form:
    i.

```





总结

首先，在遍历上：
5类数据容器都支持for循环遍历
列表、元组、字符串支持while循环，集合、字典不支持（无法下标索引）


支持`len max min`

通用类型转换
`list() str() tuple() set()`

通用排序
`sorted(容器,[reverse=True])`


**注意**，排序后都会得到列表（list）对象。





### 字符串比较

字符串是按位比较，也就是一位位进行对比，只要有一位大，那么整体就大。

1. 字符串如何比较
从头到尾，一位位进行比较，其中一位大，后面就无需比较了。
2. 单个字符之间如何确定大小？
通过ASCII码表，确定字符对应的码值数字来确定大小


```


abc小于abd
a小于ab


```

### 作业:


```python
# n=int(input())
n=10
test_list=[]
lucky_list=[]
for i in range(n):
    i=i+1
    print(i)
    test_list.append(i)
    if i%6==0:
        lucky_list.append(i)

print(test_list)
print(lucky_list)


幸运数字6：输入任意数字，如数字8，生成nums列表，元素值为1~8，从中选取幸运数字(能够被6整除)移动到新列表lucky，打印nums与lucky。


ps:
在for i 循环中会将i从0开始计算





幸运数字6：输入任意数字，如数字8，生成nums列表，元素值为1~8，从中选取幸运数字(能够被6整除)移动到新列表lucky，打印nums与lucky。



my_set={'A','B','C','D','E','F','G','H'}
my_class=[[],[],[]]
for i in range(len(my_class)):
    for j in range(2):
        my_class[i].append(my_set.pop())

print(my_class)





### pop随机索取 set

my_set={'A','B','C','D','E','F','G'}
print(my_set)
print(my_set.pop())
print(my_set)


### pop 取最后一个 list

my_list=['A','B','C','D','E','F','G']
print(my_list)
print(my_list.pop())
print(my_list)


```








































# python 数据容器

## 大纲

列表list
元组tuple
字符串string
集合set
字典key value



## 列表list

```python
a=[1,2,3,'qq']
a=[]
a=list()

a=[[1,2,3],[4,5,6]]
print(a[0]) #[1,2,3]
print(a[1]) #[4,5,6]
print(a[0][0]) # 1

支持反向索引
a=[1,2,3]
print()
a.clear

len()

a.remove() 
删除元素在列表中的第一个匹配项

my_list=[1,2,3,1,1]
mylist.count(1) #3
某个元素的数量


a.index()
查找元素对应的下标

a.insert(下标,元素)
指定位置插入元素

a.append()
追加

a.extend(list)
扩充:二合一

del a[0]
a.pop(0)


可以容纳多个元素（上限为2**63-1、9223372036854775807个）
可以容纳不同类型的元素（混装）
数据是有序存储的（有下标序号）
允许重复数据存在
可以修改（增加或删除元素等）



index=0
while index<len(a):
    b=a[index]
    b=b+1
    index+=1


for i in 容器:
    handle


my_list=[1,2,3,4]
for i in my_list:
    print(i)

while循环和for循环，都是循环语句，但细节不同：
在循环控制上：
while循环可以自定循环条件，并自行控制
for循环不可以自定循环条件，只可以一个个从容器内取出数据
在无限循环上：
while循环可以通过条件控制做到无限循环
for循环理论上不可以，因为被遍历的容器容量不是无限的
在使用场景上：
while循环适用于任何想要循环的场景
for循环适用于，遍历数据容器的场景或简单的固定次数循环场景


for循环更简单，while更灵活
```

## 元组

```python
a=()
a=tuple()
a=(1,2,"ww")


注意：元组只有一个数据，这个数据后面要添加逗号,负责不是元组类型,是单个元素的类型


元组不可修改

.index()
.count()
len()

可以修改元组内的list的内容（修改元素、增加、删除、反转等）

t1=(1,2,3,[1,2,3,4])
t1[3][2]=99
print(t1)

(1, 2, 3, [1, 2, 99, 4])


总结

可以容纳多个数据
可以容纳不同类型的数据（混装）
数据是有序存储的（下标索引）
允许重复数据存在
不可以修改（增加或删除元素等）
支持for循环

```

## 字符串

```python

同元组一样，字符串是一个：无法修改的数据容器。
name='itheima'
print(name[0])
print(name[1])

name2='itheima and ithtima'
print(name2.index('and'))


# 字符串的替换
#      语法：字符串.replace(字符串1，字符串2）
#      功能：将字符串内的全部：字符串1，替换为字符串2
#      注意：不是修改字符串本身，而是得到了一个新字符串哦


name3=name2.replace('it','replaced_flag')
print(name3)



name4=name2.split(" ")
print(name4)  # 得到了一个列表对象

# 字符串规整操作

# 去前后空格
name5="  it黑马  "
print(name5.strip())
# 去前后指定字符
name6="12  itheima12"
print(name6.strip("12"))
print(name6.strip("21"))
# 注意，传入的是“12” 其实就是：”1”和”2”都会移除，是按照单个字符

# 统计个数
name7="it heima 666"
print(name7.count('6'))

name8="  123"
print(len(name8)) # 数字字符空格都算作一个

# 支持while 和for

# 例子:分割字符串
name9="itheima itcast boxuegu"
name10=name9.replace(" ","|")
print(name10)







```



## 序列
列表、元组、字符串，均可以可以视为序列。




```python


mylist=[1,2,3,4,5]
print(mylist)
newlist=mylist[1:4] #步长默认的1
print(newlist)
# [2, 3, 4]

mytuple=(1,2,3,4,5)
newtuple=mytuple[:] # 从头开始,最后结束
print(newtuple)

newlist=mylist[::2]
print(newlist)

newlist=mylist[:4:2] #从头开始,到下标4结束(不含)
print(newlist)

newlist=mylist[::-1]
print(newlist)

newlist=mylist[:1:-2] # 从最后开始,到下标1结束,步长-2(不含)
print(newlist)







str="万过薪月,员序程马黑来,nohtyP学"
newstr=str[::-1]
print(newstr)
finalstr=newstr[9:14:]
print(finalstr)

newstr2=str[::-1]
print(newstr)
finalstr2=newstr2.split(",")
print(finalstr2)
finalstr3=finalstr2[1].replace("来"," ")
print(finalstr3)

```


## set集合

集合，最主要的特点就是：不支持元素的重复（自带去重功能）、并且内容无序




```python
# 从集合中随机取出元素   @ 随机
my_set={"hello","world","itheima"}
element=my_set.pop()
print(my_set)
print(element)



# clear
my_set={"hello","world","itheima"}
my_set.clear()
print(my_set)
# set() 空集合



# difference
# 取出2个集合的差集

set1={1,2,3}
set2={4,5,6}
set3=set1.difference(set2)  #set1-set2
print(set1)
print(set2)
print(set3)


# 消除2个集合的差集

# 在集合1内，删除和集合2相同的元素。
set1={1,2,3}
set2={1,5,6}
set1.difference_update(set2)  # 在集合1内，删除和集合2相同的元素
print("在集合1内，删除和集合2相同的元素")
print("set1_fixed",set1)
print("set2",set2)


# 合集

set1={1,2,3}
set2={1,5,6}
set3=set1.union(set2)
print(set3)




特点

可以容纳多个数据
可以容纳不同类型的数据（混装）
数据是无序存储的（不支持下标索引）
不允许重复数据存在
可以修改（增加或删除元素等）
支持for循环







练习

my_list = ['黑马程序员', '传智播客', '黑马程序员', '传智播客', 'itheima', 'itcast', 'itheima', 'itcast', 'best']
for i in my_list:
    print(i)
print()
my_set=set(my_list)
for i in my_set:
    print(i)



```



## 字典
key value


字典同集合一样，不可以使用下标索引



```python

my_dict={}
my_dict2=dict()


mydict={1:2,2:3}

stu_score={
    "王力宏":{"语文":77,"数学":99,"英语":89},
    "吴彦祖":{"语文":20,"数学":67,"英语":34}
}

print(stu_score)
print(stu_score["王力宏"])
print(stu_score["王力宏"]["数学"])





my_dict={}
my_dict["nihao"]='shijie'
my_dict[1]=2
print(my_dict)
my_dict.clear()
my_dict.keys() #获取字典的所有key
len(my_dict) #得到键值对数量


总结

可以容纳多个数据
可以容纳不同类型的数据
每一份数据是KeyValue键值对
可以通过Key获取到Value，Key不可重复（重复会覆盖）
不支持下标索引
可以修改（增加或删除更新元素等）
支持for循环，不支持while循环



作业
对所有级别为1级的员工，级别上升1级，薪水增加1000


my_form={
    "王力宏":{"部门":"科技部","工资":3000,"级别":1},
    "周杰伦":{"部门":"市场部","工资":4000,"级别":2},
    "张雪有":{"部门":"市场部","工资":5000,"级别":3}
}
print(my_form)
print()
for i in my_form:
    i.

```





总结

首先，在遍历上：
5类数据容器都支持for循环遍历
列表、元组、字符串支持while循环，集合、字典不支持（无法下标索引）


支持`len max min`

通用类型转换
`list() str() tuple() set()`

通用排序
`sorted(容器,[reverse=True])`


**注意**，排序后都会得到列表（list）对象。





### 字符串比较

字符串是按位比较，也就是一位位进行对比，只要有一位大，那么整体就大。

1. 字符串如何比较
从头到尾，一位位进行比较，其中一位大，后面就无需比较了。
2. 单个字符之间如何确定大小？
通过ASCII码表，确定字符对应的码值数字来确定大小


```


abc小于abd
a小于ab


```

### 作业:


```python
# n=int(input())
n=10
test_list=[]
lucky_list=[]
for i in range(n):
    i=i+1
    print(i)
    test_list.append(i)
    if i%6==0:
        lucky_list.append(i)

print(test_list)
print(lucky_list)


幸运数字6：输入任意数字，如数字8，生成nums列表，元素值为1~8，从中选取幸运数字(能够被6整除)移动到新列表lucky，打印nums与lucky。


ps:
在for i 循环中会将i从0开始计算





幸运数字6：输入任意数字，如数字8，生成nums列表，元素值为1~8，从中选取幸运数字(能够被6整除)移动到新列表lucky，打印nums与lucky。



my_set={'A','B','C','D','E','F','G','H'}
my_class=[[],[],[]]
for i in range(len(my_class)):
    for j in range(2):
        my_class[i].append(my_set.pop())

print(my_class)





### pop随机索取 set

my_set={'A','B','C','D','E','F','G'}
print(my_set)
print(my_set.pop())
print(my_set)


### pop 取最后一个 list

my_list=['A','B','C','D','E','F','G']
print(my_list)
print(my_list.pop())
print(my_list)


```







# flask




debug 模式
app.run(debug==True)
错误信息会在浏览器中显示
热部署


支持外部访问
—host=0.0.0.0 
—port=8000


```python

@app.route(“/“)
def hello_world():
	return render_template(“index.html”)
	# return “hello_world”

 
@app.route(<int:blog_id>)
def  a(blog_id):
	Return …”is : %s” % blog_id
def <blog_id>
Def b(blog_id):
	Return …”is : %s” % blog_id

```

渲染html
访问成员
传如参数


过滤器
def datetime_format(value,format=“%Y-%d-%m %H:%M”):
	return value.strftime(format)
app.add_template_filter(datetime_formate,”dformat”)
# dformat. is just name
自定意过滤器
dformate 为 名字
可以实现一些简单的操作

Class User:
	def __init__(self,username,email)
		self.username=username
		self.email=email
@app.route(‘/’)
def hello_world():
	user=User(username=“治疗”,email=“qq.com”)
	return user.name



{%if            if%}
{%else if.      %}
{else }
{%endif% }




{{name.value}}                          
                                        
                                        
                                        
..                                      
{% extends "base.html" %}               
....                                    
base.html                               
{%block title%}{%endblock%}             
child1.html                             
{%block title%}..{%endblock%}           
                                        
                                        
pymysql                                 
pip intall pymysql                      
flask-sqlalchemy                   
......falsk........                     
..flask........                         

HOSTNAME=“127.0.0.1”
PORT-=3306
USERNAME=“root”
PASSWORD=“”
DATABASE=“database_learn”
app.config[‘SQLALCHEMY_DATABASE_URI’] = f”mysql+pymysql://{USERNAME}:{PASSWORD}@{HOSTNAME}:{PORT}/{DATABASE}?charset=utf-8”
db=SQLAlchemy(app)
# test can use or not
with db.engine.connect() as conn:
	rs=conn.execute(“select 1”)
	print(reps.fetchone())



ORM
user=User(username=“…”,password=“…”)
db.session.add(user)
db.session.commit()
	return “用户创建成功”


查找


def query_user():
	# get 查找 ；根据主键
	User.query.get(1)
	print(f”{user.id}:{user.username}—-{user.password}”)
	users=User.query.filter_by(username=“…”)
for user in Users:
	print(user.username)
	return “查找成功”

update
@app.route(“/user/update”)
def update_user():
	user=User.query.filter_by(username=“…”).first
#why first ? when got nothing got null not Exception
user.password	=“22222”
db.session.commit()
return “数据修改成功 ”

del
find->del->commit()
user=User.query.get(1)
db.session.delete(user)
db.session.commit()












                                       
上下文出问题
withapp.app_context():                  
        要执行的内容                 


# 涉及到外键
class User(db.Model):
	__tablename__=“user”
	id=db.Column(db.Integer,primary_key=Ture,autoincrement=True)
	userame=db.Column(db.String(100),nullable=False)
password=db.Column(db.String(100),nullable=False)

class Article(db.Model):
	__tablename__=“article”
	】id=db.Column(db.Integer,primary_key=Ture,autoincrement=True)
	titile=db.Column(db.String(100),nullable=False)
content=db.Column(db.String(100),nullable=False)



构建项目
config.py
数据库连接信息
models.py
from exits import db
class UserModel(db.Model):
	__tablename__=“user”
	id=db .Column(db.Inter,primary_key=True,autoincrement=True)
	username=db.Column(db.String(100),nullable=False)

	password=db.Column(db.String(100),nullable=False)
	email=db.Column(db.String(100),nullable=False,unique=True)
join_time=db.Column(db.Datetime,default=datetime.now)

auth.py
from flask import Blueprint
bp=Bludprint(“auth”,__name__,url_prefix=“/auth”)
@bp.route(“/login”)
def login():
	pass



app.py
from exits import db
from models import UserModel
from blueprints.qa import bp as qa_bp
from bludprints.auth import bp as auth_bp
from flask migrate import Migrate
 

app=Flask(__name__)
app.config.from_object(config)
db.init_app(app)
migrate =Migrate(app,db)
app.register_blueprint(qa_bp)
app.register_blueprint(auth_bp)

console
flask db init
flask db migrate
flask db upgrade



 


auth.py
from flask import Blueprint
bp=Bludprint(“auth”,__name__,url_prefix=“/auth”)
@bp.route(“/login”)
def login():
	pass

# update auth.py add
@bp.route(“/register”)
def register():
	return render-template(“register.html”)


# html’s css can’t use
you use render_template ,and it did not support relative path
herf=“./css/a.css”
herf={{url_for(‘css’,filename=‘a.css ’)}}
jingja2


flask-mail
ex qqmail
开启pop3/smtp
config.py
MAIL_SERVER=“smtp.qq.com” 
# if 163 163
MAIL_SER_SSL=True
MAIL_PORT=456
MAIL_USERNAME=“邮箱账号”
MAIL_PASSWORD=“开启SMTP服务时产生的授码”
MAIL_DEFAULT_SENDER+”邮箱账号”






exits.py









nihokninihiuiijijjijijjjjjkojjljkojkol









  
循环引用
1->2
2->1

1->2
2->3
3->1
还是循环
可以尝试切断第二个中的一个链接，然后反过来引用，即在用的时候创建







 














exits.py

from flask_sqlalchemy import SQLAlchemy
from flask_mail import Mail

db=SQLAlchemy
mail =Mail()



app.py
from exits import mail
mail.init_app(app)




auth.py
from exits import mail
from flask_mail import message
@bp.route(“/mail/test”)
def mail_test():
message=Message(subject=“邮箱测试”,recipients=[“22@qq.com”],body=“这是一个测试邮件”)
mail.send(message)
return “发送成功”



auth.py
@bp.route(“/captcha/email”)
def get_mail_captcha():
email=request.args.get(“email”)
# import sting random
source= string.digits*4
captcha=random.sample(source,4)
 captcha=“”,join(captcha)
# 调用test中的message方法，发送
email_capthca=EmailCatchaModel(email=email,captcha=captcha)
db.session.add(email_captcha)
db.session.commit()
return jsonify({ “code”:200,”message”:””,”data”:None})




class EmailCatchaModel(db.Model):
	__tablename__=“email_capcha”	id=db .Column(db.Inter,primary_key=True,autoincrement=True)	email=db.Column(db.String(100),nullable=False,unique=True)
captcha=db.Column(db.Stirng(100))



 
加载完成后执行
```js
$(function(){
$(“#captchabtn”).click(function(evvent{
// 当前按钮的对象
$(this).text(“abc”)
event.precentDefault();
var email=$(“innput”[name]=‘email’).val();
$.ajax({
url:”/auth/capture/email”,
method:”GET”,
success:function(result){
console.log(result);
var code=result[‘code’]
if(code==200){
    var countdown=200;
    var timer=SetInterval(function(){
    $this.text(countdown);
    countdown-=1;
    if(countdown<=0){
        clearInterval(timer);
         
},1000)
alter(“验证码发送成公”); 
}
else{
alter(result[‘message’]);
}
}
fail:function(error){
console.log(error); 
}
# route 默认get请求
# @bp.route(“/capatcha/email?emal=xx@qq.com”,method=[‘post’])
})
});
})


```

import wtforms      














 





# new



大纲
重点：
1、三种基本程序结构的实现方法
2、各种数据类型的熟练使用、函数的定义与使用
3、Python数据库应用、Tkinter图形界面
难点：
1、函数的定义与使用
2、Tkinter图形界面
3、Python数据库应用



第1章  Python语言介绍
1.1  Python语言简介
1.2  安装与配置Python环境
1.3  Python开发环境IDLE简介
1.4  Python基本输入输出
1.5  Python代码规范
第2章  Python语法基础
2.1  Python数据类型
2.2  常量和变量
2.3  运算符与表达式
2.4  序列数据结构
第3章  Python控制语句
3.1  选择结构
3.2  循环结构
3.3  常用算法及应用实例
3.4  游戏初步——猜单词游戏
第4章  Python函数与模块
4.1  函数的定义和使用
4.2  函数参数
4.3  闭包和函数的递归调用
4.4 内置函数
4.5  模块
第5章  Python文件的使用
5.1  文件
5.2 文件的访问
5.3 文件夹的操作
5.4  文件应用案例——游戏地图存储
第6章  面向对象程序设计
6.1  面向对象程序设计基础
6.2  类和对象
6.3  类的继承和多态
6.4  面向对象应用案例——扑克牌类设计
第7章  Tkinter图形界面设计
7.1  Python图形开发库
7.2  常用Tkinter 组件的使用
7.3  图形绘制
7.4  Tkinter字体
7.5  Python事件处理
7.6  图形界面程序设计的应用
第8章  Python数据库应用
8.1  数据库基础
8.2  结构化查询语言SQL
8.3  SQLite数据库简介
8.4  Python的SQLite3数据库编程
8.5  Python数据库应用案例
第9章 网络编程和多线程
第10章 科学计算和可视化应用









Web开发，基于python的优秀web框架如Django
科学计算，各种实验数据的处理以及相关实验模拟等，机器学习等
网络编程，网页解析，如爬虫程序开发
数据库应用
多媒体开发
电子游戏开发



1．Web开发
      Python语言支持Web网站开发，比较流行的开发框架有web2py、django等。许多大型网站就是用Python开发的，例如YouTube、Instagram，还有国内的豆瓣。很多大公司，包括Google、Yahoo等，甚至NASA（美国航空航天局）都大量地使用Python。
      利用py2exe模块可以将Python程序转换为.exe可执行程序，使得Python程序可以脱离Python系统环境来运行。
    2．网络编程
　Python语言提供了socket模块，对Socket接口进行了二次封装，支持Socket接口的访问；还提供了urllib、cookielib、httplib、scrapy等大量模块，用于对网页内容进行读取和处理，并结合多线程编程以及其他有关模块可以快速开发网页爬虫之类的应用程序。
3．科学计算与数据可视化
　　Python中用于科学计算与数据可视化的模块有很多，例如NumPy、SciPy、SymPy、Matplotlib、Traits、TraitsUI、Chaco、TVTK、Mayavi、VPython、OpenCV等，涉及的应用领域包括数值计算、符号计算、二维图表、三维数据可视化、三维动画演示、图像处理以及界面设计等。　　　
4．数据库应用
　Python数据库模块有很多，例如，可以通过内置的sqlite3模块访问SQLite数据库，使用pywin32模块访问Access数据库，使用pymysql模块访问MySQL数据库，使用pywin32和pymssql模块来访问SQL Sever数据库。
5．多媒体开发
　　PyMedia模块是一个用于多媒体操作的Python模块，可以对包括WAV、MP3、AVI等多媒体格式文件进行编码、解码和播放；PyOpenGL模块封装了OpenGL应用程序编程接口，通过该模块可在Python程序中集成二维或三维图形；PIL（Python Imaging Library，Python图形库）为Python提供了强大的图像处理功能，并提供广泛的图像文件格式支持。
6．电子游戏应用
　　Pygame就是用来开发电子游戏软件的Python模块，在SDL库的基础上开发，可以支持多个操作系统。使用Pygame模块，可以在Python程序中创建功能丰富的游戏和多媒体程序。





Google 在其网络搜索系统中广泛应用了Python ，并且聘用了Python 的创作者。
YouTube 视频分享服务大部分是由Python 编写的。
流行的P2P 文件分享系统Bittorrent 是一个Python 程序。
Intel 、Cisco 、Hewlett-Packard 、Seagate 、Qualcomm 和IBM 使用Python 进行硬件测试。
Industrial Light & Magic 、Pixar 等公司使用Python 制作动画电影。
NASA 、Los Alamos 、Fermilab 、JPL 等使用Python 实现科学计算任务。
IRobot 使用Python 开发了商业机器人真空吸尘器。
ESRI 在其流行的GIS 地图产品中使用Python 作为终端用户的定制工具。
NSA 在加密和智能分析中使用Python 。



任何编程语言都有缺点，Python缺点主要有：
（1）运行速度慢。
（2）代码不能加密。  你发行的exe程序可以容易被得到python程序
（3）用缩进来区分语句关系的方式还是给很多初学者带来了困惑。即便是很有经验的Python程序员也可能陷入陷阱当中。最常见的情况是tab和空格的混用会导致错误。





常用的IDE有Notepad++，PyScripter，PyCharm，Eclipse with PyDev，Komodo，Wing IDE
可能需要设置utf-8中文编码，否则肯能会出现异常




python基本输入输出
输入是通过input( )函数来实现的，input( )的一般格式为：
a=input('提示：')
在Python 2.7中，使用print语句进行输出，而Python 3.5中使用print()函数进行输出。
print输出格式
print(123,'abc',45,'book')
123 abc 45 book
print() 输出一个空格
print(123,'abc',45,'book',sep='$')
123$abc$45$book
print函数默认以回车换行符号为输出结尾，即在最后会换行，end可以指定最末尾
print('price',end='=');print(100)
price=100

3. 输出到文件
   print函数默认输出到标准输出流，输出到windows终端，可以用file参数指定输出到特定文件。
   ```python
   file1=open('pythontest.txt','w')
   print(123,'pythontest',45,'book',file=file1)
   file1.close()
   print(open('pythontest.txt').read())
   ```

for i in range(10): 
    print (i, end=' ')



Python程序是依靠代码块的缩进来体现代码之间的逻辑关系的




注释
#.。。
包含在一对三引号'''...'''或"""..."""之间且不属于任何语句的内容将被解释器认为是注释。


每个import只导入一个模块。而不要一次导入多个模块


如果一行语句太长，可以在行尾加上反斜杠“\”来换行分成多行，但是更建议使用括号来包含多行内容。



运算符两侧、函数参数之间、逗号两侧建议使用空格分开。不同功能的代码块之间、不同的函数定义之间建议增加一个空行以增加可读性。



常量名所有字母大写，由下划线连接各个单词。类名首字母大写。









使用帮助
1. 查看内置函数和类型的帮助信息
    `>>> help(max)`
2. `>>> import os `
   `>>> help(os.fdopen)`
3. 查看整个模块的帮助信息
   help(math)



# 第二节python语法教程

基本数据类型
整型(int) ：通常被称为是整型或整数，是正或负整数，不带小数点。
长整型(long) ：无限大小的整数，整数最后是一个大写或小写的L。在Python3里，只有一种整数类型 int，没有Python2 中的 Long。
浮点型(float)浮点型也可以使用科学计数法表示（2.78e2就是2.78 x 102 = 278）
复数(complex)：复数由实数部分和虚数部分构成，可以用a + bj,或者complex(a,b)表示，复数的虚部以字母j或J结尾。如：2+3j。


字符串
var1 = 'Hello World!'
使用特殊字符时，Python用反斜杠(\)转义字符

\n换行
![](/src/转义字符.png)




字符串运算符
\+ 字符串连接
\*重复输出字符串 a*2
a[1]索引字符串
in成员运算符,字符串中给定字符,则返回True
not in 字符中不包含
r 或者 R   原始字符
`print(R"\n print \n")`



字符串格式化
在 Python 中，字符串格式化使用与 C语言中printf函数一样的语法
`print ("我的名字是 %s 年龄是 %d " % ('xmj', 41))`



Python支持布尔类型的数据，布尔类型只有True和False两种值。
and与运算：只有两个布尔值都为 True 时，计算结果才为True。
or或运算：只要有一个布尔值为 True，计算结果就是 True。
not非运算：把True变为False，或者把False变为True：


在Python中，逻辑值True和False作为数值，则分别是整型值1和0参与运算。例如：
\>>> x=False
\>>>a=x+(5>4)    #结果a是1
\>>> b=x+5   #结果b是5


用None表示。它不支持任何运算也没有任何内置函数方法。None和任何其他的数据类型比较永远返回False。**在Python 中未指定返回值的函数会自动返回None。**



python强制类型转换
x=20					#八进制为24
y=345.6
print(oct(x)) 				#打印结果是0o24
print(int(y)) 				#打印结果是345
print(float(x)) 				#打印结果是20.0
print(chr(65)) 				# A的ASCII为65，打印结果是A
print(ord('B')) 				# B的ASCII为66，打印结果是66



变量名必须是大小写英文、数字和_的组合，且不能用数字开头
在Python中，等号=是赋值语句，可以把任意数据类型赋值给变量，同一个变量可以反复赋值，而且可以是不同类型的变量，例如：
a = 123		# a是整数
a = 'ABC'		# a变为字符串




PI = 3.14159265359
但事实上PI仍然是一个变量，Python根本没有任何机制保证PI不会被改变，所以，用全部大写的变量名表示常量只是一个习惯上的用法，实际上是可以改变变量PI的值


print(5/2) 2.5
**指数
//整数


==
!=
<> 类似!=






Python中提供了三种逻辑运算符，它们是：
and（逻辑与，二元运算符）
or  （逻辑或，二元运算符）
not （逻辑非，一元运算符）




位运算
按位与（&）、按位或（|）、按位异或（＾）、按位求反（~）、左移（<<）、右移（>>）。位运算符是对其操作数按其二进制形式逐位进行运算。
a = 	0011 1100
b = 	0000 1101
a&b =	0000 1100
a|b = 	0011 1101
a^b =	0011 0001
~a =	1100 0011




python成员运算
in
not in




比较两个对象的内存位置。 
is
is not


运算符优先级
![](/src/%E8%BF%90%E7%AE%97%E7%AC%A6%E4%BC%98%E5%85%88%E7%BA%A7.png)



序列
Python内置序列类型最常见的是**列表、元组、字符串**
序列中的每个元素都分配一个数字即它的位置或索引，第一个索引是0，第二个索引是1，依此类推。序列都可以进行的操作包括**索引，截取（切片），加，乘，成员检查**。此外，Python已经内置确定序列的**长度以及确定最大和最小**的元素的方法。
Python提供了字典和集合这样的数据结构，它们属于无顺序的数据集合体，不能通过位置索引号来访问数据元素



列表
只要把逗号分隔的不同的数据项使用方括号括起来即可。实例如下：
list1 = ['中国', '美国', 1997, 2000];
list2 = [1, 2, 3, 4, 5 ];
1．访问列表中的值
list1 = ['中国', '美国', 1997, 2000];
list2 = [1, 2, 3, 4, 5, 6, 7 ];
print ("list1[0]: ", list1[0] )
print ("list2[1:5]: ", list2[1:5] )
2.修改
list[2] = 2001;
print ( "New value available at index 2 : ")
print (list[2] )
3．删除列表元素
方法一：使用 del 语句来删除列表的的元素
方法二：使用 remove()方法来删除列表的元素
方法三：使用 pop()方法方法来删除列表的指定位置的元素，无参数时删除最后一个元素
4．添加列表元素
可以使用 append()方法在列表末尾添加元素，如下实例：
list1 = ['中国', '美国', 1997, 2000]
list1.append(2003)
print (list1)
5．定义多维列表
可以将多维列表视为列表的嵌套，即多维列表的元素值也是一个列表，只是维度比父列表小一。
例如：定义1个二维列表。
list2 = [["CPU", "内存"], ["硬盘","声卡"]]
二维列表比一维列表多一个索引，可以如下获取元素：
列表名[索引1][索引2]
例如：定义3行6列的二维列表，打印出元素值。
```python
rows=3
cols=6
matrix = [[0 for col in range(cols)] for row in range(rows)]   #列表生成式
for i in range(rows):  
    for j in range(cols):
        matrix[i][j]=i*3+j
        print (matrix[i][j],end=",")  
    print ('\n') 

>>> print [x*x for x in range(1 , 11)] 
[1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
```








元组
Python的元组（tuple）与列表类似，不同之处在于元组的元素不能修改。元组使用小括号()
3．元组连接
元组中的元素值是不允许修改的，但可以对元组进行连接组合，实例如下：
tup1 = (12, 34,56)
tup2 = (78, 90)
#tup1[0] = 100			#修改元组元素操作是非法的。
tup3 = tup1 + tup2		#连接元组，创建一个新的元组
print (tup3)
以上实例输出结果：(12, 34,56, 78, 90)  **列表也可以连接**
4．删除元组
元组中的元素值是不允许删除的，但可以使用del语句来删除整个元组。





元组与列表转换
可以使用下面方法将元祖转换为列表：
列表对象=list(元组对象)
```python
tup=(1, 2, 3, 4, 5) 
list1= list(tup) 					#元组转为列表
print (list1) 					#返回[1, 2, 3, 4, 5]
```
可以使用下面方法将列表转换为元祖：
元组对象= tuple (列表对象)
```python
nums=[1, 3, 5, 7, 8, 13, 20]
print (tuple(nums)) 				#列表转为元组，返回(1, 3, 5, 7, 8, 13, 20)
```






字典
Python字典（dict）是一种可变容器模型，且可存储任意类型对象，如字符串、数字、元组等。字典也被称作关联数组或哈希表。
1．创建字典
键值对key-value
一个简单的字典实例：
dict = {'zhangsan' : 91 , 'wangwu' : 80}
也可如此创建字典：
dict1 = { 'abc': 456 };
dict2 = { 'abc': 123, 98.6: 37 }; //!
2．访问字典里的值
访问字典里的值时把相应的键放入方括号里，如下实例：
dict = {'Name': '王海', 'Age': 17, 'Class': '计算机一班'}
print ("dict['Name']: ", dict['Name'])
print ("dict['Age']: ", dict['Age'])
3．修改字典
dict = {'Name': '王海', 'Age': 17, 'Class': '计算机一班'}
dict['Age'] = 18 			# 更新键/值对（update existing entry）
dict['School'] = "中原工学院" 	# 增加新的键/值对（add new entry）
print ("dict['Age']: ", dict['Age'] )
print ( "dict['School']: ", dict['School'];





集合
集合（set）是一个无序不重复元素的序列。集合基本功能是进行成员关系测试和删除重复元素。
1．创建集合
可以使用大括号({})或者 set()函数创建集合，注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典。
student = {'Tom', 'Jim', 'Mary', 'Tom', 'Jack', 'Rose'}
print(student)   # 输出集合，重复的元素被自动去掉
2.集合运算
可以使用“-”、“|”、“&”运算符进行集合的差集、并集、交集运算。
# set可以进行集合运算
a = set('abcd')        #a= {'a', 'b', 'c', 'd' }
b = set('cdef')
print(a)
print("a和b的差集：", a - b)     # a和b的差集{'a', 'b' }
print("a和b的并集：", a | b)     # a和b的并集 {'a', 'b', 'c', 'd' , 'e' }
print("a和b的交集：", a & b)    # a和b的交集{'c', 'd' }
print("a和b中不同时存在的元素：", a ^ b)     # a和b中不同时存在的元素









# 第3章  Python控制语句

if a > 6:
    print ( a, "大于6")



if…else语句的语法形式如下所示：
if 表达式 ：
    语句1
else ：
    语句2


if 表达式1 ：
    语句1
elif 表达式2 ： //else if
    语句2
    ……
elif 表达式n ：
    语句n
else ：
    语句n+1




count = 0
while count < 9:
   print ('Hello')
   count = count + 1
print ("Good bye!" )






for语句可以遍历任何序列的项目，如一个列表、元祖或者一个字符串。
for循环把列表中元素遍历出来。
fruits = ['banana', 'apple',  'mango']
for fruit in fruits:        # 第二个实例
   print ( '元素 :', fruit)
print( "Good bye!" )
q
元素：banana
元素：apple
元素：mango
Good bye!
q
for循环把字符串中字符遍历出来。
for letter in 'Python':     # 第一个实例
   print ( '当前字母 :', letter )
q
程序运行结果：
当前字母：P
当前字母：y
当前字母：t
当前字母：h
当前字母：o
当前字母：n


整肃序列
计算1~10的整数之和，可以用一个sum变量做累加。
程序代码：
sum = 0
for x in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
    sum = sum + x
print(sum)
Python提供一个range()内置函数，可以生成一个整数序列。
range(1, 101)就可以生成1~100的整数序列，计算1~100的整数之和如下：
sum = 0
for x in range(1,101):
    sum = sum + x
print(sum)
range(10)




break语句在while循环和for循环中都可以使用，一般放在if选择结构中，一旦break语句被执行，将使得整个循环提前结束。

continue语句的作用是终止当前循环，**并忽略continue之后的语句**，然后回到循环的顶端，提前进入下一次循环。



递推与迭代




第4章  Python函数与模块

```python
def  函数名(函数参数):
    函数体
    return 表达式或者值


def printHello():	#打印'hello'字符串
    print ('hello')
    
def printNum():	#输出0--9数字
    for i in range(0,10):
        print (i)
    return
        
def add(a,b): 	#实现两个数的和
    return a+b


```



lambda表达式

```python
f=lambda x,y,z:x+y+z
print (f(1,2,3))
等价于定义：
def f(x,y,z):
    return x+y+z
print (f(1,2,3))
函数使用return返回值，也可以将Lambda 表达式作为函数的返回值。
最后需要补充一点：Python中函数是可以返回多个值的，如果返回多个值，会将多个值放在一个元组或者其他类型的集合中来返回。

```


函数多个返回值
```python
def demo(s):
    result = [0,0]
    for ch in s:
        if 'a'<=ch<='z':
            result[1] += 1
        elif 'A'<=ch<='Z':
            result[0] += 1
    return result		#返回列表
print(demo('aaaabbbbC'))

```






参数的传递


函数形参和实参的区别

#这里的a和b就是形参
def add(a,b):
    return a+b
#下面是调用函数
add(1,2) 			#这里的1和2是实参


在Python中参数传递采用的是值传递，这个和C语言有点类似。对于绝大多数情况下，在函数内部直接修改形参的值不会影响实参。




```python
n=100
L=[1,2,3]
print (n,L)
```



1.默认值参数

```python

def display(a='hello',b='red'):
    print (a+b)
#主程序
display()
display(b='world')
display(a='china')
display('world')

# hellored
# helloworld
# chinared
# worldred
```



2.关键值参数
```python
def display(a,b):
    print (a)
    print (b)
# 下面2句达到的效果是相同的。
display(a='world',b='hello')
display(b='hello',a='world')

```




3．任意个数参数
一般情况下我们在定义函数时，函数参数的个数是确定的，然而某些情况下我们是不能确定参数的个数的，比如要存储某个人的名字和它的小名，某些人小名可能有2个或者更多个，此时无法确定参数的个数，只需在参数前面加上'*'或者'**'。
```python
def storename(name,*nickName):
    print ('real name is %s' %name)
    for nickname in nickName:
        print ('小名',nickname)
#主程序
storename('张海')
storename('张海','小海')
storename('张海','小海','小豆豆')

```

'*'和'\**'表示能够接受0到任意多个参数，'*'表示将没有匹配的值都放在同一个元组中，'**'表示将没有匹配的值都放在一个字典中。









1．局部变量

2.全局变量
但是如果要在函数内部改变全局变量值，必须使用global关键字进行声明
```python
x=2		#全局变量
def fun1():
    print (x, end=" ") 
def fun2():
    global x	#在函数内部改变全局变量值必须使用global关键字
    x=x+1
    print (x, end=" ")
fun1()
fun2()
print (x, end=" ")

```




4.3.1  闭包（closure）
在Python中，闭包指函数的嵌套。可以在函数内部定义一个嵌套函数。

```python
def func_lib():
    def add(x, y):
        return x+y
    return add       # 返回函数对象
 
fadd = func_lib()
print(fadd(1, 2))


```



递归调用
```python
def f(x):
   if x==1: 		#递归调用结束的条件
      return 1
   else:
      return(f(x-1)+x*x)	#调用f( ) 函数本身
print(f(5))


```




内置函数
内置函数(built-in functions)又称系统函数，或内建函数，是指Python本身所提供的函数，任何时候都可以使用。

Python常用的内置函数有数学运算函数、类型转换函数和反射函数等。想要查所有内置函数名可以在python命令行方式中如下输入：
\>>> dir(__builtins__)
	


![](/python/src/数学运算符.png)
![](/python/src/io函数.png)
![](/python/src/列表内置函数.png)
![](/python/src/字符串函数.png)
![](/python/src/%E5%8F%8D%E5%B0%84%E5%87%BD%E6%95%B0.png)






模块
模块（module）能够有逻辑地组织你的Python代码段。把相关的代码分配到一个模块里能让代码更好用，更易懂。简单地说，模块就是一个保存了Python代码的文件。模块里能定义函数，类和变量。


```python
import math        #导入math模块
print ("50的平方根：", math.sqrt(50))
y=math.hex (50) 
print ("50的16进制：",y)

```

1．导入模块方式
在Python中用关键字import来导入某个模块。方式如下：
import 模块名			# 导入模块
有时候我们只需要用到模块中的某个函数，只需要引入该函数即可，此时可以通过语句：
from 模块名 import 函数名1,函数名2....
通过这种方式引入的时候，调用函数时只能给出函数名，不能给出模块名


2  定义自己的模块

在Python中，每个Python文件都可以作为一个模块，模块的名字就是文件的名字。
比如有这样一个文件fibo.py，在fibo.py中定义了3个函数add()，fib()，fib2()：
那么在其他文件（如test.py）中就可以如下使用： 



```python
#test.py
import fibo
加上模块名称来调用函数： 
fibo.fib(1000)     #结果是1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987
fibo.fib2(100)     #结果是[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
test.add(2,3)      #结果是5

```

# 文件
文件的概念
简单地说，文件是存储在辅助存储器上的数据序列，在逻辑上具有完整意义。
文件是数据的集合和抽象；相对的，函数是程序的集合和抽象。
用文件组织数据更为灵活和有效。
Windows系统的数据文件按照编码方式分为两大类：
文本文件
二进制文件

文本文件
文本文件由单一特定编码的字符组成，如UTF-8编码。
文本文件可以处理各种语言所需的字符。文件中只包含基本文本字符，不包括诸如字体、字号、颜色等信息。它可以在文本编辑器和浏览器中显示。即在任何情况下，文本文件都是可读的。
文本文件可以看做存储在存储器上的一个长字符串。




二进制文件
二进制文件由比特0和比特1组成，文件数据的组织格式与文件用途有关。二进制文件是信息按照非字符的特定格式形成的文件，如jpg格式的图像文件、avi格式的视频文件。
二进制文件与文本文件最大的区别就是有没有统一字符编码。二进制文件没有统一字符编码，使用其它编码方式。
二进制文件可以看作字节流，而不是字符串。



读文本文件
`file1=open("c:\ly\and.txt","rt")`
读二进制文件
`file1=open("c:\ly\and.txt","rb")`




文件的访问

在Python中对文件的操作通常按照以下三个步骤进行：
（1）使用open()函数打开（或建立）文件，返回一个file对象。
（2）使用file对象的读/写方法对文件进行读/写的操作。其中，将数据从外存传输到内存的过程称为读操作，将数据从内存传输到外存的过程称为写操作。
（3）使用file对象的close()方法关闭文件。

















```python
Fiel.write(s)
File.writelines(lines)
File.seek(offset) 下边有解释

Fiel.readall()
File.read(size)
File.readline(size)
File.readlines(hint)

```




r 只读模式，若文件不存在则返回异常，默认值
w 覆盖写模式，文件不存在则创建，存在则覆盖
x 创建写模式，文件不存在则创建，存在则返回异常
a 追加写模式，文件不存在则创建，存在则追加
b 二进制文件模式
t 文本文件模式
\+ 与r/w/x/a一起使用，在原功能基础上追加读写功能


默认rt模式
`>>> helloFile=open("d:\\python\\hello.txt")`


字符串或字节流取决于文件的打开方式。文件以文本方式打开则返回字符串；以二进制方式打开则返回字节流。




操作方法	含义
<file>.readall()	读入整个文件内容，返回一个字符串或一个字节流
<file>.read(size=-1)	读入整个文件内容，若给出参数size，则读入前size长度的字符串或字节流
<file>.readline(size=-1)	从文件读入一行内容，若给出参数size，则读入该行前size长度的字符串或字节流
<file>.readlines(hint=-1)	从文件读入所有行内容，以每行内容为元素形成列表，若给出参数hint，则读入hint行





读取文本文件
1．read()方法


```python
helloFile=open("d:\\python\\hello.txt")
fileContent=helloFile.read()
helloFile.close()
print(fileContent)	

```
2．readline()方法
readline()方法从文件中获取一个字符串，每个字符串就是文件中的每一行。

```python
helloFile=open("d:\\python\\hello.txt")
fileContent=""
while True:
   line=helloFile.readline()
   if line=="":    # 或者 if not line
      break
   fileContent+=line
helloFile.close()
print(fileContent)
	

```
3．readlines()方法
readlines()方法从文件中获取全部内容，并形成一个列表，列表的每一个元素就是文件里每一行的内容。
当文件过大时，一次读入所有内容会占用过多内存，造成执行效率下降


```python
helloFile=open("pythontest.txt")
for line in helloFile:
    print(line)
helloFile.close()

```





写文本文件
写文件与读文件相似，都需要先使用open()函数创建文件对象连接。所不同的是，打开文件时是以“写”模式或“添加”模式打开。如果文件不存在，则创建该文件。
与读文件时不能添加或修改数据类似，写文件时也不允许读取数据。







```python
方法	含义
<file>.write(s)	向文件写入一个字符串或一个字节流
<file>.writelines(lines)	将一个元素全为字符串的列表写入文件

```

文件复制


```python
def copy_file(oldfile,newfile):
    oldFile=open(oldfile,"r")
    newFile=open(newfile,"w")
    while(True):
        fileContent=oldFile.read(50)
        if fileContent=="":
            break
        newFile.write(fileContent)
    oldFile.close()
    newFile.close()
    return
copy_file("pythontest.txt","newpytontest.txt")
```



writelines()方法向文件写入一个列表，列表的每一个元素都是字符串。



```python

hmldFile=open("pythontest.txt", "a+")
ls=["我最亲爱的祖国\n","我永远紧依着你的心窝\n","你用你那母亲的脉搏\n","和我诉说\n"]
hmldFile.writelines(ls)
hmldFile.seek(0,0)
for line in hmldFile:
    print(line)
hmldFile.close()	



```


offset




seek
offset – 开始的偏移量，也就是代表需要移动偏移的字节数
whence：可选，默认值为 0。给offset参数一个定义，表示要从哪个位置开始偏移；0代表从文件开头开始算起，1代表从当前位置开始算起，2代表从文件末尾算起。
seek(0,0)//从开头偏移0 的位置



当文件以文本文件方式打开时，只能默认从文件头计算偏移量，即whence参数为1或2时，offset参数只能取0，Python解释器不接受非零当前偏移。



以二进制方式打开文件时，文件指针可以参数offset的值从参数whence指定的位置向前/向后偏移。



文件的关闭
```python
helloFile=open("C:\\lynn \\python\\hello.txt","w")
try :
	helloFile.write("Hello,Sunny Day!")
finally:
	helloFile.close()



也可以使用with语句自动关闭文件：
with open("d:\\python\\hello.txt") as helloFile:
	s=helloFile.read()
print(s)

```



二进制文件的读写
Python没有二进制类型，但是可以用string字符串类型来存储二进制类型数据，因为string是以字节为单位的。
1．数据转换成字节串（以字节为单位的字符串）
pack()方法可以把数据转换成字节串。
格式：pack(格式化字符串，数据)
格式化字符串中可用的格式字符见表5-1中格式字符。





```python
import struct
a=20
ex1File=open("a.txt","wb")
bytes=struct.pack('i',a)	#将a变为string字节串
ex1File.write(bytes)
ex1File.close
print(bytes)

```
2．字节串（以字节为单位的字符串）还原成数据
unpack()方法可以把相应数据的字节串还原成数据。
bytes=struct.pack('i',20)	#将20变为string字符串
再进行反操作，现有二进制数据bytes（其实就是字符串），将它反过来转换成Python的数据类型：
a,=struct.unpack('i',bytes)



通过struct.unpack()解码成变量或元组


```python
import struct
binfile=open("a.txt","rb")
bytes=binfile.read()
(a,b,c,d)=struct.unpack('5s6sif',bytes)
t=struct.unpack('5s6sif',bytes) 
print(t)

```

```python
import struct
s1="binary"
s2="file"
pi=3.1415
i=10
ex1File=open("a.txt","wb")
bytes=struct.pack("6s4sfi",s1.encode("utf-8"),s2.encode("utf-8"),pi,i)
ex1File.write(bytes)
ex1File.close()
ex1File=open("b.txt","rb")
(s1,s2,pi,i)=struct.unpack("6s4sfi",ex1File.read())
ex1File.close()
print(s1,s2,pi,i)
```




## 文件夹的操作

当前工作目录
在交互式环境中输入以下代码：
\>>> import os
\>>> os.getcwd()


```python
import os
print(os.getcwd())
```

路径
相对路径
..\
.\




```python

1．创建新目录
程序可以用os.makedirs()函数创建新目录。在交互式环境中输入以下代码：
>>> import os
>>> os.makedirs("e:\\python1\\ch5files")
2．删除目录
当目录不再使用，可以将它删除。使用rmdir()函数删除目录：
>>> import os
>>> os.rmdir("e:\\python1\\ch5files")
>>> os.rmdir("e:\\python1")
>>> os.path.exists("e:\\python1") 	#运行结果为False
3．列出目录内容
使用os.listdir()函数可以返回给出路径中文件名及文件夹名的字符串列表：
>>> os.mkdir("e:\\python1")
>>> os.listdir(“e:\\python1”)	#返回空列表
4．修改当前目录
使用os.chdir()函数可以更改当前工作目录：
>>> os.chdir("e:\\python1")
>>> os.listdir(".")   	#.代表当前工作目录


```







```python
1．获取路径和文件名
os.path.split(path)：返回参数的路径名称和文件名组成的字符串元组。
os.path.dirname(path)：返回path参数中的路径名称字符串。
os.path.basename(path)：返回path参数中的文件名。
2．检查路径有效性
os.path.exists(path)：判断参数path的文件或文件夹是否存在。存在返回true，否则返回false。
os.path.isfile(path)：判断参数path存在且是一个文件，则返回true，否则返回false。
os.path.isdir(path)：判断参数path存在且是一个文件夹，则返回true，否则返回false。
3．查看文件大小
os.path模块中的os.path.getsize()函数可以查看文件大小。
4．重命名文件
os.rename()函数可以帮助我们重命名文件。
os.rename("d:\\python\\hello.txt","d:\\python\\helloworld.txt")
5．复制文件和文件夹
shutil模块中提供一些函数，帮助我们复制、移动、改名和删除文件夹。可以实现文件的备份。
6．文件和文件夹的移动和改名
shutil.move(source,destination)：shutil.move()函数与shutil.copy()函数用法相似，参数destination 既可以是一个包含新文件名的路径，也可以仅包含文件夹。
7．删除文件和文件夹
os模块和shutil模块都有函数可以删除文件或文件夹。
os.remove(path)/os.unlink(path)：删除参数path指定的文件。
os.remove("e:\\python-backup\\data-backup.txt")
os.path.exists("e:\\python-backup\\data-backup.txt")   #False




```



统计指定文件夹下所有文件的大小。


```python
import os
totalSize=0
os.chdir("C:\\lynn\\Python")
for fileName in os.listdir(os.getcwd()):
    totalSize+=os.path.getsize(fileName)
print( totalSize)

```


os.walk()函数返回参数路径下所有文件及子目录信息元组，即遍历目录树。


```python
import os
list_dirs = os.walk("C:\\lynn\\Python")                 #返回一个元组
for folderName,subFolders,fileNames in list_dirs:
    print("当前目录：" + folderName + "\n")
    for subFolder in subFolders:
        print(folderName +"的子目录" + " 是--" + subFolder + "\n")
        for fileName in fileNames:
            print(subFolder +"的文件 " +  " 是--" + fileName + "\n")



```




文件的应用
![](/python/src/文件的应用.jpg)

```python
import os
#地图信息写入文件
helloFile=open("map1.txt","w")
helloFile.write("0,0,0,3,3,0,0\n")
helloFile.write("3,3,0,3,4,0,0\n")
helloFile.write("1,3,3,2,3,3,0\n")
helloFile.write("4,2,0,3,3,3,0\n")
helloFile.write("3,3,3,0,3,3,0\n")
helloFile.write("3,3,3,0,0,3,0\n")
helloFile.write("3,0,0,0,0,0,0\n")
helloFile.close()

从文件中读取地图信息
#读文件
helloFile=open("map1.txt","r")
myArray1=[]
while True:
   line=helloFile.readline()
   if line=="":  			# 或者 if not line
      break
   line=line.replace("\n","")   	#将读取的1行中最后的换行符去掉
   myArray1.append(line.split(","))
helloFile.close()
print(myArray1)


```
案例2、文章词频统计

```python
def getFileText():		
	 with open("C:\\lynn\\Python\\letter.txt","r") as letterFile:
        		filTxt=letterFile.read()
	filTxt=filTxt.lower()
	for ch in '!"#$%&()*+-*/,.:;<=>?@[]\\^_{}|~':
		filTxt=filTxt.replace(ch," ")
	return filTxt
letterTxt=getFileText()




使用filTxt.lower()函数将所有单词转为小写形式，保证同一个单词不同大小写形式统计的一致
用filTxt.replace()方法将特殊字符统一替换为空格，为后面的分词做准备


words=letterTxt.split()
wdCountDict={}
excludes={"the","of","you","your","that","will","this","don't"}
for word in words:
	wdCountDict[word]=wdCountDict.get(word,0)+1
for word in excludes:
        del(wdCountDict[word])


分词并使用字典统计每一个单词的词频
去掉对“停止词”的统计


对结果排序并输出

将字典转换为列表，为排序做准备
按列表的第2项使用lambda函数排序
将单词长度小于3（停止词）和出现次数过少的单词排除掉，
最后将结果按单词出现次数从大到小输出

items=list(wdCountDict.items())	
items.sort(key=lambda x:x[1],reverse=True)
print("{0:<10}{1:>5}".format("word","count"))
print(“*"*21)
for key,val in items:
	if len(key)>3 and val>2:
		print("{0:<10}{1:>5}".format(key,val))



```


allcode

```python
def getFileText():		
	filTxt=open("C:\\lynn\\Python\\letter.txt","r").read()
	filTxt=filTxt.lower()
	for ch in '!"#$%&()*+-*/,.:;<=>?@[]\\^_{}|~':
		filTxt=filTxt.replace(ch," ")
	return filTxt
letterTxt=getFileText()
words=letterTxt.split()
wdCountDict={}
excludes={"the","of","you","your","that","will","this","don't"}
for word in words:
	wdCountDict[word]=wdCountDict.get(word,0)+1
for word in excludes:
        del(wdCountDict[word])
items=list(wdCountDict.items())	
items.sort(key=lambda x:x[1],reverse=True)
print("{0:<10}{1:>5}".format("word","count"))
print("_"*21)
for key,val in items:
	if len(key)>3 and val>2:
		print("{0:<10}{1:>5}".format(key,val))

```




#### tls
sort
```python
lst=[2,3,1,4,0]
lst.sort()
print(lst)
lst.sort(reverse=True) # 默认为False
print(lst)
```
out
[0, 1, 2, 3, 4]
[4, 3, 2, 1, 0]






#### pyton differ from c++
python differ from c++
 

缩进代替中括号

冒号代替()

score = 63

if score >= 60:
    print('You passed!')
 

  while a != 0:  #a不等于0就一直做
       print "please input"
       a = int(input())
   print "over"
 

Python pass 语句
类似于c++中;
保持结构的完整性
 

del和GC

垃圾回收器 Garbage Collector 的进程完成的。（GC）机制

GC的优缺点
好处是
开发者无需过问内存管理，可以专注于解决实际问题。虽然内存泄露仍有可能会发生，但发生的概率比较小。

GC的智能算法可以在后台自动的进行内存管理，且这种管理在大多数时候是最佳的。

坏处是
当垃圾回收发生时，它会影响程序的性能，甚至会暂停程序的执行。这个被称为“Stop the world”垃圾回收机制，整个程序进程会被暂停以等待垃圾回收执行完。对某些应用而言，这可能是无法接受的。

开发者并不能指定何时或使用何种方法执行GC机制

 

python的del不同于C的free和C++的delete。

由于python都是引用，而python有GC机制，所以，del语句作用在变量上，而不是数据对象上。

复制代码
if __name__=='__main__':
    a=1       # 对象 1 被 变量a引用，对象1的引用计数器为1
    b=a       # 对象1 被变量b引用，对象1的引用计数器加1
    c=a       #1对象1 被变量c引用，对象1的引用计数器加1
    del a     #删除变量a，解除a对1的引用
    del b     #删除变量b，解除b对1的引用
    print(c)  #最终变量c仍然引用1
复制代码
 

#拷贝列表，也不会有数据对象的复制，而是创建新的变量引用
python类型转换

y=int(x)
>>> import math
>>> dir(math)





#### 引号问题

python中最常用的字符串表示方式是单引号（''）和双引号（""）

 

内容带有单引号，就用双引号表示

"It's good"

反之亦然

‘You are a "BAD" man’

还有一种在字符串中表示引号的方法，就是用\，可以不受引号的限制

 

\'表示单引号，\"表示双引号

'I\'m a \"good\" teacher'

 

\还有个用处，就是用来在代码中换行，而不影响输出的结果：

"this is the\

same line"

 

 

在三个引号中，你可以方便地使用单引号和双引号，并且可以直接换行

'''

"What's your name?" I asked.

"I'm Han Meimei."

'''

# 面向对象







```python
class Person:
    num=0			#成员变量（属性）
    def SayHello(self): 	#成员函数
        print("Hello!");

class Car:  
    def infor(self):
	    print(" This is a car ") 

通过“对象名.成员”的方式来访问其中的数据成员或成员方法

>>> isinstance(car, Car)
True
>>> isinstance(car, str)
False






```























































































































































































































































































































































































































































































































































































































































































































































































	


















































































































# bug


```python
final_duration_clips,final_text_clips=[],[]
for item in voice:
    for line in item:
        text_clips,duration_clips=[],[]
        filename = str(line.split('/')[-1])
        tmp = filename.split('_')
        # 获取语音文件名中的文本和时长
        text_clips.append(tmp[0])
        # print(tmp[0])
        duration_clips.append(float(tmp[-1].replace('.mp3', '')))
    final_text_clips.append(text_clips)
    final_duration_clips.append(duration_clips) 修正代码
```
你需要将 text_clips 和 duration_clips 的初始化移到循环外面，这样才能保存每个行的所有音频文件的文本和时长。以下是修改后的代码：

```python
Copy
final_duration_clips = []
final_text_clips = []

for item in voice:
    text_clips = []
    duration_clips = []
    for line in item:
        filename = str(line.split('/')[-1])
        tmp = filename.split('_')
        # 获取语音文件名中的文本和时长
        text_clips.append(tmp[0])
        duration_clips.append(float(tmp[-1].replace('.mp3', '')))
    final_text_clips.append(text_clips)
    final_duration_clips.append(duration_clips)
```


您可以通过以下命令升级Django版本：

也可以使用3.2版本  pip install Django==3.2
pip install --upgrade django




## pip

```bash
pip -h

pip install falsk 
    pip intall flask==1.0

pip install -r requestment.txt

pip install --upgrade 包名称

安装 whl 文件
pip install xxxx.whl

pip uninstall 包名

pip uninstall -r requriements.txt

列出安装的所有包
pip list

查看某一个包的具体信息
pip show 包名
```


## python将.py文件打成exe可执行程序详细教程

1. `pip install pyinstaller`
2. 安装成功后，会生成pyinstaller.exe文件，这个文件所在的目录要加入到【环境变量】中
3. `pyinstaller  -F -c main.py`

```
【pyinstaller -F -c 源python文件】
常用可选项及说明：
    -F：打包后只生成单个exe格式文件；
    -D：默认选项，创建一个目录，包含exe文件以及大量依赖文件；
    -c：默认选项，使用控制台(就是类似cmd的黑框)；
    -w：不使用控制台；
    -p：添加搜索路径，让其找到对应的库；
    -i：改变生成程序的icon图标。
```

    可进入CMD窗口，然后用.\main.exe的方式执行main.exe，而不是直接双击，这样就可以在cmd窗口里面看到具体的过程



	pdb3
	python debug 3


	pdb中可输入help or h(elp)

	h break
	break 5

	continue
	cont
	c

	clear guide.py 5
	清除第五行的断点

	clear 断点编号

	ord
	chr


	Dec 对应的是 10 进制数
	Hex 对应的是 16 进制数



	再沿着2的方向
	向左找到0001
	这是他的低四位
	4321位
	在前面加一个0
	得到(01100001)2进制



	bin 10->2

	除二取余法

	type


	2->10  直接输出
	(二进制数0b开头)













	0 的起源

	变量名开头不许是数字
	这就确保了 0 开头肯定是数字
	但正常情况下写数字不会用 0 开头
	这保证 0 开头很容易和 一般的 10进制数 区分开
	在 c 语言之前的 B 语言用 0 开头表示 8 进制
	c 语言继承了类似设定
	0o 开头表示 8进制数
	0b 开头表示 2 进制数





	hex() 十六进制




	>>> hex(99)
	'0x63'
	>>> hex(9+1)
	'0xa'
	>>> hex(9+2)
	'0xb'
	>>> hex(9+3)
	'0xc'
	>>> hex(9+4)
	'0xd'
	>>> hex(9+5)
	'0xe'
	>>> hex(9+6)
	'0xf'
	>>> hex(9+7)
	'0x10'

	:%!xxd


## 虚拟环境 使用pthon自带的虚拟环境 ->> venv

1. 选择一个目录
2. 终端运行`python -m venv envTest`
3. 启动脚本`envTest\Scripts\activate` 激活虚拟环境
4. `envTest\Scripts\deactivate`,脚本关闭虚拟环境
5.  使用bat脚本运行虚拟环境

```bat
@echo off
REM 激活虚拟环境
call L:\libToHtml\pythonMarkdownTohtml\Scripts\activate

REM 执行虚拟环境内的 Python 脚本
python L:\libToHtml\main2.py

REM 退出虚拟环境
deactivate
```
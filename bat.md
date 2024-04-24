## 其他 待处理

cd %~dp0

这行命令的作用是将当前目录更改为批处理文件所在的目录。%~dp0 是一个批处理文件中的参数，代表当前批处理文件所在的驱动器和路径。因此，cd %~dp0 会将当前工作目录更改为批处理文件所在的目录。



在 Windows 环境中，使用 set 命令设置的环境变量会在当前命令行窗口中生效，但在关闭该窗口后会失效。


# dos
```


dir
type 查看文件内容 
cat 查看文件内容
echo

echo "99" > a.txt
有则覆盖,无则创建

del
del指令无法删除目录,但是可以删除目录下的文件
`>> 类似与> 但是存在则追加`
copy con a.txt 等待控制台输入并且写入:会覆盖
rd 
rmdir
mkdir
del

/s 递归
/q quit 自动确认
ren 重命名
ren a.txt a.cpp
fsutil 文件管理有关
fsutil file createnew a.txt 9999
(字节)
move 移动文件
assoc 修改文件关联性:比如使系统吧txt结尾的文件识别成exe
shutdown -s -t 3600
start www.baidu.com
start cmd
start explorer
taskkill /im 指定名称 /f强制杀死 /pid 用pid杀死

ping -n 次数 默认四次
ping 一次大概一秒钟
>
con 控制台
nul 空设备

>nul 重定向到空设备nul 即不显示输出信息
>nul表示不显示ping的结果，但是不能隐藏错误信息。2>null表示隐藏错误信息
ping -n 8 127.0.0.1 >nul 2>nul
延时操作
于是最后这句命令就变成了延迟命令，表示等待4秒钟
type a.txt > con 打印到控制台
我们输入的命令默认会重定向到控制台，也就是cmd窗口。比如你在命令提示符窗口键入dir会在控制台打印出目录文件，这时候其实运行的是dir >con，con就是指的console控制台。>nul表示将输出重定向到空设备nul，也就是不显示输出。
于2>nul的2，指的是stderr标准错误输出流，是用来输出错误信息的，这里就表示不输出错误信息。对应的，还有0>nul,1>nul。0表示stdin标准输入流，就是在控制台键入的信息。1表示stdout标准输出流，也就是正常输出的信息。1>nul其实就是>nul。

set /p a=100 定义一个a,其值为100
也可以用于定义一个用户输入的变量
set /p a= pleae input a number

@echo off表示执行了这条命令后关闭所有命令(包括本身这条命令)的回显。而echo off命令则表示关闭其他所有命令(不包括本身这条命令)的回显，@的作用就是关闭紧跟其后的一条命令的回显，关于上述例子应该表现的很清楚了，不过还有几点需要解释一下：

pause也是一条命令，作用就是使程序暂停，也就是输出“请按任意键继续…”的原因。
::是批处理文件中一种注释语句的方式，其与rem命令的区别我们后续再说。
@echo off执行以后，后面所有的命令均不显示，包括本条命令。
echo off执行以后，后面所有的命令均不显示，但本条命令是显示的。


-s代表关机，-r代表重启，-a代表取消所有定时，-f代表强制执行(不论你当时在做什么)，-t代表定时操作，后面加定时时间(单位是秒)，-c用于在消息框展示提示信息，后面加要提示的内容

　　注意，一旦设置了定时，只能用命令行去解除

tasklist
tasklist | findstr 222
grep 类似 findstr 管道过滤符号

taskkill /f /im 进程号 /t 
其中/t表示递归杀死该进程和子进程



```



ii . 打开windows资源管理器 powershell



## 基础命令 echo rem cd dir


### 创建文件

```bash
echo 1 > 1.txt
```



### echo


```bat
echo hello world


@ 放在命令之前,关闭该命令的回显
@echo hello world

echo {on|off} 打开\关闭回显

echo. 输出空行,相当于回车
```


### rem

```bash
rem 这是一个注释，不会被执行
```


### cd

```bash
cd [盘符][路径]
cd C:\Documents and Settings\mzybar
cd..  cd ..  返回上级
cd\  cd \  cd/  cd /  返回本磁盘的根目录
. 代表当前目录

切换磁盘
cd d:
cd c:

查看当前路径
echo %cd%

```



### dir


```bash
dir c:\windows
dir /p c:\windows 每页暂停 page
dir /b c:\windows 不显示摘要 只显示名称
```




## 基础命令 attrib del copy xcopy

### attrib

显示或更改文件属性。

查看文件的属性

`attrib file `

修改文件的属性
......

批量文件处理


### del

删除一个或数个文件。

```
del file
del a.cpp

/P 删除每一个文件之前提示确认。
del /p d:\123\*.*
删除d:\123目录下所有文件，如果你想让它在删除前都询问你是否删除，我们可以加上/p参数，防止误删除。

/s search
del /s d:\123\*.*
删除d:\123目录及其子目录下所有文件。
通过使用/s参数后，del命令就会在指定目录(如未指定则在当前目录)及其子目录中搜索所有指定文件名的文件并删除。

/q 安静模式。删除全局通配符时，不要求确认。 quiet
del /s /q d:\123\*.*
删除d:\123目录及其子目录下所有文件。
通过/q参数则无需确认直接删除，在使用此参数时要小心！！

```

### copy

copy只能复制文件，不能复制文件夹。
`copy source destination_path`

`copy c:\123.txt d:\`
把123.txt复制到d:\目录下。如果此操作成功，命令行窗口中会提示“已复制 1个文件”。

`copy c:\123.txt d:\abc.bat`
把123.txt复制到d:\目录下，并把它修改为abc.bat，这里修改了文件名和它的格式。

`copy .\123`
把当前目录下的123子目录中的文件全部拷到**当前目录**。


###  批量复制文件

copy c:\123 d:\abc
将c:\123 目录中所有文件复制到d:\abc目录中.




## 基础命令 md rd ren move

### md

md mkdir

`md "abc and def"`
由于路径中有空格，所以我们用双引号" "把路径和文件夹名一起括起来了。


创建多个目录

`格式:md [路径\]目录1 [路径\]目录2 [路径\]目录3……`
md abc D:\gmae\123 abcd



### rd

rmdir rd

在不加任何参数时，rd命令只能删除空的文件夹。

/s  除目录本身外，还将删除指定目录下的所有子目录和文件。用于删除目录树。
`rd /s d:\123`
如果文件夹123不为空，则通过/s参数，可删除文件夹123。

/q  安静模式，带 /s 删除目录树时不要求确认。


### ren

rename ren

单个文件重命名
`ren d:\123.txt 456.bat`

**批量重命名**
`ren *.bat *.txt`


### 移动文件(夹)


`move 123.txt abc`
把当前目录下的123.txt移动到当前文件夹abc内。这里都使用了相对路径，缺省路径情况下默认为当前目录。


`move d:\abc d:\abcd`
这里要注意一下，如果目标文件夹abcd不存在，则把abc移到d:\下，并把自身名称更改为abcd。
利用这一特性，move命令具备重命名功能。



`move /y 123.txt 456.txt`
覆盖移动,不会提示


MOVE命令可以跨分区移动文件，却不可以跨分区移动文件夹。
对于隐藏或系统属性的文件，只有把它们的属性去除后，move命令才会执行。




## 基础命令  find findstr

### find

1.字符串查找：find
2.字符串查找增强：findstr

一.字符串查找：find
在文件中搜索字符串。


`find "abc" d:\abc.txt`
在abc.txt中查找字符串abc的行。

/i 搜索字符串时忽略大小写。Ignore
`find /i "abc" d:\abc.txt`


/N 显示行号。Number
`find /n "abc" d:\abc.txt`


/C 仅显示包含字符串的行数。  Count
`find /c "abc" d:\abc.txt`







### findstr


`findstr "icq" 123.txt`
findstr 命令中正则表达式的用法规则

`findstr ac* 123.txt`
在文件123.txt中查找出现一个“a”字符串，以及a后面出现过0次或者任意次c的字符行。

`findstr ak5* 123.txt`
在文件123.txt中查找出现一个“ak”字符串，以及ak后面出现过0次或者任意次5的字符行。




#### 行首、行尾符规则，即 ^ 和 $

`findstr "^step" 123.txt`
在文件123.txt中查找行首为step字符串的行。

`findstr "step$" 123.txt`
在文件123.txt中查找行尾为step字符串的行。

`findstr "^step$" 123.txt`
在文件123.txt中查找行首为step,且行尾也为step的行，即step独自一行。





## 网络命令


### ping

```bash
ping baidu.com

ping -n 5 baidu.com

ping本机11次，可用于批处理延时10秒。命令中的>nul为屏蔽输出。
简短式可以写成：
ping -n 11 127.0.0.1  > nul

   > nul  定向到空设备,意味着屏蔽输出

```



### ipconfig

ipconfig
ipconfig /all



## 启动应用,启动和关闭计算机

### start

开启命令：start

```
基本格式：start "title" filename  
其中title可以省略，filename如带有空格或是特殊符号的，用""括起来。
"title"是窗口的标题（可以是任何你想要的文本）

start http://www.baidu.com
这里是打开百度，title省略了。


start "microsoft edge" www.baidu.com

  ps: 此处的titile为命名bat脚本时候,我们可以选择的title名称, 在资源管理器里面可以查看

start "%windir%\system32\NOTEPAD.EXE" "%userprofile%\桌面\abc.txt"

用NOTEPAD（记事本）打开 桌面上的abc.txt。

start "" "%userprofile%\桌面\abc.doc"
这里start后面要加""才能启动文件abc.doc，否则不能启动。
如果start 后没有 " " 则表示把title省略了，此时文件名若有""的话start就会把它看作是标题，从而变成了省略文件名，就默认开启cmd了。所以在start后加上""就能防止这种意外的情况。
```

#### 参数/min和/max  使开启的窗口最小化和最大化。

start /min "" "%windir%\system32\NOTEPAD.EXE" "%userprofile%\桌面\abc.txt"
用记事本最小化打开桌面的abc.txt。



### 关闭和启动计算机


```bash
shutdown -l
该命令只能注销本机用户，对远程计算机不适用。



at 12:00 shutdown -s
通过-s参数，在12：00执行关闭计算机。



shutdown -r -t 60
60秒后重启计算机。这里是设置重启倒计时为60秒。如果没有设置时间则默认为30秒。


shutdown -f -s -t 300
通过-f参数强制计算机300秒后关闭。


shutdown -a    
终止当前的关机进程

```



## 进程处理taskkill

Taskkill命令可以根据进程 ID 或图像名来结束一个或多个任务或进程。

/im ImageName
指定将终止的进程的图像名称。
`taskkill /im qq.exe`

/f process
指定将强制终止的进程。对于远程进程可忽略此参数，所有远程进程都将被强制终止。
`taskkill /f /im spoolsv.exe`

/pid process id    指定要终止的进程的PID
`taskkill /pid 1230 /pid 1241 /pid 1253`
这里是终止PID 分别为 1230 1241 1253的进程。





## 其他命令

regsvr32,netsh,sc,wmic

...


## 符号命令

1．>、>>重定向符
2．| 命令管道符
3．&、&&、|| 组合命令

> 使用 "&" 时，无论前一个命令是否成功执行，后面的命令都会执行。
> 而使用 "&&" 时，只有在前一个命令成功执行（即返回退出代码0）的情况下，才会执行后面的命令。

4．^ 转义字符
5．% 变量引导符
6．"" 界定符



### 重定向符 >、>>

`>`重定向符, 意思是传递并且覆盖，它所起的作用是将运行的结果传递到后面的范围（后边可以是文件,也可以是默
认的系统控制台，即命令提示符）。

例1
`echo hello120 > 1.txt`
新建立文件 1.txt，内容为”hello120” （注意：行尾有一空格,文件后有一空行）

例2
`(echo hello120)>1.txt `  
新建立文件 1.txt，内容为”hello120” （注意：行尾无空格,但文件后有一空行）






`>>`重定向符 将命令的输出结果**追加**到其后面的设备中去。这个符号的作用和>有点类似，但他们是有区别的 >>是
传递并在文件的末尾追加，而>是覆盖。用法同>一样的。

例1
`echo hello >>1.txt`
如果1.txt不存在，则新建立文件 1.txt，内容为”hello ”；如果1.txt存在，则把内容为”hello ”追加
到其文末尾。

例2
`echo hello> 1.txt`
`echo world>>1.txt`

这时候 1.txt内容如下:
`hello`
`world`





### 命令管道符 |

例1
`tasklist | find /i "qq.exe" && taskkill /f /im qq.exe`
将tasklist（进程列表）用传递符"|" 传递给find 命令，执行查找QQ的进程。

例2
`echo y|rd /s c:\abc`
通过管道命令|将echo y 的结果传给rd /s c:\abc 命令，从而达到自动输入y 的目的。




### 组合命令 &、&&、||

&、&&、|| 为组合命令，顾名思义，就是可以把多个命令组合起来当一个命令来执行。

&符号允许同时执行多条命令，当第一个命令执行失败了，也不影响后边的命令执行。这里 & 两边的命令是顺序执行的，从前往后执行。格式：第一条命令 & 第二条命令 [& 第三条命令...]

例1
`dir z:\ & dir y:\ & dir c:\`
以上命令会连续显示z,y,c 盘的内容，不理会该盘是否存在。

> &&符号允许同时执行多条命令，当碰到执行出错的命令后将不再执行后面的命令，如果一直没有出错则一直执行完所有命令。
> 格式：第一条命令 && 第二条命令 [&& 第三条命令...]

例2
`dir z:\ && dir y:\ && dir c:\`
这个命令和上面的类似，但区别是，前一个命令执行失败时，后边的命令就不会再执行了。


||符号允许同时执行多条命令，当一条命令执行失败后才执行第二条命令，当碰到执行正确的命令后将不执行后面
的命令,如果没有出现正确的命令则一直执行完所有命令。
格式：第一条命令 || 第二条命令 [|| 第三条命令...]




> **管道**命令的优先级高于**重定向**命令，重定向命令的优先级高于**组合命令**。



### 转义字符 ^

一般而言，^ 以转义字符的身份出现。

因为在cmd环境中，有些字符具备特殊功能，如 >,|,因而使用 ^
echo ^>、echo ^|



另外，此转义字符还可以用作**续行符号**

```bash
@echo off
echo 这是^
一个^
句子
```

> 为什么转义字符放在行尾可以起到续行符的作用呢？原因很简单，因为每行末尾还有一个看不见的符号，即回车符，转义字符位于行尾时就让回车符失效了，从而起到了续行的作用。








### 变量引导符%

1. 
当百分号成对出现，并且其间包含非特殊字符时，一般做变量引用处理，比如：%var%、%str%

例1
```
@echo off
set str=abc
echo 变量 str 的值是： %str%
pause


在屏幕上将显示这样的结果：
变量 str 的值是： abc
按任意键继续...
```

2. 
出现在 set /a 语句中时，表示两数相除取余数，也就是所谓的模运算，它在命令行窗口和批处理文件中的写法
略有差异：在命令行窗口中，只需要单个的%，在批处理文件中，需要连续两个百分号，写成%%。

例如：在命令行窗口中，运行 set /a num=4%2 ，则结果将显示0，因为4除以2的余数为0；
如果保存为批处理文件，则此语句将略有改变：

例2

```
@echo off
set /a num=4%%2
echo 4除以2的余数为 %num%
pause
```

3. 
如果要显示%本身时，需要在前面用%来转义。

例4
@echo off
echo 一个百分号：%%
echo 两个百分号：%%%%
echo 三个百分号：%%%%%%
pause





### 界定符 ""

在表示带有空格或特殊符号的路径时常要用""来将路径括起来。


# if 语句

    5种if语句的基本语法：
    1。判断两个字符串是否相等，if "字符串1"=="字符串2" command 语句;
    2。判断两个数值是否相等，if 数值1 equ 数值2 command 语句；
    3。判断判断驱动器，文件或文件夹是否存在，if exist filename command 语句;
    4。判断变量是否已经定义，if defined 变量 command 语句；
    5。判断上个命令的返回值，if errorlevel 数值 command 语句。



1。判断两个**字符串**是否相等，if"字符串1"=="字符串2" command 语句
   注意：在"字符串1"=="字符串2"中，是两个连续的"="

例1
@echo off
set /p var1=请输入第一个比较的字符：
set /p var2=请输入第二个比软的字符：
if "%var1%"=="%var2%" (echo 输入的两个字符相同) else echo 输入的两个字符不相同
pause

执行后会要求你输入两个字符串，然后批处理判断它俩是否相同。在判断字符串是否相等的时候，if是会区分大小写
的，请看，

例2
@echo off
if "a"=="A" (echo 输入的两个字符相同) else echo 输入的两个字符不相同
pause

执行后会显示：输入的两个字符不相同
如果我们不想让它区分大小写，则可以加上/i 参数。再看下面这个例子，

例3
@echo off
if /i "a"=="A" (echo 输入的两个字符相同) else echo 输入的两个字符不相同
pause

这次执行后会显示：输入的两个字符相同


2。判断两个**数值**是否相等，if 数值1 equ 数值2 command 语句
语句中的equ 表示相等的意思，判断两个数值之间的大小关系还有以下关系符号：

中文含义       关系符       英文解释
等于            equ          equal
大于            gtr          greater than
大于或等于      geq          greater than or equal
小于            lss           less than
小于或等于      leq          less than or equal
不等于          neq          no equal

我们还是来看个例子，

例4
@echo off
set /p var=请输入一个数字:
if %var% geq 10 (echo 此数大于或等于10) else echo 此数小于10
pause

注意哦，批处理中的大于号，小于号，等于号等等都不能用：“>”  “<”  “=” 这些符号，而要用像"gtr"这类
的。


☆注意：if 比较字符串与比较数字 之间的区别,它们的区别体现在引号""上面,请看下面的例子

【example 1】
@echo off
if "12" lss "4" (echo 12竟然小于4哦?) else echo 12当然不会小于4的！
pause

执行的结果是：12竟然小于4哦?


【example 2】
@echo off
if 12 lss 4 (echo 12竟然小于4哦?) else echo 12当然不会小于4的！
Pause

执行的结果是：12当然不会小于4的！

为什么【example 1】中加了双引号""会出错呢？

原因如下：
   如果要比较的两个元素加了双引号""，那么会被当成是字符的比较。两个元素作比较的流程是：先比较两个元素
的首位，如果首位相同，再比较第二位，如果第二位相同，再比较第三位。。。依此，在【example 1】if "12" lss
 "4" 语句中，实质是1与4的比较，1当然小于4，所以执行了后面的命令：echo 12竟然小于4哦?

☆建议：如果是字符串的比较就使用双引号"" 是数字的比较就不用双引号了！






3。判断判断**驱动器**，文件或文件夹是否存在，if exist filename command 语句
我们来瞧瞧这个判断驱动器，文件或者文件夹是否存在的语句，这里if exist filename 是表示：存在 filename的
意思。

例5
@echo off
if exist "e:" (echo e盘存在) else echo e盘不存在
**pause>nul**

这里是判断e: 是否存在！

例6
@echo off
if exist d:\123.bat (echo 123.bat文件存在！) else echo 123.bat文件不存在！
pause

这个例子是用来判断123.bat文件是否存在的，但并不严谨！如果123.bat是一个文件夹而不是一个文件时，上面的判
断就不行了！那么如何判断指定的文件123.txt是否存在？请看：

@echo off
dir /a-d d:\123.bat >nul 2>nul
if %errorlevel%==0 (echo 123.bat文件存在！) else echo 123.bat文件不存在！
Pause

先用dir的/a-d参数去除123.bat的目录属性，指定说明要搜索的123.bat**是文件而不是文件夹**，并把结果（包括正确
和错误）屏蔽(>nul 2>nul)，如果dir找到了文件123.bat，那么其errorlevel值(dir命令的退出编码)会被设为0，否
则为1则是没有此文件。当然也可以用||和&&来判断。关于为什么要用if%errorlevel%==0 而不用if errorlevel 0呢？在后面if errorlevel中会有说明！

例7
@echo off
if exist test\ (echo test 是文件夹) else echo test 是文件
pause

这里是判断文件夹是否存在。对于文件夹存在的判断，我们**不**可以用以下两种写法：

第①种
@echo off
if exist test\. (echo test 是文件夹) else echo test 是文件
pause


第②种
@echo off
if exist test\nul (echo test 是文件夹) else echo test 是文件
pause





4。判断变量是否已经定义，if defined 变量 command 语句这是一个判断变量是否已被定义的语句，我们还是先看例子，

例8
@echo off
if defined a (echo 变量 a 已定义) else (echo 变量 a 没有被定义)
pause
执行后显示：变量 a 没有被定义

例9
@echo off
set a=
if defined a (echo 变量 a 已定义) else (echo 变量 a 没有被定义)
pause
执行后显示：变量 a 没有被定义

注意：set a=这后面是没有空格的。

例10
@echo off
set a=10
if defined a (echo 变量 a 已定义) else (echo 变量 a 没有被定义)
pause
执行后显示：变量 a 已定义

   看了上面三个例子，你应该发现点什么了吧？现在我们应该知道了：当变量不存在或是值为空时，变量则为未定义。
   当我们用if defined 变量 command 语句判断变量是否被定义时，请注意 变量 为不使用引导符号%的变量名，不
能用写为%变量%，否则出错。

大家来试一下下面的这两个例子，

例11
@echo off
set var1=5
if defined var1 (echo 变量var1已定义) else (echo 变量var1没有被定义)
set /p var2=请输入一个数字:
if defined %var2% (echo 变量var2已定义) else (echo 变量var2没有被定义)
pause

例12
@echo off
set var1=5
if defined var1 (echo 变量var1已定义) else (echo 变量var1没有被定义)
set /p var2=请输入一个数字:
if defined var2 (echo 变量var2已定义) else (echo 变量var2没有被定义)
pause

注意例11和例12哦：一个是%var2%而另一个是var2




5。判断上个命令的反回值，if errorlevel 数值 command 语句
这个语句是用于判断上一个命令执行的返回值errorlevel，我们还是先来看看例子，

例13
@echo off
net user
if %errorlevel% == 0 (echo net user 命令执行成功) else (echo net user 命令执行失败)
Pause

注意：%errorlevel%  这是个系统变量,所以用两个%括起来，这里的==为两个连续的=
也许有些朋友说我上面的例子是不是写错了？按照语法应该写成“if errorlevel 0”才对的呀。
如果你是这样用的话，那你就错了，还真不信呀？OK，我们来设计个实验看看

例14
@echo off
set /p input=请输入任意一条命令：
if errorlevel 0 (echo %input% 命令执行成功) else (echo %input% 命令执行失败)
pause
用这种语法,不管你前面的命令，是否执行成功,它都会认为命令成功了。何解？


if errorlevel  语句的特点：
当使用  if errorlevel 0 cmmand 句式时，它的含义是：如果返回的**错误码值大于或等于0 **的时候，将执行cmmand
操作；
当使用  if %errorlevel%==0 cmmand 句式时，它含义是：如果返回的**错误码值等于0 的时候**，将执行cmmand操作。

一般上一条命令的执行结果返回的值只有两个，"成功"用0 表示 "失败"用 1 表示，实际上，errorlevel 返回值可
以在0~255 之间，
例如xcopy 默认的errorlevel 值就有5 个，分别表示5 种执行状态：

0复制文件成功
1 未找到复制文件
2 用户通过CTRL C 终止了xcopy操作
4 出现了初始化错误 
5 出现了磁盘写入错误
------------------------------------------------------------------------------------------------------


   对于if 条件语句，常用的也就上面那5条吧，下面我们来看一下它的两种格式：

if条件语句的完整格式是：if 条件表达式 (语句1) else (语句2)
它的含义是：如果 条件表达式 成立，就执行 语句1，否则，将执行 语句2。?  else后的 语句2 也可以不用括号括
起。

if条件语句的简单格式是：if 条件表达式 (语句)
它的含义是：如果 条件表达式 成立，将执行 语句，否则，什么都不做。条件表达式后的 语句 也可以不用括号括
起。

例15
@echo off
if exist "d:\123.txt" (del "d:\123.txt")
pause

你也可以写成下面这样

例16

@echo off
if exist "d:\123.txt" del "d:\123.txt"
pause









补充1：if not 语句
对于if 语句前面的5种基本语法，都可以加上not参数，如
1。if not "字符串1"=="字符串2" command 语句;
2。if not数值1 equ 数值2 command 语句；
3。If not exist filename command 语句;
4。if not defined 变量 command 语句；
5。if not errorlevel 数值 command 语句。

   在if 条件表达式 (语句) 语句，如没有not参数，默认是判断条件成立时，执行语句;如有not参数，判断条件失
败时，执行语句。

我们看一下下面的例子：

例17
@echo off
if exist "c:\" (echo  C盘存在) else echo  C盘不存在
pause
执行后显示：C盘存在

例18
@echo off
if not exist "c:\" (echo  C盘存在) else echo  C盘不存在
pause
执行后显示：C盘不存在

不会吧，例18中执行后显示：C盘不存在，这简值是在说瞎话了，那到底是什么回事呢？

if not exist "c:\" (echo  C盘存在) else echo  C盘不存在
此语句中文意思：如果不存在C:\ 就执行显示C盘存在；否则<指的是存在C:\>就执行显示C盘不存在。
注：if exist 意为如果存在,那么if not exist就是如果不存在

你的C盘当然是实实在在存在的啦，根椐上面的中文分得知，那执行例18后就显示为：C盘不存在  通过分析例18,你
应该明白if语句中not参数是怎么用了吧！
------------------------------------------------------------------------------------------------------


补充2：IF语句嵌套的写法，我们先来看看

例1
@echo off
set a=55
if %a% geq 50 (
   if %a% geq 80 (
       if %a% geq 100 (
           echo a大于等于100
       ) else echo a小于100
   ) else echo a小于80
) else echo a小于50
pause

这是一种比较规范的写法，那这种写法是怎样写的呢？那我们一步步的来：

第一步：
if  %a% geq 50 ( ) else echo a小于50

第二步：在第一步geq 50 后面的括号（）里按两下回车，主要是空两行出来。
if  %a% geq 50 (
   if  %a% geq 80 ( )else echo小于80
) else echo a小于50

第三步：在第二步geq 80 后面的括号（）里按两下回车，主要是空两行出来。
if  %a% geq 50 (
   if  %a% geq 80 (
      if  %a% geq 100 ( ) else echo a小于100
   )else echo小于80
) else echo a小于50

第四步：在第三步geq 100 后面的括号（）里按两下回车，主要是空两行出来。
if  %a% geq 50 (
   if  %a% geq 80 (
      if  %a% geq 100 (
         echo a大于等于100
      ) else echo a小于100
   )else echo小于80
) else echo a小于50


上面的代码中，if  %a% 和 )else 要适当的缩进，以达到写书清晰！







# set命令详解
2022-09-05 15:58 更新
《批处理入门手册》
三.批处理高级语句篇
-----------------------------------------------------------------------------------------------------
3.3 学习set命令详解

学习要点：
1．用set 命令设置自定义变量
2．用set 命令进行字符串处理
3．用set 命令进行简单计算
-----------------------------------------------------------------------------------------------------


一、用set 命令设置自定义变量
格式：set [variable=[string]]
variable 指定环境变量名。 string 指定要指派给变量variable的一系列字符串。
-----------------------------------------------------------------------------------------------------

例1，
@echo off
set var=abcd
echo %var%
pause

在例1中，set var=abcd 是批处理设置变量的方法。Var是变量名，而abcd则是变量名var的值。echo %var% ，在批
处理中我们要引用这个变量就把var 变量名用两个%(百分号)扩起来,如%var%

注意：以下用法将清除变量variable 的值，使其变成未定义状态。

**set variable=**
**上面等号后面无任何符号，如果写成SET variable=""，此时变量值并不为空，而是等于两个引号，即"" **

例2,
@echo off
set var=
set var1=""
if defined var (echo var的值为%var%) else echo var没有赋值
if defined var1 (echo var1的值为%var1%) else echo var1没有赋值
pause

set还可以提供一个交互界面,让用户自己输入变量的值,然后我们在来根据这个值来做相应操作,现在我就来说说set
的这种语法,只需要加一个"/P"参数就可以了!

例3,
@echo off
set /p var=请输入变量的值:
echo  您输入了%var%
pause

执行上面的批处理时，系统会提示你：“请输入变量的值:”还是自己动手尝试一下吧，呵呵！
-----------------------------------------------------------------------------------------------------

 


二、用set 命令进行字符串处理

1、字符串替换
格式：%PATH:str1=str2%  先写两个%% 之后%path% 之后:A=B
意思就是：将字符串变量%PATH%中的str1 替换为str2
-----------------------------------------------------------------------------------------------------

例4，
@echo off
set a= bbs. bathome. cn
echo  替换前的值: "%a%"
set var=%a: =%  
echo  替换后的值: "%var%"
pause

执行后显示：
替换前的值: " bbs. bathome. cn"
替换后的值: "bbs.bathome.cn"

对比一下,我们发现它把变量%a%的**空格**给替换掉了, 而%PATH:str1=str2%这个操作就是把变量%PATH%的里的str1 全
部用str2 替换。   去空格操作

例5,
@echo off
set a= bbs.bathome.cn
echo  替换前的值: "%a%"
set var=%a:.=小情%
echo  替换后的值: "%var%"
pause

执行后显示：
替换前的值: " bbs.bathome.cn"
替换后的值: " bbs小情bathome小情cn"

这里是把变量%a%里面的"."全部替换为"小情"，大家多多动手练习就会了，这个没有什么难的，呵呵~~
-----------------------------------------------------------------------------------------------------


2、字符串截取
格式：%a:~[m[,n]]%
方括号表示可选，%为变量标识符，a为变量名，不可少，冒号用于分隔变量名和说明部分，符号～可以简单理解为“
偏移”即可，m 为偏移量（缺省为0），n 为截取长度（缺省为全部）
-----------------------------------------------------------------------------------------------------

例6,
@echo off
set a=bbs.bathome.cn
set var=%a:~1,3%
echo %var%
pause

执行显示为：bs.
我们来分析一下set var=%a:~1,3% ，这里%a:~1,3%就是说变量 a  偏移量为1,要截取它的3位字符长度。合起来就是
把变量a 的值从第2 位（偏移量 1）开始,截取3 个字符赋予变量var

例7,
@echo off
set a=bbs.bathome.cn
set var=%a:~1,-3%
echo %var%
pause

执行显示：bs.bathome
这里就是把变量a 第2位起到**倒数第3位**前的值赋予var

例8,
@echo off
set a=bbs.bathome.cn
set var=%a:~-3%
echo %var%
pause

执行显示：.cn
这里就是就是把变量a **倒数 3 位**的值赋予变量var

例9,
@echo off
set a=bbs.bathome.cn
set var=%a:~-3,2%
echo %var%
pause

执行显示：.c

这里就是就是把变量a 倒数 3 位的值的前2位赋予变量var

例10,
@echo off
set a=bbs.bathome.cn
set var=%a:~3%
echo %var%
pause

执行显示：.bathome.cn
这里就是把变量a 从第3 位开始后面全部的值给变量var

上面这几个例子要好好的记住哦，而且不要记错了！


我们还是总结一下吧：
a=bbs.bathome.cn

%a:~1,3%    -------- “bs.”        变量a 偏离1位，截取3位字符。

%a:~1,-3%   -------- “bs.bathome” 变量a 偏离1位，截取倒数第3位前的字符。

%a:~-3%    -------- “.cn”        变量a 偏离-3位，截取倒数的3位字符。

%a:~-3,2%   -------- “.c”        变量a 偏离-3位，截取倒数后3位的前两2位字符

%a:~3%     -------- “.bathome.cn”变量a 偏离3位，截取完后面的字符。

%a:~,3%     -------- “bbs”      变量a 偏离0位，截取3位字符。
-----------------------------------------------------------------------------------------------------

我们来道综合题：输入任意字符串，求字符串的长度

例11，
@echo off
set /p str=请输入任意长度的字符串:
echo  你输入了字符串:"%str%"
call :stringlenth "%str%" num
echo  字符串长度为：%num%
pause
:stringLenth
::字符串长度计算子程序
set thestring=%~1
::参数%1 为字符串"%str%"，%~1则去掉"%str%"的双引号。
if not defined theString goto :eof
set Return=0
:stringLenth_continue
set /a Return =%Return%+1
set thestring=%thestring:~0,-1%
::偏移量为1,截取倒数第1位前的所有字符。
if defined thestring goto stringLenth_continue
if not "%2"=="" set %2=%Return%
::参数%2为返回变量num的名称，不能含空格或特殊字符。
goto :eof
-----------------------------------------------------------------------------------------------------


最后，提供一下batmat写的一个例子帮助大家对截取字符的理解吧：

例子：
@echo off&color a&mode con cols=90 lines=12
title 截取字符练习工具 by bat-zw19750516
set var=1234567890一二三四五六七八九零
cls&echo.&echo  原始图案为：%var%
echo.&echo  注意前面有10个阿拉伯数字后面有10个语文数字,等下截取后记得看下。
echo.&echo  命令形式为：%%var:~m,n%%,其中的m为第一个参数可负可正可为零，n为第二个参数可负可正不为零。
echo.&echo  为了方便重复练习没有设置退出,要退出请直接关闭窗口。
echo.&echo  请按任意键开始练习。&pause>nul

:begin
set a=&set b=&set "c="
cls&echo.&set /p a= 请输入第一个参数（应为小于20的整数）：
echo %a:-=%|findstr "[^0-9]"&&goto wrong
if "%a%" equ "" goto wrong
if %a% geq 20 goto wrong
cls&echo.&set /p b= 请输入第二个参数（应为大于-20并不为零的整数）：
echo %b:-=%|findstr "[^0-9]"&&goto wrong
if "%b%" equ "" goto wrong
if %b% equ 0 goto wrong
if %b% lss -20 goto wrong
set /a c=20-a b
if %a% lss 0 if %b% lss 0 if %a% geq %b% goto wrong
if %a% gtr 0 if %b% lss 0 if %c% leq 0 goto wrong
cls&echo.&echo  原始图案为： %var%
echo.&echo  你输入的截取命令是： %%var:~%a%,%b%%%
echo.&call,echo  截取到的图案是 ：%%var:~%a%,%b%%%
echo.&echo  请按任意键再次练习。&pause>nul&goto begin

:wrong
cls&echo.&echo  输入不合要求，请正确输入。&ping /n 2 127.1 >nul&goto begin
-----------------------------------------------------------------------------------------------------

 


三、用set 命令进行简单计算
格式：set /a expression
/a 命令行开关指定等号右边的字符串为被评估的数字表达式。该表达式评估器很简单并以递减的优先权顺序支持下
列操作:

  ( )             分组
  !  ~  -        一元运算符
  *  /  %       算数运算符
    -           算数运算符
    <<   >>       二进制逻辑移位
    &             二进制按位“与”
    ^              二进制按位“异”
    |              二进制按位“或”
    =   *=   /=   %=  =  -=           算数赋值
    &=   ^=   |=   <<=    >>=          二进制运算赋值
    ,                                   表达式分隔符

set 的/a 参数就是让set 可以支持数学符号进行加减等一些数学运算, 注意：一般的运算常为十进制运算.
-----------------------------------------------------------------------------------------------------

例12,
@echo off
set /p input=请输入计算表达式：
set /a var=%input%
echo  计算结果：%input%=%var%
pause

注意：DOS 计算只能进行整数运算，精确到整数，它是不支持浮点计算的！

请输入计算表达式：1 9 20 30-10
计算结果：1 9 20 30-10=50
请按任意键继续. . .

请输入计算表达式：10/3        #除法只能精确到整数
计算结果：10/3=3
请按任意键继续. . .

请输入计算表达式：-100 62       #负数
计算结果：-100 62=-38
请按任意键继续. . .

请输入计算表达式：100%3         ＃求余数
计算结果：100%3=1
请按任意键继续. . .

注意：以上的求余数运算符%在批处理程序中必须写成%%

请输入计算表达式：(25 75)*2/(15 5)     #括号
计算结果：(25 75)*2/(15 5)=10
请按任意键继续. . .

请输入计算表达式：1234567890*9876543210       #范围
无效数字。数字精确度限为  32  位。
计算结果：1234567890*9876543210=
请按任意键继续. . .

注意：上面的计算过程显示，DOS 计算只能精确到32 位，这个 32 位是指二进制32 位，其中最高位为符号位（0 为
正，1 为负），低位31 位为数值。31 个 1 换成十进制为2147483647，所以DOS 计算的有效值范围是-2147483648
至2147483647，超出该数值范围时计算出错.请看下面的计算过程：

请输入计算表达式：2147483647-1       #最大值减 1，值有效
计算结果：2147483647-1=2147483646
请按任意键继续. . .

请输入计算表达式：2147483647 1        #最大值加 1，出错，结果为最小值
计算结果：2147483647 1=-2147483648
请按任意键继续. . .

请输入计算表达式：-2147483648-1      #最小值减 1，出错，结果为最大值
计算结果：-2147483648-1=2147483647
请按任意键继续. . .

我们来看一看下面的这个例子

例13,
@echo off
set /a a=1+1,b=2+1,c=3+1
echo  计算结果：%a%,%b%,%c%
pause

执行显示：计算结果：2 3 4

发现其他数学运算也有效果!,这就是表达式分隔符"逗"号的作用!
有时候我们需要直接在原变量进行加减操作就可以用这种语法，
set /a var =1对应原始语法就是set /a var = %var% 1
set /a var*=2对应原始语法就是set /a var = %var%*2

另外还有一些用逻辑或取余操作符,这些符号,按照上面的使用方法会报错。比如我们在CMD 里输入set /a var=1 & 1
 "与运算",它并不会显示为 1,而是报错,！
为什么?对于这样的"逻辑或取余操作符",我们需要把他们用双引号引起来,也可以用转义字符^，看例子set /a var=
1 "&" 1  这样结果就显示出来了。

其他逻辑或取余操作符用法 ：
set /a var= 1 " " 1  异运算
set /a var= 1 "%" 1 取模运算
set /a var= 3 "<<" 2  左移位运算，  3 的二进制为11，左移2 位为 1100，换成十进制就是12，自行验证
set /a var= 4 ">>" 2 右移位运算，4 的二进制为100，右移动2 位为 1，结果为1

注意：凡是按位计算均需换算成二进制，然后再计算。

思考题：求2 的n 次方

参考答案：
例14,
@echo off
set /p n=请输入2 的几次方:
set /a num=1^<^<n
echo %num%
pause

想一下这个到底是怎么运算的？呵呵，这就留给大家思考了！






原文地址  整理到了for循环章节
https://www.w3cschool.cn/pclrmsc/fyqro4.html




cd e: 报错 cd /d e:\正常
e: 可以正常切换，同样，切换回用  c：
可以回到c的原来文件夹




## 其他


### windows端口占用

1. 首先用
2. `netstat -ano | find "8080"` or 
3. `netstat -ano | findstr 8080`
4. 如何杀死进程呢 `taskkill /f /pid 3704`

> /f force 强制



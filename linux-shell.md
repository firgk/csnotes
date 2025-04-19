# shell
## 变量
`your_name="runoob"`

`echo ${your_name}`

```sh
for file in `ls /etc`
或
for file in $(ls /etc)
```

变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界,推荐给所有变量加上花括号，这是个好的编程习惯。

使用 unset 命令可以删除变量
`unset variable`

```bash
for ff in week?? # week 以开头的文件
do
echo $ff
```

数组
my_array=(1 2 3 4 5)

单引号字符串的限制：
单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

双引号的优点：
双引号里可以有变量
双引号里可以出现转义字符

拼接字符串
your_name="runoob"
### 使用双引号拼接
greeting="hello, "$your_name" !"
greeting_1="hello, ${your_name} !"
echo $greeting  $greeting_1



获取字符串长度
实例
string="abcd"
echo ${#string}   # 输出 4




提取子字符串
以下实例从字符串第 2 个字符开始截取 4 个字符：
实例
string="runoob is a great site"
echo ${string:1:4} # 输出 unoo




## 注释
`#`


## shell传递参数

echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第二个参数为：$2";
echo "第三个参数为：$3";


`$ ./test.sh 1 2 3`
Shell 传递参数实例！
执行的文件名：./test.sh
第一个参数为：1
第二个参数为：2
第三个参数为：3



echo "参数个数为：$#";

参数个数为：3
传递的参数作为一个字符串显示：1 2 3


$* 与 $@ 区别：

相同点：都是引用所有参数。
不同点：只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，则 " * " 等价于 "1 2 3"（传递了一个参数），而 "@" 等价于 "1" "2" "3"（传递了三个参数）。





## 数组
bash支持一维数组（不支持多维数组）

array_name=(value0 value1 value2 value3)

${数组名[下标]}

使用 @ 符号可以获取数组中的所有元素，例如：

echo ${array_name[@]}


my_array=(A B "C" D)

echo "第一个元素为: ${my_array[0]}"
echo "第二个元素为: ${my_array[1]}"
echo "第三个元素为: ${my_array[2]}"
echo "第四个元素为: ${my_array[3]}"

关联数组
`declare -A site=(["google"]="www.google.com" ["runoob"]="www.runoob.com" ["taobao"]="www.taobao.com")`

先声明后设置
```bash
declare -A site
site["google"]="www.google.com"
site["runoob"]="www.runoob.com"
site["taobao"]="www.taobao.com"
```

访问
`echo ${site["runoob"]}`
数组的元素为: www.google.com www.runoob.com www.taobao.com
`echo "数组的键为: ${!site[*]}"`
数组的键为: google runoob taobao

### 运算符

简单的计算
```bash

a=10
b=20

val=`expr $a + $b`
echo "a + b : $val"

val=`expr $a - $b`
echo "a - b : $val"

val=`expr $a \* $b`
echo "a * b : $val"

val=`expr $b / $a`
echo "b / a : $val"

val=`expr $b % $a`
echo "b % a : $val"

if [ $a == $b ]
then
   echo "a 等于 b"
fi
if [ $a != $b ]
then
   echo "a 不等于 b"
fi


```


运算符	说明
-eq	 equal
-ne	 no equal
-gt	 getter than 
-lt	 letter than
-ge	 getter than 
-le	 less than


ex
```bash
if [ $a -eq $b ]
then
   echo " a 等于 b"
```
## echo
### 显示转义字符
echo "\"It is a test\""
结果将是:
"It is a test"

### 换行
echo -e "OK! \n" # -e 开启转义
echo "It is a test"

### 不换行
echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "It is a test"

### 定向到文件
echo "It is a test" > myfile

### 原样输出
单引号
echo '$name\"'

### 显示命令
```sh
echo `date`
```
## printf

printf "Hello, Shell\n"
类似c语言

### 格式化输出
 
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.1234
printf "%-10s %-8s %-4.2f\n" 杨过 男 48.6543
printf "%-10s %-8s %-4.2f\n" 郭芙 女 47.9876

执行脚本，输出结果如下所示：

姓名     性别   体重kg
郭靖     男      66.12
杨过     男      48.65
郭芙     女      47.99
## test

Shell中的 test 命令用于检查某个条件是否成立，它可以进行数值、字符和文件三个方面的测试。

### 数值
```sh
num1=100
num2=100
if test $[num1] -eq $[num2]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi
```

### 字符串
```sh
num1="ru1noob"
num2="runoob"
if test $num1 = $num2
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi
```


参数	说明
=	等于则为真
!=	不相等则为真
-z 字符串	字符串的长度为零则为真
-n 字符串	字符串的长度不为零则为真




### 文件
文件测试
参数	说明
-e 文件名	如果文件存在则为真
-r 文件名	如果文件存在且可读则为真
-w 文件名	如果文件存在且可写则为真
-x 文件名	如果文件存在且可执行则为真
-s 文件名	如果文件存在且至少有一个字符则为真
-d 文件名	如果文件存在且为目录则为真
-f 文件名	如果文件存在且为普通文件则为真
-c 文件名	如果文件存在且为字符型特殊文件则为真
-b 文件名	如果文件存在且为块特殊文件则为真


```sh

cd /bin
if test -e ./bash
then
    echo '文件已存在!'
else
    echo '文件不存在!'
fi
```


## 流程控制
```sh
a=10
b=20
if [ $a == $b ]
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif [ $a -lt $b ]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi

```

## 函数
```sh
demoFun(){
    echo "这是我的第一个 shell 函数!"
}
echo "-----函数开始执行-----"
demoFun
echo "-----函数执行完毕-----"


funWithReturn(){
    echo "这个函数会对输入的两个数字进行相加运算..."
    echo "输入第一个数字: "
    read aNum
    echo "输入第二个数字: "
    read anotherNum
    echo "两个数字分别为 $aNum 和 $anotherNum !"
    return $(($aNum+$anotherNum))
}
funWithReturn
echo "输入的两个数字之和为 $? !"




函数参数

funWithParam(){
    echo "第一个参数为 $1 !"
    echo "第二个参数为 $2 !"
    echo "第十个参数为 $10 !"
    echo "第十个参数为 ${10} !"
    echo "第十一个参数为 ${11} !"
    echo "参数总数有 $# 个!"
    echo "作为一个字符串输出所有参数 $* !"
}
funWithParam 1 2 3 4 5 6 7 8 9 34 73


第一个参数为 1 !
第二个参数为 2 !
第十个参数为 10 !
第十个参数为 34 !
第十一个参数为 73 !
参数总数有 11 个!
作为一个字符串输出所有参数 1 2 3 4 5 6 7 8 9 34 73 !





```


## 输入输出重定向


command1 > file1



command1 < file1



# bug

等号两侧避免使用空格：

`正确的赋值
variable_name=value
有可能会导致错误
variable_name = value`



# 附加文档



###  运行 Shell 脚本有两种方法：
1、作为可执行程序

将上面的代码保存为 test.sh，并 cd 到相应目录：

`chmod +x ./test.sh  #使脚本具有执行权限
./test.sh  #执行脚本`
注意，一定要写成 ./test.sh，而不是 test.sh，运行其它二进制的程序也一样，直接写 test.sh，linux 系统会去 PATH 里寻找有没有叫 test.sh 的，而只有 /bin, /sbin, /usr/bin，/usr/sbin 等在 PATH 里，你的当前目录通常不在 PATH 里，所以写成 test.sh 是会找不到命令的，要用 ./test.sh 告诉系统说，就在当前目录找。

2、作为解释器参数

这种运行方式是，直接运行解释器，其参数就是 shell 脚本的文件名，如：

`/bin/sh test.sh
/bin/php test.php`
这种方式运行的脚本，不需要在第一行指定解释器信息，写了也没用。

### 只读
`myUrl="https://www.google.com"
readonly myUrl`

### 获取数组的长度
1.  取得数组元素的个数
length=${#array_name[@]}
2.  或者
length=${#array_name[*]}
3.  取得数组单个元素的长度
length=${#array_name[n]}







灯笼哥哥


.sh ls cal date
sh txt.sh
a=10
echo $a
echo "HELLO WORLD" $a
echo HELLO WORLD 不推荐
c=`expr $a + $b` 空格是强制的
echo $c
c=`expr $a \* $b`

+ - \* /
if[...]
then
else
fi

if[$a > $b]
then
echo $a
else
echo $b
fi

> -gt greater than
< -lt less than
>= -ge greater equal
<= -le less equal
= -eq equal
!= -ne not equal

for x in 1 2 3 4 5 6 7 8 9 10
do
done

while[$ -le 10]
do
echo $x
x=`expr $x + 1`
done

read 读入
echo "please enter a:"
read a
echo "please enter b:"
read b
c=`expr $a + $b `
echo $c

password = "hello123"
echo "please enter a password"
read userPassword
if[ $password -eq $userPassword]
then
echo "pass"
else
echo "wrong"
fi

 

## 字符串拼接

$str1 = "HELLO"
$str2 = "WORLD"
$str3 = "$str1 $str2"
if[-z $str1] 长度为0的字符串 -n 不为零 -z 为0
then echo "str1 is empty"
else echo "str1 is not empty"
fi

含str的用bash 不用sh
sh text.sh bash text.sh

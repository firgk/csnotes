


find / -name
cat 

管道符
管道符是linux系统上的特殊符号,用来将前一个命令的输出作为后一个命令的输入

重定向符
是linux或windows上的一个特殊符号,可以定向输入流或者输出流

在linux终端中,如何查找一个文件中的特定内容
可以用grep命令,`grep "hello" example.txt` 在grep后加入`-i`忽略大小写. 并且支持正则表达式

软链接 硬连接
linux中的两种不同的文件连接方式,
软链接可以用于目录或者文件,硬链接只可以用在文件上.
命令分别是 ln -s 和 ln
软连接相当于windows中的快捷方式, 源文件删除后软连接失效
硬链接相当于在底层对文件又进行了引用, 删除源文件后,仍然可以访问到目的文件

netstat -anp | grep 8080

ps -ef | grep 8080

杀死进程
`kill [-9] 进程ID`

tar -zxvf
tar -zcvf


```
mkdir -p 
rmdir -p 递归删除目录
rm -r 递归删除文件 也可以删除目录
chmod [-R] xyz dirname/filename
r : 4、w : 2、x : 1。
```






## 获得linux

1. 获得linux
虚拟机安装linux
购买云服务器安装linux,并且得到相应的连接方式
wsllinux(功能不全)
实体机安装linux(浪费性能)
2. 连接linux
ssh连接linux
图形化连接linux
	给linux安装图形化界面
	用对应的图形化软件进行连接
虚拟机中直接操作linux(有些卡顿,但是命令行绰绰有余)
3. 传输文件
某些ssh支持拖拽传输文件
命令传输




## 基础命令


### 开关机命令

```sh
# 正确的关机流程为：sync > shutdown > reboot > halt
sync 将数据由内存同步到硬盘中。
shutdown 关机指令，你可以man shutdown 来看一下帮助文档。例如你可以运行如下命令关机：
shutdown –h 10 ‘This server will shutdown after 10 mins’ # 这个命令告诉大家，计算机将在10分钟后关机，并且会显示在登陆用户的当前屏幕中。
poweroff 立马关机
shutdown –h now 立马关机
shutdown –h 20:25 系统会在今天20:25关机
shutdown –h +10 十分钟后关机
shutdown –r now 系统立马重启
shutdown –r +10 系统十分钟后重启
reboot 就是重启，等同于 shutdown –r now
halt 关闭系统，等同于shutdown –h now 和 poweroff
init 0 关机 init 6 重启
```

### 文件操作命令

```sh 
1.
[] 内为可选的参数
ls [-a -l -h] [路径]
ex 
ls -a -l
ls -al
ls- la

-h表示易于阅读的模式 对单位(kb,gb,tb进行优化)

2.
cd
后面不跟路径则切换到 用户的HOME目录下
root用户是root
其他用户是home/admin

3.pwd查看当前目录

4.路径
. 当前目录
..上一级目录
~ HOME目录

5.创建文件夹
mkdir [-p] linux路径
一次性创建多级目录 -p 选项 理解为 -r(递归进行)
如果没有修改权限,则只能在HOME下进行创建,而无法在其他目录下进行创建(权限限制)

6.touch创建文件 cat 查看文件内容 more
touch linux路径

cat linux路径
more类似cat,但是支持翻页

7.文件操作 cp mv rm
cp [-r] 参数1 参数2 (-r用于处理文件夹)
mv 参数1 参数2
rm [-r -f] 参数1 参数2 ...参数n  (-r用于处理文件夹, -f(force)不弹出确认信息)
ex
(删库跑路命令,不要执行)
rm -rf/
rm -rf/*

通配符 *
•test*，表示匹配任何以test开头的内容
•*test，表示匹配任何以test结尾的内容
•*test*，表示匹配任何包含test的内容

8.查找命令 which find
which 查找可执行文件的位置

find 按照文件名查找文件
find 起始路径 -name "被查找文件名"
ex: find / -name "test"  支持通配符进行快速查找
find 起始路径 -size +|-n [KMG]
ex:
•查找小于10KB的文件： find / -size -10k
•查找大于100MB的文件：find / -size +100M
•查找大于1GB的文件：find / -size +1G

9. grep wc 和 | (管道符)
grep [-n] 关键字 关键路径
建议使用””将关键字包围起来
关键路径 可以是文件路径,也可以是内容输入端口

wc统计文件的 行数 单词数量 等等

wc [-c -m -l -w] 文件路径(可作为内容输入端口)
c bytes
m 字符数量
l line行数
w 统计单词数量

不带参数
行数 单词数 字节数 

10. 补充管道符
ls find cat 均可作为管道符

11. echo tail 和 重定向符
echo `hello world`
echo pwd
echo `pwd` 会作为命令执行

tail [-f -num] linux文件路径
•参数，Linux路径，表示被跟踪的文件路径
•选项，-f，表示持续跟踪
•选项, -num，表示，查看尾部多少行，不填默认10行


重定向符：>和>>
>，将左侧命令的结果，覆盖写入到符号右侧指定的文件中
>>，将左侧命令的结果，追加写入到符号右侧指定的文件中


12. 小结
上述命令可能不全面
所有的命令都支持 --help选项 查看帮助文档

man (manual(手册))
man ls 查看ls命令
man cd 查看cd命令

输出帮助文档 man ls > ls-man.txt 之后翻译

```



### 用户和权限

•普通用户的权限，一般在其HOME目录内是不受限的
•一旦出了HOME目录，大多数地方，普通用户仅有只读和执行权限，无修改权限


`su [-] 用户名`
•- 符号是可选的，表示是否在切换用户后加载环境变量（后续讲解），建议带上
•参数：用户名，表示要切换的用户，用户名也可以省略，省略表示切换到root
•切换用户后，可以通过exit命令退回上一个用户，也可以使用快捷键：ctrl + d

•使用普通用户，切换到其它用户需要输入密码
•使用root用户切换到其它用户，无需密码，可以直接切换



#### sudo

我们可以使用sudo命令，为普通的命令授权，临时以root身份执行
但是并不是所有的用户，都有权利使用sudo，我们需要为普通用户配置sudo认证
	如何添加sudo认证?
	•切换到root用户，执行visudo命令，会自动通过vi编辑器打开：/etc/sudoers
	•在文件的最后添加： ctos ALL=(ALL) NOPASSWD: ALL (ctos是你想配置的账户)
	•其中最后的NOPASSWD:ALL 表示使用sudo命令，无需输入密码




#### 用户和用户组

groupadd 用户组的名字
groupdel 用户组的名字




```sh 
以下命令需root用户执行

•创建用户
useradd [-g -d] 用户名
•选项：-g指定用户的组，不指定-g，会创建同名组并自动加入，指定-g需要组已经存在，如已存在同名组，必须使用-g
•选项：-d指定用户HOME路径，不指定，HOME目录默认在：/home/用户名

•删除用户
userdel [-r] 用户名
•选项：-r，删除用户的HOME目录，不使用-r，删除用户时，HOME目录保留

•查看用户所属组
id [用户名]
•参数：用户名，被查看的用户，如果不提供则查看自身
•修改用户所属组
usermod -aG 用户组 用户名，将指定用户加入指定用户组
getent !源文件-liunx


```




#### linux权限管控
rwx 可读可写可执行 
对应数字 421

chmod 更改权限命令
	只有文件、文件夹的所属用户或root用户可以修改


`chmod [-R] 权限 文件或者文件夹`
•选项：-R，对文件夹内的全部内容应用同样的操作

ex
`chmod u=rwx,g=rx,o=x hello.txt` user group others



#### chown
chown修改所属用户、用户组
`chown [-R] [用户][:][用户组] 文件或者文件夹`

ex
```
•chown root hello.txt，将hello.txt所属用户修改为root
•chown :root hello.txt，将hello.txt所属用户组修改为root
•chown root:itheima hello.txt，将hello.txt所属用户修改为root，用户组修改为itheima
•chown -R root test，将文件夹test的所属用户修改为root并对文件夹内全部内容应用同样规则
```



### 实用操作
`ctrl + c` 终止程序 
`ctrl + d` 退出登录或者是退出python等等

`history`  查看历史命令
`!py` 自动执行上一次匹配前缀 `python`
`ctrl + r` 输入内容去匹配历史命令
	键盘左右键，可以得到此命令
	回车键可以直接执行


`head 查看文件头 head file.txt -n 5查看file的前5行`
`diff查看文件差距`



命令光标移动快捷键
```
•ctrl + a，跳到命令开头
•ctrl + e，跳到命令结尾
•ctrl + 键盘左键，向左跳一个单词
•ctrl + 键盘右键，向右跳一个单词
```


清空终端
`ctrl + l`
`clear`



### 安装软件
`yum [-y] [install | remove | search] 软件名称

	-y自动确认,,无需手动确认安装或卸载过程
	yum命令需要root权限
	yum命令需要联网

类似的ubuntu系列 需要将 yum 换位apt



查找安装路径`whereis nginx`  or `where nginx`


### systemctl

```sh
systemctl satrt docker
systemctl restart docker
systemctl stop docker 
systemctl daemon-reload # 重新加载配置文件
ssytemctl enable docker
```


`systemctl`命令控制软件的启动和关闭
`systemctl start | stop | status | enable | disable`
ex
NetworkManager
network
firewalld
sshd

ex
•yum install -y ntp，安装ntp软件s
可以通过ntpd服务名，配合systemctl进行控制
•yum install -y httpd，安装apache服务器软件
可以通过httpd服务名，配合systemctl进行控制

	部分软件安装后没有自动集成到systemctl中，我们可以手动添加。

### 软连接

`ln -s 初始位置 新建位置`

例如：ln -s /bin/less /usr/local/bin/less

-s 是代号（symbolic）的意思。
这 里有两点要注意：第一，ln命令会保持每一处链接文件的同步性，也就是说，不论你改动了哪一处，其它的文件都会发生相同的变化；第二，ln的链接又软链接 和硬链接两种，软链接就是ln -s ** **,它只会在你选定的位置上生成一个文件的镜像，不会占用磁盘空间，硬链接ln ** **,没有参数-s, 它会在你选定的位置上生成一个和源文件大小相同的文件，无论是软链接还是硬链接，文件都保持同步变化。
如果你用ls察看一个目录时，发现有的文件后面有一个@的符号，那就是一个用ln命令生成的文件，用ls -l命令去察看，就可以看到显示的link的路径了。

支持目录和文件

删除软连接

删除软链接和删除文件/目录操作一样，使用rm命令。但是需要特别注意的是，如果只是删除软链接，不想误删除软链接目录真实文件数据，在rm目录时路径末尾一定不能带"/“。如果带了”/"，会将链接到的路径下的文件全部删除。



### 日期和时区
`date [-d] [+格式化字符串]

	•%Y   年
	•%y   年份后两位数字 (00..99)
	•%m   月份 (01..12)
	•%d   日 (01..31)
	•%H   小时 (00..23)
	•%M   分钟 (00..59)
	•%S   秒 (00..60)
	•%s   自 1970-01-01 00:00:00 UTC 到现在的秒数

-d可以按照给定的字符串显示日期,可用于日期和时间计算

	•year年
	•month月
	•day天
	•hour小时
	•minute分钟
	•second秒

ex

`date- d "+1 day" +%Y%m%d`



##### 修改时区

细心的同学可能会发现，通过date查看的日期时间是不准确的，这是因为：系统默认时区非中国的东八区。

使用root权限，执行如下命令，修改时区为东八区时区

将系统自带的localtime文件删除，并将/usr/share/zoneinfo/Asia/Shanghai文件链接为localtime文件即可
```
rm -f /etc/localtime
sudo ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
```


###### ntp自动校准时区

安装ntp：
yum -y install ntp
启动并设置开机自启：
•systemctl start ntpd
•systemctl enable ntpd
当ntpd启动后会定期的帮助我们联网校准系统的时间
•也可以手动校准（需root权限）：ntpdate -u ntp.aliyun.com







### 防火墙操作

```sh
firewall-cmd --add-port=20/tcp --permanent # permanent 永久的
firewall-cmd --reload
```


	开启
	service firewalld start
	重启
	service firewalld restart
	关闭
	service firewalld stop
	查看防火墙规则
	firewall-cmd --list-all
	查询端口是否开放
	firewall-cmd --query-port=8080/tcp
	开放80端口
	firewall-cmd --permanent --add-port=80/tcp
	移除端口
	firewall-cmd --permanent --remove-port=8080/tcp
	重启防火墙(修改配置后要重启防火墙)
	firewall-cmd --reload

	参数解释
	1、firwall-cmd：是Linux提供的操作firewall的一个工具；
	2、--permanent：表示设置为持久；
	3、--add-port：标识添加的端口；\



### IP和主机 网络传输 (网络有关)

ens33 未有效的使用的网卡

查看端口占用
`netstat：yum -y install net-tools`
`netstat -anp | grep 端口号`
or
`namp`

#### 特殊ip

•127.0.0.1，这个IP地址用于指代本机

•0.0.0.0，特殊IP地址
•可以用于指代本机
•可以在端口绑定中用来确定绑定关系（后续讲解）
•在一些IP地址限制中，表示所有IP的意思，如放行规则设置为0.0.0.0，表示允许任意IP访问


`hostname` 获取主机名
`hostnamectl set-hostname` 修改主机名



#### 网站解析目录
```
•查看本机的记录
	•Windows看：C:\Windows\System32\drivers\etc\hosts
	•Linux看：/etc/hosts
•再联网去DNS服务器（如114.114.114.114，8.8.8.8等）询问
```



#### ping wget curl

ping
`ping [-c num] ip or 主机名`
-c 检查的次数 不适用将会无限制

wget
`wget [-b] url` 
-b 后台下载 会将日志写入wget-log中

curl
curl可以发送http网络请求，可用于：下载文件、获取信息等
`curl [-0] url`
•选项：-O，用于下载文件，当url是下载链接时，可以使用此选项保存文件
•参数：url，要发起请求的网络地址


#### 进程

操作系统会为每一个进程都分配一个独有的：进程ID（进程号）
`ps [-e] [-f]` 查看进程
	-e 显示全部进程
	-f 以完全格式化的形式展示信息
一般来说，固定用法就是： `ps -ef` 列出全部进程的全部信息

从左到右分别是：

	•UID：进程所属的用户ID
	•PID：进程的进程号ID
	•**PPID**：进程的父ID（启动此进程的其它进程）
	•C：此进程的CPU占用率（百分比）
	•STIME：进程的启动时间
	•TTY：启动此进程的终端序号，如显示?，表示非终端启动
	•TIME：进程占用CPU的时间
	CMD：进程对应的名称或启动路径或启动命令

	ps-ef|grep 8080

杀死进程
`kill [-9] 进程ID`
-9，表示强制关闭进程。不使用此选项会向进程发送信号要求其关闭，但是否关闭看进程自身的处理机制


### 主机状态
`top` 查看cpu 内存占用情况

默认每5秒刷新一次，语法：直接输入top即可，按q或ctrl + c退出

	•PID：进程id
	•USER：进程所属用户
	•PR：进程**优先级**，越小越高
	•NI：负值表示高优先级，正表示低优先级
	•VIRT：进程使用虚拟内存，单位KB
	•RES：进程使用物理内存，单位KB
	•SHR：进程使用共享内存，单位KB
	•S：进程状态（S休眠，R运行，Z僵死状态，N负数优先级，I空闲状态）
	•%CPU：进程占用CPU率
	•%MEM：进程占用内存率
	•TIME+：进程使用CPU时间总计，单位10毫秒
	•COMMAND：进程的命令或名称或程序文件路径

还有一系列操作

### 环境变量

环境变量是一种KeyValue型结构，即名称和值
•HOME：/home/itheima，用户的HOME路径
•USER：itheima，当前的操作用户
•PWD：当前工作路径
`echo $PATH` 查看PATH路径

发现已经存在了

	/usr/local/bin
	/usr/bin
	/usr/local/sbin
	/usr/sbin
	/home/itheima/.local/bin
	/home/itheima/bin

当执行任何命令，都会按照顺序，从上述路径中搜索要执行的程序的本体
比如执行cd命令，就从第二个目录/usr/bin中搜索到了cd命令，并执行

在Linux系统中，$符号被用于取”变量”的值
echo $PATH

Linux环境变量可以用户自行设置，其中分为：
•临时设置，语法：export 变量名=变量值
•永久生效
	•针对当前用户生效，配置在当前用户的：  ~/.bashrc文件中
	•针对所有用户生效，配置在系统的：  /etc/profile文件中
	•并通过语法：source 配置文件，进行立刻生效，或重新登录FinalShell生效

测试：
•在当前HOME目录内创建文件夹，myenv，在文件夹内创建文件mkhaha
•通过vim编辑器，在mkhaha文件内填入：echo 哈哈哈哈哈

设置上述测试的环境变量
•修改PATH的值
临时修改PATH：export PATH=$PATH:/home/itheima/myenv，再次执行mkhaha，无论在哪里都能执行了
或将export PATH=$PATH:/home/itheima/myenv，填入用户环境变量文件或系统环境变量文件中去



#### linux修改默认路径


Linux里打开文件夹/home/xxx（xxx是你的用户名），找到.bashrc文件，此文件是隐藏的

添加一行代码

cd path
path是你想设置的终端默认打开路径

如添加cd /mnt/f即可切换默认路径到挂载的F盘上

再打开终端就会发现



### 传输文件

#### scp 

```sh
scp file.txt username@remote_host:/remote/directory
scp username@remote_host:/remote/directory/file.txt ./
```

	如果你再`.bashrc`中配置了启动时输出文字的脚本,可能会导致传输不显示进度,输出不该输出的字符等等.


#### rz、sz命令 

> receive send  Zmodem(文件传输协议)

rz、sz命令需要安装，可以通过：`yum -y install lrzsz` 安装

	注意，rz、sz命令需要终端软件支持才可正常运行
	FinalShell、SecureCRT、XShell等常用终端软件均支持此操作



### 压缩解压

![](http://114.116.43.154/photobed/2024_03_08_21_30_34.png)


	•.tar，称之为tarball，归档文件，即简单的将文件组装到一个.tar的文件内，并没有太多文件体积的减少，仅仅是简单的封装
	•.gz，也常见为.tar.gz，gzip格式压缩文件，即使用gzip压缩算法将文件压缩到一个文件内，可以极大的减少压缩后的体积

#### tar

上述两种格式 均可用语法

`tar [-c -v -x -f -z -C] 参数1 参数2 ... 参数n`

	-c，创建压缩文件，用于压缩模式 compress
	-v，显示压缩、解压过程，用于查看进度
	-x，解压模式 extract
	-f，要创建的文件，或要解压的文件，-f选项必须在所有选项中位置处于最后一个
	-z，gzip模式，不使用-z就是普通的tarball格式
	-C，选择解压的目的地，用于解压模式






`•tar -cvf test.tar 1.txt 2.txt 3.txt`
将1.txt 2.txt 3.txt 压缩到test.tar文件内
`•tar -zcvf test.tar.gz 1.txt 2.txt 3.txt`
将1.txt 2.txt 3.txt 压缩到test.tar.gz文件内，使用gzip模式


	注意：
	•-z选项如果使用的话，一般处于选项位第一个
	•-f选项，必须在选项位最后一个



常用的tar解压组合有
`tar -xvf test.tar`
解压test.tar，将文件解压至当前目录
`tar -xvf test.tar -C /home/itheima`
解压test.tar，将文件解压至指定目录（/home/itheima）
`tar -zxvf test.tar.gz -C /home/itheima`
以Gzip模式解压test.tar.gz，将文件解压至指定目录（/home/itheima）

	注意：
	•-f选项，必须在选项组合体的最后一位
	•-z选项，建议在开头位置
	•-C选项单独使用，和解压所需的其它参数分开





#### zip,unzip命令


```sh
zip test.zip a.txt b.txt c.txt
将a.txt b.txt c.txt 压缩到test.zip文件内

zip -r test.zip test itheima a.txt
将test、itheima两个**文件夹**和a.txt文件，压缩到test.zip文件内

- unzip test.zip，将test.zip解压到当前目录
- unzip [-d] 参数   参数未解压要去的位置
- unzip test.zip -d /home/itheima，将test.zip解压到指定文件夹内（/home/itheima）
```








## linux文件系统

-   /bin：存储系统所使用的**常用命令**。
-   /boot：存储启动系统所需的文件，包括**内核**和**引导**程序。
-   /dev：**存储设备文件**，如**磁盘、网卡、打印机**等。
-   /etc：存储**系统的配置**文件。
-   /home：存储用户的个人文件。
-   /lib：存储**系统**所使用的**库文件**。
-   /media：存储外部设备，如 USB 闪存驱动器、CD/DVD 驱动器等。
-   /mnt：存储**挂载的文件系统**。
-   /opt：存储**可选的软件**。
-   /proc：存储内存中的**进程信息**。
-   /root：存储超级用户（root）的个人文件。
-   /sbin：存储系统管理员使用的**命令**。
-   /tmp：存储临时文件。
-   /usr：存储**应用**程序和文档。
-   /var：存储**系统日志文件和网站文件**。


/usr 目录下的一些常见子目录包括：

-   /usr/bin：存储可以被所有**用户使用的应用程序**。
-   /usr/lib：存储**应用**程序所使用的库文件。
-   /usr/share：存储**应用程序的共享文件**，如图标、字体和语言包等。
-   /usr/src：存储**源代码**。
-   /usr/local：存储**自行安装**的应用程序和文件。




## 灯笼哥 linux

正月点灯笼 linux

```sh
pwd 
ls 
cd 
cal 
data 
mkdir

cat a.txt
diff 判断两个文件的差异
wc word count 首单词
178 255 1836
行 单词 总大小
-l -w -c 
wc -l

head 
head a.cpp
head -2 a.cpp 展示前两行
tail
tail -2 a.cpp 展示前两行

 


rm a.cpp
windows 对大小写不是很敏感 而 linux对大小写十分敏感 严格区分大小写
less 文件浏览器类似 q 退回命令行
chmod rwx rwx rwx

	u g o
	user group others
	作者 小组 其他人

rw- r-- r--
chomd u-r  filename     user 减掉了read权限
chomd u+r  filename     user 加上了read权限
chomd go-r filename     group 和 others 减去了read权限




二进制操作

100 -> 1
010 -> 2
100 -> 4
chomd 444 chomd 777


正则表达式 通配符
在Linux中，通配符是用来匹配文件名或路径名的特殊字符。常用的通配符包括：

	*：匹配零个或多个任意字符。
	?：匹配任意单个字符。
	[]：匹配指定范围内的任意单个字符，如[abc]匹配a、b或c。
	[-]：匹配指定范围内的任意单个字符，如[a-z]匹配a到z之间的任意字符。
	[^]：匹配除括号内指定字符之外的任意单个字符，如[^abc]匹配除a、b、c之外的任意字符。





> < 重定向符 文件与命令的读取
ls > 1.txt

 

```



## 高级软件命令


### vim

:!w|python3 %

: 执行命令
:! 执行外部命令
% 当前文件
| 依次执行


yy 复制
p粘贴
999p
粘贴999行
大写G跳到最后一行
gg跳转第一行

u撤销



:99,017s/8/心/g

dd 删除

yG 复制 G是到末尾行

ctrl+r重做



### corntab自动化

注意：新创建的 cron 任务，不会马上执行，至少要过 2 分钟后才可以，当然你可以重启 cron 来马上执行。



#### 安装
检查是否安装
crontab：rpm -qa | grep crontab 
安装
yum -y install vixie-cronyum -y install crontabs
简单说明：
vixie-cron 是 cron 的主程序；
crontabs 是用来安装、卸装、或列举用来驱动 cron 守护进程的表格的程序。
crontab -e
*/1 * * * * python3 /home/1.py



#### 查看日志
tail -f /var/log/cron



#### 服务维护和配置
service crond start    //启动
service crond stop     //关闭
service crond restart  //重启
service crond reload   //重新载入配置
service crond staus    //查看状态
systemctl enable crond //开机启动
个性化
crontab [ -u user ] { -l | -r | -e }
-e : 执行文字编辑器来设定时程表，内定的文字编辑器是 VI，如果你想用别的文字编辑器，则请先设定 VISUAL 环境变数来指定使用那个文字编辑器(比如说 setenv VISUAL joe)
-r : 删除目前的时程表
-l : 列出目前的时程表






#### 服务命令格式
https://crontab.guru/#5_*_*_*_*
可以对命令进行转换简化
*/1 * * * *  每一分钟执行一次




#### 时间格式
f1 f2 f3 f4 f5 program
其中 f1 是表示分钟，f2 表示小时，f3 表示一个月份中的第几日，f4 表示月份，f5 表示一个星期中的第几天。program 表示要执行的程序。
当 f1 为 * 时表示每分钟都要执行 program，f2 为 * 时表示每小时都要执行程序，其馀类推
当 f1 为 a-b 时表示从第 a 分钟到第 b 分钟这段时间内要执行，f2 为 a-b 时表示从第 a 到第 b 小时都要执行，其馀类推
当 f1 为 */n 时表示每 n 分钟个时间间隔执行一次，f2 为 */n 表示每 n 小时个时间间隔执行一次，其馀类推
当 f1 为 a, b, c,... 时表示第 a, b, c,... 分钟要执行，f2 为 a, b, c,... 时表示第 a, b, c...个小时要执行，其馀类推
```
*    *    *    *    *
-    -    -    -    -
|    |    |    |    |
|    |    |    |    +----- 星期中星期几 (0 - 6) (星期天 为0)
|    |    |    +---------- 月份 (1 - 12) 
|    |    +--------------- 一个月中的第几天 (1 - 31)
|    +-------------------- 小时 (0 - 23)
+------------------------- 分钟 (0 - 59)
```




参考：https://www.runoob.com/linux/linux-comm-crontab.html











### nohub

后台运行 linux 菜鸟教程	







## 如何关闭一个进程

     1. 第一步：找到程序的进程ID（PID）
     知道进程的名称
     pidof <程序名称>
   	 会给出程序启动的所有进程的 PID

     2. 第二步：用PID杀死程序
     一旦获得所需应用程序的 PID 后，使用以下命令终止该进程：
     sudo kill -9 process_id(PID)
     多个进程
     sudo kill -9 process_id_1 process_id_2 process_id_3

     3. 当然了，也可以化两步为一步，直接写：
     sudo kill -9 `pidof programe_name` 

     4. 如果我们知道程序名称，且想要关闭所有的进程，可以直接这么写：
     killall program_name




##  软件安装

### 安装nodejs


用命令行形式进行下载
```sh
wget https://nodejs.org/dist/v14.15.4/node-v14.15.4-linux-x64.tar.xz
```
解压缩
```sh
tar -xvf node-v14.15.4-linux-x64.tar.xz
mkdir -p /usr/local/nodejs
mv node-v14.15.4-linux-x64/* /usr/local/nodejs/

```
创建软链接
建立node软链接
```sh
ln -s /usr/local/nodejs/bin/node /usr/local/bin
```
建立npm 软链接
```sh
ln -s /usr/local/nodejs/bin/npm /usr/local/bin
```
更换镜像源
```sh
npm config set registry https://registry.npm.taobao.org
npm config list
```


验证是否安装成功
```sh
node -v
npm -v
```



### 安装nginx

#### 详细

	1.安装gcc
	yum install gcc-c++

	2.PCRE pcre-devel 
	yum install -y pcre pcre-devel

	3、zlib 安装
	zlib 库提供了很多种压缩和解压缩的方式， nginx 使用 zlib 对 http 包的内容进行 gzip ，所以需要在 Centos 上安装 zlib 库。
	yum install -y zlib zlib-devel

	4、OpenSSL 安装
	OpenSSL 是一个强大的安全套接字层密码库，囊括主要的密码算法、常用的密钥和证书封装管理功能及 SSL 协议，并提供丰富的应用程序供测试或其它目的使用。
	nginx 不仅支持 http 协议，还支持 https（即在ssl协议上传输http），所以需要在 Centos 安装 OpenSSL 库。
	yum install -y openssl openssl-devel

	5、下载安装包
	手动下载.tar.gz安装包，地址：https://nginx.org/en/download.html
	后缀pgp

	6、解压
	tar -zxvf nginx-1.18.0.tar.gz
	cd nginx-1.18.0

	7、配置
	使用默认配置，在nginx根目录下执行
	./configure
	make
	make install

	8.启动成功访问 服务器ip:80



	cd /usr/local/nginx/sbin/
	./nginx  启动
	./nginx -s stop  停止
	./nginx -s quit  安全退出
	./nginx -s reload  重新加载配置文件
	ps aux|grep nginx  查看nginx进程





#### 不详细



1
创建文件目录
```sh
cd /usr/local/
mkdir nginx
cd nginx
```
2
将nginx安装包解压到/usr/local/nginx中
```sh
[root@local nginx]tar zxvf /root/nginx(安装包位置) -C ./
```
3
安装依赖
```sh
yum -y install pcre-devel
yum -y install openssl-devel
```
4
编译安装
```sh
cd nginx
./configure
make&&make install
```
5
安装完成后,nginx的可执行文件位于
`/user/lcoal/nginx/sbin/`
6
启动程序
`./nginx`
7
配置文件位于
`/user/lcoal/nginx/conf`
8
重启nginx服务
`/usr/local/nginx/sbin/nginx -s reload`


### 安装doscify

	安装(前提 安装nodejs)  
	npm install -g docsify-cli  安装docsify npm安装看之前文档

	创建软连接
	ln -s /usr/local/nodejs/bin/docsify /usr/local/bin/

	初始化文档
	doscify init ./

	启动服务
	doscify service








## 删除软件

```
apt list --installed |grep steam # 用来查看完整的包名
sudo dpkg -r steam-launcher
```





## 其他linux


apline linux
卸载软件 apk del gcc


kali linux
apt-get remove
删除包







## 杂

### 写入文件

	curl localhost:8080 > 1.txt


### 端口占用

	netstat -anp | grep 8080





### alias bashrc

	bashrc 中 alias 可以起别名
	alias rm='rm -i'


	重新加载文件
	source .bashrc


### 查看 Linux 系统的信息


```

查看 Linux 发行版和版本信息：
cat /etc/*release

ls /etc/*release
or
ls | grep release

查看内核版本：
uname -a

查看系统架构信息：
uname -m

查看CPU信息：
cat /proc/cpuinfo

查看内存信息：
free -m

查看磁盘空间：
df -h

```











### 创建systemctl服务

首先，新建一个系统服务文件：

```sh
vi /etc/systemd/system/redis.service
```

内容如下：

```conf
[Unit]
Description=redis-server
After=network.target

[Service]
Type=forking
ExecStart=/usr/local/bin/redis-server /usr/local/src/redis-6.2.6/redis.conf
PrivateTmp=true

[Install]
WantedBy=multi-user.target
```



然后重载系统服务：

```sh
systemctl daemon-reload
```



现在，我们可以用下面这组命令来操作redis了：

```sh
# 启动
systemctl start redis
# 停止
systemctl stop redis
# 重启
systemctl restart redis
# 查看状态
systemctl status redis
```




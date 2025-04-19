## 错误回显的ip和我输入得ip不同，javaweb终端，largon终端
密码输入错了。

## intel项目报错也可以正常运行项目
不用管控制台的报错异常，如果不影响项目的话


## 一般的java项目在打包成jar包的时候，会出现src外的文件访问不到的情况。
原因：由于存放的图片的路径可能是在src外面的。这样子，打包成jar包之后，类中访问的路径是需要到src下找资源。这时候就出现找不到图片的情况。


![](http://localhost/photobed/src_jar_test.png)


1. 打包后操作
就是在打包成jar包之后，把需要的图片的文件夹，存在到和jar包相同的目录中。
在这里插入图片描述
2. 打包前操作
就是把图片存放的文件夹，写到src的路径下，把普通文件夹，改成package包。然后使用如下代码进行图片的获取：
类.class.getResource("图片路径")


## 外部导入的springboot项目不显示启动按钮,也不识别
File-Project structure(Ctrl+Alt+Shift+S)-Modules

Import Module

之后就正常了

## 更改css之后,重新上传至服务器中不生效

浏览器缓存了css数据, 用隐私窗口打开,发现正常




### C++

```c++


#include<bits/stdc++.h>
using namespace std;
char str[100][100];
char reg[100];
int n,m,top=0;
int get(char ch){
	for(int i=0;i<m;i++){
		if(ch==reg[i])
			return i;
	}
	return -1;
}
int use(int x,char ch){
	for(int i=x;i<n;i++){
		if(ch==str[i][3]||ch==str[i][5])
			return i;
	}
	return -1;
}
int find(int x){
	if(top<m)
		return top++;
	int ans=-1;
	int w=-1;
	for(int i=0;i<m;i++){
		int v=use(x,reg[i]);
		if(v>ans||v==-1){
			ans=v;
			w=i;
		}
	}
	return w;
}
void print1(char ch){
	if(ch=='+')
		cout<<"ADD ";
	else if(ch=='-')
		cout<<"SUB ";
	else if(ch=='*')
		cout<<"MUL ";
	else if(ch=='\\')
		cout<<"DIV ";
}
void print2(char ch){
	int x=get(ch);
	if(x==-1)
		cout<<ch<<endl;
	else
		cout<<"R"<<x<<endl;
}
int main(){
	int n,m;
	cin>>n>>m;
	for(int i=0;i<n;i++)
		cin>>str[i];
	for(int i=0;i<n;i++){
		int x=get(str[i][3]);
		if(x==-1){
			x=find(i);
			if(reg[x]!='\0'&&use(i,reg[x])>0){
				printf("ST R%d, %c\n",x,reg[x]);
				reg[x]=NULL;
			}
			printf("LD R%d, %c\n",x,str[i][3]);
		}
		print1(str[i][4]);
		printf("R%d, ",x);
		print2(str[i][5]);
		reg[x]=str[i][0];
	}
	return 0;
}


int n,m; 定义了两遍，导致调试的时候出现错误


```





### 大于换成小于等于而不是小于






### 找不到依赖

```
org.apache.poi:poi-ooxml:jar:unknown was not found in https://maven.aliyun.com/repository/public during a previous attempt. This failure was cached in the local repository and resolution is not reattempted until the update interval of aliyunmaven has elapsed or updates are forced
```

![](http://leaweihou.site:1003/photobed/2024_03_13_23_32_42.png)

找不到这个依赖

小众的依赖, 因为springboot中未定义这个版本, 你也没有指定版本. 

> 指定版本后正常

 
### 文件问题

```java
FileOutputStream outputStream=new FileOutputStream(new File("E:/test/info.xlsx"));
```

不存在test这个文件夹,不会自动创建文件夹



## 无法连接tomcat
---没有配置tomcat插件

## tomcat启动失败 
----已经被占用了,打开本地端口发现确实有进程占用


## 用Scanner 操作文件会出错, 打成jar包会出错

文件操作的时候减少使用Scanner,交互的时候可以考虑Scanner


```java
public static void main(String[] args) throws FileNotFoundException {  
    File file = new File("test.txt");  
    Scanner sc = new Scanner(file);  
    String str;  
    StringBuffer buf = new StringBuffer();  
    while (sc.hasNext()) {  
        str = sc.nextLine();  
        for (int i = 0; i < str.length(); i++) {  
            buf.append(str.charAt(i));  
        }  
    }  
}
```





重启项目之后Session仍然存在

1. X
```yml
server:
  servlet:
    session:
      persistent: false
```
1. X
去掉快速部署的依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```


### jdk和springboot版本问题

Caused by: org.springframework.core.NestedIOException: ASM ClassReader failed to parse class file - probably due to a new Java class file version that isn't supported yet: file [C:\Users\kali\Desktop\java\day08-HTTP&Tomcat&Servlet\untitled3\target\classes\com\lgh\test\MainApplication.class]; nested exception is java.lang.IllegalArgumentException: Unsupported class file major version 62
	at org.springframework.core.type.classreading.SimpleMetadataReader.<init>(SimpleMetadataReader.java:57) ~[spring-core-5.1.7.RELEASE.jar:5.1.7.RELEASE]



### jdk和springboot版本问题

引起java.lang.ExceptionInInitializerError 错误的原因是：在类的初始化时，出错。也就是说，在加载类时，执行static的属性、方法块时，出错了。

或者是引入的某个东西的版本不对，因为springboot引入的部分以来会自己配置，比如你的jdk版本过高








## Swagger bug

Swagger 测试输入Date类型问题

isS -> s 
isB -> b








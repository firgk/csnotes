# 第1章 SpringBoot开发入门

## 重点：SpringBoot开发环境搭建

## 1.SpringBoot概述

## 1.1SpringBoot简介

- 学过javaweb的同学就知道，开发一个web应用，从最初开始接触Servlet结合Tomcat, 跑出一个Hello Wolrld程序，是要经历特别多的步骤；后来就用了框架Struts，再后来是SpringMVC，到了现在的SpringBoot，过一两年又会有其他web框架出现；你们有经历过框架不断的演进，然后自己开发项目所有的技术也在不断的变化、改造吗？建议都可以去经历一遍；

- 言归正传，什么是SpringBoot呢，就是一个javaweb的开发框架，和SpringMVC类似，对比其他javaweb框架的好处，官方说是简化开发，约定大于配置，  you can "just run"，能迅速的开发web应用，几行代码开发一个http接口。

- 所有的技术框架的发展似乎都遵循了一条主线规律：从一个复杂应用场景 衍生 一种规范框架，人们只需要进行各种配置而不需要自己去实现它，这时候强大的配置功能成了优点；发展到一定程度之后，人们根据实际生产应用情况，选取其中实用功能和设计精华，重构出一些轻量级的框架；之后为了提高开发效率，嫌弃原先的各类配置过于麻烦，于是开始提倡“约定大于配置”，进而衍生出一些一站式的解决方案。

- 是的这就是Java企业级应用->J2EE->spring->springboot的过程。

- 随着 Spring 不断的发展，涉及的领域越来越多，项目整合开发需要配合各种各样的文件，慢慢变得不那么易用简单，违背了最初的理念，甚至人称配置地狱。Spring Boot 正是在这样的一个背景下被抽象出来的开发框架，目的为了让大家更容易的使用 Spring 、更容易的集成各种常用的中间件、开源软件；

- Spring Boot 基于 Spring 开发，Spirng Boot 本身并不提供 Spring 框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于 Spring 框架的应用程序。也就是说，它并不是用来替代 Spring 的解决方案，而是和 Spring 框架紧密结合用于提升 Spring 开发者体验的工具。Spring Boot 以**约定大于配置的核心思想**，默认帮我们进行了很多设置，多数 Spring Boot 应用只需要很少的 Spring 配置。同时它集成了大量常用的第三方库配置（例如 Redis、MongoDB、Jpa、RabbitMQ、Quartz 等等），Spring Boot 应用中这些第三方库几乎可以零配置的开箱即用。

- 简单来说就是SpringBoot其实不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合了所有的jar包，spring boot整合了所有的框架 。

- Spring Boot 出生名门，从一开始就站在一个比较高的起点，又经过这几年的发展，生态足够完善，Spring Boot 已经当之无愧成为 Java 领域最热门的技术。

## 1.2SpringBoot的优点

- 为所有Spring开发者更快的入门
- **开箱即用**，提供各种默认配置来简化项目配置
- 内嵌式容器简化Web项目
- 没有冗余代码生成和XML配置的要求
- 自动化配置Spring和第三方库

## 2.SpringBoot入门程序

## 2.1SpringBoot环境搭建

## 2.2使用Maven方式构建SpringBoot项目

### 1.创建Maven项目

![](E:\java框架\SpringBoot课件\imgs\chap01\1.png)

![](E:\java框架\SpringBoot课件\imgs\chap01\2.png)

### 2.添加SpringBoot相关依赖

```
 <!--引入SpringBoot依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
    </parent>
```

```
 <!-- 选择的Web模块依赖启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

### 3.编写主程序启动类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Chap01Application {
    //主程序启动方法
    public static void main(String[] args) {
        SpringApplication.run(Chap01Application.class,args);
    }
}
```

@SpringBootApplication是SpringBoot框架的核心注解，表明所在类是SpringBoot项目的主程序启动类

### 4.创建一个用于Web访问的Controller

```
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot!";
    }
}
```

（1）@RestController = @Controller + @ResponseBody组成，等号右边两位同志简单介绍两句，就明白我们@RestController的意义了：

@Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。当然也有语义化的作用，即代表该类是充当Controller的作用

@ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会以Json字符串的形式返回给客户端，本人尝试了一下，如果返回的是String类型，则仍然是String。

（2） @GetMapping等同于@RequestMapping(method=RequestMethod.GET)注解如果我们想使用传统的*@RequestMapping*注释实现URL处理程序，那么它应该是这样的：

@RequestMapping(value = “/get/{id}”, method = RequestMethod.GET)

新方法可以简化为：

@GetMapping("/get/{id}")

### 5.运行项目

![](E:\java框架\SpringBoot课件\imgs\chap01\3.png)

```
http://localhost:8080/hello
```

### 6.彩蛋

（1）使用properties文件方式：

在src/main/resources目录下创建：application.properties，添加如下配置即可修改端口号：

```
# 更改项目的端口号
server.port=8081
```

（2）修改application.yml配置文件改端口

在src/main/resources目录下创建：application.yml，添加如下配置即可修改端口号：

```
server:  
  port: 8090  //配置端口
  session-timeout: 30  
  tomcat.max-threads: 0  
  tomcat.uri-encoding: UTF-8  
  servlet:
    context-path: /demo # 服务的路径，不写默认为空
spring:  
  datasource:  //数据库配置
    url : jdbc:mysql://localhost:3306/newbirds  
    username : root  
    password : mymysql  
    driverClassName : com.mysql.jdbc.Driver  
```

（3）如何更改启动时显示的字符拼成的字母，SpringBoot呢？也就是 banner 图案；

只需一步：到项目下的 resources 目录下新建一个banner.txt 即可。

图案可以到：https://www.bootschool.net/ascii 这个网站生成，然后拷贝到文件中即可！

![](E:\java框架\SpringBoot课件\imgs\chap01\4.png)

（4）端口占用问题

windows

```
1. 首先用netstat -ano | find “端口号”查出进程号
2. tasklist 查询当前的进行
3. 如何杀死进程呢  tasklist /pid ${xx}
```

macos

```
lsof -i :端口
kill -9 进程ID
```

## 2.3使用Spring Initializr方式构建Spring Boot项目

### 1.创建Spring Boot项目

![](E:\java框架\SpringBoot课件\imgs\chap01\5.png)

![](E:\java框架\SpringBoot课件\imgs\chap01\6.png)

![](E:\java框架\SpringBoot课件\imgs\chap01\7.png)

### 2.创建一个Controller

```
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot!";
    }
}
```

### 3.运行项目

推荐使用这种方式构建Spring Boot项目

注：如果出现

![](E:\java框架\SpringBoot课件\imgs\chap01\8.png)

加上@ComponentScan(basePackages = {"com.sdut"})

### 4.使用阿里构建Spring Boot 应用程序

![](assets/2022-08-18-15-22-39-image.png)

[http://start.aliyun.com](http://start.aliyun.com/)

## 2.4单元测试与热部署

### 1.单元测试

（1）添加测试依赖启动器

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
</dependency>
```

（2）编写单元测试类和测试方法

```
import com.sdut.controller.HelloBoot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    @Autowired
    private HelloBoot helloBoot;
    @Test
    public void testHello(){
        String hello=helloBoot.hello();
        System.out.println(hello);
    }
}
```

### 2.热部署

（1）添加热部署依赖启动器

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

（2）IDEA工具热部署设置

![](E:\java框架\SpringBoot课件\imgs\chap01\9.png)

在项目任意界面使用组合键“Ctrl+Shift+Alt+/”,打开Maintenance选项框，选中并打开Registry界面

注：

windows环境：按快捷键Ctrl+Shift+Alt+/，选择1.Registry
mac： 组合键 shift + option + command + / 选择1.Registry

![](E:\java框架\SpringBoot课件\imgs\chap01\10.png)

找到compiler.automake.allow.when.app.running 勾选

菜单栏选择 Run->Edit Configurations…

![](E:\java框架\SpringBoot课件\imgs\chap01\11.png)

## 3.Spring Boot原理分析

# 第4章  Spring Boot视图技术

Spring Boot框架为简化项目的整体开发，对一些常用的视图技术实现了整合支持，并主要推荐整合模板引擎技术来实现前端页面的动态化内容。

MVC

## 4.1Spring Boot 支持的视图技术

### 1.Spring Boot支持的视图技术

![](E:\java框架\SpringBoot课件\imgs\chap04\1.png)

（1）FreeMarker 是一款 *模板引擎*： 即一种基于模板和要改变的数据， 并用来生成输出文本(HTML网页，电子邮件，配置文件，源代码等)的通用工具。 它不是面向最终用户的，而是一个Java类库，是一款程序员可以嵌入他们所开发产品的组件。

模板编写为FreeMarker Template Language (FTL)。它是简单的，专用的语言， *不是* 像PHP那样成熟的编程语言。 那就意味着要准备数据在真实编程语言中来显示，比如数据库查询和业务运算， 之后模板显示已经准备好的数据。在模板中，你可以专注于如何展现数据， 而在模板之外可以专注于要展示什么数据。

![](E:\java框架\SpringBoot课件\imgs\chap04\2.png)

（2）Groovy是一种基于JVM的敏捷开发语言，它结合了Python、Ruby和Smalltalk的许多强大的特性，Groovy 代码能够与 Java 代码很好地结合，也能用于扩展现有代码。由于其运行在 JVM 上的特性，Groovy也可以使用其他非Java语言编写的库。

（3）Thymeleaf（/taɪm/）是一个XML/XHTML/HTML5模板引擎，可用于Web与非Web环境中的应用开发。它是一个开源的Java库。

Thymeleaf提供了一个用于整合Spring MVC的可选模块，在应用开发中，你可以使用Thymeleaf来完全代替JSP，或其他模板引擎，如Velocity、FreeMarker等。Thymeleaf的主要目标在于提供一种可被浏览器正确显示的、格式良好的模板创建方式，因此也可以用作静态建模。你可以使用它创建经过验证的XML与HTML模板。相对于编写逻辑或代码，开发者只需将标签属性添加到模板中即可。

（4）Mustache （ /ˈmʌstæʃ,məˈstæʃ/ ）是一款经典的前端模板引擎，在前后端分离的技术架构下面，前端模板引擎是一种可以被考虑的技术选型，随着重型框架（AngularJS、ReactJS、Vue）的流行，前端的模板技术已经成为了某种形式上的标配，Mustache 的价值在于其稳定和经典。

### 2.Spring Boot 与Jsp

![](E:\java框架\SpringBoot课件\imgs\chap04\3.png)

## 4.2 Thymaleaf 基本语法

### 1.常用标签

实例：

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" media="all"
          href="../../css/gtvg.css" th:href="@{/css/gtvg.css}" />
    <title>Title</title>
</head>
<body>
    <p th:text="#{hello}">欢迎进入Thymeleaf的学习</p>
</body>
</html>
```

Thymeleaf常用标签

| **th:标签** | **说明**                               |
| ----------- | -------------------------------------- |
| th:insert   | 页面片段包含（类似JSP中的include标签） |
| th:replace  | 页面片段包含（类似JSP中的include标签） |
| th:each     | 元素遍历（类似JSP中的c:forEach标签）   |
| th:if       | 条件判断，如果为真                     |
| th:unless   | 条件判断，如果为假                     |
| th:switch   | 条件判断，进行选择性匹配               |
| th:case     | 条件判断，进行选择性匹配               |
| th:object   | 变量声明                               |
| th:with     | 变量声明                               |
| th:attr        | 通用属性修改                                 |
| th:attrprepend | 通用属性修改，将计算结果追加前缀到现有属性值 |
| th:attrappend  | 通用属性修改，将计算结果追加后缀到现有属性值 |
| th:value       | 属性值修改，指定标签属性值                   |
| th:href        | 用于设定链接地址                             |
| th:src         | 用于设定链接地址                             |
| th:text        | 用于指定标签显示的文本内容                   |
| th:utext       | 用于指定标签显示的文本内容，对特殊标签不转义 |
| th:fragment    | 声明片段                                     |
| th:remove      | 移除片段                                     |

更多参考官方文档[Thymeleaf](https://www.thymeleaf.org/)

### 2.标准表达式

**主要表达式语法**

| **说明**       | **表达式语法** |
| -------------- | -------------- |
| 变量表达式     | ${...}         |
| 选择变量表达式 | *{...}         |
| 消息表达式     | #{...}         |
| 链接URL表达式  | @{...}         |
| 片段表达式     | ~{...}         |

说明：

变量表达式：获取上下文中的变量值

选择变量表达式：用于从被选定对象获取属性值

消息表达式：用于Thymeleaf模板页面国际化内容的动态替换和展示

链接URL表达式：用于页面跳转或者资源的引入

片段表达式：用来标记一个片段模板，并根据需要移动或传递给其他模板

（1）变量表达式

实例代码

```
<p th:text="${title}">这是标题</p>
```

说明：若存在title变量，p标签的内容会被替换，反之就显示默认的

Thymeleaf为变量所在域提供了一些内置对象，如下

- #ctx：上下文对象、
- #vars：上下文变量
- #locale：上下文区域设置
- #request：（仅限Web Context）HttpServletRequest对象
- #response：（仅限Web Context）HttpServletResponse对象
- #session：（仅限Web Context）HttpSession对象
- #servletContext：（仅限Web Context）ServletContext对象

实例代码

```
The local country is:<span th:text="${#local.country}">US</sapn>
```

(2)选择变量表达式

同变量表达式类似，一般用于从被选定对象而不是上下文获取属性值，若没有选定对象，则和变量表达式一样。

```
<div th:object="${session.user}">
    <p>Name:<span th:text="${#object.firstName}">Sebastian</span>.</p>
    <p>Surname:<span th:text="${#session.user.lastName}">Pepper</span>.</p>
    <p>Nationlity:<span th:text="*{nationality}">Saturn</span>.</p>
</div>
```

(3)消息表达式

主要用于国际化

(4)链接表达式

一般用于页面跳转或者资源的引入

```
<a href="details.html"
   th:href="@{http://localhost:8080/gtvg/order/details(orderId=${o.id})}">view</a>
<a href="details.html" th:href="@{/order/details(orderId=${o.id})}">view</a> 
```

 (5)片段表达式

用于将标记片段移动到模板中的方法，最常用th:insert或th:replace

```
<div th:insert="~{thymeleafDemo::title}"></div>
```

## 4.3Thymaleaf 基本使用

### 1.Thymeleaf 模板基本配置

(1)在Spring Boot项目中使用Thymeleaf模板，必须保证引入Thymeleaf依赖。

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

(2)其次在全局配置文件中配置Thymeleaf模板的一些参数。如设置模板缓存、模板编码、模板样式、指定模板页面存放路径、指定模板页面名称的后缀

```
spring.thymeleaf.cache = true        #启用模板缓存
spring.thymeleaf.encoding = UTF-8   #模板编码
spring.thymeleaf.mode = HTML5       #应用于模板的模板样式
spring.thymeleaf.prefix = classpath:/templates/  #指定模板页面存放路径
spring.thymeleaf.suffix = .html   #指定模板页面名称的后缀
```

### 2.静态资源的访问

Spring Boot默认将/**所有访问映射到以下目录：

- classpath：/META-INF/resources/：项目类路径下的META-INF文件夹下的resources文件夹下的所有文件。
- classpath：/resources/：项目类路径下的resources文件夹下的所有文件。
- classpath：/static/：项目类路径下的static文件夹下的所有文件
- classpath：/public/：项目类路径下的public文件夹下的所有文件。

## 4.4使用Thymaleaf完成页面的数据展示
### 1.创建Spring Boot项目，引入Thymeleaf依赖

![](E:\java框架\SpringBoot课件\imgs\chap04\4.png)

### 2.编写配置文件，对Thymeleaf模板的页面数据缓存进行设置

thymeleaf页面缓存设置（默认为true），开发中方便调试应设置为false，上线稳定后应保持默认true

```
spring.thymeleaf.cache=false
```

### 3.创建控制类：@Controller

在项目下创建controller包，创建用于前端的模板页面动态数据测试的实体类LoginController

```
@Controller
public class LoginController {
    @GetMapping("/toLoginPage")
    public String toLoginPage(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "login";
    } 
}
```

### 4.创建模板页面并引入静态资源文件

在resources的templates目录下创建一个用户登录的模板页面login.html

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
    <title>用户登录界面</title>
    <link th:href="@{/login/css/bootstrap.min.css}" rel="stylesheet">
    <link th:href="@{/login/css/signin.css}" rel="stylesheet">
</head>
<body class="text-center">
<!--  用户登录form表单 -->
<form class="form-signin">
    <img class="mb-4" th:src="@{/login/img/login.jpg}" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
    <input type="text" class="form-control"
           placeholder="用户名" required="" autofocus="">
    <input type="password" class="form-control"
           placeholder="密码" required="">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me">
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <p class="mt-5 mb-3 text-muted">© <span th:text="${currentYear}">2018</span>-<span th:text="${currentYear}+1">2019</span></p>
</form>
</body>
</html>
```

### 5.效果测试

http://localhost:8080/toLoginPage

![](E:\java框架\SpringBoot课件\imgs\chap04\5.png)


## 4.5使用Thymaleaf配置国际化页面
### 1.编写多语言国际化文件以及配置文件

在resource目录下创建i18n的文件夹，添加国际化文件login.properties、login_zh_CN.properties、login_en_US.properties

```
login.tip=请登录
login.username=用户名
login.password=密码
login.rememberme=记住我
login.button=登录
```

```
login.tip=Please sign in
login.username=Username
login.password=Password
login.rememberme=Remember me
login.button=Login
```

```
login.tip=请登录
login.username=用户名
login.password=密码
login.rememberme=记住我
login.button=登录
```

注意：文件名有特殊要求 

### 2.编写配置文件

打开项目的application.properties全局配置文件，在该文件中添加国际化文件基础名设置

```
# thymeleaf页面缓存设置（默认为true），开发中方便调试应设置为false，上线稳定后应保持默认true
spring.thymeleaf.cache=false
# 配置国际化文件基础名
spring.messages.basename=i18n.login
```

### 3.定制区域信息解析器

在config包下创建一个用于定制国际化功能区域信息解析器的自定义配置类MyLocalResovel，MyLocalResolver自定义区域解析器配置类实现了LocaleResolver接口，并重写了其中的resolveLocale()方法进行自定义语言解析，最后使用@Bean注解将当前配置类注册成Spring容器中的一个类型为LocaleResolver的Bean组件，这样就可以覆盖默认的LocaleResolver组件。

```
@Configuration
public class MyLocalResovel implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取页面手动切换传递的语言参数l
        String l = httpServletRequest.getParameter("l");
        // 获取请求头自动传递的语言参数Accept-Language
        String header = httpServletRequest.getHeader("Accept-Language");
        Locale locale=null;
        // 如果手动切换参数不为空，就根据手动参数进行语言切换，否则默认根据请求头信息切换
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale=new Locale(split[0],split[1]);
        }else {
            // Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7
            String[] splits = header.split(",");
            String[] split = splits[0].split("-");
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResovel();
    }
}
```

### 4.页面国际化使用

用户登录页面login1.html结合Thymeleaf模板引擎实现国际化功能，核心代码如下

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
    <title>用户登录界面</title>
    <link th:href="@{/login/css/bootstrap.min.css}" rel="stylesheet">
    <link th:href="@{/login/css/signin.css}" rel="stylesheet">
</head>
<body class="text-center">
<!--  用户登录form表单 -->
<form class="form-signin">
    <img class="mb-4" th:src="@{/login/img/login.jpg}" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">请登录</h1>
    <input type="text" class="form-control"
           th:placeholder="#{login.username}" required="" autofocus="">
    <input type="password" class="form-control"
           th:placeholder="#{login.password}" required="">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> [[#{login.rememberme}]]
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{login.button}">登录</button>
    <p class="mt-5 mb-3 text-muted">© <span th:text="${currentYear}">2018</span>-<span th:text="${currentYear}+1">2019</span></p>
    <a class="btn btn-sm" th:href="@{/toLoginPage1(l='zh_CN')}">中文</a>
    <a class="btn btn-sm" th:href="@{/toLoginPage1(l='en_US')}">English</a>
</form>
</body>
</html>
```

### 5.整合效果测试

在LoginController添加

```
  @GetMapping("/toLoginPage1")
    public String toLoginPage1(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "login1";
    }
```

http://localhost:8080/toLoginPage1


# thymeleaf

from
https://www.bilibili.com/video/BV1qy4y117qi/


## springboot maven

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>

```

application.yaml

```yaml

spring:
  # 配置thymeleaf的相关信息
  thymeleaf:
    # 开启视图解析
    enabled: true
    #编码格式
    encoding: UTF-8
    #前缀配置
    prefix: classpath:/templates/
    # 后缀配置
    suffix: .html
    #是否使用缓存 开发环境时不设置缓存
    cache: false
    # 格式为 HTML 格式
    mode: HTML5
    # 配置类型
    servlet:
      content-type: text/html

```

## 一个简单的例子


static：用于存放静态资源，例如html、JavaScript、css以及图片等。
templates：用来存放模板引擎Thymeleaf(本质依然是.html文件)
需要在controller中指定Thymeleaf页面的url，然后再Model中绑定数据。



controller

```java
@Controller
public class HelloController
{
    @GetMapping("/index")
    public String hello(Model model)
    //对应函数
    {
        model.addAttribute("name","Leo");
        return "index";
        //与templates中index.html对应
    }
}

@Controller()
@RequestMapping("/api")
public class MainApi {
    @RequestMapping("/index")
    public String test(){
        return "index";
    }
}



public String basicTrain(Model model){
    Userv0.userv0=new UserV0();
    Userv0.setUsername("lookroot");
    Userv0.setAge(22);
    Userv0.setIsVip(true);
    Userv0.setTags(Arrays.asList("php","node","java"));
    model.setAttribute("user",userV0);
    return "basic";
}
```



注意引入

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
hello 第一个Thymeleaf程序
<div th:text="${name}">name是Leo>(我是离线数据)</div>
</body>
</html>
```







常用方法

```
th:id	替换id	<input th:id="${user.id}"/>
th:text	文本替换	<p text:="${user.name}">bigsai</p>
th:utext	支持html的文本替换	<p utext:="${htmlcontent}">content</p>
th:object	替换对象	<div th:object="${user}"></div>
th:include	替换内容到引入文件	<script type="text/javascript" th:include="@{index.js}"></script>
th:value	替换值	<input th:value="${user.name}" >
th:each	迭代	<tr th:each="student:${user}" >
th:href	替换超链接	<a th:href="@{index.html}">超链接</a>
th:src	替换资源	<script type="text/javascript" th:src="@{index.js}"></script>声明。
```






## 语法



字符串链接方式

1
`<div th:text="'名字是'+${name}">name是Leo>(我是离线数据)</div>`

2
`<div th:text=|名字是${name}|>name是Leo>(我是离线数据)</div>`



```
<div th:text="${user}">name是Leo>(我是离线数据)
    <p th:text="*{name}">name</p>
    <h1 th:text="*{age}">age</h1>
</div>
```


```
<p th:text="${user.name}">name</p>
<h1 th:text="${user.age}">age</h1>`
```

if

`<p th:if="$"{user.isVip}>会员</p>`
效果
`会员`


each

`user.setTags(Arrays.asList("PHP,Node,"java"))`

```html
<ul>
    <li th:each="tag:${user.tags}" th:text="${tag}"></li>
</ul>    
```


<tr th:each="comment : ${comments}">
        <td th:text="${comment.id}"></td>
        <td th:text="${comment.content}"></td>
        <td th:text="${comment.author}"></td>
        <td th:text="${comment.aId}"></td>
</tr>



switch

<div th:switch="${user.sex}">
    <p th:case ="1">男</p>
    <p th:case ="2">女</p>
    <p th:case ="*">默认</p>

</div>


引入script

<link rel="stylesheet" th:hetf="@{app.css}">


引入 js 并且传入参数
<script th:inline="javascript">
    const user=/*[[${user}]]{}*/{默认值};
    console.log(user)
</script>

```js
var userId = [[${ session.user.id }]];
```


切片

component.html

两种方式，但是引入的时候用法不同


```html
<!doctype html>
<html lang='en' xmlns:th="http://www.thymeleaf.org">
<footer th: fragment="com1">
    com1
</footer>

<div id ="com2">
    com2
</div>
```
在index.html 中

第一个会替换掉标签
第二个不会替换掉
第三个配合上述的写法

```html
<div th:replace="~{commponent::com1}"><div>
<div th:insert="~{commponent::com1}"><div>
<div th:insert="~{commponent::#com2}"><div>
```




## 表单提交
```html
<form th:action="@{/user/login}" method="post">
    <table cellpadding="0" cellspacing="0" border="0"
        class="form_table">
        <tr>
            <td valign="middle" align="right">
                用户名:
            </td>
            <td valign="middle" align="left">
                <input type="text" class="inputgri" name="username" />
            </td>
        </tr>
        <tr>
            <td valign="middle" align="right">
                密码:
            </td>
            <td valign="middle" align="left">
                <input type="password" class="inputgri" name="password" />
            </td>
        </tr>
    </table>
    <p>
        <input type="submit" class="button" value="点我登录 &raquo;" />
        &nbsp;&nbsp;
        <a href="regist.html">还没有账号,立即注册</a>
    </p>
</form>
```

```java

@RequestMapping("login")
public String login(String username,String password,HttpSession session){
    if...{
        return "redirect:/login";//登录失败回到登录界面
    }
    return "redirect:/employee/lists";//登录成功之后,跳转到查询所有员工信息控制器路径
}
```

## 工具类



## 组件拓展和组件传播





## 参考

### 语法

新建basic.html，此时如果我们想渲染User这个对象的信息我们可以这样

```html
<div>
    <h2 th:text="${user.getUsername()}"></h2>
    <p th:text="${user.getAge()}"></p>
</div>
```

也可以将User定义为临时变量，接着使用*{xxx}就能取到值了


```html
<div th:object="${user}">
    <h2 th:text="*{username}"></h2>
    <p th:text="*{age}"></p>
</div>
```

还可以不使用get的方式，直接使用属性名

```html
<h2 th:text="${user.username}" ></h2>
```



th:if
th:if通过布尔值决定这个元素是否渲染

```html
<p th:if="${user.isVip}">会员</p>
```


th:each
th:each可以迭代循环出数据，前面我们User对象里面的tags是一个数组，我们来渲染一下

```html
<ul>
   <li th:each="tag:${user.getTags()}" 
       th:text="${tag}"></li>
</ul>
```

状态变量在th:each属性中定义，并且包含以下数据：
当前的迭代索引，从0开始。这是index属性。
从1开始的当前迭代索引。这是count属性。
迭代变量中元素的总数。这是size财产。
每次迭代的iter变量。这是current财产。
当前迭代是偶数还是奇数。这些是even/odd布尔属性。
当前迭代是否是第一个。这是first布尔属性。
当前迭代是否为最后一次。这是last布尔属性。
th:switch
th:switch选择语句

```html
<div th:switch="${user.getSex()}">
    <p th:case="'1'">男</p>
    <p th:case="'2'">女</p>
    <p th:case="*">默认</p>
</div>
```

如果在springboot中需要引入static目录下的静态资源可以使用@{xxx}的方式

```html
<link th:href="@{/app.css}" rel="stylesheet">
<!-- JavaScript动态渲染 -->
<script th:inline="javascript">
    const user = /*[[${user}]]*/ {};
    console.log(user);
</script>

```

同理css也是可以的

```html
<style th:inline="css">
    .main\ elems {
      text-align: /*[[${align}]]*/ left;
    }
</style>
```

### 碎片（组件）
日常开发中呢我们经常将有些可以复用的部分抽离出来

新建一个component.html,一个文件里面可以写多个碎片，使用th:fragment来定义

```html
<footer th:fragment="com1">
    this is com1
</footer>
<footer id="com2">
    this is com2
</footer>
```

使用碎片主要有两种方式replace和insert,在index.html中编写

```html
<!--replace-->
<div th:replace="~{component::com1}"></div>
<!--insert-->
<div th:insert="~{component::com1}"></div>
```

    这两种方式的区别就是，replace会将新标签完全替换原本的标签，也就是说原本写th:replace属性的标签就不会渲染出来，insert是往这个地方插入标签



直接通过选择器使用
对于碎片，甚至可以不定义，我们再次添加一个 碎片

```html
<footer id="com2">
    this is com2
</footer>
```

然后使用它

```html
<div th:insert="~{component::#com2}"></div>
```


组件传递参数
组件也是可以传递数据的

```html
<div th:fragment="com3(message)">
    <p th:text="${message}"></p>
</div>
```
使用的时候

```html
<div th:insert="~{component::com3('传递数据')}"></div>
```

局部替换组件
我们使用一个组件的时候，想要局部替换掉这个组件里面的部分内容该怎么做呢？通过传递参数的方式传递一个组件过来，并把这个组件替换原本的一部分

```html
<div th:fragment="com4(message)">
   <p th:replace="${message}">原本的message</p>
</div>
```
使用的时候


```html
<div th:insert="~{component::com4(~{::#message})}">
    <p id="message">替换的message</p>
</div>
```


基本对象

    #ctx：上下文对象
    ${#ctx.request}
    ${#ctx.response}
    ${#ctx.session}
    ${#ctx.servletContext}
    请求/会话属性
    ${session.xxx}                 
    ${application.xxx}          
    ${#request.getAttribute('xxx')}


### 工具类

在thymeleaf里面是可以直接使用一些Java的函数的，并且你可以通过传递参数的方式把一些自己写的方法传递给页面，在里面调用也是可以的

一些可以直接的使用函数

    #dates
    #calendars
    #strings
    #numbers
    #objects
    #bools
    #arrays
    #lists
    #sets
    #maps
    #aggregates


以日期格式化来举例

<!--日期格式化-->
```html
<p th:text="${#dates.format(user.createTime,'yyyy-MM-dd HH:mm')}"></p>
```
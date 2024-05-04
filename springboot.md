


## 典型的Spring Boot项目的树形目录结构：

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── demo
│   │   │               └── DemoApplication.java
│   │   └── resources
│   │       ├── static
│   │       │   └── css
│   │       │       └── style.css
│   │       ├── templates
│   │       │   └── index.html
│   │       ├── application.properties
│   │       └── application.yml
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── demo
│       │               └── DemoApplicationTests.java
│       └── resources
│           └── test.properties
└── pom.xml

```

## 新建springboot项目


1. 新建一个简单的项目


2. 配置pom

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.6</version>
</parent>


<dependences>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
</dependences>

```










## 创建springbootweb项目


[已经创建好的springboot+web](./assets/template_springboot.zip)

### 从网页创建

start.spring.io
快速生成springboot项目
start.aliyun.com
国内







在springboot项目上

1. 添加web依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
2. 创建控制类
```java

/**
 * @Author: zhaoss
 *
 * @Controller 容器创建DemoContrller对象
 * @RequestMapping("/show")  访问该方法的路径
 */
@Controller
public class DemoController {
    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello springboot"; // 响应给浏览器一句话
    }
}
```


ps


~~~yaml
server:
  port: 8888
  servlet:
    context-path: /hello
~~~

其他参考[文档](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.server)



## 整合Lombok

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```






## 整合MyBatis


1. 引入Mybatis 驱动 引入mysql 驱动

~~~xml
<dependencies>

        <!--Mybatis启动器,Mybatis为了整合SpringBoot第三方提供的-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.2</version>
        </dependency>

        <!--MySQL依赖-->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
~~~


```yml
spring:
  datasource:
  	driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/msbsys?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: root
```


mybatis支持注解开发和xml文件开发



## 整合Druid



~~~xml
<!-- 添加druid启动器-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.11</version>
</dependency>
~~~



~~~yaml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  #底层使用druid连接池
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/msbsys?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf8
    username: root
    password: root
    druid:
      # 连接池的配置信息
      initial-size: 5  # 初始化连接数大小
      max-active: 30  # 最大连接数
      min-idle: 5   # 最小连接数
      # 配置获取连接等待超时的时间
      max-wait: 60000  #如果连接都被占用，最大等待时间1min=60000ms
      validation-query: SELECT 1 FROM DUAL  #通过从续表查询来监控是否连接数据库，语句执行能连接，语句不执行没连接
      # 配置一个连接在池中最小空闲的时间，单位是毫秒
      min-evictable-idle-time-millis: 300000
      test-while-idle: true
      # 开启SQL监控、防火墙监控、日志监控
      filters: stat,wall,slf4j
      # 配置DruidStatViewServlet
      stat-view-servlet:
        # 登录名
        login-username: admin
        # 登录密码
        login-password: admin
        url-pattern: /druid/*
        # IP白名单(没有配置或者为空，则允许所有访问)
        allow: 192.167.10.1,127.0.0.1  # 本机和设置的ip可以访问后台管理界面
        reset-enable: false # 不要重置
        # 启用控制台-后台管理界面，必须启用，否则访问界面会404
        enabled: true
~~~

## 整合FreeMarker

1. xml
~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
~~~



Freemarker 默认默认视图路径文 resources/templates 目录由自动化配置类FreemarkerProperties 类决定



该目录可以进行在application.yml 中进行修改：修改application.yml 添加freemarker 基本配置如下: 

2. yml
```yml
spring:
    freemarker:
        suffix: .ftl # 修改后缀
        content-type: text/html   # 此为默认类型，不设置也没有关系
        charset: UTF-8
        template-loader-path: classpath:/views/ # 修改存放目录,不修改默认就是templates下
```


PS：springboot 2.2.0版本以后，freemaker模版引擎默认的模版文件后缀名由**ftl**改变为**ftlh**。若使用ftl将无法正常映射与展示。ftlh，使freemarker默认以html内容转码输出，以利于模板输出的安全。







3. 创建Controller

编写FreemarkerController 控制器转发视图

~~~java
package com.msb.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhaoss
 */

@Controller
public class MyController {
    @RequestMapping("/test1")
    public String test1(HttpServletRequest req){
        req.setAttribute("msg","msb");
        //返回模板文件名称
        return "test1";
    }
}

~~~

4. 创建Freemarker模板

在resources/views目录下创建test1.ftl文件

~~~java
hi test1.ftl <br>
${msg}
~~~
































## 整合logback

Spring Boot默认使用Logback组件作为日志管理（官方推出的）。Logback是由log4j创始人设计的一个开源日志组件。

在Spring Boot项目中我们不需要额外的添加Logback的依赖，因为在spring-boot-starter或者spring-boot-starter-web中已经包含了Logback的依赖。


使用案例：打印sql相关日志。在配置文件application.yml中设置：

```yml
# 不改变日志类型和日志规则 只开启mybatis中对应SQL的日志
logging:
  level:
    com.msb.mapper: trace
```







## 整合Thymeleaf


1. 在pom.xml中添加Thymeleaf启动器

~~~xml
<!--thymeleaf启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
~~~




2. 在`resources/template`s文件夹下新建html页面。

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    hi test01.html
</body>
</html>
```







3. 新建控制器

​	此处方法返回值为页面名称。

~~~java
package com.msb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhaoss
 */
@Controller
public class MyController {
    @RequestMapping("/show")
    public String show(){
        System.out.println("执行show方法");
        return "test01";
    }
}
~~~



源码
```
public class ThymeleafProperties {
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
}
```

























## 整合PageHelper


> 在Service中添加入主要的分页逻辑.

SpringBoot整合PageHelper完成分页，可直接利用上面整合代码进行测试。

PageHelper是一个Mybatis分页插件，官网：https://pagehelper.github.io/

现在SpringBoot整合PageHelper完成分页使用非常简单的（以前搞依赖，配置拦截器）

1. 先在pom.xml中导入启动器：**(SpringBoot3 必须整合  1.4.6以上版本，否则不好使)**

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.6</version>  
</dependency>
```

2. 编写mapper层

接口：

```java
public interface UserMapper {
    List<User> selectAllUsers();
}
```

mapper.xml:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.msb.mapper.UserMapper">


    <select id="selectAllUsers">
        select
        *
        from
        t_user
    </select>

    
</mapper>
```



3. 编写业务层

接口：

```java

public interface UserService {

    /**
     *
     * @param pageNum 页码
     * @param pageSize 每页数据大小
     * @return
     */
    PageInfo<User> findAllUsers(int pageNum, int pageSize);
}

```

实现类：

```java
package com.msb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msb.pojo.User;
import com.msb.service.UserService;
import com.msb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: zhaoss
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public PageInfo<User> findAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.selectAllUsers();
       
        // PageInfo是分页查询所有查询结果封装的类，所有的结果都从这个类取
        // 使用PageInfo包装查询后的结果,只需要将PageInfo交给页面就行。
        PageInfo<User> info = new PageInfo<>(users);

        return info;
    }
}
```

4. 编写控制层

```java
package com.msb.controller;

import com.github.pagehelper.PageInfo;
import com.msb.pojo.User;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhaoss
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

   
    @GetMapping("/user/list")
    @ResponseBody
    public PageInfo<User> selectAll(int pageNum,int pageSize){
        return userService.findAllUsers(pageNum,pageSize);
    }

}
```



## 整合SpringDoc



```xml
<dependency>
     <groupId>org.springdoc</groupId>
     <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
     <version>2.1.0</version>
</dependency>
```


```java
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restfulOpenAPI() {
        Info info = new Info().title("SpringBoot项目")
                .description("用户管理项目")
                .version("1.0.0");
        return new OpenAPI()
                .info(info);
    }
}
```













## DevTools热部署

    热部署原理
​	项目中修改代码后重启的目的：其实就是重新编译生成了新的 Class 文件，这个文件里记录着和代码等对应的各种信息，然后 Class 文件将被虚拟机的 ClassLoader 加载。
​	热部署在原理上是使用了两个 ClassLoader，一个 ClassLoader 加载那些不会改变的类（第三方 Jar 包），另一个ClassLoader 加载会更改的类，称为 restart ClassLoader。它监听到如果有 Class 文件改动了，原来的 restart ClassLoader 被丢弃，重新创建一个 restart ClassLoader，由于需要加载的类相比较少，不需要人工重启服务器，所以实现了较快的重启时间。
​    在Spring Boot项目中 通过配置 DevTools  工具（开发者工具）来达到热部署效果。


#### 配置 DevTools 环境

- 如果是新项目，选中DevTools 依赖

- 不是新项目，就修改 Pom 文件，添加 DevTools 依赖

```xml
<!-- DevTools 的坐标 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <!--项目运行时生效。表示编译时不参与，但是参与项目的测试、打包。该依赖被打包时会被包含。-->
    <scope>runtime</scope>
    <!--设置为true热部署才生效-->
    <optional>true</optional>
</dependency>
```


​	devtools 可以实现**页面热部署**（即页面修改后会立即生效，这个可以直接在 application.properties 文件中配置 spring.thymeleaf.cache=false 来实现），实现**类文件热部署**（类文件修改后不会立即生效），实现对**属性文件的热部署**。即 devtools 会监听 classpath 下的文件变动，并且会立即重启应用（发生在保存时机），注意：因为其采用的虚拟机机制，该项重启是很快的。配置了后在修改 java 文件后也就支持了热启动，不过这种方式是属于项目重启（速度比较快的项目重启），会清空 session 中的值，也就是如果有用户登陆的话，项目重启后需要重新登陆。

​	默认情况下，/META-INF/maven，/META-INF/resources，/resources，/static，/templates，/public 这些文件夹下的文件修改不会使应用重启，但是会重新加载（ devtools 内嵌了一个 LiveReload server，当资源发生改变时，浏览器刷新）



在 application.yml 中配置 spring.devtools.restart.enabled=false，此时 restart 类加载器还会初始化，但不会监视文件更新。

```yml
spring:
  ## 热部署配置
  devtools:
    restart:
      enabled: true  # 开启热部署
      # 设置热启动路径，这个路径下的内容修改后不需要重启，进行热部署。    
      additional-paths: src/main/java
      # 设置的内容为不让热部署去管理，不需要加载 (其实指的就是编译过的文件，因为热部署只管理变化的内容，编译过的没有变化的内容不加载)
      exclude: WEB-INF/**
```




#### IDEA 配置

当我们修改了 Java 类后，IDEA 默认是不自动编译的，而 spring-boot-devtools 又是监测 classpath 下的文件发生变化才会重启应用，所以需要设置 IDEA 的自动编译。

- 自动编译配置
  File -> Settings -> Compiler -> Build Project automatically

- 高级设置- 属性修改-允许auto-make
允许程序在运行的时候自动编译

- 关闭浏览器缓存
  不关闭缓存，浏览器可能还用上次的界面，不是新的内容，找到浏览器，开发者工具（f12）-网络-禁用缓存：


## 事务支持

直接使用注解配置即可，无需xml配置内容。

```java
/**
 * @Author: zhaoss
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteUserById(Integer uid) {
        int num = userMapper.delete(uid);
        int a = 1/0;
        return num;
    }

}

```

启动服务器，测试：
发现，数据库数据被删除，但是后台报错：
所以必须要加入事务的控制：

```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED) //added
    public int deleteUserById(Integer uid) {
        int num = userMapper.delete(uid);
        int a = 1/0;
        return num;
    }

}

```

启动服务器，测试，发现程序报错，数据库数据没有被删除，证明事务加入成功，非常简单的操作！在service层每个增删改方法前都加入@Transactional(propagation = Propagation.REQUIRED)注解即可。


## 异常控制

`template/error`
如果你整合的是thymeleaf就创建error.html，如果你整合的是freemarker你就创建error.ftl,SpringBoot会自动帮你跳转到异常页
面。


`template/error/404`
如果你想不同的异常跳转到不同的页面，可以做如下处理：页面名字设为即将出现错误的状态码


### ExceptionHandler 局部异常处理

这种处理方式属于局部处理方式，只对当前MyController控制单元中的异常生效，所以我们需要加入全局异常处理

```java
@Controller
public class MyController {
    @RequestMapping("/test1")
    @ResponseBody
    public String test01(){
        System.out.println("test1---");
        int a = 1/0;
        return "test01";
    }

    // 出现算术异常，跳转到如下方法解决：
    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
    public String myexceptionhandler(){
        System.out.println("异常处理逻辑代码。。");
        return "myerror";
    }
}
```

### @ControllerAdvice、@ExceptionHandler实现全局异常处理

```java
@ControllerAdvice // 代表当前类为异常处理类
public class GlobalExceptionHandler {
    // 出现算术异常，跳转到如下方法解决：
    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
    public String myexceptionhandler(){
        System.out.println("异常处理逻辑代码。。");
        return "myerror";
    }
}
```





### 配置方式处理

```java
public class GlobalExceptionHandler3 implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView m = new ModelAndView();
        if (ex instanceof ArithmeticException){
            m.setViewName("myerror"); 
        }
        return m;
    }
}

```


## 数据校验


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```





## 整合 拦截器


首先定义拦截器，这个与SpringMVC中的学习是一致的，在com.msb.interceptors中定义拦截器：
注意需要加入@Component注解，代表使用容器管理拦截器对象

```java
@Component
public class DemoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行拦截器");
        return true;
    }
    
    // 省略postHandle、afterCompletion方法，与springmvc中定义一致
}

```

定义好拦截器以后，配置拦截器，在springmvc中是利用xml对拦截器配置进行注册，但是springboot中没有xml配置，怎么处理呢？使用配置类：

```java
@Configuration  // 类上有注解@Configuration,此类相当于SpringMVC配置文件。
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;
    //配置拦截器的映射
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /*InterceptorRegistration ir = registry.addInterceptor(demoInterceptor);
        InterceptorRegistration ir2 = ir.addPathPatterns("/**");  // 设置拦截路径
        InterceptorRegistration ir3 = ir2.excludePathPatterns("/login"); // 设置不拦截url*/
        // 链式调用
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
```

registry.addInterceptor(demoInterceptor) —— 代表注册拦截器

addPathPattern() —— 设置拦截路径，拦截哪些URL，/** 拦截全部

excludePathPatterns() —— 不拦截哪些URL



PS：当excludePathPatterns()和addPathPattern()冲突时，excludePathPatterns()生效。

如下：registry.addInterceptor(demoInterceptor).addPathPatterns("/login").excludePathPatterns("/login");








## 整合 mybatis plus




```xml
<!--web启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!--mysql驱动依赖-->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
<!--lombok依赖-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<!--mybatis-plus启动器-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.3.1</version>
</dependency>
<!-- 添加druid启动器-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.11</version>
</dependency>

<!--单元测试依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```


yml

在application.yml中配置数据源、实体类别名、扫描xml位置

**配置方式与之前整合mybatis一致**，因为mybatis-plus的原则是:只增强不改变

```yml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  #底层使用druid连接池
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/msbsys?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf8
    username: root
    password: root
    druid:
      # 连接池的配置信息
      initial-size: 5  # 初始化连接数大小
      max-active: 30  # 最大连接数
      min-idle: 5   # 最小连接数

      # 配置获取连接等待超时的时间
      max-wait: 60000  #如果连接都被占用，最大等待时间1min=60000ms
      validation-query: SELECT 1 FROM DUAL  #通过从续表查询来监控是否连接数据库，语句执行能连接，语句不执行没连接
      # 配置一个连接在池中最小空闲的时间，单位是毫秒
      min-evictable-idle-time-millis: 300000
      test-while-idle: true
      # 开启SQL监控、防火墙监控、日志监控
      filters: stat,wall,slf4j
      # 配置DruidStatViewServlet
      stat-view-servlet:
        # 登录名
        login-username: admin
        # 登录密码
        login-password: admin
        url-pattern: /druid/*
        # IP白名单(没有配置或者为空，则允许所有访问)
        allow: 192.167.10.1,127.0.0.1  # 本机和设置的ip可以访问后台管理界面
        reset-enable: false # 不要重置
        # 启用控制台-后台管理界面，必须启用，否则访问界面会404
        enabled: true



mybatis:
  #起别名
  type-aliases-package: com.msb.pojo
  #扫描mybatis下所有的xml文件  ---》使用mybatis-plus后可以不使用mapper.xml文件了 ，所以这步可以取消
 # mapper-locations: classpath:mapper/*.xml
```









### 测试

1. 构建实体类


```java

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_user")  // 对应的数据库中表为t_user,如果数据库中表和实体类名字一致，可以不指定
public class User {

    @TableField(exist = false) // 如果数据库表中没有这个字段，这个字段与数据库表字段对不上，加了这个属性，就不会报错
    private Integer a;

    @TableField("uid") // 指定数据库表中字段名字，如果数据库表字段和属性名字一致，可以不指定
    private Integer id;

    private String uname;

    private String pwd;

    private String realname;

    private Integer identity;
}

```

2. 构建mapper层

mapper接口：

```java

/**
 * 继承BaseMapper接口，该接口中定义了很多基础的增删改查的方法，
 * 所以UserMapper中就不需要定义基础的增删改查方法了
 */
public interface UserMapper extends BaseMapper<User> {

}

```

mapper.xml可以不写了





3. 构建service层

service接口：

```java
/**
 * 继承IService接口，该接口中定义了很多基础的增删改查的方法，
 *  * 所以UserService中就不需要定义基础的增删改查方法了
 */
public interface UserService extends IService<User> {
}

```

service实现类：

```java

/**
 * 如果用UserServiceImpl  直接implements UserService会报错
 * 因为要实现全部的抽象方法
 * 那还不如不继承了，太麻烦了呀
 * mybatis-plus同样给出了解决方案，让UserServiceImpl继承ServiceImpl
 * ServiceImpl有两个泛型，其中一个代表你要注入的mapper，另一个代表你这个实现类中操作的那个表对应的实体类
 * 这样UserServiceImpl中一些基本的方法就不用去写了，除非一些特殊的逻辑底层没提供那你就自己加入
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

```

4. 启动类

启动类加入mapper层扫描：

```java

@SpringBootApplication
@MapperScan("com.msb.mapper")  // 加入mapper层的扫描
public class SpringBootM12Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootM12Application.class, args);
    }

}

```

5. 测试

利用单元测试进行测试

```java
@SpringBootTest

class SpringBootM12ApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        // 查询全部USer：
        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user);
        }
    }


}

```





6. 测试增删改查

```java
@SpringBootTest

class SpringBootM12ApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void test01() {
        // 查询全部USer：
        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    void test02() {
        // 带条件的查询：
        // 查询uid>=5的数据
        // QueryWrapper 作用就是在原本的sql上拼接where条件
        // 适用场合：查询、删除、更新
        QueryWrapper<User> q = new QueryWrapper<>();
        q.ge("uid",5);// 条件指定参照使用手册

        List<User> list = userService.list(q); // 将条件传入
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    void test03() {
        // 带条件的查询：
        // 查询uid>=5，uname=fh的数据
        QueryWrapper<User> q = new QueryWrapper<>();
        q.ge("uid", 5).eq("uname","fh");// 多条件追加调用即可

        List<User> list = userService.list(q); // 将条件传入
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    void test04() {
        // 查询单个数据：
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("uname","fh");

        User one = userService.getOne(q);
        System.out.println(one);


    }

    @Test
    void test05() {
        // 增加数据：单个增加用save方法，多个用saveBatch方法
        boolean save = userService.save(new User(1, 111, "a", "a", "a", 1));
        System.out.println(save);
    }

    @Test
    void test06() {
        // 更新数据：
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("uid","111");
        User u = new User(1, 111, "bbb", "bbb", "bb", 2);
        boolean update = userService.update(u, q);//第一个参数为更新的字段，第二个参数为更新的where条件
        System.out.println(update);


    }

    @Test
    void test07() {
        // 删除数据：
        QueryWrapper<User> q = new QueryWrapper<>();
        q.eq("uid","111");
        boolean remove = userService.remove(q);//第一个参数为更新的字段，第二个参数为更新的where条件
        System.out.println(remove);
    }
}

```


7. 分页操作

mybatis-plus自带分页插件，不用像之前一样整合`PageHelper`了。

（1）想要分页插件生效，先进行配置分页插件，配置分页拦截器：

可以修改为如下，可修改、也可以使用官网的

```java
@Configuration
public class PageConfig {
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor p = new PaginationInnerInterceptor();
        p.setOverflow(false);// 设置是否轮回，就是查到最后一条的时候是否需要轮回到第一条
        p.setMaxLimit(500L);// 单页最大数量500
        p.setDbType(DbType.MYSQL);// 设置数据库类型
        interceptor.addInnerInterceptor(p);
        return interceptor;
    }
}

```



（2）测试方法中：

```java
@SpringBootTest

class SpringBootM12ApplicationTests {

    @Autowired
    private UserService userService;


    @Test
    public void testPage(){
        // 分页查询条件：当前页  页大小
        // 带条件的分页加上QueryWrapper
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.lt("uid", "5");
        Page<User> page = userService.page(new Page<>(1, 3), queryWrapper);
        // 分页返回数据：当前页数据  总页数  总记录数  当前页  页大小 ... ..
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("总页数:"+page.getPages());
        System.out.println("总记录数:"+page.getTotal());
        System.out.println("当前页:"+page.getCurrent());
        System.out.println("页大小:"+page.getSize());
    }


}

```





## 配置访问图标favicon

一般favicon图标都是前端进行设置，当然后端也可以完成：



```yml
spring:
    favicon:
      enabled: false
```

















## Mybatis


### 注解开发


MyBatis提供了多种注解来简化SQL映射的配置，常用的注解包括：

```
@Select： 用于执行查询操作。
@Insert： 用于执行插入操作。
@Update： 用于执行更新操作。
@Delete： 用于执行删除操作。
@Results： 用于配置结果集的映射。
@Result： 用于配置单个结果映射。
@Param： 用于传递参数。
```


```java
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 查询用户列表
    @Select("SELECT * FROM user")
    @Results({
        @Result(column="id", property="id"),
        @Result(column="username", property="username"),
        @Result(column="password", property="password")
    })
    List<User> selectAll();

    // 根据ID查询用户
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param("id") Integer id);

    // 插入用户
    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    int insertUser(@Param("username") String username, @Param("password") String password);

    // 更新用户
    @Update("UPDATE user SET username=#{username}, password=#{password} WHERE id=#{id}")
    int updateUser(@Param("id") Integer id, @Param("username") String username, @Param("password") String password);

    // 删除用户
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(@Param("id") Integer id);
}

```



### xml文件开发


```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.msb.mapper.UserMapper">
    <select id="selectAllUsers" resultType="com.msb.pojo.User">
        select * from t_user
    </select>
</mapper>
```

xml文件放在resources目录下,并且配置路径

```yml

mybatis:
  mapper-locations: classpath:mapper/*.xml
```


#### 设置实体类包别名

~~~yaml
mybatis:
  type-aliases-package: com.msb.pojo   #给指定包中的所有类起别名
~~~

resultType中改为别名即可：

```xml
<select id="selectAllUsers" resultType="User">
```



### @Mapper和@MapperScan

接口上加入@Mapper注解，但是这样只会扫描一个接口，或用@MapperScan，扫描整个包。

```
@MapperScan("com.msb.mapper")  // 在启动类上添加注解，表示mapper接口所在位置
```



## Lombok


```

@Getter/@Setter: 作用类上，生成所有成员变量的getter/setter方法；
	作用于成员变量上，生成该成员变量的getter/setter方法。

@ToString: 作用于类，覆盖默认的toString()方法

@EqualsAndHashCode: 作用于类，覆盖默认的equals和hashCode

@NoArgsConstructor：生成无参构造器；

@RequiredArgsConstructor：生成包含final和@NonNull注解的成员变量的构造器；

@AllArgsConstructor：生成全参构造器

@Data: 作用于类上，注解集合，使用它相当于使用下列注解：
	@ToString 
	@EqualsAndHashCode 
	@Getter 
	@Setter 
	@RequiredArgsConstructor

@Builder: 作用于类上，将类转变为建造者模式

@Log: 作用于类上，生成日志变量

```



## thymleaf



上面的案例中我们感觉就是在操作html呀？只是看似是html，实际是在用Thymeleaf，来代替jsp的作用。

Thymeleaf 通过在 html 标签中，增加额外属性来达到“模板+数据”的展示方式，所以在此我们需要学习Thymeleaf的语法。（就类似于在jsp中学习el和jstl等）

在使用Thymeleaf时页面要引入名称空间：

```html
<html xmlns:th="http://www.thymeleaf.org" >
```

代表我们用的是thymeleaf的方式，加入后，即可支持Thymeleaf语法，写thymeleaf的语法会有提示。

#### 常用th属性

##### th:text属性

​	向HTML标签内部输出信息，属性依附于标签，所以先写标签再写属性。（双标签用th:text）

~~~html
<!--直接向标签内部填充内容，清空原有内容 -->
<span th:text="abc"></span>
<!-- 从作用域中获取name输入到标签内部 ,idea中报红色波浪线错误不要紧，只是页面语法不支持-->
<span th:text="${msg1}"></span> 
<span th:text="${msg4.id}"></span> 
<span th:text="${msg4.name}"></span> 
~~~

#####  th:value

​	表单元素，设置HTML标签中表单元素value属性时使用。（单标签用th:value）

~~~html
<input type="text" th:value="${msg2}"/>
~~~

#####  th:if

​	进行逻辑判断。如果成立该标签生效（显示），如果不成立，此标签无效（不显示）。

​	注意：判断条件中逻辑判断符号写在${}外面的

~~~xml
<span th:if="${msg3}>40">会显示</span>
~~~

#####  th:each

​	循环遍历. 示例中u为迭代遍历。

1. th:each=”u,i :${list}” 其中i表示迭代状态。i用的少，一般都是：th:each=”u:${list}”

2. index:当前迭代器的索引 从0开始

3. count:当前迭代对象的计数 从1开始

4. size:被迭代对象的长度

5. even/odd:布尔值，当前循环是否是偶数/奇数 从0开始

6. first:布尔值，当前循环的是否是第一条，如果是返回true否则返回false

7. last:布尔值，当前循环的是否是最后一条，如果是则返回true否则返回false

   


~~~html
<table border="1" width="500">
    <tr>
        <td>编号</td>
        <td>姓名</td>
    </tr>
    <tr th:each="u,i : ${msg5}">
        <td th:text="${u.id}" ></td>
        <td th:text="${u.name}"></td>
        <td th:text="${i.size}"></td> <!--集合长度-->
        <td th:text="${i.count}"></td><!--计数-->
        <td th:text="${i.index}"></td><!--索引-->
    </tr>
</table>
~~~

##### th:href

​	设置href属性的。使用@{} ,show2代表的请求的地址

~~~html
<!-- 多个参数就用逗号分开：@{/show2(id=1,name='lili')}-->
<a th:href="@{/show2(id=1)}" >跳转</a>  
<!-- 获取作用域值-->
<a th:href="@{/show2(id=${msg4.id})}">跳转2</a>
~~~

##### th:onclick  

​	点击链接执行js函数：

~~~html
<!-- href=”javascript:void(0);”的含义 即让超链接去执行一个js函数,而不是去跳转到一个地址-->
<th><a href="javascript:void(0);"  th:onclick="'del('+${msg4.id}+')'">删除</a></th>

<script type="text/javascript">
	function del(id){
		alert(id)
	}
</script>
~~~



案例中后台控制层代码：

```java
package com.msb.controller;

import com.msb.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaoss
 */
@Controller
public class MyController {
    @RequestMapping("/show")
    public String show(){
        return "test01";
    }

    @RequestMapping("/show2")
    public String show2(HttpServletRequest req){
        req.setAttribute("msg1","msb");
        req.setAttribute("msg2","yjx");
        req.setAttribute("msg3",50);
        req.setAttribute("msg4",new User(18,"zs"));
        List<User> list = new ArrayList<>();
        list.add(new User(11,"lili"));
        list.add(new User(22,"lulu"));
        list.add(new User(33,"feifei"));
        req.setAttribute("msg5",list);


        return "test01";
    }


    @RequestMapping("/show3")
    public String show3(int id){
        System.out.println("-------show3方法的参数id为：-------" + id);
        return "XXXX";
    }

}
```

案例中thymeleaf的test01.html:

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  hi...test01.html<br>
  <span th:text="abc"></span><br>
  <span th:text="${msg1}"></span><br>
  <span th:text="${msg4.id}"></span><br>
  <span th:text="${msg4.name}"></span><br>
  <input type="text" th:value="${msg2}"><br>
  <span th:if="${msg3}>=30">男生</span><br><br>
  <span th:if="${msg3}<30">女生</span><br><br>

  <table border="1" width="500">
      <tr>
          <td>编号</td>
          <td>姓名</td>
          <td>集合中元素大小</td>
          <td>计数</td>
          <td>索引</td>
      </tr>
      <tr th:each="u,i : ${msg5}">
          <td th:text="${u.id}"></td>
          <td th:text="${u.name}"></td>
          <td th:text="${i.size}"></td>
          <td th:text="${i.count}"></td>
          <td th:text="${i.index}"></td>
      </tr>
  </table>

  <a th:href="@{/show3(id=30)}">跳转</a>
  <a th:href="@{/show3(id=${msg3})}">跳转2</a>

  <a href="javascript:void(0);" th:onclick="'del(' + ${msg3} + ')'">跳转3</a>


<script type="text/javascript">
    function del(id){
        alert(id)
    }
</script>

</body>
</html>
```

#### 内置对象

​	Thymeleaf提供了一些内置对象，内置对象可直接在模板中使用。这些对象是以#引用的。

1. 引用内置对象需要使用#
2. 大部分内置对象的名称都以s结尾。如：strings、numbers、dates

##### 使用内置对象进行字符串操作

其中msg为从作用域中取的key

| ${#strings.isEmpty(key)}                                     |
| ------------------------------------------------------------ |
| 判断字符串是否为空，如果为空返回true，否则返回false          |
| ${#strings.contains(msg,'T')}                                |
| 判断字符串是否包含指定的子串，如果包含返回true，否则返回false |
| ${#strings.startsWith(msg,'a')}                              |
| 判断当前字符串是否以子串开头，如果是返回true，否则返回false  |
| ${#strings.endsWith(msg,'a')}                                |
| 判断当前字符串是否以子串结尾，如果是返回true，否则返回false  |
| ${#strings.length(msg)}                                      |
| 返回字符串的长度                                             |
| ${#strings.indexOf(msg,'h')}                                 |
| 查找子串的位置，并返回该子串的下标，如果没找到则返回-1       |
| ${#strings.substring(msg, 0, 3)}                             |
| 截取字串。                                                   |



##### 日期格式化处理

| ${#dates.format(key)}                                     |
| --------------------------------------------------------- |
| 格式化日期，默认的以浏览器默认语言为格式化标准            |
| ${#dates.format(key,'yyyy/MM/dd')}                        |
| 按照自定义的格式做日期转换                                |
| ${#dates.year(key)}${#dates.month(key)}${#dates.day(key)} |
| Year：取年。   Month：取月。   Day：取日                  |





案例中后台控制层代码：

```java
package com.msb.controller;

import com.msb.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @Author: zhaoss
 */
@Controller
public class MyController {

    @RequestMapping("/show4")
    public String show4(){
        req.setAttribute("msg","msb");
        req.setAttribute("date",new Date());
        return "test02";
    }

}
```



案例中thymeleaf的test02.html:

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  this is test02.html...<br>
  <span th:text="${#strings.length(msg)}" ></span><br>
  <span th:text="${date}"></span><br>
  <span th:text="${#dates.format(date)}"></span><br>
  <span th:text="${#dates.format(date,'yyyy-MM-dd')}"></span><br>
  <span th:text="${#dates.year(date)}"></span><br>
  <span th:text="${#dates.month(date)}"></span><br>
  <span th:text="${#dates.day(date)}"></span><br>


</body>
</html>
```



#### 操作域对象

##### request域-HttpServletRequest

```java
request.setAttribute("req", "HttpServletRequest");
```

```html
<span th:text="${req}"></span><br/>
```

##### session域-HttpSession

```java
request.getSession().setAttribute("ses", "HttpSession");
```

```html
<span th:text="${session.ses}"></span><br/>
```



##### application域-ServletContext

```java
request.getSession().getServletContext().setAttribute("app", "Application");
```

```html
<span th:text="${application.app}"></span><br/>
```



案例中控制层代码：

```java
package com.msb.controller;

import com.msb.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhaoss
 */
@Controller
public class MyController {
    
    @RequestMapping("/show5")
    public String show5(HttpServletRequest req){
        // 将数据存入request作用域
        req.setAttribute("req","aaa");
        // 将数据存入session作用域
        req.getSession().setAttribute("ses","bbb");
        // 将数据存入application作用域
        req.getSession().getServletContext().setAttribute("app","ccc");

        return "test03";
    }

}
```

案例中thymele的test03.html:

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <span th:text="${req}"></span><br>
  <span th:text="${session.ses}"></span><br>
  <span th:text="${application.app}"></span><br>

</body>
</html>
```




## FreeMaker





### 数据类型

在Freemarker 模板中我们可以进行数据的输出，那么Freemarker 模板中有几种数据类型呢？并且如何进行输出呢？

Freemarker 模板中的数据类型由如下几种：

- 布尔型：等价于 Java 的 boolean 类型，不同的是不能直接输出，可**转换为字符串输出**

- 日期型：等价于 java 的 Date 类型，不同的是不能直接输出，需要**转换成字符串再输出**

- 数值型：等价于 java 中的 int,float,double 等数值类型

  可以转换为：数值型(默认)、货币型、百分比型的形式

- 字符型：等价于 java 中的字符串，有很多内置函数

- sequence（序列 ）类型：等价于 java 中的数组，List，Set 等集合类型

- hash 类型：等价于 java 中的 Map 类型

PS：案例是在Servlet中准备数据模型。

#### 布尔类型

1. 在Servlet中设置布尔类型的数据

   ```java
   package com.msb.controller;
   
   import javax.servlet.ServletException;
   import javax.servlet.annotation.WebServlet;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   
   /**
    * @Author: zhaoss
    */
   @WebServlet("/fm02")
   public class FMServlet02 extends HttpServlet {
       @Override
       protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           // 存储布尔类型在request作用域中：
           req.setAttribute("flag",true);
           // 请求转发到模板2：
           req.getRequestDispatcher("f02.ftl").forward(req,resp);
       }
   }
   
   ```

2. 模板中处理：

   ```F#
   <#--
       数据类型：布尔类型
           在freemarker中布尔类型不能直接输出；如果输出要先转成字符串
           方式一：?c
           方式二：?string 或 ?string("true时的文本","false时的文本")   -->等价三元运算符，从 FreeMarker 2.3.23 版本开始废弃：使用 ?then("yes", "no") 来替代。
           方式三：?then("true时的文本", "false时的文本")}  -->then替代string的用法
   -->
   ${flag} <br>   <#--在freemarker中布尔类型不能直接输出，直接在页面报错-->
   ${flag?c} <br>
   ${flag?string} <br>
   ${flag?string("yes","no")} <br>
   ${flag?then("yes","no")} <br>
   ```

具体使用可以去在线手册中查找使用：

![](01-FreeMarker.assets/freemarker-14.png)

#### 日期类型

1. 在Servlet中设置日期类型的数据

   ```java
   // 日期类型
   request.setAttribute("createDate",new Date());
   ```

2. 获取数据

   ```F#
   <#--
       数据类型：日期类型
           在freemarker中日期类型不能直接输出；如果输出要先转成日期型或字符串
           1. 年月日          ?date
           2. 时分秒          ?time
           3. 年月日时分秒     ?datetime
           4. 指定格式        ?string("自定义格式")
                              y：年  M：月  d：日
                              H：时  m：分  s：秒
   -->
   <#-- 输出日期格式 -->
   ${createDate?date} <br>  
   <#-- 输出时间格式 -->
   ${createDate?time} <br> 
   <#-- 输出日期时间格式 -->
   ${createDate?datetime} <br>   
   <#-- 输出格式化日期格式 -->
   ${createDate?string("yyyy年MM月dd日 HH:mm:ss")} <br>   
   ```



#### 数值类型

1. 在Servlet设置数值型的数据

   ```java
   // 数值类型
   request.setAttribute("age",18); // 数值型
   request.setAttribute("salary",10000); // 数值型
   request.setAttribute("avg",0.545); // 浮点型
   ```

2. 获取数据

   ```F#
   <#--
       数据类型：数值类型
           在freemarker中数值类型可以直接输出；
           1. 转字符串
               普通字符串       ?c
               货币型字符串     ?string.currency
               百分比型字符串   ?string.percent   自动进行四舍五入
           2. 保留浮点型数值指定小数位（#表示一个小数位）
               ?string["0.##"]   自动进行四舍五入
   -->
   <#-- 直接输出数值型 -->
   ${age} <br>
   ${salary} <br>
   ${avg} <br>
   <#-- 将数值转换成字符串输出 -->
   ${salary?c} <br>
   <#-- 将数值转换成货币类型的字符串输出 -->
   ${salary?string.currency} <br>
   <#-- 将数值转换成百分比类型的字符串输出 -->
   ${avg?string.percent} <br>
   <#-- 将浮点型数值保留指定小数位输出 （##表示保留两位小数） -->
   ${avg?string["0.##"]} <br>
   ```



#### 字符串类型

##### 字符串类型显示

1. 在Servlet中设置字符串类型的数据

   ```java
   // 字符串类型
   request.setAttribute("msg"," Hello2023 ");
   request.setAttribute("msg2","freemarker");
   ```

2. 获取数据

   ```F#
   <#--
       数据类型：字符串类型
           在freemarker中字符串类型可以直接输出；
           可以使用内置函数进行操作：
           1. 截取字符串（左闭右开） ?substring(start,end)
           2. 首字母小写输出  ?uncap_first
           3. 首字母大写输出  ?cap_first
           4. 字母转小写输出  ?lower_case
           5. 字母转大写输出  ?upper_case
           6. 获取字符串长度  ?length
           7. 是否以指定字符开头（boolean类型） ?starts_with("xx")，返回布尔类型，不能直接输出，需要加?string
           							最终写法：?starts_with("xx")?string
           8. 是否以指定字符结尾（boolean类型） ?ends_with("xx")?string
           9. 获取指定字符的索引  ?index_of("xx")
           10. 去除字符串前后空格 ?trim
           11. 替换指定字符串  ?replace("xx","xx")
   -->
   <#-- 直接输出 -->
   ${msg} - ${msg2} <br>
   ${msg?string} - ${msg2?string} <br>
   <#-- 1. 截取字符串（左闭右开） ?substring(start,end) -->
   ${msg2?substring(1,4)} <br>
   <#-- 2. 首字母小写输出  ?uncap_first -->
   ${msg?uncap_first} <br>
   <#-- 3. 首字母大写输出  ?cap_first -->
   ${msg2?cap_first} <br>
   <#-- 4. 字母转小写输出  ?lower_case -->
   ${msg?lower_case} <br>
   <#-- 5. 字母转大写输出  ?upper_case -->
   ${msg?upper_case} <br>
   <#-- 6. 获取字符串长度  ?length -->
   ${msg?length} <br>
   <#-- 7. 是否以指定字符开头（boolean类型） ?starts_with("xx")?string -->
   ${msg?starts_with("H")?string} <br>
   <#-- 8. 是否以指定字符结尾（boolean类型） ?ends_with("xx")?string -->
   ${msg?ends_with("h")?string} <br>
   <#-- 9. 获取指定字符的索引  ?index_of("xxx") -->
   ${msg?index_of("e")} <br>
   <#-- 10. 去除字符串前后空格 ?trim -->
   ${msg?trim?length} <br>
   <#-- 11. 替换指定字符串  ?replace("xx","xxx") -->
   ${msg?replace("o","a")}<br>
   ```

##### **字符串空值情况处理**

​	FreeMarker 的变量必须赋值，否则就会抛出异常。null 值和不存在的变量是完全一样的，因为 FreeMarker 无法理解 null 值。

​	FreeMarker 提供两个运算符来避免空值：（避免null 值和不存在的变量，否则报错影响用户体验）

​		① !  ：指定缺失变量的默认值

​			${value!}：如果value值为空，则默认值是空字符串

​			${value!"默认值"}：如果value值为空，则默认值是字符串"默认值"

​		② ?? ：判断变量是否存在	

​			如果变量存在，返回 true，否则返回 false

​			${(value??)?string}

Servlet中：

```
/*对字符串的空值情况进行处理*/
req.setAttribute("a",null);
req.setAttribute("b","");
```



模板中：

```F#

<#--${a} 如果a是null，那么输出直接报错-->
${b}<#--如果b是空字符串，那么可以直接输出，不会报错--> <br>
<#--${c}如果数据不存在，那么输出直接报错-->

${c!}<#-- 使用!，当值不存在时，默认显示空字符串 -->
${c!"yes"}<#-- 使用!"xx"，当值不存在时，默认显示指定字符串 -->
<#-- 使用??，判断字符串是否为空；返回布尔类型。如果想要输出，需要将布尔类型转换成字符串 -->
${c???string}

```



#### sequence 类型

1. 在Servlet中设置序列类型的数据

   ```java
   // 序列类型 （数组、List、Set）
   // 数组操作
   String[] stars = new String[]{"周杰伦","林俊杰","陈奕迅","五月天"};
   request.setAttribute("stars",stars);
   
   // List操作
   List<String> citys = Arrays.asList("上海","北京","杭州","深圳");
   request.setAttribute("cityList",citys);
   
   // JavaBean集合
   List<User> userList = new ArrayList<>();
   userList.add(new User(1,"zhangsan",22));
   userList.add(new User(2,"lisi",18));
   userList.add(new User(3,"wangwu",20));
   request.setAttribute("userList",userList);
   ```

2. 获取数据

   ```F#
   <#--
       数据类型：序列类型 （数组、List、Set）
           通过list指令输出序列
               <#list 序列名 as 元素名>
                   ${名称}
               </#list>
           获取序列的长度        ${序列名?size}
           获取序列元素的下标     ${元素名?index}
           获取第一个元素        ${序列名?first}
           获取最后一个元素      ${序列名?last}
   
           倒序输出    	 序列名?reverse
           升序输出    	 序列名?sort
           降序输出    	 序列名?sort?reverse
           指定字段名排序    序列名?sort_by("字段名")
              注：一般是JavaBean集合，对应的字段名需要提供get方法
              
              下面的案例以数组为案例，list和set同理
   -->
   <hr>
   <h1>序列类型的处理：</h1>
   <#--${strarr}数组不能直接进行操作，报错-->
   <#--通过list指令输出序列-->
   <#list strarr as ele>
       元素：${ele} -- 索引：${ele?index}  <br>
   </#list>
   <hr>
   获取序列的长度：${strarr?size}<br>
   获取序列的第一个元素：${strarr?first}<br>
   获取序列的最后一个元素：${strarr?last}<br>
   <hr>
   倒叙输出序列：
   <#list strarr?reverse as ele>
       元素：${ele} -- 索引：${ele?index}  <br>
   </#list>
   <hr>
   升序输出：
   <#list strarr?sort as ele>
       元素：${ele} -- 索引：${ele?index}  <br>
   </#list>
   <hr>
   降序输出：
   <#list strarr?sort?reverse as ele>
       元素：${ele} -- 索引：${ele?index}  <br>
   </#list>
   
   <hr>
   
   对User数组按照年龄进行排序：
   <br>
   <#list userarr as user>
       ${user.age} ---${user.name}-- 索引：${user?index}  <br>
   </#list>
   <hr>
   <#list userarr?sort_by("age") as user>
       ${user.age} ---${user.name}-- 索引：${user?index}  <br>
   </#list>
   ```



#### hash 类型

1. 在Servlet中设置hash类型的数据

   ```java
   // Map操作
   Map<String,String> cityMap = new HashMap<>();
   cityMap.put("sh","上海");
   cityMap.put("bj","北京");
   cityMap.put("sz","深圳");
   request.setAttribute("cityMap",cityMap);
   ```

2. 获取数据

   ```F#
   <#--
        数据类型：hash类型
        通过list指令输出序列
            key遍历输出
               <#list hash?keys as key>
                   ${key} -- ${hash[key]}
               </#list>
            value遍历输出
               <#list hash?values as value>
                   ${value}
               </#list>
   -->
   <#-- key遍历输出 -->
   <#list cityMap?keys as key>
       ${key} -- ${cityMap[key]} <br>
   </#list>
   
   <#-- value遍历输出 -->
   <#list cityMap?values as value>
       ${value} |
   </#list>
   ```



#### FreeMarker 常见指令

FreeMarker有很多指令：

<img src="01-FreeMarker.assets/freemarker-15.png" style="zoom:67%;" />

我们学习一些常用指令。

#### assign 自定义变量指令

​	有时候我们需要在页面中定义数据使用，那么就需要assign指令。

​	使用 `assign` 指令你可以创建一个新的变量， 或者替换一个已经存在的变量。

```F#
<#--
    assign 自定义变量指令
        语法：
            <#assign 变量名=值>
            <#assign 变量名=值 变量名=值> （定义多个变量）
-->
<#assign str="hello">
${str} <br>
<#assign num=1 names=["zhangsan","lisi","wangwu"] >
${num} -- ${names?join(",")}
```



#### if  elseif  else 逻辑判断指令

​	可以使用 `if`， `elseif` 和 `else` 指令来条件判断是否满足某些条件。

```F#
<#--
    if, else, elseif 逻辑判断指令
        格式：
            <#if condition>
                  ...
                <#elseif condition2>
                  ...
                <#elseif condition3>
                  ...
                <#else>
                  ...
            </#if>
        注：
            1. condition, condition2等：将被计算成布尔值的表达式。
            2. elseif 和 else 指令 是可选的。
-->
<#assign score=72>
<#if score lt 60>
    不及格！
    <#elseif score == 60>
        正好及格！
    <#elseif score gt 60 && score lt 80>
        良好！
    <#else >
        优秀！
</#if>
<#if b??>
    数据存在
    <#else >
        数据不存在
</#if>


<#--
(1)如果只使用if指令，就是单分支
(2)如果使用if指令和elseif指令和else指令，就是多分支
(3)如果使用if指令和else指令，就是双分支
-->
```



#### list 遍历指令 

​	可以使用 `list ` 指令来对序列进行遍历。

```F#
<#--
    list指令
        格式1：
            <#list sequence as item>

            </#list>
        格式2：
            <#list sequence as item>

            <#else>
                当没有选项时，执行else指令
            </#list>
        注：
            1. else 部分是可选的
            2. sequence： 想要迭代的项，可以是序列或集合的表达式
            3. item： 循环变量 的名称
            4. 当没有迭代项时，才使用 else 指令， 可以输出一些特殊的内容而不只是空在那里
-->
<#assign users = ["张三","李四","王五"]>
<#-- 遍历序列 -->
<#list users as user>
    ${user}
</#list>

<br>
<#--判断数据不为空，再执行遍历 （如果序列不存在，直接遍历会报错）-->
<#if users2??>
    <#list users2 as user>
        ${user}
    </#list>
</#if>
<br>

<#assign users3 = []>
<#-- 当序列没有数据项时，使用默认信息 -->
<#list users3 as user>
    ${user}
    <#else>
        当前没有数据！
</#list>
```



#### macro 自定义指令   (也称为宏)

​	当有一部分模板片段被反复使用的时候，可以利用宏变量存储模板片段，也叫自定义指令。

​	可以使用 `macro ` 指令来自定义一些自定义指令。

```F#
<#--
    macro 自定义指令 （宏）
        1. 基本使用
            格式：
                <#macro 指令名>
                    指令内容
                </#macro>
            使用：
                <@指令名></@指令名>
        2. 有参数的自定义指令
            格式：
                 <#macro 指令名 参数名1 参数名2>
                    指令内容
                </#macro>
            使用：
                <@指令名 参数名1=参数值1 参数名2=参数值2></@指令名>

        注：
            1. 指令可以被多次使用。
            2. 自定义指令中可以包含字符串，也可包含内置指令
-->
<#-- 定义基本的自定义指令 
将模板片段放入定义的macro的标签内部，需要调用的话，就用@指令去调用即可
address为宏变量，存储模板片段，也叫自定义指令
使用的感受：就像定义方法和调用方法
作用：复用
-->
<#macro address>
    © 1999–2015 The FreeMarker Project. All rights reserved.
</#macro>
<#macro address2>
    <h2>© 1999–2015 The FreeMarker Project. All rights reserved.</h2>
</#macro>
<#-- 使用指令 -->
<@address></@address> <br>
<@address2></@address2>
<hr>

<#-- 定义有参数的自定义指令 -->
<#macro queryUserByName uname>
    通过用户名查询用户信息 - ${uname}
</#macro>
<#-- 使用指令，并传递参数 -->
<@queryUserByName uname="admin"></@queryUserByName> <br>

<#-- 定义有多个参数的自定义指令 -->
<#macro queryUserByParams uname uage>
    通过多个餐宿查询用户信息 - ${uname} - ${uage}
</#macro>
<#-- 使用指令，并传递多个参数 -->
<@queryUserByParams uname="admin" uage=18></@queryUserByParams> <br>
<hr>

<#-- 自定义指令中包含内置指令 -->
<#macro cfb>
    <#list 1..9 as i>
        <#list 1..i as j>
            ${j}*${i}=${j*i}&nbsp;
        </#list>
        <br>
    </#list>
</#macro>
<@cfb></@cfb>
<@cfb></@cfb>

<#-- 动态数据 -->
<#macro cfb2 num>
    <#list 1..num as i>
        <#list 1..i as j>
            ${j}*${i}=${j*i}&nbsp;
        </#list>
        <br>
    </#list>
</#macro>
<@cfb2 num=5></@cfb2>
```



#### nested 占位指令

​	`nested` 指令执行自定义指令开始和结束标签中间的模板片段。嵌套的片段可以包含模板中任意合法的内容。

```F#
<#--
    nested 占位指令
        nested 相当于占位符,一般结合macro指令一起使用。
        可以将自定义指令中的内容通过nested指令占位，当使用自定义指令时，会将占位内容显示。
-->
<#macro g>
    京ICP备11041704号 | ICP | 互联网药品信息服务资格证编号(京)-经
    公司：<#nested >
</#macro>

<@g>京东</@g>
<@g>阿里</@g>
```



#### import 导入指令

​	一般在使用宏变量的时候，都是单独提取到一个模板中，其余模板想要使用的话就通过import指令去引入即可。

​	`import` 指令可以引入一个库。也就是说，它创建一个新的命名空间， 然后在那个命名空间中执行给定路径的模板。可以使用引入的空间中的指令。

commons.ftl

```F#
<#macro cfb>
    <#list 1..9 as i>
        <#list 1..i as j>
            ${j}*${i}=${j*i}&nbsp;
        </#list>
        <br>
    </#list>
</#macro>
```

在其他ftl页面中通过import导入commons.ftl的命名空间，使用该命名空间中的指令

test.ftl

```F#
<#-- 导入命名空间 -->
<#import "commons.ftl" as common>
<#-- 使用命名空间中的指令 -->
<@common.cfb></@common.cfb>
```



#### include 包含指令

​	作用：在模板中包含另一个模板（可以是html、ftl、txt等）

​	可以使用 `include` 指令在你的模板中插入另外一个 FreeMarker 模板文件 。 被包含模板的输出格式是在 `include` 标签出现的位置插入的。 被包含的文件和包含它的模板共享变量，就像是被复制粘贴进去的一样。

```F#
<#--包含指令(引入其他页面文件) include-->
<#--html文件-->
<#include "test.html"> 

<#--freemarker文件-->
<#include "test.ftl"> 

<#--text文件-->
<#include "test.txt"> 
```



### FreeMarker 页面静态化

> 页面静态化应用场景

静态化处理：通过freemarker将数据库内容静态化到html页面中，以后访问的话，include这个html页面就好了，数据就在已经静态化在html页面中了，就不用每次都去访问数据库了，避免了与数据库的交互。
比如：
（1）京东网站某一个商品介绍，这个商品介绍录入在数据库以后，变化频率不高，也没有必要访问一次就去数据库查询一次。而且这种数据量也大，并不是少量数据。访问html肯定也要比访问数据库快。
（2）新闻网站的新闻，新闻经过编辑发布好以后，存于数据库，基本不会改变，那么就可以将数据库数据静态化到html页面中，以后每次访问就是访问html即可。
那么如何将数据库的数据**静态化到html页面**中呢？



1. 定义模板

创建Maven普通项目（quickstart）,创建resources目录，将模板放入该目录下：


2. 加载模板

```java
public class Test {
    public static void main(String[] args) throws IOException, TemplateException {
        // 实例化模板配置对象
        Configuration configuration = new Configuration();
        // 设置加载模板的上下文 以及 设置加载模板路径（模板存放的路径）
        configuration.setDirectoryForTemplateLoading(new File("D:\\offline_workspace\\untitled\\src\\main\\resources"));
        // 设置模板的编码格式
        configuration.setDefaultEncoding("UTF-8");
        // 加载模板文件，获取模板对象
        Template template = configuration.getTemplate("f.ftl");

        // 设置模型数据 ：此处就是模拟数据库中的数据，实际开发中这个数据应该从数据库中查询
        Map<String,Object> map = new HashMap<>();
        map.put("title", "特别就业季：稳就业情况如何? 哪些问题待解?");
        map.put("source", "人民日报");
        map.put("pubTime", new Date());
        map.put("content", "中共中央政治局常务委员会近日召开会议强调，" +
                "要有针对性地开展援企、稳岗、扩就业工作，" +
                "做好高校毕业生、农民工等重点群体就业工作，" +
                "积极帮助个体工商户纾困。疫情期间，稳就业情况如何？还有哪些问题待解？" +
                "记者采访了不同群体，记录这个特别的就业季。");


        // 设置页面存放的目录
        File htmlFile = new File("D:/");

        // 获取文件名（随机生成不重复的文件名）
        String fileName = System.currentTimeMillis() + ".html";
        // 创建html文件
        File file = new File(htmlFile, fileName);
        // 获取文件输出流
        FileWriter writer = new FileWriter(file);

        // 输出html  将模型数据填充到模板中
        template.process(map, writer);
        // 输出成功
        System.out.println("新闻创建成功！");

    }
}

```

3. 生成对应的html文件








### FreeMarker 运算符

#### 算术运算符

```F#
<!--
    算术运算
        +、-、*、/、%
-->
<#assign a1 = 8 a2 = 2 >
${a1} + ${a2} = ${a1 + a2} <br/>
${a1} - ${a2} = ${a1 - a2} <br/>
${a1} * ${a2} = ${a1 * a2} <br/>
${a1} / ${a2} = ${a1 / a2} <br/>
${a1} % ${a2} = ${a1 % a2} <br/>
<!--字符串运算-->
${"hello" + "," + "freemarker"}
```

#### 逻辑运算符

```F#
<#--
    逻辑运算符
     &&、||、!
-->
```

#### 比较运算符

```F#
<#--
    比较运算符
        > (gt): 大于号，推荐使用 gt
        < (lt)： 小于号，推荐使用 lt
        >= (gte): 大于等于， 推荐是用 gte
        <= (lte): 小于等于，推荐使用 lte
        == ： 等于
        != : 不等于
-->
```

#### 空值运算符

```F#
<#--
    空值运算符
     1. ??:判断是否为空，返回布尔类型
        如果不为空返回 false， 如果为空返回 true，不能直接输出
        ${(name??)?string}
     2. !: 设置默认值，如果为空，则设置默认值
         1. 设置默认为空字符串：
         ${name!}
         2. 设置指定默认值
         ${name!'zhangsan'}
-->
```












## SpringDoc

### SpringDoc常用注解说明

利用注解标注在接口的方法上，给方法添加说明信息。SpringDoc会借助相应的注解来生成对应的帮助文档。因为你自己写后端，每个方法什么意思你比较清楚，即便是很简单的方法，前端人员是不清晰的。

#### @Tag

```java
@Tag：作用-用在请求的类上，说明该类的作用
    配置参数name="该类对应的模块名字 "tags="说明该类的作用，对模块进行描述"
```

```java
@Tag(name = "用户模块管理", description = "用户接口")
```

#### @Operation

```csharp
@Operation：作用-用在请求的方法上，说明方法的作用
    配置参数summary="说明方法的作用"
```

```java
@Operation(summary="用户模块-根据用户id查询用户信息")
```

#### @Parameters

```dart
@Parameters：作用-用在请求的方法上，对请求的参数（方法的形参）进行说明，是一个复合注解，里面包含了多个@Parameter子注解。
    @Parameter：用在 @Parameters 注解中，指定一个请求参数的配置信息   
    	注解的属性介绍：（配置参数）
        name：参数名
        value：参数的汉字说明、解释
        required：参数是否必须传
```

如果方法是一个参数，可以直接使用@Parameter注解：

```java
@Parameter(name="uid",description ="查询参数用户id",required = true)
public User select(@PathVariable Integer uid){
	return userService.findOneUser(uid);
}
```

如果多个参数，可以使用@Parameters，如：

```java
@Parameters({
    @Parameter(name="mobile",description="手机号",required=true),
    @Parameter(name="password",description="密码",required=true),
    @Parameter(name="age",description="年龄",required=true)
})
```



#### @ApiResponses

```dart
@ApiResponses：用于请求的方法上，表示一组响应，，是一个复合注解，里面包含了多个@ApiResponse子注解。
    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
        code：数字，例如400
        message：信息，例如"请求参数没填好"
        response：抛出异常的类
```

```css
@ApiResponses({
    @ApiResponse(code=400, message="请求参数没填好"),
    @ApiResponse(code=404, message="请求路径没有或页面跳转路径不对")
})
```

#### @Schema

用在模型类上,对模型类做注释; 用在属性上,对属性做注释 ;

```dart
package com.msb.pojo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhaoss
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "用户实体类")
public class User {
    @Schema(description = "用户id主键")
    private Integer uid;
    @Schema(description =  "用户名字")
    private String uname;
    @Schema(description =  "账户密码")
    private String pwd;
    @Schema(description =  "真实名字")
    private String realname;
    @Schema(description = "身份标识")
    private Integer identity;
}

```



### 用户模块注解配置

注解的使用案例：

#### 在Controller 上使用注解

```java
@Controller
@Tag(name = "用户模块管理", description = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{uid}")
    @ResponseBody
    @Operation(summary="用户模块-根据用户id查询用户信息")
    @Parameter(name="uid",description ="查询参数用户id",required = true)
    public User select(@PathVariable Integer uid){
        return userService.findOneUser(uid);
    }

    @PostMapping("/user")
    @Parameter(name = "user",description = "用户信息",required = true)
    @ResponseBody
    @Operation(summary="用户模块-保存用户信息")
    public int save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/user")
    @ResponseBody
    @Operation(summary="用户模块-更新用户信息")
    public int update(@RequestBody User user){
        return userService.update(user);
    }
    @DeleteMapping("/user/{uid}")
    @ResponseBody
    @Operation(summary="用户模块-根据用户id删除用户信息")
    public int delete(@PathVariable Integer uid){
        return userService.deleteUserById(uid);
    }

    @GetMapping("/user/list")
    @ResponseBody
    @Operation(summary="用户模块-查询用户信息用来分页")
    public PageInfo<User> selectAll(int pageNum,int pageSize){
        return userService.findAllUsers(pageNum,pageSize);
    }

}

```

#### 在JavaBean上 使用注解

- User.java

```java
package com.msb.pojo;

/*import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;*/
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhaoss
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "用户实体类")
public class User {
    @Schema(description = "用户id主键")
    private Integer uid;
    @Schema(description =  "用户名字")
    private String uname;
    @Schema(description =  "账户密码")
    private String pwd;
    @Schema(description =  "真实名字")
    private String realname;
    @Schema(description = "身份标识")
    private Integer identity;
}

```



###  接口文档访问

启动工程，浏览器访问 ：

swagger 文档形式访问在以下 URL 中提供：http://localhost:8080/swagger-ui/index.html。

json 格式的 OpenAPI 3.0.1 描述将在以下 URL 中提供： http://localhost:8080/v3/api-docs 。

yaml 格式在以下 URL 中提供： http://localhost:8080/v3/api-docs.yaml。

对于 HTML 格式的 swagger 文档的自定义路径，在 config 文件中添加自定义 springdoc 属性：

```
# swagger-ui custom path
springdoc.swagger-ui.path=/swagger-ui.html
```



我们一般使用：http://localhost:8080/swagger-ui/index.html，查看生成的接口文档，查看文档说明信息：









## validation校验

| 注解         | 功能                                                         |
| :----------- | :----------------------------------------------------------- |
| @AssertFalse | 可以为null,如果不为null的话必须为false                       |
| @AssertTrue  | 可以为null,如果不为null的话必须为true                        |
| @DecimalMax  | 设置不能超过最大值                                           |
| @DecimalMin  | 设置不能超过最小值                                           |
| @Digits      | 设置必须是数字且数字整数的位数和小数的位数必须在指定范围内   |
| @Future      | 日期必须在当前日期的未来                                     |
| @Past        | 日期必须在当前日期的过去                                     |
| @Max         | 最大不得超过此最大值                                         |
| @Min         | 最大不得小于此最小值                                         |
| @NotNull     | 不能为null，可以是空                                         |
| @Min         | 最大不得小于此最小值                                         |
| @Pattern     | 必须满足指定的正则表达式                                     |
| @Size        | 集合、数组、map等的size()值必须在指定范围内                  |
| @Email       | 必须是email格式                                              |
| @Length      | 长度必须在指定范围内                                         |
| @NotBlank    | 字符串不能为null,字符串trim()后也不能等于“”                  |
| @NotEmpty    | 不能为null，集合、数组、map等size()不能为0；字符串trim()后可以等于“” |
| @Range       | 值必须在指定范围内                                           |
| @URL         | 必须是一个URL                                                |

### 校验注解的使用

- User 实体类参数校验注解

  如对用户进行删除或者更新操作的时候，对用户的属性值进行一些基本校验：

```java
public class User{
  
    @NotBlank(message = "用户名不能为空!")
    private String userName;

    @NotBlank(message = "用户密码不能为空!")
    @Length(min = 6, max = 10,message = "密码长度至少6位但不超过10位!")
    private String userPwd;
    
    /*
      省略get set 方法  
    */
}
```

校验注解配置好了，如何在校验环境生效呢？需要结合下面的接口方法：

- 接口方法形参 @Valid 注解添加

  @Valid 代表对当前客户端提交的表单参数进行校验

```java
package com.msb.controller;

import com.msb.pojo.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhaoss
 */
@RestController
public class MyController {
    @PostMapping("/user")
    public int save(@RequestBody @Valid User user){
        System.out.println("进入save方法");
        // 这里有访问service层的代码，省略...模拟return 0 ，主要为了测试参数部分的校验
        return 0;
    }

}

```


## 拦截器



首先定义拦截器，这个与SpringMVC中的学习是一致的，在com.msb.interceptors中定义拦截器：
注意需要加入@Component注解，代表使用容器管理拦截器对象

```java
@Component
public class DemoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行拦截器");
        return true;
    }
    
    // 省略postHandle、afterCompletion方法，与springmvc中定义一致
}

```

定义好拦截器以后，配置拦截器，在springmvc中是利用xml对拦截器配置进行注册，但是springboot中没有xml配置，怎么处理呢？使用配置类：

```java
@Configuration  // 类上有注解@Configuration,此类相当于SpringMVC配置文件。
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;
    //配置拦截器的映射
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /*InterceptorRegistration ir = registry.addInterceptor(demoInterceptor);
        InterceptorRegistration ir2 = ir.addPathPatterns("/**");  // 设置拦截路径
        InterceptorRegistration ir3 = ir2.excludePathPatterns("/login"); // 设置不拦截url*/
        // 链式调用
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
```

registry.addInterceptor(demoInterceptor) —— 代表注册拦截器

addPathPattern() —— 设置拦截路径，拦截哪些URL，/** 拦截全部

excludePathPatterns() —— 不拦截哪些URL



PS：当excludePathPatterns()和addPathPattern()冲突时，excludePathPatterns()生效。

如下：registry.addInterceptor(demoInterceptor).addPathPatterns("/login").excludePathPatterns("/login");







### 拦截器应用案例


**案例：非登录状态下无法访问静态资源。**

创建登录页面login.html:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="login">
        <input type="text" name="uname">
        <input type="password" name="pwd">
        <input type="submit" value="登录">
    </form>
</body>
</html>
```

点击登录按钮，跳转到login控制单元： com.msb.controller.MyController:

```java
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(String uname, String pwd, HttpServletRequest req){
        System.out.println("---" + uname + "---" + pwd);
        if ("lili".equals(uname) && "123123".equals(pwd)){
            // 登录成功在session作用域中存入数据：
            req.getSession().setAttribute("uname",uname);
            // 登录成功跳转到主页面：
            return "main";
        }
       return "redirect:/login.html";
    }
}

```

登录成功，跳转如main.html中：resources/templates/main.html:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
这是主页
</body>
</html>
```



启动服务器，测试，发现 ：http://localhost:8080/a.html 、  http://localhost:8080/login.html都可以直接访问，对于a.html来说，应该是登录后才可以访问，怎么处理呢？使用拦截器：

加入拦截器类：com.msb.interceptors.LoginInterceptor:

```java
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果登录过，就放行：
        if (request.getSession().getAttribute("uname") != null){
            return true;
        }
        // 如果没有登录过，先进行登录：(重定向)
        response.sendRedirect("login.html");
        return false; // 不放行
    }
}

```

注册拦截器：com.msb.config.MyConfig:

```java
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    //配置拦截器的映射
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有路径
        // 但是对：login.html放行，向后台提交login的时候放行。
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/login.html");//链式调用
    }
}

```

测试：在没有登录的情况下直接访问：http://localhost:8080/a.html 不可以，会直接跳转到：  http://localhost:8080/login.html。案例成功。







但是新的问题又出现了，如果在login.html中有其他的静态资源：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="login">
        <input type="text" name="uname">
        <input type="password" name="pwd">
        <input type="submit" value="登录">
    </form>

<img src="images/a.png">
</body>
</html>
```



那么访问login.html的时候：


因为你请求login.html,login.html中有a.png这个静态资源，拦截器中处理的是这个静态资源拦截会又跳转到login.html，进入login.html中又会请求a.png.....反复......

那怎么处理呢？在没登录的情况下，(登录界面的 //HACK)图片资源、js资源、css资源应该放行，所以在拦截器中加入静态资源的放行：


```java
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    //配置拦截器的映射
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有路径
        // 但是对：login.html放行，向后台提交login的时候放行。
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/login.html","/css/**","/js/**","/images/**");//链式调用
    }
}

```




## Bean管理



之前在异常处理中我们使用了利用配置类来完成全局异常处理的方式：

```java
@Configuration
public class GlobalExceptionHandler2 {
    @Bean
    public SimpleMappingExceptionResolver getSME(){
        SimpleMappingExceptionResolver sme = new SimpleMappingExceptionResolver();
        Properties p = new Properties();
        p.put("java.lang.ArithmeticException","myerror");
        sme.setExceptionMappings(p);
        return sme;
    }
}
```

利用@Configuration、@Bean注解来完成。因为Spring Boot 中没有XML文件，所以所有的Bean管理都放入在一个配置类中实现。
配置类就是类上具有@Configuration的类。这个类就相当于之前的applicationContext.xml。

现在我们就重点讲一下这个bean管理，通过案例来感受，我们构建一个Student类，然后构建Student类对象：

**在com.msb.pojo下构建Student类：**

```java
package com.msb.pojo;

/**
 * @Author: zhaoss
 */
public class Student {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

```

Springboot 所以所有的Bean管理都放入在一个配置类中实现：

**创建包com.msb.config，在下面定义配置类：MyConfig:**

```java
package com.msb.config;

import com.msb.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhaoss
 */
@Configuration  // 加入这个注解，代表当前为配置类，可以替代xml
public class MyConfig {
    // 相当于以前的bean标签,s相当于以前的id
    // 如果括号中s没有写，那么id相当于方法名：getStudent
    @Bean("s")
    public Student getStudent(){
        Student s = new Student();
        s.setId(1);
        s.setName("zs");  //这个过程就相当于之前给对象注入属性的过程
        return s;
    }

}

```

利用单元测试，测试s对象：

```java
package com.msb;

import com.msb.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootM10ApplicationTests {
    /*
     将对象注入
     先按照类型注入，如果该类型对象有多个再按照名字
     */
    @Autowired
    private Student stu;

    @Test
    void contextLoads() {
        System.out.println(stu);
    }

}

```


我们用这种方式，一般都是创建Spring框架封装的对象，如异常案例中的：SimpleMappingExceptionResolver。


### 注入高级   @Qualifier("s2") 

@Autowired的注入：先按照类型注入，如果该类型对象有多个再按照名字**，那么问题来了，如果真的有多个类型相同的对象呢？**

比如：

```java
@Configuration 
public class MyConfig {
    @Bean("s")
    public Student getStudent(){
        Student s = new Student();
        s.setId(1);
        s.setName("zs");  
        return s;
    }

    @Bean("s2")
    public Student getStudent2(){
        Student s = new Student();
        s.setId(2);
        s.setName("ls");  
        return s;
    }

}

```

那么测试类中如何写呢？

```java
@SpringBootTest
class SpringBootM10ApplicationTests {
    @Autowired
    @Qualifier("s2")  // Spring容器中存在同类型的Bean通过Bean的名称获取到Bean对象,需要@Autowired、@Qualifier结合使用
    private Student stu;

    @Test
    void contextLoads() {
        System.out.println(stu);
    }
}

```

## Errors






### jdk和springboot版本问题

Caused by: org.springframework.core.NestedIOException: ASM ClassReader failed to parse class file - probably due to a new Java class file version that isn't supported yet: file [C:\Users\kali\Desktop\java\day08-HTTP&Tomcat&Servlet\untitled3\target\classes\com\lgh\test\MainApplication.class]; nested exception is java.lang.IllegalArgumentException: Unsupported class file major version 62
	at org.springframework.core.type.classreading.SimpleMetadataReader.<init>(SimpleMetadataReader.java:57) ~[spring-core-5.1.7.RELEASE.jar:5.1.7.RELEASE]



### jdk和springboot版本问题

引起java.lang.ExceptionInInitializerError 错误的原因是：在类的初始化时，出错。也就是说，在加载类时，执行static的属性、方法块时，出错了。

或者是引入的某个东西的版本不对，因为springboot引入的部分以来会自己配置，比如你的jdk版本过高








## ---------------------

## store


### springBoot底层原理

之前对于SpringBoot的学习，就是停留在应用阶段，那么SpringBoot底层原理是什么样子呢？一起来揭晓。

之前我们就学习过Spring Boot的核心，你从之前的应用阶段也感受到了：

​		**起步依赖**- 即启动器，起步依赖本质上是一个Maven项目对象模型（Project Object Model，POM），定义了对其他库的传递依赖，这些东西加在一起即支持某项功能。 简单的说，起步依赖就是将具备某种功能的坐标打包到一起，并提供一些默认的功能。

​		**自动配置** -Spring Boot的自动配置是一个运行时（更准确地说，是应用程序启动时）的过程，考虑了众多因素，才决定 Spring配置应该用哪个，不该用哪个。该过程是Spring自动完成的。  

所以起步依赖和自动配置就是我们要研究的两个方向。

##依赖机制管理

先进行思考：

#### （1）为什么我们进行web开发，导入web启动器后，所有相关的依赖都导入进来了？

web启动器：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

对应依赖：

<img src="02-SpringBoot.assets/SpringBoot73.png" style="zoom:67%;" />

答案：

点入<artifactId>spring-boot-starter-web</artifactId>，发现其中有个<dependencies>标签，内部导入了非常多的依赖，这些依赖中有的也是启动器，有的是依赖jar包。依赖的启动器又依赖了很多jar包。

所以只要导入一个场景启动器，这个场景启动器会自动的把它用到的所有的功能场景相关的依赖全部导入。按照maven的依赖传递原则，A依赖B，B依赖C，那么A就依赖了B和C。这样以后的开发会变得非常简单，你想做什么场景，就导入什么场景启动器就可以了。



#### （2）为什么在导入依赖的时候，版本号不用写？

比如：

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
```

答案：

因为我们的springboot项目，都有一个父项目spring-boot-starter-parent：

```xml
   <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```

点击进入：<artifactId>spring-boot-starter-parent</artifactId>发现这个父项目还有父项目spring-boot-dependencies：

```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>3.1.2</version>
  </parent>
```

点击进入：<artifactId>spring-boot-dependencies</artifactId>发现其中有个标签<properties>，内部可以看到很多技术对应的版本信息，这些技术的版本信息已经内置规定好了，这些版本信息都是精挑细选的，如果将来某一天你用到这些技术，这些版本之间是一定不会造成冲突的，相当于springboot在定义3.1.2版本的时候把其他大部分技术对应的版本都定义好了，都是经过测试的，我们使用的时候放心大胆的去用就行了，如果人家没有给我们规定好，让我们自己去选择版本的话，很可能就造成冲突了。

往下看还有<dependencyManagement>标签，叫做版本锁定/依赖管理，一般在父工程中定义一些版本的信息，这样子工程继承父工程的时候，就无需指定版本信息了，比如后续我们在项目中要是引入spring-boot-starter-web就无需指定版本信息了，因为已经内置好了：（下面图片摘自源码）


换句话说就是版本号其实已经在父项目中进行管理了！父项目可以称为版本仲裁中心。

但是如果你觉得父项目中给你提供的版本不好用，你想用自己指定版本的依赖，那么就利用maven的就近原则，在pom.xml中指定自己的版本即可。比如我想修改mysql驱动的依赖，父项目中提供的版本号为：


可以在你自己项目的pom.xml中直接指定你需要的版本即可：






如果是第三方的jar包，父项目没有进行管理的，那么版本号就需要自行声明，比如我们用过的druid连接池的依赖：（坐标自己去maven仓库中找）

```xml
<!-- 添加druid启动器-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.11</version>
</dependency>
```

#### 自动配置机制

SpringBoot自动配置完成流程是什么样子的呢？

**（一）创建项目，导入启动器。比如导入web启动器：**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

1. 场景启动器帮我们导入了相关场景的所有依赖，比如：spring-boot-starter-json、spring-boot-starter-tomcat、spring-web、spring-webmvc。

2. 除了上面的依赖之外，还导入了一个spring-boot-starter，你会发现所有的启动器都导入了spring-boot-starter，这个spring-boot-starter是核心启动器，是启动器的启动器。

3. 点入spring-boot-starter，发现其中有一个依赖spring-boot-autoconfigure，自动配置的包。这个包你可以看一下：springboot把各种场景的功能对应的自动配置都放在这里了（比如整合web的配置，整合thymeleaf的配置、整合es的配置、整合redis的配置等等全都在里面），里面囊括了所有场景的所有配置。




4. 只要spring-boot-autoconfigure包下的所有类都能生效，那么相当于springboot的官方写好的整合功能就生效。
5. 但是SpringBoot默认扫描不到spring-boot-autoconfigure包下写好的配置类，SpringBoot默认只扫描主程序所在的包和子包中的注解。启动类在启动时会**做注解扫描**(@Controller、@Service、@Repository......)，扫描位置为同包或者子包下的注解。

------》截止到这里，相当于spring-boot-autoconfigure包下所有自动配置类引入项目中了，至于是否生效，要看下面第（二）步。

**（二）创建主程序，主程序上使用@SpringBootApplication注解**

主程序中有一个@SpringBootApplication注解，点入@SpringBootApplication注解发现，该注解上面还有三个非常重要的注解：

```
@Configuration（@SpringBootConfiguration点开查看发现里面还是应用了@Configuration）
@EnableAutoConfiguration
@ComponentScan
```

​	即 @SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。

​	所以，如果我们使用如下的SpringBoot启动类，整个SpringBoot应用依然可以与之前的启动类功能对等：

```
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {// 启动类
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

这些注解的作用是什么呢？

1. @SpringBootConfiguration点进去发现该注解上有个@Configuration注解，所以就相当于主程序是一个Spring Ioc容器的配置类

2. @ComponentScan的功能其实就是自动扫描并加载符合条件的组件（比如@Component和@Repository等）或者bean定义，最终将这些bean定义加载到IoC容器中。我们可以通过@ComponentScan的basePackages等属性来细粒度的定制@ComponentScan自动扫描的范围，如果不指定，则默认Spring框架实现会从声明@ComponentScan所在类的package进行扫描。这也是为什么我们的启动类一般放在com.msb下，因为会从com.msb及其子包扫描。
3. @EnableAutoConfiguration：该注解是开启springboot自动配置的核心。如何开启的自动配置呢？

点入EnableAutoConfiguration注解，发现上面还有一个注解Import：

```java
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
    ...
}
```

@Import注解的作用是：给容器中放入指定类型的组件。AutoConfigurationImportSelector这个类比较特殊，可以实现批量导入组件，这个类中有个方法：该方法可以获取自动配置的集合，看看都可以帮我们批量导入哪些组件。


```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).getCandidates();
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
```



getCandidateConfigurations这个方法就是获取候选的配置，这些配置是从哪里来的呢？从：META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports这个文件中来，这个文件在哪呢？


这个文件中列举了springboot项目启动需要导入到容器中的所有类，一共142个类，名字都叫XXXConfiguration。

@EnableAutoConfiguration是借助@Import的帮助，将所有符合自动配置条件的bean定义加载到IoC容器。帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。

这样就解决了启动类只会会从com.msb及其子包扫描的问题了，解决了SpringBoot默认扫描不到spring-boot-autoconfigure包下写好的配置类的问题，这样所有的组件都在容器中了。



虽然导入了142个自动配置类，但是这些类不一定生效，随便打开一个类看看：比如AopAutoConfiguration类：


按住ctrl按键点击Advice发现报错：cannot find declaration to go to ,点不进去

Advice不存在，那么这个配置不生效的。这个类属于AOP场景下的包，所以只有你真正导入了aop场景的包才会生效。

**所以：这142个自动配置类，会按需生效。**通过条件注解@ConditionalOnXXXXXX来完成。







又比如：web依赖相关的自动配置类：DispatcherServletAutoConfiguration，


按住ctrl按键点击点击DispatcherServlet，发现可以进去，因为我们项目中导入了webmvc的包，所以底层可以使用到。

类路径下存在DispatcherServlet，那么这个配置类就生效，结合@Bean注解给容器中放入一堆组件，那这些组件就能工作。






又比如：tomcat的自动配置类：嵌入式web服务器工厂类：EmbeddedWebServerFactoryCustomizerAutoConfiguration


只要你导包有tomcat的场景，就有Tomcat这个类，只要有这个类，就会给容器放入一个tomcat服务器的工厂定制化器TomcatWebServerFactoryCustomizer，这个定制化器中要用一个东西：serverProperties



这个是什么呢？可以看看开头的注解：@EnableConfigurationProperties({ServerProperties.class})


很多自动配置类都有这个@EnableConfigurationProperties注解，这个注解代表：开启这个类和配置文件的绑定，也就是配置文件中的所有属性都封装到ServerProperties类中了，点击ServerProperties类可以看到：


这样就可以把配置文件中server.port的属性值封装到ServerProperties这个属性类中，

@EnableConfigurationProperties注解除了开启这个类和配置文件的绑定外，还会把这个ServerProperties对象放入容器中。



再看一个与服务器有关的自动配置类：ServletWebServerFactoryAutoConfiguration

发现：也有@EnableConfigurationProperties({ServerProperties.class})这个配置：


再看下面方法：


所以@Bean放入ServletWebServerFactoryCustomizer组件的时候，会要求传入ServerProperties，这个参数容器中有，会从容器中取。这样ServletWebServerFactoryCustomizer就会拥有serverProperties对象，点入ServletWebServerFactoryCustomizer：


这样底层调用这个属性就可以获取到tomcat的端口号了，可以调用各种封装的信息了，都是来源于ServerProperties封装的文件。

**所以给容器中放入的组件的核心参数，其实都是来源于XXXXProperties，XXXXProperties适合配置文件绑定的！**

只需要该配置文件中的值，这样核心组件的底层参数都能修改了！

**（三）写业务，全程无需关心各种整合。（无需我们关心，因为底层帮我们完成了整合）**








### Bean注入

再加深难度，如果现在有**学生类，班级类**：

班级类：

```java
package com.msb.pojo;

/**
 * @Author: zhaoss
 */
public class Clazz {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clazz() {
    }

    public Clazz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

```



学生类：

```java
package com.msb.pojo;

/**
 * @Author: zhaoss
 */
public class Student {
    private int id;
    private String name;
    private Clazz c;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clazz getC() {
        return c;
    }

    public void setC(Clazz c) {
        this.c = c;
    }

    public Student(int id, String name, Clazz c) {
        this.id = id;
        this.name = name;
        this.c = c;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", c=" + c +
                '}';
    }
}

```

现在就需要创建学生对象的同时，指定学生所在的班级，所以需要构建班级对象，然后注入给学生：

配置类：

```java
package com.msb.config;

import com.msb.pojo.Clazz;
import com.msb.pojo.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhaoss
 */
@Configuration  
public class MyConfig {

    @Bean("s2")
    public Student getStudent2(Clazz c){// 在参数中加入Clazz c,通过参数传递的形式完成注入，会优先找容器中同类型的Clazz对象，如果多个Clazz对象，可以使用：(@Qualifier("cla") Clazz c),这个位置不需要使用@AutoWire注解，因为在这里默认就是利用参数注入对象
        Student s = new Student();
        s.setId(2);
        s.setName("ls");  
        //s.setC(getCla());// 这种调用方法的形式也可以，但不是注入的形式
        s.setC(c);
        return s;
    }

    @Bean("cla")
    public Clazz getCla(){
        Clazz c = new Clazz();
        c.setId(1);
        c.setName("java406班");
        return c;
    }
    
    @Bean("cla2")
    public Clazz getCla(){
        Clazz c = new Clazz();
        c.setId(1001);
        c.setName("java班级");
        return c;
    }

}

```

测试类：

```java
package com.msb;

import com.msb.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootM10ApplicationTests {
    @Autowired
    @Qualifier("s2") 
    private Student stu;

    @Test
    void contextLoads() {
        System.out.println(stu);
    }

}

```

所以以后你可能会在**配置类的方法**中见到很多参数，你没有传递，但是这个参数就有值了，那一定是因为SpringBoot底层已经帮你把各种对象创建好了，帮你注入了，你拿着这个参数直接使用即可。（这种注入只在配置类中有效，在别处不可以通过参数注入对象）







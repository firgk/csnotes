## SpringBoot的优点

简单快速
内嵌web容器
自动配置

创建项目`maven`方式


```xml
<!--引入SpringBoot依赖-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.3.RELEASE</version>
</parent>
```

```xml
<!-- 选择的Web模块依赖启动器 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```




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


`@RestController = @Controller + @ResponseBody`
@Controller 将当前修饰的类注入SpringBoot IOC容器
@ResponseBody 以json字符串形式返回数据




```
# 更改项目的端口号
server.port=8081
```



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


端口占用问题: 

windows
```
1. 首先用netstat -ano | find “端口号”查出进程号
2. tasklist 查询当前的进行
3. 如何杀死进程呢  tasklist /pid ${xx}
```


## 单元测试


```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
</dependency>
```

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



# 第2章Spring Boot核心配置与注解

[配置属性参考文档](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)



## yaml

使用key： value格式，注意要有空格

    value的值为数组和单列集合

```
person:
  hobby:
    - play
    - read
    - sleep 
或
person:
  hobby: [play,read,sleep]
```

value的值为Map集合或对象

```
person:
  map:
    k1: v1
    k2: v2
或
person:
    map: {k1: v1,k2: v2}
```







## 如何注入属性:

1. 引入依赖
2. 类上注解@ConConfigurationProperties 或者 也支持其他的比如@Compent的注入
3. 类的属性中声明 @Value



例子:

1. 添加application.yaml文件

```
#对实体类对象Person进行属性配置
person:
 id: 3
 name: 张三
 hobby: [sing,read,sleep]
 family: [father,mother]
 map: {k1: v1, k2: v2}
 pet: {type: cat,name: tom}
```

2. 运行测试类的方法

1.创建Student类

```
@Component
public class Student {
    @Value("${person.id}")
    private int id;
    @Value("${person.name}")
    private String name;
    private List hobby;
    private String[] family;
    private Map map;
    private Pet pet;

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

    public List getHobby() {
        return hobby;
    }

    public void setHobby(List hobby) {
        this.hobby = hobby;
    }

    public String[] getFamily() {
        return family;
    }

    public void setFamily(String[] family) {
        this.family = family;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby=" + hobby +
                ", family=" + Arrays.toString(family) +
                ", map=" + map +
                ", pet=" + pet +
                '}';
    }
}
```

3. 测试

```
@Autowired
    private Student student;
    @Test
    public void testStudent(){
        System.out.println(student);

    }
```




### 使用@Configuration编写自定义配置类

1. 在现有的项目基础上新建一个类MyConfig，使用`@Configuration`注解将该类声明一个配置类。

```
@Configuration
public class MyConfig {
    @Bean
    public MyService myService(){
        return new MyService();
    }
}
```

2. 在项目启动类上添加的`@ImportResource`注解注释，执行项目测试类中的测试方法iocTest()






## 数据访问


### 6.使用注解方式整合MyBatis
```java
@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM t_comment WHERE id =#{id}")
    public Comment findById(Integer id);

    @Insert("INSERT INTO t_comment(content,author,a_id) " + 
            "values (#{content},#{author},#{aId})")
    public int insertComment(Comment comment);

    @Update("UPDATE t_comment SET content=#{content} WHERE id=#{id}")
    public int updateComment(Comment comment);

    @Delete("DELETE FROM t_comment WHERE id=#{id}")
    public int deleteComment(Integer id);
}
```

@MapperScan
注意：如果编写的Mapper接口过多时，每一个接口文件都需要添加@Mapper注解，为避免这种麻烦，可以直接在Spring Boot项目启动类上添加@MapperScan（“XXXX”）注解






## 视图技术

Spring Boot框架为简化项目的整体开发，对一些常用的视图技术实现了整合支持，并主要推荐整合模板引擎技术来实现前端页面的动态化内容。



## MVC
## 4.2 Thymaleaf 基本语法







# 第5章 Spring Boot实现Web的常用功能


在Web开发中，会涉及到静态资源的访问支持、视图解析器的配置、转换器和格式化器的定制、文件上传下载等功能，甚至还需要考虑到与Web服务器关联的Servlet相关组件的定制，Spring Boot框架支持整合一些常用Web框架从而实现Web开发，并默认支持Web开发中的一些通用功能。

## 5.1Spring MVC 的整合支持

### 1.Spring MVC 自动配置介绍

在Spring Boot项目中，一旦引入了Web依赖启动器spring-boot-starter-web，那么Spring Boot整合Spring MVC框架默认实现的一些XxxAutoConfiguration自动配置类就会自动生效，几乎可以在无任何额外配置的情况下进行Web开发。

**Spring Boot整合Spring MVC 的自动化配置功能特性**

- 内置了两个视图解析器：ContentNegotiatingViewResolver和BeanNameViewResolver；
- 支持静态资源以及WebJars；
- 自动注册了转换器和格式化器；
- 支持Http消息转换器；
- 自动注册了消息代码解析器；
- 支持静态项目首页index.html；
- 支持定制应用图标favicon.ico；
- 自动初始化Web数据绑定器ConfigurableWebBindingInitializer。




## Spring MVC 功能拓展实现


功能拓展实现


1. 

注册视图管理器，创建一个实现WebMvcConfigurer接口的配置类MyMVCconfig，用于对MVC框架功能扩展

```java
@Configuration
public class MyMVCconfig implements WebMvcConfigurer {
    // 添加视图管理
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 请求toLoginPage映射路径或者login.html页面都会自动映射到login.html页面
        registry.addViewController("/toLoginPage").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
    }
}
```



2. 

注册自定义拦截器MyInterceptor，实现HandlerInterceptor 接口，在该类中编写如下方法

```java
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 用户请求/admin开头路径时，判断用户是否登录
        String uri = request.getRequestURI();
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (uri.startsWith("/admin") && null == loginUser) {
            response.sendRedirect("/toLoginPage");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        request.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse
            response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
```

然后在自定义配置类MyMVCconfig中，重写addInterceptors方法注册自定义拦截器

```java
@Autowired
    private MyInterceptor myInterceptor;
    // 添加拦截器管理
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html");
    }
```





## Spring Boot 整合Servlet三大组件

使用组件注册方式整合Servlet


```java
@Component
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.getWriter().write("hello MyServlet"); //输出hello world
    }
}
```


2）创建Servlet组件配置类，在config包下创建ServletConfig类

```java
@Configuration
public class ServletConfig {
    // 注册Servlet组件
     @Bean
     public ServletRegistrationBean getServlet(MyServlet myServlet){
        ServletRegistrationBean registrationBean =
                 new ServletRegistrationBean(myServlet,"/myServlet");
         return registrationBean;
     }
}
```



## 使用组件注册方式整合Filter


在servletComponent包下创建一个自定义Servlet类MyFilter，使用@Component注解将当前MyFilter类作为组件注入到Spring容器中。MyFilter类实现Filter接口，并重写了init()、doFilter()和destroy()方法，在doFilter()方法中向控制台打印了“hello MyFilter”字符串。

```java
@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("hello MyFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
```

在ServletConfig类向Servlet组件配置类注册自定义Filter类

```java
 // 注册Filter组件
    @Bean
     public FilterRegistrationBean getFilter(MyFilter filter){
         FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setUrlPatterns(Arrays.asList("/toLoginPage","/myFilter"));
         return registrationBean;
     }
```


## 使用组件注册方式整合Listener

在servletComponent包下创建一个类MyListener。

```java
@Component
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized ...");
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed ...");
    }
}
```

在ServletConfig类向Servlet组件配置类注册自定义Listener类

```java
//注册Listener组件
    @Bean
    public ServletListenerRegistrationBean getServletListener(MyListener myListener){
        ServletListenerRegistrationBean registrationBean =
                new ServletListenerRegistrationBean(myListener);
        return registrationBean;
    }
```






## 路径扫描整合Servlet三大组件

使用路径扫描的方式整合Servlet、Filter、Listener
在对应组件上分别使用@WebServlet(“/annotationServlet”)注解来映射“/annotationServlet”请求的Servlet类，
使用@WebFilter(value = {“/antionLogin”,“/antionMyFilter”})注解来映射“/antionLogin”和“/antionMyFilter”请求的Filter类，
使用@WebListener注解来标注Listener类。



**注意注释掉ServletConfig类**，**自定义三大组件的@Component注释掉**

```
@WebServlet("/annotationServlet")
public class MyServlet extends HttpServlet {
...
```

```
@WebFilter(value = {"/antionLogin","/antionMyFilter"})
public class MyFilter implements Filter {
...
```

```
@WebListener
public class MyListener implements ServletContextListener {
...
```

主程序启动类上添加@ServletComponentScan注解开启基于注解方式的Servlet组件扫描支持

```
@SpringBootApplication
@ServletComponentScan  // 开启基于注解方式的Servlet组件扫描支持
public class Demo05Application {
...
```





## 文件上传与下载

### 上传

1. 编写文件上传的表单页面

创建上传文件的upload.html页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>动态添加文件上传列表</title>
    <link th:href="@{/login/css/bootstrap.min.css}" rel="stylesheet">
    <script th:src="@{/login/js/jquery.min.js}"></script>
</head>
<body>
<div th:if="${uploadStatus}" style="color: red" th:text="${uploadStatus}">上传成功</div>
<form th:action="@{/uploadFile}" method="post" enctype="multipart/form-data">
    上传文件:&nbsp;&nbsp;<input type="button" value="添加文件" onclick="add()"/>
    <div id="file" style="margin-top: 10px;" th:value="文件上传区域">  </div>
    <input id="submit" type="submit" value="上传"
           style="display: none;margin-top: 10px;"/>
</form>
<script type="text/javascript">
    // 动态添加上传按钮
    function add(){
        var innerdiv = "<div>";
        innerdiv += "<input type='file' name='fileUpload' required='required'>" +
            "<input type='button' value='删除' onclick='remove(this)'>";
        innerdiv +="</div>";
        $("#file").append(innerdiv);
        // 打开上传按钮
        $("#submit").css("display","block");
    }
    // 删除当前行<div>
    function remove(obj) {
        $(obj).parent().remove();
        if($("#file div").length ==0){
            $("#submit").css("display","none");
        }
    }
</script>
</body>
</html>
```

2. 在全局配置文件中添加文件上传的相关配置

在全局配置文件application.properties中添加文件上传功能的相关设置

```yml
# thymeleaf页面缓存设置（默认为true）
spring.thymeleaf.cache=false
# 单个上传文件大小限制（默认1MB）
spring.servlet.multipart.max-file-size=10MB
# 总上传文件大小限制（默认10MB）
spring.servlet.multipart.max-request-size=50MB
```

3. 进行文件上传处理实现文件上传功能

在controller包下创建一个管理文件上传下载的控制类FileController

```java
@Controller
public class FileController {
    // 向文件上传页面跳转
    @GetMapping("/toUpload")
    public String toUpload(){
        return "upload";
    }
    // 文件上传管理
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile[] fileUpload, Model model) {
        // 默认文件上传成功，并返回状态信息
        model.addAttribute("uploadStatus", "上传成功！");
        for (MultipartFile file : fileUpload) {
            // 获取文件名以及后缀名
            String fileName = file.getOriginalFilename();
            // 重新生成文件名（根据具体情况生成对应文件名）
            fileName = UUID.randomUUID()+"_"+fileName;
            // 指定上传文件本地存储目录，不存在需要提前创建
            String dirPath = "F:/file/";
            File filePath = new File(dirPath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            try {
                file.transferTo(new File(dirPath+fileName));
            } catch (Exception e) {
                e.printStackTrace();
                // 上传失败，返回失败信息
                model.addAttribute("uploadStatus","上传失败： "+e.getMessage());
            }
        }
        // 携带上传状态信息回调到文件上传页面
        return "upload";
    }
}
```

`toUpload()方法处理路径为“/toUpload”的GET请求， 向文件上传页面upload.html跳转；uploadFile()方法处理路径为“/uploadFile”的POST请求，对上传文件进行处理。文件上传处理过程中，对文件名进行重命名并存放在“F:/file/”目录下，并封装了返回结果。其中，处理上传文件的请求方法中，使用了“MultipartFile[] fileUpload”参数处理单个或多个上传文件（也可以使用单列集合参数），fileUpload参数名必须与upload.html页面中上传文件<input>框中的name属性值一致。`



## 文件下载

1. 添加文件下载工具依赖
pom.xml文件中这引入文件下载的一个工具类依赖commons-io

```xml
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
</dependency>
```

2. 定制文件下载页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>文件下载</title>
</head>
<body>
<div style="margin-bottom: 10px">文件下载列表：</div>
<table>
    <tr>
        <td>bloglogo.jpg</td>
        <td><a th:href="@{/download(filename='bloglogo.jpg')}">下载文件</a></td>
    </tr>
    <tr>
        <td>Spring Boot应用级开发教程.pdf</td>
        <td><a th:href="@{/download(filename='Spring Boot应用级开发教程.pdf')}">
            下载文件</a></td>
    </tr>
</table>
</body>
</html>
```

3. 编写文件下载处理办法

```java
// 向文件下载页面跳转
@GetMapping("/toDownload")
public String toDownload(){
    return "download";
}
// 文件下载管理
@GetMapping("/download")
public ResponseEntity<byte[]> fileDownload(String filename){
    // 指定要下载的文件根路径
    String dirPath = "F:/file/";
    // 创建该文件对象
    File file = new File(dirPath + File.separator + filename);
    // 设置响应头
    HttpHeaders headers = new HttpHeaders();
    // 通知浏览器以下载方式打开
    headers.setContentDispositionFormData("attachment",filename);
    // 定义以流的形式下载返回文件数据
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    try {
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<byte[]>(e.getMessage().getBytes(),HttpStatus.EXPECTATION_FAILED);
    }
    }
```

toDownload()方法用来处理路径为“/toDownload”的Get请求，向文件下载页面download.html跳转；fileDownload(String filename)方法用来处理路径为“/download”的Get请求，并进行文件下载处理。其中，在fileDownload(String filename)方法中，设定了文件下载的存储路径为“F:/file/”、文件下载的打开方式和返回形式；在获取下载结果时，使用了FileUtils的readFileToByteArray()方法快速下载文件，并以ResponseEntity<byte[]>类型数据返回。


4. 中文文件名问题

在浏览器上访问http://localhost:8080/toDownload，下载后的文件中文名称统一变成了“_”

在FileController类的fileDownload()方法中添加中文的编码处理代码，getFilename(HttpServletRequest request,String filename)方法用来根据不同浏览器对下载的中文名进行转码。其中，通过HttpServletRequest中的“User-Agent”用于获取用户下载文件的浏览器内核信息（不同版本的IE浏览器内核可能不同，需要特别查看），如果内核信息是IE则转码为UTF-8，其他浏览器转码为ISO-8859-1即可。

```java
// 所有类型文件下载管理
@GetMapping("/download")
public ResponseEntity<byte[]> fileDownload(HttpServletRequest request,
                                           String filename) throws Exception{
    // 指定要下载的文件根路径
    String dirPath = "F:/file/";
    // 创建该文件对象
    File file = new File(dirPath + File.separator + filename);
    // 设置响应头
    HttpHeaders headers = new HttpHeaders();
    // 通知浏览器以下载方式打开（下载前对文件名进行转码）
    filename=getFilename(request,filename);
    headers.setContentDispositionFormData("attachment",filename);
    // 定义以流的形式下载返回文件数据
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    try {
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<byte[]>(e.getMessage().getBytes(),HttpStatus.EXPECTATION_FAILED);
    }
}

// 根据浏览器的不同进行编码设置，返回编码后的文件名
private String getFilename(HttpServletRequest request, String filename)
        throws Exception {
    // IE不同版本User-Agent中出现的关键词
    String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
    // 获取请求头代理信息
    String userAgent = request.getHeader("User-Agent");
    for (String keyWord : IEBrowserKeyWords) {
        if (userAgent.contains(keyWord)) {
            //IE内核浏览器，统一为UTF-8编码显示，并对转换的+进行更正
            return URLEncoder.encode(filename, "UTF-8").replace("+"," ");
        }
    }
    //火狐等其它浏览器统一为ISO-8859-1编码显示
    return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
}
```


## 打包和部署

### Jar包方式打包部署

(1)添加Maven打包插件

```
   <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```


# 缓存管理

缓存是分布式系统中的重要组件，主要解决数据库数据的高并发访问。在实际开发中，尤其是用户访问量较大的网站，用户对高频热点数据的访问非常频繁，为了提高服务器访问性能、减少数据库的压力、提高用户体验，使用缓存显得尤为重要。

## Spring Boot 默认缓存管理
Spring框架支持透明地向应用程序添加缓存并对缓存进行管理，其管理缓存的核心是将缓存应用于操作数据的方法中，从而减少操作数据的次数，同时不会对程序本身造成任何干扰。
Spring Boot继承了Spring框架的缓存功能，使用@EnableCaching注解开启基于注解的缓存支持。

```java
@EnableCaching //  开启Spring Boot基于注解的缓存管理支持
@SpringBootApplication
public class Demo06Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo06Application.class, args);
    }
}
```

使用`@Cacheable`注解对数据操作方法进行缓存管理
```java
@Cacheable(cacheNames = "comment")
public Comment findById(int comment_id){
    Optional<Comment> optional = commentRepository.findById(comment_id);
    if(optional.isPresent()){
        return optional.get();
    }
    return null;
}
```
#### 注解

### 1.@EnableCaching注解
@EnableCaching是由Spring框架提供的，Spring Boot框架对该注解进行了继承，该注解需要配置在类上（在Spring Boot中，通常配置在项目启动类上），用于开启基于注解的缓存支持。

### 2.@Cacheable注解
@Cacheable注解也是由Spring框架提供的，可以作用于类或方法（通常用在数据查询方法上），用于对方法结果进行缓存存储。
@Cacheable注解的执行顺序是，先进行缓存查询，如果为空则进行方法查询，并将结果进行缓存；如果缓存中有数据，不进行方法查询，而是直接使用缓存数据。
**@Cacheable注解(属性)**

### 3.@CachePut注解
@CachePut注解是由Spring框架提供的，可以作用于类或方法（通常用在数据更新方法上），该注解的作用是更新缓存数据。@CachePut注解的执行顺序是，先进行方法调用，然后将方法结果更新到缓存中。
@CachePut注解也提供了多个属性，这些属性与@Cacheable注解的属性完全相同。

### 4.@CacheEvict注解
@CacheEvict注解是由Spring框架提供的，可以作用于类或方法（通常用在数据删除方法上），该注解的作用是删除缓存数据。@CacheEvict注解的默认执行顺序是，先进行方法调用，然后将缓存进行清除。
@CacheEvict注解也提供了多个属性，这些属性与@Cacheable注解的属性基本相同，除此之外，还额外提供了两个特殊属性allEntries和beforeInvocation
allEntries属性
   allEntries属性表示是否清除指定缓存空间中的所有缓存数据，默认值为false（即默认只删除指定key对应的缓存数据）。
beforeInvocation属性
   beforeInvocation属性表示是否在方法执行之前进行缓存清除，默认值为false（即默认在执行方法后再进行缓存清除）。

### 5.@Caching注解

@Caching注解用于针对复杂规则的数据缓存管理，可以作用于类或方法，在@Caching注解内部包含有Cacheable、put和evict三个属性，分别对应于@Cacheable、@CachePut和@CacheEvict三个注解。
```java
@Caching(cacheable={@Cacheable(cacheNames ="comment",key = "#id")},
	put = {@CachePut(cacheNames = "comment",key = "#result.author")})
	public Comment getComment(int comment_id){
	return commentRepository.findById(comment_id).get();
}
```

### 6.@CacheConfig注解
@CacheConfig注解使用在类上，主要用于统筹管理类中所有使用@Cacheable、@CachePut和@CacheEvict注解标注方法中的公共属性，这些公共属性包括有cacheNames、keyGenerator、cacheManager和cacheResolver。
```java
@CacheConfig(cacheNames = "comment")
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Cacheable
    public Comment findById(int comment_id){
        Comment comment = commentRepository.findById(comment_id).get();
        return comment; }
        ...}
```

## Spring Boot + Redis实例

### Spring Boot 支持的缓存组件

Spring从3.1开始定义了org.springframework.cache.**Cache**和org.springframework.cache.**CacheManager**接口来统一不同的缓存技术，若程序中没有定义类型为cacheManager的Bean组件或者名为cacheResolver的cacheResolver缓存解析器，Spring Boot将尝试启用以下缓存组件（按照指定的顺序）

（1）Generic
（2）JCache (JSR-107) (EhCache 3、Hazelcast、Infinispan等)
（3）EhCache 2.x
（4）Hazelcast
（5）Infinispan
（6）Couchbase
（7）Redis
（8）Caffeine
（9）Simple（默认）


### 基于注解的Redis缓存实现

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

```yml
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
```

使用`@Cacheable、@CachePut、@CacheEvict`注解定制缓存管理
对CommentService类中的方法进行修改使用`@Cacheable、@CachePut、@CacheEvict`三个注解定制缓存管理


```java
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    // 根据评论id查询评论信息
    //@Cacheable(cacheNames = "comment")
    @Cacheable(cacheNames = "comment",unless = "#result==null")
    public Comment findById(int comment_id){
        Optional<Comment> optional = commentRepository.findById(comment_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // 更新评论信息
    @CachePut(cacheNames = "comment",key = "#result.id")
    public Comment updateComment(Comment comment){
        commentRepository.updateComment(comment.getAuthor(), comment.getaId());
        return comment;
    }
    // 删除评论信息
    @CacheEvict(cacheNames = "comment")
    public void deleteComment(int comment_id){
        commentRepository.deleteById(comment_id);
    }
}
```

启动项目报错
提示信息要求对应实体类必须实现序列化。

将缓存对象实现序列化

```java
@Entity(name="t_comment") // 设置ORM实体类，并指定映射的表名
public class Comment implements Serializable {
    @Id   // 表明映射对应的主键id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增策略
    private Integer id;
    private String content;
    private String author;
    @Column(name = "a_id") //指定映射的表字段名
    private Integer aId;
    ...
}
```


基于注解的Redis缓存查询测试
项目启动成功后，通过浏览器访问http://localhost:8080/get/1，并重复刷新浏览器查询同一条数据信息


基于注解的Redis缓存更新测试
项目启动成功后，通过浏览器访问http://localhost:8080/update/1/shitou，接着，继续访问http://localhost:8080/get/1
执行updateComment()方法更新id为1的数据时执行了一条更新SQL语句，后续调用findById()方法查询id为1的用户评论信息时没有执行查询SQL语句。


基于注解的Redis缓存删除测试
项目启动成功后，通过浏览器访问http://localhost:8080/delete/1，接着，继续访http://localhost:8080/get/1



```
#对于基于注解的Redis缓存数据统一设置有效期为1分钟，单位毫秒
spring.cache.redis.time-to-live=60000
#不灵活，对基于API的Redis缓存实现没效果
```



### 基于API的Redis缓存实现

使用Redis API 进行业务数据缓存管理

编写一个进行业务处理的类ApiCommentService,使用@Autowired注解注入Redis API中常用的RedisTemplate（类似于Java基础API中的JdbcTemplate）；然后在数据查询、修改和删除三个方法中，根据业务需求分别进行数据缓存查询、缓存存储、缓存更新和缓存删除。同时，Comment数据对应缓存管理的key值都手动设置了一个前缀“comment_”，这是针对不同业务数据进行缓存管理设置的唯一key，避免与其他业务缓存数据的key重复。

```java
@Service
public class ApiCommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    public Comment findById(int comment_id){
        // 先从Redis缓存中查询数据
        Object object =  redisTemplate.opsForValue().get("comment_"+comment_id);
        if (object!=null){
            return (Comment)object;
        }else {
            // 缓存中没有，就进入数据库查询
            Optional<Comment> optional = commentRepository.findById(comment_id);
            if(optional.isPresent()){
                Comment comment= optional.get();
                // 将查询结果进行缓存，并设置有效期为1天
                redisTemplate.opsForValue().set("comment_"+comment_id, comment,1, TimeUnit.DAYS);
                return comment;
            }else {
                return null;
            }
        }
    }

    public Comment updateComment(Comment comment){
        commentRepository.updateComment(comment.getAuthor(), comment.getaId());
        // 更新数据后进行缓存更新
        redisTemplate.opsForValue().set("comment_"+comment.getId(),comment);
        return comment;
    }

    public void deleteComment(int comment_id){
        commentRepository.deleteById(comment_id);
        // 删除数据后进行缓存删除
        redisTemplate.delete("comment_"+comment_id);
    }
}
```


编写Web访问层Controller文件ApiCommentController 
```java
@RestController
@RequestMapping("/api")  // 窄化请求路径
public class ApiCommentController {
    @Autowired
    private ApiCommentService apiCommentService;

    @GetMapping("/get/{id}")
    public Comment findById(@PathVariable("id") int comment_id){
        Comment comment = apiCommentService.findById(comment_id);
        return comment;
    }

    @GetMapping("/update/{id}/{author}")
    public Comment updateComment(@PathVariable("id") int comment_id,
                                 @PathVariable("author") String author){
        Comment comment = apiCommentService.findById(comment_id);
        comment.setAuthor(author);
        Comment updateComment = apiCommentService.updateComment(comment);
        return updateComment;
    }

    @GetMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id") int comment_id){
        apiCommentService.deleteComment(comment_id);
    }
}
```


基于API的Redis缓存实现的相关配置
- 基于API的Redis缓存实现`不需要@EnableCaching`注解开启基于注解的缓存支持。
- 基于API的Redis缓存实现需要在Spring Boot项目的pom.xml文件中引入Redis依赖启动器，并在配置文件中进行Redis服务连接配置，同时将进行数据存储的Comment实体类实现序列化接口。



## 自定义Redis缓存序列化机制

### 自定义RedisTemplate

Redis API 默认序列化机制
基于Redis API的Redis缓存实现是使用RedisTemplate模板进行数据缓存操作的.
```java
public class RedisTemplate<K, V> extends RedisAccessor implements RedisOperations<K, V>, BeanClassLoaderAware {
    private boolean enableTransactionSupport = false;
    private boolean exposeConnection = false;
    private boolean initialized = false;
    private boolean enableDefaultSerializer = true;
    @Nullable
    private RedisSerializer<?> defaultSerializer;
    @Nullable
    private ClassLoader classLoader;
    @Nullable
    private RedisSerializer keySerializer = null;
    @Nullable
    private RedisSerializer valueSerializer = null;
    @Nullable
    private RedisSerializer hashKeySerializer = null;
    @Nullable
    private RedisSerializer hashValueSerializer = null;
    ...
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        boolean defaultUsed = false;
        if (this.defaultSerializer == null) {
            this.defaultSerializer = new JdkSerializationRedisSerializer(this.classLoader != null ? this.classLoader :                              this.getClass().getClassLoader());
    }
    ...
 }
```

1. 使用RedisTemplate进行Redis数据缓存操作时，内部默认使用的是JdkSerializationRedisSerializer序列化方式，所以进行数据缓存的实体类必须实现JDK自带的序列化接口（例如Serializable）；
2. 使用RedisTemplate进行Redis数据缓存操作时，如果自定义了缓存序列化方式defaultSerializer，那么将使用自定义的序列化方式。


自定义RedisTemplate序列化机制

在项目中引入Redis依赖后，Spring Boot提供的RedisAutoConfiguration自动配置会生效。打开RedisAutoConfiguration类，查看内部源码中关于RedisTemplate的定义方式可知：

在Redis自动配置类中，通过Redis连接工厂RedisConnectionFactory初始化了一个RedisTemplate；该类上方添加了@ConditionalOnMissingBean注解（顾名思义，当某个Bean不存在时生效），用来表明如果开发者自定义了一个名为redisTemplate的Bean，则该默认初始化的RedisTemplate会被覆盖。

如果想要使用自定义序列化方式的RedisTemplate进行数据缓存操作，可以参考上述核心代码创建一个名为redisTemplate的Bean组件，并在该组件中设置对应的序列化方式即可。

在项目中创建创建一个Redis自定义配置类RedisConfig，通过@Configuration注解定义了一个RedisConfig配置类，并使用@Bean注解注入了一个默认名称为方法名的redisTemplate组件（注意，该Bean组件名称必须是redisTemplate）。在定义的Bean组件中，自定义了一个RedisTemplate，使用自定义的Jackson2JsonRedisSerializer数据序列化方式；在定制序列化方式中，定义了一个ObjectMapper用于进行数据转换设置。

```java
@Configuration   // 定义一个配置类
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        // 使用JSON格式序列化对象，对缓存数据key和value进行转换
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 设置RedisTemplate模板API的序列化方式为JSON
        template.setDefaultSerializer(jacksonSeial);
        return template;
    }    
}
```


### 自定义RedisCacheManager

Redis 注解默认序列化机制

Spring Boot整合Redis组件提供的缓存自动配置类RedisCacheConfiguration,其内部是通过Redis连接工厂RedisConnectionFactory定义了一个缓存管理器RedisCacheManager；同时定制RedisCacheManager时，也默认使用了JdkSerializationRedisSerializer序列化方式。
如果想要使用自定义序列化方式的RedisCacheManager进行数据缓存操作，可以创建一个名为cacheManager的Bean组件，并在该组件中设置对应的序列化方式即可。


自定义RedisCacheManager
在RedisConfig类中添加方法cacheManager
```java
@Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jacksonSeial =
                new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 定制缓存数据序列化方式及时效
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(strSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSeial))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
        return cacheManager;
    }
```

# Spring Boot 任务管理

## 异步任务
### 无返回值异步任务调用

(1)Spring Boot 项目创建
创建一个Spring Boot项目，选择Web模块中的Web依赖

(2)编写异步调用方法
编写异步调用方法，模拟用户短信验证码发送
创建一个业务实现类MyAsyncService，在该类中模拟编写用户短信验证码发送的方法
```java
@Service
public class MyAsyncService {
    /**
     * 模拟无返回值的异步任务处理
     * @throws Exception
     */
    @Async  //添加注释
    public void sendSMS() throws Exception {
        System.out.println("调用短信验证码业务方法...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        Long endTime = System.currentTimeMillis();
        System.out.println("短信业务执行完成耗时：" + (endTime - startTime));
    }
}
```

(3)开启基于注解的异步任务支持
使用@EnableAsync开启基于注解的异步方法支持
```java
@EnableAsync //添加注释
@SpringBootApplication
public class Demo09Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo09Application.class, args);
    }

}
```


(4)编写控制层业务调用方法
创建一个异步方法调用的实现类MyAsyncController，在该类中模拟编写用户短信验证码发送的处理方法
```java
@RestController
public class MyAsyncController {
    @Autowired
    private MyAsyncService myService;
    @GetMapping("/sendSMS")
    public String sendSMS() throws Exception {
        Long startTime = System.currentTimeMillis();
        myService.sendSMS();
        Long endTime = System.currentTimeMillis();
        System.out.println("主流程耗时： "+(endTime-startTime));
        return "success";
    }
}
```

### 有返回值异步任务调用

(1)编写异步调用方法
在MyAsyncService业务处理类中，添加两个模拟有返回值的异步任务业务处理方法
```java
/**
     * 模拟有返回值的异步任务处理
     * @return
     * @throws Exception
     */
    @Async
    public Future<Integer> processA() throws Exception {
        System.out.println("开始分析并统计业务A数据...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(4000);
        // 模拟定义一个假的统计结果
        int count=123456;
        Long endTime = System.currentTimeMillis();
        System.out.println("业务A数据统计耗时：" + (endTime - startTime));
        return new AsyncResult<Integer>(count);
    }
    @Async
    public Future<Integer> processB() throws Exception {
        System.out.println("开始分析并统计业务B数据...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        // 模拟定义一个假的统计结果
        int count=654321;
        Long endTime = System.currentTimeMillis();
        System.out.println("业务B数据统计耗时：" + (endTime - startTime));
        return new AsyncResult<Integer>(count);
    }
```

(2)编写控制层业务调用方法
在MyAsyncController业务处理类中，编写业务数据分析统计的请求处理方法
```java
@GetMapping("/statistics")
    public String statistics() throws Exception {
        Long startTime = System.currentTimeMillis();
        Future<Integer> futureA = myService.processA();
        Future<Integer> futureB = myService.processB();
        int total = futureA.get() + futureB.get();
        System.out.println("异步任务数据统计汇总结果： "+total);
        Long endTime = System.currentTimeMillis();
        System.out.println("主流程耗时： "+(endTime-startTime));
        return "success";
    }
```

(3)异步任务效果测试

调用的两个异步方法会作为两个子线程并行执行，直到异步方法执行完成并返回结果，这样主流程会在`最后一个`异步方法返回结果后跳出阻塞状态。

## 定时任务

### 定时任务介绍

(1)@EnableScheduling
启动定时任务支持，主要作用于项目启动类上。

(2)@Scheduled
主要作用定时任务上

cron属性
这是一个时间表达式，可以通过简单的配置就能完成各种时间的配置，我们通过CRON表达式几乎可以完成任意的时间搭配，它包含了六或七个域：
```
Seconds : 可出现", - * /"四个字符，有效范围为0-59的整数
Minutes : 可出现", - * /"四个字符，有效范围为0-59的整数
Hours : 可出现", - * /"四个字符，有效范围为0-23的整数
DayofMonth : 可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
Month : 可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
DayofWeek : 可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
Year : 可出现", - * /"四个字符，有效范围为1970-2099年
```


[在线Cron表达式](http://cron.qqe2.com/)生成器http://cron.qqe2.com/

几个简单例子
```
"0 0 12 * * ?" 每天中午十二点触发
"0 15 10 ? * *" 每天早上10：15触发
"0 15 10 * * ?" 每天早上10：15触发
"0 15 10 * * ? *" 每天早上10：15触发
"0 15 10 * * ? 2005" 2005年的每天早上10：15触发
"0 * 14 * * ?" 每天从下午2点开始到2点59分每分钟一次触发
"0 0/5 14 * * ?" 每天从下午2点开始到2：55分结束每5分钟一次触发
"0 0/5 14,18 * * ?" 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
"0 0-5 14 * * ?" 每天14:00至14:05每分钟一次触发
"0 10,44 14 ? 3 WED" 三月的每周三的14：10和14：44触发
"0 15 10 ? * MON-FRI" 每个周一、周二、周三、周四、周五的10：15触发
```

### 例子

(1)编写定时任务业务处理方法
新建一个定时任务管理的业务处理类ScheduledTaskService，并在该类中编写对应的定时任务处理方法。使用@Scheduled注解声明了三个定时任务方法，这三个方法定制的执行规则基本相同，都是每隔1分钟重复执行一次定时任务，在使用fixedDelay属性的方法scheduledTaskAfterSleep()中，使用Thread.sleep(10000)模拟该定时任务处理耗时为10秒钟。

```java
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count1 = 1;
    private Integer count2 = 1;
    private Integer count3 = 1;
    @Scheduled(cron = "0 * * * * *")
    public void scheduledTaskCron(){
        System.out.println(String.format("cron第%s次执行，当前时间为：%s",count3++, dateFormat.format(new Date())));
    }
}
```

(2)开启基于注解的定时任务支持

在项目启动类上开启基于注解的定时任务支持

```java
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Demo09Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo09Application.class, args);
    }

}

```


## 邮件任务
### 发送纯文本邮件

(1)添加邮件服务的依赖启动器
在pom.xml文件中添加邮件服务的依赖启动器
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
注：
当添加上述依赖后，Spring Boot自动配置的邮件服务会生效，在邮件发送任务时，可以直接使用Spring框架提供的JavaMailSender接口或者它的实现类JavaMailSenderImpl邮件发送。


(2)添加邮件服务配置
在全局配置文件中添加邮件服务配置
```yml
# 发件人邮服务器相关配置
spring.mail.host=smtp.qq.com
spring.mail.port=587
# 配置个人QQ账户和密码（密码是加密后的授权码）
spring.mail.username=2127269781@qq.com
spring.mail.password=ijdjokspcbnzfbfa
spring.mail.default-encoding=UTF-8
# 邮件服务超时时间配置
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000
```

(3)定制邮件发送服务
新建一个邮件发送任务管理的业务处理类SendEmailService，编写了一个发送纯文本邮件的sendSimpleEmail()方法，在该方法中通过SimpleMailMessage类定制了邮件信息的发件人地址（From）、收件人地址（To）、邮件标题（Subject）和邮件内容（Text），最后使用JavaMailSenderImpl的send()方法实现纯文本邮件发送。

```java
@Service
public class SendEmailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本邮件
     * @param to       收件人地址
     * @param subject  邮件标题
     * @param text     邮件内容
     */
    public void sendSimpleEmail(String to,String subject,String text){
        // 定制纯文本邮件信息SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            // 发送邮件
            mailSender.send(message);
            System.out.println("纯文本邮件发送成功");
        } catch (MailException e) {
            System.out.println("纯文本邮件发送失败 "+e.getMessage());
            e.printStackTrace();
        }
    }
}
```

(4)纯文本邮件发送效果测试
先定制了纯文本邮件发送方法所需要的参数（示例中定制了给自己邮箱发送邮件），然后调用业务方法实现了纯文本邮件发送。
```java
@Autowired
private SendEmailService sendEmailService;
@Test
public void sendSimpleMailTest() {
    String to="2127269781@qq.com";
    String subject="【纯文本邮件】标题";
    String text="Spring Boot纯文本邮件发送内容测试.....";
    // 发送简单邮件
    sendEmailService.sendSimpleEmail(to,subject,text);
}
```
接启动单元测试方法`sendSimpleMailTest()`


### 发送带附件和图片的邮件

(1)定制邮件发送服务
打开之前创建的邮件发送任务的业务处理类SendEmailService，在该类中编写一个发送带附件和图片邮件的业务方法sendComplexEmail() ，该方法需要接收的参数除了基本的发送信息外，还包括静态资源唯一标识、静态资源路径和附件路径。

```java
/**
 * 发送复杂邮件（包括静态资源和附件）
 * @param to           收件人地址
 * @param subject      邮件标题
 * @param text         邮件内容
 * @param filePath     附件地址
 * @param rscId        静态资源唯一标识
 * @param rscPath      静态资源地址
 */
public void sendComplexEmail(String to,String subject,String text,String filePath,String rscId,String rscPath){
    // 定制复杂邮件信息MimeMessage
    MimeMessage message = mailSender.createMimeMessage();
    try {
        // 使用MimeMessageHelper帮助类，并设置multipart多部件使用为true
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        // 设置邮件静态资源
        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);
        // 设置邮件附件
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);
        // 发送邮件
        mailSender.send(message);
        System.out.println("复杂邮件发送成功");
    } catch (MessagingException e) {
        System.out.println("复杂邮件发送失败 "+e.getMessage());
        e.printStackTrace();
    }
}
```

(2)邮件发送效果测试
在项目测试类中添加一个方法调用带附件和图片的复杂邮件发送的方法实现邮件发送效果测试，根据前面定义的复杂邮件发送业务方法定制了各种参数。其中，在定义邮件内容时使用了Html标签编辑邮件内容，并内嵌了一个标识为rscId的图片，并为邮件指定了携带的附件路径。在邮件发送之前，务必保证指定路径下存放有对应的静态资源和附件文件。
```java
@Test
public void sendComplexEmailTest() {
    String to="2127269781@qq.com";
    String subject="【复杂邮件】标题";
    // 定义邮件内容
    StringBuilder text = new StringBuilder();
    text.append("<html><head></head>");
    text.append("<body><h1>祝大家元旦快乐！</h1>");
    // cid为固定写法，rscId指定一个唯一标识
    String rscId = "img001";
    text.append("<img src='cid:" +rscId+"'/></body>");
    text.append("</html>");
    // 指定静态资源文件和附件路径
    String rscPath="F:\\email\\newyear.jpg";
    String filePath="F:\\email\\元旦放假注意事项.docx";
    // 发送复杂邮件
    sendEmailService.sendComplexEmail(to,subject,text.toString(),filePath,rscId,rscPath);
}
```

### 发送模板邮件
(1)添加Thymeleaf模板引擎依赖启动器
在pom.xml文件中添加Thymeleaf模板引擎依赖启动器
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

(2)定制模板邮件
定制模板邮件，添加发送用户注册验证码的模板页面
```xml
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>用户验证码</title>
</head>
<body>
    <div><span th:text="${username}">XXX</span>&nbsp;先生/女士,您好：</div>
    <P style="text-indent: 2em">您的新用户验证码为<span th:text="${code}" style="color: cornflowerblue">123456</span>，请妥善保管。</P>
</body>
</html>
```

(3)定制邮件发送服务
定制邮件发送服务，模拟发送html模板邮件
在业务处理类SendEmailService中编写一个发送Html模板邮件的业务方法，sendTemplateEmail()方法主要用于处理Html内容（包括Thymeleaf邮件模板）的邮件发送，在定制Html模板邮件信息时，、使用了MimeMessageHelper类对邮件信息进行封装处理。
```java
/**
 * 发送模板邮件
 * @param to       收件人地址
 * @param subject  邮件标题
 * @param content  邮件内容
 */
public void sendTemplateEmail(String to, String subject, String content) {
    MimeMessage message = mailSender.createMimeMessage();
    try {
        // 使用MimeMessageHelper帮助类，并设置multipart多部件使用为true
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        // 发送邮件
        mailSender.send(message);
        System.out.println("模板邮件发送成功");
    } catch (MessagingException e) {
        System.out.println("模板邮件发送失败 "+e.getMessage());
        e.printStackTrace();
    }
}
```

(4)模板邮件发送效果测试
在项目测试类中添加一个方法调用前面编写的Html模板邮件发送方法测试邮件发送效果，先使用@Autowired注解引入了Thymeleaf提供的模板引擎解析器`TemplateEngine`，然后定制了模板邮件发送所需的参数。
```java
@Test
public void sendTemplateEmailTest() {
    String to="2127269781@qq.com";
    String subject="【模板邮件】标题";
    // 使用模板邮件定制邮件正文内容
    Context context = new Context();
    context.setVariable("username", "石头");
    context.setVariable("code", "456123");
    // 使用TemplateEngine设置要处理的模板页面
    String emailContent = templateEngine.process("emailTemplate_vercode", context);
    // 发送模板邮件
    sendEmailService.sendTemplateEmail(to,subject,emailContent);
}
```



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

### 2.Spring MVC 功能拓展实现

(1)项目基础环境搭建

使用Spring Initializr方式创建Spring Boot项目，并在Dependencies依赖选择中选择Web依赖启动器和Thymeleaf依赖启动器

![](E:\java框架\SpringBoot课件\imgs\chap05\1.png)

复制第四章项目的文件到项目对应目录下

(2)功能拓展实现

1)注册视图管理器，创建一个实现WebMvcConfigurer接口的配置类MyMVCconfig，用于对MVC框架功能扩展

```
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

在浏览器上分别访问http://localhost:8080/toLoginPage

http://localhost:8080/login.html

<img src="E:\java框架\SpringBoot课件\imgs\chap05\2.png" style="zoom: 67%;" />

<img src="E:\java框架\SpringBoot课件\imgs\chap05\3.png" style="zoom:67%;" />

说明：使用 WebMvcConfigurer接口中addViewControllers方法也实现了用户请求跳转的效果，但是无法获取后台处理的数据。只适用于无参视图Get方式请求跳转。

2）注册自定义拦截器MyInterceptor，实现HandlerInterceptor 接口，在该类中编写如下方法

```
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

```
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

测试

http://localhost:8080/admin

## 5.2Spring Boot 整合Servlet三大组件

### 1.组件注册整合Servlet三大组件

**(1)使用组件注册方式整合Servlet**

1)添加servletComponent包，创建一个自定义Servlet类MyServlet，使用@Component注解将MyServlet类作为组件注入Spring容器。MyServlet类继承自HttpServlet，通过HttpServletResponse对象向页面输出“hello MyServlet”。

```
@Component
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.getWriter().write("hello MyServlet");
    }
}
```

2）创建Servlet组件配置类，在config包下创建ServletConfig类

```
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

测试：http://localhost:8080/myServlet

**(2)使用组件注册方式整合Filter**

1）在servletComponent包下创建一个自定义Servlet类MyFilter，使用@Component注解将当前MyFilter类作为组件注入到Spring容器中。MyFilter类实现Filter接口，并重写了init()、doFilter()和destroy()方法，在doFilter()方法中向控制台打印了“hello MyFilter”字符串。

```
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

2）在ServletConfig类向Servlet组件配置类注册自定义Filter类

```
 // 注册Filter组件
    @Bean
     public FilterRegistrationBean getFilter(MyFilter filter){
         FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setUrlPatterns(Arrays.asList("/toLoginPage","/myFilter"));
         return registrationBean;
     }
```

测试：http://localhost:8080/myFilter

**(3)使用组件注册方式整合Listener**

1）在servletComponent包下创建一个类MyListener。

```
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

2）在ServletConfig类向Servlet组件配置类注册自定义Listener类

```
//注册Listener组件
    @Bean
    public ServletListenerRegistrationBean getServletListener(MyListener myListener){
        ServletListenerRegistrationBean registrationBean =
                new ServletListenerRegistrationBean(myListener);
        return registrationBean;
    }
```

![](E:\java框架\SpringBoot课件\imgs\chap05\4.png)

### 2.路径扫描整合Servlet三大组件

（1）使用路径扫描的方式整合Servlet、Filter、Listener

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

(2)整合测试

项目启动成功后查看控制台打印效果

在浏览器上访问http://localhost:8080/annotationServlet

在浏览器上访问http://localhost:8080/antionMyFilter

## 5.3文件上传与下载

### 1.文件上传

(1)编写文件上传的表单页面

创建上传文件的upload.html页面

```
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

(2))在全局配置文件中添加文件上传的相关配置

在全局配置文件application.properties中添加文件上传功能的相关设置

```
# thymeleaf页面缓存设置（默认为true）
spring.thymeleaf.cache=false
# 配置国际化文件基础名
spring.messages.basename=i18n.login
# 单个上传文件大小限制（默认1MB）
spring.servlet.multipart.max-file-size=10MB
# 总上传文件大小限制（默认10MB）
spring.servlet.multipart.max-request-size=50MB
```

(3))进行文件上传处理实现文件上传功能

在controller包下创建一个管理文件上传下载的控制类FileController

```
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

toUpload()方法处理路径为“/toUpload”的GET请求，向文件上传页面upload.html跳转；uploadFile()方法处理路径为“/uploadFile”的POST请求，对上传文件进行处理。文件上传处理过程中，对文件名进行重命名并存放在“F:/file/”目录下，并封装了返回结果。其中，处理上传文件的请求方法中，使用了“MultipartFile[] fileUpload”参数处理单个或多个上传文件（也可以使用单列集合参数），fileUpload参数名必须与upload.html页面中上传文件<input>框中的name属性值一致。

(4)效果测试

在浏览器上访问http://localhost:8080/toUpload

### 2.文件下载

(1)添加文件下载工具依赖

pom.xml文件中这引入文件下载的一个工具类依赖commons-io

```
<dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.6</version>
        </dependency>
```

(2)定制文件下载页面

```
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

(3)编写文件下载处理办法

```
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

(4)效果测试

在浏览器上访问http://localhost:8080/toDownload

(5)中文文件名问题

在浏览器上访问http://localhost:8080/toDownload，下载后的文件中文名称统一变成了“_”

在FileController类的fileDownload()方法中添加中文的编码处理代码，getFilename(HttpServletRequest request,String filename)方法用来根据不同浏览器对下载的中文名进行转码。其中，通过HttpServletRequest中的“User-Agent”用于获取用户下载文件的浏览器内核信息（不同版本的IE浏览器内核可能不同，需要特别查看），如果内核信息是IE则转码为UTF-8，其他浏览器转码为ISO-8859-1即可。

```
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

## 5.4Spring Boot应用的打包和部署

### 1.Jar包方式打包部署

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

(2)使用IDEA开发工具进行打包

![](E:\java框架\SpringBoot课件\imgs\chap05\5.png)

![](E:\java框架\SpringBoot课件\imgs\chap05\6.png)

(3)Jar包目录结构展示说明

(4)Jar包方式部署

切换到终端界面输入

```
java -jar xxx.jar
```

![](E:\java框架\SpringBoot课件\imgs\chap05\7.png)

![](E:\java框架\SpringBoot课件\imgs\chap05\8.png)

### 2.War包方式打包部署

(1)声明打包方式为War包

```
 <description>demo05</description>
    <packaging>war</packaging>
    <properties>
        <java.version>1.8</java.version>
    </properties>
```

(2)声明使用外部的Tomcat服务器

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
```

(3)提供Spring Boot启动的Servlet初始化器

主程序启动类继承SpringBootServletInitializer类并实现configure()方法，在configure()方法中，sources(Chapter05Application.class,args)方法的首个参数必须是项目主程序启动类。
需要说明的是，为Spring Boot提供启动的Servlet初始化器SpringBootServletInitializer的典型的做法就是让主程序启动类继承SpringBootServletInitializer类并实现configure()方法；除此之外，还可以在项目中单独提供一个继承SpringBootServletInitializer的子类，并实现configure()方法。

```
public class Demo05Application extends SpringBootServletInitializer {
    // 3、程序主类继承SpringBootServletInitializer，并重写configure()方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Demo05Application.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Demo05Application.class, args);
    }
}
```

(4)使用IDEA开发工具进行打包

打包方式同jar包

(5)War包方式部署

将打包好的War包拷贝到Tomcat安装目录下的webapps目录中，执行Tomcat安装目录下bin目录中的startup.bat命令启动War包项目

**注意：使用外部Tomcat部署的项目进行访问时，必须加上项目名称（打成war包后的项目全名）**


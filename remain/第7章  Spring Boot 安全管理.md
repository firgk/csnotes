# 第7章  Spring Boot 安全管理

实际开发中，一些应用通常要考虑到安全性问题。例如，对于一些重要的操作，有些请求需要用户验明身份后才可以执行，还有一些请求需要用户具有特定权限才可以执行。这样做的意义，不仅可以用来保护项目安全，还可以控制项目访问效果。

## 7.1Spring Security介绍

### 1.Spring Security介绍

Spring Security是基于Spring生态圈的，用于提供安全访问控制解决方案的框架。
Spring Security的安全管理有两个重要概念，分别是Authentication（认证）和Authorization（授权）。

### 2.Spring Boot整合Spring Security实现的安全管理功能

- MVC Security是Spring Boot整合Spring MVC框架搭建的Web应用的安全管理。
- WebFlux Security是Spring Boot整合Spring WebFlux框架搭建的Web应用的安全管理。
- OAuth2是大型项目的安全管理框架，可以实现第三方认证、单点登录等功能。
- Actuator Security用于对项目的一些运行环境提供安全监控，例如Health健康信息、Info运行信息等，它主要作为系统指标供运维人员查看管理系统的运行情况。

## 7.2Spring Security快速入门

### 1.基础环境搭建

(1)创建Spring Boot项目

创建项目，引入Web和Thymeleaf的依赖启动器

![](E:\java框架\SpringBoot课件\imgs\chap07\1.png)

(2)引入页面Html资源文件

在项目的resources下templates目录中，引入案例所需的资源文件

![](E:\java框架\SpringBoot课件\imgs\chap07\2.png)

index.html

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>影视直播厅</title>
</head>
<body>
<h1 align="center">欢迎进入电影网站首页</h1>
<hr>
	<h3>普通电影</h3>
	<ul>
		<li><a th:href="@{/detail/common/1}">飞驰人生</a></li>
		<li><a th:href="@{/detail/common/2}">夏洛特烦恼</a></li>
	</ul>
	<h3>VIP专享</h3>
	<ul>
		<li><a th:href="@{/detail/vip/1}">速度与激情</a></li>
		<li><a th:href="@{/detail/vip/2}">猩球崛起</a></li>
	</ul>
</body>
</html>
```

1.html

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影片详情</title>
</head>
<body>
	<a th:href="@{/}">返回</a>
	<h1>飞驰人生</h1>
	<p style="width: 550px">简介：曾经在赛车界叱咤风云、如今却只能经营炒饭大排档的赛车手张驰（沈腾饰）决定重返车坛挑战年轻一代的天才。
		然而没钱没车没队友，甚至驾照都得重新考，这场笑料百出不断被打脸的复出之路，还有更多哭笑不得的窘境在等待着这位过气车神……
	</p>
</body>
</html>
```

(3)编写Web控制层

创建controller包，创建一个用于页面请求处理的控制类

```
@Controller
public class FilmeController {
    //  影片详情页
    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type")String type, @PathVariable("path")String path) {
        return "detail/"+type+"/"+path;
    }
}
```

至此，使用Spring Boot整合Spring MVC框架实现了一个传统且简单的Web项目，目前项目没有引入任何的安全管理依赖，也没有进行任何安全配置，项目启动成功后，可以访问首页，单击影片进入详情详情页。

### 2.开启安全管理效果测试

(1)添加spring-boot-starter-security启动器

一旦项目引入spring-boot-starter-security启动器，MVC Security和WebFlux Security负责的安全功能都会立即生效

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

(2)项目启动测试

项目启动时会在控制台Console中自动生成一个安全密码

![](E:\java框架\SpringBoot课件\imgs\chap07\3.png)

浏览器访问http://localhost:8080/查看项目首页项目中并没有手动创建用户登录页面，而添加了Security依赖后，Spring Security会自带一个默认的登录页面,登录页面随意输入一个错误的用户名和密码，会出现错误提示

![](E:\java框架\SpringBoot课件\imgs\chap07\4.png)

Security会默认提供一个可登录的用户信息，其中用户名为user，密码随机生成，这个密码会随着项目的每次启动随机生成并打印在控制台上，在登录页面输入用户名和密码

![](E:\java框架\SpringBoot课件\imgs\chap07\5.png)

这种默认安全管理方式存在诸多问题，例如：只有唯一的默认登录用户user、密码随机生成且过于暴露、登录页面及错误提示页面不是我们想要的等。

## 7.3MVC Security安全配置介绍

### 1.MVC Security安全配置介绍

项目引入spring-boot-starter-security依赖启动器，MVC Security安全管理功能就会自动生效，其默认的安全配置是在SecurityAutoConfiguration和UserDetailsServiceAutoConfiguration中实现的。

- SecurityAutoConfiguration:导入并自动化配置启动Web安全管理
- UserDetailsServiceAutoConfiguration:用于配置用户身份信息

### 2.如何关闭Sercurity提供的Web应用默认安全配置

- 要完全关闭Security提供的Web应用默认安全配置，可以自定义WebSecurityConfigurerAdapter类型的Bean组件以及自定义UserDetailsService、AuthenticationProvider或AuthenticationManager类型的Bean组件。
- 另外，可以通过自定义WebSecurityConfigurerAdapter类型的Bean组件来覆盖默认访问规则。

### 3.WebSecurityConfigurerAdapter类的主要方法及说明

| 方法                                         | 描述                             |
| -------------------------------------------- | -------------------------------- |
| configure(AuthenticationManagerBuilder auth) | 定制用户认证管理器来实现用户认证 |
| configure(HttpSecurity http)                 | 定制基于HTTP请求的用户访问控制   |

## 7.4自定义用户认证

### 1.内存身份认证

(1)自定义WebSecurityConfigurerAdapter配置类

创建名为config的包，创建一个配置类SecurityConfig

```
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
```

@EnableWebSecurity注解是一个组合注解，主要包括@Configuration注解、@Import({WebSecurityConfiguration.class, SpringWebMvcImportSelector.class})注解和@EnableGlobalAuthentication注解

@Import注解的作用是根据pom.xml中导入的Web模块和Security模块进行自动化配置。

@EnableGlobalAuthentication用于开启自定义的全局认证。

(2)使用内存进行身份认证

SecurityConfig类中重写configure(AuthenticationManagerBuilder auth)方法，并在该方法中使用内存身份认证的方式自定义了认证用户信息。定义用户认证信息时，设置了两个用户名和密码以及对应的角色信息。

```
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 1、使用内存用户信息，作为测试使用
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("shitou").password(encoder.encode("123456")).roles("common")
                .and()
                .withUser("李四").password(encoder.encode("123456")).roles("vip");

    }
}
```

(3)效果测试

重启项目进行效果测试，项目启动成功后，仔细查看控制台打印信息，发现没有默认安全管理时随机生成的密码了。通过浏览器访问http://localhost:8080/

输入错误的用户名和密码，会出现相应的提示

输入正确的用户名和密码，会进入网站首页

### 2.JDBC身份认证

(1)数据准备

数据库 springbootdata

![](E:\java框架\SpringBoot课件\imgs\chap07\6.png)

```
# 选择使用数据库
USE springbootdata;
# 创建表t_customer并插入相关数据
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `t_customer` VALUES ('1', 'shitou', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '1');
INSERT INTO `t_customer` VALUES ('2', '李四', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '1');
# 创建表t_authority并插入相关数据
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `t_authority` VALUES ('1', 'ROLE_common');
INSERT INTO `t_authority` VALUES ('2', 'ROLE_vip');
# 创建表t_customer_authority并插入相关数据
DROP TABLE IF EXISTS `t_customer_authority`;
CREATE TABLE `t_customer_authority` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `customer_id` int(20) DEFAULT NULL,
  `authority_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `t_customer_authority` VALUES ('1', '1', '1');
INSERT INTO `t_customer_authority` VALUES ('2', '2', '2');

# 记住我功能中创建持久化Token存储的数据表
create table persistent_logins (username varchar(64) not null,
								series varchar(64) primary key,
								token varchar(64) not null,
								last_used timestamp not null);

```

(2)添加JDBC连接数据库的依赖启动器

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>5.1.2</scope>
        </dependency>
```

(3)进行数据库连接配置

```
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdata?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
```

(4)使用JDBC进行身份认证

在SecurityConfig 类中的configure(AuthenticationManagerBuilder auth)方法中使用JDBC身份认证的方式进行自定义用户认证,使用JDBC身份认证时，首先需要对密码进行编码设置（必须与数据库中用户密码加密方式一致）；然后需要加载JDBC进行认证连接的数据源DataSource；最后，执行SQL语句，实现通过用户名username查询用户信息和用户权限。

```
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 1、使用内存用户信息，作为测试使用
//        auth.inMemoryAuthentication().passwordEncoder(encoder)
//                .withUser("shitou").password(encoder.encode("123456")).roles("common")
//                .and()
//                .withUser("李四").password(encoder.encode("123456")).roles("vip");
                // 2、使用JDBC进行身份认证
        String userSQL ="select username,password,valid from t_customer " +
                "where username = ?";
        String authoritySQL="select c.username,a.authority from t_customer c,t_authority a,"+
                "t_customer_authority ca where ca.customer_id=c.id " +
                "and ca.authority_id=a.id and c.username =?";
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);
    }
}
```

(5)效果测试

重启项目进行效果测试，项目启动成功后，通过浏览器访问http://localhost:8080/

### 3.UserDetailsService 身份认证

(1)定义查询用户及角色信息的服务接口

1）导入依赖

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>2.4.10</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>2.4.10</version>
        </dependency>
```

2）创建domain包，添加Customer、Authority类

```
@Entity(name = "t_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
```

```
@Entity(name = "t_authority ")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String authority ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
```

3）创建repository包，添加CustomerRepository、AuthorityRepository接口

```
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);
}
```

```
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
    @Query(value = "select a.* from t_customer c,t_authority a,t_customer_authority ca where ca.customer_id=c.id and ca.authority_id=a.id and c.username =?1",nativeQuery = true)
    public List<Authority> findAuthoritiesByUsername(String username);
}
```

4）在config包下添加RedisConfig类

```
@Configuration
public class RedisConfig {
    /**
     *  定制Redis API模板RedisTemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        // 使用JSON格式序列化对象，对缓存数据key和value进行转换
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        // 设置RedisTemplate模板API的序列化方式为JSON
        template.setDefaultSerializer(jacksonSeial);
        return template;
    }

    /**
     * 定制Redis缓存管理器RedisCacheManager，实现自定义序列化并设置缓存时效
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        // 定制缓存数据序列化方式及时效
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))   // 设置缓存有效期为1天
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(strSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSeial))
                .disableCachingNullValues();   // 对空数据不进行缓存
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
        return cacheManager;
    }
}
```

5）创建service包，添加CustomerService业处理类

```
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    // 业务控制：使用唯一用户名查询用户信息
    public Customer getCustomer(String username){
        Customer customer=null;
        Object o = redisTemplate.opsForValue().get("customer_"+username);
        if(o!=null){
            customer=(Customer)o;
        }else {
            customer = customerRepository.findByUsername(username);
            if(customer!=null){
                redisTemplate.opsForValue().set("customer_"+username,customer);
            }
        }
        return customer;
    }
    // 业务控制：使用唯一用户名查询用户权限
    public List<Authority> getCustomerAuthority(String username){
        List<Authority> authorities=null;
        Object o = redisTemplate.opsForValue().get("authorities_"+username);
        if(o!=null){
            authorities=(List<Authority>)o;
        }else {
            authorities=authorityRepository.findAuthoritiesByUsername(username);
            if(authorities.size()>0){
                redisTemplate.opsForValue().set("authorities_"+username,authorities);
            }
        }
        return authorities;
    }
}
```

(2)定义UserDetailsService用于封装认证用户信息

自定义一个接口实现类UserDetailsServiceImpl进行用户认证信息UserDetailsService封装，重写了UserDetailsService接口的loadUserByUsername(String s)方法，在该方法中，使用CustomerService业务处理类获取用户的用户信息和权限信息，并通过UserDetails进行认证用户信息封装。

```
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 通过业务方法获取用户及权限信息
        Customer customer = customerService.getCustomer(s);
        List<Authority> authorities = customerService.getCustomerAuthority(s);
        // 对用户权限进行封装
        List<SimpleGrantedAuthority> list = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        // 返回封装的UserDetails用户详情类
        if(customer!=null){
            UserDetails userDetails= new User(customer.getUsername(),customer.getPassword(),list);
            return userDetails;
        } else {
            // 如果查询的用户不存在（用户名不存在），必须抛出此异常
            throw new UsernameNotFoundException("当前用户不存在！");
        }
    }
}
```

(3)使用UserDetailsService进行身份认证

```
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         //3、使用UserDetailsService进行身份认证
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
```

(4)效果测试

重启项目进行效果测试，项目启动成功后，通过浏览器访问http://localhost:8080/

## 7.5自定义用户授权管理

### 1.自定义用户访问控制

(1)HttpSecurity类的主要方法

| **方法**                | **说明**                                                     |
| ----------------------- | ------------------------------------------------------------ |
| **openidLogin()**       | 用于基于 OpenId 的验证                                       |
| **headers()**           | 将安全标头添加到响应                                         |
| **cors()**              | 配置跨域资源共享（ CORS ）                                   |
| **sessionManagement()** | 允许配置会话管理                                             |
| **portMapper()**        | 允许配置一个PortMapper(HttpSecurity#(getSharedObject(class)))，其他提供SecurityConfigurer的对象使用 PortMapper 从 HTTP 重定向到 HTTPS 或者从 HTTPS 重定向到 HTTP。默认情况下，Spring Security使用一个PortMapperImpl映射 HTTP 端口8080到 HTTPS 端口8443，HTTP 端口80到 HTTPS 端口443 |
| **jee()**               | 配置基于容器的预认证。 在这种情况下，认证由Servlet容器管理   |
| **x509()**              | 配置基于x509的认证                                           |
| **rememberMe**          | 允许配置“记住我”的验证                                       |
| **authorizeRequests()** | 允许基于使用HttpServletRequest限制访问                       |
| **requestCache()**      | 允许配置请求缓存                                             |
| **exceptionHandling()** | 允许配置错误处理                                             |
| **securityContext()**   | 在HttpServletRequests之间的SecurityContextHolder上设置SecurityContext的管理。 当使用WebSecurityConfigurerAdapter时，这将自动应用 |
| **servletApi()**        | 将HttpServletRequest方法与在其上找到的值集成到SecurityContext中。 当使用WebSecurityConfigurerAdapter时，这将自动应用 |
| **csrf()**              | 添加 CSRF 支持，使用WebSecurityConfigurerAdapter时，默认启用 |
| **logout()**            | 添加退出登录支持。当使用WebSecurityConfigurerAdapter时，这将自动应用。默认情况是，访问URL”/ logout”，使HTTP Session无效来清除用户，清除已配置的任何#rememberMe()身份验证，清除SecurityContextHolder，然后重定向到”/login?success” |
| **anonymous()**         | 允许配置匿名用户的表示方法。 当与WebSecurityConfigurerAdapter结合使用时，这将自动应用。 默认情况下，匿名用户将使用org.springframework.security.authentication.AnonymousAuthenticationToken表示，并包含角色 “ROLE_ANONYMOUS” |
| **formLogin()**         | 指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面 |
| **oauth2Login()**       | 根据外部OAuth 2.0或OpenID Connect 1.0提供程序配置身份验证    |
| **requiresChannel()**   | 配置通道安全。为了使该配置有用，必须提供至少一个到所需信道的映射 |
| **httpBasic()**         | 配置 Http Basic 验证                                         |
| **addFilterAt()**       | 在指定的Filter类的位置添加过滤器                             |

(2)访问控制 url匹配及内置访问控制方法介绍

.authorizeRequests()主要是对 url 进行控制，也就是我们所说的授权（访问控制）。http.authorizeRequests()也支持连缀写法，总体公式为：

url  匹配规则. 权限控制方法

通过上面的公式可以有很多 url 匹配规则和很多权限控制方法。这些内容进行各种组合就形成了 Spring Security 中的授权。在所有匹配规则中取所有规则的交集。配置顺序影响了之后授权效果，越是具体的应该放在前面，越是笼统的应该放到后面。

1)anyRequest():表示匹配所有的请求。一般情况下此方法都会使用，设置全部内容都需要进行认证。

```
anyRequest().authenticated();
```

2)antMatcher():

参数是不定向参数，每个参数是一个 ant 表达式，用于匹配 URL规则。

规则如下：

? 匹配一个字符

\* 匹配 0 个或多个字符

** 匹配 0 个或多个目录

在实际项目中经常需要放行所有静态资源，下面演示放行 js 文件夹下所有脚本文件。

```
.antMatchers( "/js/**").permitAll()
```

还有一种配置方式是只要是.js 文件都放行

```
.antMatchers( "/**/*.js").permitAll()
```

3)regexMatchers():使用正则表达式进行匹配。和 antMatchers()主要的区别就是参数，antMatchers()参数是 ant 表达式，regexMatchers()参数是正则表达式。

演示所有以.js 结尾的文件都被放行

```
.regexMatchers( ".+[.]js").permitAll()
```

**注意：无**论是 antMatchers()还是 regexMatchers()都具有两个参数的方法，其中第一个参数都是 HttpMethod，表示请求方式，当设置了HttpMethod 后表示只有设定的特定的请求方式才执行对应的权限设置。

![](E:\java框架\SpringBoot课件\imgs\chap07\7.png)

4）mvcMatchers()：适用于配置了 servletPath 的情况。

servletPath 就是所有的 URL 的统一前缀。在 SpringBoot 整合SpringMVC 的项目中可以在 application.properties 中添加下面内容设置 ServletPath

```
spring.mvc.servlet.path=  /bjsxt
```

在 Spring Security 的配置类中配置.servletPath()是 mvcMatchers()返回值特有的方法，antMatchers()和 regexMatchers()没有这个方法。在 servletPath()中配置了 servletPath 后，mvcMatchers()直接写 SpringMVC 中@RequestMapping()中设置的路径即可。

```
.mvcMatchers( "demo").servletPath( "/bjsxt").permitAll()
```

如果不习惯使用 mvcMatchers()也可以使用 antMatchers()，下面代码和上面代码是等效的

```
.antMatchers( "/bjsxt/demo").permitAll()
```

5)permitAll():表示所匹配的 URL 任何人都允许访问。

6)authenticated():表示所匹配的 URL 都需要被认证才能访问。

7)anonymous():表示可以匿名访问匹配的 URL。和 permitAll()效果类似，只是设置为 anonymous()的 url 会执行 filter 链中

8)denyAll():表示所匹配的 URL 都不允许被访问。

9)rememberMe():被“remember me”的用户允许访问

10)fullyAuthenticated():如果用户不是被 remember me 的，才可以访问。

(2)自定义用户访问控制

1)自定义用户访问控制

自定义配置类SecurityConfig，继续重写configure(HttpSecurity http)方法

```
@Override
    protected void configure(HttpSecurity http) throws Exception {
              http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated()
                .and()
                .formLogin();        
    }
```

2)效果测试

重启项目进行效果测试，项目启动成功后，通过浏览器访问http://localhost:8080/

![](E:\java框架\SpringBoot课件\imgs\chap07\8.png)

项目首页单击普通电影或者VIP专享电影名称查询电影详情

![](E:\java框架\SpringBoot课件\imgs\chap07\9.png)

在此登录界面输入正确的用户名和密码信息后（如果访问的是普通电影）

![](E:\java框架\SpringBoot课件\imgs\chap07\10.png)

在项目首页中单击VIP专享电影名称查看影片详情，登录普通用户，在查看VIP电影详情时，页面会出现403 Forbidden的错误信息

![](E:\java框架\SpringBoot课件\imgs\chap07\11.png)

### 2.自定义用户登录

(1)formLogin()用户登录方法中涉及用户登录的主要方法

```
.loginPage("/login.html") //表单登录页面地址
.passwordParameter("password")//form表单用户名参数名
.usernameParameter("username") //form表单密码参数名
.loginProcessingUrl("/login")//form表单POST请求url提交地址，默认为/login
.successForwardUrl("/success.html")  //登录成功跳转地址
.failureForwardUrl("/error.html") //登录失败跳转地址
.defaultSuccessUrl()//如果用户没有访问受保护的页面，默认跳转到页面
.failureHandler(AuthenticationFailureHandler) //用户登录失败后的错误处理
.successHandler(AuthenticationSuccessHandler) //用户登陆成功后的处理
.failureUrl("/login?error")//用户登录失败后跳转的地址
.permitAll();//允许所有用户都有权限访问登录页面
```

(2)自定义用户登录

1)自定义用户登录页面

在项目的resources/ templates目录下新创建login.html

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录界面</title>
    <link th:href="@{/login/css/bootstrap.min.css}" rel="stylesheet">
    <link th:href="@{/login/css/signin.css}" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" th:action="@{/userLogin}" th:method="post" >
    <img class="mb-4" th:src="@{/login/img/login.jpg}" width="72px" height="72px">
    <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
    <!-- 用户登录错误信息提示框 -->
    <div th:if="${param.error}"
         style="color: red;height: 40px;text-align: left;font-size: 1.1em">
        <img th:src="@{/login/img/loginError.jpg}" width="20px">用户名或密码错误，请重新登录！
    </div>
    <input type="text" name="name" class="form-control"
           placeholder="用户名" required="" autofocus="">
    <input type="password" name="pwd" class="form-control"
           placeholder="密码" required="">
    <button class="btn btn-lg btn-primary btn-block" type="submit" >登录</button>
    <p class="mt-5 mb-3 text-muted">Copyright© 2019-2020</p>
</form>
</body>
</html>
```

通过<form>标签定义了一个用户登录功能，且登录数据以POST方式向“/userLogin”路径进行提交。其中，登录表单中的用户名参数和密码参数可以自行定义；登录数据提交方式必须为post，提交的路径也可以自行定义。

2)自定义用户登录跳转

在之前创建的FilmeController类中添加一个跳转到登录页面login.html的方法

```
@GetMapping("/userLogin")
    public String toLoginPage() {
        return "login/login";
    }
```

**注：**Spring Security默认使用Get方式的“/login”请求用于向登录页面跳转，默认使用Post方式的“/login”请求用于对登录后的数据进行处理。因此，自定义用户登录控制时，需要提供向用户登录页面跳转的方法，且自定义的登录页跳转路径必须与数据处理提交路径一致。

3)自定义用户登录控制

打开SecurityConfig类，重写configure(HttpSecurity http)方法实现用户登录控制

```
 @Override
    protected void configure(HttpSecurity http) throws Exception {
              http.authorizeRequests()
                .antMatchers("/").permitAll()
                // 需要对static文件夹下静态资源进行统一放行
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();
        // 自定义用户登录控制
              http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
    }
```

4)效果测试

重启项目进行效果测试，项目启动成功后，通过浏览器访问http://localhost:8080/
会直接进入到项目首页,在项目首页，单击普通电影或者VIP专享电影名称查询电影详情

![](E:\java框架\SpringBoot课件\imgs\chap07\12.png)

### 3.自定义用户退出

(1)HttpSecurity类的logout()方法用来处理用户退出，默认路径为“/logout”的Post类型请求，同时也会清除Session和“Remeber Me”等任何默认用户配置。

logout()方法及说明

| 方法                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| logoutUrl(String logoutUrl)                                  | 用户退出处理控制URL，默认为post请求的/logout                 |
| logoutSuccessUrl(String logoutSuccessUrl)                    | 用户退出成功后的重定向地址                                   |
| logoutSuccessHandler(LogoutSuccessHandler logoutSuccessHandler) | 用户退出成功后的处理器设置                                   |
| deleteCookies(String ...cookieNamesToClear)                  | 用户退出后删除指定的Cookie                                   |
| invalidateHttpSession(boolean invalidateHttpSession)         | 用户退出后是否立即清除Session（默认为true）                  |
| clearAuthentication(boolean clearAuthentication)             | 用户退出后是否立即清除Authentication用户认证信息（默认为true） |

(2)自定义用户退出

1)添加自定义用户退出链接

在index.html添加自定义用户退出链接

```
<form th:action="@{/mylogout}" method="post">
		<input th:type="submit" th:value="注销" />
	</form>
```

**注**：Spring Boot项目中引入Spring Security框架后会自动开启CSRF防护功能，用户退出时必须使用POST请求；如果关闭了CSRF防护功能，那么可以使用任意方式的HTTP请求进行用户注销。

2)自定义用户退出控制

在SecurityConfig类，重写configure(HttpSecurity http)方法进行用户退出控制，在该方法中添加如下代码

```
 @Override
    protected void configure(HttpSecurity http) throws Exception {
              http.authorizeRequests()
                .antMatchers("/").permitAll()
                // 需要对static文件夹下静态资源进行统一放行
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();
        // 自定义用户登录控制
              http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
        // 自定义用户退出控制
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");
    }
```

3)效果测试

重启项目进行效果测试，项目启动成功后，通过浏览器访问http://localhost:8080/

### 4.登录用户信息获取

(1)使用HttpSession获取用户信息及测试

在FilmeController类中新增一个用于获取当前会话用户信息的getUser()方法，在该方法中通过获取当前HttpSession的相关方法遍历并获取了会话中的用户信息。
在获取认证用户信息时，使用了Authentication的getPrincipal()方法，默认返回的也是一个Object对象，其本质是封装用户信息的UserDetails封装类，其中包括有用户名、密码、权限、是否过期等。

```
@GetMapping("/getuserBySession")
    @ResponseBody
    public void getUser(HttpSession session) {
        // 从当前HttpSession获取绑定到此会话的所有对象的名称
        Enumeration<String> names = session.getAttributeNames();
        while (names.hasMoreElements()){
            // 获取HttpSession中会话名称
            String element = names.nextElement();
            // 获取HttpSession中的应用上下文
            SecurityContextImpl attribute = (SecurityContextImpl) session.getAttribute(element);
            System.out.println("element: "+element);
            System.out.println("attribute: "+attribute);
            // 获取用户相关信息
            Authentication authentication = attribute.getAuthentication();
            UserDetails principal = (UserDetails)authentication.getPrincipal();
            System.out.println(principal);
            System.out.println("username: "+principal.getUsername());
        }
    }
```

以Debug模式重启项目，浏览器访问http://localhost:8080/随意查看一个影片详情进行用户登录。登录成功后，在保证当前浏览器未关闭的情况下，使用同一浏览器执行http://localhost:8080/getuserBySession来获取用户详情。

![](E:\java框架\SpringBoot课件\imgs\chap07\13.png)

(2)使用SecurityContextHolder获取用户信息

在FilmeController控制类中新增一个获取当前会话用户信息的getUser2()方法

```
/**
     * 通过Security提供的SecurityContextHolder获取登录用户信息
     */
    @GetMapping("/getuserByContext")
    @ResponseBody
    public void getUser2() {
        // 获取应用上下文
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("userDetails: "+context);
        // 获取用户相关信息
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        System.out.println(principal);
        System.out.println("username: "+principal.getUsername());
    }
```

### 5.记住我功能

![](E:\java框架\SpringBoot课件\imgs\chap07\14.png)

记住我相关的主要方法及说明

| 方法                                                       | 描述                                                         |
| ---------------------------------------------------------- | ------------------------------------------------------------ |
| remeberMeParameter(String remeberMeParameter)              | 指示在登录时记住用户的HTTP参数                               |
| key(String key)                                            | 记住我认证生成的Token令牌表示                                |
| tokenValiditySeconds(int tokenValiditySeconds)             | 记住我Token令牌有效期，单位为秒                              |
| tokenRepository(PersistentTokenRepository tokenRepository) | 指定要使用的PersistentTokenRepository，用来配置持久化Token令牌 |
| alwaysRemember(boolean alwaysRemember)                     | 是否应该始终创建记住我Cookie，默认是false                    |
| clearAuthentication(boolean clearAuthentication)           | 是否设置Cookie为安全的，若true,则必须通过HTTPS进行连接请求   |

(1)基于简单加密Token的方式

在之前创建的项目用户登录页login.html中新增一个记住我功能勾选框

```
<div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="rememberme">记住我
        </label>
    </div>
```

打开SecurityConfig类，重写configure(HttpSecurity http)方法进行记住我功能配置

```
 @Override
    protected void configure(HttpSecurity http) throws Exception {
              http.authorizeRequests()
                .antMatchers("/").permitAll()
                // 需要对static文件夹下静态资源进行统一放行
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();
        // 自定义用户登录控制
              http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
        // 自定义用户退出控制
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");
        // 定制Remember-me记住我功能
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(200);
    }
```

(2)基于简单加密Token的方式效果测试

重启项目进行效果测试，通过浏览器访问http://localhost:8080/userLogin

![](E:\java框架\SpringBoot课件\imgs\chap07\15.png)

在登录界面输入正确的用户名和密码信息，同时勾选记住我功能，就会默认跳转到项目首页index.html，重新打开浏览器访问项目首页，直接查看影片详情

(3)基于持久化Token方式

需要在数据库中创建一个存储cookie信息的持续登录用户表

在springbootdata数据库创建表

```
create table persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null);
```

打开SecurityConfig类，重写configure(HttpSecurity http)方法进行记住我功能配置

```
@Override
    protected void configure(HttpSecurity http) throws Exception {
              http.authorizeRequests()
                .antMatchers("/").permitAll()
                // 需要对static文件夹下静态资源进行统一放行
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasRole("common")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();
        // 自定义用户登录控制
              http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
        // 自定义用户退出控制
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");
        // 定制Remember-me记住我功能
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(200)
                .tokenRepository(tokenRepository());
    }
    /**
     * 持久化Token存储
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }
```

(4)基于持久化Token方式效果测试

重启项目进行效果测试，通过浏览器访问项目首页，输入正确的账户信息，勾选记住我后跳转到项目首页index.html,查看数据库中创建的存储cookie信息的持续登录用户表。

![](E:\java框架\SpringBoot课件\imgs\chap07\16.png)

重新打开刚才使用的浏览器，访问项目首页并直接查看影片详情，会发现无需重新登录就可以直接访问。此时，再次查看数据库中表数据信息。

返回到浏览器首页，单击首页上方的用户“注销”连接，在Token有效期内进行用户手动注销。此时，再次查看数据库中表数据信息。

### 6.CSRF防护功能

**跨站请求伪造**（英语：Cross-site request forgery），也被称为 **one-click attack** 或者 **session riding**，通常缩写为 **CSRF** 或者 **XSRF**， 是一种挟制用户在当前已登录的Web[应用程序](https://baike.baidu.com/item/应用程序/5985445)上执行非本意的操作的攻击方法。跟[跨网站脚本](https://baike.baidu.com/item/跨网站脚本/23316003)（XSS）相比，**XSS** 利用的是用户对指定网站的信任，CSRF 利用的是[网站](https://baike.baidu.com/item/网站/155722)对用户[网页浏览器](https://baike.baidu.com/item/网页浏览器/8309940)的信任。

跨站请求攻击，简单地说，是攻击者通过一些技术手段欺骗用户的浏览器去访问一个自己曾经认证过的网站并运行一些操作（如发邮件，发消息，甚至财产操作如转账和购买商品）。由于浏览器曾经认证过，所以被访问的网站会认为是真正的用户操作而去运行。这利用了web中用户身份验证的一个漏洞：**简单的身份验证只能保证请求发自某个用户的浏览器，却不能保证请求本身是用户自愿发出的**。

**例子**

假如一家银行用以运行转账操作的URL地址如下：http://www.examplebank.com/withdraw?account=AccoutName&amount=1000&for=PayeeName

那么，一个恶意攻击者可以在另一个网站上放置如下代码： <img src="http://www.examplebank.com/withdraw?account=Alice&amount=1000&for=Badman">

如果有账户名为Alice的用户访问了恶意站点，而她之前刚访问过银行不久，登录信息尚未过期，那么她就会损失1000资金。

这种恶意的网址可以有很多种形式，藏身于网页中的许多地方。此外，攻击者也不需要控制放置恶意网址的网站。例如他可以将这种地址藏在论坛，博客等任何[用户生成内容](https://baike.baidu.com/item/用户生成内容)的网站中。这意味着**如果服务端没有合适的防御措施的话，用户即使访问熟悉的可信网站也有受攻击的危险**。

透过例子能够看出，攻击者并不能通过CSRF攻击来直接获取用户的账户控制权，也不能直接窃取用户的任何信息。他们能做到的，是**欺骗用户浏览器，让其以用户的名义运行操作**。

防御措施

- 验证HTTP Referer字段
- 在请求地址中添加Token并验证
- 在HTTP头中自定义属性并验证

(1)CSRF防护功能关闭

1)创建数据修改页面

模板目录下添加csf/csrfTest.html

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>用户修改</title>
</head>
<body>
<div align="center">
    <form method="post" th:action="@{/updateUser}">
      <!--  <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>-->
        用户名: <input type="text" name="username" /><br />
        密&nbsp;&nbsp;码: <input type="password" name="password" /><br />
        <button type="submit">修改</button>
    </form>
</div>
</body>
</html>
```

2）编写后台控制层方法，编写的toUpdate()方法用于向用户修改页面跳转，updateUser()方法用于对用户修改提交数据处理。其中，在updateUser()方法中只是演示了获取的请求参数，没有具体的业务实现。

```
@Controller
public class CSRFController {
    // 向用户修改页跳转
    @GetMapping("/toUpdate")
    public String toUpdate() {
        return "csrf/csrfTest";
    }
    // 用户修改提交处理
    @ResponseBody
    @PostMapping(value = "/updateUser")
    public String updateUser(@RequestParam String username, @RequestParam String password,
                             HttpServletRequest request) {
        System.out.println(username);
        System.out.println(password);
        String csrf_token = request.getParameter("_csrf");
        System.out.println(csrf_token);
        return "ok";
    }
}
```

3）重启项目，通过浏览器访问http://localhost:8080/toUpdate由于前面配置了请求拦截，会先被拦截跳转到用户登录页面。在用户登录页面输入正确的用户信息后，就会自动跳转到用户修改页面

![](E:\java框架\SpringBoot课件\imgs\chap07\17.png)

整合Spring Security安全框架后，项目默认启用了CSRF安全防护功能，项目中所有涉及到数据修改方式的请求都会被拦截。

针对这种情况，可以有两种处理方式：

- 直接关闭Security默认开启的CSRF防御功能
- 配置Security需要的CSRF Token

配置类SecurityConfig，在重写的configure(HttpSecurity http)方法中进行关闭配置即可

```
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable();
	...
}
```

Spring Security提供的CSRF Token配置，主要有：

- 针对Form表单数据修改的CSRF Token配置
- 针对Ajax数据修改请求的CSRF Token配置

(2)针对From表单数据修改的CSRF Token配置

Form表单类型的数据修改请求

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>用户修改</title>
</head>
<body>
<div align="center">
    <form method="post" th:action="@{/updateUser}">
      <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
        用户名: <input type="text" name="username" /><br />
        密&nbsp;&nbsp;码: <input type="password" name="password" /><br />
        <button type="submit">修改</button>
    </form>
</div>
</body>
</html>
```

(3)针对Ajax数据修改请求的CSRF Token配置

在页面<head>标签中添加<meta>子标签，并配置CSRF Token信息

```
<html>
<head>
	<meta name="_csrf" th:content="${_csrf.token}"/>
	<meta name="_csrf_header" th:content="${_csrf.headerName}"/>
</head>
…

```

在具体的Ajax请求中获取<meta>子标签中设置的CSRF Token信息并绑定在HTTP请求头中进行请求验证

```
$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
```

## 7.6Security管理前端页面

### 1.添加thymeleaf-extras-springsecurity5依赖启动器

```
 <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-springsecurity5</artifactId>
        </dependency>
```

### 2.修改前端页面，使用Security相关标签进行页面控制

在index.html页面中引入Security安全标签

```
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" 
       xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>影视直播厅</title>
</head>
```

页面顶部通过“xmlns:sec”引入了Security安全标签，页面中根据需要编写了4个<div>模块.

```
<div sec:authorize="isAnonymous()">
	<h2 align="center">游客您好，如果想查看电影<a th:href="@{/userLogin}">请登录</a></h2>
</div>
<div sec:authorize="isAuthenticated()">
	<h2 align="center"><span sec:authentication="name" style="color: #007bff"></span>您好，您的用户权限为<span sec:authentication="principal.authorities" style="color:darkkhaki"></span>，您有权观看以下电影</h2>
	<form th:action="@{/mylogout}" method="post">
		<input th:type="submit" th:value="注销" />
	</form>
</div>
<hr>
<div sec:authorize="hasRole('common')">
	<h3>普通电影</h3>
	<ul>
		<li><a th:href="@{/detail/common/1}">飞驰人生</a></li>
		<li><a th:href="@{/detail/common/2}">夏洛特烦恼</a></li>
	</ul>
</div>
<div sec:authorize="hasAuthority('ROLE_vip')">
	<h3>VIP专享</h3>
	<ul>
		<li><a th:href="@{/detail/vip/1}">速度与激情</a></li>
		<li><a th:href="@{/detail/vip/2}">猩球崛起</a></li>
	</ul>
</div>
```

### 3.效果测试

重启项目进行效果测试，项目启动成功后，通过浏览器访问http://localhost:8080/

![](E:\java框架\SpringBoot课件\imgs\chap07\18.png)


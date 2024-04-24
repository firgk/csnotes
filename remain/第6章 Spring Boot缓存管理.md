# 第6章 Spring Boot缓存管理

缓存是分布式系统中的重要组件，主要解决数据库数据的高并发访问。在实际开发中，尤其是用户访问量较大的网站，用户对高频热点数据的访问非常频繁，为了提高服务器访问性能、减少数据库的压力、提高用户体验，使用缓存显得尤为重要。

## 6.1Spring Boot 默认缓存管理

Spring框架支持透明地向应用程序添加缓存并对缓存进行管理，其管理缓存的核心是将缓存应用于操作数据的方法中，从而减少操作数据的次数，同时不会对程序本身造成任何干扰。

Spring Boot继承了Spring框架的缓存功能，使用@EnableCaching注解开启基于注解的缓存支持。

### 1.基础环境搭建

(1)准备数据，创建项目

1)创建Spring Boot项目，引入相关依赖

- 这里使用之前创建的springbootdata的数据库，该数据库有两个表     t_article和t_comment，这两个表预先插入几条测试数据。
- 使用Spring Initializr方式创建一个Spring Boot项目，在Dependencies依赖选择项中JPA依赖、MySQL依赖和Web依赖。

![](E:\java框架\SpringBoot课件\imgs\chap06\1.png)

**注：mysql依赖要与自己的数据库版本对应**

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
```

2)编写数据库表对应的实体类

创建domain包，在包下新建类Comment、并使用JPA相关注解配置映射关系

```
@Entity(name="t_comment") // 设置ORM实体类，并指定映射的表名
public class Comment {
    @Id   // 表明映射对应的主键id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增策略
    private Integer id;
    private String content;
    private String author;
    @Column(name = "a_id") //指定映射的表字段名
    private Integer aId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", aId=" + aId +
                '}';
    }
}
```

3）编写数据库操作的Repository接口文件CommentRepository

创建repository包，在包下创建用于操作实体类Comment的Repository接口，该接口继承JpaRepository

```
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    // 根据评论id修改评论作者评论作者author
    @Transactional
    @Modifying
    @Query("UPDATE t_comment c SET c.author= ?1 WHERE  c.id = ?2")
    public int updateComment(String author,Integer id);

}
```

4）编写业务操作类Service文件CommentService，在该类中编写数据的查询、修改和删除操作 

创建service包，在包下创建一个用于Comment相关业务操作的实体类

```
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    // 根据评论id查询评论信息
    public Comment findById(int comment_id){
        Optional<Comment> optional = commentRepository.findById(comment_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // 更新评论信息
    public Comment updateComment(Comment comment){
        commentRepository.updateComment(comment.getAuthor(), comment.getaId());
        return comment;
    }
    // 删除评论信息
    public void deleteComment(int comment_id){
        commentRepository.deleteById(comment_id);
    }
}
```

5）编写Web访问层Controller文件CommentController，使用注入的CommentService实例对象编写对Comment评论数据的查询、修改和删除方法。

创建controller包，在包下创建一个访问层Controller文件CommentController

```
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/get/{id}")
    public Comment findById(@PathVariable("id") int comment_id){
        Comment comment = commentService.findById(comment_id);
        return comment;
    }

    @GetMapping("/update/{id}/{author}")
    public Comment updateComment(@PathVariable("id") int comment_id,
                                 @PathVariable("author") String author){
        Comment comment = commentService.findById(comment_id);
        comment.setAuthor(author);
        Comment updateComment = commentService.updateComment(comment);
        return updateComment;
    }

    @GetMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id") int comment_id){
        commentService.deleteComment(comment_id);
    }
}
```

(2)编写配置文件

```
#mysql数据库连接配置
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdata?serverTimezone=UTC&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
# 显示使用JPA进行数据库查询的SQL语句
spring.jpa.show-sql=true
```

(3)项目测试

项目启动成功后，在浏览器上访问http://localhost:8080/get/1，浏览器每刷新一次，控制台会新输出一条SQL语句

![](E:\java框架\SpringBoot课件\imgs\chap06\2.png)

### 2.Spring Boot 默认缓存体验

(1)使用@EnableCaching注解开启基于注解的缓存支持

```
@EnableCaching //  开启Spring Boot基于注解的缓存管理支持
@SpringBootApplication
public class Demo06Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo06Application.class, args);
    }

}
```

(2)使用@Cacheable注解对数据操作方法进行缓存管理

修改CommentService类中的方法

```
@Cacheable(cacheNames = "comment")
    public Comment findById(int comment_id){
        Optional<Comment> optional = commentRepository.findById(comment_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
```

(3)项目测试

项目启动成功后，在浏览器上访问http://localhost:8080/get/1，不论浏览器刷新多少次，页面的查询结果都会显示同一条数据

![](E:\java框架\SpringBoot课件\imgs\chap06\3.png)

## 6.2Spring Boot 缓存注解介绍

### 1.@EnableCaching注解
@EnableCaching是由Spring框架提供的，Spring Boot框架对该注解进行了继承，该注解需要配置在类上（在Spring Boot中，通常配置在项目启动类上），用于开启基于注解的缓存支持。

### 2.@Cacheable注解

@Cacheable注解也是由Spring框架提供的，可以作用于类或方法（通常用在数据查询方法上），用于对方法结果进行缓存存储。
@Cacheable注解的执行顺序是，先进行缓存查询，如果为空则进行方法查询，并将结果进行缓存；如果缓存中有数据，不进行方法查询，而是直接使用缓存数据。

**@Cacheable注解(属性)**

| **属性名**       | **说明**                                                  |
| ---------------- | --------------------------------------------------------- |
| value/cacheNames | 指定缓存空间的名称，必配属性。这两个属性二选一使用        |
| key              | 指定缓存数据的key，默认使用方法参数值，可以使用SpEL表达式 |
| keyGenerator     | 指定缓存数据的key的生成器，与key属性二选一使用            |
| cacheManager     | 指定缓存管理器                                            |
| cacheResolver    | 指定缓存解析器，与cacheManager属性二选一使用              |
| condition        | 指定在符合某条件下，进行数据缓存                          |
| unless           | 指定在符合某条件下，不进行数据缓存                        |
| sync             | 指定是否使用异步缓存。默认false                           |

Cache SpEL 表达式

| 名字          | 位置               | 描述                                                         | 示例                 |
| ------------- | ------------------ | ------------------------------------------------------------ | -------------------- |
| methodName    | root object        | 当前被调用的方法名                                           | #root.methodName     |
| method        | root object        | 当前被调用的方法                                             | #root.method.name    |
| target        | root object        | 当前被调用的目标对象                                         | #root.target         |
| targetClass   | root object        | 当前被调用的目标对象类                                       | #root.targetClass    |
| args          | root object        | 当前被调用的方法的参数列表                                   | #root.args[0]        |
| caches        | root object        | 当前方法调用使用的缓存列表（如@Cacheable(value={"cache1", "cache2"})），则有两个cache | #root.caches[0].name |
| argument name | evaluation context | 方法参数的名字. 可以直接 #参数名 ，也可以使用 #p0或#a0 的形式，0代表参数的索引； | #iban 、 #a0 、  #p0 |
| result        | evaluation context | 方法执行后的返回值（仅当方法执行之后的判断有效，如‘unless’，’cache put’的表达式 ’cache evict’的表达式beforeInvocation=false） | #result              |

### 3.@CachePut注解

@CachePut注解是由Spring框架提供的，可以作用于类或方法（通常用在数据更新方法上），该注解的作用是更新缓存数据。@CachePut注解的执行顺序是，先进行方法调用，然后将方法结果更新到缓存中。
@CachePut注解也提供了多个属性，这些属性与@Cacheable注解的属性完全相同。

### 4.@CacheEvict注解

@CacheEvict注解是由Spring框架提供的，可以作用于类或方法（通常用在数据删除方法上），该注解的作用是删除缓存数据。@CacheEvict注解的默认执行顺序是，先进行方法调用，然后将缓存进行清除。
@CacheEvict注解也提供了多个属性，这些属性与@Cacheable注解的属性基本相同，除此之外，还额外提供了两个特殊属性allEntries和beforeInvocation

（1）allEntries属性
   allEntries属性表示是否清除指定缓存空间中的所有缓存数据，默认值为false（即默认只删除指定key对应的缓存数据）。
（2）beforeInvocation属性
   beforeInvocation属性表示是否在方法执行之前进行缓存清除，默认值为false（即默认在执行方法后再进行缓存清除）。

### 5.@Caching注解

@Caching注解用于针对复杂规则的数据缓存管理，可以作用于类或方法，在@Caching注解内部包含有Cacheable、put和evict三个属性，分别对应于@Cacheable、@CachePut和@CacheEvict三个注解。

```
@Caching(cacheable={@Cacheable(cacheNames ="comment",key = "#id")},
	put = {@CachePut(cacheNames = "comment",key = "#result.author")})
	public Comment getComment(int comment_id){
	return commentRepository.findById(comment_id).get();
}
```

### 6.@CacheConfig注解

@CacheConfig注解使用在类上，主要用于统筹管理类中所有使用@Cacheable、@CachePut和@CacheEvict注解标注方法中的公共属性，这些公共属性包括有cacheNames、keyGenerator、cacheManager和cacheResolver。

```
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

## 6.3Spring Boot整合Redis缓存实现

### 1.Spring Boot支持的缓存组件

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

### 2.基于注解的Redis缓存实现

(1)添加Spring Data Redis 依赖启动器

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

(2)Redis服务连接配置

```
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
```

(3)使用@Cacheable、@CachePut、@CacheEvict注解定制缓存管理

对CommentService类中的方法进行修改使用@Cacheable、@CachePut、@CacheEvict三个注解定制缓存管理

```
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

(4)基于注解的Redis查询缓存测试

项目启动成功后，通过浏览器访问http://localhost:8080/get/1

会出现如下错误提示

![](E:\java框架\SpringBoot课件\imgs\chap06\4.png)

提示信息要求对应实体类必须实现序列化。

(5)将缓存对象实现序列化

```
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

(6)基于注解的Redis缓存查询测试

项目启动成功后，通过浏览器访问http://localhost:8080/get/1，并重复刷新浏览器查询同一条数据信息

![](E:\java框架\SpringBoot课件\imgs\chap06\5.png)

(7)基于注解的Redis缓存更新测试

项目启动成功后，通过浏览器访问http://localhost:8080/update/1/shitou，接着，继续访问http://localhost:8080/get/1

执行updateComment()方法更新id为1的数据时执行了一条更新SQL语句，后续调用findById()方法查询id为1的用户评论信息时没有执行查询SQL语句。

(8)基于注解的Redis缓存删除测试

项目启动成功后，通过浏览器访问http://localhost:8080/delete/1，接着，继续访http://localhost:8080/get/1

```
#对于基于注解的Redis缓存数据统一设置有效期为1分钟，单位毫秒
spring.cache.redis.time-to-live=60000
#不灵活，对基于API的Redis缓存实现没效果
```

### 3.基于API的Redis缓存实现

(1)使用Redis API 进行业务数据缓存管理

编写一个进行业务处理的类ApiCommentService,使用@Autowired注解注入Redis API中常用的RedisTemplate（类似于Java基础API中的JdbcTemplate）；然后在数据查询、修改和删除三个方法中，根据业务需求分别进行数据缓存查询、缓存存储、缓存更新和缓存删除。同时，Comment数据对应缓存管理的key值都手动设置了一个前缀“comment_”，这是针对不同业务数据进行缓存管理设置的唯一key，避免与其他业务缓存数据的key重复。

```
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

(2)编写Web访问层Controller文件ApiCommentController 

在类上加入了@RequestMapping(“/api”)注解用于窄化请求，并通过@Autowired注解注入了新编写的ApiCommentService实例对象，然后调用ApiCommentService中的相关方法进行数据查询、修改和删除。

```
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

(3)基于API的Redis缓存实现的相关配置

- 基于API的Redis缓存实现不需要@EnableCaching注解开启基于注解的缓存支持。
- 基于API的Redis缓存实现需要在Spring Boot项目的pom.xml文件中引入Redis依赖启动器，并在配置文件中进行Redis服务连接配置，同时将进行数据存储的Comment实体类实现序列化接口。
- 缓存测试与基于注解的Redis缓存实现的测试完全一样。

**注意：整合其他缓存管理，需要添加依赖spring-boot-starter-cache**

## 6.4自定义Redis缓存序列化机制

### 1.自定义RedisTemplate

(1)Redis API 默认序列化机制

基于Redis API的Redis缓存实现是使用RedisTemplate模板进行数据缓存操作的.

```
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

1)使用RedisTemplate进行Redis数据缓存操作时，内部默认使用的是JdkSerializationRedisSerializer序列化方式，所以进行数据缓存的实体类必须实现JDK自带的序列化接口（例如Serializable）；
2)使用RedisTemplate进行Redis数据缓存操作时，如果自定义了缓存序列化方式defaultSerializer，那么将使用自定义的序列化方式。

(2)自定义RedisTemplate序列化机制

在项目中引入Redis依赖后，Spring Boot提供的RedisAutoConfiguration自动配置会生效。打开RedisAutoConfiguration类，查看内部源码中关于RedisTemplate的定义方式可知：

1)在Redis自动配置类中，通过Redis连接工厂RedisConnectionFactory初始化了一个RedisTemplate；该类上方添加了@ConditionalOnMissingBean注解（顾名思义，当某个Bean不存在时生效），用来表明如果开发者自定义了一个名为redisTemplate的Bean，则该默认初始化的RedisTemplate会被覆盖。
2)如果想要使用自定义序列化方式的RedisTemplate进行数据缓存操作，可以参考上述核心代码创建一个名为redisTemplate的Bean组件，并在该组件中设置对应的序列化方式即可。

在项目中创建创建一个Redis自定义配置类RedisConfig，通过@Configuration注解定义了一个RedisConfig配置类，并使用@Bean注解注入了一个默认名称为方法名的redisTemplate组件（注意，该Bean组件名称必须是redisTemplate）。在定义的Bean组件中，自定义了一个RedisTemplate，使用自定义的Jackson2JsonRedisSerializer数据序列化方式；在定制序列化方式中，定义了一个ObjectMapper用于进行数据转换设置。

```
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

(3)效果测试

项目启动成功后，通过浏览器访问http://localhost:8080/api/get/3，并重复刷新浏览器查看同一条数据信息

### 2.自定义RedisCacheManager

(1)Redis 注解默认序列化机制

1)Spring Boot整合Redis组件提供的缓存自动配置类RedisCacheConfiguration,其内部是通过Redis连接工厂RedisConnectionFactory定义了一个缓存管理器RedisCacheManager；同时定制RedisCacheManager时，也默认使用了JdkSerializationRedisSerializer序列化方式。
2)如果想要使用自定义序列化方式的RedisCacheManager进行数据缓存操作，可以创建一个名为cacheManager的Bean组件，并在该组件中设置对应的序列化方式即可。

(2)自定义RedisCacheManager

在RedisConfig类中添加方法cacheManager

```
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

(3)效果测试

项目启动成功后，通过浏览器访问http://localhost:8080/api/get/3，并重复刷新浏览器查看同一条数据信息

执行findById()方法正确查询出用户评论信息Comment，重复进行同样的查询操作，数据库只执行了一次SQL语句

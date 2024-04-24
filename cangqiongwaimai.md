


## apache poi

Apache POI是一个流行的开源Java库，提供了一组API，用于处理各种微软文档格式，如Word、Excel和PowerPoint。它允许用户以编程方式创建、修改和提取这些文档的内容

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.9</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.9</version>
</dependency>
```

```java
  
package com.lgh.test;  
  
import org.apache.poi.hssf.util.HSSFColor;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
import org.junit.jupiter.api.Test;  
import org.springframework.boot.test.context.SpringBootTest;  
  
import java.io.*;  
  
@SpringBootTest  
public class POI {  
    @Test  
    public void write() throws Exception {  
        //创建一个excel文件  
        XSSFWorkbook excel=new XSSFWorkbook();  
        //在excel中创建一页  
        XSSFSheet sheet=excel.createSheet("info");  
        XSSFRow row=sheet.createRow(1);  
  
        row.createCell(1).setCellValue("姓名");  
        row.createCell(2).setCellValue("城市");  
  
        row= sheet.createRow(2);  
        row.createCell(1).setCellValue("张三");  
        row.createCell(2).setCellValue("北京");  
  
        FileOutputStream outputStream=new FileOutputStream(new File("E:/test/info.xlsx"));  
        excel.write(outputStream);  
  
        outputStream.close();  
    }  
  
  
    @Test  
    public void read() throws Exception {  
        InputStream inputStream=new FileInputStream((new File("E:/test/info.xlsx")));  
        XSSFWorkbook excel=new XSSFWorkbook(inputStream);  
        XSSFSheet sheet=excel.getSheetAt(0);  
        int lastRowNum=sheet.getLastRowNum();  
        for (int i = 1; i<=lastRowNum; i++) {  
            //get line  
            XSSFRow row=sheet.getRow(i);  
            //read line  
            String cellValue=row.getCell(1).getStringCellValue();  
            String cellValue2=row.getCell(2).getStringCellValue();  
            System.out.println(cellValue+":"+cellValue2);  
        }  
        inputStream.close();  
    }  
}
```





## Swagger注解


```

@Api
类上
Controller


@ApiOperation
方法上
例如Controller中的方法


@ApiModel
类上
entity DTO VO

@ApiModelProperty
属性上,描述属性信息
属性方法

```




![](http://leaweihou.site:1003/photobed/2024_03_10_12_57_33.png)


![](http://leaweihou.site:1003/photobed/2024_03_10_12_57_56.png)







## HttpClient


使用java发送get和post请求

```xml
<dependency>  
    <groupId>org.apache.httpcomponents</groupId>  
    <artifactId>httpclient</artifactId>  
</dependency>
```

```java
package com.lgh.test;  
  
import org.apache.commons.codec.StringEncoder;  
import org.apache.http.HttpEntity;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONException;  
import org.json.JSONObject;  
import org.junit.jupiter.api.Test;  
import org.springframework.boot.test.context.SpringBootTest;  
import org.springframework.web.servlet.tags.EscapeBodyTag;  
import java.io.UnsupportedEncodingException;  
import java.net.ServerSocket;  
  
@SpringBootTest  
public class HttpClientTest {  
    @Test  
    public void testGet() throws Exception {  
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();  
        HttpGet httpGet=new HttpGet("http://www.baidu.com");  
  
        CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);  
  
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();  
        System.out.println("服务器返回的状态码是："+statusCode);  
  
        HttpEntity httpEntity=closeableHttpResponse.getEntity();  
        String body= EntityUtils.toString(httpEntity);  
  
        System.out.println(body);  
  
        closeableHttpResponse.close();  
        closeableHttpClient.close();  
    }  
    @Test  
    public void testPost() throws Exception {  
        CloseableHttpClient httpClient= HttpClients.createDefault();  
        HttpPost httpPost=new HttpPost("http://");  
        JSONObject jsonObject=new JSONObject();  
        jsonObject.put("username","admin");  
        jsonObject.put("password","123456");  
        StringEntity stringEntity=new StringEntity(jsonObject.toString());  
        stringEntity.setContentEncoding("utf_8");  
        stringEntity.setContentType("application/json");  
        httpPost.setEntity(stringEntity);  
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);  
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();  
        System.out.println("响应码为:"+statusCode);  
        HttpEntity httpEntity=closeableHttpResponse.getEntity();  
        String body=EntityUtils.toString(httpEntity);  
        System.out.println("相应数据为"+body);  
        closeableHttpResponse.close();  
        httpClient.close();  
    }  
}
```

> HttpClientUtil 封装好的工具类

```java
package com.sky.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http工具类
 */
public class HttpClientUtil {

    static final  int TIMEOUT_MSEC = 5 * 1000;

    /**
     * 发送GET方式请求
     * @param url
     * @param paramMap
     * @return
     */
    public static String doGet(String url,Map<String,String> paramMap){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = "";
        CloseableHttpResponse response = null;

        try{
            URIBuilder builder = new URIBuilder(url);
            if(paramMap != null){
                for (String key : paramMap.keySet()) {
                    builder.addParameter(key,paramMap.get(key));
                }
            }
            URI uri = builder.build();

            //创建GET请求
            HttpGet httpGet = new HttpGet(uri);

            //发送请求
            response = httpClient.execute(httpGet);

            //判断响应状态
            if(response.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 发送POST方式请求
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> paramMap) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建参数列表
            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    /**
     * 发送POST方式请求
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String doPost4Json(String url, Map<String, String> paramMap) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            if (paramMap != null) {
                //构造json格式数据
                JSONObject jsonObject = new JSONObject();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    jsonObject.put(param.getKey(),param.getValue());
                }
                StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
                //设置请求编码
                entity.setContentEncoding("utf-8");
                //设置数据类型
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }
    private static RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MSEC)
                .setConnectionRequestTimeout(TIMEOUT_MSEC)
                .setSocketTimeout(TIMEOUT_MSEC).build();
    }

}

```




## java WebSocket


```xml
websocket
```

websocket.html

```java
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebSocket Demo</title>
</head>
<body>
    <input id="text" type="text" />
    <button onclick="send()">发送消息</button>
    <button onclick="closeWebSocket()">关闭连接</button>
    <div id="message">
    </div>
</body>
<script type="text/javascript">
    var websocket = null;
    var clientId = Math.random().toString(36).substr(2);

    //判断当前浏览器是否支持WebSocket
    if('WebSocket' in window){
        //连接WebSocket节点
        websocket = new WebSocket("ws://localhost:8080/ws/"+clientId);
    }
    else{
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function(){
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function(){
        setMessageInnerHTML("连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function(event){
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function(){
        setMessageInnerHTML("close");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML){
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //发送消息
    function send(){
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
	
	//关闭连接
    function closeWebSocket() {
        websocket.close();
    }
</script>
</html>

```

配置类

```java
package com.sky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类，用于注册WebSocket的Bean
 */
@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}


```

工具类

```java
package com.sky.websocket;

import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket服务
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    //存放会话对象
    private static Map<String, Session> sessionMap = new HashMap();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端：" + sid + "建立连接");
        sessionMap.put(sid, session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        System.out.println("收到来自客户端：" + sid + "的信息:" + message);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("连接断开:" + sid);
        sessionMap.remove(sid);
    }

    /**
     * 群发
     *
     * @param message
     */
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

```

使用

```java
package com.sky.task;

import com.sky.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WebSocketTask {
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 通过WebSocket每隔5秒向客户端发送消息
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMessageToClient() {
        webSocketServer.sendToAllClient("这是来自服务端的消息：" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }
}
```






## SpringTask


corn表达式

```
1 2 3 4 5 6 7
秒 分钟 小时  日 月 周 年

```


```xml
spring-context
```



```java
启动类
@EnableScheduling
类
@Scheduled (corn="0/5 * * * * ?")

```




## SpringCash

```xml
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-cache</artifactId>  
</dependency>
```


```
@EnableCashing # 启动类注解
@Casheable # 查询缓存
@CashePut # 返回数据放到缓存
@CashEvict # 删除缓存
```



> 用于Controller

```java
@CachePut(cacheNames = "userCash",key="#result.id") // #result.id 取到的是返回值

```


```java
@Cacheable(cacheNames="userCache",key="#id") //key的生成 userCache::10public Result<DishVO> er(@PathVariable Long id){  
    log.info("根据id查询菜品");  
    DishVO dishVO=dishService.getByIdWithFlavor(id);  
    return Result.success(dishVO);  
}
```



```java
@CacheEvict(cacheNames="userCache",key="#id")
or
@CacheEcivt(cacheNames="userCache",allEvtries=true)
```


## redis



全都是key-value
value如下
```
string
hash
list
set
zset

```

![](http://leaweihou.site:1003/photobed/2024_03_11_23_23_25.png)

### 字符串

```
set name jack
get name
setex code 30 1234 # 30s 失效
setnx name jack # 不存在的时候设置,存在的时候不设置 返回 1/0
```

### HASH

```
hset key field value
hget key field
hdel key field
hkeys key  #获取所有字段
hvals key  #获取哈希的所有值
```

```
hset 100 name xiaoming
hset 100 age 22

hget 100 name
hget 100 age

hdel 100 name
hkeys 100 
hvals 100 
```

### 列表

```
lpush key value [value2] # 表头 插入一个或者多个元素
lrange key start stop # 获取指定范围内的元素
rpop key # 移除列表最后一个元素
llen key # 获取列表长度
```



```
lpush mylist a b c 
lrange mylist 0 -1 # 获取所有
rpop mylist
llen mylist 
```


### 集合

```
sadd key num1 [num2] # 添加
smembers key # 返回所有成员
scard key # 获取数量

sinter key1 [key2] # 返回指定集合的交集
sunion key1 [key2] # 返回并
srem key member1 [member2] # 删除集合中的一个或多个成员
```

### 有序集合

![](http://leaweihou.site:1003/photobed/2024_03_12_11_15_29.png)

```
zadd key score1 member1 [score2 member2]
zrenge key start stop [withscores] # 返回区间成员
zincreby key increment member # 有序集合中对指定成员的分数怎加member
zrem key member [member..] # 移除成员

```



```
zadd zset1 10.0 a 10.5 b
zrenge zset1 0 -1 # 所有
zrenge zset1 0 -1 with scores # 所有
zincreby zset1 5 a
zrem zset1 a 
```

> 通用

```
keys patternX # 匹配key
exists key
type key
del key
```

```
keys *
keys set*

exists name
type name
del name
```




### use Redis in Java

Spring Data Redis

1. 
```xml
<dependency>
```

2. 
```yml
spring:
 redis:
	 host:
	 port:
	 password:
	database: 10 # 默认使用0号数据库
```

3.  配置类,创建ResidTemplate对象

```java
@Configuration  
@Slf4j  
public class RedisConfiguration {  
    @Bean // 此处使用注解可以注入RedisConnectionFactory  
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){  
        RedisTemplate redisTemplate = new RedisTemplate();  
        redisTemplate.setConnectionFactory(redisConnectionFactory);  
        redisTemplate.setKeySerializer(new StringRedisSerializer());  
        return redisTemplate;  
    }  
}
```

4. 通过RedisTemplate对象操作Redis


```java
@Autowired  
private RedisTemplate redisTemplate;  
@Test  
public void springRedisTest(){  
    System.out.println(redisTemplate);  
    ValueOperations valueOperations =redisTemplate.opsForValue();  
    HashOperations hashOperations=redisTemplate.opsForHash();  
    ListOperations listOperations= redisTemplate.opsForList();  
    SetOperations setOperations=redisTemplate.opsForSet();  
    ZSetOperations zSetOperations=redisTemplate.opsForZSet();  
}
```



简单的redis操作示例

```java
package com.sky;  
import org.junit.jupiter.api.Test;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.test.context.SpringBootTest;  
import org.springframework.data.redis.core.*;  
  
@SpringBootTest  
public class SpringRedisTest {  
    @Autowired  
    private RedisTemplate redisTemplate;  
    @Test  
    public void testString(){  
        redisTemplate.opsForValue().set("city","beijing");  
        String city=(String) redisTemplate.opsForValue().get("city");  
        System.out.println(city);  
  
    }  
}
```



实际使用
在Controller中使用 

```java
  
//    @GetMapping("/list")  
//    @ApiOperation("根据分类id查询菜品")  
//    public Result<List<DishVO>> list(Long cateGoryId) {  
//        Dish dish=new Dish();  
//        dish.setCategoryId(cateGoryId);  
//        dish.setStatus(StatusConstant.ENABLE);  
//        List<DishVO> list=dishService.listWithFlavor(dish);  
//        return Result.success(list);  
//    }  
  
  
    @Autowired  
    private RedisTemplate redisTemplate;  
  
    @GetMapping("/list")  
    @ApiOperation("根据分类id查询菜品")  
    public Result<List<DishVO>> list(Long cateGoryId) {  
        String key = "dish_" + cateGoryId;  
        List<DishVO> list=(List<DishVO>)redisTemplate.opsForValue().get(key);  
        if(list!=null&&list.size()>0){  
            return Result.success(list);  
        }  
        Dish dish=new Dish();  
        dish.setCategoryId(cateGoryId);  
        dish.setStatus(StatusConstant.ENABLE);  
        list=dishService.listWithFlavor(dish);  
        redisTemplate.opsForValue().set(key,list);  
        return Result.success(list);  
    }
```


新增和修改需要清理缓存数据


```java
private void cleanCache(String pattern){  
    Set keys =redisTemplate.keys(pattern);  
    redisTemplate.delete(keys);  
}
```



```java
@ApiOperation("修改菜品")  
public Result update(@RequestBody DishDTO dishDTO){  
    log.info("修改菜品:{}",dishDTO);  
    dishService.updateWithFlavor(dishDTO);  
    cleanCache("dish_*");   # 此处
    return Result.success();  
}
```



## 拦截器 和 登陆验证的实现





> 拦截器

```java
package com.sky.interceptor;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工id：", empId);
            BaseContext.setCurrentId(empId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}

```


> 配置类


```java

import com.sky.interceptor.JwtTokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/**") // 添加需要拦截的路径
                .excludePathPatterns("/login"); // 排除不需要拦截的路径
    }
}


```

> JwtUtil


```java

package com.sky.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置过期时间
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }

}


```



> Controller 中



```java

@PostMapping("/login")
@ApiOperation("登录接口")
public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
    log.info("员工登录：{}", employeeLoginDTO);

    Employee employee = employeeService.login(employeeLoginDTO);

    //登录成功后，生成jwt令牌
    Map<String, Object> claims = new HashMap<>();
    claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
    String token = JwtUtil.createJWT(
            jwtProperties.getAdminSecretKey(),
            jwtProperties.getAdminTtl(),
            claims);

    EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
            .id(employee.getId())
            .userName(employee.getUsername())
            .name(employee.getName())
            .token(token)
            .build();

    return Result.success(employeeLoginVO);
}

```





## nginx

> 解决跨域和负载均衡

```conf

upstream webservers{
	  server 127.0.0.1:8081 weight=90 ;
	  #server 127.0.0.1:8088 weight=10 ;
	}

    server {
        listen       81;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html/sky;
            index  index.html index.htm;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # 反向代理,处理管理端发送的请求
        # /api/employee -> admin/employee
        location /api/ {
			proxy_pass   http://localhost:8081/admin/;
            #proxy_pass   http://webservers/admin/;
        }
		
		# 反向代理,处理用户端发送的请求
        location /user/ {
            proxy_pass   http://webservers/user/;
        }
		
		# WebSocket
		location /ws/ {
            proxy_pass   http://webservers/ws/;
			proxy_http_version 1.1;
			proxy_read_timeout 3600s;
			proxy_set_header Upgrade $http_upgrade;
			proxy_set_header Connection "$connection_upgrade";
        }

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }

```
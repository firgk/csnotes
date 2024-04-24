
> 除去了docker等比较独立大型的

微服务技术栈

  

## RestTemplate 服务远程调用 发送http请求

  

```java

  

@Bean

public RestTemplate xxrestTemplate(){

    return new RestTemplate();

}

  
  

@Autowired

private RestTemplate kkrestTemplate;

String xxurl = "http://localhost:8081/user/"+order.getUserId();

User gguser = kkrestTemplate.getForObject(xxurl, User.class);//第一个参数是路径，第二个参数是你想要拿到什么类型的数据

order.setUser(gguser);//把向用户项目拿到的数据封装到这个订单项目

```

  
  
  

## Eureka注册中心

  

第一步: 在order-server微服务中的src/main/java/cn.itcast.order/service/OrderService类，修改访问的url路径，用服务名代替ip、端口。修改为如下

```java

@Service

public class OrderService {

  

    @Autowired

    private OrderMapper orderMapper;

  

    @Autowired

    private RestTemplate kkrestTemplate;

  
  

    public Order queryOrderById(Long orderId) {

        // 1.查询订单

        Order order = orderMapper.findById(orderId);

  

        /**

         * 使用RestTemplate，向用户项目发起http请求，查询用户

         */

        //String xxurl = "http://localhost:8081/user/"+order.getUserId();

        //User gguser = kkrestTemplate.getForObject(xxurl, User.class);//第一个参数是路径，第二个参数是你想要拿到什么类型的数据

        //order.setUser(gguser);//把向用户项目拿到的数据封装到这个订单项目

  
  

        String xxurl = "http://UserService/user/"+order.getUserId();

        User gguser = kkrestTemplate.getForObject(xxurl, User.class);//第一个参数是路径，第二个参数是你想要拿到什么类型的数据

        order.setUser(gguser);//把向用户项目拿到的数据封装到这个订单项目

  
  

        // 4.返回

        return order;

    }

}

  

```

  
  

第二步: 负载均衡。在order-server微服务中的OrderApplication引导类修改为如下

```java

@MapperScan("cn.itcast.order.mapper")

@SpringBootApplication

public class OrderApplication {

  

    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class, args);

    }

  

    @Bean

    @LoadBalanced

    public RestTemplate xxrestTemplate(){

        return new RestTemplate();

    }

}

```

  
  

## Ribbon负载均衡

  
  
  

## Nacos注册中心

  

## Gateway网关

  
  

![](http://114.116.43.154:1003/photobed/2024_03_20_21_39_30.png)

  

![](http://114.116.43.154:1003/photobed/2024_03_20_21_40_20.png)

  
  

## MQ

  
  
  
  

## ES

## MQ


### SpringAMQP



## ES




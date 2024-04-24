
@RestController = @Controller + @ResponseBody组成
@Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。当然也有语义化的作用，即代表该类是充当Controller的作用
@ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会以Json字符串的形式返回给客户端，本人尝试了一下，如果返回的是String类型，则仍然是String。


@GetMapping等同于@RequestMapping
@GetMapping等同于@RequestMapping(method=RequestMethod.GET)注解如果我们想使用
传统的@RequestMapping注释实现URL处理程序，那么它应该是这样的：
@RequestMapping(value = “/get/{id}”, method = RequestMethod.GET)
新方法可以简化为：
@GetMapping("/get/{id}")
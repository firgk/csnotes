```
maven 离线安装
mvn install:install-file -Dfile=spring-boot-starter-inforsuite-10.0.2.4-2024F03.jar -DgroupId=com.cvicse.embedded -DartifactId=spring-boot-starter-inforsuite -Dversion=10.0.2.4-2024F03 -Dpackaging=jar

```




单元测试模板
```java
@SpringBootTest  
public class Test1 {  \

	@autowired
	XXXX
	
    @Test  
    public void testAdd() {  
        int a = 5;  
        int b = 3;  
        int result = XXXX.add(a, b);  
        assertEquals(8, result);  
    }  
}
```






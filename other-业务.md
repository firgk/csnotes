
## json 返回值包装 用来优化前后端传递


```java
package com.someray.vacs.common;
import lombok.Data;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private Integer code; // 编码：1成功，0和其它数字为失败
    private String msg; // 错误信息
    private T data; // 数据


    //静态方法 因而需要在内部创建类 之后再返回
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg="成功";
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        result.msg="成功";
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.msg = "失败";
        result.code = 0;
        return result;
    }
}
```




Controller

```java
package com.someray.vacs.controller;

import com.someray.vacs.common.Result;
import com.someray.vacs.entity.User;
import com.someray.vacs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserApi {
    @Autowired
    private UserService userService;
    /*
    而不是路径变量（path variables）。
    在Spring框架中，如果你的参数是通过查询字符串传递的，应该使用@RequestParam注解而不是@PathVariable。
     */
    @GetMapping("insertUser")
    public Result insertUser(@RequestParam String username, String password) {
        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            userService.insertUser(new User(username,password));
            return Result.success();
        }
        return Result.error();
    }

    @GetMapping("delUserById/{id}")
    public Result delUserById(@PathVariable String id) {
        try {
            userService.deleteUserById(Integer.parseInt(id));
        } catch (Exception e) {
            userService.deleteUserById(Integer.parseInt(id));
        }
        return Result.success();
    }

    @GetMapping("updateUserById/{id}")
    public Result updateUserById(@PathVariable String id, @RequestParam String username, String password) {
        userService.updateUser(new User(Integer.parseInt(id),username,password));
        return Result.success();
    }

    @GetMapping("getUserById/{id}")
    public Result getUserById(@PathVariable String id) {
        return Result.success(userService.getUserById(Integer.parseInt(id)));
    }

    @GetMapping("GetUserByUsername")
    public Result GetUserByUsername(@RequestParam String username){
        return Result.success(userService.getUserByUsername(username));
    }
}

```





## 为数据添加排序字段的过程
添加一个字段 sort  用来排序
查询的时候 对他使用 order by 即得到的数据为排序好的
修改的时候 还是原来的 sort
插入的时候 需要检索数据库中最大的 sort 值 之后插入的时候 新的 sort 为 sort+1






controller
```java
int maxSort = service.getMaxSort();
entity.setSort(ObjectUtils.isEmpty(maxSort) ? 0 : maxSort+1);
return super.post(entity);
```


service
```java
public int getMaxSort() {
    return dao.getMaxSort();
}
```

dao
```java
int getMaxSort();
```

xml
```xml
<select id="getMaxSort" resultType="java.lang.Integer">
    select sort from car  order by sort desc limit 1
</select>

...
</where>
order by u.sort
<if test='pageInfo != null'>
    limit #{pageInfo.offset}, #{pageInfo.limit}
</if>
```






挂载的时候 把时间挂在出来 会使时间更加准确







```
<insert  id="insert" parameterType="com.someray.vacs.business.car_info.entity.CarInfo" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO car_info (license_plate_number, vehicle_type, vin, create_date, brand_and_model, fuel_type, emission_standard, network_status, use_type, team_name)
    VALUES (#{licensePlateNumber}, #{vehicleType}, #{vin}, #{createDate}, #{brandAndModel}, #{fuelType}, #{emissionStandard}, #{networkStatus}, #{useType}, #{teamName})
</insert >
```




```
@Override
public int addCarInfo(CarInfo carInfo) {
    carInfoMapper.insert(carInfo);
    return carInfo.getId();
}
```










users



查询
get/{id}
路径


查询多个
get
body数组



添加
post
body


删除一个
del/{id}
路径



删除多个
del
body数组



更新
put
body









分页 手动实现
前端传入的分页数据
后端用 limit 拼接一下语句





ext/plain;charset=UTF-8' not supported
2024-07-05 10:22:21.031  WARN 7048 --- [-nio-80-exec-10] .m.m.a.ExceptionHandlerExceptionResolver : Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'text/plain;charset=UTF-8' not supported]

postman 的请求中 body 使用的不是 json 格式






2024-07-05 14:07:12.531  INFO 18088 --- [p-nio-80-exec-2] o.s.web.servlet.DispatcherServlet        : Completed initialization in 7 ms
2024-07-05 14:07:12.677  WARN 18088 --- [p-nio-80-exec-2] .m.m.a.ExceptionHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.util.ArrayList<com.someray.vacs.business.car_info.entity.Car>` out of START_OBJECT token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList<com.someray.vacs.business.car_info.entity.Car>` out of START_OBJECT token
 at [Source: (PushbackInputStream); line: 1, column: 1]]


传入的数据有问题
    接口地址写错了
    json格式有问题


 : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.util.ArrayList<com.someray.vacs.business.car_info.entity.Car>` out of START_OBJECT token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList<com.someray.vacs.business.car_info.entity.Car>` out of START_OBJECT token
 at [Source: (PushbackInputStream); line: 1, column: 1]]







抛出Runtime Exceptioin 而不是 Exception 从而可以忽略检查 不用一直抛出




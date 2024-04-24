set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m 生效周期


java -jar %JAVA_OPTS% ruoyi-admin.jar

mvn clean package -Dmaven.test.skip=true

顺序是有必要的, 因为有参数的原因
参数为跳过测试 -D表示用来设置系统属性




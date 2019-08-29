# Shizy-Springboot-Empty

空的springboot项目，没有数据库，没有CRUD，，没有各种配置，各种功能，何もないです。

### Quick Start

#### Web入口

```
http://127.0.0.1/
```

#### 打包部署

* Maven打包命令
```
mvn clean package -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
```

打包完idea启动项目报错，Rebuild project或者mvn clean

* jar包运行命令
```
(java -server -Xmx1024m -Xms256m -XX:+UseParallelGC -XX:ParallelGCThreads=20  -jar Shizy-SpringBoot-Empty-1.0-SNAPSHOT.jar &)
```


<br>
<br>
<br>
<br>
<br>
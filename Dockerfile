#基础镜像
FROM 192.168.2.78:5000/library/centos-jdk:1.8.25

#作者
MAINTAINER chenjiayu chenjiayu@ly-sky.com

#执行命令，主要用来安装相关的软件
#RUN 


#添加文件
ADD target/Shizy-Springboot-Empty-1.0-SNAPSHOT.jar /usr/local

RUN chmod u+x /usr/local/Shizy-Springboot-Empty-1.0-SNAPSHOT.jar

#挂载目录到容器
#VOLUME ["/data"]

#环境变量设置
#ENV 

#开放端口
EXPOSE 80

#启动时执行的命令
CMD ["/bin/bash"]

#启动时执行的命令
ENTRYPOINT ["java","-jar","-Xms2048m", "-Xmx2048m", "-XX:PermSize=256M", "-XX:MaxPermSize=256M","/usr/local/Shizy-Springboot-Empty-1.0-SNAPSHOT.jar", ">stdout.txt"]
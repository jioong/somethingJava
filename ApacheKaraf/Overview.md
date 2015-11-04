# Apache Karaf 概论 #

*Apache Karaf* 是一个时髦的多形态的**容器**。  
它是一个基于*OSGI*轻量级的，强劲的，企业级的容器。利用*Karaf*能够发布不同的应用。  

特点是比较灵活，因此适用于微服务、系统整合、大数据等。  
*Karaf* 能从一个轻量级的同期扩展到一个全特性的企业级服务。它非常灵活，具有很强的可扩展性，涵盖所有的专业需求。  

**特性：**  

1. 热发布。将文件放入 *deploy* 文件夹，*karaf*会自动检测文件的类型，并尝试发布。  
2. 完整控制台。提供类*Unix*控制台，用于管理容器。  
3. 动态配置。所有的配置文件都在*etc*文件夹下。任何的改变都会被注意到，并重加载。  
4. 预配置。*Apache Karaf* 提供一个大系列的URL去安装应用。同时，提供*Karaf Features* 的概念用于去描述自己的应用。  
5. 管理。*Apache Karaf* 是一个企业级的容器，通过*JMX* 提供很多管理指示和操作。  
6. 远程。嵌入一个*SSHd*服务提供远程控制台。管理层同样可以远程接入。  
7. 安全。*Apache Karaf* 提供一个基于*JAAS*的绝对安全架构，并对控制台和*JMX*的接入提供*RBAC(Role-Base Access Control)*机制。  
8. 实例。通过主实例(root)可以管理*Karaf*多实例。  
9. *OSGI*框架。*Apache Karaf*并没有域*OSGI*强耦合。默认的，*Karaf*运行于*Apache Felix*框架，但是可以简单的切换到*Equinox(Eclipse Equinox OSGI)*,通过修改配置文件中的一个属性。  

<center>
![Karaf 架构图]("./pictures/karaf.png" "karaf 架构图")
</center>

## 目录结构 ##

* /bin ---> 控制脚本，*start,stop,login...*  
* /demos ---> 一些简单的*Karaf*例子  
* /etc ---> 配置文件  
* /data ---> 工作目录  
	* /cache ---> *OSGI*架构包缓存  
	* /generated-bundle ---> 部署暂时文件夹  
	* /log ---> 日志文件  
* /deploy ---> 热部署文件夹  
* /instances ---> 包含实例文件夹  
* /lib ---> 包含库文件  
	* /lib/boot ---> *Karaf*引导程序使用的系统库文件  
	* /lib/endorsed ---> 授权库文件（该文件夹中的*jar*包用于去重写*JVM*定义的类）  
	* /lib/ext ---> *JRE*扩展文件夹  
* /system ---> *OSGI*包库，
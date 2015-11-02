# 日志(Log) #

*Karaf*提供一个动态的日志系统。它支持：  
* *OSGI*日志服务。  
* *Log4j*框架。  
* *Apache*一般日志框架。  
* *Logback*框架。  
* *SLF4J*框架。  
* 本地的*Java Util*日志框架。  

**配置文件**  
初始的日志配置文件放在*etc/org.ops4j.pax.logging.cfg*，该文件是一个标准的*Log4j*配置文件。可以发现不同的*Log4j*元素：  
* loggers  
* appenders  
* layouts  

可以直接在文件中添加自己的初始配置信息。
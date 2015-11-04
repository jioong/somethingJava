# 远程(Remote) #

*Karaf*提供一种完整的远程机制可以远程连接到一个*Apache Karaf*实例。此外，还可以远程**浏览，下载，上传文件**到一个远程的*karaf*实例。  
*Apache Karaf*内部嵌入一个完整的*SSHd*服务。  

### SSHd 服务 ###

当启动一个*Karaf*时，可以通过*SSH*远程连接到控制台。远程的控制台与本地控制台的特性一样。  
此外，*karaf*还提供一个远程的文件系统*filesystem*,能通过*SCP/SFTP*客户端接入。  

**配置**  
*SSHd*服务的配置存放在*etc/org.apache.karaf.shell.cfg*文件中，该文件中包含包含配置*SSHd*服务的不同属性：  

* *sshPort*: *SSHd*服务绑定的端口，默认为8101。  
* *sshHorst*: 绑定的网络接口地址，默认为0.0.0.0，表示绑定到所有的网络接口。也可以通过指定一个*IP*地址去绑定网络接口。  
* *hostKey*: *host.key*的存放位置。默认为*etc/host.key*。该文件存放*SSHd*服务的公有和私有密钥对。  
* *sshRole*: 指定连接到*SSH*的默认角色。默认值为定义在文件*etc/system.properties*文件中的*karaf.admin.role*属性值。 **有问题啊！** 
* *keySize*: 密钥大小。  
* *algorithm*: 主密钥加密算法。可选值为*DSA、RSA*。默认为*DSA*。  

*SSHd*服务的配置可以在运行时改变：  
1. 修改*etc/org.apache.karaf.shell.cfg*配置文件  
2. 运行 *config:\** 命令。  

当在运行时修改服务的配置文件，需要重启*SSHd*服务来重新加载改变。使用如下命令：  
		bundle:restart -f org.apache.karaf.shell.ssh  
  
*ssh:ssh* 就是一个*SSH*客户端。  

**文件系统客户端**  
出于安全原因的考虑，可获得的文件系统被限定在*KARAF_BASE*文件夹中。
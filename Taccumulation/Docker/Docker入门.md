2015/1/22 星期四 12:36:41 

# 入门 #
1. Docker 特征
	- 速度快以及隔离框架
	- 物美价廉
	- CPU/内存消耗低
	- 开机速度快
	- 跨云计算基础框架

2. Docker 组件与元素

**三个基本组件：**
	- Docker Client: 用户界面，支持用于与 Docker Daemon 之间通信。
	- Docker Daemon: 运行在主机之上，处理服务请求。
	- Docker Index : 是中央的 Registry，支持拥有公用与私有访问权限的 Docker 容器镜像的备份。

**三个要素：**
	- Docker Containers: 负责应用程序的运行，包括操作系统、用户添加的文件以及元数据。
	- Docker Images: 是一个**只读**模版，用来运行 Docker 容器。
	- DockerFile: 是文件指令集，用来说明如何自动创建 Docker 镜像。

<center>
![relationship]("./images/relationship.png" "关系说明")
</center>

Docker 使用以下操作系统的功能来提高容器技术效率：  
	- *Namespace* 充当隔离的第一级。确保一个容器中运行一个进程而且不能看到货影响容器外的其他进程。
	- *Control Groups* 是LXC的重要组成部分，具有资源核算与限制的关键功能。
	- *UnionFS(文件系统)* 作为容器的构建块，为了支持 Docker 的轻量级以及速度快的特性，它创建层与用户(?)。

**如何把它们放在一起**  
运行任何应用程序，都需要两个基本步骤：  
1. 构建一个镜像。  
2. 运行容器。  

这些步骤都是从 Docker Client 开始。Docker Client 使用的是 Docker 二进制文件。在基础层面上， Docker Client 告诉 Docker Daemon 需要创建的镜像以及需要在容器内运行的命令。当 Daemon 收到创建镜像的信号后，会进行如下操作：  

**第一步：构建镜像**
Docker Image是一个构建容器的只读模版，它包含了容器启动所需的所有信息，包括运行哪些进程和**配置数据**。  
所有的镜像都会基于一个基本镜像构建，紧接着会根据 DockerFile 中的指令创建模版，对于每个指令，**在镜像上创建一个新的层。**  

**第二部：运行容器**  
运行容器原语在第一步中创建的镜像。当一个容器被启动后，一个读写层会被添加到镜像的顶层。当被分配合适的网络和 IP 地址后，应用程序就可以在容器汇总运行了。  

# 命令 #

1. 通过下面命令检查 Docker 是否正确安装：

		docker info

2. daemon: 是一个用于管理容器的后台进程。一般情况下，守护进程是一个长期运行的用来处理请求的进程服务。 *-d*参数用于运行后台进程。  
3. build: 使用 DockerFile 来构建镜像。  

		docker build [options] PATH | URL
		其他选项：
		--rm=true 表示构建成功后，删除所有的中间容器。
		--no-cache=false 表示在构建过程中不适用缓存。

4. attach: Docker允许使用 attach 命令与运行中的容器交互，并且可以随时观察容器内进程的运行状况。退出容器可以通过两种方式完成：  
	- Ctrl + C 直接退出
	- Ctrl + \ 退出并显示堆栈信息(stack trace)
attach命令语法：
		docker attach container

5. diff: 可以列出容器内发生变化的文件和目录。变化包括添加(A-add)、删除(D-delete)、修改(C-change)。该命令便于Debug, 并支持快速的共享环境。语法是：

		docker diff container

6. events: 打印指定时间内的容器的实时系统事件。
7. import: Docker 可以导入远程文件、本地文件和目录。使用 PATH 和 URL 从远程位置导入，而本地文件或目录的导入需要使用 *-参数*。从远程位置导入语法：

		docker import http://example.com/example.tar

8. export: 类似于 import, export命令用于将容器系统文件打包成 tar 文件。
9. cp: 从容器内复制文件到指定的路径上。语法如下：

		docker cp container:path hostpath

10. login: 用来登录到 Docker Registry 服务器。语法如下：

		docker login [options] [server]
如果登录自己主机的Registry使用：

		docker login localhost:8080

11. inspect: 用来收集有关容器和镜像的底层信息。信息包括：

	- 容器实例的 IP 地址。
	- 端口绑定列表。
	- 特定的端口映射的搜索。
	- 收集配置的详细信息。

语法：
		docker inspect container/image

12. kill: 发送 SIGKILL 信号来停止容器的主进程。语法如下：

		docker kill [options] container

13. rim: 用于移除一个或者多个镜像。语法如下：

		docker rmi image

镜像可以有多喝标签链接到它。在删除镜像时，应确保删除所有的标签以避免错误。

14. wait: 阻塞对指定容器的其他调用方法，直到容器停止后退出阻塞。

15. load: 该命令从 tar 文件中载入镜像或仓库到 STDIN。

16. save: 该命令保存镜像为 tar 文件并发送到 STDOUT。语法如下：

		docker save image

# DockerFile #

**命令为易于自动化**

DockerFile 是包含创建镜像所需要的全部指令。基于在 DockerFile 中的指令，可以使用*docker build* 命令来创建镜像。通过减少镜像和容器的创建过程来简化部署。  

DockerFiles支持的语法命令如下：

		INSTRUCTION argument

指令不区分小大写。但是，命名约定全部为大写。

所有的 DockerFile 都必须以 *FROM* 命令开始。 *FROM* 命令会指定镜像基于那个基础镜像创建，以及接下来的命令也会基于这个基础镜像。 *FROM* 命令可以使用多次，表示会创建多个镜像。具体语法如下：

		FROM <image name>

继 *FROM* 命令， DockerFile 还提供一些其他的命令来实现自动化。在DockerFile文件中这些命令的顺序就是它们被执行的顺序。

1. *MAINTAINER*： 设置该镜像的作者。语法如下：

		MAINTAINER <author name>

2. *RUN*: 在 *shell* 或者 *exec* 的环境下执行的命令。 *RUN* 指令会在新创建的镜像上添加新的层，接下来提交的结果用于在 DockerFile 的下一条指令。语法如下：

		RUN <command>

3. *ADD*: 复制文件指令，它有两个参数 *<source>* 和 *<destination>*。 *destination* 是容器内的路径。 *source* 可以是 URL或者是启动配置上下文中的一个文件。语法如下：

	ADD <src> <destination>

4. *CMD*: 提供容器默认的执行命令。 DockerFile 只允许**CMD命令使用一次。**使用多个*CMD*会抵消之前所有的，只有最后一个生效。*CMD*有三种形式：

		1. CMD ["executable", "param1", "param2"]
		2. CMD ["param1", "param2"]
		3. CMD command param1 param2

5. *EXPOSE*: 指定容器在运行时监听的端口。如法如下：

		EXPOSE <port>

6. *ENTRYPOINT*:配置容器一个可执行的命令，这意味着每次使用镜像创建容器时一个特定的应用程序可以被设置为默认程序。同时也意味着该镜像每次被调用时仅能运行制定的应用。类似于*CMD*， Docker**只允许一个ENTRYPOINT**，当有多个时会抵消之前的，只执行最后的。语法如下：

		ENTRYPOINT ["executable", "param1", "param2"]
		ENTRYPOINT cpmmand param1 param2

7. *WORKDIR*: 制定 *RUN、CMD、ENTRYPOINT*命令的工作目录。语法如下：

		WORKDIR /path/to/workdir

8. *ENV*:设置环境变量。它们使用**键值对**，并增加运行的程序的灵活性。语法如下：

		ENV <key> <value>

9. *USER*:镜像正在运行时设置一个 UID。语法如下：

		USER <uid>

10. *VOLUME*：授权访问从容器到主机上的目录。语法如下：

		VOLUME ['/data']
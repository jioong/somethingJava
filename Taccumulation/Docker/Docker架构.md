2015/1/22 星期四 15:02:04 

Docker对使用者来讲是一个 C/S 模式的架构，Docker后端是一个非常松耦合的架构，各模块各司其职，并有机组合，支撑 Docker 的运行。

**1. Docker 总架构**
<center>
![total]("./images/total.png" "总架构")
</center>

用户是使用 Docker Client 与 Docker Daemon 建立通信，并发送请求给后者。  
而Docker Daemon作为 Docker 架构中的主体部分，首先提供 Server 的功能使其可以接受 Socker Client 的请求；而后 Engine 执行 Docker 内部一系列工作，每一项工作都是以一个 Job 的形式存在。
Job 的运行过程中，当需要容器镜像时，则从 Docker Registry 中下载镜像，并通过镜像管理驱动 graphdriver 将下载镜像以 Graph 的形式存储；当需要为 Docker 创建网络环境时，通过网络管理驱动 networkdriver 创建并配置 Docker 容器网络环境； 当需要限制 Docker 容器运行资源或执行用户指令等操作时，则通过 execdriver 来完成。  

而 libcontainer 是一项独立的容器管理包， networkdriver 以及 execdriver 都是通过 libcontainer 来实现具体对容器经行的操作。  

当执行完运行容器的命令后，一个实际的 Docker 容器就**处于运行状态**，该容器拥有独立的文件系统，独立并且安全的运行环境等。  


**2. Docker Client**

Docker Client 是 Docker 架构中用户用来和 Docker Daemon 建立通信的客户端。用户使用的可执行文件为 docker, 通过 docker 命令行工具可以发起众多管理 container 的请求。  

Docker Client 可以通过以下三种方式和 Docker Daemon 建立通信： tcp://host:port, unix://path_to_socket 和 fd://socketfd。  
与此同时，与 Docker Daemon 建立链接并传输请求时， Docker Client 可以通过设置命令行 flag 参数的形式设置安全传输层协议(TLS)的有关参数，保证传输的安全性。  

Docker Client 发送容器管理请求后，由 Docker Daemon 接受并处理请求， 当 Docker Client 接收到返回，并简单处理后， Docker Client 一次完整的生命周期就结束了。当需要继续发送容器管理请求时，用户必须再次通过 docker 可执行文件创建 Docker Client。

**3. Docker Daemon**

<center>
![docker daemon]("./images/daemon.png" "Docker Daemon")
</center>

Docker Daemon 是 Docker 架构中一个常驻后台的**系统进程**。功能是：接受并处理 Docker Client 发送的请求。该守护进程在后台启动一个 *server*，该*server* 负责接受 Docker Client 发送的请求。接受请求后， server 通过**路由与分发调度**，找到相应的*Handler* 来执行请求。  

Docker Daemon 启动所使用的可执行文件也为 docker，与 Docker CLient 启动所使用的可执行文件 docker 相同。 在 docker 命令执行时，通过传入的参数来判别 Docker Daemon 和 Docker Clinet。

**Docker Daemon 大致可以分为三部分： Docker Server、 Engine 和 Job.**

**3.1 Docker Server**

Docker server 在 Docker 架构中是专门服务于 Docker Client 的 server。该server的功能是：接受并调度分发 Docker Client 发送的请求。

<center>
![docker server]("./images/dockerserver.png" "Docker Server")
</center>

在 Docker 的启动过程中，通过包 gorilla/mux，创建一个 mux.Router,提供请求的路由功能。在 Golang 中，gorilla/mux 是一个强大的 URL 路由器以及调度分发器。该 mux.Router中添加了众多的**路由项**，每一个路由项由 HTTP 请求方法(PUT、POST、GET、DELETE)、URL、Handler 三部分组成。  

当 Docker Client 通过HTTP 的形式访问 Docker Daemon，创建完 mux.Router 之后， Docker 将 server 的监听地址以及 mux.Router 作为参数，创建一个 httpSrv=http.Server{}，最终执行 httpSrv.Server()为请求服务。  

在 Server 的服务过程中， Server 在 listener 上接受 Docker Client 的访问请求，并创建一个全新的 goroutine 来服务该请求。在 goroutine 中，首先读取请求内容，然后做解析工作，接着找到相应的路由项，随后调用相应的 Handler 来处理请求，最后 Handler 处理完请求后回复该请求。  

需要注意的是： Docker Server 的运行在 Docker 的启动过程中，是靠一个名为 "serverapi"的 Job 的运行来完成的。原则上， Docker Server 的运行是众多 Job 中的一个，但是为了强调 Docker Server 的重要性以及为后续 Job 服务的重要特性，将该 "serverapi"的 Job 单独抽离出来分析，理解为 Docker Server。

**3.2 Engine**  

Engine 是 Docker 架构中的运行引擎，同时也是 Docker 运行的核心模块。它扮演着 Docker Container 存储仓库的角色，并且通过执行 job 的方式来操作管理这些容器。  

在 Engine 数据结构的设计和实现过程中，有一个 handler 对象。该 handler 对象存储的都是关于众多特定 job 的 handler 处理访问。举例说明， Engine 的 handler 的对象中有一项为： {"create":daemon.ContainerCreate}，则说明当名为 "create"的 job 运行时，执行的是 daemon.ContainerCreater 的 handler。

**3.3 Job**

一个 Job 可以认为是 Docker 架构中 Engine内部最基本的工作执行单元。 Docker 可以做的每一项工作，都可以抽象为一个 Job。例如：在容器内部运行一个进程，这是一个 Job；创建一个新的容器，也是一个 Job；从Internet 下载一个文档，还是一个 Job;创建 Server 服务于 HTTP 的 API 也是一个 Job。  

Job 的设计者，把 Job 设计的与 Unix 进行相仿。比如说：Job 有参数、环境变量、标准输入输出、错误处理、返回状态等。

**4. Docker Registry**

Docker Registry 是一个存储容器镜像的仓库。而容器镜像是在容器被创建时，被加载用来初始化容器的文件架构与目录。  

在 Docker 的运行过程中， Docker Daemon 会与 Docker Registry 通信，并实现搜索镜像、下载镜像、上传镜像三个功能，分别对应的 Job 名称为 "search"、"pull"、"push"。

其中，在Docker 框架中， Docker 可以使用公有的 Docker Registry，即大家熟知的 Docker Hub,必须通过互联网访问；同时Docker 也允许用户构建本地私有的 Docker Registry，可以保证容器镜像的获取在内网完成。

**5. Graph**

Graph 在 Docker 架构中扮演着**已下载容器镜像的保管者**，以及**已下载容器镜像之间关系的记录者**。一方面， Graph 存储着本地具有版本信息的文件系统镜像，另一方面也通过 GraphDB 记录着所有文件系统镜像彼此之间的关系。  

<center>
![docker graph]("./images/dockergraph.png" "Graph")
</center>

其中， GraphDB 是一个构建在 SQLite 之上的小型图形数据库，实现了节点的命名以及节点之间关联关系的记录。它仅仅实现了大多数图形数据库所拥有的一个小子集，但是提供了简单的接口表示节点之间的关系。  

同时在 Graph 的本地目录中，关于每一个容器镜像，具体的存储信息有：该容器镜像的元数据、容器镜像的大小信息、该容器所代表的具体 rootfs。

**6. Driver**

Driver 是Docker 架构中的驱动模块。通过Driver驱动， Docker 可以实现 Docker 容器执行环境的定制。由于 Docker 运行的生命周期中，并非用户所有的操作都是针对 Docker 容器的管理，另外还有关于 Docker 运行信息的获取， Graph 的存储于记录等。因此，为了将 Docker 容器的管理从 Docker Daemon内部业务逻辑中区分开来，设计了 Driver 层驱动来接管所有这部分请求。  

在 Docker Driver 的实现中，可以分为三类驱动： graphdriver、networkdriver、execdriver。  

**graphdriver:**主要用于完成容器镜像的管理，包括存储与获取。即当用户需要下载指定的容器镜像时， graphdriver 将容器镜像存储在本地的指定目录；同时当用户需要使用指定的容器镜像来创建容器 rootfs 时， graphdriver 从本地镜像存储目录中获取指定的容器镜像。  

在 graphdriver 的初始化过程之前，有 4 种文件系统或类文件系统在其内部注册，他们分别是 aufs、btrfs、vfs、devmapper。而Docker 在初始化之时，通过获取系统环境变量 "DOCKER_DRIVER" 来提取所使用 driver 的指定类型。而之后所有的 graph 操作，都是用该 driver 来执行。  
<center>
![graphdriver]("./images/graphdriver.png" "GraphDriver")
</center>

**networkdriver:**的用途是完成 Docker 容器网络环境的配置，其包括 Docker 启动时为 Docker 环境创建网桥； Docker 容器创建时为其创建专属虚拟网卡设备；以及为 Docker 容器分配 IP、端口并与宿主机做端口映射，设置容器防火墙策略等。  

<center>
![networkdriver]("./images/networkdriver.png" "NetworkDriver")
</center>

**execdriver:**作为 Docker 容器的执行驱动，负责创建容器运行命名空间，负责容器资源使用的统计与限制，负责容器内部进程的真正运行等。在 execdriver 的实现过程中，原先可以使用 LXC 驱动调用 LXC 的接口来操纵容器的配置以及生命周期，而现在 execdriver 默认使用 native 驱动，不依赖于 LXC。具体体现在 Daemon 启动过程中加载的 ExecDriverFlag 参数，该参数在配置文件已经被设为 "native"。

<center>
![execdriver]("./images/execdriver.png" "ExecDriver")
</center>

**7. libcontainer**

**libcontainer**是 Docker 架构中一个使用 Go 语言设计实现的库，设计初衷是希望该库可以不依靠任何依赖，直接访问内核中与容器相关的 API。

正是由于 libcontainer 的存在， Docker 可以直接调用 libcontainer,而最终操作容器的 namespace、cgroup、apparmor、网络设备以及防火墙规则等。这一系列操作的完成都不需要依赖 LXC 或者其他包。  

<center>
![libcontainer]("./images/libcontainer.png" "libcontainer")
</center>

另外， libcontainer 提供一整套标准的借口来满足上层对容器管理的需求。或者说， libcontainer 屏蔽了 Docker 上层对容器的直接管理。

**8. Docker Container**  

Docker Container 是 Docker 架构中服务交付的最终体现形式。  

Docker 按照用户的需求与指令，定制相应的 Docker 容器：  

	- 用户可以通过指定容器镜像，使得 Docker 容器可以自定义 rootfs 等文件系统。
	- 用户可以通过指定计算资源的配额，使得 Docker 容器使用指定的计算资源。
	- 用户可以通配置网络及其安全策略，使得 Docker 容器拥有独立且安全的网络环境。
	- 用户可以通过指定运行的命令，使得 Docker 容器执行指定的工作。

<center>
![Docker Container]("./images/dockercontainer.png" "Docker 容器")
</center>  

**9. Docker 运行实例分析**  

**9.1 docker pull**  

**docker pull**命令的作用是：从 Docker Registry 中下载指定的容器镜像，并存储在本地的 Graph 中，以备后续创建 Docker 容器时使用。 Docker pull 命令执行流程如下：  

<center>
![docker pull]("./images/dockerpull.png" "docker pull")
</center>  

(1) Docker Client 接受 docker pull 命令，解析完请求以及收集完请求参数后，发送一个 HTTP 请求给 Docker Server， HTTP 请求方式为 POST，请求 URL 为 "/images/create?" + "xxx";
(2) Docker Server 接受以上 HTTP 请求，并交给 mux.Router, mux.Router通过 URL以及请求方式来确定执行该请求的具体 handler；
(3) mux.Router 将请求路由分发至相应的 handler，具体为 PostimagesCreate;
(4) 在 PostimageCreate 这个 handler 之后，一个名为 "pull" 的 Job 被创建，并开始执行；
(5) 名为 "pull" 的 Job 在执行过程中，执行 pullRepository 操作，即从 Docker Registry 中下载相应的一个或者多个 image;
(6) 名为 "pull" 的 Job 将下载的 image 交给 graphdriver;
(7) graphdriver 负责将 image 进行存储，一方面创建 graph 对象，另一方面在 GraphDB 中记录 image 之间的关系。

**9.2 docker run**

Docker run 命令的作用是在一个全新的 Docker 容器内部运行一条指令。 Docker 在执行这条命令的时候，做作的工作可以分为两部分：第一，创建 Docker 容器所需的 rootfs；第二，创建容器的网络等运行环境，并真正运行用户指令。因此，在整个执行流程中， Docker Client 给 Docker Server 发送了两次 HTTP 请求，第二次请求的发起取决于第一次请求的返回状态。 Docker run 执行流程如下：  

<center>
![docker run]("./images/dockerrun.png" "docker run")
</center>  

(1) Docker Client 接受 docker run 命令，解析完请求以及收集完请求参数之后，发送一个 HTTP 请求给 Docker Server, HTTP 请求方法为 POST，请求 URL为 "/containers/create?" + "xxx";
(2) Docker Server 接受以上 HTTP 请求，并交给 mux.Router, mux.Router 通过 URL 以及请求方法来确定执行该请求的具体 handler;
(3) mux.Router 将请求路由分发至相应的 handler，具体为 PostimageCreate;
(4) 在 PostimageCreate 这个 handler 之中，一个名为 "create"的 Job 被创建，并开始执行该 Job;
(5) 名为 "create" 的 Job 在运行过程中， 执行 Container Create 操作，该操作需要获取容器镜像来为 Docker 容器创建 rootfs，即调用 graphdriver。
(6) graphdriver 从 Graph 中获取创建 Docker 容器 rootfs 所需要的所有镜像;
(7) graphdriver 将 rootfs 所有镜像，加载安装至 Docker 容器指定的文件目录下;
(8) 若以上操作全部正常执行，没有返回错误或异常，则 Docker Client 收到 Docker Server 返回状态之后，发起第二次 HTTP 请求。请求方式为 POST， 请求 URL 为 "/containers" + container_ID + "/start"；
(9) Docker Server 接受以上 HTTP 请求，并交给 mux.Router, mux.Router 通过 URL 以及请求方式来确定执行该请求的具体 handler；
(10) mux.Router 将请求路由分发至相应的 handler，具体为 PostContainerStart;
(11) 在 PostContainerStart 这个 handler 之中，名为 "start" 的 Job 被创建，并开始执行；
(12) 名为 "start" 的 Job 执行完初步的配置工作后，开始配置与网络环境，调用 networkdriver;
(13) networkdriver 需要为指定的 Docker 容器创建网络接口设备，并为其分配 IP、Port,以及设置防火墙规则，相应的操作转交至 libcontainer 中的 netlink 包来完成;
(14) netlink 完成 Docker 容器的网络环境配置与创建;
(15) 返回至名为 "start" 的 Job，执行完一些辅助性操作之后， Job 开始执行用户指令，调用 exexdriver;
(16) execdriver 被调用，初始化 Docker 容器内部的运行环境，如命名空间，资源控制与隔离，以及用户命令的执行，相应的操作转交至 libcontainer 来完成;
(17) libcontainer 被调用，完成 Docker 容器内部的运行环境初始化，并最终执行用户要求启动的命令。
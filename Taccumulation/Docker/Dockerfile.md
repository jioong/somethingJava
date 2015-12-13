

## FROM ##

用法：
		FROM <image>
或者
		From <image>:<tag>

*FROM* 指令用于为接下来的指令设置[基础镜像]("http://docs.docker.com/terms/image/#base-image-def")。 在有效的 *Dockerfile* 中，第一条指令必须是 *FROM*。这里，镜像可以是任意有效的镜像。  

1. *FROM* 必须是 *Dockerfile* 中第一个非注释的指令。
2. *FROM* 在一个 *Dockerfile* 中可以出现多次，用于构建**多层镜像(multiple images)**。  
3. 如果 *FROM* 的镜像没有指定 *tag*，则默认使用 *latest* 标签的镜像。如果指定的标签不存在，则命令报错。

## MAINTAINER ##

用法：  

		MAINTAINER <name>  

该命令用于设置产生镜像的作者。

## RUN ##

*RUN* 指令有两种用法：

		RUN <command>                            (shell form)
		RUN ["executable", "param1", "param2"]   (exec form)

*RUN* 命令会在当前镜像之上的**新层(new layer)**中执行，并提交结果。提交的镜像会是 *Dockerfile* 中下一条命令的基础。  
分层 *RUN* 命令和每层提交符合**Docker**的核心概念，这使得提交成本更低、容器能从任何镜像历史节点被创造。类似版本控制。
*exec 风格*可以避免 *shell* 要求，同时 *RUN* 命令可以在基础镜像不包含 */bin/sh* 时照样执行。

**其他：**
1. 使用不同的 *shell*，用 *exec 风格* 传递所需的 *shell*。如，**RUN ["/bin/bash", "-c", "echo hello"]**。  
2. *exec 风格*被解析成 *JSON* 数组形式，因此需要**用双引号**而不是单引号来包住单词。  
3. 与 *shell 风格*不同，*exec 风格*不会调用命令行 *shell*。

*RUN* 命令的缓存在下次 *build* 时不会自动失效。缓存在下一次构建时会被重用。当使用 *--no-cache* 标识时，缓存将失效。例如，**docker duild --no-cache**。

## CMD ##

*CMD* 指令有三种用法：

		CMD ["executable","param1","param2"]     (exec 风格，最优先风格)
		CMD ["param1","param2"]                  (缺省默认参数 ENTRYPOINT)
		CMD command param1 parma2                (shell 风格)

在一个 *Dockerfile* 文件中只能有一个 *CMD* 指令。如果有多余一个的 *CMD* 指令，则只有最后一个会起效。  
*CMD* 指令的主要目的是：**为执行容器提供缺省值。**

**其他：**  
1. 如果 *CMD* 用于为 *ENTRYPOINT* 指令提供缺省值，那么 *CDM* 和 *ENTRYPOINT* 都必须使用 *JSON* 数组的形式。  
2. *exec 风格*被解析成 *JSON* 数组形式，因此需要**用双引号**而不是单引号来包住单词。  
3. 与 *shell 风格*不同，*exec 风格*不会调用命令行 *shell*。

**RUN 是在构建时执行，运行命令并提交结果。CMD 不是在构建时执行，而是指定镜像将要执行的命令。**

## EXPOSE ##

用法：

		EXPOSE <port> [<port> ...]

*EXPOSE* 指令用于通知 *Docker*, 容器将会在运行时监听指定的网络端口。*Docker* 通过这些信息来利用 *links* 实现容器间互连，并确定使用 *-P* 标识时哪个端口将会被暴露给主机。

## ENV ##

用法：

		ENV <key> <value>
		ENV <key>=<value> ...

*ENV* 指定用于设置环境变量。其中，*value* 可以传递给之后的 *RUN* 指令。  
第一种格式，只能设置单个变量，第一个空格后的整个字符串被当做 *value*，包括空格和引号等。  
第二种格式，可以一次设置多个变量。  
用 *ENV* 设置的环境变量当结果镜像运行一个容器时会存在。(?)  
可以用 *docker inspect* 查看设置的环境变量。  
通过 *docker run --env <key>=<value>* 可以重设环境变量。

## ADD ##

用法：

		ADD <src>... <dest>

*ADD* 指令用于复制 *<src>* 指定的新文件、文件夹或远程文件URL，并添加到 *<dest>* 指定的容器中的文件系统。  
指定多个 *<src>* 且是文件或文件夹时，必须是相对于构建的源文件夹(构建上下文)。 
每个 *<src>* 可以包含通配符。  

*<dest>* 是目的容器中的目的地址的**绝对路径**。所有的新文件和文件夹用 *UID* 和 *GID* 0 创建。  

当 *<src>* 为远程 URL 时，目的地权限应为 **600**。
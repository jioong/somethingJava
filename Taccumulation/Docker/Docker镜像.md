2015/1/22 星期四 20:43:30 

# Docker 镜像 #

## Docker 镜像命名解析 ##

镜像是 Docker 最核心的技术之一，也是应用发布的标准格式。  

Repository:tag 来引用镜像。  

Registry 存储镜像数据，并且提供拉取和上传镜像的功能。Registry 中镜像是通过 Repository 来组织的，而每个 Repository 又包含若干个 Image。

	- Registry 包含一个或多个 Repository。
	- Repository 包含一个或多个 Image。
	- Image 用 GUID 表示，有一个或多个 Tag 与之关联。

## 配置 Registry Mirror ##

为了克服跨洋网络延迟，一般有两个解决方案：一个是使用私有Registry,另一个是使用 Registry Mirror。  

**方案一：**搭建或使用现有的私有Registry,通过定期和 Docker Hub同步热门的镜像，私有 Registry 上保存了一些镜像的副本，然后大家可以通过  

		docker pull private-registry.com/user-name/image

从私有的 Registry 拉取镜像。因为这个方案需要定期同步 Docker Hub 镜像，因此比较适合与使用的镜像相对稳定，或者都是私有镜像的场景。而且用户需要显示的映射官方镜像名称到私有镜像名称，私有Registry 更多被应用在企业内部场景。私有 Registry 部署也很方便，可以直接在Docker Hub 上下载 Registry 镜像。

**方案二：**使用Registry Mirror。 它的原理类似于缓存，如果镜像在 Mirror 中命中则会直接返回给客户端，否则从存放镜像的 Registry 上拉取并自动缓存到 Mirror 中。最酷的是，是否使用 Mirror 对 Docker 使用者来讲是透明的，也就是说在配置 Mirror 后，仍可以输入 *docker pull ubuntu* 来拉取 Docker Hub 镜像，除了速度变快，其他和以前没有任何区别。  

申请开通 Mirror 服务后会得到一个 Mirror 地址，然后要做的就是把这个地址配置在 Docker Server 启动脚本中，重启 Docker 服务后 Mirror 配置就生效了。  

Ubuntu 下配置 Docker Registry Mirror 的命令如下：  

		sudo echo "DOCKER_OPTS=\"\$DOCKER_OPTS -registry-mirror=http://your-id.m.docloud.io -d\"" >> /etc/default/docker  

		docker restart 

配置好 Registry Mirror 后，皆可以拉取 Docker 镜像了。
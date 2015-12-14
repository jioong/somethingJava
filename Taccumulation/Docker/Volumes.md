2015/1/28 星期三 16:02:50 

# Volumes #

## Data Volumes ##

*Data volumes* 是一个在一个或多个容器内特别设计的文件夹，用于绕过 UFS 提供提供保存或共享数据：  
	- Data volumes 可以在不同容器之间共享和重用。
	- 对卷的修改是即时生效的。
	- 对卷的修改不会对更新镜像产生影响。
	- 卷会一直存在知道没有容器再使用它。

### 增加数据卷 ###

执行 *docker run* 命令时可以通过 *-v* 标识来增加数据卷。在一次 *docker run* 命令中可以多次使用 *-v* 标识来挂载多个数据卷。  
例子，给应用容器挂载一个数据卷：

		$sudo docker run -d -P --name web -v /webapp training/webapp python app.py
上述命令会在容器内的 */webapp* 目录下创建一个卷。  
也可以在 *Dockerfile* 中通过 *VOLUME* 来增加卷。

### 挂载主机目录作为数据卷 ###

例子：

		$sudo docker run -d -P --name web -v /src/wesbapp:/opt/webapp training/webapp python app.py
将主机目录 */src/web* 挂载到容器目录 *opt/web*。  
主机上的目录必须为**绝对路径**。   
*Docker* 默认挂载一个读写层，但是也可以挂载一个只读文件夹：  

		$sudo docker run -d -P --name web -v /src/webapp:/opt/webapp ：ro training/webapp python app.py

### 挂载主机文件作为数据卷 ###


# 配置(Configuration) #

*Karaf* 保存和加载的所有配置信息都在*etc*文件夹里。默认的，该*etc*文件夹是相对于*KARAF_BASE*文件夹的，所以可以通过修改*KARAF_BASE*变量来修改存放位置。  
一个配置问价就是一些属性的集合，由键值对组成：  
		property=value  

  
默认的，*karaf*会加载*etc*目录下的所有**.cfg*文件。  

*config.*命令：  
*Karaf*提供了一系列命令去管理配置文件。  
  
*config:list* : 显示所有可用的配置文件，或是给定配置文件中的属性。也可以指定显示属性的配置文件  
		config:list "(service.pid=想要查询的Pid值)"  
  
*config:edit* : 该命令可以进入编辑模式，针对给定的配置文件。该命令不会显示任何东西，仅仅是进入配置文件的编辑模式。现在可以开始使用其他的配置命令(config:property-append,config:property-delete，...)。所有的修改都会保存在控制台回话中，而不会直接作用到配置文件中。通过**提交**可以让修改生效。  
> *config:property-list*:列出当前配置文件的所有属性。  
> *config:property-set*:修改当前配置文件中已有属性的值。  

		config:property-set 要修改的属性 新的属性值  

如果**要修改的属性**不存在，则会直接创建新的属性。  
> *config:property-append*:在指定属性的属性值后面加上一个字符串。如果给定的属性不存在，则会创建属性。  
> *config:property-delete*:删除一个属性。  
> *config:update*:提交保存在控制台回话中的修改，更新配置和配置文件。  
> *config:dcancel*:放弃所有的修改，状态会滚到*config:edit*命令之前，并从编辑模式退出。  
> *config:delete*:完全删除一个已经存在的配置文件。不需要进入编辑模式。  

		config:delete 带删除配置文件名  

> *config:meta*:列出多有指定配置文件的元数据类型信息*(meta type information)*。
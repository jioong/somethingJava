2014/12/23 星期二 16:45:03 

## IDLE 清屏问题 ##

1. 下载 [cleanwindow.py]("http://bugs.python.org/issue6143")(右击 --- 目标另存为，格式为 .py 结尾，直接点击会打开脚本内容)。
2. 拷贝 cleanwindow.py 文件，放在 Python 安装目录 PythonXXX\Lib\idlelib 文件夹下(XXX 为 python 版本号)。
3. 用记事本打开 PythonXXX\Lib\idlelib 文件下下的 **config-extensions.def(IDLE 扩展的配置文件)。**为防止出错，可以在修改之前 copy 一个备份。
4. 修改 **config-extension.def**,在文件末尾加上如下内容，然后保存退出。

    [ClearWindow]
	enable=1
	enable_editor=0
	enable_shell=1
	[ClearWindow_cfgBindings]
	clear-window=<Control-Key-;>

最后一行为自己设置的快捷键操作。

5. 打开 Python 的 IDLE， options 选项中就可以看到增加了 **Clear shell windows ctrl+;** 。
6. 在 IDLE 输入代码，然后按 **Ctrl + ；**就可以清屏了。
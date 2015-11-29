# Eclipse 调试 #
  
## Eclipse 快捷键 ##
  
* `Ctrl + D` --- 删除当前行。  
* `Alt + Enter` --- 显示当前选择资源的属性。  
* `Shift + Enter` --- 在当前行的下一行插入空行。  
* `Shift + Ctrl + Enter` --- 在当前行的上一行插入空行。  
* `Ctrl + Q` --- 定位到最后编辑的地方。  
* `Ctrl + L` --- 定位到某一行。  
* `Ctrl + M` --- 最大化当前编辑器或视图。  
* `Ctrl + /` --- 注释当前行，或取消注释。  
* `Ctrl + O` --- 快速显示 *OutLine* 。  
* `Ctrl + T` --- 快速显示当前类的继承结构。  
* `Ctrl + K` --- 参照选中的 *Word* 快速定位到下一个。  
* `Ctrl + E` --- 快速显示当前 *Edirer* 的下拉表，当前页面没有显示的用黑体表示。  
* `Alt + Shift + R` --- 重命名。  
* 
  
## 本地调试 ##
  
**快捷键：**
* `F5` --- 单步执行程序，遇到方法时进入。  
* `F6` --- 单步执行程序，遇到方法时跳过。  
* `F7` --- 单步执行程序，从当前方法跳出。  
* `F8` --- 直接执行程序，遇到断点时暂停。  

## Eclipse 中的远程调试 ##
  
从菜单中 **Run > Debug > Configuration...** ,选择 **Remote Java Application** 添加一个启动配置，然后选择 **连接器**。提供两个连接器：  
* *Socket Attach*  
* *Socket Listen*  

对于监听套接字的连接器，*Eclipse VM* 将是与远程 *Java* 应用程序连接的主机。对于连接套接字的连接器，目标 *VM* 将作为主机。两种连接器对应用程序调试没有影响，可以任意选择。但是，一般使用速度更快、更强大的计算机作为 *VM* 调试主机，因为需要计算的资源很多。  
  
在调试 *Java* 应用程序之前，需要确保已经为远程应用程序启用所有的调试选项。如果选项信息不可用，将收到一个错误信息，比如：*Debug information is not available* 或 *Unable to install breakpoint due to missing line number* 。可以通过更改 *Eclipse* 才当上的 **Window > Preferences > Java > Compiler** 来修改设置。
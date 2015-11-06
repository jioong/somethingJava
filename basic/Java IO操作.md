9/6/2015 8:50:20 PM 
# Java IO 操作 #

## 基于 Java SE 7 ##

基本文件操作 **API** 位于 *java.nit.file* 包及其两个子包 *java.nio.file.attribute* 和 *java.nio.file.spi* 中。  
新的**API**把文件相关的操作从 *java.io* 包中分离出来，而且使文件系统的管理更为只管，此外提供一些额外的方法。


### 流 ###

流是一个连续的字节序列。输入流用来读取该序列，输出流用来构建该序列。  
*InputStream* 和 *OutputStream* 所操纵的基本单元就是字节。每次读取或写入单个字节或是字节数组。  

**从字节的层次处理数据的话，操作会非常繁琐。**  

**用更易使用的流实现来包装基本的字节流。**  
*DatainputStream* 和*DataOutputStream* 。  
读取或写入的Java 中的对象的话，可以使用 *ObjectInputStream* 和 *ObjectOutputStream* 。  

基本流所提供的对于输入和输出的控制比较弱，*InputStream* 只提供了顺序读取、跳过部分字节和标记/重置的支持。*OutputStream* 则只能顺序输出。

### 流的使用 ###

每个打开的流都应该被正确的关闭以释放资源。

### 缓冲区 ###

使用缓冲区来提高性能。传统的缓冲区是使用数组来完成的。  
*Buffer*的三个属性：  
1. *position* : 表示*buffer*当前的去屑位置。
2. *limit* : 表示可用的读写范围。
3. *capacity* : 容量限制，是在创建的时候指定的。  

**将数据读入缓冲区之前，需要调用 clear 方法；将缓冲区中的数据输出之前，需要调用 flip 方法。**
#XML解析

## java解析 --- DOM
### 原理  
DOM技术会把XML文件看做 Dom 树。整个文档被看做 Document  
1. DOM 会把 XML 文件看做“一棵树”，并加载到内存。
2. DOM 特别适合做 CRUD 操作。
3. 不适合去处理比较大的 XML 文件。
   
### 获得 JAXP 中的 DOM 解析器步骤
1. 调用 DocumentBuilderFactory.newInstance() 方法得到创建 DOM 解析器的工厂。
2. 调用工厂对象的 newDocumentBuilder 方法得到 DOM 解析器对象。
3. 调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象，接着可以利用 DOM 特性对整个 XML 文档进行操作。
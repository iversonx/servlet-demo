## Servlet接口
Servlet接口是Servlet API的核心抽象。所有Servlet都直接或间接的实现Servlet接口。

Servlet API中实现Servlet接口的`GenericServlet`和`HttpServlet`。通常扩展`HttpServlet`来实现Servlet。

Servlet接口定义了`service`方法来处理请求。当有请求进来时，由Servlet容器调用`service`方法。

### HTTP特定的请求处理方法
`HttpServlet`中定义了HTTP请求相关的方法，这些方法由`HttpServlet`类中的`service`方法自动调用。这些方法是：

- doGet 处理Get请求
- doPost 处理Post请求
- doPut 处理Put请求
- doDelete 处理Delete请求
- doHead 处理Head请求
- doOptions 处理Options请求

### 实例数
对于未在分布式环境中的servlet容器对每个servlet必须仅使用一个实例。

对于分布式环境，每个JVM中的servlet容器对歌每个servlet必须只能有一个实例。

### 生命周期

`Serlvet`接口定义了Servlet生命周期相关的API：`init`,`service`,`destroy`，所有Servlet必须实现这些方法。

#### 加载和实例化

容器启动时，Servlet容器负责加载和初始化Servlet，或延迟直到有请求需要处理时初始化。

当Servlet容器启动后，必须定位所需要的Servlet类。servlet容器使用普通类加载工具来加载servlet类。

加载Servlet类后，容器将其实例化以供使用。

> 默认情况下，在第一次请求到来时容器才实例化相应的Servlet。可以通过`@WebServlet`的`loadOnStartup`配置指定Servlet在容器启动后就实例化

#### 初始化

Servlet实例化后，容器必须初始化Servlet之后，Servlet才能处理客户端的请求。初始化的目的是为了执行一些前置工作，比如建立JDBC连接之类等操作。

#### 请求处理

Servlet正确初始化后，容器就可以使用它处理请求。请求由`ServletRequest`类型的对象表示，使用`ServletResponse`类型的对象来填写响应。

##### 异步处理

有时候，`Filter`及`Servlet`在响应之前必须等待资源或事件以便完成请求处理。在Servlet中等待是一个消耗线程和其他有限资源的阻塞操作；可能会导致其他线程进行
等待，进而出现线程不足和服务质量下降。

Servlet 3.0引入了异步处理请求的功能，线程可以返回到容器并执行其他任务。当对请求开始异步处理，另一个线程或回调生成响应并调用`complete`，或分派请求，以便
它可以使用`AsyncContext`在容器的上下文中运行。

异步处理的事件序列

1. 请求被接收，通过一系列的`filter`后传递给Servlet
2. Servlet处理请求参数或`Content`以确定请求的性质
3. Servlet发出对资源或数据的请求，例如：加入等待JDBC连接的队列。
4. Servlet返回而不生成响应。
5. 一段时间后，Servlet所请求的资源变为可用，处理事件的线程继续在同一个线程中处理，或通过`AsyncContext`调度到容器中的资源

`@WebServlet`和`@Filter`注解的`asyncSupported`属性，当值为true时，应用通过执行startAsync，可以启动一个线程进行异步处理。

#### 结束服务
当servlet容器确定servlet应该被移除时，会调用servlet的destroy方法，以释servlet使用的任何资源。
在调用destroy方法之前，容器必须让当前正在执行service方法的线程执行完成

一旦调用了 servlet 实例的 destroy 方法，容器无法再路由其他请求到该 servlet 实例了。如果容器需要再次使用该 servlet，它必须用该servlet 类的一个新的实例。在 destroy 方法完成后，servlet 容器必须释放 servlet 实例以便被垃圾回收。

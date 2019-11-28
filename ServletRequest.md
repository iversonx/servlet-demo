# ServletRequest
用于封装客户端请求信息。Servlet容器创建ServletRequest对象，并将其作为参数传递给Servlet的service方法。

ServletRequest对象提供的数据包括参数名称和参数值，属性以及输入流。

ServletRequest提供下列方法获取URI查询字符串和POST参数

- getParameterValues: 返回一个String数组，包含与参数名关联的所有参数值
- getParameter: 以字符串形式返回请求参数的值，如果参数不存在，则返回null。
- getParameterNames: 返回所有参数的名称, Enumeration<String>类型
- getParameterMap: 返回请求参数的Map，其中参数名为key,参数为value

查询字符串和 POST 请求的数据被汇总到请求参数集合中。查询字符串数据放在 POST 数据之前。

例如，如果请求由查询字符串 a=hello 和 POST 数据 a=goodbye&a=world 组成，得到的参数集合顺序将是 a=(hello, goodbye, world)。

Post 表单数据能填充到参数集（Parameter Set）前必须满足的条件

- 请求是HTTP或HTTPS请求
- HTTP请求方法是POST
- 请求的Content Type是application/x-www-form-urlencoded
- Servlet已对请求对象上的任何getParameter方法族进行了初始调用



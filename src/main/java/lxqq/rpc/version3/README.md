## 背景知识

+ 代码解耦
+ 线程池

本节问题：
如果一个服务端要提供多个服务的接口， 例如新增一个BlogService，怎么处理?

自然的想到用一个Map来保存

```java
Map<String, Object> map = new HashMap<>();
UserService userService = new UserServiceImpl();
BlogService blogService = new BlogServiceImpl();
map.put("***.userService", userService);
map.put("***.blogService", blogService);
```

此时来了一个request，我们就能从map中取出对应的服务

Object service = map.get(request.getInterfaceName())

1. 新的服务接口`BlogService`及实现类`BlogServiceImpl`
2. 新增实体类`Blog`
3. 新建`ServiceProvide`管理服务
4. 抽象出服务接口，实现开闭原则
5. 使用线程或线程池处理客户端请求
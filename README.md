# 实验室管理系统

### update

#### 2024-12-21
用cache缓存并用CacheManager查看缓存
遇到问题:无法解析注解中key  -key="'中间再有一层才能识别'"
修改cacheConfig使其底层用redis实现
删除redis数据库中的数据,用CacheManager得不到了,说明调用的是得到redis缓存

#### 2024-12-11
批处理添加用户信息


#### 2024-12-05
结果集映射和行映射
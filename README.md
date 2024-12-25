# 实验室管理系统

### update

#### 2024-12-25
批量删除预约记录, 保证事务:4,1 前者自己的后者别人的,抛出权限错误异常,应进行回滚

#### 2024-12-24
每个添加逻辑把id置空,确保安全性
BeanUtils.copyProperties(laboratory,countDTO)
#### 2024-12-23
重载了ResultVO.ok() 有无参数和有参数(message)无需每次builder

#### 2024-12-21
用cache缓存并用CacheManager查看缓存
遇到问题:无法解析注解中key  -key="'中间再有一层才能识别'"
修改cacheConfig使其底层用redis实现
删除redis数据库中的数据,用CacheManager得不到了,说明调用的是得到redis缓存

#### 2024-12-11
批处理添加用户信息


#### 2024-12-05
结果集映射和行映射

# Booking-Center
Booking-Center项目基于spring cloud + spring security + jwt实现权限控制，使用docker、docker compose部署，具体业务逻辑没想好，开发中...

## 模块说明
- api-gateway 网关
- booking-common 通用工具
- booking-service 核心业务逻辑
- eureka-server 注册中心
- feign-consumer 声明式服务调用（负载均衡、熔断）
- oauth-server 权限认证中心
- docs 脚本文档等

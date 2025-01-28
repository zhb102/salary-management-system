# 公司人员工资管理系统 (Salary Management System)

![React](https://img.shields.io/badge/React-18.x-blue?logo=react)
![Ant Design](https://img.shields.io/badge/Ant%20Design-5.x-blue?logo=ant-design)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.x-green?logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-5.7+-blue?logo=mysql)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-yellow)
![JDK](https://img.shields.io/badge/JDK-1.8+-orange?logo=java)
![npm](https://img.shields.io/badge/npm-6.x-red?logo=npm)

[English](./README.en.md) | 简体中文

一个现代化的企业工资管理系统，采用前后端分离架构，提供完整的员工薪资管理解决方案。系统具有高效、安全、易用等特点，适用于中小型企业的人事工资管理需求。

## ✨ 特性

- 📦 开箱即用的高质量 React 组件
- 🛡 使用 TypeScript 开发，提供完整的类型定义
- 📱 响应式设计，支持移动端显示
- 🎨 定制主题能力，满足多样化的产品需求
- 🌍 国际化支持
- 🔢 大量的业务模板，提高开发效率

## 🚀 系统架构

### 🎯 前端技术栈
- React.js
- Ant Design
- Axios
- React Router
- Redux

### 🛠 后端技术栈
- Spring Boot 2.7.x
- Spring Security
- MyBatis Plus
- MySQL
- JWT 认证
- Maven

## 💡 核心功能

### 👥 1. 员工管理
- 员工信息的增删改查
- 员工部门调动
- 员工状态管理

### 💰 2. 工资管理
- 基本工资设置
- 津贴管理（岗位津贴、午餐补贴）
- 加班工资计算
- 全勤奖金
- 社保公积金扣除
- 个人所得税自动计算
- 实发工资统计

### 🏢 3. 部门管理
- 部门结构维护
- 部门人员管理
- 部门工资统计

### ⚙️ 4. 系统管理
- 用户权限控制
- 角色分配
- 系统日志

## 📊 数据库设计

### 📝 主要数据表
1. `departments` - 部门信息表
   - departmentId (主键)
   - departmentName
   - isDeleted

2. `employees` - 员工信息表
   - employeeId (主键)
   - name
   - departmentId (外键)
   - isDeleted

3. `salaries` - 工资信息表
   - salaryId (主键)
   - employeeId (外键)
   - year, month
   - workDays, actualWorkDays
   - basicSalary
   - positionAllowance
   - lunchAllowance
   - overtimeSalary
   - fullAttendanceBonus
   - socialInsurance
   - housingFund
   - tax
   - deductions
   - netSalary

4. `users` - 系统用户表
   - userid (主键)
   - username
   - password
   - userrole
   - createdat
   - updatedat

## 🚀 快速开始

### 📋 环境要求
- JDK >= 1.8
- Maven >= 3.6
- MySQL >= 5.7
- Node.js >= 14.x
- npm >= 6.x

### 📥 安装步骤

1. 克隆项目
```bash
git clone https://github.com/zhb102/salary-management-system.git
cd salary-management-system
```

2. 安装依赖

```bash
# 后端打包
cd backend
mvn clean install

# 前端依赖安装
cd ../frontend
npm install
```

3. 数据库配置

- 创建数据库
- 导入 `MySQL/wage-management.sql` 文件
- 修改 `backend/src/main/resources/application.yml` 中的数据库配置

4. 启动服务

```bash
# 启动后端服务
cd backend
mvn spring-boot:run

# 启动前端服务
cd frontend
npm start
```

## 📖 使用说明

1. 系统默认管理员账号：admin（首次使用请修改密码）
2. 普通用户需要管理员创建账号
3. 具体操作手册请参考 `docs` 目录

## 🤝 贡献指南

1. Fork 本仓库
2. 创建新的功能分支
3. 提交你的修改
4. 创建 Pull Request

## 📄 许可证

MIT License

## 📞 联系方式

如有问题请提交 [Issue](https://github.com/zhb102/salary-management-system/issues) 或联系项目维护者。

## 🌟 支持我们

如果这个项目对你有帮助，请给我们一个 star ⭐️

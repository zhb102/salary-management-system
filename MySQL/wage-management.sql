create table departments
(
    departmentId   int auto_increment comment '部门ID'
        primary key,
    departmentName varchar(100)         not null comment '部门名称',
    isDeleted      tinyint(1) default 0 null comment '逻辑删除标志'
);

create table employees
(
    employeeId   int auto_increment comment '员工ID'
        primary key,
    name         varchar(100)         not null comment '姓名',
    departmentId int                  not null comment '部门ID',
    isDeleted    tinyint(1) default 0 null comment '逻辑删除标志',
    constraint employees_ibfk_1
        foreign key (departmentId) references departments (departmentId)
);

create index departmentId
    on employees (departmentId);

create table salaries
(
    salaryId            int auto_increment comment '工资ID'
        primary key,
    employeeId          int                                                                        not null comment '员工ID',
    year                int                                                                        not null comment '年份',
    month               int                                                                        not null comment '月份',
    workDays            int                                                                        not null comment '本月应出勤天数',
    actualWorkDays      int                                                                        not null comment '实际出勤天数',
    basicSalary         decimal(10, 2)                                                             not null comment '基本工资',
    positionAllowance   decimal(10, 2)                                                             not null comment '岗位津贴',
    lunchAllowance      decimal(10, 2)                                                             not null comment '午餐补贴',
    overtimeSalary      decimal(10, 2) default 0.00                                                null comment '加班工资',
    fullAttendanceBonus decimal(10, 2) default 0.00                                                null comment '全勤工资',
    socialInsurance     decimal(10, 2)                                                             not null comment '扣社保',
    housingFund         decimal(10, 2)                                                             not null comment '扣公积金',
    tax                 decimal(10, 2) default ((
        ((((((`basicSalary` + `positionAllowance`) + `lunchAllowance`) + `overtimeSalary`) + `fullAttendanceBonus`) -
          `socialInsurance`) - `housingFund`) -
        5000))                                                                                     not null comment '扣税',
    deductions          decimal(10, 2) default 0.00                                                null comment '迟到请假等扣除',
    netSalary           decimal(10, 2) default ((
        ((((((((`actualWorkDays` + `basicSalary`) + `positionAllowance`) + `lunchAllowance`) + `overtimeSalary`) +
            `fullAttendanceBonus`) - `socialInsurance`) - `housingFund`) - `tax`) -
        `deductions`))                                                                             not null comment '实发工资',
    isDeleted           tinyint(1)     default 0                                                   null comment '逻辑删除标志',
    constraint salaries_ibfk_1
        foreign key (employeeId) references employees (employeeId)
);

create index employeeId
    on salaries (employeeId);

create table users
(
    userid    int auto_increment comment '用户ID'
        primary key,
    username  varchar(100)                        not null comment '用户名',
    password  varchar(255)                        not null comment '密码哈希',
    createdat timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    updatedat timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isdeleted tinyint   default 0                 null comment '逻辑删除标志',
    userrole  int       default 0                 not null comment '用户角色 0-普通用户 1-用户管理员',
    constraint username
        unique (username)
);


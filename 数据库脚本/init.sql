-- yuyuedb.department_first definition

CREATE TABLE `department_first`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `department_name` varchar(100) NOT NULL COMMENT '一级科室名称',
    PRIMARY KEY (`id`),
    UNIQUE KEY `department_name` (`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- yuyuedb.patients definition

CREATE TABLE `patients`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`      varchar(50) NOT NULL COMMENT '姓名',
    `gender`    varchar(2)  NOT NULL COMMENT '性别',
    `id_number` varchar(18) NOT NULL COMMENT '身份证号',
    `user_id`   int(11) NOT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_number` (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- yuyuedb.department_second definition

CREATE TABLE `department_second`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `department_id`   int(11) NOT NULL COMMENT '一级科室ID',
    `department_name` varchar(100) NOT NULL COMMENT '二级科室名称',
    PRIMARY KEY (`id`),
    KEY               `department_id` (`department_id`),
    CONSTRAINT `department_second_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department_first` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- yuyuedb.doctor definition

CREATE TABLE `doctor`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`          varchar(50) NOT NULL COMMENT '医生姓名',
    `title`         varchar(50) NOT NULL COMMENT '职称',
    `department_id` int(11) NOT NULL COMMENT '所属二级科室ID',
    PRIMARY KEY (`id`),
    KEY             `department_id` (`department_id`),
    CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department_second` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- yuyuedb.schedule definition

CREATE TABLE `schedule`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `doctor_id`     int(11) NOT NULL COMMENT '医生ID',
    `work_date`     date NOT NULL COMMENT '排班日期',
    `time_slot`     enum('上午','下午','晚上') NOT NULL COMMENT '时间段',
    `total_num`     int(11) DEFAULT '30' COMMENT '可预约总数',
    `available_num` int(11) DEFAULT '30' COMMENT '剩余可预约数',
    PRIMARY KEY (`id`),
    KEY             `doctor_id` (`doctor_id`),
    CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
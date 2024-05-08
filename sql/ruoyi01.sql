DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

-- ----------------------------
-- 1、存储每一个已配置的 jobDetail 的详细信息
-- ----------------------------
CREATE TABLE QRTZ_JOB_DETAILS
(
    sched_name        VARCHAR(120) NOT NULL COMMENT '调度名称',
    job_name          VARCHAR(200) NOT NULL COMMENT '任务名称',
    job_group         VARCHAR(200) NOT NULL COMMENT '任务组名',
    description       VARCHAR(250) NULL COMMENT '相关介绍',
    job_class_name    VARCHAR(250) NOT NULL COMMENT '执行任务类名称',
    is_durable        VARCHAR(1)   NOT NULL COMMENT '是否持久化',
    is_nonconcurrent  VARCHAR(1)   NOT NULL COMMENT '是否并发',
    is_update_data    VARCHAR(1)   NOT NULL COMMENT '是否更新数据',
    requests_recovery VARCHAR(1)   NOT NULL COMMENT '是否接受恢复执行',
    job_data          BLOB NULL COMMENT '存放持久化job对象',
    PRIMARY KEY (sched_name, job_name, job_group)
) ENGINE=INNODB COMMENT = '任务详细信息表';

-- ----------------------------
-- 2、 存储已配置的 Trigger 的信息
-- ----------------------------
CREATE TABLE QRTZ_TRIGGERS
(
    sched_name     VARCHAR(120) NOT NULL COMMENT '调度名称',
    trigger_name   VARCHAR(200) NOT NULL COMMENT '触发器的名字',
    trigger_group  VARCHAR(200) NOT NULL COMMENT '触发器所属组的名字',
    job_name       VARCHAR(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
    job_group      VARCHAR(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
    description    VARCHAR(250) NULL COMMENT '相关介绍',
    next_fire_time BIGINT(13) NULL COMMENT '上一次触发时间（毫秒）',
    prev_fire_time BIGINT(13) NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
    priority       INTEGER NULL COMMENT '优先级',
    trigger_state  VARCHAR(16)  NOT NULL COMMENT '触发器状态',
    trigger_type   VARCHAR(8)   NOT NULL COMMENT '触发器的类型',
    start_time     BIGINT(13) NOT NULL COMMENT '开始时间',
    end_time       BIGINT(13) NULL COMMENT '结束时间',
    calendar_name  VARCHAR(200) NULL COMMENT '日程表名称',
    misfire_instr  SMALLINT(2) NULL COMMENT '补偿执行的策略',
    job_data       BLOB NULL COMMENT '存放持久化job对象',
    PRIMARY KEY (sched_name, trigger_name, trigger_group),
    FOREIGN KEY (sched_name, job_name, job_group) REFERENCES QRTZ_JOB_DETAILS (sched_name, job_name, job_group)
) ENGINE=INNODB COMMENT = '触发器详细信息表';

-- ----------------------------
-- 3、 存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数
-- ----------------------------
CREATE TABLE QRTZ_SIMPLE_TRIGGERS
(
    sched_name      VARCHAR(120) NOT NULL COMMENT '调度名称',
    trigger_name    VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    trigger_group   VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    repeat_count    BIGINT(7) NOT NULL COMMENT '重复的次数统计',
    repeat_interval BIGINT(12) NOT NULL COMMENT '重复的间隔时间',
    times_triggered BIGINT(10) NOT NULL COMMENT '已经触发的次数',
    PRIMARY KEY (sched_name, trigger_name, trigger_group),
    FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES QRTZ_TRIGGERS (sched_name, trigger_name, trigger_group)
) ENGINE=INNODB COMMENT = '简单触发器的信息表';

-- ----------------------------
-- 4、 存储 Cron Trigger，包括 Cron 表达式和时区信息
-- ----------------------------
CREATE TABLE QRTZ_CRON_TRIGGERS
(
    sched_name      VARCHAR(120) NOT NULL COMMENT '调度名称',
    trigger_name    VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    trigger_group   VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    cron_expression VARCHAR(200) NOT NULL COMMENT 'cron表达式',
    time_zone_id    VARCHAR(80) COMMENT '时区',
    PRIMARY KEY (sched_name, trigger_name, trigger_group),
    FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES QRTZ_TRIGGERS (sched_name, trigger_name, trigger_group)
) ENGINE=INNODB COMMENT = 'Cron类型的触发器表';

-- ----------------------------
-- 5、 Trigger 作为 Blob 类型存储(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)
-- ----------------------------
CREATE TABLE QRTZ_BLOB_TRIGGERS
(
    sched_name    VARCHAR(120) NOT NULL COMMENT '调度名称',
    trigger_name  VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    trigger_group VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    blob_data     BLOB NULL COMMENT '存放持久化Trigger对象',
    PRIMARY KEY (sched_name, trigger_name, trigger_group),
    FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES QRTZ_TRIGGERS (sched_name, trigger_name, trigger_group)
) ENGINE=INNODB COMMENT = 'Blob类型的触发器表';

-- ----------------------------
-- 6、 以 Blob 类型存储存放日历信息， quartz可配置一个日历来指定一个时间范围
-- ----------------------------
CREATE TABLE QRTZ_CALENDARS
(
    sched_name    VARCHAR(120) NOT NULL COMMENT '调度名称',
    calendar_name VARCHAR(200) NOT NULL COMMENT '日历名称',
    calendar      BLOB         NOT NULL COMMENT '存放持久化calendar对象',
    PRIMARY KEY (sched_name, calendar_name)
) ENGINE=INNODB COMMENT = '日历信息表';

-- ----------------------------
-- 7、 存储已暂停的 Trigger 组的信息
-- ----------------------------
CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS
(
    sched_name    VARCHAR(120) NOT NULL COMMENT '调度名称',
    trigger_group VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    PRIMARY KEY (sched_name, trigger_group)
) ENGINE=INNODB COMMENT = '暂停的触发器表';

-- ----------------------------
-- 8、 存储与已触发的 Trigger 相关的状态信息，以及相联 Job 的执行信息
-- ----------------------------
CREATE TABLE QRTZ_FIRED_TRIGGERS
(
    sched_name        VARCHAR(120) NOT NULL COMMENT '调度名称',
    entry_id          VARCHAR(95)  NOT NULL COMMENT '调度器实例id',
    trigger_name      VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    trigger_group     VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    instance_name     VARCHAR(200) NOT NULL COMMENT '调度器实例名',
    fired_time        BIGINT(13) NOT NULL COMMENT '触发的时间',
    sched_time        BIGINT(13) NOT NULL COMMENT '定时器制定的时间',
    priority          INTEGER      NOT NULL COMMENT '优先级',
    state             VARCHAR(16)  NOT NULL COMMENT '状态',
    job_name          VARCHAR(200) NULL COMMENT '任务名称',
    job_group         VARCHAR(200) NULL COMMENT '任务组名',
    is_nonconcurrent  VARCHAR(1) NULL COMMENT '是否并发',
    requests_recovery VARCHAR(1) NULL COMMENT '是否接受恢复执行',
    PRIMARY KEY (sched_name, entry_id)
) ENGINE=INNODB COMMENT = '已触发的触发器表';

-- ----------------------------
-- 9、 存储少量的有关 Scheduler 的状态信息，假如是用于集群中，可以看到其他的 Scheduler 实例
-- ----------------------------
CREATE TABLE QRTZ_SCHEDULER_STATE
(
    sched_name        VARCHAR(120) NOT NULL COMMENT '调度名称',
    instance_name     VARCHAR(200) NOT NULL COMMENT '实例名称',
    last_checkin_time BIGINT(13) NOT NULL COMMENT '上次检查时间',
    checkin_interval  BIGINT(13) NOT NULL COMMENT '检查间隔时间',
    PRIMARY KEY (sched_name, instance_name)
) ENGINE=INNODB COMMENT = '调度器状态表';

-- ----------------------------
-- 10、 存储程序的悲观锁的信息(假如使用了悲观锁)
-- ----------------------------
CREATE TABLE QRTZ_LOCKS
(
    sched_name VARCHAR(120) NOT NULL COMMENT '调度名称',
    lock_name  VARCHAR(40)  NOT NULL COMMENT '悲观锁名称',
    PRIMARY KEY (sched_name, lock_name)
) ENGINE=INNODB COMMENT = '存储的悲观锁信息表';

-- ----------------------------
-- 11、 Quartz集群实现同步机制的行锁表
-- ----------------------------
CREATE TABLE QRTZ_SIMPROP_TRIGGERS
(
    sched_name    VARCHAR(120) NOT NULL COMMENT '调度名称',
    trigger_name  VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
    trigger_group VARCHAR(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
    str_prop_1    VARCHAR(512) NULL COMMENT 'String类型的trigger的第一个参数',
    str_prop_2    VARCHAR(512) NULL COMMENT 'String类型的trigger的第二个参数',
    str_prop_3    VARCHAR(512) NULL COMMENT 'String类型的trigger的第三个参数',
    int_prop_1    INT NULL COMMENT 'int类型的trigger的第一个参数',
    int_prop_2    INT NULL COMMENT 'int类型的trigger的第二个参数',
    long_prop_1   BIGINT NULL COMMENT 'long类型的trigger的第一个参数',
    long_prop_2   BIGINT NULL COMMENT 'long类型的trigger的第二个参数',
    dec_prop_1    NUMERIC(13, 4) NULL COMMENT 'decimal类型的trigger的第一个参数',
    dec_prop_2    NUMERIC(13, 4) NULL COMMENT 'decimal类型的trigger的第二个参数',
    bool_prop_1   VARCHAR(1) NULL COMMENT 'Boolean类型的trigger的第一个参数',
    bool_prop_2   VARCHAR(1) NULL COMMENT 'Boolean类型的trigger的第二个参数',
    PRIMARY KEY (sched_name, trigger_name, trigger_group),
    FOREIGN KEY (sched_name, trigger_name, trigger_group) REFERENCES QRTZ_TRIGGERS (sched_name, trigger_name, trigger_group)
) ENGINE=INNODB COMMENT = '同步机制的行锁表';

COMMIT;
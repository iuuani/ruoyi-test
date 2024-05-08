-- ----------------------------
-- 1、产品属性表
-- ----------------------------
DROP TABLE IF EXISTS prod_attribute;
CREATE TABLE prod_attribute
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '属性编号',
    `attr_name`    VARCHAR(255) NOT NULL UNIQUE COMMENT '属性名称',
    `gmt_create`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='产品属性表';
-- 初始化-产品属性表数据
INSERT INTO prod_attribute(attr_name)
VALUES ("成品"),
       ("配件");

-- ----------------------------
-- 2、产品类型表
-- ----------------------------
DROP TABLE IF EXISTS prod_type;
CREATE TABLE prod_type
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '类型编号',
    `type_name`    VARCHAR(255) NOT NULL UNIQUE COMMENT '类型名称',
    `type_code`    VARCHAR(255) NOT NULL UNIQUE COMMENT '类型编码',
    `gmt_create`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='产品类型表';
-- 初始化-产品类型表数据
INSERT INTO prod_type(type_name, type_code)
VALUES ("笔记本电脑", "NB"),
       ("平板电脑", "TL"),
       ("手机", "MP"),
       ("充电头", "CP"),
       ("手机膜", "PS"),
       ("数据线", "US");

-- ----------------------------
-- 3、产品品牌表
-- ----------------------------
DROP TABLE IF EXISTS prod_brand;
CREATE TABLE prod_brand
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '品牌编号',
    `brand_name`   VARCHAR(255) NOT NULL UNIQUE COMMENT '品牌名称',
    `gmt_create`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='产品品牌表';
-- 初始化-产品品牌表数据
INSERT INTO prod_brand(brand_name)
VALUES ("华为"),
       ("小米"),
       ("苹果");

-- ----------------------------
-- 4、产品单位表
-- ----------------------------
DROP TABLE IF EXISTS prod_unit;
CREATE TABLE prod_unit
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '单位编号',
    `unit_name`    VARCHAR(255) NOT NULL UNIQUE COMMENT '单位名称',
    `gmt_create`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='产品单位表';
-- 初始化-产品单位表数据
INSERT INTO prod_unit(unit_name)
VALUES ("个"),
       ("枚"),
       ("台"),
       ("套"),
       ("件");

-- ----------------------------
-- 5、产品权限表
-- ----------------------------
DROP TABLE IF EXISTS prod_permission;
CREATE TABLE prod_permission
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '权限编号',
    `perm_name`    VARCHAR(255) NOT NULL UNIQUE COMMENT '权限名称',
    `gmt_create`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='产品权限表';
-- 初始化-产品权限表数据
INSERT INTO prod_permission(perm_name)
VALUES ("销售"),
       ("采购"),
       ("赠送");

-- ----------------------------
-- 6、产品表
-- ----------------------------
DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    `id`             BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '产品编号',
    `prod_code`      VARCHAR(255)  NOT NULL COMMENT '产品编码',
    `prod_attr`      INT           NOT NULL COMMENT '属性编号',
    `prod_type`      INT           NOT NULL COMMENT '类型编号',
    `prod_name`      VARCHAR(255)  NOT NULL COMMENT '产品名称',
    `prod_brand`     INT           NOT NULL COMMENT '品牌编号',
    `prod_specs`     VARCHAR(255)  NOT NULL COMMENT '规格型号',
    `prod_unit`      INT           NOT NULL COMMENT '单位编号',
    `cost_price`     DECIMAL(8, 2) NOT NULL COMMENT '成本单价',
    `vat_rate`       DECIMAL(8, 2) NOT NULL COMMENT '增值税税率',
    `incl_tax_price` DECIMAL(8, 2) NOT NULL COMMENT '销售单价(含税)',
    `excl_tax_price` DECIMAL(8, 2) NOT NULL COMMENT '销售单价(不含税)',
    `tax_amount`     DECIMAL(8, 2) NOT NULL COMMENT '税额',
    `gross_profit`   DECIMAL(8, 2) NOT NULL COMMENT '售价毛利',
    `prod_perm`      INT           NOT NULL COMMENT '权限编号',
    `prod_img`       VARCHAR(255)           DEFAULT '' COMMENT '产品图片',
    `gmt_create`     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='产品表';
INSERT INTO product (prod_code, prod_attr, prod_type, prod_name, prod_brand, prod_specs, prod_unit, cost_price,
                     vat_rate, incl_tax_price, excl_tax_price, tax_amount, gross_profit, prod_perm)
VALUES ('P001', 1, 1, '笔记本电脑', 1, '规格型号A', 1, 1200.00, 0.05, 1260.00, 1200.00, 60.00, 60.00, 1),
       ('P002', 2, 2, '手机', 2, '规格型号B', 2, 800.00, 0.05, 840.00, 800.00, 40.00, 40.00, 1),
       ('P003', 1, 1, '平板电脑', 3, '规格型号C', 1, 1500.00, 0.05, 1575.00, 1500.00, 75.00, 75.00, 2);
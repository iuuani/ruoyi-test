-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品', '2001', '1', 'product', 'crm/product/index', 1, 0, 'C', '0', '0', 'crm:product:list', '#', 'admin', sysdate(), '', null, '产品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'crm:product:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'crm:product:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'crm:product:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'crm:product:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'crm:product:export',       '#', 'admin', sysdate(), '', null, '');
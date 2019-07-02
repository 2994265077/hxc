

/*******************************
模块名：统一权限认证
 */

/*
drop TABLE auth_user_roles;
drop TABLE auth_role_permissions;
drop TABLE auth_users;
drop table auth_permissions;
drop table auth_roles;
*/
/*=============== DDL ========================*/
create table auth_users(
	user_id varchar2(32) not null primary key,
	account varchar2(50) not null UNIQUE,
	password varchar2(50) not null,
	enabled Number(1) not null,
	phoneNumber varchar2(20) not null,
	id_card varchar2(30) not null,
	real_name varchar2(30) not null,
	organization_code VARCHAR2(50) not null,
	create_user_account varchar2(32) not null,
	create_time DATE default sysdate not null,
	update_time date default sysdate not null
);
comment on TABLE auth_users is '用户信息表';
comment on column auth_users.user_id is '编号PK';
comment on column auth_users.account is '账号';
comment on column auth_users.password is '密码';
comment on column auth_users.enabled is '是否有效,1有效,0被禁用';
comment on column auth_users.phoneNumber is '手机号';
comment on column auth_users.id_card is '身份证号';
comment on column auth_users.real_name is '真实姓名';
comment on column auth_users.create_user_account is '创建用户的账号';
comment on column auth_users.organization_code is '组织结构编号';
comment on column auth_users.create_time is '创建时间';
comment on column auth_users.update_time is '更新时间';

create table auth_permissions(
	permission_id varchar2(32) not null primary key,
	permission_str varchar2(50) not null,
	permission_name varchar2(50) not null,
	permission_url VARCHAR2(200) null,
	description VARCHAR2(200) null,
	create_time DATE default sysdate not null,
	update_time date default sysdate not null
);
comment on table auth_permissions is '权限表';
comment on column auth_permissions.permission_id is '主键';
comment on column auth_permissions.permission_str is '权限字符串';
comment on column auth_permissions.permission_name is '权限名';
comment on column auth_permissions.permission_url is '权限控制的url范围';
comment on column auth_permissions.description is '描述';
comment on column auth_permissions.create_time is '创建时间';
comment on column auth_permissions.update_time is '更新时间';


create table auth_roles(
	role_id varchar2(32) not null primary key,
	role_name varchar2(50) not null,
	description VARCHAR2(200) NULL,
	create_time DATE default sysdate not null,
	update_time date default sysdate not null
);
comment on table auth_roles is '角色表';
comment on column auth_roles.role_id is '主键';
comment on column auth_roles.role_name is '角色名';
comment on column auth_roles.description is '描述';
comment on column auth_roles.create_time is '创建时间';
comment on column auth_roles.update_time is '更新时间';


create table auth_user_roles(
	user_id varchar2(32) not null,
	role_id varchar2(32) not null,
	create_time DATE default sysdate not null,
	update_time date default sysdate not null
);
alter table auth_user_roles add constraint PK_user_roles_composite_key primary key (user_id,role_id);
ALTER TABLE  auth_user_roles ADD CONSTRAINT FK_user_roles_account FOREIGN KEY(user_id ) REFERENCES AUTH_USERS(user_id);
ALTER TABLE  auth_user_roles ADD CONSTRAINT FK_user_roles_role FOREIGN KEY(role_id ) REFERENCES auth_roles(role_id);
comment on TABLE auth_user_roles is '用户-角色表';
comment on column auth_user_roles.user_id is '用户编号';
comment on column auth_user_roles.role_id is '角色编号';
comment on column auth_user_roles.create_time is '创建时间';
comment on column auth_user_roles.update_time is '更新时间';

create table auth_role_permissions(
	role_id varchar2(32) not null,
	permission_id varchar2(32) not null,
	create_time DATE default sysdate not null,
	update_time date default sysdate not null
);
alter table auth_role_permissions add constraint PK_role_permissions_c_key primary key (role_id,permission_id);
ALTER TABLE  auth_role_permissions ADD CONSTRAINT FK_role_permissions_permission FOREIGN KEY(permission_id ) REFERENCES auth_permissions(permission_id);
ALTER TABLE  auth_role_permissions ADD CONSTRAINT FK_role_permissions_role FOREIGN KEY(role_id ) REFERENCES auth_roles(role_id);
comment on TABLE auth_role_permissions is '角色-权限表';
comment on column auth_role_permissions.role_id is '角色编号';
comment on column auth_role_permissions.permission_id is '权限编号';
comment on column auth_role_permissions.create_time is '创建时间';
comment on column auth_role_permissions.update_time is '更新时间';


/*权限初始化*/
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(1,'安全隐患一张图','安全隐患一张图子系统:安全隐患一张图:**','/hiddendanger/**','安全隐患一张图');
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(2,'运行展现','运行展现子系统:运行展现:*','/rundisplay/**','运行展现');
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(3,'城市体征','城市体征子系统:城市体征:*','/urbansign/**','城市体征');
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(4,'预警中心','预警中心子系统:预警中心:*','/alarmcenter/**','预警中心');
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(5,'运行周报','运行周报:运行周报:*','/tableau/**','运行周报');
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(6,'卫计一张图','卫计一张图:卫计一张图:*','/publicHealth/**','卫计一张图');
INSERT INTO auth_permissions(permission_id,permission_name,permission_str,permission_url,description) values(7,'搜索','搜索:搜索:*','/search/**','搜索');

-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(1,'大屏汇报页面','大屏子系统:大屏汇报页面:*','大屏汇报页面');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(2,'运行展现页面','运行展现子系统:运行展现页面:*','运行展现页面');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(3,'城市KPI','城市体征子系统:城市KPI:*','城市KPI');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(4,'城市基本面','城市体征子系统:城市基本面:*','城市基本面');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(5,'运营周报','城市体征子系统:运营周报:*','运营周报');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(6,'预警监测页面','预警监测子系统:预警监测页面:*','预警监测页面');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(7,'安全隐患一张图','专题子系统:安全隐患一张图:*','安全隐患一张图');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(8,'卫计一张图','专题子系统:卫计一张图:*','卫计一张图');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(9,'生态环境一张图','专题子系统:生态环境一张图:*','生态环境一张图');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(10,'数据展现管理','后台管理:数据展现管理:*','数据展现管理');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(11,'信息配置管理','后台管理:信息配置管理:*','信息配置管理');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(12,'数据分类管理','后台管理:数据分类管理:*','数据分类管理');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(13,'预警信息管理','后台管理:预警信息管理:*','预警信息管理');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(14,'用户设置','后台管理:用户设置:*','用户设置');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(15,'角色管理','后台管理:角色管理:*','角色管理');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(16,'组织结构','后台管理:组织结构:*','组织结构');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(17,'报告推送','后台管理:报告推送:*','报告推送');
-- INSERT INTO auth_permissions(permission_id,permission_name,permission_str,description) values(18,'预警推送','后台管理:预警推送:*','预警推送');

/*初始化admin账号*/
INSERT INTO auth_roles(role_id, role_name, description) VALUES(1, 'role_admin', '系统管理员角色，系统默认创建');
INSERT INTO auth_users(user_id, account, password, enabled, phoneNumber, id_card, real_name, organization_code, create_user_account)
VALUES(1, 'admin', 123, 1, 123, 123, 123, 440304004, 'admin');
INSERT INTO AUTH_USER_ROLES(user_id, role_id) VALUES(1,1);
--初始化admin角色权限
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,1);
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,2);
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,3);
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,4);
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,5);
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,6);
INSERT INTO AUTH_ROLE_PERMISSIONS(role_id, permission_id) VALUES(1,7);
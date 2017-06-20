create table at_autotask (
	id	number not null,
	code varchar2,
	name varchar2
);
comment on table at_autotask is '自动任务表';
comment on column at_autotask.id is '主键';
comment on column at_autotask.code is '自动任务编号';
comment on column at_autotask.name is '自动任务名称';

create table at_autotask_execute (
	id	number not null,
	autotaskid number not null,
	executetime varchar2
);
comment on table at_autotask_execute is '自动任务执行表';
comment on column at_autotask_execute.id is '主键';
comment on column at_autotask_execute.autotaskid is '自动任务id';
comment on column at_autotask_execute.executetime is '执行时间';
create table BANKAREACODE
(
  n_id           NUMBER not null,
  s_areacode     VARCHAR2(50) not null,
  s_areaprovince VARCHAR2(50) not null,
  s_areaname     VARCHAR2(50),
  s_areacity     VARCHAR2(50),
  s_arealevel    VARCHAR2(50),
  dt_inputtime   DATE,
  n_inputuserid  NUMBER(5) default -1,
  dt_modifytime  DATE,
  n_modifyuserid NUMBER(5) default -1
)
;
alter table BANKAREACODE
  add constraint PK_BANKAREACODE primary key (N_ID);

create table BANKNAMEANDCODE
(
  n_id           NUMBER(32) not null,
  s_bankcode     VARCHAR2(30),
  s_bankname     VARCHAR2(200),
  s_banktypecode VARCHAR2(50),
  s_areacode     VARCHAR2(50),
  s_remark       VARCHAR2(500)
)
;
comment on table BANKNAMEANDCODE
  is '行名行号表';
comment on column BANKNAMEANDCODE.n_id
  is '唯一标识';
comment on column BANKNAMEANDCODE.s_bankcode
  is '开户银行行号';
comment on column BANKNAMEANDCODE.s_bankname
  is '开户银行行名';
comment on column BANKNAMEANDCODE.s_banktypecode
  is '开户银行类别号';
comment on column BANKNAMEANDCODE.s_areacode
  is '开户银行地区编码';
comment on column BANKNAMEANDCODE.s_remark
  is '备注';
alter table BANKNAMEANDCODE
  add primary key (N_ID);


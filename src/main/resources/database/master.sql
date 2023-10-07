--liquibase formatted sql

--changeSet michael:developers.add runOnChange:true splitStatements:false stripComments:false
create role developers superuser nologin;

--changeSet michael:api_user.add runOnChange:true splitStatements:false stripComments:false
create role api_user login  password 'api_user';

--changeSet michael:schema.add runOnChange:true splitStatements:false stripComments:false
create schema if not exists test authorization developers;
grant usage on schema test to api_user;

--changeSet michael:test.entry_types.add runOnChange:true splitStatements:false stripComments:false
create table if not exists test.entry_types(
    type text not null,
    description text not null,
    constraint entry_types_pk primary key (type)
);
alter table test.entry_types owner to developers;
grant select,insert,update,delete on test.entry_types to api_user;

--changeSet michael:test.entries.add runOnChange:true splitStatements:false stripComments:false
create table if not exists test.entries(
    id uuid not null,
    entry_type text not null,
    account_d text not null,
    account_c text not null,
    sum decimal(16,2) not null,
    create_timestamp timestamp without time zone not null,
    timestamp timestamp without time zone not null,
    constraint entries_pk primary key (id),
    constraint entries__entry_types_fk foreign key (entry_type) references test.entry_types(type) on delete restrict
                                       on update  cascade
);
alter table test.entries owner to developers;
grant select,insert,update,delete on test.entries to api_user;



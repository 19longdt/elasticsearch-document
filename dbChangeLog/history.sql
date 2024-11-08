create table dbo.ew_user
(
  id         int identity
        constraint ew_user_pk
            primary key,
  username   varchar(100) not null,
  password   varchar(100) not null,
  full_name  nvarchar(100),
  status     bit          not null,
  is_manager bit          not null
)
  go



create table dbo.document
(
  id        int identity
        constraint document_pk
            primary key,
  author_id int not null,
  content   nvarchar(max) not null,
  parent_id int
)
  go

alter table dbo.document
  add type nvarchar(200)
go

alter table dbo.document
  add title nvarchar(500)
go

alter table dbo.document
  add ref_elastic_id varchar(100)
  go

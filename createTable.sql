-- Type表格
create table if not exists t_type(
    id int auto_increment primary key,
    name varchar(256)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- Tag表格
create table if not exists t_tag(
    id int auto_increment primary key,
    name varchar(256)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- Blog表格
create table if not exists t_blog(
    id int auto_increment,
    title varchar(256),
    content Longtext,
    first_picture varchar(256),
    flag varchar(256),
    views int ,
    appreciation boolean,
    share_statement boolean,
    commentabled boolean,
    published boolean,
    recommend boolean,
    create_time datetime,
    update_time datetime,
    description longtext,
    type_id int,
    user_id int,
    primary key (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- Comment表格
create table if not exists t_comment(
    id int auto_increment,
    nickname varchar(256),
    email varchar(256),
    content varchar(256),
    avatar varchar(256),
    create_time datetime,
    blog_id int,
    parent_comment int,
    admin_comment boolean,
    primary key (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- User表格
create table if not exists t_user(
    id int auto_increment,
    nickname varchar(256),
    username varchar(256),
    password varchar(256),
    email varchar(256),
    avatar varchar(256),
    type int,
    create_time datetime,
    update_time datetime,
    primary key (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- Blog和Tag的中间表
create table if not exists t_blog_tag(
    blog_id int,
    tag_id int,
    primary key (blog_id,tag_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
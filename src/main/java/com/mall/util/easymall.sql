
create table prod(
id int primary key auto_increment,##--主键
pname varchar(100),##--商品名称
price double,##--商品单价
cid int,##--商品种类的id
pnum int,##--商品的数量
imgurl varchar(100),##--图片在服务器上的url
description varchar(255)

);

create table prod_category(
id int primary key auto_increment, ##--主键
cname varchar(100) ##--商品种类
);

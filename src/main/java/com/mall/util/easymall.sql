
create table prod(
id int primary key auto_increment,##--����
pname varchar(100),##--��Ʒ����
price double,##--��Ʒ����
cid int,##--��Ʒ�����id
pnum int,##--��Ʒ������
imgurl varchar(100),##--ͼƬ�ڷ������ϵ�url
description varchar(255)

);

create table prod_category(
id int primary key auto_increment, ##--����
cname varchar(100) ##--��Ʒ����
);

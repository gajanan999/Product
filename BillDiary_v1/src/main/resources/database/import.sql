--create table if not exists user2(userid int,username varchar(20),password varchar(20),role varchar(20),primary key(userid));
--insert into user2 values(1,'Gajanan','Gajanan','admin');
--insert into user2 values(2,'Gajanan','Gajanan','admin');
create table if not exists user(id int, username varchar(20),password varchar(20),role varchar(20),primary key(id));
--insert into user values(1,'Gajanan','Gajanan','admin');
create table if not exists product(product_id int,name varchar(20),description varchar(20),wholesale_price double,retail_price double,discount double,stock int, primary key(product_id));

--insert into product values(101,'Mobile','Apple',30000,26500.50,0,5);
--insert into product values(102,'Camera','Sony',35000,29000.50,5,5);
--insert into product values(103,'Mobile','Samsung',20000,18000.50,0,5);
--insert into product values(104,'Laptop','Apple',65000,60000.50,0,5);
create table customer(Customer_ID int(5) primary key,
					  Name char(25) not null,
                      Address varchar(255) not null,
                      Email_ID varchar(255),
                      Username varchar(30) not null unique,
                      Password varchar(30) not null unique);

create table Telephone_number(Phone_Number varchar(13) not null,
			                  Customer_ID int(5) not null, 
							  foreign key(Customer_ID) references customer(Customer_ID));
                              
create table Coupon_code(Coupon_name varchar(7) not null,
			             Customer_ID int(5) not null, 
                         foreign key(Customer_ID) references customer(Customer_ID));

create table Category(Category_ID int(3) primary key,
					  Category_name char(25) not null unique);

create table Product(Product_ID int(5) primary key,
					 Category_ID int(3) not null,
                     Item_name char(25) not null,
                     Price float(7) not null,
                     Quantity int(3) not null,
                     Brand char(10),
                     Availability varchar(5)not null);

create table Reviews(Review varchar(255) not null,
			         Product_ID int(5) not null, 
					 foreign key(Product_ID) references Product(Product_ID));
                     
create table Cart(Retail_ID int(5) primary key,
				  Cart_ID int(5) not null,
				  Product_ID int(5) not null unique,
                  Customer_ID int(5) not null,
                  Item_name char(25) not null,
                  Quantity int(3) not null,
                  Total_price float(7) not null,
                  foreign key(Product_ID) references Product(Product_ID));
                  

create table Payment(Payment_ID int(10) primary key,
					 Cart_ID int(5) not null,
					 Amount float(7) not null,
                     Payment_method varchar(10),
                     Coupon_code varchar(7));
                     
                     
create table _Order_(Order_ID int (5) primary key,
					 Payment_ID int(10) not null unique,
                     Customer_ID int(5) not null,
                     Amount float(7) not null,
                     Order_Date DATE,
					 foreign key(Payment_ID) references Payment(Payment_ID));
            
create table Delivery(Delivery_ID int(5) primary key, 
					  Customer_ID int(5) not null,
                      Order_ID int(5) not null unique,
                      Order_Status char(20) not null,
                      Helpline int(13) not null,
                      foreign key(Order_ID) references _Order_(Order_ID));

create table Supplier(Supplier_ID int(5) primary key,
					  Supplier_name char(25) not null,
					  Address varchar(255) not null,
                      Rating int(5) not null);
                      
create table Order_history(Order_ID int(5) primary key,
						   Customer_ID int(5) not null unique,
						   Payment_ID int(10) not null unique,
                           Order_Date DATE not null,
                           foreign key(Customer_ID) references customer(Customer_ID),
                           foreign key(Payment_ID) references Payment(Payment_ID));

create table Return_order(Return_ID int(5) primary key,
                          Order_ID int(5) unique not null,
                          foreign key(Order_ID) references _Order_(Order_ID));

show databases;
use online_retail_store;
show tables;

insert into customer values (00001,"Priyanka","GTB nagar","priyanka@gmail.com","prigtb69","lesgo");
insert into customer values (00002,"Jai Kishan","Okhla 3","jaik@gmail.com","jk123@123","jaimatadi");
insert into customer values (00003,"Raj Put","South ex","rajasthani@gmail.com","richboi","haveli100");

insert into Telephone_number values (9234567890,00001);
insert into Telephone_number values (9234852890,00001);

insert into Telephone_number values (9578954124,00002);
insert into Telephone_number values (7831516489,00002);

insert into Telephone_number values (8524567463,00003);
insert into Telephone_number values (8945637158,00003);

Select * from Telephone_number where Customer_ID = '00003';

insert into Coupon_code values ("FREE25",00001);
insert into Coupon_code values ("FREE50",00001);
insert into Coupon_code values ("FREE50",00002);
insert into Coupon_code values ("FREE75",00003);

Select * from Coupon_code where Customer_ID = '00001';

insert into Category values (1,"Fruits & Vegetables");
insert into Category values (2,"Bakery");
insert into Category values (3,"Dairy");
insert into Category values (4,"Snacks");
insert into Category values (5,"Clothes");

select * from Category;

insert into Product values (00001,1,"Capscicum","120.45",500,null,'yes');
insert into Product values (00002,1,"Tomato","045.00",600,null,'yes');
insert into Product values (00003,1,"Apple","150.00",400,null,'yes');

insert into Product values (00004,2,"Bread","40",400,"behemoth",'yes');
insert into Product values (00005,2,"Cupcake","50",500,"Brittania",'yes');
insert into Product values (00006,2,"Pau","30",300,"Harvest",'yes');

insert into Product values (00007,3,"Milk","45",800,"Amul",'yes');
insert into Product values (00008,3,"Butter","50",500,"Amul",'yes');
insert into Product values (00009,3,"Cheese","90",300,"Amul",'yes');

insert into Product values (00010,4,"Cream & Onion","20",400,"Lays",'yes');
insert into Product values (00011,4,"Namkeen","30",700,"Bhikaji",'yes');
insert into Product values (00012,4,"Dark Fantasy","20",300,"Sunfeast",'yes');

insert into Product values (00013,5,"Jeans","750",100,"Pepe Jeans",'yes');
insert into Product values (00014,5,"T-shirt","500",200,"Pepe Jeans",'yes');
insert into Product values (00015,5,"Shorts","300",300,"Pepe Jeans",'yes');

insert into Reviews values ("Delivered fresh",00001);
insert into Reviews values ("Fresh & good",00001);
insert into Reviews values ("Ready sliced cheese easy to use in grilled sandwiches",00009);
insert into Reviews values ("Awesome fitting, just go for it without any 2nd thought",00005);

insert into Cart values (00001,00001,00002,00001,"Tomato",4,180.00);
insert into Cart values (00002,00001,00009,00001,"Cheese",1,90.00);
insert into Cart values (00003,00001,00011,00001,"Namkeen",2,60.00);

insert into Cart values (00004,00002,00013,00002,"Jeans",3,2250.00);
insert into Cart values (00005,00002,00010,00002,"Cream & Onion",5,100.00);

insert into Cart values (00006,00003,00007,00003,"Milk",2,90.00);
insert into Cart values (00007,00003,00008,00003,"Butter",1,50.00);

insert into Supplier values (00001,"Jaat Ram","Najafgarh",4);
insert into Supplier values (00002,"Gujjar mart","Noida",3);
insert into Supplier values (00003,"Narendra Yogi","Ghanta Ghar",1);

insert into Payment values (10001,1,"330","Debit card",null);
insert into Payment values (10002,2,"2300","Debit card","FREE50");
insert into Payment values (10003,3,"65","Debit card","FREE75");

insert into _Order_ values (00001,10001,00001,330,"2022-03-01");
insert into _Order_ values (00002,10002,00002,2300,"2022-03-01");
insert into _Order_ values (00003,10003,00003,65,"2022-03-01");

insert into Delivery values (00001,00001,00001,"Shipped",01124569871);
insert into Delivery values (00002,00002,00002,"Packed",01124569871);
insert into Delivery values (00003,00003,00003,"Delivered",01124574571);

insert into Order_history values (00001,00001,10001,"2022-03-01");
insert into Order_history values (00002,00002,10002,"2022-03-01");
insert into Order_history values (00003,00003,10003,"2022-03-01");

insert into Return_order values (00001,00001);

Select * from Product;
Select * from Reviews;
Select * from Return_order;
Select * from Order_history;
Select * from Delivery;
Select * from _Order_;
Select * from Payment;
Select * from Cart;
Select * from supplier;

Select Sum(Total_price) as Combined_Amount From Cart Where Customer_ID = '00001' or Customer_ID = '00002' ;
Select count(*) as Distinct_Products From (Select distinct Product_ID From Product P where P.Category_ID = '00003') As custom;
Select S.Supplier_ID,S.Supplier_name 
From Supplier S
Where S.Address = 'Noida' OR S.Supplier_ID IN (select s1.Supplier_ID from Supplier s1 where s1.Rating > 3);
Select P.Product_ID,P.Item_name from Product P where (P.Quantity > 300 or P.Price < 50.00) and P.Availability = 'yes';

Select O.Customer_ID 
From Order_history O
Where O.Order_ID IN (Select Order_ID 
					 From Return_order);

Select * from Reviews where Reviews.Product_ID IN (Select P.Product_ID From Product P Where P.Category_ID = '00001');

Select D.Order_ID, D.Order_Status
From Delivery D
Where D.Customer_ID IN (Select Customer_ID
						From Customer
                        Where Customer.Customer_ID = "00001");
                        
Select D.Customer_ID,D.Order_Status, D.Helpline
From Delivery D
Where D.Order_ID IN (Select Order_ID From _Order_ Where Amount > 2000);

Select P.Coupon_code, O1.Order_ID
From Payment P , _Order_ O1
Where P.Payment_ID = O1.Payment_ID and P.Payment_ID In (Select O.Payment_ID From _Order_ O Where O.Order_ID IN (Select Order_ID From Delivery Where Order_status = 'Packed' or Order_status = 'Delivered'));
show databases;
create database Big_Bazaar;
use Big_Bazaar;
show tables;

 create table Customer(Customer_ID int(5) primary key,
					  Name char(25) not null,
                      Address varchar(255) not null,
                      Email_ID varchar(255),
                      Username varchar(30) not null unique,
                      Password varchar(30) not null unique);

create table Telephone_number(Phone_Number varchar(13) not null,
			                  Customer_ID int(5) not null, 
							  foreign key(Customer_ID) references customer(Customer_ID));
                              
create table Category(Category_ID int(3) primary key,
					  Category_name char(25) not null unique);
                      
create table Product(Product_ID int(5) primary key,
                     Item_name char(25) not null,
                     Item_info JSON not null,
                     Quantity int(3) not null,
                     Reviews JSON);

create table Cart(Customer_ID int(5) primary key,
				  Item_info JSON,
                  Total_price float(7) not null); 


create table Payment(Payment_ID int(10) primary key,
					 Customer_ID int(5) not null,
					 Amount float(7) not null,
                     Payment_method varchar(10),
                     Coupon_code varchar(7));

create table _Order_(Order_ID int (5) primary key,
					 Payment_ID int(10) not null unique,
                     Customer_ID int(5) not null,
                     Amount float(7) not null,
                     Item_info JSON not null,
                     Order_Date DATE,
					 foreign key(Payment_ID) references Payment(Payment_ID));

create table Order_history(Order_ID int(5) primary key,
						   Customer_ID int(5) not null unique,
						   Payment_ID int(10) not null unique,
                           Order_Date DATE not null,
                           foreign key(Customer_ID) references customer(Customer_ID),
                           foreign key(Payment_ID) references Payment(Payment_ID));
                           
create table Return_order(Return_ID int(5) primary key,
                          Order_ID int(5) unique not null,
                          foreign key(Order_ID) references _Order_(Order_ID));
                          
create table Supplier(Supplier_ID int(5) primary key,
					  Supplier_name char(25) not null,
					  Address varchar(255) not null,
                      Rating int(5) not null);
                      
create table Delivery(Delivery_ID int(5) primary key, 
					  Customer_ID int(5) not null,
                      Order_ID int(5) not null unique,
                      Order_Status char(20) not null,
                      Helpline int(13) not null,
                      foreign key(Order_ID) references _Order_(Order_ID));
                     
create table Coupon_code(Coupon_name varchar(20) not null, 
			             Coupon_discount int(5) not null);

insert into Coupon_code values ("GETFLAT10",10);
insert into Coupon_code values ("LOYAL100",15);
insert into Coupon_code values ("GETDIS20",20);


insert into Category values (1,"Fruits & Vegetables");
insert into Category values (2,"Bakery");
insert into Category values (3,"Dairy");
insert into Category values (4,"Snacks");
insert into Category values (5,"Clothes");
insert into Category values (6,"Electronics");

insert into Product values (00001,"Capscicum",'{"Category_ID": "1", "Price": "120.45", "Availability": "Yes"}',500,null);
insert into Product values (00002,"Tomato",'{"Category_ID": "1", "Price": "045.00", "Availability": "Yes"}',600,null);
insert into Product values (00003,"Apple",'{"Category_ID": "1", "Price": "150.00", "Availability": "Yes"}',400,null);
insert into Product values (00004,"Onion",'{"Category_ID": "1", "Price": "25.00", "Availability": "Yes"}',800,null);
insert into Product values (00005,"Lemon",'{"Category_ID": "1", "Price": "250.00", "Availability": "Yes"}',300,null);

insert into Product values (00006,"Bread",'{"Category_ID": "2", "Price": "40", "Brand": "behemoth", "Availability": "Yes"}',400,null);
insert into Product values (00007,"Cupcake",'{"Category_ID": "2", "Price": "50", "Brand": "Brittania", "Availability": "Yes"}',500,null);
insert into Product values (00008,"Pao",'{"Category_ID": "2", "Price": "30", "Brand": "Harvest", "Availability": "Yes"}',300,null);

insert into Product values (00009,"Milk",'{"Category_ID": "3", "Price": "45", "Brand": "Amul", "Availability": "Yes"}',800,null);
insert into Product values (00010,"Butter",'{"Category_ID": "3", "Price": "50", "Brand": "Amul", "Availability": "Yes"}',500,null);
insert into Product values (00011,"Cheese",'{"Category_ID": "3", "Price": "90", "Brand": "Amul", "Availability": "Yes"}',400,null);

insert into Product values (00012,"Cream & Onion",'{"Category_ID": "4", "Price": "20", "Brand": "Lays", "Availability": "Yes"}',400,null);
insert into Product values (00013,"Namkeen",'{"Category_ID": "4", "Price": "30", "Brand": "Bhikaji", "Availability": "Yes"}',700,null);
insert into Product values (00014,"Dark Fantasy",'{"Category_ID": "4", "Price": "20", "Brand": "Sunfeast", "Availability": "Yes"}',300,null);

insert into Product values (00015,"Denim Jeans", '{"Category_ID": "5", "Price": "1399", "Brand": "Levis", "Availability": "Yes"}',100,null);
insert into Product values (00016,"T-shirt", '{"Category_ID": "5", "Price": "599", "Brand": "USPA", "Availability": "Yes"}',200,null);
insert into Product values (00017,"Shorts", '{"Category_ID": "5", "Price": "399", "Brand": "Puma", "Availability": "Yes"}',300,null);

insert into Product values (00018,"Ipad Air", '{"Category_ID": "6", "Price": "54999", "Brand": "Apple", "Availability": "Yes"}',20,null);
insert into Product values (00019,"Fitness tracker", '{"Category_ID": "6", "Price": "1999", "Brand": "Fitbit", "Availability": "Yes"}',250,null);
insert into Product values (00020,"Smartphone", '{"Category_ID": "6", "Price": "15999", "Brand": "Mi", "Availability": "Yes"}',100,null);

insert into Supplier values (00001,"Jaat Ram","Najafgarh",4);
insert into Supplier values (00002,"Gujjar mart","Noida",3);
insert into Supplier values (00003,"Narendra Yogi","Ghanta Ghar",1);


Select * from category;
Select * from product;
Select * from supplier;


insert into customer values (00001,"Priyanka","GTB nagar","priyanka@gmail.com","prigtb69","lesgo");
insert into customer values (00002,"Jai Kishan","Okhla 3","jaik@gmail.com","jk123@123","jaimatadi");
insert into customer values (00003,"Raj Put","South ex","rajasthani@gmail.com","richboi","haveli100");

insert into Telephone_number values (9234567890,00001);
insert into Telephone_number values (9234852890,00001);
insert into Telephone_number values (9578954124,00002);
insert into Telephone_number values (7831516489,00002);
insert into Telephone_number values (8524567463,00003);
insert into Telephone_number values (8945637158,00003);


create index Item_names on Product(Item_name);
create index suppliers on Supplier(Supplier_name);
create index Stock on Product(Item_name, Quantity);
create index C_orders on _Order_(Order_ID, Customer_ID);
create index Delivery_updates on Delivery(Order_ID, Order_Status);

Select Product_ID, Quantity, Item_name,
RANK () OVER ( Order BY Quantity DESC ) As Index_no
From Product;

Select Coupon_name, Coupon_Discount,
RANK () OVER ( Order BY Coupon_Discount Desc) As Index_no
From Coupon_code;

grant Select on Product TO 'S1'; 
grant Select on Product TO 'S2'; 
grant Select on Product TO 'S3'; 

grant Select on consumer_details TO 'S1'; 
grant Select on consumer_details TO 'S2'; 
grant Select on consumer_details TO 'S3'; 

grant Select on order_dates TO 'S1'; 
grant Select on order_dates TO 'S2'; 
grant Select on order_dates TO 'S3'; 


#1 Customers who have availed coupon code for their purchase
Select Customer_ID, Name 
from Customer 
where Customer_Id IN  
(Select  C.Customer_ID  
 from  Payment P, Cart C 
 WHERE P.Coupon_code IS NOT NULL AND P.Customer_ID = C.Customer_ID);
 
 #2 Customers who have placed orders uptil now.
 Select C.Customer_ID, Name, Order_Date
 From Customer C
	INNER JOIN _Order_ O
		On C.Customer_ID = O.Customer_ID; 

#3 Create a view where login credentials of customers are not required
create view consumer_details as 
	select Customer_ID, Name, Address, Email_ID
    from customer;

#4 Create a view where the customer’s ID and the date the customer placed order is visible,
# and the view should not create those order’s which were returned.
create view order_dates as
	select Customer_ID, Order_Date
    from _Order_ O1, Return_order O2
    where O1.Order_ID != O2.Order_ID;
    
#select * from consumer_details;
#select * from order_dates;
        
#5 Return count details of orders returned by customer
Select  O.Customer_ID ,count(O.Order_ID) as Count_
from Order_history O, Return_order R  
where O.Order_ID = R.Return_ID 
GROUP BY(O.Customer_ID);

#6 ID’s and name of suppliers who have 2+ rating AND are located at Najafgarh.
Select S.Supplier_ID,S.Supplier_name
From Supplier S
Where S.Address = 'Najafgarh' AND S.Supplier_ID IN (Select s1.Supplier_ID
													From Supplier s1 Where s1.Rating > 2);

#7 Name of the coupon code used for the orders whose status is either packed or delivered. 
Select P.Coupon_code, O1.Order_ID, C.Customer_ID 
From Payment P , _Order_ O1, Customer C 
Where P.Payment_ID = O1.Payment_ID and P.Payment_ID In 
	(Select O.Payment_ID 
	 From _Order_ O 
	 Where O.Order_ID IN 
			(Select Order_ID 
			 From Delivery 	
			 Where Order_status = 'Packed' or Order_status = 'Delivered') AND O.Order_ID = C.Customer_ID);

#8 Name and price of those items present in Category having ID 3 or 5.
Select json_value(Item_info,'$.Price') as Price_, Item_name
from Product P 
where json_value(Item_info,'$.Category_ID') = 3 OR  json_value(Item_info,'$.Category_ID') = 5 ;

#9 Rank the orders which were not returned with respect to their amounts and their payment method was cash.
Select O.Order_ID, O.Item_Info, O.Amount,
Rank() OVER (Order BY O.Amount Desc ) As Index_no
From _Order_ O
	INNER JOIN Payment P
		On P.Payment_ID = O.Payment_ID
    Where P.Payment_method = 'Cash' ; 

 
#10 List the item details from category with ID 1 having price greater than the maximum price form category with ID 3
Select P.Product_ID, P.Item_name 
From Product P
Where json_value(P.Item_info,'$.Category_ID') = 1 AND 
	  json_value(P.Item_info,'$.Price') > (Select max(json_value(P2.Item_info,'$.Price'))
										   From Product P2
										   Where json_value(P2.Item_info,'$.Category_ID') = 3);

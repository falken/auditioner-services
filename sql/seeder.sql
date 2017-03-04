delete from Production;
insert into Production (name, audition_date, season) values ('Nutcracker','2017-09-01','Winter 2017');
insert into Production (name, audition_date, season) values ('Dracula','2018-03-18','Summer 2018');

delete from Family;
insert into Family (Name) values ('Hansons');
insert into Family (Name) values ('Smiths');
insert into Family (Name) values ('Jones');

delete from FamilyMember;
insert into FamilyMember (family_id,first_name,last_name,weight,height,past_roles) values (1,'Sammy','Hanson','90','4\' 5"',null);
insert into FamilyMember (family_id,first_name,last_name,weight,height,past_roles) values (1,'Sally','Hanson','92','4\' 5"',null);

insert into FamilyMember (family_id,first_name,last_name,weight,height,past_roles) values (2,'Lisa','Smith Jr.','70','4\' 1"',null);
insert into FamilyMember (family_id,first_name,last_name,weight,height,past_roles) values (2,'Tammy','Smith','101','5\' 0"',null);

insert into FamilyMember (family_id,first_name,last_name,weight,height,past_roles) values (3,'Mary','Jones','112','5\' 2"',null);

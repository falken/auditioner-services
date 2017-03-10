delete from Family;
insert into Family (Name) values ('Hansons');
insert into Family (Name) values ('Smiths');
insert into Family (Name) values ('Jones');

delete from FamilyMember;
insert into FamilyMember
set family_id = (select Family.id from Family where Family.name = 'Smiths'),
    first_name = 'Tammy',
    last_name = 'Smith',
    birth_date = '1956/10/01',
    weight = '90',
    height = '4\'4"';

insert into FamilyMember
set family_id = (select Family.id from Family where Family.name = 'Smiths'),
  first_name = 'Lisa',
  last_name = 'Smith',
  birth_date = '1956/10/01',
  weight = '105',
  height = '4\'8"';

insert into FamilyMember
set family_id = (select Family.id from Family where Family.name = 'Hansons'),
  first_name = 'Jim',
  last_name = 'Hanson',
  birth_date = '1956/10/01',
  weight = '115',
  height = '5\'0"';

insert into FamilyMember
set family_id = (select Family.id from Family where Family.name = 'Hansons'),
  first_name = 'Sally',
  last_name = 'Hanson',
  birth_date = '1956/10/01',
  weight = '120',
  height = '6\'0"';

insert into FamilyMember
set family_id = (select Family.id from Family where Family.name = 'Jones'),
  first_name = 'Mary',
  last_name = 'Jones',
  birth_date = '1956/10/01',
  weight = '112',
  height = '5\'2"';

delete from Production;
insert into Production (name, audition_date, season) values ('Nutcracker','2017-09-01','Winter 2017');
insert into Production (name, audition_date, season) values ('Dracula','2018-03-18','Summer 2018');

delete from ProductionMember;
insert into ProductionMember
  set family_member_id = (select FamilyMember.id from FamilyMember where FamilyMember.first_name = 'Tammy'),
      production_id = (select Production.id from Production where Production.name = 'Nutcracker'),
      requested_roles = 'role1',
      rehearsal_conflicts = 'all the conflicts',
      audition_number = '1234';

insert into ProductionMember
set family_member_id = (select FamilyMember.id from FamilyMember where FamilyMember.first_name = 'Jim'),
  production_id = (select Production.id from Production where Production.name = 'Nutcracker'),
  requested_roles = 'role2',
  rehearsal_conflicts = 'none the conflicts',
  audition_number = '5676';

insert into ProductionMember
set family_member_id = (select FamilyMember.id from FamilyMember where FamilyMember.first_name = 'Sally'),
  production_id = (select Production.id from Production where Production.name = 'Dracula'),
  requested_roles = 'role3',
  rehearsal_conflicts = 'all the conflicts',
  audition_number = '5677';

insert into ProductionMember
set family_member_id = (select FamilyMember.id from FamilyMember where FamilyMember.first_name = 'Mary'),
  production_id = (select Production.id from Production where Production.name = 'Dracula'),
  requested_roles = 'role4',
  rehearsal_conflicts = 'none the conflicts',
  audition_number = '5678';

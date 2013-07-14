create sequence emp_id_seq start with 1;

create table employee(id integer, first_name varchar(25), last_name varchar(25), hire_date date,  salary decimal, primary key(id));
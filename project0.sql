drop schema if exists project0 cascade;
create schema project0;
set schema 'project0';

create table employees (
	employee_id serial primary key,
	first_name text not null,
	last_name text not null,
	username text unique not null,
	"password" text not null
);

create table customers (
	customer_id serial primary key,
	first_name text not null,
	last_name text not null,
	username text unique not null,
	"password" text not null
);

create table applications (
	application_id serial primary key,
	username text unique not null references customers (username),
	starting_balance int not null check (starting_balance >= 0),
	credit_score int not null,
	yearly_salary int,
	application_status text default 'pending'
);

create table accounts (
	account_number serial primary key,
	username text unique not null references customers (username),
	account_balance int not null
);

ALTER SEQUENCE accounts_account_number_seq RESTART WITH 123000;

create table transactions (
	transaction_id serial,
	account_number int references accounts (account_number),
	t_type text not null,
	t_amount int not null
);

create table transfers (
	from_account_number int references accounts (account_number),
	amount int not null,
	to_account_number int,
	status text default 'pending'
);
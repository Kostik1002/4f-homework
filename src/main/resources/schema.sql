drop table clients if exists
drop table loans if exists
drop table loan_details if exists
drop table loan_requests if exists

create table clients (id bigint auto_increment not null, name varchar(255) not null, phone varchar(255) not null, surname varchar(255) not null, status varchar(255) not null, primary key (id))
create table loans (id bigint auto_increment not null, amount decimal(19,2) not null, client_id bigint not null, date timestamp not null, status varchar(255) not null, primary key (id))
create table loan_details (id bigint auto_increment not null, date timestamp not null, loan_id bigint, term timestamp not null, status varchar(255) not null, primary key (id))
create table loan_requests (id bigint auto_increment not null, client_id bigint not null, date timestamp not null, ip varchar(255) not null, primary key (id))

alter table clients add constraint UK_client_phone unique (phone)
alter table loans add constraint FK_loan_client foreign key (client_id) references clients
alter table loan_details add constraint FK_loan_details foreign key (loan_id) references loans
alter table loan_requests add constraint FK_loan_request_client foreign key (client_id) references clients

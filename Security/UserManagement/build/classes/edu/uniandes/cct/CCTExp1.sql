--Create database
create database service_desk;
use service_desk;
--Create user
CREATE USER sqluser IDENTIFIED BY 'sqluserpw';
--Set user permissions
grant usage on *.* to sqluser@localhost identified by 'sqluserpw';
grant all privileges on service_desk.* to sqluser@localhost;
--Create table
CREATE TABLE service_request (
                id INT NOT NULL AUTO_INCREMENT,
                TICKET_NUMBER VARCHAR(300) NOT NULL,
                DESCRIPTION NVARCHAR(4000),
                PRIMARY KEY (ID)
        );
--Insert test value
INSERT INTO service_request values (default, '20160410132200000', 'Test Case');
--See table values
SELECT * FROM service_desk.service_request;
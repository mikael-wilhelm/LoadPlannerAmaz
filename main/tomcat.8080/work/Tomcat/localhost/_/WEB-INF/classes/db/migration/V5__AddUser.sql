CREATE TABLE loadUsers(
  userName varchar(40) PRIMARY KEY,
  password varchar(40) NOT NULL);
ALTER TABLE loads ADD COLUMN reservedBy varchar(40) references loadUsers(userName);
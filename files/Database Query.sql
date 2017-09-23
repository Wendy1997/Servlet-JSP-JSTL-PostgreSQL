// create db
CREATE DATABASE BlibliMovies

// create table + column 
CREATE TABLE StoreAccount
(
  Username VARCHAR(20) PRIMARY KEY,
  Password VARCHAR(20),
  StoreName VARCHAR(50)
);


CREATE TABLE Account
(
  Username VARCHAR(20) PRIMARY KEY,
  StoreUsername VARCHAR(20) REFERENCES StoreAccount(Username),
  Password VARCHAR(20),
  Type VARCHAR(10)
);

INSERT StoreAccount VALUES ('blibli', 'lalala', 'GLOBAL DIGITAL NIAGA');

INSERT Account VALUES ('ndi', 'blibli', 'bear', 'admin');
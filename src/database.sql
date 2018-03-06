CREATE DATABASE hutubill;
CREATE TABLE config(
  id INT AUTO_INCREMENT,
  key_ VARCHAR(255),
  value VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE category (
  id INT AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE record (
  id INT AUTO_INCREMENT,
  spend INT,
  cid INT,
  comment VARCHAR(255),
  date DATE,
  PRIMARY KEY (id),
  CONSTRAINT fk_record_category FOREIGN KEY (cid) REFERENCES category (id)
);
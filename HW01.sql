CREATE TABLE customer (
    tel varchar(11) not null,
    deposit int not null
);
CREATE TABLE movie (
    isbn varchar(13) not null,
    title varchar(50) not null,
    genre varchar(20) not null,
    price int not null
);
CREATE TABLE item (
    itemId int not null,
    ranted int not null,
    isbn int not null
);
CREATE TABLE rent_history (
    rentHistoryId int not null,
    lateFee int not null,
    rentDate datetime,
    returnDate datetime,
    itemID int not null
);
CREATE TABLE rentStatus (
    itemID int not null,
    customerID int not null,
    dueDate datetime,
    price int
);
CREATE TABLE reservation (
    movieID int not null,
    customerID int not null,
    reservationDate datetime
);

--PRIMARY KEY
ALTER TABLE customer ADD CONSTRAINT PK_customer_tel
PRIMARY KEY(tel);

ALTER TABLE movie ADD CONSTRAINT PK_movie_isbn
PRIMARY KEY(isbn);

ALTER TABLE item ADD CONSTRAINT PK_item_itemId
PRIMARY KEY(itemId);

ALTER TABLE rent_history ADD CONSTRAINT PK_rent_history_rentHistoryId
PRIMARY KEY(rentHistoryId);

ALTER TABLE rentStatus ADD CONSTRAINT PK_rentStatus_itemId_customerId
PRIMARY KEY(itemId,customerID);

ALTER TABLE reservation ADD CONSTRAINT PK_reservation_movieId_customerId
PRIMARY KEY(movieId,customerID);

-- FOREIGN KEY 
ALTER TABLE item ADD CONSTRAINT FK_item_isbn 
FOREIGN KEY(isbn) REFERENCES movie(isbn);

ALTER TABLE rent_history ADD CONSTRAINT FK_rent_history_itemId
FOREIGN KEY(itemId) REFERENCES item(itemId);
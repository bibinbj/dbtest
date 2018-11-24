# NoSQL database performance test

This project is a simple comparison of cassandra vs mongodb vs couchbasse database with read and writes

# Cassandra setup and query:
CREATE KEYSPACE IF NOT EXISTS reservation WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

CREATE TABLE IF NOT EXISTS  reservation.reservation (
    id int,
    orderId varchar,
    deliverId int,
    fullLoc int,
    creationDate text,
	address text,
    primary key((id))
);

select count(*) from reservation;

truncate table reservation;

# Couchbase setup and query:
CREATE PRIMARY INDEX `reservation-primary-index` ON `reservation` USING GSI;

# MongoDB setup and query

use reservation;

db.reservation.find().count();

db.reservation.remove({});


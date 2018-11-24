package com.code.dbtest.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoReservationRepository extends MongoRepository<Reservation, Long> {


}

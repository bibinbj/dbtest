package com.code.dbtest.couchbase;

import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

public interface CouchbaseReservationRepository extends CouchbasePagingAndSortingRepository<BReservation, Long>{

}

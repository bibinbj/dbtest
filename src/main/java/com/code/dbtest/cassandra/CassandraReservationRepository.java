package com.code.dbtest.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassandraReservationRepository extends CassandraRepository<CReservation> {


}

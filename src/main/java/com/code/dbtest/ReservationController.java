package com.code.dbtest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.code.dbtest.cassandra.CReservation;
import com.code.dbtest.cassandra.CassandraReservationRepository;
import com.code.dbtest.couchbase.BReservation;
import com.code.dbtest.couchbase.CouchbaseReservationRepository;
import com.code.dbtest.mongo.MongoReservationRepository;
import com.code.dbtest.mongo.Reservation;

@RestController
@RequestMapping(value = "/")
public class ReservationController {
	
	private final MongoReservationRepository mongoReservationRepository;
	private final CassandraReservationRepository cassandraReservationRepository;
	private final CouchbaseReservationRepository couchbaseReservationRepository;
	private final BatchReservation batchReservation;	

	public ReservationController(MongoReservationRepository mongoReservationRepository,CassandraReservationRepository cassandraReservationRepository,
			CouchbaseReservationRepository couchbaseReservationRepository,BatchReservation batchReservation) {
		this.mongoReservationRepository = mongoReservationRepository;
		this.cassandraReservationRepository = cassandraReservationRepository;
		this.couchbaseReservationRepository = couchbaseReservationRepository;
		this.batchReservation = batchReservation;
	}
	
	
	@RequestMapping(value = "/mongodb", method = RequestMethod.GET)
	public List<Reservation> getMAllUsers() {
		return mongoReservationRepository.findAll();
	}
	
	@RequestMapping(value = "/mongodb/create", method = RequestMethod.POST)
	public Reservation addMNewUsers(@RequestBody Reservation reservation) {
		return mongoReservationRepository.save(reservation);
	}
	
	@RequestMapping(value = "/mongodb/{id}", method = RequestMethod.GET)
	public Reservation getMUser(@PathVariable long id) {
		return mongoReservationRepository.findOne(id);
	}
	
	@RequestMapping(value = "/cassandra", method = RequestMethod.GET)
	public Iterable<CReservation> getAllUsers() {
		return cassandraReservationRepository.findAll();
	}
	
	@RequestMapping(value = "/cassandra/create", method = RequestMethod.POST)
	public CReservation addNewUsers(@RequestBody CReservation cReservation) {
		return cassandraReservationRepository.save(cReservation);
	}
	
	@RequestMapping(value = "/cassandra/{id}", method = RequestMethod.GET)
	public CReservation getUser(@PathVariable long id) {
		BasicMapId basicMapId = new BasicMapId();
		basicMapId.put("id", id);
		return cassandraReservationRepository.findOne(basicMapId);
	}
	
	@RequestMapping(value = "/couchbase", method = RequestMethod.GET)
	public Iterable<BReservation> getAllUsersB() {
		return couchbaseReservationRepository.findAll();
	}
	
	@RequestMapping(value = "/couchbase/create", method = RequestMethod.POST)
	public BReservation addNewUsersB(@RequestBody BReservation reservation) {
		return couchbaseReservationRepository.save(reservation);
	}
	
	@RequestMapping(value = "/couchbase/{id}", method = RequestMethod.GET)
	public BReservation getUserB(@PathVariable long id) {
		return couchbaseReservationRepository.findOne(id);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Response> test() {
		List<Response> resp = new ArrayList<Response>();
		resp.add(batchReservation.testCassandra());
		resp.add(batchReservation.testMongodb());
		resp.add(batchReservation.testCouchbase());
		return resp;
	}
}

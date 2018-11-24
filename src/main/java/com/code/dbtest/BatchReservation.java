package com.code.dbtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.stereotype.Component;

import com.code.dbtest.cassandra.CReservation;
import com.code.dbtest.cassandra.CassandraReservationRepository;
import com.code.dbtest.couchbase.BReservation;
import com.code.dbtest.couchbase.CouchbaseReservationRepository;
import com.code.dbtest.mongo.MongoReservationRepository;
import com.code.dbtest.mongo.Reservation;

@Component
public class BatchReservation {
	private final MongoReservationRepository mongoReservationRepository;
	private final CassandraReservationRepository cassandraReservationRepository;
	private final CouchbaseReservationRepository couchbaseReservationRepository;
	private static final int COUNT = 10000;

	public BatchReservation(MongoReservationRepository mongoReservationRepository,CassandraReservationRepository cassandraReservationRepository,
			CouchbaseReservationRepository couchbaseReservationRepository) {
		this.mongoReservationRepository = mongoReservationRepository;
		this.cassandraReservationRepository = cassandraReservationRepository;
		this.couchbaseReservationRepository = couchbaseReservationRepository;
		
	}
	
	
	public Response testCassandra() {
		Response response = new Response();
		List<Long> allRespTime = new ArrayList<Long>();
		long avgResponse = 0;
		for(int i=0;i<=COUNT;i++) {
			long start = System.currentTimeMillis();
			CReservation cReservation = getCReservation(i);
			cassandraReservationRepository.save(cReservation);
			BasicMapId basicMapId = new BasicMapId();
			basicMapId.put("id", i);
			CReservation cReservation2 = cassandraReservationRepository.findOne(basicMapId);
			if(cReservation2.getId() != cReservation.getId()) {
				System.out.println("Failed -----> "+ cReservation2.getId());
			}
			long total = System.currentTimeMillis()-start;
			allRespTime.add(total);
			avgResponse = avgResponse + total;
		}
		avgResponse = avgResponse/COUNT;
		response.setDbType("Cassandra");
		response.setAvgRespTime(avgResponse);
		response.setAllRespTime(allRespTime);
		return response;
	}
	
	public Response testMongodb() {
		Response response = new Response();
		List<Long> allRespTime = new ArrayList<Long>();
		long avgResponse = 0;
		for(int i=0;i<=COUNT;i++) {
			long start = System.currentTimeMillis();
			Reservation reservation = getReservation(i);
			mongoReservationRepository.save(reservation);
			Reservation reservation2 = mongoReservationRepository.findOne((long) i);
			if(reservation2.getId() != reservation.getId()) {
				System.out.println("Failed -----> "+ reservation2.getId());
			}			
			long total = System.currentTimeMillis()-start;
			allRespTime.add(total);
			avgResponse = avgResponse + total;
		}
		avgResponse = avgResponse/COUNT;
		response.setDbType("MongoDB");
		response.setAvgRespTime(avgResponse);
		response.setAllRespTime(allRespTime);
		return response;
	}
	
	public Response testCouchbase() {
		Response response = new Response();
		List<Long> allRespTime = new ArrayList<Long>();
		long avgResponse = 0;
		for(int i=0;i<=COUNT;i++) {
			long start = System.currentTimeMillis();
			BReservation reservation = getBReservation(i);
			couchbaseReservationRepository.save(reservation);
			BReservation reservation2 = couchbaseReservationRepository.findOne(new Long(i));
			if(reservation2.getId() != reservation.getId()) {
				System.out.println("Failed -----> "+ reservation2.getId());
			}			
			long total = System.currentTimeMillis()-start;
			allRespTime.add(total);
			avgResponse = avgResponse + total;
		}
		avgResponse = avgResponse/COUNT;
		response.setDbType("Couchbase");
		response.setAvgRespTime(avgResponse);
		response.setAllRespTime(allRespTime);
		return response;
	}
	
	private CReservation getCReservation(long id) {
		CReservation cReservation = new CReservation();
		cReservation.setId(id);
		cReservation.setCreationDate("1516165830856");
		cReservation.setAddress("1223 sfsf 123123 asd");
		cReservation.setDeliverId(1233+id);
		cReservation.setFullLoc(423+id);
		cReservation.setOrderId("asd"+id);
		return cReservation;
	}
	
	private Reservation getReservation(long id) {
		Reservation reservation = new Reservation();
		reservation.setId(id);
		reservation.setCreationDate(new Date());
		reservation.setAddress("1223 sfsf 123123 asd");
		reservation.setDeliverId(1233+id);
		reservation.setFullLoc(423+id);
		reservation.setOrderId("asd"+id);
		return reservation;
	}
	
	private BReservation getBReservation(long id) {
		BReservation reservation = new BReservation();
		reservation.setId(id);
		reservation.setCreationDate("1231231");
		reservation.setAddress("1223 sfsf 123123 asd");
		reservation.setDeliverId(1233+id);
		reservation.setFullLoc(423+id);
		reservation.setOrderId("asd"+id);
		return reservation;
	}
	
}

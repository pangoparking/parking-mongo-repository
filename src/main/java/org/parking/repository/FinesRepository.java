package org.parking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.parking.FineDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FinesRepository extends MongoRepository<FineDoc, Long> {

	
	List<FineDoc> findAllByOwnerID(long ownerID);

	boolean existsByOwnerID(long ownerID);
	
	@Query(value = "{$and:[{ownerID:?0}, {dateTime:{$gte:?1, $lte:?2}}]}")
	List<FineDoc> findAllFinesByOwnerIDAndDateTimeInterval(long ownerID, LocalDateTime from, LocalDateTime to);

}

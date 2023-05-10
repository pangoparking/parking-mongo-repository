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
	List<FineDoc> findAllByOwnerIDAndDateTimeInterval(long ownerID, LocalDateTime from, LocalDateTime to);

	List<FineDoc> findAllByCarID(long carID);

	boolean existsByCarID(long carID);
	
	@Query(value = "{$and:[{carID:?0}, {dateTime:{$gte:?1, $lte:?2}}]}")
	List<FineDoc> findAllByCarIDAndDateTimeInterval(long carID, LocalDateTime from, LocalDateTime to);

	List<FineDoc> findAllByStatus(String status);
	
	@Query(value = "{$and:[{status:?0}, {dateTime:{$gte:?1, $lte:?2}}]}")
	List<FineDoc> findAllByStatusAndDateTimeInterval(String status, LocalDateTime from, LocalDateTime to);


}

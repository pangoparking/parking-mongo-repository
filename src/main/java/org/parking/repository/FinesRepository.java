package org.parking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.parking.FineDoc;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.parking.model.MonthlyFineCount;

public interface FinesRepository extends MongoRepository<FineDoc, Long> {

	@Query(value = "{ ?0 : ?1 }")
	List<FineDoc> existsByKeyAndID(String key, long id, Pageable pageable);
	
	boolean existsByOwnerID(long id);
	
	boolean existsByCarID(long id);
	
	@Query(value = "{ ?0 : ?1 }")
	List<FineDoc> findAllByKeyAndID(String key, long id);
	
	@Query(value = "{$and:[{?0:?1}, {dateTime:{$gte:?2, $lte:?3}}]}")
	List<FineDoc> findAllByKeyAndIDAndDateTimeInterval(String key, long id, LocalDateTime from, LocalDateTime to);

	List<FineDoc> findAllByStatus(String status);
	
	@Query(value = "{$and:[{status:?0}, {dateTime:{$gte:?1, $lte:?2}}]}")
	List<FineDoc> findAllByStatusAndDateTimeInterval(String status, LocalDateTime from, LocalDateTime to);

	@Query(value = "{dateTime:{$gte:?0, $lte:?1}}")
	List<FineDoc> findAllByDateTimeInterval(LocalDateTime from, LocalDateTime to);
	
	@Aggregation(pipeline = {
			"{ $match: { dateTime: { $gte: ?0, $lt: ?1 }}}",
			"{ $group: { _id: { $month: '$dateTime' }, count: { $sum: 1 }}}",
			"{ $project: { month: '$_id', count: 1, _id: 0}}",
			"{ $sort: { _id: 1 }}"
	})
	List<MonthlyFineCount> findAllByYear(LocalDateTime from, LocalDateTime to);

	
}

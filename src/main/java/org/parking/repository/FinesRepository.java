package org.parking.repository;

import org.parking.FineDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinesRepository extends MongoRepository<FineDoc, Long> {

}

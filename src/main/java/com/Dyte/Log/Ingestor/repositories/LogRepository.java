package com.Dyte.Log.Ingestor.repositories;

import com.Dyte.Log.Ingestor.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log,String> {
}

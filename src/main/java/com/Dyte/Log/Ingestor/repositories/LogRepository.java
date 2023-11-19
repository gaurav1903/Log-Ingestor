package com.Dyte.Log.Ingestor.repositories;

import com.Dyte.Log.Ingestor.models.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends MongoRepository<Log,String>
{

}

package com.Dyte.Log.Ingestor.schedulers;

import com.Dyte.Log.Ingestor.models.Log;
import com.Dyte.Log.Ingestor.models.LogData;
import com.mongodb.bulk.BulkWriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogIngesionScheduler {
    @Autowired
    MongoTemplate mongoTemplate;

    @Scheduled(fixedDelay = 6000)
    void flushtoDB()
    {
        List<Log> logList= LogData.logList;
        if(!logList.isEmpty())
        {
            try {
                final BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Log.class);
                final BulkWriteResult execute = bulkOperations.insert(logList).execute();
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
            logList.clear();
        }
    }
}

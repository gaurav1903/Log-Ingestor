package com.Dyte.Log.Ingestor.schedulers;

import com.Dyte.Log.Ingestor.models.Log;
import com.Dyte.Log.Ingestor.models.LogData;
import com.mongodb.bulk.BulkWriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogIngesionScheduler {
    @Autowired
    MongoTemplate mongoTemplate;

    @Scheduled(fixedDelay = 60000)

    public void flushtoDB()
    {
        //issue with concurrency
        List<Log> origLogList= LogData.logList;
        List<Log>copyLogList=new ArrayList<Log>();
        for(Log log: origLogList)
        {
            copyLogList.add(log);
        }
        if(!copyLogList.isEmpty())
        {
            try {
                final BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Log.class);
                final BulkWriteResult execute = bulkOperations.insert(copyLogList).execute();
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }
            LogData.logList=LogData.logList.subList(copyLogList.size(),origLogList.size());
        }
    }
}

package com.Dyte.Log.Ingestor.service.impl;

import com.Dyte.Log.Ingestor.models.LogData;
import com.Dyte.Log.Ingestor.models.Log;
import com.Dyte.Log.Ingestor.models.LogFilter;
import com.Dyte.Log.Ingestor.service.LogService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class logServiceImpl implements LogService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addLog(Log log) {
        List<Log> logList=LogData.logList;
        logList.add(log);
    }

    @Override
    public List<Log> getLogs(LogFilter logFilters) {
        //verify logFilters
        if(isVerified(logFilters)==false)
            return new ArrayList<>();
        else
        {
            final List<Criteria> criteria=new ArrayList<Criteria>();
            if(!StringUtils.isEmpty(logFilters.getSpanId()))
                criteria.add(Criteria.where("spanId").is(logFilters.getSpanId()));
            if(!StringUtils.isEmpty(logFilters.getTraceId()))
                criteria.add(Criteria.where("traceId").is(logFilters.getTraceId()));
            if(!StringUtils.isEmpty(logFilters.getResourceId()))
                criteria.add(Criteria.where("resourceId").is(logFilters.getResourceId()));

            if(!StringUtils.isEmpty(logFilters.getMetadata().getParentResourceId()))
                criteria.add(Criteria.where("metadata.parentResourceId").is(logFilters.getMetadata().getParentResourceId()));

            if(!StringUtils.isEmpty(logFilters.getCommit()))
                criteria.add(Criteria.where("commit").is(logFilters.getCommit()));

            if(!StringUtils.isEmpty(logFilters.getMessage()))
                criteria.add(Criteria.where("message").is(logFilters.getMessage()));

            //level
            if(!ObjectUtils.isEmpty(logFilters.getMessage()))
                criteria.add(Criteria.where("message").is(logFilters.getMessage()));

            if(!ObjectUtils.isEmpty(logFilters.getFromDate()))
                criteria.add(Criteria.where("timestamp").gte(logFilters.getFromDate()).lte(logFilters.getToDate()));
            else if(!ObjectUtils.isEmpty(logFilters.getTimestamp()))
                criteria.add(Criteria.where("timestamp").is(logFilters.getTimestamp()));
            Query query=new Query();
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
            return mongoTemplate.find(query,Log.class);
        }
    }

    public boolean isVerified(LogFilter logFilters)
    {
         if((!StringUtils.isEmpty(logFilters.getSpanId())) || (!StringUtils.isEmpty(logFilters.getCommit())) || (!StringUtils.isEmpty(logFilters.getMessage()))|| (!StringUtils.isEmpty(logFilters.getTraceId())) || (!StringUtils.isEmpty(logFilters.getMetadata().getParentResourceId())) || (!StringUtils.isEmpty(logFilters.getResourceId())) || (!ObjectUtils.isEmpty(logFilters.getTimestamp())) || (!ObjectUtils.isEmpty(logFilters.getLevel())) || ((!ObjectUtils.isEmpty(logFilters.getFromDate())) && (!ObjectUtils.isEmpty(logFilters.getToDate()))) )
         {
             return true;
         }
         return false;
    }

}

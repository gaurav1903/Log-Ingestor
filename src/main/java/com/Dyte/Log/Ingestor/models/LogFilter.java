package com.Dyte.Log.Ingestor.models;

import lombok.Data;

import java.util.Date;

@Data
public class LogFilter {
    String message,resourceId,traceId,spanId,commit;
    LoggingLevel level;
    Date fromDate,toDate,timestamp;
    MetaData metadata;
}

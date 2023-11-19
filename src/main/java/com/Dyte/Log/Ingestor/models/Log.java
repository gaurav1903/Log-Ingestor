package com.Dyte.Log.Ingestor.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

enum LoggingLevel
{
    debug,warn,info,trace,error,fatal
}
@Data
@Document(collection="logs")
public class Log {
    @Id
    String id;
    String message,resourceId,traceId,spanId,commit;
    LoggingLevel level;
    Date timestamp;
    MetaData metadata;


}



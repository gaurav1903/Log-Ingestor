package com.Dyte.Log.Ingestor.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

//{
//
//"level": "error",
//        "message": "Failed to connect to DB",
//        "resourceId": "server-1234",
//        "timestamp": "2023-09-15T08:00:00Z",
//        "traceId": "abc-xyz-123",
//        "spanId": "span-456",
//        "commit": "5e5342f",
//        "metadata": {
//        "parentResourceId": "server-0987"
//        }
//}

enum LoggingLevel
{
    debug,warn,info,trace,error,fatal
}
@Data
public class Log {
    @Id
    String id;
    String message,resourceId,traceId,spanId,commit;
    LoggingLevel level;
    Date timestamp;
    MetaData metadata;


}



package com.Dyte.Log.Ingestor.service.impl;

import com.Dyte.Log.Ingestor.models.LogData;
import com.Dyte.Log.Ingestor.models.Log;
import com.Dyte.Log.Ingestor.service.LogService;

import java.util.ArrayList;
import java.util.List;

public class logServiceImpl implements LogService {

    @Override
    public void addLog(Log log) {
        List<Log> logList=LogData.logList;
        logList.add(log);
    }
}

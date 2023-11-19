package com.Dyte.Log.Ingestor.service;

import com.Dyte.Log.Ingestor.models.Log;
import com.Dyte.Log.Ingestor.models.LogFilter;

import java.util.List;

public interface LogService {

    void addLog(Log log);

    List<Log> getLogs(LogFilter logFilters);

    public void bulkAdd(List<Log>logs);
}

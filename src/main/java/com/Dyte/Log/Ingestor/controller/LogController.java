package com.Dyte.Log.Ingestor.controller;

import com.Dyte.Log.Ingestor.models.Log;
import com.Dyte.Log.Ingestor.models.LogFilter;
import com.Dyte.Log.Ingestor.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping("/")
    public ResponseEntity<?> addLog(@RequestBody Log log)
    {
        logService.addLog(log);
        return new ResponseEntity<>("queued", HttpStatus.OK);
    }

    @GetMapping("/log")
    public ResponseEntity<List<Log>> getLogs(@RequestBody LogFilter logFilters) //all filters are set in req body
    {
        return new ResponseEntity<>(logService.getLogs(logFilters),HttpStatus.OK);

    }

    @PostMapping("/bulk")
    public ResponseEntity<?> bulkAddLogs(@RequestBody List<Log> logs)
    {

        logService.bulkAdd(logs);
        return new ResponseEntity<>("added", HttpStatus.OK);
    }

}

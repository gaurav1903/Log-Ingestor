package com.Dyte.Log.Ingestor.controller;

import com.Dyte.Log.Ingestor.models.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class LogController {


    @PostMapping("/")
    public ResponseEntity<?> addLog(@RequestBody Log log)
    {

        return new ResponseEntity<>("done", HttpStatus.OK);
    }


}

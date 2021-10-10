package com.example.readingisgood.controller;

import com.example.readingisgood.dto.StatisticsDto;
import com.example.readingisgood.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Map<YearMonth, StatisticsDto>> getStatisticsBetweenStartAndEnd(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        final Map<YearMonth, StatisticsDto> statisticsBetweenStartAndEnd = statisticsService.getStatisticsBetweenStartAndEnd(start, end);
        return ResponseEntity.status(HttpStatus.OK).body(statisticsBetweenStartAndEnd);
    }
}

package com.example.readingisgood.service;

import com.example.readingisgood.dto.OrderDto;
import com.example.readingisgood.dto.StatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private OrderService orderService;

    public Map<YearMonth, StatisticsDto> getStatisticsBetweenStartAndEnd(LocalDate start, LocalDate end) {
        Map<YearMonth, StatisticsDto> statisticsDtoMap = new HashMap<>();
        LocalDateTime startDateTime = LocalDateTime.of(start, LocalTime.of(0, 0)).withDayOfMonth(1);
        LocalDateTime endDateTime = LocalDateTime.of(end, LocalTime.of(23, 59)).withDayOfMonth(end.lengthOfMonth());
        final List<OrderDto> orders = orderService.findOrdersBetweenStartAndEnd(startDateTime, endDateTime);
        for (OrderDto order : orders) {
            StatisticsDto statisticsDto = statisticsDtoMap.get(YearMonth.from(order.getOrderDateTime()));
            if (statisticsDto != null) {
                statisticsDto.setTotalBookCount(statisticsDto.getTotalBookCount() + order.getBooks().size());
                statisticsDto.setTotalOrderCount(statisticsDto.getTotalOrderCount() + 1);
                statisticsDto.setTotalPurchasedAmount(statisticsDto.getTotalPurchasedAmount().add(order.getTotalOrderAmount()));
            } else {
                statisticsDto = new StatisticsDto();
                statisticsDto.setTotalBookCount(order.getBooks().size());
                statisticsDto.setTotalOrderCount(1);
                statisticsDto.setTotalPurchasedAmount(order.getTotalOrderAmount());
            }
        }
        return statisticsDtoMap;
    }
}

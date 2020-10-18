package com.inaction.practise.service.api;

import com.inaction.practise.request.SortRequest;
import com.inaction.practise.sorting.Direction;
import com.inaction.practise.sorting.SortArrayService;
import com.inaction.practise.sorting.SortAs;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortService {

    @Autowired
    private SortArrayService sortArrayService;

    public Object[] sortArray(SortRequest sortRequest) {
        String[] incomeArray = Optional.ofNullable(sortRequest.getIncomeArray()).orElseThrow(() ->
                new RuntimeException("An empty array"));
        Direction direction = Optional.ofNullable(sortRequest.getDirection()).orElse(Direction.ASC);
        SortAs sortAs = Optional.ofNullable(sortRequest.getSortAs()).orElse(SortAs.STRING);
        return sortAs.equals(SortAs.STRING)
                ? sortArrayService.selectionSort(incomeArray, direction)
                : sortArrayService.selectionSort(
                Arrays.stream(incomeArray).map(Double::valueOf).toArray(Double[]::new), direction);
    }

}

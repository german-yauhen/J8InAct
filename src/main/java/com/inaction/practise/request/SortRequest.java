package com.inaction.practise.request;

import com.inaction.practise.sorting.Direction;
import com.inaction.practise.sorting.SortAs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortRequest {

    private String[] incomeArray;
    private Direction direction;
    private SortAs sortAs;

}

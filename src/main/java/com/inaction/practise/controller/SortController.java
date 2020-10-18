package com.inaction.practise.controller;

import com.inaction.practise.request.SortRequest;
import com.inaction.practise.service.api.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "array/")
public class SortController {

    @Autowired
    private SortService sortService;

    @RequestMapping(value = "sort", method = RequestMethod.POST)
    public Object sortArray(@ModelAttribute SortRequest sortRequest) {
        return sortService.sortArray(sortRequest);
    }

}

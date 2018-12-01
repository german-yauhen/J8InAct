package com.inaction.practise.interview;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {

  private String userName;
  private LocalDate date;
  private int count;

}

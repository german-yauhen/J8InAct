package com.inaction.practise.interview;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

  private int id;
  private String text;
  private LocalDate date;
  private int userId;

}

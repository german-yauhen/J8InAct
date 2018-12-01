package com.inaction.practise.interview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class CalculationService {

  public List<Report> makeReport(List<Message> messages, List<User> users) {
    Map<ComplexKey, Integer> userDateCount = new HashMap<>();
    for (Message message: messages) {
      ComplexKey complexKey = new ComplexKey(message.getUserId(), message.getDate());
      if (!userDateCount.containsKey(complexKey)) {
        userDateCount.put(complexKey, 1);
        continue;
      }
      Integer count = userDateCount.get(complexKey);
      userDateCount.replace(complexKey, count, ++count);
    }
    List<Report> reports = new ArrayList<>();
    for (Entry<ComplexKey, Integer> entry : userDateCount.entrySet()) {
      ComplexKey key = entry.getKey();
      User user = users.stream()
          .filter(usr -> usr.getUserId() == key.getUserId())
          .findFirst()
          .orElse(null);
      if (user == null) {
        continue;
      }
      reports.add(new Report(user.getName(), key.getDate(), entry.getValue()));
    }
    return reports;
  }

  public List<Report> makeReportUsingStream(List<Message> messages, List<User> users) {
    return null;
  }

  @Data
  @AllArgsConstructor
  private class ComplexKey {
    private int userId;
    private LocalDate date;
  }

}

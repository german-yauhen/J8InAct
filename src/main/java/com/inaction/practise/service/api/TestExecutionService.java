package com.inaction.practise.service.api;

import java.util.List;

public interface TestExecutionService {

  void execute(List<String> cmdArgs);

  void executeMultiThreadExample();

  void executeCalculationOfMessages();

}

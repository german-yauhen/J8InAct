package com.inaction.practise;

import com.inaction.practise.service.api.TestExecutionService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PractiseApplication implements CommandLineRunner {

  @Autowired
  private TestExecutionService testExecutionService;

  public static void main(String[] args) {
    SpringApplication.run(PractiseApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    testExecutionService.execute(Arrays.asList(args));
    testExecutionService.executeMultiThreadExample();
  }

}

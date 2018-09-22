package com.inaction.practise;

import com.inaction.practise.service.api.GeneralService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PractiseApplication implements CommandLineRunner {

  @Autowired
  private GeneralService generalService;

  public static void main(String[] args) {
    SpringApplication.run(PractiseApplication.class, args);
  }

  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    generalService.execute(Arrays.asList(args));
  }
}

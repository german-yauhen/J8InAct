package com.inaction.practise.service.impl;

import com.inaction.practise.bean.Photo;
import com.inaction.practise.interview.CalculationService;
import com.inaction.practise.interview.GenerationUserMessageUtil;
import com.inaction.practise.interview.Message;
import com.inaction.practise.interview.Report;
import com.inaction.practise.interview.User;
import com.inaction.practise.multithread.CallableClass;
import com.inaction.practise.multithread.RunnableClass;
import com.inaction.practise.service.api.TestExecutionService;
import com.inaction.practise.service.api.PhotoPredicate;
import com.inaction.practise.util.FiltrationService;
import com.inaction.practise.util.GenerationService;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestExecutionServiceImpl implements TestExecutionService {

  @Autowired
  private GenerationService generationService;

  @Autowired
  private FiltrationService filtrationService;

  @Autowired
  private GenerationUserMessageUtil generationUserMessageUtil;

  @Autowired
  private CalculationService calculationService;

  @Value("${count.photo}")
  private Integer genCount;

  @Override
  public void execute(List<String> cmdArgs) {
    log.warn("Start program with command line arguments " + cmdArgs);
    List<Photo> photos = generationService.generatePhotos(genCount);
    photos.forEach(photo -> log.info(photo.toString()));

    List<Photo> coloredPhotos = filtrationService.filterPhotosByCondition(photos,
        new PhotoColorPredicateImpl());

    List<Photo> coloredLandscapePhotos = filtrationService.filterPhotosByCondition(coloredPhotos,
        new PhotoLandscapePredicateImpl());

    // Filtration using anonymous class
    List<Photo> coloredLandscapePhotosByNikon = filtrationService.filterPhotosByCondition(
        coloredLandscapePhotos,
        new PhotoPredicate() {
          @Override public boolean test(Photo photo) {
            return photo.getAuthor().getDeviceModel().equals("Nikon");
          }
        });

    PhotoPredicate photoPredicate = (Photo photo) ->
        photo.getAuthor().getDeviceModel().equals("Nikon")
        || photo.getAuthor().getDeviceModel().equals("Canon");
    List<Photo> coloredLandscapePhotosByNikonOrCanon = filtrationService.filterPhotosByCondition(
        coloredLandscapePhotos, photoPredicate);

    Predicate<Photo> photoGeneralPredicate = (Photo photo) -> photo.getAuthor()
        .getDeviceModel().equals("Canon");
    List<Photo> coloredLandscapePhotosByCanon = filtrationService.filterPhotosByGeneralCondition(
        coloredLandscapePhotos, photoGeneralPredicate);
    log.warn("End program");
  }

  @Override
  public void executeMultiThreadExample() {
    Thread threadOne = new Thread(new RunnableClass("First thread"));
    Thread threadTwo = new Thread(new RunnableClass("Second thread"));
    Thread threadThree = new Thread(new RunnableClass("Third thread"));
    threadOne.run();
    threadThree.run();
    threadTwo.run();

    ExecutorService executorService = Executors.newFixedThreadPool(3);
    List<String> welcomeMessages = Arrays.asList("Hi!", "Hello!", "Welcome!");
    Set<Future<String>> results = welcomeMessages.stream()
        .map(msg -> executorService.submit(new CallableClass(msg)))
        .collect(Collectors.toSet());
    results.forEach(stringFuture -> {
      try {
        log.info(stringFuture.get());
      } catch (InterruptedException | ExecutionException e) {
        log.error(e.getMessage());
      }
    });
  }

  @Override
  public void executeCalculationOfMessages() {
    int userCount = 5;
    int messageCount = 20;
    List<User> users = generationUserMessageUtil.generateUsers(userCount);
    List<Message> messages = generationUserMessageUtil.generateMessages(messageCount, users);
    List<Report> reports = calculationService.makeReport(messages, users);
    reports.forEach(report -> log.info(report.toString()));
  }

}

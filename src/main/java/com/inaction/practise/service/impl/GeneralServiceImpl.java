package com.inaction.practise.service.impl;

import com.inaction.practise.bean.Photo;
import com.inaction.practise.service.api.GeneralService;
import com.inaction.practise.util.GenerationService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralServiceImpl implements GeneralService {

  @Autowired
  private GenerationService generationService;

  @Value("${count.photo}")
  private Integer genCount;

  @Override
  public void execute(List<String> cmdArgs) {
    log.warn("Start program with command line arguments " + cmdArgs);
    List<Photo> photos = generationService.generatePhotos(genCount);
    photos.forEach(photo -> log.info(photo.toString()));
    log.warn("End program");
  }

}

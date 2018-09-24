package com.inaction.practise.service.impl;

import com.inaction.practise.bean.Photo;
import com.inaction.practise.service.api.GeneralService;
import com.inaction.practise.service.api.PhotoPredicate;
import com.inaction.practise.util.FiltrationService;
import com.inaction.practise.util.GenerationService;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralServiceImpl implements GeneralService {

  @Autowired
  private GenerationService generationService;

  @Autowired
  private FiltrationService filtrationService;

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

}

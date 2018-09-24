package com.inaction.practise.util;

import com.inaction.practise.bean.Category;
import com.inaction.practise.bean.Photo;
import com.inaction.practise.service.api.PhotoPredicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;

@Service
public class FiltrationService {

  // Primitive code
  public List<Photo> filterPhotosByColor(List<Photo> photos, Boolean isColor) {
    List<Photo> result = new ArrayList<>();
    for (Photo photo: photos) {
      if (photo.getIsColor().equals(isColor)) {
        result.add(photo);
      }
    }
    return result;
  }

  // Primitive code
  public List<Photo> filterPhotosByCategory(List<Photo> photos, Category category) {
    List<Photo> result = new ArrayList<>();
    for (Photo photo: photos) {
      if (photo.getCategory().equals(category)) {
        result.add(photo);
      }
    }
    return result;
  }

  // A little bit more abstract filtration method
  public List<Photo> filterPhotosByCondition(List<Photo> photos, PhotoPredicate photoPredicate) {
    List<Photo> result = new ArrayList<>();
    for (Photo photo: photos) {
      if (photoPredicate.test(photo)) {
        result.add(photo);
      }
    }
    return result;
  }

  // Using java.util.function.Predicate class
  public List<Photo> filterPhotosByGeneralCondition(List<Photo> photos,
      Predicate<Photo> generalPredicate) {
    List<Photo> result = new ArrayList<>();
    for (Photo photo: photos) {
      if (generalPredicate.test(photo)) {
        result.add(photo);
      }
    }
    return result;
  }

}

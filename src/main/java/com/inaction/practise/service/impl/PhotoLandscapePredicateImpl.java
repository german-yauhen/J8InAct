package com.inaction.practise.service.impl;

import static com.inaction.practise.bean.Category.LANDSCAPE;

import com.inaction.practise.bean.Photo;
import com.inaction.practise.service.api.PhotoPredicate;
import org.springframework.stereotype.Service;

@Service
public class PhotoLandscapePredicateImpl implements PhotoPredicate {

  @Override
  public boolean test(Photo photo) {
    return photo.getCategory().equals(LANDSCAPE);
  }
}

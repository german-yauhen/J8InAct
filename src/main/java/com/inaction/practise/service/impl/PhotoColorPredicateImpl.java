package com.inaction.practise.service.impl;

import com.inaction.practise.bean.Photo;
import com.inaction.practise.service.api.PhotoPredicate;
import org.springframework.stereotype.Service;

@Service
public class PhotoColorPredicateImpl implements PhotoPredicate {

  @Override
  public boolean test(Photo photo) {
    return photo.getIsColor();
  }

}

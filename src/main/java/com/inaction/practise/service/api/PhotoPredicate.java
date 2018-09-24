package com.inaction.practise.service.api;

import com.inaction.practise.bean.Photo;

@FunctionalInterface
public interface PhotoPredicate {

  boolean test(Photo photo);

}

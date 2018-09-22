package com.inaction.practise.util;

import com.inaction.practise.bean.Author;
import com.inaction.practise.bean.Category;
import com.inaction.practise.bean.Dimension;
import com.inaction.practise.bean.Photo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenerationService {

  private List<Author> authors;
  private Integer authorsSize;
  private List<Dimension> dimensions;
  private Integer dimensionsSize;
  private List<Category> categories;
  private Integer categoriesSize;
  private Random random;

  @PostConstruct
  public void init() {
    log.warn("Init method from " + this.getClass().getSimpleName());
    this.authors = generateAuthors();
    this.authorsSize = authors.size();
    this.dimensions = Dimension.dimensions();
    this.dimensionsSize = dimensions.size();
    this.categories = Category.categories();
    this.categoriesSize = categories.size();
    this.random = new Random();
  }

  public List<Photo> generatePhotos(Integer count) {
    List<Photo> photoList = new ArrayList<>();
    Optional.ofNullable(count).ifPresent(cnt -> {
      IntStream.range(0, count).forEach(index -> {
        photoList.add(generatePhoto());
      });
    });
    return photoList;
  }

  private List<Author> generateAuthors() {
    Author author1 = new Author("Yauheni", "German", "Canon");
    Author author2 = new Author("Nikolai", "Pankoff", "Zenit");
    Author author3 = new Author("Victoria", "IS", "Nikon");
    Author author4 = new Author("Katerina", "Barta", "Nikon");
    Author author5 = new Author("Adrei", "Shaman", "Sony");
    return Arrays.asList(author1, author2, author3, author4, author5);
  }

  private Author randomAuthor() {
    return authors.get(random.nextInt(authorsSize));
  }

  private Dimension randomDimension() {
    return dimensions.get(random.nextInt(dimensionsSize));
  }

  private Category randomCategory() {
    return categories.get(random.nextInt(categoriesSize));
  }

  private Boolean randomIsColor() {
    return random.nextBoolean();
  }

  private LocalDate randomDate() {
    long fromTimestamp = LocalDate.of(2016, 1, 1).toEpochDay();
    long toTimestamp = LocalDate.now().toEpochDay();
    return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(fromTimestamp, toTimestamp));
  }

  private Photo generatePhoto() {
    Dimension dimension = randomDimension();
    return Photo.builder()
        .id(UUID.randomUUID().toString())
        .author(randomAuthor())
        .category(randomCategory())
        .width(dimension.getWidth())
        .height(dimension.getHeight())
        .isColor(randomIsColor())
        .creationDate(randomDate())
        .build();
  }

}

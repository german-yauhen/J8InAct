package com.inaction.practise.bean;

import java.time.LocalDateTime;

public class Moment {

  private String name;
  private String author;
  private Category category;
  private LocalDateTime time;

  private Moment(String name, String author, Category category, LocalDateTime time) {
    super();
    this.name = name;
    this.author = author;
    this.category = category;
    this.time = time;
  }

  @Override
  public String toString() {
    return String.format("Moment %s in category %s was made %s by %s",
        this.name, this.category, this.time, this.author);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String name;
    private String author;
    private Category category;
    private LocalDateTime time;

    private Builder() {
      super();
    }

    public Builder name(String name) {
      this.name = name != null && !name.isEmpty() ? name : "";
      return this;
    }

    public Builder author(String author) {
      this.author = author != null && !author.isEmpty() ? author : "";
      return this;
    }

    public Builder category(Category category) {
      this.category = category != null ? category : Category.LANDSCAPE;
      return this;
    }

    public Builder time(LocalDateTime time) {
      this.time = time != null ? time : LocalDateTime.now();
      return this;
    }

    public Moment build() {
      return new Moment(this.name, this.author, this.category, this.time);
    }
  }

}

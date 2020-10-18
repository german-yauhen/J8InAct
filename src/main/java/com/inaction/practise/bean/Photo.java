package com.inaction.practise.bean;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private String id;
    private Integer width;
    private Integer height;
    private Author author;
    private Category category;
    private Boolean isColor;
    private LocalDate creationDate;

    @Override
    public String toString() {
        return String.format("%s photo [%s] (%sx%s) in category %s, created %s by %s",
                this.isColor ? "Colored" : "BW", this.id, this.width, this.height, this.category,
                this.author, this.creationDate);
    }
}

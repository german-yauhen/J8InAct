package com.inaction.practise.bean;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private String id;
    private String name;
    private String surname;
    private String deviceModel;

    public Author(String name, String surname, String deviceModel) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.deviceModel = deviceModel;
    }

    @Override
    public String toString() {
        return String.format("%s %s with %s", this.name, this.surname, this.deviceModel);
    }
}

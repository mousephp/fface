package com.fface.group.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagePostGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne
    private PostGroup postGroup;

    public ImagePostGroup(String image, PostGroup postGroup) {
        this.image = image;
        this.postGroup = postGroup;
    }
}

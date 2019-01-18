package com.nani.boot1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PostPhoto {
    private long idx;
    private long postIdx;
    private int order;
    private String path;
    private Date createdAt;

    private List<PostPhoto> postPhotos = new ArrayList<>();

}

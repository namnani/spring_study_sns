package com.nani.boot1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Reply {
    private long idx;
    private long postIdx;
    private User author;
    private String content;
    private int state;
    private Date createdAt;
    private Date updatedAt;
}

package com.nani.boot1.service;

import com.nani.boot1.model.Post;

public interface PostService {
    Object getPostList(Integer offset, Integer count);
    Object createPost(Post post);
    Object updatePost(Post post);
    Object deletePost(int userIdx, long postIdx);
    Object likePost(int userIdx, long postIdx);
    Object unlikePost(int userIdx, long postIdx);
}

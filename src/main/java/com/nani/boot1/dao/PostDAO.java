package com.nani.boot1.dao;

import com.nani.boot1.model.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO {

    List<Post> selectPostList(@Param("offset") long offset, @Param("limit") int limit);

    int insertPost(Post post);

    int deletePost(@Param("postIdx") long postIdx, @Param("userIdx") int userIdx);

    int insertPostLike(@Param("postIdx") long postIdx, @Param("userIdx") int userIdx);

    int deletePostLike(@Param("postIdx") long postIdx, @Param("userIdx") int userIdx);

}

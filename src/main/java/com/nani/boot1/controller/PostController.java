package com.nani.boot1.controller;

import com.nani.boot1.common.Constant;
import com.nani.boot1.model.Post;
import com.nani.boot1.model.User;
import com.nani.boot1.resolver.SessionLogin;
import com.nani.boot1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.REST_API_VERSION+"/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/list")
    public Object listGet(
        @RequestParam(value = "offset", required = false) Integer offset,
        @RequestParam(value = "count", required = false) Integer count
    ){
        if(offset == null)
            offset = Constant.POST_LIST_DEFAULT_OFFSET;
        if(count == null)
            count = Constant.POST_LIST_DEFAULT_COUNT;


        return postService.getPostList(offset, count);
    }



    //search
    @PostMapping("")
    public Object createPOST(@SessionLogin Integer userIdx, @RequestBody Post post)
    {
            User author = new User();
            author.setIdx(userIdx);
            post.setAuthor(author);

            return postService.createPost(post);
    }

    //이건 당장에 안쓸수있다.
    @GetMapping("/{idx}")
    public Object readGET( @PathVariable("idx") Integer postIdx){
        return null;
    }

    @PostMapping("/{idx}")
    public Object updatePUT(
            @SessionLogin Integer userIdx,
            @PathVariable("idx") Integer postIdx,
            @RequestBody Post post
    ){
        return null;
    }

    @DeleteMapping("/{idx")
    public Object deleteDELETE(@SessionLogin Integer userIdx, @PathVariable("idx") Integer postIdx){
        return postService.deletePost(userIdx, postIdx);
    }

    @PostMapping("/{idx}/like")
    public Object likePOST(@SessionLogin Integer userIdx, @PathVariable("idx") Integer postIdx){
        return postService.likePost(userIdx, postIdx);
    }

    @PostMapping("/{idx}/unlike")
    public Object unlikePOST(@SessionLogin Integer userIdx, @PathVariable("idx") Integer postIdx){
        return postService.unlikePost(userIdx, postIdx);
    }
}

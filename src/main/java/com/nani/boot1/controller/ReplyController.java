package com.nani.boot1.controller;

import com.nani.boot1.common.Constant;
import com.nani.boot1.model.Reply;
import com.nani.boot1.model.User;
import com.nani.boot1.resolver.SessionLogin;
import com.nani.boot1.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.REST_API_VERSION+"/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @GetMapping("/list")
    public Object listGET(
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "count", required = false) Integer count
            //sort
    ) {
        if(offset == null)
            offset = Constant.REPLY_LIST_DEFAULT_OFFSET;
        if(count == null)
            count = Constant.REPLY_LIST_DEFAULT_COUNT;

        return replyService.getReplyList(offset, count);
    }

    //search

    @PostMapping("")
    public Object createPOST(@SessionLogin Integer userIdx, @RequestBody Reply reply ) {
        User author = new User();
        author.setIdx(userIdx);
        reply.setAuthor(author);

        return replyService.createReply(reply);
    }

    @GetMapping("/{idx}")
    public Object readGET( @PathVariable("idx") Integer replyIdx ) {
        return null;
    }

    @PostMapping("/{idx}")
    public Object updatePUT(
            @SessionLogin Integer userIdx,
            @PathVariable("idx") Integer replyIdx,
            @RequestBody Reply reply
    ) {
        return null;
    }

    @DeleteMapping("/{idx}")
    public Object deleteDELETE( @SessionLogin Integer userIdx, @PathVariable("idx") Integer replyIdx ) {
        return replyService.deleteReply(userIdx, replyIdx);
    }

    @PostMapping("/{idx}/like")
    public Object likePOST( @SessionLogin Integer userIdx, @PathVariable("idx") Integer replyIdx ){
        return replyService.likeReply(userIdx, replyIdx);
    }

    @PostMapping("/{idx}/unlike")
    public Object unlikePOST( @SessionLogin Integer userIdx, @PathVariable("idx") Integer replyIdx ){
        return replyService.unlikeReply(userIdx, replyIdx);
    }
}

package com.nani.boot1.service;

import com.nani.boot1.model.Post;
import com.nani.boot1.model.Reply;

public interface ReplyService {
    Object getReplyList(Integer offset, Integer count);
    Object createReply(Reply reply);
    Object updateReply(Reply reply);
    Object deleteReply(int userIdx, long replyIdx);
    Object likeReply(int userIdx, long replyIdx);
    Object unlikeReply(int userIdx, long replyIdx);
}

package com.qyl.blog.service;

import com.qyl.blog.po.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}

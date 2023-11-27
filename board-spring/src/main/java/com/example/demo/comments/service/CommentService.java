package com.example.demo.comments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.comments.dao.CommentDao;
import com.example.demo.comments.domain.Comment;

@Service
public class CommentService {

  @Autowired
  private CommentDao commentDao;

  public void addComment(Comment comment) {
    commentDao.add(comment);
  }
}

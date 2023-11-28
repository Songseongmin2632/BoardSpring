package com.example.demo.comments.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.comments.dao.CommentDao;
import com.example.demo.comments.domain.Comment;

@Service
public class CommentService {
  @Autowired
  CommentDao commentDao;

  public void add(Comment comment) {
    commentDao.add(comment);
  }

  public List<Comment> getComments(int boardId, int start) {
    List<Comment> list = commentDao.getParents(boardId, start);
    // for (int i = 0; i < list.size(); ++i) {
    // list.get(i).setChildren(
    // commentDao.getChildren(
    // boardId,
    // list.get(i).getId()
    // )
    // );
    // }
    list.forEach((item) -> {
      item.setChildren(getChildren(boardId, item));
    });
    return list;
  }

  private List<Comment> getChildren(int boardId, Comment comment) {
    List<Comment> list = commentDao.getChildren(boardId, comment.getId());
    list.forEach((item) -> {
      item.setChildren(getChildren(boardId, item));
    });
    return list;
  }
}

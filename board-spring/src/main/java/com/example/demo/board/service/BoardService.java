package com.example.demo.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.board.dao.BoardDao;
import com.example.demo.board.domain.Board;

@Service
public class BoardService {
  @Autowired
  BoardDao boardDao;

  public void add(Board board) {
    boardDao.add(board);
  }

  public List<Board> getAll() {
    return boardDao.getAll();
  }

  public void fix(Board board) {
    boardDao.fix(board);
  }

  public void delete(Board board) {
    boardDao.delete(board);
  }
}

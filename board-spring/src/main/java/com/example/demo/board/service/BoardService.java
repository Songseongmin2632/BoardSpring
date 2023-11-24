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

  public Board get(int id) {
    return boardDao.get(id);
  }

  public List<Board> getAll(int page, int count) {
    return boardDao.getAll((page - 1) * count, count);
  }

  public int getPageCount(int count) {
    // 한 페이지에서 목록을 몇개 출력할 것인가? 5
    // 글 1 => 페이지 1 <= (1 - 1) / 5 + 1
    // 글 10 => 페이지 2 <= (10 - 1) / 5 + 1
    // 글 11 => 페이지 3 <= (11 - 1) / 5 + 1
    // 글 15 => 페이지 3 <= (15 - 1) / 5 + 1
    // int / int => int?
    return (boardDao.getCount() - 1) / count + 1;
  }
}

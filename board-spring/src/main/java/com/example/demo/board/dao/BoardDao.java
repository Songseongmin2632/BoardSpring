package com.example.demo.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.demo.board.domain.Board;

@Repository
public class BoardDao {
  @Autowired
  JdbcTemplate jdbcTemplate;

  private RowMapper<Board> mapper = new RowMapper<Board>() {

    @Override
    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
      // TODO Auto-generated method stub
      return new Board(rs.getInt("id"), rs.getString("title"), rs.getString("content"),
          rs.getInt("views"), 0, 0, rs.getTimestamp("created_at"), rs.getInt("is_withdrew") == 1,
          rs.getInt("user_id"), rs.getString("userName"));
    }
  };

  public void add(Board board) {
    jdbcTemplate.update(
        "insert into boards (title, content, is_withdrew, user_id) values (?, ?, ?, ?)",
        board.getTitle(), board.getContent(), board.isWithdrew() ? 1 : 0, board.getUserId());
  }

  public List<Board> getAll() {
    return jdbcTemplate.query(
        "select boards.*, users.name as userName from boards join users on boards.user_id=users.id order by boards.id offset 2 rows fetch first 5 rows only",
        mapper);
  }

  public void fix(Board board) {
    jdbcTemplate.update("update boards set title = ?, content = ? where id = ?", board.getTitle(),
        board.getContent());
  }

  public void delete(Board board) {
    jdbcTemplate.update("delete from boards where id = ?", board.getId());
  }
}

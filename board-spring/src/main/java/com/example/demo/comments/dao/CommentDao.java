package com.example.demo.comments.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.demo.comments.domain.Comment;


@Repository
public class CommentDao {
  @Autowired
  JdbcTemplate jdbcTemplate;

  private RowMapper<Comment> mapper = new RowMapper<Comment>() {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
      // TODO Auto-generated method stub
      return new Comment(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("created_at"),
          rs.getInt("is_withdrew") == 1, rs.getInt("user_id"), rs.getInt("board_id"),
          rs.getInt("comment_id"), null);
    }
  };

  public void add(Comment comment) {
    jdbcTemplate.update(
        "insert into comments (\"content\", \"user_id\", \"board_id\", \"comment_id\") values (?, ?, ?, ?)",
        comment.getContent(), comment.getUserId(), comment.getBoardId(),
        comment.getCommentId() > 0 ? comment.getCommentId() : null);
  }

  public List<Comment> getParents(int boardId, int start) {
    return jdbcTemplate.query(
        "select * from comments " + "where \"board_id\" = ? and \"comment_id\" is null "
            + "order by \"id\" desc " + "offset ? rows fetch first 5 rows only",
        mapper, boardId, start);
  }

  public List<Comment> getChildren(int boardId, int commentId) {
    return jdbcTemplate.query("select * from comments "
        + "where \"board_id\" = ? and \"comment_id\" = ? " + "order by \"id\"", mapper, boardId,
        commentId);
  }
}

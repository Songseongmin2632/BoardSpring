package com.example.demo.board.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.board.domain.Board;
import com.example.demo.board.service.BoardService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
  @Autowired
  BoardService boardService;
  int count = 5;

  @GetMapping("/")
  public String boardMainPage(Model model, @RequestParam Map<String, String> data) {
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("title", "게시판");
    model.addAttribute("path", "/board/index");
    model.addAttribute("content", "boardFragment");
    model.addAttribute("contentHead", "boardFragmentHead");
    model.addAttribute("list", boardService.getAll(page, count));
    model.addAttribute("pageCount", boardService.getPageCount(count));
    return "/basic/layout";
  }

  @PostMapping("/add")
  public String add(@RequestParam Map<String, String> data, HttpSession session) {
    if (session.getAttribute("userName") != null) {
      boardService.add(new Board(data.get("title"), data.get("content"),
          Integer.parseInt(session.getAttribute("userId").toString())));
    }

    return "redirect:/";
  }

  @GetMapping("/notice")
  public String noticePage(Model model) {
    model.addAttribute("title", "공지사항");
    model.addAttribute("path", "/board/notice");
    model.addAttribute("content", "noticeFragment");
    model.addAttribute("contentHead", "noticeFragmentHead");
    return "/basic/layout";
  }

  @GetMapping("/board/{boardId}")
  public String itemPage(Model model, @PathVariable("boardId") int boardId) {
    Board board = boardService.get(boardId);

    model.addAttribute("title", board.getTitle());
    model.addAttribute("path", "/board/item");
    model.addAttribute("content", "boardItemFragment");
    model.addAttribute("contentHead", "boardItemFragmentHead");
    board.setContent(board.getContent().replace("\n", "<br />"));
    model.addAttribute("board", board);

    return "/basic/layout";
  }
}

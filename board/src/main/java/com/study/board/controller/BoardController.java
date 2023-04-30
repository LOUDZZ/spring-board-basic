package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {
    /* @Autowired 어노테이션이 붙은 필드나 메서드에 주입되는 객체는 스프링 컨테이너에서 생성된 빈임.
    * 빈으로 등록된 클래스에 대해서는
    * (스프링에서는 @Component, @Service, @Repository, @Controller 등의 어노테이션을 사용하여 빈을 등록할 수 있으며,
    * 이들 어노테이션은 스프링에서 자동으로 빈으로 등록 됨.)
    * 스프링 컨테이너가 객체를 생성하고, 필요한 곳에서 해당 빈을 참조할 수 있도록 관리
     */
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write로 접속 하면 html 문서 보여줌
    public String boardWriteForm() {
        return "boardWrite";
    }


    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{ //form 태그를 통해 전달된 title과 content를 entity 형태로 받음
        boardService.write(board, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "boardList";
    }

    //localhost:8080/board/view?id=1
    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    //{id}가 PathVariable을 통해 들어와 Integer id로 인식 된다.
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardModify";
    }
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable Integer id, Board board, Model model, MultipartFile file) throws Exception {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }


}

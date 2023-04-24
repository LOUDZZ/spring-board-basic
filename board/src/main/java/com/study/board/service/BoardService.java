package com.study.board.service;

import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.board.entity.Board;

import java.util.List;

@Service
//Service를 통해 빈 등록
public class BoardService {
    @Autowired //스프링빈이 알아서 읽어와서 아래에 주입.
    private BoardRepository boardRepository;
    public void write(Board board) {
        boardRepository.save(board);
    }

    public List<Board> boardList () {
        return boardRepository.findAll();
    }
}

package com.study.board.service;

import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.board.entity.Board;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
//Service를 통해 빈 등록
public class BoardService {
    @Autowired //스프링빈이 알아서 읽어와서 아래에 주입.
    private BoardRepository boardRepository;
    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\board\\src\\main\\resources\\static\\files";

        UUID uuId = UUID.randomUUID();

        String fileName = uuId + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public List<Board> boardList () {
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}

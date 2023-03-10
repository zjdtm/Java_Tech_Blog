package org.zerock.b01.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.BoardRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long register(Board board) {
        boardRepository.save(board);
        return board.getBno();
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow();
    }

    @Transactional
    public void updateBoard(Long id, String title, String content){
        Board board = boardRepository.findById(id).orElseThrow();
        board.change(title, content);
    }

    public Page paging(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Page searchBoard(String keyword, Pageable pageable){
        return boardRepository.searchAll(keyword, pageable);
    }

}
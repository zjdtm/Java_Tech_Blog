package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.repository.BoardRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Transactional
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);

        Long bno = boardRepository.save(board).getBno();

        return bno;

    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public BoardDTO findOne(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("board doesn't exist"));

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);

        return boardDTO;
    }

    @Transactional
    public Long updateBoard(BoardDTO boardDTO){
        Board board = boardRepository.findById(boardDTO.getBno()).orElseThrow(() -> new IllegalArgumentException("board doesn't exist"));

        board.change(boardDTO.getTitle(), boardDTO.getContent());

        return board.getBno();
    }

    @Transactional
    public void deleteBoard(Long bno){
        boardRepository.deleteById(bno);
    }

    public Page<Board> searchBoard(PageRequestDTO pageRequestDTO){

        PageRequest pageRequest = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());

        return boardRepository.searchAll(pageRequestDTO.getKeyword(), pageRequest);

    }

}
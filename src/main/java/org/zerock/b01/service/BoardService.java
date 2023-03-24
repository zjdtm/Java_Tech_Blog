package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Transactional
    public Long register(Board board) {

        Long bno = boardRepository.save(board).getBno();

        return bno;

    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("board doesn't exist"));

        return board;
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

    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){

        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    public Page<Board> apiList(PageRequestDTO pageRequestDTO){

        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(keyword, pageable);

        return result;
    }

//    public Slice<Board> apiSlice(){
//
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("bno");
//
//        Slice<Board> result = boardRepository.searchSlice(keyword, pageable);
//
//        return result;
//
//    }

}
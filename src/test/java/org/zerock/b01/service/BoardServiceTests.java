package org.zerock.b01.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class BoardServiceTests {

    @Autowired
    BoardService boardService;


    @Test
    @BeforeEach
    public void testRegister(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("Sample Title..." + i)
                    .content("Sample Content..."+ i)
                    .writer("user" + i)
                    .build();

            boardService.register(boardDTO);
        });
    }

    @Test
    public void testFindOne(){

        BoardDTO boardDTO = BoardDTO.builder()
                .title("안녕하세요!!")
                .content("반갑습니다 처음 개발하게 되어서 ....")
                .writer("나신입")
                .build();

        Long bno = boardService.register(boardDTO);

        BoardDTO findBoard = boardService.findOne(bno);

        assertThat(findBoard.getTitle()).isEqualTo(boardDTO.getTitle());

    }

    @Test
    public void testFindAll(){

        // given
        // when

        List<Board> boards = boardService.findBoards();

        // then
        assertThat(boards.size()).isEqualTo(100);
    }

    @Test
    public void testUpdate() {

        // given
        BoardDTO boardDTO = BoardDTO.builder()
                .title("백앤드 개발자가 알아야할 필수 지식")
                .content("백엔드 개발자는 이러 이러 이러한 내용을...")
                .writer("백엔드 지망생")
                .build();

        // when
        Long boardId = boardService.register(boardDTO);

        BoardDTO updateBoardDTO = BoardDTO.builder()
                .bno(boardId)
                .title("프론트 엔드 개발자가 알아야할 필수 지식")
                .content("프론트 개발자는 이러 이러 이러한 내용을...")
                .writer("프론트 지망생")
                .build();

        Long updatedBoardId = boardService.updateBoard(updateBoardDTO);

        BoardDTO findBoard = boardService.findOne(updatedBoardId);

        // then
        assertThat(findBoard.getTitle()).isEqualTo(updateBoardDTO.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(updateBoardDTO.getContent());

    }

    @Test
    public void testDelete(){

        BoardDTO boardDTO = BoardDTO.builder()
                .title("백앤드 개발자가 알아야할 필수 지식")
                .content("백엔드 개발자는 이러 이러 이러한 내용을...")
                .writer("백엔드 지망생")
                .build();

        Long boardId = boardService.register(boardDTO);

        boardService.deleteBoard(boardId);

        List<Board> boards = boardService.findBoards();

        assertThat(boards.size()).isEqualTo(100);
    }

    @Test
    public void testSearch(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(0)
                .size(10)
                .keyword("1")
                .build();

        Page<Board> boards = boardService.searchBoard(pageRequestDTO);

        assertThat(boards.getSize()).isEqualTo(10);
        assertThat(boards.getContent()).extracting("title")
                .containsExactly("Sample Title...1", "Sample Title...10",
                        "Sample Title...11", "Sample Title...12", "Sample Title...13", "Sample Title...14",
                        "Sample Title...15", "Sample Title...16", "Sample Title...17", "Sample Title...18");

    }

}
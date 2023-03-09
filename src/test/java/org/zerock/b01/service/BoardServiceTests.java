package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
public class BoardServiceTests {

    @Autowired
    BoardService boardService;

    @Test
    public void board_register() {
        //given
        Member member = new Member("lofi", "lofi@naver.com", "11111");

        Board board = new Board("패스트 캠퍼스 회고록", "안녕하세요 저는 ....", member);

        //when
        Long register_id = boardService.register(board);

        //then
        assertThat(board).isEqualTo(boardService.findOne(register_id));
    }

    @Test
    public void board_findAll(){

        // given
        Member member = new Member("lofi", "lofi@naver.com", "11111");

        Board board1 = new Board("요즘 경제가 어려워요", "요즘 참 살기 힘들시죠.......", member);
        Board board2 = new Board("코딩이 너무 어려워요 어떡하죠?", "AI의 발전으로 코딩의 대한 미래는 .....", member);

        // when
        boardService.register(board1);
        boardService.register(board2);

        List<Board> boards = boardService.findBoards();

        // then
        assertThat(boards.get(0).getTitle()).isEqualTo(board1.getTitle());
        assertThat(boards.get(1).getTitle()).isEqualTo(board2.getTitle());
    }

    @Test
    public void board_update() throws Exception {

        // given
        Member member = new Member("lofi", "lofi@naver.com", "11111");

        Board board = new Board("백엔드 개발자가 알아야할 필수 지식", "백엔드 개발자라면 꼭 알아야 할 .....", member);

        // when
        Long registeredId = boardService.register(board);
        Board findBoard = boardService.findOne(registeredId);

        findBoard.change("프론트엔드 개발자가 알아야할 필수 지식", "프론트 엔드 개발자가 꼭 알아야 할 .....");

        // then
        Board updatedBoard = boardService.findOne(registeredId);
        assertThat(updatedBoard.getTitle()).isEqualTo("프론트엔드 개발자가 알아야할 필수 지식");
        assertThat(updatedBoard.getContent()).isEqualTo("프론트 엔드 개발자가 꼭 알아야 할 .....");
        assertThat(updatedBoard.getMember().getEmail()).isEqualTo("lofi@naver.com");

    }

    @Test
    public void board_like() {

        // given
        Member member = new Member("lofi", "lofi@naver.com", "11111");

        Board board = new Board("백엔드 개발자가 알아야할 필수 지식", "백엔드 개발자라면 꼭 알아야 할 .....", member);

        // when
        Long registeredId = boardService.register(board);
        Board findBoard = boardService.findOne(registeredId);

        findBoard.boardLike();
        findBoard.boardLike();
        findBoard.boardNotLike();

        // then
        assertThat(findBoard.getLike()).isEqualTo(1);

    }

}
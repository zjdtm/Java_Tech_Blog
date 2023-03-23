package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Member;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
public class BoardServiceTests {

    @Autowired
    MemberService memberService;

    @Autowired
    BoardService boardService;

    @Test
    public void testRegister(){

        // given
        Member member = new Member("관리자", "관리자@gmail.com", "1234");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("안녕하세요");
        boardDTO.setContent("안녕하세요 게시판에 온 것을 환영합니다.");
        boardDTO.setMember(member);

        // when
        memberService.join(member);

        Long boardId = boardService.register(boardDTO);

        BoardDTO findBoard = boardService.findOne(boardId);

        // then
        assertThat(findBoard.getMember()).isEqualTo(member);

    }

    @Test
    public void testFindOne(){

        // given
        Member member = new Member("나신입", "나신입@gmail.com", "5678");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("안녕하세요");
        boardDTO.setContent("안녕하세요 게시판에 온 것을 환영합니다.");
        boardDTO.setMember(member);

        // when
        Long memberId = memberService.join(member);

        Long bno = boardService.register(boardDTO);

        BoardDTO findBoard = boardService.findOne(bno);

        // then
        assertThat(findBoard.getTitle()).isEqualTo(boardDTO.getTitle());
        assertThat(memberService.findOne(memberId)).isEqualTo(member);
        assertThat(boardService.findBoards().size()).isEqualTo(1);

    }

    @Test
    public void testFindAll(){

        // given
        Member member1 = new Member("나신입", "나신입@gmail.com", "5678");

        BoardDTO boardDTO1 = new BoardDTO();
        boardDTO1.setTitle("안녕하세요");
        boardDTO1.setContent("안녕하세요 게시판에 온 것을 환영합니다.");
        boardDTO1.setMember(member1);

        Member member2 = new Member("가신입", "가신입@gmail.com", "5678");

        BoardDTO boardDTO2 = new BoardDTO();
        boardDTO2.setTitle("안녕하세요");
        boardDTO2.setContent("안녕하세요 게시판에 온 것을 환영합니다.");
        boardDTO2.setMember(member2);

        // when
        memberService.join(member1);

        boardService.register(boardDTO1);

        memberService.join(member2);

        boardService.register(boardDTO2);

        List<Board> boards = boardService.findBoards();

        // then
        assertThat(boards.size()).isEqualTo(2);
    }

    @Test
    public void testUpdate() {

        // given
        Member member1 = new Member("나신입", "나신입@gmail.com", "5678");

        memberService.join(member1);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("안녕하세요");
        boardDTO.setContent("안녕하세요 게시판에 온 것을 환영합니다.");
        boardDTO.setMember(member1);

        Long boardId = boardService.register(boardDTO);

        // when
        BoardDTO updateBoard = new BoardDTO();
        updateBoard.setBno(boardId);
        updateBoard.setTitle("변경된 제목");
        updateBoard.setContent("변경된 내용");

        Long updatedBoardId = boardService.updateBoard(updateBoard);

        BoardDTO findBoard = boardService.findOne(updatedBoardId);

        // then
        assertThat(findBoard.getTitle()).isEqualTo(updateBoard.getTitle());
        assertThat(findBoard.getContent()).isEqualTo(updateBoard.getContent());

    }

    @Test
    public void testDelete(){

        // given
        Member member = new Member("나신입", "나신입@gmail.com", "5678");

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("나신입의 게시물 제목");
        boardDTO.setContent("나신입의 게시물 내용");
        boardDTO.setMember(member);

        // when

        memberService.join(member);
        Long boardId = boardService.register(boardDTO);

        boardService.deleteBoard(boardId);

        // then
        assertThat(boardService.findBoards().size()).isEqualTo(0);

    }

//    @Test
//    public void testList(){
//
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .keyword("1")
//                .build();
//
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//
//        log.info(responseDTO);
//
//        assertThat(responseDTO.getSize()).isEqualTo(10);
//        assertThat(responseDTO.getDtoList()).extracting("title")
//                .containsExactly("Sample Title...100", "Sample Title...91",
//                        "Sample Title...81", "Sample Title...71", "Sample Title...61", "Sample Title...51",
//                        "Sample Title...41", "Sample Title...31", "Sample Title...21", "Sample Title...19");
//
//    }

}
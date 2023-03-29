package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.domain.Role;
import org.zerock.b01.dto.BoardDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
@Transactional
public class ReplyServiceTests {

    @Autowired
    MemberService memberService;

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyService replyService;

    @Test
    public void reply_register() {
        // given
        Member member = new Member("김신입", "김신입@naver.com", "12345", Role.USER);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("김신입의 게시물 제목");
        boardDTO.setContent("김신입의 게시물 내용");
        boardDTO.setMember(member);

        Board board = Board.toSaveEntity(boardDTO);

        memberService.join(member);

        Long boardId = boardService.register(board);

        // when
        Board findBoard = boardService.findOne(boardId);

        Reply reply = new Reply(findBoard, "김신입님 안녕하세요?");

        replyService.register(reply);

        List<Reply> replies = replyService.findReplies(boardId);

        // then
        assertThat(replies).extracting("replyContent").containsExactly("김신입님 안녕하세요?");

    }

    @Test
    public void reply_update() {
        // given
        Member member = new Member("김신입", "김신입@naver.com", "12345", Role.USER);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("김신입의 게시물 제목");
        boardDTO.setContent("김신입의 게시물 내용");
        boardDTO.setMember(member);

        Board board = Board.toSaveEntity(boardDTO);

        memberService.join(member);

        Long boardId = boardService.register(board);

        // when
        Board findBoard = boardService.findOne(boardId);

        Reply reply = new Reply(findBoard, "김신입님 안녕하세요?");

        replyService.register(reply);

        List<Reply> replies = replyService.findReplies(boardId);

        replies.get(0).change("김신입님 안녕안하세요?");

        // then
        assertThat(replies.get(0).getReplyContent()).isEqualTo("김신입님 안녕안하세요?");
    }


}
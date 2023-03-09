package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.Reply;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyService replyService;

    @Test
    public void reply_register() {
        // given
        Member member = new Member("트레이서", "trace@naver.com", "trace12#$");
        Board board = new Board("오버워치 게임에 관한 제목", "오버워치 게임은 ....", member);

        Reply reply1 = new Reply(board, "오버워치는 재밌어요!!");
        Reply reply2 = new Reply(board, "그님티");
        Reply reply3 = new Reply(board, "오버워치 랭커입니다 답변 주세요");

        // when
        Long board_id = boardService.register(board);
        replyService.register(reply1);
        replyService.register(reply2);
        replyService.register(reply3);

        List<Reply> replies = replyService.findReplies(board_id);

        // then
        assertThat(replies.size()).isEqualTo(3);

    }

    @Test
    public void reply_update() {
        // given
        Member member = new Member("트레이서", "trace@naver.com", "trace12#$");
        Board board = new Board("오버워치 게임에 관한 제목", "오버워치 게임은 ....", member);

        Reply reply1 = new Reply(board, "오버워치는 재밌어요!!");
        Reply reply2 = new Reply(board, "그님티");
        Reply reply3 = new Reply(board, "오버워치 랭커입니다 답변 주세요");

        // when
        Long board_id = boardService.register(board);
        replyService.register(reply1);
        replyService.register(reply2);
        replyService.register(reply3);

        List<Reply> replies = replyService.findReplies(board_id);

        replies.get(0).change("롤은 재밌어요!!!");
        replies.get(1).change("티어는 중요치 않아요");
        replies.get(2).change("롤 랭커입니다.");


        // then
        assertThat(replies.get(0).getReplyContent()).isEqualTo("롤은 재밌어요!!!");
        assertThat(replies.get(1).getReplyContent()).isEqualTo("티어는 중요치 않아요");
        assertThat(replies.get(2).getReplyContent()).isEqualTo("롤 랭커입니다.");
    }


}
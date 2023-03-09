package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public Long register(Reply reply){
        replyRepository.save(reply);
        return reply.getId();
    }

    public List<Reply> findReplies(Long board_id){
        return replyRepository.findReplyByBoardId(board_id);
    }




}

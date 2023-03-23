package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.zerock.b01.dto.BoardDTO;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(exclude = "member")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "board_id ")
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public void createBoard (String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.createBoard(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getMember());
        return board;
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

}

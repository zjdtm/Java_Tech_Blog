package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "board")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String replyContent;

    @Column(name = "reply_like")
    private int like;

    public Reply(Board board, String replyContent) {
        this.board = board;
        this.replyContent = replyContent;
        this.like = 0;
    }

    public void change(String replyContent){
        this.replyContent = replyContent;
    }

    public void like_press(){
        like += 1;
    }

    public void like_not(){
        like -= 1;
    }


}

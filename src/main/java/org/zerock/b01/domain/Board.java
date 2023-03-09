package org.zerock.b01.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(exclude = "member")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "board_id ")
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(name = "board_like")
    private int like;

    private int views;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void boardLike(){
        like = like + 1;
    }

    public void boardNotLike(){
        like = like - 1;
    }

    public void views(){
        views = views + 1;
    }

}

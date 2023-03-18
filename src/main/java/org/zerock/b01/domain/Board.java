package org.zerock.b01.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(exclude = "member")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id ")
    private Long bno;

    private String title;

    private String content;

    @Column(name = "board_like")
    private int like;

    private int views;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "member_id")
//    private Member member;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void notLike(){
        this.like += 1;
    }

    public void like(){
        this.like -= 1;
    }

    public void views(){
        views = views + 1;
    }

}

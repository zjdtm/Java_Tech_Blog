package org.zerock.b01.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_id ")
    private Long bno;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(name = "board_like")
    private int like;

    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void like_press(){
        like = like + 1;
    }

    public void like_not(){
        like = like - 1;
    }

    public void views(){
        views = views + 1;
    }

}

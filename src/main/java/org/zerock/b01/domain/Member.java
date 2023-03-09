package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private String email;

    private String password;

    public Member(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void change(String nickname, String email, String password){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }




}

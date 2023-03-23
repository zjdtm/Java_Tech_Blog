package org.zerock.b01.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Member;
import org.zerock.b01.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void member_register() {

        // given
        Member member = new Member("겐지", "겐지@naver.com", "겐지123");

        // when
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberService.findOne(savedId));
    }

    @Test
    public void member_duplicate() {

        // given
        Member member1 = new Member("윈스턴", "윈스턴@naver.com", "윈스턴123");
        Member member2 = new Member("윈스턴", "윈스턴@naver.com", "윈스턴123");

        // when
        memberService.join(member1);

        // then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());

    }

    @Test
    public void member_change() {

        // given
        Member member = new Member("윈스턴", "윈스턴@naver.com", "윈스턴123");

        // when
        Long savedId = memberService.join(member);
        Member findMember = memberService.findOne(savedId);

        findMember.change("메이", "메이@naver.com", "메이123");

        // then
        Member updatedMember = memberService.findOne(savedId);
        assertEquals(updatedMember.getNickname(), "메이");
        assertEquals(updatedMember.getEmail(), "메이@naver.com");
        assertEquals(updatedMember.getPassword(), "메이123");


    }

}
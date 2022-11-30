package com.returns.member.service;

import com.returns.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member createMember(Member member) {
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member) {
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId) {
        Member member = new Member(memberId, "abc@abc.com", "aaa", "010-1111-1111");
        return member;
    }

    public List<Member> findMembers() {
        List<Member> members = List.of(
                new Member(1L, "abc@abc.com", "aaa", "010-1111-1111"),
                new Member(2L, "aaa@aaa.com", "bbb", "010-2222-2222"));
        return members;
    }

    public void deleteMember(long memberId) {}
}

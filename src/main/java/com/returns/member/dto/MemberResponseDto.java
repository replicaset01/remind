package com.returns.member.dto;

import com.returns.member.entity.Member;
import com.returns.stamp.Stamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private Long memberId;
    private String email;
    private String name;
    private String phone;
    private Member.MemberStatus memberStatus;

    private Stamp stamp;

    public String getMemberStatus() {
        return memberStatus.getStatus();
    }

    public int getStamp() {
        return stamp.getStampCount();
    }
}

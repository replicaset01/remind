package com.returns.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private Long memberId;
    private String email;
    private String name;
    private String phone;
}

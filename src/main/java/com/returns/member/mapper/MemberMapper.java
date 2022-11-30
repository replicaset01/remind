package com.returns.member.mapper;

import com.returns.member.dto.MemberPatchDto;
import com.returns.member.dto.MemberPostDto;
import com.returns.member.dto.MemberResponseDto;
import com.returns.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    MemberResponseDto memberToMemberResponseDto(Member member);
}

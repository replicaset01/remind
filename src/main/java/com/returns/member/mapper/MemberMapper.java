package com.returns.member.mapper;

import com.returns.member.dto.MemberDto;
import com.returns.member.dto.MemberPatchDto;
import com.returns.member.dto.MemberPostDto;
import com.returns.member.dto.MemberResponseDto;
import com.returns.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);

    @Mapping(source = "member.stamp.stampCount", target = "stampCount")
    @Mapping(source = "member.memberStatus.status", target = "memberStatus")
    MemberDto.Response memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}

package com.returns.member.controller;

import com.returns.member.dto.MemberDto;
import com.returns.member.dto.MemberPatchDto;
import com.returns.member.dto.MemberPostDto;
import com.returns.member.dto.MemberResponseDto;
import com.returns.member.entity.Member;
import com.returns.member.mapper.MemberMapper;
import com.returns.member.service.MemberService;
import com.returns.response.MultiResponseDto;
import com.returns.response.SingleResponseDto;
import com.returns.stamp.Stamp;
import com.returns.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v11/members")
@Validated
public class MemberController {

    private final static String MEMBER_DEFAULT_URL = "/v10/members";

    private final MemberService memberService;
    private final MemberMapper mapper;


    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

//    @PostMapping
//    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
//
//        Member member = memberService.createMember(mapper.memberPostDtoToMember(memberDto));
//        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());
//        return ResponseEntity.created(location).build();
//    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = mapper.memberPostDtoToMember(requestBody);
        member.setStamp(new Stamp()); // homework solution 추가

        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(createdMember)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @RequestBody @Valid MemberDto.Patch requestBody) {
        requestBody.setMemberId(memberId);

        Member member = memberService.updateMemberV2(mapper.memberPatchDtoToMember(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {

        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)) ,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {

        //i 서비스에서 컨트롤러에게 줄때 -1을 해줘야함
        Page<Member> pageMembers = memberService.findMembers(page -1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.membersToMemberResponseDtos(members), pageMembers), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

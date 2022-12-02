package com.returns.member.repository;

import com.returns.member.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


/**
 * Spring Data JDBC의 Repository를 이용해 페이지네이션 처리된 데이터를 조회하기 위해서는
 * 페이지네이션 기능을 제공하는 PagingAndSortingRepository 을 이용하면 됩니다.
 */

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}

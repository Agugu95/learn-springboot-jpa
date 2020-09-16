package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 인터페이스만 있다면 JPA가 실행 시점에 구현체를 로
    // JPQL로 변환, select m from member m where m.name := name
    List<Member> findByName(String name);
}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// JPA의 모든 로직은 트랜잭션 안에서 실행 되어야 한다.
// readOnly 걸면 조회 최적화, 더티체킹 안하고 뭐 그럼 읽기가 더 많다면 클래스 레벨 트랜잭션 걸어도 됨
@RequiredArgsConstructor // 롬복을 통해 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository; // 생성 시점 외 변경할 필요가 없기에 final

    // 회원 가입
    @Transactional // 읽기 전용 아님
    public Long join(Member member) {
        if (member == null) {
            throw new IllegalStateException("회원 정보가 null입니다.");
        }
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재 하는 회원");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name, Address address) {
        Member member = memberRepository.findById(id).get(); // 영속 상태로 만들
        // 변경 감지
        member.setName(name);
        member.setAddress(address);
    }
}

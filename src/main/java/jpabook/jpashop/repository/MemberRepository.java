package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 어노테이션 등록 시 자동으로 스프링 빈으로 관리
@RequiredArgsConstructor
public class MemberRepository{

    // 의존성 주입 어노테이션
    private final EntityManager em; // 롬복을 통해 생성장 생성

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) { // 단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { // 리스트 조
        return em.createQuery("select m from Member m", Member.class) // JPQL
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
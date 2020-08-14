package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository { // 래포지토리는 DAO 역할을 수행, 엔티티 매니저 사용

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    // findOne
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    /*// findAll
    public List<Order> findAll(OrderSearch orderSearch) {
        return
    }
     */
}

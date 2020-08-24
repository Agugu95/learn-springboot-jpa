package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository; // 실제 접근 할 DAO
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 아이디를 통한 엔티티 가져오기
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress()); // 회원 주소를 배송 주소로 설정

        // 주문 상품 설정, 정적 팩토리 메소드, protected로 생성 패턴 보호
        OrderItem orderItem = OrderItem.createOrder(item, item.getPrice(), count);

        // 주문 생성, 정적 팩토리 메소드, protected로 생성 패턴 보호
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장, 캐스케이드, flush 시점
        orderRepository.save(order);

        return order.getId();
    }

    // Cancel

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) { // id만 넘어 오기 때문에 주문을 찾아야 함
        // 조회
        Order order = orderRepository.findOne(orderId);

        // 취소
        order.cancel(); // JPA의 더티체킹으로 인해 간단한 코드
    }

    // Search
    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }
}

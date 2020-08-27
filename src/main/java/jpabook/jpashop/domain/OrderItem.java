package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // 생성에 따른 초기화가 필요한 필드들
    private int orderPrice; // 실제 주문 가격
    private int count; // 주문 수량

    // == 생성 메소드 == //
    // 실제 주문 시 할인 등에 의해 가격이 변동될 수 있기에 별도의 orderPrice를 둠
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 수량 감소
        return orderItem;
    }

    // == 비즈니스 로직 == //
    public void cancel() { // 재고 수량을 되돌리기 위한 로직
        getItem().addStock(count);
    }

    // == 조회 로직 == //

    /**
     *
     * @return 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount(); // lombok 덕에 필드에 그냥 getter 사용 가능
    }
}

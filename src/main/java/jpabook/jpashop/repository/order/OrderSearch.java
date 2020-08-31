package jpabook.jpashop.repository.order;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName; // 이름
    private OrderStatus orderStatus; // ORDER, CANCEL
    
}

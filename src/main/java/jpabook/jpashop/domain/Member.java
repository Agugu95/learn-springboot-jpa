package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;

    @Embedded // 내장 타입 임베딩
    private Address address;

    @OneToMany (mappedBy = "member") // 읽기 전용
    private List<Order> orders = new ArrayList<>();


}
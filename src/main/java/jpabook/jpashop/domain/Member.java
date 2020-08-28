package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @Embedded // 내장 타입 임베딩
    private Address address;

    @JsonIgnore
    @OneToMany (mappedBy = "member") // 읽기 전용
    private List<Order> orders = new ArrayList<>();

}
package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //  JPA 내장 타입 설정
@Getter @Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}

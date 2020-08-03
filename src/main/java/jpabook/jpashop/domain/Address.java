package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //  JPA 내장 타입 설정
@Getter // 값 타입은 Immutable 해야 함, setter를 아예 제공 안함
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { // 변경 불가능한 제약 걸기, JPA 기본 제약
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

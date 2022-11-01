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

    private String name;

    @Embedded
    private Address address;

    /**
     * * 컬랙션을 가급적 수정하지 말아라!
     * * 필드에서 바로 초기화 하는것이 좋음!!
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}

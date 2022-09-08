package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // final : 반드시 값이 있어야한다!!
    private final MemberRepository memberRepository;
    private final  DiscountPolicy discountPolicy;
    // 생성자 의존 : 불변&필수 일때 주로 사용
    // 생성자가 하나일때는 @Autowired 생략 가능
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

/*
    // 수정자 주임 : setter 사용
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
*/
/*

    // 필드 주입 : private 일때 가능! -> 코드는 간결하나 외부엫서 변경이 불가능하다!! 즉, 사용하지말자! (테스트 코드에선 사용가능)
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
*/
/*
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 일반 메서드 주입 : 한번에 여러 필드 주입 받을 수 있다. - 거의 사용하지 않음
    @Autowired
    public void init(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

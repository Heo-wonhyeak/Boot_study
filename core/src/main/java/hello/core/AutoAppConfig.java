package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
)
public class AutoAppConfig {

/*

    // 필드주입은 이렇게 사용할 수 있으나, 잘 사용하지 않는다.
    @Autowired MemberRepository memberRepository;
    @Autowired DiscountPolicy discountPolicy;

    @Bean
    OrderService orderService() {
        return new OrderServiceImpl(memberRepository,discountPolicy);
    }
*/
/*

    @Bean(name = "memoryMemberRepository")
    MemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
*/

}

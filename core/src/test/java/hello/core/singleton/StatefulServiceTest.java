package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10,000원 주문
        int userAPrice = statefulService1.order("userA",10000);
        // ThreadB : B 사용자가 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
        assertThat(userAPrice).isNotEqualTo(userBPrice);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return  new StatefulService();
        }
    }

}
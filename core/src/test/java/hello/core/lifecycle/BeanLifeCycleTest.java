package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    - 메서드 이름을 자유롭게 사용 가능
    - 스프링 빈이 스프링 코드에 의존하지 않는다
    ** 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠수 없는 외부라이브러리에도 초기화 종료 메서드 적용가능
*/
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // 기본 ApplicationContext 는 클로즈 할 일이 많지 않기 때문에 하위에 있는 ConfigurableApplicationContext를 사용해야한다
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /*
            @Bean 으로 등록시 destroyMethod 의 특별한 기능
            - close 나 shutdown 으로 설정한 메서드는 자동으로 종료 메서드 등록되어짐
            - '(inferred)' 추론 기능이 default 값으로 지정됨
        */
        @Bean /*(initMethod = "init",destroyMethod = "close")*/
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

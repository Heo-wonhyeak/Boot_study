package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자를 호출 , url = "+url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : "+url);
    }

    public void call(String message) {
        System.out.println("call : "+url+" message = "+message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " +url);
    }

    /*
         @PostConstruct,@PreDestroy 이 가장 최근의 방법
         가장 권장되는 방법이기도 하다
         - JSR-250 자바 표준
         - ComponentScan 과도 잘 어울림
         - 유일한 단점은 외부 라이브러리에 적용하지 못함
            -> 이 경우에는 바로 직전의 @Bean(initMethod = "init",destroyMethod = "close") 을 사용!
    */

    //시작시 호출
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");

    }
    //종료시 호출
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

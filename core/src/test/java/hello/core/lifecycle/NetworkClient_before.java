package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
    초기화 소멸 인터페이스
    - 스프링 전용 인터페이스에 의존
    - 초기화 소멸 메서드의 이름을 변경할 수 없다
    - 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다
    * 2003년에 나온 방식으로 현재는 더 좋은방법이 있어 사용하지 않는다
*/
public class NetworkClient_before implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient_before() {
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

    //시작시 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");

    }

    //종료시 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}

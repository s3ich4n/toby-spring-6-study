package s3ich4n.spring6;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s3ich4n.spring6.data.OrderRepository;
import s3ich4n.spring6.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        System.out.println(order);

        // 오만가지 예외가 이제 생길 수 있다.
        //
        // 버그일 수도 있지만, 진짜 주문이 겹쳐서 에러가 나면? 서비스 정책에 따라 재시도 할 수도 있지 않을까?
        // 엔진따라 다른 에러가 나면?
        // JDBC나 MyBatis를 쓰거나 해서 각각 Exception이 나면?
        //
        // 결국 변화에 능동적으로 대응할 수 있는 코드를 짜야함 -> 확장성
        Order order2 = new Order("100", BigDecimal.TEN);
        repository.save(order2);
    }
}

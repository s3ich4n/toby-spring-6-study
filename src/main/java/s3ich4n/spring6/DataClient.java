package s3ich4n.spring6;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import s3ich4n.spring6.order.Order;
import s3ich4n.spring6.order.OrderService;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = beanFactory.getBean(OrderService.class);

        Order order = service.createOrder("0100", BigDecimal.TEN);
        System.out.println(order);
    }
}

package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import s3ich4n.spring6.data.OrderRepository;
import s3ich4n.spring6.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(JpaTransactionManager jpaTransactionManager) {
        return new OrderService(orderRepository(), jpaTransactionManager);
    }

    // 얘는 팩토리 빈이라 파라미터를 다르게 처리해야댐
    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository();
    }
}

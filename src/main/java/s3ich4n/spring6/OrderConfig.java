package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import s3ich4n.spring6.data.JdbcOrderRepository;
import s3ich4n.spring6.data.JpaOrderRepository;
import s3ich4n.spring6.order.OrderRepository;
import s3ich4n.spring6.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderService orderService(JpaTransactionManager jpaTransactionManager) {
        return new OrderService(orderRepository(), jpaTransactionManager);
    }

    // 구성정보는 이렇게 깔끔하게 치워줄 수 있다
    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepository();
    }
}

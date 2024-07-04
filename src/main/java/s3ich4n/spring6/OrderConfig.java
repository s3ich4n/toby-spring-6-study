package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import s3ich4n.spring6.data.JdbcOrderRepository;
import s3ich4n.spring6.order.OrderRepository;
import s3ich4n.spring6.order.OrderService;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    // 구성정보는 이렇게 깔끔하게 치워줄 수 있다
    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    // OrderRepository를 생성자 파라미터로 바로 넘겨주는 방법
    // 굳이 메소드를 호출하면서 DataSource를 또 직접 넘겨줄 필요가 없어짐
    @Bean
    public OrderService orderService(
            PlatformTransactionManager jpaTransactionManager,
            OrderRepository orderRepository
    ) {
        return new OrderService(orderRepository, jpaTransactionManager);
    }
}

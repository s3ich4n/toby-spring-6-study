package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import s3ich4n.spring6.data.JdbcOrderRepository;
import s3ich4n.spring6.order.OrderRepository;
import s3ich4n.spring6.order.OrderService;
import s3ich4n.spring6.order.OrderServiceImpl;
import s3ich4n.spring6.order.OrderServiceTxProxy;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    // 리턴타입은 업캐스팅
    // 실제 쓰는거는 다운캐스팅
    @Bean
    public OrderService orderService(
            PlatformTransactionManager transactionManager,
            OrderRepository orderRepository
    ) {
        return new OrderServiceTxProxy(
                new OrderServiceImpl(orderRepository),
                transactionManager
        );
    }
}

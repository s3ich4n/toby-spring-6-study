package s3ich4n.spring6.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository jpaOrderRepository;
    private final JpaTransactionManager transactionManager;

    public OrderService(OrderRepository jpaOrderRepository, JpaTransactionManager transactionManager) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        return new TransactionTemplate(transactionManager).execute(status -> {
            this.jpaOrderRepository.save(order);

            return order;
        });
    }
}

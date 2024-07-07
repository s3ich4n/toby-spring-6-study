package s3ich4n.spring6.order;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository jpaOrderRepository;

    public OrderServiceImpl(OrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        this.jpaOrderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return reqs.stream().map(req -> createOrder(req.no(), req.total())).toList();
    }
}

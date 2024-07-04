package s3ich4n.spring6.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import s3ich4n.spring6.order.Order;
import s3ich4n.spring6.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }
}

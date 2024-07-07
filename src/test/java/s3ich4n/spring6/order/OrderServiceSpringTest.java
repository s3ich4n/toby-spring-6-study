package s3ich4n.spring6.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import s3ich4n.spring6.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
        // auto 타입!!!!!
        //  테스트에서만큼은 뭐 넣는지도 알고 타입 제대로 지정하기 커찮으니까
        //  컴파일 레벨에서 알아서 하게 하자
        var order = orderService.createOrder("0100", BigDecimal.TEN);

        Assertions.assertThat(order.getId()).isGreaterThan(0L);
    }

    @Test
    void createOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0200", BigDecimal.TWO),
                new OrderReq("0201", BigDecimal.ONE)
        );

        var orders = orderService.createOrders(orderReqs);

        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order -> Assertions.assertThat(order.getId()).isGreaterThan(0L));
    }

    @Test
    void createDuplicatedOrders() {
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.TWO),
                new OrderReq("0300", BigDecimal.ONE)
        );

        // 이거는 했는데, 앞의것도 제대로 들어가느냐? 그건 검증이 안됨.
        Assertions.assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no = '0300'").query(Long.class).single();
        Assertions.assertThat(count).isEqualTo(0L);
    }
}

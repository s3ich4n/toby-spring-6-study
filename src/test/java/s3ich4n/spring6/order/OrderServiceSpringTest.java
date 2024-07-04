package s3ich4n.spring6.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import s3ich4n.spring6.OrderConfig;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired
    OrderService orderService;

    @Test
    void createOrder() {
        // auto 타입!!!!!
        //  테스트에서만큼은 뭐 넣는지도 알고 타입 제대로 지정하기 커찮으니까
        //  컴파일 레벨에서 알아서 하게 하자
        var order = orderService.createOrder("0100", BigDecimal.TEN);

        Assertions.assertThat(order.getId()).isGreaterThan(0L);
    }
}

package s3ich4n.spring6;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        // 이 코드만 바꾸면 변화에 보다 더 능동적으로 대처가능
        // 필요한 구현체를 상속받아서 접근했으니까
        // PaymentService paymentService = new WebApiExRatePaymentService();
        PaymentService paymentService = new SimpleExRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}

package s3ich4n.spring6;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        // 여기에 쓰고싶은 생성자를 넣어주기만 하면 된다
        // "클라이언트" 코드가 멋대로 갖다 쓰도록 하는게 핵심이다.
        // "프로바이더"의 코드가 변화에 계속 대응하는게 아니라!
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}

package s3ich4n.spring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    private final ExRateProvider exRateProvider;

    // 관심사를 완전히 분리했다.
    //      더이상 프로바이더 코드는 뭘 쓰는지 알 바 없음.
    //      이 것이 핵심이다.
    public PaymentService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 쓰는 사람은 그런거 모르고 걍 쓰면 된다
        //      근데 이 "쓰는 사람" 은 아직도 "프로바이더" 다.
        //      결국 이 "그런거 모르고 써도 된다" 를 클라이언트 레벨까지 끌어올리는게 핵심이다
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}

package s3ich4n.spring6.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드의 요구사항 충족 확인")
    void convertedAmount() throws IOException {
        /**
         * 이런 검증을 하면?
         *
         * 1. 소수점 이하 자릿수 검증이 가능해짐
         * 2. 큰 금액의 환율을 검증할 수 있음 (코드레벨에서)
         */
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1_000), valueOf(10_000));
        testAmount(valueOf(3_000), valueOf(30_000));

        // 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}

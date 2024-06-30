package s3ich4n.spring6.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import s3ich4n.spring6.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드의 요구사항 충족 확인")
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // assert - 환율정보를 가져옴
        assertThat(payment.getExRate()).isNotNull();

        // 원화 환산금액을 계산함
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}

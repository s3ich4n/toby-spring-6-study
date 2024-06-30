package s3ich4n.spring6.exrate;

import org.springframework.stereotype.Component;
import s3ich4n.spring6.payment.ExRateProvider;

import java.math.BigDecimal;

/**
 * 메타 프로그래밍 기법으로 스프링의 BeanFactory 가 얘를 찾을 수 있다
 */
@Component
public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IllegalArgumentException {
        if (currency.equals("USD")) return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("Unsupported currency. " + currency);
    }
}

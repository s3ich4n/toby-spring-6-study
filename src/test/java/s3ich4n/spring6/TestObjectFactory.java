package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s3ich4n.spring6.exrate.CachedExRateProvider;
import s3ich4n.spring6.payment.ExRateProvider;
import s3ich4n.spring6.payment.ExRateProviderStub;
import s3ich4n.spring6.payment.PaymentService;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}

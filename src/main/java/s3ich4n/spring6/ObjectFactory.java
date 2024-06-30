package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s3ich4n.spring6.exrate.CachedExRateProvider;
import s3ich4n.spring6.payment.ExRateProvider;
import s3ich4n.spring6.exrate.WebApiExRateProvider;
import s3ich4n.spring6.payment.PaymentService;

@Configuration
public class ObjectFactory {

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
        return new WebApiExRateProvider();
    }
}

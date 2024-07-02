package s3ich4n.spring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import s3ich4n.spring6.api.ApiTemplate;
import s3ich4n.spring6.api.ErApiExRateExtractor;
import s3ich4n.spring6.api.SimpleApiExecutor;
import s3ich4n.spring6.exrate.CachedExRateProvider;
import s3ich4n.spring6.payment.ExRateProvider;
import s3ich4n.spring6.exrate.WebApiExRateProvider;
import s3ich4n.spring6.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        // 어떤 전략을 넣느냐에 따라 대응이 가능하다.
        //      중요: 그 흐름은 템플릿-콜백과 다를게 없다
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() { return Clock.systemDefaultZone(); }
}

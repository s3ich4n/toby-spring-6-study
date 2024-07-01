package s3ich4n.spring6.exrate;

import java.math.BigDecimal;

import s3ich4n.spring6.api.ApiTemplate;
import s3ich4n.spring6.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url);
    }
}

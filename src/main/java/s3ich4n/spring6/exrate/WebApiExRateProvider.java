package s3ich4n.spring6.exrate;

import java.math.BigDecimal;

import s3ich4n.spring6.api.ApiTemplate;
import s3ich4n.spring6.api.ErApiExRateExtractor;
import s3ich4n.spring6.api.HttpClientApiExecutor;
import s3ich4n.spring6.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();    // thread-safe 하게

    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}

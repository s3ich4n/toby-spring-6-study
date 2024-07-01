package s3ich4n.spring6.exrate;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import s3ich4n.spring6.api.ApiTemplate;
import s3ich4n.spring6.api.ErApiExRateExtractor;
import s3ich4n.spring6.api.SimpleApiExecutor;
import s3ich4n.spring6.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    ApiTemplate apiTemplate = new ApiTemplate();    // thread-safe 하게

    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, uri -> {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            try (HttpClient client = HttpClient.newBuilder().build()) {
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, new ErApiExRateExtractor());
    }
}

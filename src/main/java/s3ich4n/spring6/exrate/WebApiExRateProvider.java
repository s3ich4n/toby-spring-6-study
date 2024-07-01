package s3ich4n.spring6.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import s3ich4n.spring6.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

    // exception 처리가 너무 단호하다. 그걸 다 쳐냈다
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            // 얘는 AutoCloseable 이라 가능함
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = br.lines().collect(Collectors.joining());
            };

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = null;
        try {
            exRateData = mapper.readValue(response, ExRateData.class);
            return exRateData.rates().get("KRW");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

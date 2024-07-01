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

        return runApiForExRate(url);
    }

    private static BigDecimal runApiForExRate(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response = null;

        try {
            // 서비스가 종료되면?
            // 비동기로 콜할거면?
            response = executeApi(uri);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // 다른데 콜해서 파싱법이 바뀌면?
            // 다른 성능좋은 라이브러리를 쓰면?
            return extractExRate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get("KRW");
    }

    private static String executeApi(URI uri) throws IOException {
        String response;
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = br.lines().collect(Collectors.joining());
        }
        ;
        return response;
    }
}

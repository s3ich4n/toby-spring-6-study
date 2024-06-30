package s3ich4n.spring6.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import s3ich4n.spring6.payment.ExRateProvider;

/**
 * 메타 프로그래밍 기법으로 스프링의 BeanFactory 가 얘를 찾을 수 있다
 */
@Component
public class WebApiExRateProvider implements ExRateProvider {

    public BigDecimal getExRate(String currency)
            throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();
        
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);

        System.out.println("API ExRate: " + exRateData.rates().get("KRW"));

        return exRateData.rates().get("KRW");
    }
}

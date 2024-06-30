package s3ich4n.spring6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebApiExRatePaymentService extends PaymentService{

    @Override
    public BigDecimal getKRWExRate(String currency)
            throws MalformedURLException, IOException, JsonProcessingException, JsonMappingException {
                /**
         * 환율 가져오기
         *      E,g., https://open.er-api.com/v6/latest/USD
         * 금액 계산
         * 유효시간 계산
         */
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();
        
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        BigDecimal exRate = exRateData.rates().get("KRW");
        return exRate;
    }
    
}

package s3ich4n.spring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import s3ich4n.spring6.exrate.ExRateData;

import java.math.BigDecimal;

public class ErApiExRateExtractor implements ExRateExecutor {
    @Override
    public BigDecimal extract(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get("KRW");
    }
}

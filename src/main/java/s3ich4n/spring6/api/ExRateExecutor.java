package s3ich4n.spring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExRateExecutor {
    BigDecimal extract(String response) throws JsonProcessingException;
}

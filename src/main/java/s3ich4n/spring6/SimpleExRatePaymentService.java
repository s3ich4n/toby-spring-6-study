package s3ich4n.spring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SimpleExRatePaymentService extends PaymentService{

    @Override
    public BigDecimal getKRWExRate(String currency)
            throws MalformedURLException, IOException, JsonProcessingException, JsonMappingException {
        if (currency.equals("USD")) return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("Unsupported currency. " + currency);
    }
    
}

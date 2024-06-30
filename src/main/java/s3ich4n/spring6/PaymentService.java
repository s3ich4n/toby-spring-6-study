package s3ich4n.spring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

abstract public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = getKRWExRate(currency);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    /**
     * @param currency
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    abstract public BigDecimal getKRWExRate(String currency)
            throws MalformedURLException, IOException, JsonProcessingException, JsonMappingException;
}

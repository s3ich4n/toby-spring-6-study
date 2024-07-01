package s3ich4n.spring6.exrate;

import s3ich4n.spring6.payment.ExRateProvider;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IllegalArgumentException {
        if (currency.equals("USD")) return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("Unsupported currency. " + currency);
    }
}

package s3ich4n.spring6.order;

import java.math.BigDecimal;

public record OrderReq(String no, BigDecimal total) {
}

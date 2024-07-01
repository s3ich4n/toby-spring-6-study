package s3ich4n.spring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExRateExecutor exRateExecutor;

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExecutor = new ErApiExRateExtractor();
    }

    public BigDecimal getExRate(String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExecutor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExecutor exRateExecutor) {
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
            response = apiExecutor.execute(uri);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // 다른데 콜해서 파싱법이 바뀌면?
            // 다른 성능좋은 라이브러리를 쓰면?
            return exRateExecutor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

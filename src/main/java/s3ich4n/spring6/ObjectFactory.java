package s3ich4n.spring6;

public class ObjectFactory {

    // 각각의 오브젝트를 만드는 두 메소드를 분리함
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}

package k0.junit5.order;

public class OrderBizException extends Exception {

    public static final String FAIL_TO_CREATE_AN_ORDER = "Fail to create an order.";
    public static final String FAIL_TO_CANCEL_AN_ORDER_WITH_NULL_ORDER_ID =
            "Fail to cancel an order with null order ID.";
    public static final String FAIL_TO_CANCEL_AN_ORDER = "Fail to cancel an order.";

    public OrderBizException(String message) {
        super(message);
    }

    public OrderBizException(String message, Throwable cause) {
        super(message, cause);
    }
}

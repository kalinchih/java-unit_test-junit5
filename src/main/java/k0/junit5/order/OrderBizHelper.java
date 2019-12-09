package k0.junit5.order;

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public class OrderBizHelper {

    private OrderDbUtil orderDbUtil;

    public OrderBizHelper(OrderDbUtil orderDbUtil) {
        this.orderDbUtil = orderDbUtil;
    }

    private String newOrderId() {
        return String.format("%s.%s", Instant.now().toEpochMilli(), UUID.randomUUID().toString());
    }

    public String placeOrder(Order order) throws OrderBizException {
        try {
            order.setId(newOrderId());
            return orderDbUtil.createOrder(order);
        } catch (SQLException e) {
            throw new OrderBizException(OrderBizException.FAIL_TO_CREATE_AN_ORDER, e);
        }
    }

    public void cancelOrder(String orderId) throws OrderBizException {
        if (orderId == null) {
            throw new OrderBizException(
                    OrderBizException.FAIL_TO_CANCEL_AN_ORDER_WITH_NULL_ORDER_ID);
        }
        try {
            orderDbUtil.setOrderStatusCancel(orderId);
        } catch (SQLException e) {
            throw new OrderBizException(OrderBizException.FAIL_TO_CANCEL_AN_ORDER, e);
        }
    }
}

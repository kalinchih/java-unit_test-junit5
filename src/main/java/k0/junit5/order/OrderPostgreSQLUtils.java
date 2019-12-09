package k0.junit5.order;

import java.sql.SQLException;

public class OrderPostgreSQLUtils extends OrderDbUtil {

    @Override
    String createOrder(Order order) throws SQLException {
        return order.getId();
    }

    @Override
    void setOrderStatusCancel(String orderId) throws SQLException {}
}

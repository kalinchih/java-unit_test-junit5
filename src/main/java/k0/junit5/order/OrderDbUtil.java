package k0.junit5.order;

import java.sql.SQLException;

public abstract class OrderDbUtil {

    abstract String createOrder(Order order) throws SQLException;

    abstract void setOrderStatusCancel(String orderId) throws SQLException;
}

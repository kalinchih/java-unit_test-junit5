package k0.junit5.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/** Mockito: practice mock, stub, dummy, verify */
public class OrderBizHelper_PostgreSQLUtil_Test {

    @Mock private OrderDbUtil orderDbUtil;
    private OrderBizHelper orderBizHelper;
    @Spy private List<OrderLineItem> orderLineItems;

    @BeforeEach
    public void setUp() {
        this.orderDbUtil = new OrderPostgreSQLUtils();
        this.orderLineItems = new ArrayList<>();
        // *** MockitoAnnotations MUST BEHIND after mock object.
        // Otherwise throw `org.mockito.exceptions.misusing.MissingMethodInvocationException`.
        MockitoAnnotations.initMocks(this);
        // *** REMIND objects dependency. OrderBizHelper MUST be initialized after Mock OrderDbUtil.
        this.orderBizHelper = new OrderBizHelper(orderDbUtil);
    }

    @AfterEach
    public void tearDown() {
        this.orderDbUtil = null;
        this.orderLineItems = null;
        this.orderBizHelper = null;
    }

    @Test
    public void placeOrder_success() throws SQLException, OrderBizException {
        String expectedResult = "order_id";
        // A stub fakes a return value to the createOrder() of the @Mock OrderDbUtil.
        // Leverage any(Order.class) to create a dummy Order object
        when(this.orderDbUtil.createOrder(any(Order.class))).thenReturn(expectedResult);
        // *** NOT to use any() to execute method. any() does not work in mockito 3+ version.
        Object actualResult = this.orderBizHelper.placeOrder(new Order(null));
        Assertions.assertEquals(expectedResult, actualResult);
        // Verify the mock method invocation times
        verify(this.orderDbUtil, times(1)).createOrder(any(Order.class));
    }

    @Test
    public void placeOrder_fail_to_create_an_order() throws SQLException {
        OrderBizException expectedResult =
                new OrderBizException(
                        OrderBizException.FAIL_TO_CREATE_AN_ORDER, new SQLException());
        when(this.orderDbUtil.createOrder(any(Order.class))).thenThrow(new SQLException());
        try {
            Object actualResult = this.orderBizHelper.placeOrder(new Order(null));
        } catch (Exception e) {
            // Assert the exception message
            Assertions.assertEquals(expectedResult.getClass(), e.getClass());
            Assertions.assertEquals(OrderBizException.FAIL_TO_CREATE_AN_ORDER, e.getMessage());
            verify(this.orderDbUtil, times(1)).createOrder(any(Order.class));
        }
    }

    @Test
    public void cancelOrder_success() throws SQLException, OrderBizException {
        // stub void setOrderStatusCancel()
        doNothing().when(orderDbUtil).setOrderStatusCancel(anyString());
        this.orderBizHelper.cancelOrder("order_id");
        verify(this.orderDbUtil, times(1)).setOrderStatusCancel(any(String.class));
    }

    @Test
    public void cancelOrder_fail_to_cancel_an_order_with_null_order_id() throws SQLException {
        doNothing().when(orderDbUtil).setOrderStatusCancel(any(String.class));
        try {
            this.orderBizHelper.cancelOrder(null);
        } catch (Exception e) {
            Assertions.assertEquals(OrderBizException.class, e.getClass());
            Assertions.assertEquals(
                    OrderBizException.FAIL_TO_CANCEL_AN_ORDER_WITH_NULL_ORDER_ID, e.getMessage());
        }
    }

    @Test
    public void cancelOrder_fail_to_cancel_an_order() throws SQLException {
        // Throw SQLException from the stub method
        doThrow(SQLException.class).when(orderDbUtil).setOrderStatusCancel(any(String.class));
        try {
            this.orderBizHelper.cancelOrder("order_id");
        } catch (Exception e) {
            Assertions.assertEquals(OrderBizException.class, e.getClass());
            Assertions.assertEquals(OrderBizException.FAIL_TO_CANCEL_AN_ORDER, e.getMessage());
            verify(this.orderDbUtil, times(1)).setOrderStatusCancel(any(String.class));
        }
    }

    @Test
    public void spying_real_object() {
        List<OrderLineItem> orderLineItems = spy(new ArrayList<>());
        OrderLineItem expectedResult = new OrderLineItem();
        expectedResult.setSkuId("SKU_1");
        expectedResult.setAmount(1);
        // *** Call empty List REAL method
        // when(orderLineItems.get(0)).thenReturn(expectedResult);
        // *** MUST use doReturn() for stubbing
        doReturn(expectedResult).when(orderLineItems).get(0);
        System.out.println(orderLineItems.get(0).getSkuId());
    }

    @Test
    public void spying_real_object_2() {
        OrderLineItem expectedResult = new OrderLineItem();
        expectedResult.setSkuId("SKU_1");
        expectedResult.setAmount(1);
        // @Spy private List<OrderLineItem> orderLineItems;
        doReturn(expectedResult).when(this.orderLineItems).get(0);
        System.out.println(orderLineItems.get(0).getSkuId());
    }
}

package k0.junit5.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Order_Test {

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(createOrder(new int[] {1, 3}), 4),
                Arguments.of(createOrder(new int[] {2, 5, 6}), 13),
                Arguments.of(createOrder(new int[] {-1, 1}), 1));
    }

    private static Order createOrder(int... lineItemAmounts) {
        List<OrderLineItem> orderLineItems = new ArrayList<>();
        for (int amount : lineItemAmounts) {
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setAmount(amount);
            orderLineItems.add(orderLineItem);
        }
        return new Order(orderLineItems);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void totalLineItemAmount_success(Order actualOrder, int expectedResult) {
        Assertions.assertEquals(expectedResult, actualOrder.totalLineItemAmount());
    }

    // @CsvFileSource: https://www.baeldung.com/parameterized-tests-junit-5
}

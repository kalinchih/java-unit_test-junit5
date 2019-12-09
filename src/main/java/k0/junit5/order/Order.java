package k0.junit5.order;

import java.util.List;

public class Order {

    private List<OrderLineItem> lineItems;
    private String id;
    private String status;

    public Order(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int totalLineItemAmount() {
        int total = 0;
        for (OrderLineItem orderLineItem : this.lineItems) {
            int orderLineItemAmount = orderLineItem.getAmount();
            if (orderLineItemAmount < 0) {
                orderLineItemAmount = 0;
            }
            total = total + orderLineItemAmount;
        }
        return total;
    }
}

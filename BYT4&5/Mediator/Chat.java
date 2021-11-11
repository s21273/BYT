import java.util.HashMap;
import java.util.Map;

public class Chat implements IChat {

    private Map<String, Customer> customersMap = new HashMap<>();

    @Override
    public void sendMessage(String message, String idCustomer)
    {
        Customer customer = customersMap.get(idCustomer);
        customer.receive(message);
    }

    @Override
    public void addCustomer(Customer customer) {
        this.customersMap.put(customer.getId(), customer);
    }
}
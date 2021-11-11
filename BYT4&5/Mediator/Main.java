public class Main
{
    public static void main(String[] args)
    {
        IChat conversation = new Chat();

        Customer customer1 = new ChatCustomer(conversation,"1", "Customer");
        Customer customer2 = new ChatCustomer(conversation,"2", "Employee");

        conversation.addCustomer(customer1);
        conversation.addCustomer(customer2);

        customer1.send("Hello, I have a question.", "2");
        customer2.send("How can I help you?", "1");
    }
}
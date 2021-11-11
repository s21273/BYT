public interface IChat {
    public void sendMessage(String message, String idCustomer);

    void addCustomer(Customer customer);
}
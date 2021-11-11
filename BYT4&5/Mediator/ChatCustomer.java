public class ChatCustomer extends Customer {

    public ChatCustomer(IChat conversation, String id, String name) {
        super(conversation, id, name);
    }

    @Override
    public void send(String message, String idCustomer) {
        System.out.println(this.getName() + " : Sending Message : " + message);
        getMediator().sendMessage(message, idCustomer);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " : Received Message : " + msg);
    }
}

public abstract class Customer
{
    private IChat mediator;

    private String id;
    private String name;

    public Customer(IChat conversation, String id, String name){
        this.mediator = conversation;
        this.name = name;
        this.id = id;
    }

    public abstract void send(String message, String idCustomer);
    public abstract void receive(String message);

    public IChat getMediator() {
        return mediator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
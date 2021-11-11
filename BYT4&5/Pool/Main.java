public class Main {

    public static void main(String[] args) {

        ObjectPool pool = ObjectPool.getInstance();

        Client client1 = new Client();
        Client client2 = new Client();

        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client1.takeObjectFromPool(pool);
        client2.returnObjectToPool(pool);
        client1.takeObjectFromPool(pool);
        client2.takeObjectFromPool(pool);

        System.out.println();
        System.out.println("number of objects user 1: " + client1.getListSize());
        System.out.println("number of objects user 2: " + client2.getListSize());
        System.out.println("number of objects pool: " + pool.getListSize());
        ObjectPool.printNumber();

    }
}
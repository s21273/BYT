import java.util.ArrayList;
import java.util.List;

public class ObjectPool {

    private static ObjectPool instance = null;

    private static  List<MyObject> myList = null;
    private static int numberOfObjects = 0;


    private ObjectPool() {
        myList = new ArrayList<>();
    }

    public  void addObject(MyObject obj) {
        if(myList.size() < 10) {
            myList.add(obj);
        }

    }

    public  MyObject pullObject() {
        if(myList.isEmpty() && !ObjectLimitReached()) {
            numberOfObjects++;
            return new MyObject();
        } else if (!myList.isEmpty()) {
            MyObject tmp = myList.get(myList.size() - 1);
            myList.remove(myList.size() - 1);
            return tmp;
        } else {
            return null;
        }
    }

    public static ObjectPool getInstance()
    {
        if (instance == null)
            instance = new ObjectPool();
        return instance;
    }

    public static void printNumber() {
        System.out.println("number of objects in the system: " + numberOfObjects);
    }

    public int getListSize() {
        return myList.size();
    }

    public boolean ObjectLimitReached() {
        return numberOfObjects >= 10;

    }
}
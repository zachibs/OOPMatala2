package observer;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------");
        System.out.println("Test1: ");
        GroupAdmin testObject = new GroupAdmin();
        ConcreteMember CM = new ConcreteMember("Adir");

        testObject.register(CM);
        testObject.append("testt");
        System.out.println(CM);

        testObject.insert(1, "bla");
        System.out.println(CM);

        testObject.delete(0,5);
        System.out.println(CM);

        testObject.undo();
        System.out.println(CM);

        testObject.unregister(CM);
    
            
        
        System.out.println("---------------------------------------------------------");
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import observer.GroupAdmin;
import observer.ConcreteMember;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void FootprintAndSizeTests(){
        GroupAdmin testObject = new GroupAdmin();
        ConcreteMember firstMember = new ConcreteMember("Adir");
        ConcreteMember secondMember = new ConcreteMember("Dor");

        
        System.out.println("Footprint");
        logger.info(()->JvmUtilities.objectFootprint(testObject, firstMember, secondMember));
        System.out.println("TotalSize");
        logger.info(()->JvmUtilities.objectTotalSize(testObject, firstMember, secondMember));
        System.out.println("\nRegister members - ");
        testObject.register(firstMember); 
        testObject.register(secondMember);

        logger.info(()->JvmUtilities.objectFootprint(testObject));
        logger.info(()->JvmUtilities.objectTotalSize(testObject));

        testObject.append("abcd"); //notify members

        System.out.println("\nlet's see what happen to the memory after we appended the string 123 ");
        logger.info(()->JvmUtilities.objectFootprint(testObject));
        logger.info(()->JvmUtilities.objectTotalSize(testObject));

        System.out.println("\nNow let's unregister a members and see what's happen to the memory aloccate: ");
        testObject.unregister(secondMember);
        logger.info(()->JvmUtilities.objectFootprint(testObject));
        logger.info(()->JvmUtilities.objectTotalSize(testObject));
        testObject.unregister(secondMember);
        logger.info(()->JvmUtilities.memoryStats(testObject)); 

        System.out.println("Info: ");
        logger.info(() -> JvmUtilities.jvmInfo());

    }

    
    @Test
    void registerTest() {
        GroupAdmin testObject = new GroupAdmin();
        testObject.register(new ConcreteMember("Adir"));
        testObject.register(new ConcreteMember("Dor"));
        testObject.append("string");
        assertEquals("GroupAdmin [situationsGroup= string], [membersList= ConcreteMember [memberName=Adir, str=string] ConcreteMember [memberName=Dor, str=string]", testObject.toString());
    }

    @Test
    void unregisterTest() {
        GroupAdmin testObject = new GroupAdmin();
        ConcreteMember firstMember = new ConcreteMember("Adir");
        ConcreteMember secondMember = new ConcreteMember("Dor");
        testObject.register(firstMember);
        testObject.register(secondMember);
        testObject.append("string");

        testObject.unregister(firstMember);
        testObject.unregister(secondMember);
        assertEquals("GroupAdmin [situationsGroup= string], [membersList=", testObject.toString());
    }

    @Test
    void insertTest() {
        GroupAdmin testObject = new GroupAdmin();
        testObject.register(new ConcreteMember("Adir"));
        testObject.register(new ConcreteMember("Dor"));

        testObject.insert(0,"abcd");
        assertEquals("GroupAdmin [situationsGroup= abcd], [membersList= ConcreteMember [memberName=Adir, str=abcd] ConcreteMember [memberName=Dor, str=abcd]", testObject.toString());
        testObject.insert(-1,"notvalid");
        assertEquals("GroupAdmin [situationsGroup= abcd], [membersList= ConcreteMember [memberName=Adir, str=abcd] ConcreteMember [memberName=Dor, str=abcd]", testObject.toString());
        testObject.insert(3,"bbb");
        assertEquals("GroupAdmin [situationsGroup= abcbbbd], [membersList= ConcreteMember [memberName=Adir, str=abcbbbd] ConcreteMember [memberName=Dor, str=abcbbbd]", testObject.toString());
        testObject.insert(7,"\n");
        assertEquals("GroupAdmin [situationsGroup= abcbbbd\n], [membersList= ConcreteMember [memberName=Adir, str=abcbbbd\n] ConcreteMember [memberName=Dor, str=abcbbbd\n]", testObject.toString());
        testObject.insert(8,null);
        assertEquals("GroupAdmin [situationsGroup= abcbbbd\nnull], [membersList= ConcreteMember [memberName=Adir, str=abcbbbd\nnull] ConcreteMember [memberName=Dor, str=abcbbbd\nnull]", testObject.toString());

    }

    @Test
    void appendTest() {
        GroupAdmin testObject = new GroupAdmin();
        testObject.register(new ConcreteMember("Adir"));
        testObject.register(new ConcreteMember("Dor"));

        testObject.append("bbb");
        assertEquals("GroupAdmin [situationsGroup= bbb], [membersList= ConcreteMember [memberName=Adir, str=bbb] ConcreteMember [memberName=Dor, str=bbb]", testObject.toString());

        testObject.append("\n");
        assertEquals("GroupAdmin [situationsGroup= bbb\n], [membersList= ConcreteMember [memberName=Adir, str=bbb\n] ConcreteMember [memberName=Dor, str=bbb\n]", testObject.toString());
        testObject.append(null);
        assertEquals("GroupAdmin [situationsGroup= bbb\nnull], [membersList= ConcreteMember [memberName=Adir, str=bbb\nnull] ConcreteMember [memberName=Dor, str=bbb\nnull]", testObject.toString());
    }

    @Test
    void deleteTest() {
        GroupAdmin testObject = new GroupAdmin();
        testObject.register(new ConcreteMember("Adir"));
        testObject.register(new ConcreteMember("Dor"));

        testObject.delete(0,3);
        assertEquals("GroupAdmin [situationsGroup= ], [membersList= ConcreteMember [memberName=Adir, str=] ConcreteMember [memberName=Dor, str=]", testObject.toString());

        testObject.append("abcd");
        testObject.append("Testing");
        testObject.delete(0,5);
        assertEquals("GroupAdmin [situationsGroup= esting], [membersList= ConcreteMember [memberName=Adir, str=esting] ConcreteMember [memberName=Dor, str=esting]", testObject.toString());
        testObject.delete(2,3);
        assertEquals("GroupAdmin [situationsGroup= esing], [membersList= ConcreteMember [memberName=Adir, str=esing] ConcreteMember [memberName=Dor, str=esing]", testObject.toString());
    }

    @Test
    void undoTest() {
        GroupAdmin testObject = new GroupAdmin();
        testObject.register(new ConcreteMember("Adir"));
        testObject.register(new ConcreteMember("Dor"));

        testObject.append("bla");
        testObject.undo();
        assertEquals("GroupAdmin [situationsGroup= ], [membersList= ConcreteMember [memberName=Adir, str=] ConcreteMember [memberName=Dor, str=]", testObject.toString());
        testObject.undo();
        assertEquals("GroupAdmin [situationsGroup= ], [membersList= ConcreteMember [memberName=Adir, str=] ConcreteMember [memberName=Dor, str=]", testObject.toString());

        testObject.insert(0,"test");
        testObject.insert(4,"abcd");
        testObject.undo();
        assertEquals("GroupAdmin [situationsGroup= test], [membersList= ConcreteMember [memberName=Adir, str=test] ConcreteMember [memberName=Dor, str=test]", testObject.toString());
        testObject.delete(0,1);
        testObject.undo();
        assertEquals("GroupAdmin [situationsGroup= test], [membersList= ConcreteMember [memberName=Adir, str=test] ConcreteMember [memberName=Dor, str=test]", testObject.toString());    
    }

    @Test
    void updateTest() {
        GroupAdmin testObject = new GroupAdmin();
        ConcreteMember CM = new ConcreteMember("Adir");

        testObject.register(CM);
        testObject.append("testt");
        assertEquals(" ConcreteMember [memberName=Adir, str=testt]", CM.toString());

        testObject.insert(1, "bla");
        assertEquals(" ConcreteMember [memberName=Adir, str=tblaestt]", CM.toString());

        testObject.delete(0,3);
        assertEquals(" ConcreteMember [memberName=Adir, str=aestt]", CM.toString());

        testObject.undo();
        assertEquals(" ConcreteMember [memberName=Adir, str=tblaestt]", CM.toString());

        testObject.unregister(CM);
    }
}

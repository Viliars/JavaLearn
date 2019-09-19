import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class TestObject {
    private String text="";
    public TestObject(String text) { this.text = text; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}

public class HashMapTest {
    public static void main(String[] args) {
        HashMap hm = new HashMap();
        TestObject to;


        hm.put("key1", new TestObject("Значение 1"));
        hm.put("key2", new TestObject("Значение 2"));
        hm.put("key3", new TestObject("Значение 3"));

        to = (TestObject) hm.get("key1");

        System.out.println("Значение обхекта для ключа key1 " + to.getText());

        Map.Entry entry;
        Iterator it = hm.entrySet().iterator();
        while(it.hasNext()) {
            entry = (Map.Entry) it.next();
            System.out.println("Ключ " + entry.getKey() + " значение = " + ((TestObject)entry.getValue()).getText());
        }

        System.out.println();
        String key = "";
        it = hm.keySet().iterator();
        while(it.hasNext()) {
            key = (String) it.next();
            System.out.println("Для ключа " + key + " значение " + ((TestObject)hm.get(key)).getText());
        }
    }
}

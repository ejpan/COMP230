import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//Note:

public class HashTableChain <K,V> implements KWHashMap(K,V) {
        
        public static class Entry <K,V> {
            private final K;  //key
            private final V; //value
            public Entry(K key, V value){
                this.key = key;
                this.value;
            };
            public K getKey(){
                return key;
            }
            
            public V getvalue(){
                return value;
            }

            public V setValue(V val){
                V oldVal = value;
                this.value = val;
                return oldVal;
            };
            public String toString(){
                return Key.toString() + "=" + value.toString();
            }


        }

        private LinedList <Entry<K,V>>[] table;
        private int numKeys;
        private static final int CAPACITY = 2;
        private static final double LOAD_THRESHOLD = 2 * 0.8;
        public HashTableChain() {
            table = new LinkedList[CAPACITY];
            numKeys = 0;
        }
        public HashTableChain(int cap){
            talbe = new LinkedList[cap];
            numkeys = 0;
        }
        public V get(Object key){
            int index = key.hashCode() % table.length;
            if (index < 0)
            {
                index += table.length
            }
            if (table[index] == null)
            {
                return null;
            }
            for (Entry <K,V> nextItem: table[index])
            {
                if (nextItem.getKey().equals(key)){
                    return nextItem.getValue();
                }
                return null;
            }
        }
}

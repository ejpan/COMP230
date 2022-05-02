// import java.util.Iterator;
// import java.util.LinkedList;
// import java.util.List;
import java.util.*;
import com.apple.laf.resources.aqua;

//Note:

public class HashTableChain <K,V> implements KWHashMap<K,V> {
        
        public static class Entry <K,V> {
            private final K key;  //key
            private V value; //value
            
            public Entry(K key, V value){
                this.key = key;
                this.value = value;
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

        private LinkedList<Entry<K,V>>[] table;
        private int numKeys; //the counter
        private static final int CAPACITY = 101;
        private static final double LOAD_THRESHOLD = 3;


        public HashTableChain() 
        {
            table = new LinkedList[CAPACITY];
            numKeys = 0;
        }
        public HashTableChain(int cap)
        {
            table = new LinkedList[cap];
            numKeys = 0;
        }

        


        public V get(Object key)
        {
            int index = key.hashCode() % table.length;
            if (index < 0)
            {
                index += table.length;
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
            }
            return null;
        }

        public V put (K key, V value){
            int index = key.hashCode() % table.length;
            if (index < 0)
            {
                index += table.length;
            }
            if (table[index] == null)
            {
                table[index] = new LinkedList<>();
            }
            for (Entry<K,V> nextItem: table[index])
            {
                if (nextItem.getKey().equals(key))
                {
                    V oldVal = nextItem.getValue();
                    nextItem.setValue(value);
                    return oldVal;
                }
            }
            table[index].addFirst(new Entry<>(key,value));
            numKeys++;
            if (numKeys > (LOAD_THRESHOLD * table.length))
            {
                rehash();
            }
            return null
        }

        private V remove(Object key)
        {
            int index = key.hashCode() % table.length;
            if (index < 0)
            {
                index += table.length;
            }
            if (table[index] == null)
            {
                return null;
            }
            for (Entry<K,V> nextItem: table[index])
            {
                if (nextItem.getKey().equals(key))
                {
                    V oldVal = nextItem.getValue();
                    nextItem.remove();
                    numKeys--;
                if (table[index].isEmpty())
                {
                    table[index] = null;
                }
                return oldVal;
                }
            }
            return null;
        }


        private void hashCode()
        {
            // uses ASCII values
            int sum = 0;
            for (int i = 0; i < this.length; i++){
                sum += this.charAt(i);
            }
            return sum % table.length;
        }
        


        private void rehash()
        {
            LinkedList oldTable = table;
            int new_cap = CAPACITY * 2;
            boolean prime = false;
            while (prime == false){
                for (int i = 2; i < new_cap/2; i++){
                    if (new_cap % i == 0)
                    {
                        new_cap += 1;
                        prime = false;
                        break;
                    }else
                    {
                        prime = true;
                    }
                }

            }
            table = new LinkedList[new_cap];
            numKeys = 0;
            for (int i = 0; i < oldTable.size(); i++)
            {
                for (int k = 0; k < oldTable.get(i).size(); k++)
                {
                    int index = k.hashCode() % table.length;
                    table[index].put(k.getKey(), k.getValue());
                    numKeys++;
                }
            }

            
        }




}

// import java.util.Iterator;
// import java.util.LinkedList;
// import java.util.List;
import java.util.*;

//Note:

public class HashTableChain <K,V> implements KWHashMap<K,V> 
{
        
        public static class Entry<K,V> 
        {
            private final K key;  //key
            private V value; //value
            
            public Entry(K k, V v)
            {
                this.key = k;
                this.value = v;
            }

            public K getKey()
            {
                return key;
            }
            
            public V getValue()
            {
                return value;
            }

            public V setValue(V val)
            {
                V oldVal = value;
                this.value = val;
                return oldVal;
            }

            public String toString()
            {
                return key.toString() + "=" + value.toString();
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
            int index = hashCode(key) % table.length;
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
                if (nextItem.getKey().equals(key)){
                    return nextItem.getValue();
                }
            }
            return null;
        }

        public V put (K key, V val)
        {
            Entry e = new Entry<K,V>(key, val);
            int index = hashCode(key) % table.length;
            if (index < 0)
            {
                index += table.length;
            }
            if (table[index] == null)
            {
                table[index] = new LinkedList<Entry<K,V>>();
            }
            table[index].add(e);
            numKeys++;
            if (numKeys > (LOAD_THRESHOLD * table.length))
            {
                rehash();
            }
            return null;
        }

        public V remove(Object key)
        {
            int index = hashCode(key) % table.length;
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
                    table[index].remove(nextItem);
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


        public int size()
        {
            return numKeys;
        }

        public boolean isEmpty()
        {
            if (numKeys == 0)
            {
                return true;
            }
            return false;
        }

        public static int hashCode(Object o)
        {
            String s = (String)o;
            int sum = 0;
            for(int i = 0; i < s.length(); i++)
            {
                sum += s.charAt(i);
            }
            return sum;
        }
        

        private void rehash()
        {
            LinkedList<Entry<K,V>>[] oldTable = table.clone();
            int new_cap = CAPACITY * 2;
            boolean prime = false;
            while (!prime)
            {
                prime = true;
                for (int i = 2; i < new_cap/2; i++)
                {
                    if(new_cap % i == 0)
                    {
                        prime = false;
                        break;
                    }
                }
                new_cap++;

            }
            table = new LinkedList[new_cap];
            numKeys = 0;
            for (int i = 0; i < oldTable.length; i++)
            {
                if (oldTable[i] != null)
                {
                    for (int k = 0; k < oldTable[i].size(); k++)
                    {
                        Entry<K,V> e = oldTable[i].get(k);
                        int index = hashCode(e.getKey()) % table.length;
                        if (index < 0)
                        {
                            index += table.length;
                        }
                        if (table[index] == null)
                        {
                            table[index] = new LinkedList<Entry<K,V>>();
                        }
                        table[index].add(e);
                        numKeys++;

                    }
                }
            }
        }

        public void printmap()
        {
            for (int i = 0; i < table.length; i++)
            {
                if (table[i] != null){
                    for (int k = 0; k < table[i].size(); k++)
                    {
                        System.out.println(table[i].get(i));
                    }

                }
                
            }
        }




}

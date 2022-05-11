/* Class HashTableChain used to create Hash tables for the compression and 
decompression programs.  Works by hashing keys and storing key/value pairs in 
an array of linkedlists
Eric Pan & Gabe Seidl
May 11 ,2022
*/
// import java.util.Iterator;
// import java.util.LinkedList;
// import java.util.List;
import java.util.*;

//Note:

public class HashTableChain <K,V> implements KWHashMap<K,V> 
{
        
        public static class Entry<K,V>  //creates an entry object that gets placed in linkedlist                             
        {
            private final K key;  //key
            private V value; //value
            
            public Entry(K k, V v) // entry contructor
            {
                this.key = k;
                this.value = v;
            }

            public K getKey() // returns a key
            {
                return key;
            }
            
            public V getValue() // returns value
            {
                return value;
            }

            public V setValue(V val) // changes an entries value
            {
                V oldVal = value;
                this.value = val;
                return oldVal;
            }

            public String toString() // prints out entry values
            {
                return key.toString() + "=" + value.toString();
            }
           

        }

        private LinkedList<Entry<K,V>>[] table;
        private int numKeys; //the counter
        private static final int CAPACITY = 101; //initial capacity
        private static final double LOAD_THRESHOLD = 3; 
        public static int rehash_count = 0;

        public HashTableChain() // default constructor
        {
            table = new LinkedList[CAPACITY]; //creates a linkedlist array
            numKeys = 0;
        }
        public HashTableChain(int cap) // contructor with params
        {
            table = new LinkedList[cap];
            numKeys = 0;
        }


        public V get(Object key) // returns entry with given key, if no key exists return null
        {
            int index = hashCode(key) % table.length; // gets the index of the linkedlist that entry is stored in
            //System.out.println(index);
            if (index < 0)
            {
                index += table.length;
            }
            if (table[index] == null)
            {
                return null;
            }
            for (Entry<K,V> nextItem: table[index]) // loops through linkedlist to find an entry with key
            {
                if (nextItem.getKey().equals(key)){ 
                    return nextItem.getValue();
                }
            }
            return null;
        }

        public V put (K key, V val) // puts entry into hashtable
        {
            Entry e = new Entry<K,V>(key, val);
            int index = hashCode(key) % table.length; // uses hashcode to find the index of the linkedlist the entry should be appended to
            if (index < 0)
            {
                index += table.length;
            }
            if (table[index] == null)
            {
                table[index] = new LinkedList<Entry<K,V>>();
            }
            table[index].add(e); //adds entry to linkedlist
            numKeys++;
            if (numKeys > (LOAD_THRESHOLD * table.length)) //checks if capacity has been passed and rehashes table
            {
                rehash();
                rehash_count++;
            }
            return null;
        }

        public V remove(Object key) //removes entry associated with key or returns null if entry DNE
        {
            int index = hashCode(key) % table.length; //finds index where entry should exist
            if (index < 0)
            {
                index += table.length;
            }
            if (table[index] == null)
            {
                return null;
            }
            for (Entry<K,V> nextItem: table[index]) //loops through linkedlist to find entry
            {
                if (nextItem.getKey().equals(key)) //when entry found
                {
                    V oldVal = nextItem.getValue();
                    table[index].remove(nextItem); //removes entry from linkedlist
                    numKeys--; //decrements numKeys
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

        public static int hashCode(Object o) //method used to calculate a hashcode
        {
            int sum = 0;
            if(o instanceof Integer){ //checks if the object is an Integer
                int num = (int) o;
                return num; //if so, returns just the int value
            }
            String s = (String)o; //if the object is an ascii String,
            for(int i = 0; i < s.length(); i++) //returns sum of ascii values of chars
            {
                sum += s.charAt(i);
            }
            return sum;
        }
        
        //rehash method used to keep operation runtime lower or close to O(1)
        private void rehash() //increases size of the HashTable and reassigns entry objects to new linkedlists
        {
            LinkedList<Entry<K,V>>[] oldTable = table.clone();
            int new_cap = CAPACITY * 2;
            boolean prime = false;
            while (!prime)
            {
                prime = true;
                for (int i = 2; i < new_cap/2; i++) //finds next prime number to use as table size to decrease amount of entries per list
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
                    for (int k = 0; k < oldTable[i].size(); k++) //for each entry in old table, reassign it to a new list in the new table
                    {
                        Entry<K,V> e = oldTable[i].get(k);
                        int index = hashCode(e.getKey()) % table.length;
                        if (index < 0)
                        {
                            index += table.length;
                        }
                        if (table[index] == null) //all indexes are originally null, need to make linkedlists
                        {
                            table[index] = new LinkedList<Entry<K,V>>();
                        }
                        table[index].add(e);
                        numKeys++;

                    }
                }
            }
        }





}

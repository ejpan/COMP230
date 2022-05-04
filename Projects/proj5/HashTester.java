public class HashTester
{
    public static void main(String[] args)
    {
        HashTableChain myHash = new HashTableChain();
        String k = "lol";
        Integer val = 0;
        String k2 = "scott";
        Integer v2 = 1;
        myHash.put(k,val);
        myHash.put(k2,v2);
        System.out.println(myHash.get(k));
        System.out.println(myHash.get(k2));
        System.out.println(myHash.get(k));
        System.out.println(myHash.get(k2));
        System.out.println(myHash.size());
        myHash.remove(k);
        System.out.println(myHash.get(k));
        System.out.println(myHash.size());
    }
}
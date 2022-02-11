package Data;

public class CacheItem<K, V> {
    private K key;
    private V value;
    private int hitCount = 0; // LFU require this
    private CacheItem<K, V> prev, next;

    public CacheItem(K key, V value) {
        this.value = value;
        this.key = key;
    }
    public void incrementHitCount() {
        this.hitCount++;
    }

    // getter / setter

    public int getHitCount(){
        return hitCount;
    }

    public CacheItem<K, V> getPrev(){
        return prev;
    }

    public CacheItem<K, V> getNext(){
        return next;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setPrev(CacheItem<K, V> prev){
        this.prev = prev;
    }

    public void setNext(CacheItem<K, V> next){
        this.next = next;
    }
}
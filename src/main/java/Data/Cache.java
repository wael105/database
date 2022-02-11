package Data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class Cache<K, V> {
    private final Map<K, CacheItem<K, V>> map;
    private CacheItem<K, V> first;
    private CacheItem last;
    private int size;
    private final int CAPACITY;
    public Cache(int capacity) {
        CAPACITY = capacity;  //
        map = new ConcurrentHashMap<>(CAPACITY);
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        CacheItem<K, V> node = new CacheItem<>(key, value);

        if(!map.containsKey(key)) {
            if(size() >= CAPACITY) {
                deleteNode(first);
            }
            addNodeToLast(node);
        }
        map.put(key, node);
    }

    public void delete(K key) {
        deleteNode(map.get(key));
    }

    private void deleteNode(CacheItem<K, V> node) {
        if(node == null) {
            return;
        }
        if(last == node) {
            last = node.getPrev();
        }
        if(first == node) {
            first = node.getNext();
        }
        map.remove(node.getKey());
        size--;
    }
    private void reorder(CacheItem<K, V> node) {
        if(last == node) {
            return;
        }
        CacheItem<K, V> nextNode = node.getNext();
        while (nextNode != null) {
            if(nextNode.getHitCount() > node.getHitCount()) {
                break;
            }
            if(first == node) {
                first = nextNode;
            }
            if(node.getPrev() != null) {
                node.getPrev().setNext(nextNode);
            }
            nextNode.setPrev(node.getPrev());
            node.setPrev(nextNode);
            node.setNext(nextNode.getNext());
            if(nextNode.getNext() != null) {
                nextNode.getNext().setPrev(node);
            }
            nextNode.setNext(node);
            nextNode = node.getNext();
        }
        if(node.getNext() == null) {
            last = node;
        }
    }
    private void addNodeToLast(CacheItem<K, V> node) {
        if(last != null) {
            last.setNext(node);
            node.setPrev(last);
        }

        last = node;
        if(first == null) {
            first = node;
        }
        size++;
    }

    private void addNodeToFirst(CacheItem<K, V> node) {
        if(first != null) {
            node.setNext(first);
            first.setPrev(node);
        }
        first = node;

        if(last == null) {
            last = node;
        }
        size++;
    }

    public V get(K key) {
        if(!map.containsKey(key)) {
            return null;
        }
        CacheItem<K, V> node = (CacheItem<K, V>) map.get(key);
        node.incrementHitCount();
        //reorder(node);
        return (V) node.getValue();
    }
}

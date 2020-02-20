package algorithms.chapter3searching;

import java.util.Collection;
import java.util.Set;

/**
 * 符号表抽象类
 *
 * 看了看github上的代码，这个类依赖了JDK的TreeMap类，是否可以不依赖它，自行实现字段呢
 * 应该是可以的，看了AbstractMap这个抽象类，keys和values分别是用Set和Collection容器存储的
 *
 * @author ihaokun
 * @date 2020/2/17 19:49
 */
public class SymbolTable<K, V> {
  private Set<K> keySet;
  private Collection<V> values;
  private Set<Entry<K, V>> entrySet;

  public void put(K k, V v){
    if (keySet.contains(k)) throw new UnsupportedOperationException("Already key in table");
    keySet.add(k);
    values.add(v);
    entrySet.add(new Entry<>(k, v));
  }
  V get(K k){
    for (Entry<K, V> entry : entrySet)
      if (entry.getKey() == k) return entry.getVal();
    return null;
  }
  void remove(K k) {
    if (keySet.contains(k)) {
      for (Entry<K, V> kvEntry : entrySet)
        if (kvEntry.getKey().equals(k)) {
          keySet.remove(k);
          values.remove(kvEntry.getVal());
          entrySet.remove(kvEntry);
          return;
        }
    }
    throw new UnsupportedOperationException("table exclude the key");
  }

  // key-value pair
  private class Entry<K, V>{
    private K key;
    private V val;

    public K getKey() {
      return key;
    }
    public void setKey(K key) {
      this.key = key;
    }
    public V getVal() {
      return val;
    }
    public void setVal(V val) {
      this.val = val;
    }

    public Entry(K key, V val) {
      this.key = key;
      this.val = val;
    }
  }
}
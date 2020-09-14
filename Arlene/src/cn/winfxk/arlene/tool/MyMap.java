package cn.winfxk.arlene.tool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义Map<其实就只是增加了一些没卵用的功能>
 * 
 * @Createdate 2020/05/22 19:37:29
 * @author Winfxk
 * @param <K>
 * @param <V>
 */
public class MyMap<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = -8324429888407081347L;

	public MyMap(K k, V v) {
		put(k, v);
	}

	public MyMap() {
	}

	public MyMap(Map<K, V> map) {
		putAll(map);
	}

	public MyMap<K, V> add(K k, V v) {
		put(k, v);
		return this;
	}

	/**
	 * 根据Key位置返回Map值
	 * 
	 * @param index
	 * @return
	 */
	public V getVOfIndex(int index) {
		return get(new ArrayList<>(keySet()).get(index));
	}

	/**
	 * 根据Map的值获取Key
	 * 
	 * @param v
	 * @return
	 */
	public List<K> getKey(V v) {
		List<K> list = new ArrayList<>();
		for (Map.Entry<K, V> entry : entrySet())
			if (entry.getValue().equals(v))
				list.add(entry.getKey());
		return list;
	}

	/**
	 * 删除一个Map值
	 * 
	 * @param v
	 * @return
	 */
	public MyMap<K, V> removeValues(V v) {
		if (containsValue(v)) {
			List<K> list = getKey(v);
			for (K k : list)
				remove(k);
		}
		return this;
	}

	/**
	 * 返回第一个Key出现的位置
	 * 
	 * @param k
	 * @return
	 */
	public int indexOf(K k) {
		return new ArrayList<>(keySet()).indexOf(k);
	}

	/**
	 * 返回第一个Key出现的位置
	 * 
	 * @param k
	 * @param strIndex 开始检索的位置
	 * @return
	 */
	public int indexOf(K k, int strIndex) {
		List<K> list = new ArrayList<>(keySet());
		for (int i = strIndex; i < list.size(); i++)
			if (list.get(i).equals(k))
				return i;
		return -1;
	}

	/**
	 * 从后往前返回第一个出现的位置
	 * 
	 * @param k
	 * @return
	 */
	public int laterIndexOf(K k) {
		return new ArrayList<>(keySet()).lastIndexOf(k);
	}

	/**
	 * 从后往前返回第一个出现的位置
	 * 
	 * @param k
	 * @param endIndex 开始检索的位置
	 * @return
	 */
	public int laterIndexOf(K k, int endIndex) {
		List<K> list = new ArrayList<>(keySet());
		for (int i = endIndex; i > 0; i--)
			if (list.get(i).equals(k))
				return i;
		return -1;
	}

	public static <K, V> MyMap<K, V> make(K k, V v) {
		return new MyMap<>(k, v);
	}

	public int getInt(K key) {
		return Tool.ObjToInt(get(key));
	}

	public int getInt(K key, int D) {
		return Tool.ObjToInt(get(key), D);
	}

	public String getString(K key) {
		return Tool.objToString(get(key), null);
	}

	public String getString(K key, String D) {
		return Tool.objToString(get(key), D);
	}

	public Long getLong(K key) {
		return Tool.objToLong(get(key));
	}

	public Long getLong(K key, long D) {
		return Tool.objToLong(get(key), D);
	}

	public boolean getBoolean(K Key, boolean D) {
		return Tool.ObjToBool(get(Key), D);
	}

	public boolean getBoolean(K Key) {
		return Tool.ObjToBool(get(Key));
	}

	public <E> List<E> getList(K key, List<E> D) {
		try {
			return get(key) == null ? D : (List<E>) get(key);
		} catch (Exception e) {
			return D;
		}
	}

	public List<Object> getList(K key) {
		return (List<Object>) get(key);
	}

}

package cn.winfxk.arlene.tool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.nukkit.utils.Utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Winfxk
 */
public class MyConfig extends Config {
	private String Text = "";
	private Map<String, Object> map = new HashMap<>();
	private Map<String, Object> isMap;
	private File file;
	private static Yaml yaml;
	static {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		yaml = new Yaml(dumperOptions);
	}

	public File getFile() {
		return file;
	}

	public MyConfig(File file) {
		this(file, new HashMap<String, Object>());
	}

	public MyConfig(File file, Map<String, Object> map) {
		this.file = file;
		isMap = map;
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getAll() {
		return map;
	}

	public String getContent() {
		return Text;
	}

	@Override
	public String toString() {
		return "File: " + file.getAbsolutePath() + "\nExists: " + file.exists() + "\n\n" + yaml.dump(map);
	}

	public boolean reload(HashMap<String, Object> map) {
		try {
			Utils.writeFile(file, yaml.dump(map));
			this.map = yaml.loadAs(Text = Utils.readFile(file), Map.class);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void reload() {
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MyConfig setAll(Map<String, Object> map) {
		this.map.clear();
		this.map.putAll(map);
		return this;
	}

	@Override
	public void set(String Key, Object obj) {
		map.put(Key, obj);
	}

	@Override
	public boolean getBoolean(String Key) {
		return getBoolean(Key, false);
	}

	@Override
	public boolean getBoolean(String Key, boolean Default) {
		return Tool.ObjToBool(get(Key, Default), Default);
	}

	@Override
	public int getInt(String Key) {
		return getInt(Key, 0);
	}

	@Override
	public int getInt(String Key, int Default) {
		String string = getString(Key, null);
		if (string == null || string.isEmpty() || !Tool.isInteger(string))
			return Default;
		return Tool.ObjToInt(string, Default);
	}

	@Override
	public String getString(String Key) {
		return getString(Key, null);
	}

	@Override
	public String getString(String Key, String Default) {
		if (!map.containsKey(Key))
			return Default;
		Object object = map.get(Key);
		try {
			return (String) object;
		} catch (Exception e) {
			return String.valueOf(object);
		}
	}

	@Override
	public Object get(String Key) {
		return get(Key, null);
	}

	@Override
	public Object get(String Key, Object Default) {
		if (!map.containsKey(Key))
			return Default;
		return map.get(Key);
	}

	private void load() throws Exception {
		if (!file.exists())
			if (!save(isMap))
				throw new Exception("无法初始化配置文件！");
		map = yaml.loadAs(Text = Utils.readFile(file), Map.class);
	}

	@Override
	public boolean save() {
		return save(map);
	}

	public boolean save(Map<String, Object> map) {
		Text = yaml.dump(map);
		try {
			Utils.writeFile(file, Text);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}

package cn.winfxk.arlene.tool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.nukkit.Server;
import cn.nukkit.scheduler.FileWriteTask;
import cn.nukkit.utils.ConfigSection;
import cn.nukkit.utils.MainLogger;
import cn.nukkit.utils.Utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * 自定义Config//其实就是修改了原版的File可视性
 * 
 * @amend Winfxk
 * @Createdate 2020/05/30 15:54:15
 * @author MagicDroidX
 */
@SuppressWarnings("rawtypes")
public class Config extends cn.nukkit.utils.Config {
	public static final int DETECT = -1; // Detect by file extension
	public static final int PROPERTIES = 0; // .properties
	public static final int CNF = Config.PROPERTIES; // .cnf
	public static final int JSON = 1; // .js, .json
	public static final int YAML = 2; // .yml, .yaml
	// public static final int EXPORT = 3; // .export, .xport
	// public static final int SERIALIZED = 4; // .sl
	public static final int ENUM = 5; // .txt, .list, .enum
	public static final int ENUMERATION = Config.ENUM;

	// private LinkedHashMap<String, Object> config = new LinkedHashMap<>();
	private ConfigSection config = new ConfigSection();
	public File file;
	private boolean correct = false;
	private int type = Config.DETECT;

	public static final Map<String, Integer> format = new TreeMap<>();

	static {
		format.put("properties", Config.PROPERTIES);
		format.put("con", Config.PROPERTIES);
		format.put("conf", Config.PROPERTIES);
		format.put("config", Config.PROPERTIES);
		format.put("js", Config.JSON);
		format.put("json", Config.JSON);
		format.put("yml", Config.YAML);
		format.put("yaml", Config.YAML);
		// format.put("sl", Config.SERIALIZED);
		// format.put("serialize", Config.SERIALIZED);
		format.put("txt", Config.ENUM);
		format.put("list", Config.ENUM);
		format.put("enum", Config.ENUM);
	}

	public File getFile() {
		return file;
	}

	/**
	 * Constructor for Config instance with undefined file object
	 *
	 * @param type - Config type
	 */
	public Config(int type) {
		this.type = type;
		this.correct = true;
		this.config = new ConfigSection();
	}

	/**
	 * Constructor for Config (YAML) instance with undefined file object
	 */
	public Config() {
		this(Config.YAML);
	}

	public Config(String file) {
		this(file, Config.DETECT);
	}

	public Config(File file) {
		this(file.toString(), Config.DETECT);
	}

	public Config(String file, int type) {
		this(file, type, new ConfigSection());
	}

	public Config(File file, int type) {
		this(file.toString(), type, new ConfigSection());
	}

	@Deprecated
	public Config(String file, int type, LinkedHashMap<String, Object> defaultMap) {
		this.load(file, type, new ConfigSection(defaultMap));
	}

	public Config(String file, int type, ConfigSection defaultMap) {
		this.load(file, type, defaultMap);
	}

	public Config(File file, int type, ConfigSection defaultMap) {
		this.load(file.toString(), type, defaultMap);
	}

	@Deprecated
	public Config(File file, int type, LinkedHashMap<String, Object> defaultMap) {
		this(file.toString(), type, new ConfigSection(defaultMap));
	}

	@Override
	public void reload() {
		this.config.clear();
		this.correct = false;
		// this.load(this.file.toString());
		if (this.file == null)
			throw new IllegalStateException("Failed to reload Config. File object is undefined.");
		this.load(this.file.toString(), this.type);

	}

	@Override
	public boolean load(String file) {
		return this.load(file, Config.DETECT);
	}

	@Override
	public boolean load(String file, int type) {
		return this.load(file, type, new ConfigSection());
	}

	@Override
	public boolean load(String file, int type, ConfigSection defaultMap) {
		this.correct = true;
		this.type = type;
		this.file = new File(file);
		if (!this.file.exists()) {
			try {
				this.file.getParentFile().mkdirs();
				this.file.createNewFile();
			} catch (IOException e) {
				MainLogger.getLogger().error("Could not create Config " + this.file.toString(), e);
			}
			this.config = defaultMap;
			this.save();
		} else {
			if (this.type == Config.DETECT) {
				String extension = "";
				if (this.file.getName().lastIndexOf(".") != -1 && this.file.getName().lastIndexOf(".") != 0) {
					extension = this.file.getName().substring(this.file.getName().lastIndexOf(".") + 1);
				}
				if (format.containsKey(extension)) {
					this.type = format.get(extension);
				} else {
					this.correct = false;
				}
			}
			if (this.correct) {
				String content = "";
				try {
					content = Utils.readFile(this.file);
				} catch (IOException e) {
					Server.getInstance().getLogger().logException(e);
				}
				this.parseContent(content);
				if (!this.correct)
					return false;
				if (this.setDefault(defaultMap) > 0) {
					this.save();
				}
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean load(InputStream inputStream) {
		if (inputStream == null)
			return false;
		if (this.correct) {
			String content;
			try {
				content = Utils.readFile(inputStream);
			} catch (IOException e) {
				Server.getInstance().getLogger().logException(e);
				return false;
			}
			this.parseContent(content);
		}
		return correct;
	}

	@Override
	public boolean check() {
		return this.correct;
	}

	@Override
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * Save configuration into provided file. Internal file object will be set to
	 * new file.
	 *
	 * @param file
	 * @param async
	 * @return
	 */
	@Override
	public boolean save(File file, boolean async) {
		this.file = file;
		return save(async);
	}

	@Override
	public boolean save(File file) {
		this.file = file;
		return save();
	}

	@Override
	public synchronized boolean save() {
		return this.save(false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public synchronized boolean save(Boolean async) {
		if (this.file == null)
			throw new IllegalStateException("Failed to save Config. File object is undefined.");
		if (this.correct) {
			String content = "";
			switch (this.type) {
			case Config.PROPERTIES:
				content = this.writeProperties();
				break;
			case Config.JSON:
				content = new GsonBuilder().setPrettyPrinting().create().toJson(this.config);
				break;
			case Config.YAML:
				DumperOptions dumperOptions = new DumperOptions();
				dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
				Yaml yaml = new Yaml(dumperOptions);
				content = yaml.dump(this.config);
				break;
			case Config.ENUM:
				for (Object o : this.config.entrySet()) {
					Map.Entry entry = (Map.Entry) o;
					content += entry.getKey() + "\r\n";
				}
				break;
			}
			if (async) {
				Server.getInstance().getScheduler().scheduleAsyncTask(new FileWriteTask(this.file, content));

			} else {
				try {
					Utils.writeFile(this.file, content);
				} catch (IOException e) {
					Server.getInstance().getLogger().logException(e);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void set(final String key, Object value) {
		this.config.set(key, value);
	}

	@Override
	public Object get(String key) {
		return this.get(key, null);
	}

	@Override
	public <T> T get(String key, T defaultValue) {
		return this.correct ? this.config.get(key, defaultValue) : defaultValue;
	}

	@Override
	public ConfigSection getSection(String key) {
		return this.correct ? this.config.getSection(key) : new ConfigSection();
	}

	@Override
	public boolean isSection(String key) {
		return config.isSection(key);
	}

	@Override
	public ConfigSection getSections(String key) {
		return this.correct ? this.config.getSections(key) : new ConfigSection();
	}

	@Override
	public ConfigSection getSections() {
		return this.correct ? this.config.getSections() : new ConfigSection();
	}

	@Override
	public int getInt(String key) {
		return this.getInt(key, 0);
	}

	@Override
	public int getInt(String key, int defaultValue) {
		return this.correct ? this.config.getInt(key, defaultValue) : defaultValue;
	}

	@Override
	public boolean isInt(String key) {
		return config.isInt(key);
	}

	@Override
	public long getLong(String key) {
		return this.getLong(key, 0);
	}

	@Override
	public long getLong(String key, long defaultValue) {
		return this.correct ? this.config.getLong(key, defaultValue) : defaultValue;
	}

	@Override
	public boolean isLong(String key) {
		return config.isLong(key);
	}

	@Override
	public double getDouble(String key) {
		return this.getDouble(key, 0);
	}

	@Override
	public double getDouble(String key, double defaultValue) {
		return this.correct ? this.config.getDouble(key, defaultValue) : defaultValue;
	}

	@Override
	public boolean isDouble(String key) {
		return config.isDouble(key);
	}

	@Override
	public String getString(String key) {
		return this.getString(key, "");
	}

	@Override
	public String getString(String key, String defaultValue) {
		return this.correct ? this.config.getString(key, defaultValue) : defaultValue;
	}

	@Override
	public boolean isString(String key) {
		return config.isString(key);
	}

	@Override
	public boolean getBoolean(String key) {
		return this.getBoolean(key, false);
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		return this.correct ? this.config.getBoolean(key, defaultValue) : defaultValue;
	}

	@Override
	public boolean isBoolean(String key) {
		return config.isBoolean(key);
	}

	@Override
	public List getList(String key) {
		return this.getList(key, null);
	}

	@Override
	public List getList(String key, List defaultList) {
		return this.correct ? this.config.getList(key, defaultList) : defaultList;
	}

	@Override
	public boolean isList(String key) {
		return config.isList(key);
	}

	@Override
	public List<String> getStringList(String key) {
		return config.getStringList(key);
	}

	@Override
	public List<Integer> getIntegerList(String key) {
		return config.getIntegerList(key);
	}

	@Override
	public List<Boolean> getBooleanList(String key) {
		return config.getBooleanList(key);
	}

	@Override
	public List<Double> getDoubleList(String key) {
		return config.getDoubleList(key);
	}

	@Override
	public List<Float> getFloatList(String key) {
		return config.getFloatList(key);
	}

	@Override
	public List<Long> getLongList(String key) {
		return config.getLongList(key);
	}

	@Override
	public List<Byte> getByteList(String key) {
		return config.getByteList(key);
	}

	@Override
	public List<Character> getCharacterList(String key) {
		return config.getCharacterList(key);
	}

	@Override
	public List<Short> getShortList(String key) {
		return config.getShortList(key);
	}

	@Override
	public List<Map> getMapList(String key) {
		return config.getMapList(key);
	}

	@Override
	public void setAll(LinkedHashMap<String, Object> map) {
		this.config = new ConfigSection(map);
	}

	@Override
	public void setAll(ConfigSection section) {
		this.config = section;
	}

	@Override
	public boolean exists(String key) {
		return config.exists(key);
	}

	@Override
	public boolean exists(String key, boolean ignoreCase) {
		return config.exists(key, ignoreCase);
	}

	@Override
	public void remove(String key) {
		config.remove(key);
	}

	@Override
	public MyMap<String, Object> getAll() {
		Map<String, Object> map = this.config.getAllMap();
		return map instanceof MyMap ? (MyMap<String, Object>) map : new MyMap<>(map);
	}

	/**
	 * Get root (main) config section of the Config
	 *
	 * @return
	 */
	@Override
	public ConfigSection getRootSection() {
		return config;
	}

	@Override
	public int setDefault(LinkedHashMap<String, Object> map) {
		return setDefault(new ConfigSection(map));
	}

	@Override
	public int setDefault(ConfigSection map) {
		int size = this.config.size();
		this.config = this.fillDefaults(map, this.config);
		return this.config.size() - size;
	}

	private ConfigSection fillDefaults(ConfigSection defaultMap, ConfigSection data) {
		for (String key : defaultMap.keySet()) {
			if (!data.containsKey(key)) {
				data.put(key, defaultMap.get(key));
			}
		}
		return data;
	}

	private void parseList(String content) {
		content = content.replace("\r\n", "\n");
		for (String v : content.split("\n")) {
			if (v.trim().isEmpty()) {
				continue;
			}
			config.put(v, true);
		}
	}

	private String writeProperties() {
		String content = "#Properties Config file\r\n#" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
				+ "\r\n";
		for (Object o : this.config.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			Object v = entry.getValue();
			Object k = entry.getKey();
			if (v instanceof Boolean) {
				v = (Boolean) v ? "on" : "off";
			}
			content += k + "=" + v + "\r\n";
		}
		return content;
	}

	private void parseProperties(String content) {
		for (final String line : content.split("\n")) {
			if (Pattern.compile("[a-zA-Z0-9\\-_.]*+=+[^\\r\\n]*").matcher(line).matches()) {
				final int splitIndex = line.indexOf('=');
				if (splitIndex == -1) {
					continue;
				}
				final String key = line.substring(0, splitIndex);
				final String value = line.substring(splitIndex + 1);
				final String valueLower = value.toLowerCase();
				if (this.config.containsKey(key)) {
					MainLogger.getLogger()
							.debug("[Config] Repeated property " + key + " on file " + this.file.toString());
				}
				switch (valueLower) {
				case "on":
				case "true":
				case "yes":
					this.config.put(key, true);
					break;
				case "off":
				case "false":
				case "no":
					this.config.put(key, false);
					break;
				default:
					this.config.put(key, value);
					break;
				}
			}
		}
	}

	/**
	 * @deprecated use {@link #get(String)} instead
	 */
	@Override
	@Deprecated
	public Object getNested(String key) {
		return get(key);
	}

	/**
	 * @deprecated use {@link #get(String, Object)} instead
	 */
	@Override
	@Deprecated
	public <T> T getNested(String key, T defaultValue) {
		return get(key, defaultValue);
	}

	/**
	 * @deprecated use {@link #get(String)} instead
	 */
	@Override
	@Deprecated
	public <T> T getNestedAs(String key, Class<T> type) {
		return (T) get(key);
	}

	/**
	 * @deprecated use {@link #remove(String)} instead
	 */
	@Override
	@Deprecated
	public void removeNested(String key) {
		remove(key);
	}

	private void parseContent(String content) {
		try {
			switch (this.type) {
			case Config.PROPERTIES:
				this.parseProperties(content);
				break;
			case Config.JSON:
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				this.config = new ConfigSection(gson.fromJson(content, new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType()));
				break;
			case Config.YAML:
				DumperOptions dumperOptions = new DumperOptions();
				dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
				Yaml yaml = new Yaml(dumperOptions);
				this.config = new ConfigSection(yaml.loadAs(content, LinkedHashMap.class));
				break;
			// case Config.SERIALIZED
			case Config.ENUM:
				this.parseList(content);
				break;
			default:
				this.correct = false;
			}
		} catch (Exception e) {
			MainLogger.getLogger().warning("Failed to parse the config file " + file, e);
			throw e;
		}
	}

	@Override
	public Set<String> getKeys() {
		if (this.correct)
			return config.getKeys();
		return new HashSet<>();
	}

	@Override
	public Set<String> getKeys(boolean child) {
		if (this.correct)
			return config.getKeys(child);
		return new HashSet<>();
	}
}

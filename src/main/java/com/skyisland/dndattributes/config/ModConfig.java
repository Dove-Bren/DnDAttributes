package com.skyisland.dndattributes.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.skyisland.dndattributes.DnDAttributesMod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

/**
 * Config wrapper class. Ease of use class
 * @author Skyler
 *
 */
public class ModConfig {
	
	public static enum Key {
		TAG_MOBS(Category.SERVER, "tagMobs", false, true, "Tag non-player entities with DnD attributes"),
		SHOW_INV_INFO(Category.DISPLAY, "showInvInfo", true, false, "Display attribute info popup in inventory screen"),
		SHOW_INV_INFO_COLOR(Category.DISPLAY, "showInvInfoColor", 0x00, false, "Attribute text color as RGB int. E.G. 0xFF0000 is RED. 0xFFFFFF is white. 0x000000 is black."),
		ATTR_DEF_VAL(Category.SERVER, "attrDefVal", 8, true, "Default attribute value");
		
		public static enum Category {
			SERVER("server", "Core properties that MUST be syncronized bytween the server and client. Client values ignored"),
			DISPLAY("display", "Item tag information and gui display options"),
			TEST("test", "Options used just for debugging and development");
			
			private String categoryName;
			
			private String comment;
			
			private Category(String name, String tooltip) {
				categoryName = name;
				comment = tooltip;
			}
			
			public String getName() {
				return categoryName;
			}
			
			@Override
			public String toString() {
				return getName();
			}
			
			protected static void deployCategories(Configuration config) {
				for (Category cat : values()) {
					config.setCategoryComment(cat.categoryName, cat.comment);
					config.setCategoryRequiresWorldRestart(cat.categoryName, cat == SERVER);
				}
			}
			
		}
		
		private Category category;
		
		private String key;
		
		private String desc;
		
		private Object def;
		
		private boolean serverBound;
		
		private Key(Category category, String key, Object def, String desc) {
			this(category, key, def, false, desc);
		}
		
		private Key(Category category, String key, Object def, boolean serverBound, String desc) {
			this.category = category;
			this.key = key;
			this.desc = desc;
			this.def = def;
			this.serverBound = serverBound;
			
			if (!(def instanceof Float || def instanceof Integer || def instanceof Boolean
					|| def instanceof String)) {
				DnDAttributesMod.logger.warn("Config property defaults to a value type that's not supported: " + def.getClass());
			}
		}
		
		protected String getKey() {
			return key;
		}
		
		protected String getDescription() {
			return desc;
		}
		
		protected String getCategory() {
			return category.getName();
		}
		
		protected Object getDefault() {
			return def;
		}
		
		/**
		 * Returns whether this config value should be replaced by
		 * the server's values instead of the clients
		 * @return
		 */
		public boolean isServerBound() {
			return serverBound;
		}
		
		/**
		 * Returns whether this config option can be changed at runtime
		 * @return
		 */
		public boolean isRuntime() {
			if (category == Category.SERVER)
				return false;
			
			//add other cases as they come
			
			return true;
		}
		
		public void saveToNBT(ModConfig config, NBTTagCompound tag) {
			if (tag == null)
				tag = new NBTTagCompound();
			
			if (def instanceof Float)
				tag.setFloat(key, config.getFloatValue(this)); 
			else if (def instanceof Boolean)
				tag.setBoolean(key, config.getBooleanValue(this));
			else if (def instanceof Integer)
				tag.setInteger(key, config.getIntValue(this));
			else
				tag.setString(key, config.getStringValue(this));
		}

		public Object valueFromNBT(NBTTagCompound tag) {
			if (tag == null)
				return null;
			
			if (def instanceof Float)
				return tag.getFloat(key); 
			else if (def instanceof Boolean)
				return tag.getBoolean(key);
			else if (def instanceof Integer)
				return tag.getInteger(key);
			else
				return tag.getString(key);
		}
		
		public static Collection<Key> getCategoryKeys(Category category) {
			Set<Key> set = new HashSet<Key>();
			
			for (Key key : values()) {
				if (key.category == category)
					set.add(key);
			}
			
			return set;
		}
	}

	public static ModConfig config;
	
	private Configuration base;
	
	public ModConfig(Configuration base) {
		this.setBase(base);
		ModConfig.config = this;
		
		initConfig();
	}

	
	private void initConfig() {
		for (Key key : Key.values())
		if (!getBase().hasKey(key.getCategory(), key.getKey())) {
			if (key.getDefault() instanceof Float) {
				getBase().getFloat(key.getKey(), key.getCategory(), (Float) key.getDefault(),
						Float.MIN_VALUE, Float.MAX_VALUE, key.getDescription());
			}
			else if (key.getDefault() instanceof Boolean)
				getBase().getBoolean(key.getKey(), key.getCategory(), (Boolean) key.getDefault(),
						key.getDescription());
			else if (key.getDefault() instanceof Integer)
				getBase().getInt(key.getKey(), key.getCategory(), (Integer) key.getDefault(),
						Integer.MIN_VALUE, Integer.MAX_VALUE, key.getDescription());
			else
				getBase().getString(key.getKey(), key.getCategory(), key.getDefault().toString(),
						key.getDescription());
		}
		
		if (getBase().hasChanged())
			getBase().save();
	}
	
	/**************************
	 *   Underlying getters
	 *************************/
	

	protected boolean getBooleanValue(Key key) {
		//DOESN'T cast check. Know what you're doing before you do it
		
		return getBase().getBoolean(key.getKey(), key.getCategory(), (Boolean) key.getDefault(),
		key.getDescription());
	}
	
	protected float getFloatValue(Key key) {
		//DOESN'T cast check. Know what you're doing before you do it
		
		return getBase().getFloat(key.getKey(), key.getCategory(), (Float) key.getDefault(),
		Float.MIN_VALUE, Float.MAX_VALUE, key.getDescription());
	}
	
	protected int getIntValue(Key key) {
		//DOESN'T cast check. Know what you're doing before you do it
		
		return getBase().getInt(key.getKey(), key.getCategory(), (Integer) key.getDefault(),
		Integer.MIN_VALUE, Integer.MAX_VALUE, key.getDescription());
	}
	
	protected String getStringValue(Key key) {
		//DOESN'T cast check. Know what you're doing before you do it
		return getBase().getString(key.getKey(), key.getCategory(), (String) key.getDefault(),
				key.getDescription());
	}
	
	public boolean getTagAllMobs() {
		return getBooleanValue(Key.TAG_MOBS);
	}
	
	public boolean showInvInfo() {
		return getBooleanValue(Key.SHOW_INV_INFO);
	}
	
	public int showInvInfoColor() {
		return getIntValue(Key.SHOW_INV_INFO_COLOR);
	}
	
	/**
	 * Used when creating a new set of attributes
	 * @return
	 */
	public int getDefaultAttributeValue() {
		return getIntValue(Key.ATTR_DEF_VAL);
	}


	public Configuration getBase() {
		return base;
	}


	public void setBase(Configuration base) {
		this.base = base;
	}
		
}

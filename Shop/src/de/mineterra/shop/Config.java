package de.mineterra.shop;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	public String groupName = "Markt";
	public String groupNamePath = "shop.groupName";
	
	shop plugin;
	
	
	public Config(shop plugin){
		this.plugin = plugin;
		
		loadConfig();
	}
	
	public void loadConfig(){
		File configFile = new File(this.plugin.getDataFolder(), "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		
		if (!configFile.exists()){
			config.options().header("[Shop Hilfe] Plugin von mineterra.de");
			config.set(this.groupNamePath, this.groupName);
			try{
				config.save(configFile);
				System.out.println("[Shop Hile] Config erfolgreich angelegt!");
			}
			catch (IOException e){
				System.out.println("[Shop Hilfe] kann keine Config Datei anlegen!");
				e.printStackTrace();
			}
		}
		else
		{
			this.groupName = config.getString(this.groupName);
		}
	}

}

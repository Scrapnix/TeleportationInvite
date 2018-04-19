package com.scrap.tpinvite.managers;

import java.io.File; 
import java.io.IOException;

import lombok.Data;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

@Data
public class FileManager {

	public String prefix = getConfigFileConfiguration().getString("prefix");
	
	public File getConfigFile() 
	  {
	    return new File("plugins/TPInvite", "config.yml");
	  }
	  
	  public Configuration getConfigFileConfiguration(){
		  try {
			return ConfigurationProvider.getProvider(YamlConfiguration.class).load(getConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	  }
	  
	  public void setConfigFile() throws IOException {
		  Configuration config = getConfigFileConfiguration();
	    
		  config.set("prefix", "§6TeleportEinladung §7| ");
		  
		  ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, getConfigFile());

	  }
	
	  public void setFile() {
		  if (!getConfigFile().exists()) {
			  try {
				  File f = new File("plugins/TPInvite", "config.yml");

				  f.getParentFile().mkdirs(); 
				  f.createNewFile();
				  setConfigFile();
			} catch (IOException e) {
				System.out.println("TPInvite | Die config.yml konnte nicht erstellt werden! Das Plugin wurde disabled!");
				e.printStackTrace();
			}
		  }else {
			  System.out.println("TPInvite | Die config.yml wurde schon einmal erstellt! Ueberspringen...");
		  }
	  }
	  
}

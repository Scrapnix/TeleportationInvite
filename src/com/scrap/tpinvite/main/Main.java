package com.scrap.tpinvite.main;

import com.scrap.tpinvite.commands.TPinvite;
import com.scrap.tpinvite.commands.TeleportToPoint;
import com.scrap.tpinvite.managers.FileManager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
	
	@Override
	public void onEnable() {
		FileManager fm = new FileManager();
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new TPinvite(this));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new TeleportToPoint(this));
		fm.setFile();
	}
	
}

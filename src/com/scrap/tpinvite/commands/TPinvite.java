package com.scrap.tpinvite.commands;

import java.util.ArrayList; 

import com.scrap.tpinvite.main.Main;
import com.scrap.tpinvite.managers.FileManager;
import com.scrap.tpinvite.managers.TextComponentManager;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TPinvite extends Command{

	public TPinvite(Main main) {
		super("tpinvite");
	}
	
	public static ArrayList<String> map = new ArrayList<String>();
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer)sender;
		FileManager fm = new FileManager();
		TextComponentManager tcm = new TextComponentManager();					
		String m = "";
		if (p.hasPermission("tpinvite.invite")) {
			if (args.length >= 2) {
				if (args[0].equalsIgnoreCase("send")) {
					for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
						for (int i = 1; i < args.length; i++) {
							if (m == "") {
								m = args[i];
							}else {
								m = m + " " + args[i];
							}
							ArrayList<String> remove = new ArrayList<String>();
							if (!map.isEmpty()) {
								for (String s : map) {
									String[] string = s.split(";");
									if (string[0].equalsIgnoreCase(sender.getName())) {
										remove.add(s);
									}
								}
							}
							if (!remove.isEmpty()) {
								for (String rm : remove) {
									map.remove(rm);
								}
							}
							remove.clear();
							map.add(sender.getName() + ";" + p.getName() + ";" + "false" + ";" + p.getServer().getInfo().getName());
						}
						TextComponent message = tcm.tc(fm.prefix + m, p.getName());
						player.sendMessage(message);
					}
					p.sendMessage(new TextComponent(fm.prefix + "§7Du hast die Nachricht an §aalle Spieler §7versendet!"));
				}else if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage(new TextComponent(fm.prefix + "§7Hilfe: §a/tpinvite send [Nachricht]§7!"));
				}else if (args[0].equalsIgnoreCase("add")) {					
				}else if (args[0].equalsIgnoreCase("remove")) {	
				}else {
					p.sendMessage(new TextComponent(fm.prefix + "§7Hilfe: §a/tpinvite send [Nachricht]§7!"));
				}
			}else {
				p.sendMessage(new TextComponent(fm.prefix + "§7Hilfe: §a/tpinvite send [Nachricht]§7!"));
			}
		}else {
			p.sendMessage(new TextComponent(fm.prefix + "§cDazu hast du keine Berechtigung!"));
		}
	}
	
}

package com.scrap.tpinvite.commands;

import com.scrap.tpinvite.main.Main;
import com.scrap.tpinvite.managers.FileManager;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TeleportToPoint extends Command{

	public TeleportToPoint(Main main) {
		super("teleporttopoint");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer)sender;
		FileManager fm = new FileManager();
		for (String s : TPinvite.map) {
			System.out.println(s);
			String[] parts = s.split(";");
			
			String senderp = parts[0];
			String recipient = parts[1];
			String bol = parts[2];
			String servername = parts[3];
			
			if (p.getName().equalsIgnoreCase(recipient)) {
				if (args[0].equalsIgnoreCase(senderp)) {
					if (bol.equalsIgnoreCase("false")) {
						if (!p.getServer().getInfo().getName().equalsIgnoreCase(servername)) {
							ServerInfo target = ProxyServer.getInstance().getServerInfo(servername);
							p.connect(target);
							p.sendMessage(new TextComponent(fm.prefix + "§aDu wurdest verbunden!"));
						}else {
							p.sendMessage(new TextComponent(fm.prefix + "§cDu bist schon auf dem Server!"));
						}
						String mapstring = senderp + ";" + recipient + ";true;" + servername;
						TPinvite.map.remove(s);
						TPinvite.map.add(mapstring);
					}else {
						p.sendMessage(new TextComponent(fm.prefix + "§cDu hast dich schon einmal verbunden!"));
					}
				}
			}
		}
	}
}

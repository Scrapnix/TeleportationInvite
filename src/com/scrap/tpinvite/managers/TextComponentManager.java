package com.scrap.tpinvite.managers;

import lombok.Data;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

@Data
public class TextComponentManager {
	
	public TextComponent tc(String text, String sendername) {
		String text2 = text.replace("&", "§");
		TextComponent tc = new TextComponent();
		FileManager fm = new FileManager();
		tc.setText(fm.prefix + text2);
        tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/teleporttopoint " + sendername));
        tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aKlicke §7zum Teleportieren!").create()));
        return tc;
	}
	
}
       
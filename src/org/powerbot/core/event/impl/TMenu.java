package org.powerbot.core.event.impl;

import java.awt.Graphics;

import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.script.event.TextPaintListener;
import org.powerbot.util.StringUtil;

public class TMenu implements TextPaintListener {
	public int draw(int idx, final Graphics render) {
		StringUtil.drawLine(render, idx++, "Menu");
		final String[] menuItems = Menu.getItems();
		for (final String menuItem : menuItems) {
			StringUtil.drawLine(render, idx++, " -> " + menuItem);
		}
		return idx;
	}
}
package org.powerbot.core.event.impl;

import java.util.logging.Logger;

import org.powerbot.script.event.MessageEvent;
import org.powerbot.script.event.MessageListener;

public class MessageLogger implements MessageListener {
	private static final Logger log = Logger.getLogger("Messages");

	public void messageReceived(final MessageEvent e) {
		if (e.getSender().equals("")) {
			log.info("[" + e.getId() + "] " + e.getMessage());
		} else {
			log.info("[" + e.getId() + "] " + e.getSender() + ": " + e.getMessage());
		}
	}
}

package com.officialsounding.notifybot;

import org.jivesoftware.smack.Chat;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class IRCListener extends ListenerAdapter<PircBotX> implements Listener<PircBotX> {

	private String notifyOn;
	private Chat xmpp;
	
	@Override
	public void onMessage(MessageEvent<PircBotX> event) throws Exception {
		if(event.getMessage().toLowerCase().contains(notifyOn)) {
			if(xmpp != null) {
				xmpp.sendMessage(event.getUser().getNick()+" in "+event.getChannel().getName()+" says: "+event.getMessage());
			} else {
				System.err.println(event.getUser().getNick()+" in "+event.getChannel().getName()+" says: "+event.getMessage());
			}
		}
	}

	public String getNotifyOn() {
		return notifyOn;
	}

	public void setNotifyOn(String notifyOn) {
		this.notifyOn = notifyOn;
	}

	public Chat getXmpp() {
		return xmpp;
	}

	public void setXmpp(Chat xmpp) {
		this.xmpp = xmpp;
	}

	
	
	
}

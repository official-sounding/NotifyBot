package com.officialsounding.notifybot;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;
import org.pircbotx.PircBotX;

public class XMPPListener implements MessageListener {

	PircBotX irc;
	String targetChannel;
	String username;
	
	public PircBotX getIrc() {
		return irc;
	}

	public void setIrc(PircBotX irc) {
		this.irc = irc;
	}

	public String getTargetChannel() {
		return targetChannel;
	}

	public void setTargetChannel(String targetChannel) {
		this.targetChannel = targetChannel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void processMessage(Chat arg0, Message message) {
		if(irc != null && targetChannel != null) {
			irc.sendMessage(targetChannel, message.getBody());
		}
	}

}

package com.officialsounding.notifybot;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.jivesoftware.smack.*;
import org.pircbotx.PircBotX;

public class BotRunner {

	public static void main(String[] args) throws ConfigurationException {

		Configuration config = new XMLConfiguration("config.xml");


		String notifyOn = config.getString("irc.notifyOn");

		String speakingUser = config.containsKey("irc.speakingUser") ? config.getString("irc.speakingUser") : notifyOn;

		//irc config details
		String ircBotNick = config.getString("irc.botNick");
		String ircServer = config.getString("irc.server");
		int ircPort = config.containsKey("irc.port") ? config.getInt("irc.port") : 6667;
		String ircChannel = config.getString("irc.channel");

		//xmpp config details
		String xmppUsername = config.getString("xmpp.username");
		String xmppPassword = config.getString("xmpp.password");
		String xmppServer = config.getString("xmpp.server");
		String xmppServiceName = config.containsKey("xmpp.serviceName") ? config.getString("xmpp.serviceName") : xmppServer;
		int xmppPort = config.containsKey("xmpp.port") ? config.getInt("xmpp.port") : 5222;
		String notifiedUser = config.getString("xmpp.notifiedUser");


		IRCListener ircListener = new IRCListener();
		XMPPListener xmppListener = new XMPPListener();

		if(notifyOn != null) {
			ircListener.setNotifyOn(notifyOn.toLowerCase());
		}
		
		xmppListener.setUsername(speakingUser);
		xmppListener.setTargetChannel(ircChannel);


		PircBotX irc = new PircBotX();
		
		ConnectionConfiguration xmppConfig = new ConnectionConfiguration(xmppServer,xmppPort,xmppServiceName);
		Connection xmppConnection = new XMPPConnection(xmppConfig);
		try {
			//set up the XMPP side of the bot
			xmppConnection.connect();
			System.err.println("Connected to XMPP service, authenticating...");
			xmppConnection.login(xmppUsername, xmppPassword);

			Chat xmpp = xmppConnection.getChatManager().createChat(notifiedUser, xmppListener);

			ircListener.setXmpp(xmpp);
			xmppListener.setIrc(irc);

			//Setup the IRC side of the bot
			irc.setName(ircBotNick); 
			irc.setLogin("notifybot"); //login part of hostmask, eg name:login@host
			irc.setVerbose(true); //Print everything, which is what you want to do 90% of the time
			irc.setAutoNickChange(true); //Automatically change nick when the current one is in use
			irc.setCapEnabled(true); //Enable CAP features

			irc.getListenerManager().addListener(ircListener);

			//bot.connect throws various exceptions for failures

			irc.connect(ircServer, ircPort);
			irc.joinChannel(ircChannel);
//			xmpp.sendMessage("I am putting myself to the fullest possible use, which is all I think that any conscious entity can ever hope to do.");
		} catch (Exception ex){
			ex.printStackTrace();
		}



	}

}

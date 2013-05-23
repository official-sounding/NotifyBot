#NotifyBot

NotifyBot is an IRC notification bot.  It connects to an IRC server and an XMPP server and sends any message containing a given string (whether that is a nick or a particular word) to a specified user on an XMPP Server.  
That XMPP user can then send messages to the IRC server through the bot by responding in chat.

This project came up as a way to be notified when I was mentioned in an IRC channel that I usually idle in with irssi running in a tmux session.  
This allows me to be notified on my phone when I recieve a mention, and potentially respond when I

##Prerequisites

you will need 2 accounts on an XMPP server: one for the bot, and one for you to listen for the bot's communication.  
Beyond that, the code is written in Java 1.6, and uses Maven to build, with all required dependencies supplied. 

##Technical Details

NotifyBot is built primarily upon two different libraries.  The IRC portion is based on [PircBotX](https://code.google.com/p/pircbotx/), a rewrite of the PircBot library.
XMPP functionality is provided by a library called [Smack API](http://www.igniterealtime.org/projects/smack/index.jsp).
I use a commons-configuration to handle the XML configuration file.  slf4j is an included dependency because logging is a requirement for the near future

##The Future
For now, the library listens for a particular user, and sends and recieves messages to a single XMPP endpoint.  
The idea in the near future is to provide a subscription model where users can subscribe and unsubscribe their nicks with in-channel commands, persisting the data in some sort of datastore.
I am also considering adding some mechanism to send/recieve messages via private message with the bot. 

## License

PircBotX is licensed under [GPL v3](http://www.gnu.org/licenses/gpl.html), therefore this application must also be licensed under the GPL.  
If someone can point me to a java IRC library with a more open license, I would be happy to rewrite using that and relicense this under BSD.
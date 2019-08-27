package net.x666c.disc;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class DiscordBot {
	
	public static void main(String[] args) {
		final DiscordClient client = new DiscordClientBuilder("NjEyMzQ3MDMyMjExODE2NTA3.XWUfdQ.HisB-nSuFHFLrdSUQw_bd-xsPcc").build();

		client.getEventDispatcher().on(ReadyEvent.class)
		        .subscribe(ready -> System.out.println("Logged in as " + ready.getSelf().getUsername()));

		client.getEventDispatcher().on(MessageCreateEvent.class)
		        .map(MessageCreateEvent::getMessage)
		        .filter(msg -> msg.getContent().map("!ping"::equals).orElse(false))
		        .flatMap(Message::getChannel)
		        .flatMap(channel -> channel.createMessage("Pong!"))
		        .subscribe();

		client.login().block();
	}

}

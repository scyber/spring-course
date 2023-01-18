package ru.otus.spring.events;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Component
public class CustomWebSocketHandler implements WebSocketHandler {

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		// var f = Flux.just("A", "B", "C").map(m -> session.textMessage(m));
		// return session.send(f);
		return session.send(session.receive().map(msg -> "RECEIVE ON SERVER ::" + msg.getPayloadAsText())
				.map(session::textMessage));
	}
}

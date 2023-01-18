package ru.otus.spring.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import ru.otus.spring.events.BlogCreatedEventPublisher;
import ru.otus.spring.events.BlogCreationEvent;
import java.util.Collections;

@Log4j2
@Configuration
public class WebSocketConfiguration {

	@Bean
	HandlerMapping handlerMapping(WebSocketHandler wsh) {
		return new SimpleUrlHandlerMapping() {
			{
				setUrlMap(Collections.singletonMap("/ws/blogs", wsh));
				setOrder(10);
			}

		};
	}

	@Bean
	WebSocketHandlerAdapter webSocketHandlerAdapter() {
		return new WebSocketHandlerAdapter();
	}

	@Bean
	WebSocketHandler webSocketHandler(ObjectMapper objectMapper, BlogCreatedEventPublisher eventPublisher) {

		Flux<BlogCreationEvent> publish = Flux.create(eventPublisher).share();

		return session -> {
			Flux<WebSocketMessage> messageFlux = publish.map(evt -> {
				try {
					return objectMapper.writeValueAsString(evt.getSource());
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}

			}).map(str -> {
				log.info("send message " + str);
				return session.textMessage(str);
			});
			return session.send(messageFlux);
		};
	}

}

package ru.otus.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.domain.Butterfly;
import ru.otus.domain.Catterpillar;

import java.util.Collection;

@MessagingGateway
public interface Convertion {

    @Gateway(requestChannel = "butterflyChannel", replyChannel = "catterpillarChannel")
    Collection<Catterpillar> process(Collection<Butterfly> orderItem);

}

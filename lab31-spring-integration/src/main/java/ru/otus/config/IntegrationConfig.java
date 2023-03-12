package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

import java.util.concurrent.TimeUnit;

@Configuration
public class IntegrationConfig {

    @Bean
    public QueueChannel butterflyChannel() {
        return MessageChannels.queue( 100 ).get();
    }

    @Bean
    public PublishSubscribeChannel catterpillarChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow transformFlow() {
        return IntegrationFlows.from( "butterflyChannel" )
                .split()
                .handle( "transformService", "transform" )
                .aggregate()
                .channel( "catterpillarChannel" )
                .get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(3, TimeUnit.SECONDS).maxMessagesPerPoll(10).get();
    }
}

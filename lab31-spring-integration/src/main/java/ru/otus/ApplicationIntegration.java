package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.config.ButterflyGenerator;
import ru.otus.domain.Butterfly;
import ru.otus.domain.Catterpillar;
import ru.otus.gateway.Convertion;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class ApplicationIntegration {

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationIntegration.class);

        Convertion convertion = ctx.getBean(Convertion.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        while (true) {
            pool.execute(() -> {
                Collection<Butterfly> butterflys = new ButterflyGenerator().getButterflyes();
                butterflys.forEach(b -> System.out.println("New Butterfly comming .. " + b.getName()));
                Collection<Catterpillar> catterpillars = convertion.process(butterflys);
                catterpillars.forEach(c -> System.out.println("Transformed catterpillar " + c.getName()));
            });
        }

    }
}

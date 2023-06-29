package ru.otus.config;

import com.fasterxml.jackson.databind.Module;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


//@Configuration
public class LibraryLoadBalancingConfiguration {

//    @Bean
//    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
//        return ServiceInstanceListSupplier.builder()
//                .withBlockingDiscoveryClient()
//                .withSameInstancePreference()
//                .withBlockingHealthChecks()
//                .build(context);
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public Module pageJacksonModule() {
//        return new PageJacksonModule();
//    }
//
//    @Bean
//    public Module sortJacksonModule() {
//        return new SortJacksonModule();
//    }


}

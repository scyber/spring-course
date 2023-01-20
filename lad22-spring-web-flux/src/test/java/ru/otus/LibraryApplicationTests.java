package ru.otus;

import org.junit.jupiter.api.*;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;


@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class LibraryApplicationTests {

//    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.0");
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    @BeforeAll
//    static void setUp() {
//        mongoDBContainer.start();
//    }
//
//    @AfterAll
//    static void clean() {
//        mongoDBContainer.stop();
//    }
//
//    @Test
//    @DisplayName("Общая проверка контекста")
//    void bookDaoTest() {
//
//    }


}

package ru.otus.config;


import java.util.List;


//import org.springframework.data.mongodb.core.MongoTemplate;
//import com.github.cloudyrock.mongock.ChangeLog;
//import com.github.cloudyrock.mongock.ChangeSet;
//import com.mongodb.client.MongoDatabase;
//import lombok.val;
//import ru.otus.domain.Author;
//import ru.otus.domain.Book;
//import ru.otus.domain.Genre;


//@ChangeLog(order = "000")
public class InitDataConfiguration {
	
//	Genre drama;
//    Genre comedy;
//    Genre crime;
//    Genre fantasy;
//
//    Author pushkin;
//    Author jamesChaise;
//    Author ernestHemingway;
//    Author julesVerne;
//    Author levTolstoy;
//
//    @ChangeSet(order = "001", id = "testd", author = "root", runAlways = true)
//    public void dropDb(MongoDatabase db) {
//        db.drop();
//    }
//
//    @ChangeSet(order = "002", id = "2", author = "admin", runAlways = true)
//    public void insertBook(MongoTemplate template){
//        drama = new Genre("Drama");
//        comedy = new Genre("Comedy");
//        fantasy = new Genre("Fantasy");
//        crime = new Genre("Crime");
//        val genres = List.of(drama,comedy,crime,fantasy);
//        genres.forEach(g -> template.save(g));
//    }
//    @ChangeSet(order = "003", id = "3", author = "admin", runAlways = true)
//    public void insertAuthors(MongoTemplate template){
//        levTolstoy = new Author("Лев Толстой");
//        julesVerne = new Author("Jules Verne");
//        ernestHemingway = new Author("Ernest Hemingway");
//        jamesChaise = new Author("James Hadley Chase");
//        pushkin = new Author("Александр Пушкин");
//        val authors = List.of(ernestHemingway,levTolstoy,jamesChaise,julesVerne,pushkin);
//        authors.forEach(a -> template.save(a));
//    }
//    @ChangeSet(order = "004", id = "4", author = "admin", runAlways = true)
//    public void insertBooks(MongoTemplate template){
//        val oldManAndSea = new Book("The Old Man and The Sea");
//        oldManAndSea.setAuthors(List.of(ernestHemingway));
//        oldManAndSea.setGenres(List.of(drama));
//        template.save(oldManAndSea);
//
//        val theSunAlsoRise = new Book("The Sun Also Rises");
//        theSunAlsoRise.setAuthors(List.of(ernestHemingway));
//        theSunAlsoRise.setGenres(List.of(fantasy));
//        template.save(theSunAlsoRise);
//
//        val journeyToTheEarth = new Book("Journey to the Centre of the Earth");
//        journeyToTheEarth.setAuthors(List.of(julesVerne));
//        journeyToTheEarth.setGenres(List.of(fantasy));
//        template.save(journeyToTheEarth);
//
//        val theWorldInPocket = new Book("The World In My Pocket");
//        theWorldInPocket.setGenres(List.of(crime));
//        theWorldInPocket.setAuthors(List.of(jamesChaise));
//        template.save(theWorldInPocket);
//    }

}

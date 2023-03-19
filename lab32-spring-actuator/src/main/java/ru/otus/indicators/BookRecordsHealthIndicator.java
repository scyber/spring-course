package ru.otus.indicators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import ru.otus.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class BookRecordsHealthIndicator extends AbstractHealthIndicator {

    private final BookRepository bookRepository;

    private long getNumBookRecords() {
        return bookRepository.count();
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        var numOfBookRecords = getNumBookRecords();
        if(numOfBookRecords > 0) {
            builder.withDetail("books records",numOfBookRecords ).status("Up").build();
        } else {
             builder.withDetail("books records is ", 0).status("NO Records Found").down().build();
        }
    }

}

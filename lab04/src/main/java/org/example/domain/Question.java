package org.example.domain;


import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Question {
    @CsvBindByPosition(position = 0)
    private Long id;
    @CsvBindByPosition(position = 1)
    private String context;

    @CsvBindAndSplitByPosition(position = 2, elementType = Long.class, collectionType = LinkedList.class)
    private LinkedList<Long> answersList;

    @CsvBindAndSplitByPosition(position = 3, elementType = Long.class, collectionType = List.class)
    private List<Long> correctAnswers;


}

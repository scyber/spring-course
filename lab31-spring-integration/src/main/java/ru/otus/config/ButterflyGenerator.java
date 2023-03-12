package ru.otus.config;

import ru.otus.domain.Butterfly;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ButterflyGenerator {
    private final List<String> BUTTERFLYS = new ArrayList<>(List.of("Black Prince", "Papilio machaon Linnaeus", "Parnassius", "Carcharodus orientalis"));

    private Butterfly genButterfly() {
        var index = ThreadLocalRandom.current().nextInt(0, BUTTERFLYS.size() - 1);
        var name = BUTTERFLYS.get(index);
        return new Butterfly(name);
    }

    public List<Butterfly> getButterflyes() {
        var size = ThreadLocalRandom.current().nextInt(50);
        List<Butterfly> butterflyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var butterfly = genButterfly();
            butterflyList.add(butterfly);
        }
        return butterflyList;
    }

}


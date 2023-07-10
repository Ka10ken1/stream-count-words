package com.epam.rd.autotasks;

import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> wordCounts = lines.stream()
                .flatMap(line -> Arrays.stream(line.split("[^\\p{L}\\d]+")))
                .filter(word -> word.length() >= 4)
                .map(String::toLowerCase)
                .collect(
                        HashMap::new,
                        (map, word) -> map.put(word, map.getOrDefault(word, 0) + 1),
                        HashMap::putAll
                );

        List<Map.Entry<String, Integer>> sortedEntries = wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= 10)
                .sorted(
                        Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .collect(Collectors.toList());

        return sortedEntries.stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

}

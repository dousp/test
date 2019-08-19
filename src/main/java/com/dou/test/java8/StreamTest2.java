package com.dou.test.java8;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author dsp
 * @date 2019-07-03
 */
public class StreamTest2 {

    public static void main(String[] args) {

        final String SENTENCE = " Nel mezzo del cammin di nostra vita " + "mi ritrovai in una selva oscura" + " ché la dritta via era smarrita ";

        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static int countWordsIteratively(String s) {
        char[] chars = s.toCharArray();
        boolean flag = true;
        int count = 0;
        for (char c : chars) {
            if (Character.isWhitespace(c)) {
                flag = true;
            } else {
                if (flag) {
                    count++;
                }
                flag = false;
            }
        }
        return count;
    }
}

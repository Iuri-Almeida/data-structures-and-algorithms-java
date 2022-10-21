package br.com.itau.letscode.ialmeida.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class SearchTest {

    @Test
    void sequentialSearchOk() {
        Search<Integer> search = new SearchImpl<>();

        Integer e = random();
        Integer[] unordered = new Integer[] {random(), random(), random(), e, random(), random(), random(), random(), random(), random()};

        assertThat(search.sequentialSearch(unordered, e)).isEqualTo(3);
    }

    @Test
    void binarySearchOk() {
        Search<Integer> search = new SearchImpl<>();

        Integer e = 5;
        Integer[] ordered = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        assertThat(search.sequentialSearch(ordered, e)).isEqualTo(4);
    }

    private Integer random() {
        return new Random().nextInt();
    }

}
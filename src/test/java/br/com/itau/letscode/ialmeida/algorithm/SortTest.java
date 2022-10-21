package br.com.itau.letscode.ialmeida.algorithm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortTest {

    @Test
    void bubbleSortOk() {
        Sort<Integer> sort = new SortImpl<>();

        Integer[] unordered = new Integer[] {4, 2, 7, 8, 3, 10, 5, 9, 1, 6};

        assertThat(sort.bubbleSort(unordered)).isEqualTo(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    void selectionSortOk() {
        Sort<Integer> sort = new SortImpl<>();

        Integer[] unordered = new Integer[] {4, 2, 7, 8, 3, 10, 5, 9, 1, 6};

        assertThat(sort.selectionSort(unordered)).isEqualTo(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    void insertionSortOk() {
        Sort<Integer> sort = new SortImpl<>();

        Integer[] unordered = new Integer[] {4, 2, 7, 8, 3, 10, 5, 9, 1, 6};

        assertThat(sort.insertionSort(unordered)).isEqualTo(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    void mergeSortOk() {
        Sort<Integer> sort = new SortImpl<>();

        Integer[] unordered = new Integer[] {4, 2, 7, 8, 3, 10, 5, 9, 1, 6};

        assertThat(sort.mergeSort(unordered)).isEqualTo(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    void quickSortOk() {
        Sort<Integer> sort = new SortImpl<>();

        Integer[] unordered = new Integer[] {4, 2, 7, 8, 3, 10, 5, 9, 1, 6};

        assertThat(sort.quickSort(unordered)).isEqualTo(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

}
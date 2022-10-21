package br.com.itau.letscode.ialmeida.algorithm;

public interface Sort<T extends Comparable<T>> {

    T[] bubbleSort(T[] elements);

    T[] selectionSort(T[] elements);

    T[] insertionSort(T[] elements);

    T[] mergeSort(T[] elements);

    T[] quickSort(T[] elements);
}

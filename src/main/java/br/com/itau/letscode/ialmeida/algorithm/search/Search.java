package br.com.itau.letscode.ialmeida.algorithm.search;

public interface Search<T extends Comparable<T>> {

    int sequentialSearch(T[] elements, T element);

    int binarySearch(T[] elements, T element);

}

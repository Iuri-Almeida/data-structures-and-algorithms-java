package br.com.itau.letscode.ialmeida.algorithm.search;

public class SearchImpl<T extends Comparable<T>> implements Search<T> {
    @Override
    public int sequentialSearch(T[] elements, T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int binarySearch(T[] elements, T element) {
        int p1 = 0;
        int p2 = elements.length - 1;

        while (p1 < p2) {
            int mid = (int) Math.floor((p1 + p2) / 2.0);

            if (element.compareTo(elements[mid]) > 0) {
                p1 = mid;
            } else if (element.compareTo(elements[mid]) < 0) {
                p2 = mid;
            } else {
                return mid;
            }
        }

        return -1;
    }
}

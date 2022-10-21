package br.com.itau.letscode.ialmeida.algorithm;

import java.util.*;

public class SortImpl<T extends Comparable<T>> implements Sort<T> {
    @Override
    public T[] bubbleSort(T[] elements) {
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length - 1; j++) {
                if (elements[j].compareTo(elements[j + 1]) > 0) {
                    T aux = elements[j + 1];
                    elements[j + 1] = elements[j];
                    elements[j] = aux;
                }
            }
        }
        return elements;
    }

    @Override
    public T[] selectionSort(T[] elements) {
        for (int i = 0; i < elements.length; i++) {
            int pos = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (elements[j].compareTo(elements[pos]) < 0) {
                    pos = j;
                }
            }
            T aux = elements[i];
            elements[i] = elements[pos];
            elements[pos] = aux;
        }
        return elements;
    }

    @Override
    public T[] insertionSort(T[] elements) {
        for (int i = 1; i < elements.length; i++) {
            T aux = elements[i];
            int j = i - 1;
            while (j >= 0 && aux.compareTo(elements[j]) < 0) {
                elements[j + 1] = elements[j];
                j--;
            }
            elements[j + 1] = aux;
        }
        return elements;
    }

    @Override
    public T[] mergeSort(T[] elements) {
        if (Optional.ofNullable(elements).isEmpty()) {
            return null;
        }

        if (elements.length == 1) {
            return elements;
        }

        int mid = (int) Math.floor(elements.length / 2.0);

        T[] left = Arrays.copyOfRange(elements, 0, mid);
        T[] right = Arrays.copyOfRange(elements, mid, elements.length);

        return this.merge(this.mergeSort(left), this.mergeSort(right));
    }

    private T[] merge(T[] left, T[] right) {
        List<T> aux = new ArrayList<>();

        int posLeft = 0;
        int posRight = 0;

        while (posLeft < left.length && posRight < right.length) {
            if (left[posLeft].compareTo(right[posRight]) < 0) {
                aux.add(left[posLeft]);
                posLeft++;
            } else {
                aux.add(right[posRight]);
                posRight++;
            }
        }

        return this.concat(aux, Arrays.copyOfRange(left, posLeft, left.length), Arrays.copyOfRange(right, posRight, right.length));
    }

    // https://www.baeldung.com/java-concatenate-arrays
    private T[] concat(List<T> list, T[] arr1, T[] arr2) {
        Collections.addAll(list, arr1);
        Collections.addAll(list, arr2);

        return (T[]) list.toArray(new Integer[] {});
    }

    @Override
    public T[] quickSort(T[] elements) {
        if (elements.length < 2) {
            return elements;
        }

        List<T> low = new ArrayList<>();
        List<T> same = new ArrayList<>();
        List<T> high = new ArrayList<>();

        T pivot = elements[this.random(elements.length)];

        for (T e : elements) {
            if (e.compareTo(pivot) < 0) {
                low.add(e);
            } else if (e.compareTo(pivot) > 0) {
                high.add(e);
            } else {
                same.add(e);
            }
        }

        return this.concat(
                new ArrayList<>(),
                this.quickSort((T[]) low.toArray(new Integer[] {})),
                (T[]) same.toArray(new Integer[] {}),
                this.quickSort((T[]) high.toArray(new Integer[] {}))
        );
    }

    // https://www.baeldung.com/java-concatenate-arrays
    private T[] concat(List<T> list, T[] arr1, T[] arr2, T[] arr3) {
        Collections.addAll(list, arr1);
        Collections.addAll(list, arr2);
        Collections.addAll(list, arr3);

        return (T[]) list.toArray(new Integer[] {});
    }

    private int random(int limit) {
        return new Random().nextInt(limit);
    }
}

package br.com.itau.letscode.ialmeida.datastructure.linkedlist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CircularDoublyLinkedListTest {

    @Test
    void initializationOk() {
        int head = random();

        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedListImpl<>(head);

        assertThat(cdll.size()).isEqualTo(1);
        assertThat(cdll.exists(head)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("insert")
    void insertOk(int head, int[] elements, int expectedSize) {
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedListImpl<>(head);

        for(int element: elements) {
            cdll.insert(element);
        }

        assertThat(cdll.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> insert() {
        int e1 = random();
        int e2 = random();
        int e3 = random();

        return Stream.of(
                arguments(random(), new int[] {e1, e2, e3}, 4),
                arguments(random(), new int[] {e1, e2}, 3),
                arguments(random(), new int[] {e1}, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("remove")
    void removeOk(CircularDoublyLinkedList<Integer> cdllToBeUsed, int expectedElement, int expectedSize) {
        assertThat(cdllToBeUsed.remove(expectedElement)).isEqualTo(expectedElement);
        assertThat(cdllToBeUsed.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> remove() {
        int e1 = random();
        int e2 = random();

        CircularDoublyLinkedList<Integer> cdll1 = new CircularDoublyLinkedListImpl<>(random());
        cdll1.insert(e1);

        CircularDoublyLinkedList<Integer> cdll2 = new CircularDoublyLinkedListImpl<>(random());
        cdll2.insert(e2);
        cdll2.insert(e1);

        CircularDoublyLinkedList<Integer> cdll3 = new CircularDoublyLinkedListImpl<>(random());
        cdll3.insert(e1);
        cdll3.insert(e2);

        return Stream.of(
                arguments(cdll1, e1, 1),
                arguments(cdll2, e1, 2),
                arguments(cdll3, e2, 2)
        );
    }

    @Test
    void removeFailedCannotRemoveLastElement() {
        int head = random();
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedListImpl<>(head);

        assertThatThrownBy(() -> cdll.remove(head))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void removeFailedCannotFindElement() {
        int head = random();
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedListImpl<>(head);

        assertThatThrownBy(() -> cdll.remove(random()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void insertFailedCannotInsertDuplicatedElements() {
        int head = random();
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedListImpl<>(head);

        assertThatThrownBy(() -> cdll.insert(head))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void verifyCdll() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();

        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedListImpl<>(head);
        assertThat(cdll.size()).isEqualTo(1);

        cdll.insert(e1);
        assertThat(cdll.size()).isEqualTo(2);

        cdll.insert(e2);
        assertThat(cdll.size()).isEqualTo(3);

        cdll.insert(e3);
        assertThat(cdll.size()).isEqualTo(4);

        assertThat(cdll.remove(e3)).isEqualTo(e3);
        assertThat(cdll.remove(head)).isEqualTo(head);
        assertThat(cdll.remove(e1)).isEqualTo(e1);
    }

    private static int random() {
        return new Random().nextInt();
    }

}
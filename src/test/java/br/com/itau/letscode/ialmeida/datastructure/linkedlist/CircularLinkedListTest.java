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

class CircularLinkedListTest {

    @Test
    void initializationOk() {
        int head = random();

        CircularLinkedList<Integer> cll = new CircularLinkedListImpl<>(head);

        assertThat(cll.size()).isEqualTo(1);
        assertThat(cll.exists(head)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("insert")
    void insertOk(int head, int[] elements, int expectedSize) {
        CircularLinkedList<Integer> cll = new CircularLinkedListImpl<>(head);

        for(int element: elements) {
            cll.insert(element);
        }

        assertThat(cll.size()).isEqualTo(expectedSize);
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
    void removeOk(CircularLinkedList<Integer> cllToBeUsed, int expectedElement, int expectedSize) {
        assertThat(cllToBeUsed.remove(expectedElement)).isEqualTo(expectedElement);
        assertThat(cllToBeUsed.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> remove() {
        int e1 = random();
        int e2 = random();

        CircularLinkedList<Integer> cll1 = new CircularLinkedListImpl<>(random());
        cll1.insert(e1);

        CircularLinkedList<Integer> cll2 = new CircularLinkedListImpl<>(random());
        cll2.insert(e2);
        cll2.insert(e1);

        CircularLinkedList<Integer> cll3 = new CircularLinkedListImpl<>(random());
        cll3.insert(e1);
        cll3.insert(e2);

        return Stream.of(
                arguments(cll1, e1, 1),
                arguments(cll2, e1, 2),
                arguments(cll3, e2, 2)
        );
    }

    @Test
    void removeFailedCannotRemoveLastElement() {
        int head = random();
        CircularLinkedList<Integer> cll = new CircularLinkedListImpl<>(head);

        assertThatThrownBy(() -> cll.remove(head))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void removeFailedCannotFindElement() {
        int head = random();
        CircularLinkedList<Integer> cll = new CircularLinkedListImpl<>(head);

        assertThatThrownBy(() -> cll.remove(random()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void insertFailedCannotInsertDuplicatedElements() {
        int head = random();
        CircularLinkedList<Integer> cll = new CircularLinkedListImpl<>(head);

        assertThatThrownBy(() -> cll.insert(head))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void verifyCll() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();

        CircularLinkedList<Integer> cll = new CircularLinkedListImpl<>(head);
        assertThat(cll.size()).isEqualTo(1);

        cll.insert(e1);
        assertThat(cll.size()).isEqualTo(2);

        cll.insert(e2);
        assertThat(cll.size()).isEqualTo(3);

        cll.insert(e3);
        assertThat(cll.size()).isEqualTo(4);

        assertThat(cll.remove(e3)).isEqualTo(e3);
        assertThat(cll.remove(head)).isEqualTo(head);
        assertThat(cll.remove(e1)).isEqualTo(e1);
    }

    private static int random() {
        return new Random().nextInt();
    }

}
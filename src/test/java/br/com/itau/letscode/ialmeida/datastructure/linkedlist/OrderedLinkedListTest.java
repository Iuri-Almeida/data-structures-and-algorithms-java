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

class OrderedLinkedListTest {

    @Test
    void initializationOk() {
        int head = random();

        OrderedLinkedList<Integer> oll = new OrderedLinkedListImpl<>(head);

        assertThat(oll.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("insert")
    void insertOk(int head, int[] elements, int expectedSize) {
        OrderedLinkedList<Integer> oll = new OrderedLinkedListImpl<>(head);

        for(int element: elements) {
            oll.insert(element);
        }

        assertThat(oll.size()).isEqualTo(expectedSize);
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
    void removeOk(OrderedLinkedList<Integer> ollToBeUsed, int expectedElement, int expectedSize) {
        assertThat(ollToBeUsed.remove(expectedElement)).isEqualTo(expectedElement);
        assertThat(ollToBeUsed.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> remove() {
        int e1 = random();
        int e2 = random();

        OrderedLinkedList<Integer> oll1 = new OrderedLinkedListImpl<>(random());
        oll1.insert(e1);

        OrderedLinkedList<Integer> oll2 = new OrderedLinkedListImpl<>(random());
        oll2.insert(e2);
        oll2.insert(e1);

        OrderedLinkedList<Integer> oll3 = new OrderedLinkedListImpl<>(random());
        oll3.insert(e1);
        oll3.insert(e2);

        return Stream.of(
                arguments(oll1, e1, 1),
                arguments(oll2, e1, 2),
                arguments(oll3, e2, 2)
        );
    }

    @Test
    void removeFailedCannotRemoveHead() {
        int head = random();
        OrderedLinkedList<Integer> dll = new OrderedLinkedListImpl<>(head);

        assertThatThrownBy(() -> dll.remove(head))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void removeFailedCannotRemoveNonExistingElement() {
        int head = random();
        OrderedLinkedList<Integer> dll = new OrderedLinkedListImpl<>(head);

        assertThatThrownBy(() -> dll.remove(random()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void verifyOll() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();

        OrderedLinkedList<Integer> oll = new OrderedLinkedListImpl<>(head);
        assertThat(oll.size()).isEqualTo(1);

        oll.insert(e1);
        assertThat(oll.size()).isEqualTo(2);

        oll.insert(e2);
        assertThat(oll.size()).isEqualTo(3);

        oll.insert(e3);
        assertThat(oll.size()).isEqualTo(4);

        assertThat(oll.remove(e3)).isEqualTo(e3);
        assertThat(oll.remove(head)).isEqualTo(head);
        assertThat(oll.remove(e1)).isEqualTo(e1);
    }

    private static int random() {
        return new Random().nextInt();
    }

}
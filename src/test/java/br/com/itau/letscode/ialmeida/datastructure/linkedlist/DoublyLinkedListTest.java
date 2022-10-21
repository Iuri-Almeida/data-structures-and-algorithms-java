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

class DoublyLinkedListTest {

    @Test
    void initializationOk() {
        int head = random();

        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(head);

        assertThat(dll.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("prepend")
    void prependOk(int head, int[] elements, int expectedSize) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(head);

        for(int element: elements) {
            dll.prepend(element);
        }

        assertThat(dll.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> prepend() {
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
    @MethodSource("append")
    void appendOk(int head, int[] elements, int expectedSize) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(head);

        for(int element: elements) {
            dll.append(element);
        }

        assertThat(dll.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> append() {
        int e1 = random();
        int e2 = random();
        int e3 = random();

        return Stream.of(
                arguments(random(), new int[] {e1, e2, e3}, 4),
                arguments(random(), new int[] {e1, e2}, 3),
                arguments(random(), new int[] {e1}, 2)
        );
    }

    @Test
    void insertOk() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();
        int e4 = random();

        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(head);

        dll.append(e1);
        assertThat(dll.size()).isEqualTo(2);

        dll.insert(e2, 1);
        assertThat(dll.size()).isEqualTo(3);

        dll.insert(e3, 1);
        assertThat(dll.size()).isEqualTo(4);

        dll.insert(e4, 1);
        assertThat(dll.size()).isEqualTo(5);

        assertThat(dll.popLeft()).isEqualTo(head);
        assertThat(dll.popLeft()).isEqualTo(e4);
        assertThat(dll.popLeft()).isEqualTo(e3);
        assertThat(dll.popLeft()).isEqualTo(e2);
    }

    @ParameterizedTest
    @MethodSource("popLeft")
    void popLeftOk(DoublyLinkedList<Integer> dllToBeUsed, int expectedElement, int expectedSize) {
        assertThat(dllToBeUsed.popLeft()).isEqualTo(expectedElement);
        assertThat(dllToBeUsed.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> popLeft() {
        int e1 = random();
        int e2 = random();

        DoublyLinkedList<Integer> dll1 = new DoublyLinkedListImpl<>(random());
        dll1.prepend(e1);

        DoublyLinkedList<Integer> dll2 = new DoublyLinkedListImpl<>(random());
        dll2.prepend(e2);
        dll2.prepend(e1);

        DoublyLinkedList<Integer> dll3 = new DoublyLinkedListImpl<>(random());
        dll3.prepend(e1);
        dll3.prepend(e2);

        return Stream.of(
                arguments(dll1, e1, 1),
                arguments(dll2, e1, 2),
                arguments(dll3, e2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("pop")
    void popOk(DoublyLinkedList<Integer> dllToBeUsed, int expectedElement, int expectedSize) {
        assertThat(dllToBeUsed.pop()).isEqualTo(expectedElement);
        assertThat(dllToBeUsed.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> pop() {
        int e1 = random();
        int e2 = random();

        DoublyLinkedList<Integer> dll1 = new DoublyLinkedListImpl<>(random());
        dll1.append(e1);

        DoublyLinkedList<Integer> dll2 = new DoublyLinkedListImpl<>(random());
        dll2.append(e1);
        dll2.append(e2);

        return Stream.of(
                arguments(dll1, e1, 1),
                arguments(dll2, e2, 2)
        );
    }

    @Test
    void popFailedCannotRemoveHead() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(random());

        assertThatThrownBy(dll::pop)
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(dll::popLeft)
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void insertFailedCannotInsertInNegativeIndexes() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(random());

        assertThatThrownBy(() -> dll.insert(3, -3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void insertFailedCannotInsertIndexesBiggerOrEqualsToSize() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(random());

        assertThatThrownBy(() -> dll.insert(3, dll.size()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> dll.insert(3, dll.size() + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void verifyDll() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();

        DoublyLinkedList<Integer> dll = new DoublyLinkedListImpl<>(head);
        assertThat(dll.size()).isEqualTo(1);

        dll.append(e1);
        assertThat(dll.size()).isEqualTo(2);

        dll.append(e2);
        assertThat(dll.size()).isEqualTo(3);

        dll.append(e3);
        assertThat(dll.size()).isEqualTo(4);

        assertThat(dll.pop()).isEqualTo(e3);
        assertThat(dll.popLeft()).isEqualTo(head);
        assertThat(dll.popLeft()).isEqualTo(e1);
    }

    private static int random() {
        return new Random().nextInt();
    }

}
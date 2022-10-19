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

class LinkedListTest {

    @Test
    void initializationOk() {
        int head = random();

        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList2.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("prepend")
    void prependOk(int head, int[] elements, int expectedSize) {
        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        for(int element: elements) {
            linkedList.prepend(element);
            linkedList2.prepend(element);
        }

        assertThat(linkedList.size()).isEqualTo(expectedSize);
        assertThat(linkedList2.size()).isEqualTo(expectedSize - 1);
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
        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        for(int element: elements) {
            linkedList.append(element);
            linkedList2.append(element);
        }

        assertThat(linkedList.size()).isEqualTo(expectedSize);
        assertThat(linkedList2.size()).isEqualTo(expectedSize - 1);
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

        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        linkedList.append(e1);
        assertThat(linkedList.size()).isEqualTo(2);

        linkedList.insert(e2, 1);
        assertThat(linkedList.size()).isEqualTo(3);

        linkedList.insert(e3, 1);
        assertThat(linkedList.size()).isEqualTo(4);

        linkedList.insert(e4, 1);
        assertThat(linkedList.size()).isEqualTo(5);

        assertThat(linkedList.popLeft()).isEqualTo(head);
        assertThat(linkedList.popLeft()).isEqualTo(e4);
        assertThat(linkedList.popLeft()).isEqualTo(e3);
        assertThat(linkedList.popLeft()).isEqualTo(e2);
        assertThat(linkedList.popLeft()).isEqualTo(e1);
        assertThat(linkedList.size()).isEqualTo(0);

        linkedList2.append(e1);
        assertThat(linkedList2.size()).isEqualTo(1);

        linkedList2.append(e2);
        assertThat(linkedList2.size()).isEqualTo(2);

        linkedList2.insert(e3, 1);
        assertThat(linkedList2.size()).isEqualTo(3);

        linkedList2.insert(e4, 1);
        assertThat(linkedList2.size()).isEqualTo(4);

        assertThat(linkedList2.popLeft()).isEqualTo(e1);
        assertThat(linkedList2.popLeft()).isEqualTo(e4);
        assertThat(linkedList2.popLeft()).isEqualTo(e3);
        assertThat(linkedList2.popLeft()).isEqualTo(e2);
        assertThat(linkedList2.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("popLeft")
    void popLeftOk(LinkedList<Integer> linkedListToBeUsed, int expectedElement) {
        assertThat(linkedListToBeUsed.popLeft()).isEqualTo(expectedElement);
    }

    static Stream<Arguments> popLeft() {
        int e1 = random();
        int e2 = random();

        LinkedList<Integer> linkedList1 = new LinkedListImpl<>(random());
        linkedList1.prepend(e1);

        LinkedList<Integer> linkedList2 = new LinkedListImpl<>(random());
        linkedList2.prepend(e2);
        linkedList2.prepend(e1);

        LinkedList<Integer> linkedList3 = new LinkedListImpl<>(random());
        linkedList3.prepend(e1);
        linkedList3.prepend(e2);

        LinkedList<Integer> linkedList4 = new LinkedListImpl<>();
        linkedList4.prepend(e1);

        LinkedList<Integer> linkedList5 = new LinkedListImpl<>();
        linkedList5.prepend(e2);
        linkedList5.prepend(e1);

        LinkedList<Integer> linkedList6 = new LinkedListImpl<>();
        linkedList6.prepend(e1);
        linkedList6.prepend(e2);

        return Stream.of(
                arguments(linkedList1, e1),
                arguments(linkedList2, e1),
                arguments(linkedList3, e2),
                arguments(linkedList4, e1),
                arguments(linkedList5, e1),
                arguments(linkedList6, e2)
        );
    }

    @ParameterizedTest
    @MethodSource("pop")
    void popOk(LinkedList<Integer> linkedListToBeUsed, int expectedElement) {
        assertThat(linkedListToBeUsed.pop()).isEqualTo(expectedElement);
    }

    static Stream<Arguments> pop() {
        int e1 = random();
        int e2 = random();

        LinkedList<Integer> linkedList1 = new LinkedListImpl<>(random());
        linkedList1.append(e1);

        LinkedList<Integer> linkedList2 = new LinkedListImpl<>(random());
        linkedList2.append(e1);
        linkedList2.append(e2);

        LinkedList<Integer> linkedList3 = new LinkedListImpl<>(random());
        linkedList3.append(e1);

        LinkedList<Integer> linkedList4 = new LinkedListImpl<>(random());
        linkedList4.append(e1);
        linkedList4.append(e2);

        return Stream.of(
                arguments(linkedList1, e1),
                arguments(linkedList2, e2),
                arguments(linkedList3, e1),
                arguments(linkedList4, e2)
        );
    }

    @Test
    void insertFailedCannotInsertIndexNegative() {
        LinkedList<Integer> linkedList = new LinkedListImpl<>(random());
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        assertThatThrownBy(() -> linkedList.insert(random(), -1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> linkedList2.insert(random(), -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void insertFailedCannotInsertIndexBiggerOrEqualsToSize() {
        LinkedList<Integer> linkedList = new LinkedListImpl<>(random());
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        assertThatThrownBy(() -> linkedList.insert(random(), linkedList.size()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> linkedList.insert(random(), linkedList.size() + 1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> linkedList2.insert(random(), linkedList2.size()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> linkedList2.insert(random(), linkedList2.size() + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void verifyStack() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();

        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);
        LinkedList<Integer> linkedList2 = new LinkedListImpl<>();

        assertThat(linkedList.size()).isEqualTo(1);
        assertThat(linkedList2.size()).isEqualTo(0);

        linkedList.append(e1);
        assertThat(linkedList.size()).isEqualTo(2);

        linkedList.append(e2);
        assertThat(linkedList.size()).isEqualTo(3);

        linkedList.append(e3);
        assertThat(linkedList.size()).isEqualTo(4);

        assertThat(linkedList.pop()).isEqualTo(e3);
        assertThat(linkedList.popLeft()).isEqualTo(head);
        assertThat(linkedList.popLeft()).isEqualTo(e1);

        linkedList2.append(e1);
        assertThat(linkedList2.size()).isEqualTo(1);

        linkedList2.append(e2);
        assertThat(linkedList2.size()).isEqualTo(2);

        linkedList2.append(e3);
        assertThat(linkedList2.size()).isEqualTo(3);

        assertThat(linkedList2.pop()).isEqualTo(e3);
        assertThat(linkedList2.popLeft()).isEqualTo(e1);
        assertThat(linkedList2.popLeft()).isEqualTo(e2);
    }

    private static int random() {
        return new Random().nextInt();
    }

}
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

        assertThat(linkedList.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("prepend")
    void prependOk(int head, int[] elements, int expectedSize) {
        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);

        for(int element: elements) {
            linkedList.prepend(element);
        }

        assertThat(linkedList.size()).isEqualTo(expectedSize);
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

        //when
        for(int element: elements) {
            linkedList.append(element);
        }

        assertThat(linkedList.size()).isEqualTo(expectedSize);
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

    @ParameterizedTest
    @MethodSource("popLeft")
    void popLeftOk(LinkedList<Integer> stackToBeUsed, int expectedElement) {
        assertThat(stackToBeUsed.popLeft()).isEqualTo(expectedElement);
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

        return Stream.of(
                arguments(linkedList1, e1),
                arguments(linkedList2, e1),
                arguments(linkedList3, e2)
        );
    }

    @ParameterizedTest
    @MethodSource("pop")
    void popOk(LinkedList<Integer> stackToBeUsed, int expectedElement) {
        assertThat(stackToBeUsed.pop()).isEqualTo(expectedElement);
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

        return Stream.of(
                arguments(linkedList1, e1),
                arguments(linkedList2, e2),
                arguments(linkedList3, e1)
        );
    }

    @Test
    void popFailedCannotRemoveHead() {
        LinkedList<Integer> linkedList = new LinkedListImpl<>(random());

        assertThatThrownBy(linkedList::pop)
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void verifyStack() {
        int head = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();

        LinkedList<Integer> linkedList = new LinkedListImpl<>(head);
        assertThat(linkedList.size()).isEqualTo(1);

        linkedList.append(e1);
        assertThat(linkedList.size()).isEqualTo(2);

        linkedList.append(e2);
        assertThat(linkedList.size()).isEqualTo(3);

        linkedList.append(e3);
        assertThat(linkedList.size()).isEqualTo(4);

        assertThat(linkedList.pop()).isEqualTo(e3);

        assertThat(linkedList.popLeft()).isEqualTo(head);

        assertThat(linkedList.popLeft()).isEqualTo(e1);
    }

    private static int random() {
        return new Random().nextInt();
    }

}
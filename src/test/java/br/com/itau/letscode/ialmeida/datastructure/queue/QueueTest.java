package br.com.itau.letscode.ialmeida.datastructure.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QueueTest {

    @Test
    void initializationOk() {
        int size = random(5);

        Queue<Integer> queue = new QueueImpl<>(size);

        assertThat(queue.size()).isEqualTo(size);
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.isFull()).isFalse();
        assertThat(queue.front()).isNull();
    }

    @Test
    void initializationZeroSizeNotAllowed() {
        assertThatThrownBy(() -> new QueueImpl<>(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void initializationNegativeSizeNotAllowed() {
        assertThatThrownBy(() -> new QueueImpl<>(-random(5))).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource(value = "dequeue")
    void dequeueOk(Queue<Integer> expectedQueue, int expectedElement, boolean expectedEmpty) {
        assertThat(expectedQueue.dequeue()).isEqualTo(expectedElement);
        assertThat(expectedQueue.isEmpty()).isEqualTo(expectedEmpty);
        assertThat(expectedQueue.isFull()).isFalse();
    }

    static Stream<Arguments> dequeue() {
        int e1 = random();
        int e2 = random();

        Queue<Integer> q1 = new QueueImpl<>(1);
        q1.enqueue(e1);

        Queue<Integer> q2 = new QueueImpl<>(2);
        q2.enqueue(e2);
        q2.enqueue(e1);

        Queue<Integer> q3 = new QueueImpl<>(2);
        q3.enqueue(e1);

        return Stream.of(
                Arguments.arguments(q1, e1, true),
                Arguments.arguments(q2, e2, false),
                Arguments.arguments(q3, e1, true)
        );
    }

    @Test
    void dequeueFailedEmptyQueue() {
        Queue<Integer> queue = new QueueImpl<>(random(10));

        assertThatThrownBy(queue::dequeue).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void verifyQueue() {
        int e1 = random();
        int e2 = random();
        int e3 = random();

        Queue<Integer> queue = new QueueImpl<>(3);
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.isFull()).isFalse();
        assertThat(queue.front()).isNull();

        queue.enqueue(e1);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.isFull()).isFalse();
        assertThat(queue.front()).isEqualTo(e1);

        queue.enqueue(e2);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.isFull()).isFalse();
        assertThat(queue.front()).isEqualTo(e1);

        queue.enqueue(e3);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.isFull()).isTrue();
        assertThat(queue.front()).isEqualTo(e1);

        assertThat(queue.dequeue()).isEqualTo(e1);
        assertThat(queue.front()).isEqualTo(e2);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.isFull()).isFalse();

        assertThat(queue.dequeue()).isEqualTo(e2);
        assertThat(queue.front()).isEqualTo(e3);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.isFull()).isFalse();

        assertThat(queue.dequeue()).isEqualTo(e3);
        assertThat(queue.front()).isNull();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.isFull()).isFalse();
    }

    private static int random() {
        return new Random().nextInt();
    }

    private static int random(int limit) {
        int n;
        do {
            n = new Random().nextInt(limit + 1);
        } while (n == 0);
        return n;
    }

}
package br.com.itau.letscode.ialmeida.datastructure.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UnlimitedQueueTest {

    @Test
    void initializationOk() {
        UnlimitedQueue<Integer> queue = new UnlimitedQueueImpl<>();

        assertThat(queue.size()).isEqualTo(0);
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.front()).isNull();
    }

    @ParameterizedTest
    @MethodSource(value = "dequeue")
    void dequeueOk(UnlimitedQueue<Integer> expectedQueue, int expectedElement, boolean expectedEmpty) {
        assertThat(expectedQueue.dequeue()).isEqualTo(expectedElement);
        assertThat(expectedQueue.isEmpty()).isEqualTo(expectedEmpty);
    }

    static Stream<Arguments> dequeue() {
        int e1 = random();
        int e2 = random();

        UnlimitedQueue<Integer> q1 = new UnlimitedQueueImpl<>();
        q1.enqueue(e1);

        UnlimitedQueue<Integer> q2 = new UnlimitedQueueImpl<>();
        q2.enqueue(e2);
        q2.enqueue(e1);

        UnlimitedQueue<Integer> q3 = new UnlimitedQueueImpl<>();
        q3.enqueue(e1);
        q3.enqueue(e2);

        return Stream.of(
                Arguments.arguments(q1, e1, true),
                Arguments.arguments(q2, e2, false),
                Arguments.arguments(q3, e1, false)
        );
    }

    @Test
    void dequeueFailedEmptyQueue() {
        UnlimitedQueue<Integer> queue = new UnlimitedQueueImpl<>();

        assertThatThrownBy(queue::dequeue).isInstanceOf(RuntimeException.class);
    }

    @Test
    void verifyQueue() {
        int e1 = random();
        int e2 = random();
        int e3 = random();

        UnlimitedQueue<Integer> queue = new UnlimitedQueueImpl<>();
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.front()).isNull();

        queue.enqueue(e1);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.front()).isEqualTo(e1);

        queue.enqueue(e2);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.front()).isEqualTo(e1);

        queue.enqueue(e3);
        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.front()).isEqualTo(e1);

        assertThat(queue.dequeue()).isEqualTo(e1);
        assertThat(queue.front()).isEqualTo(e2);
        assertThat(queue.isEmpty()).isFalse();

        assertThat(queue.dequeue()).isEqualTo(e2);
        assertThat(queue.front()).isEqualTo(e3);
        assertThat(queue.isEmpty()).isFalse();

        assertThat(queue.dequeue()).isEqualTo(e3);
        assertThat(queue.front()).isNull();
        assertThat(queue.isEmpty()).isTrue();
    }

    private static int random() {
        return new Random().nextInt();
    }

}
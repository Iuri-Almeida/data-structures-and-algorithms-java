package br.com.itau.letscode.ialmeida.datastructure.binarytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BinarySearchTreeTest {

    @Test
    void initializationOk() {
        int root = random();

        BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>(root);

        assertThat(bst.exists(root)).isTrue();
        assertThat(bst.size()).isEqualTo(1);
        assertThat(bst.retrieve(root).getData()).isEqualTo(root);
    }

    @ParameterizedTest
    @MethodSource(value = "remove")
    void removeOk(BinarySearchTree<Integer> expectedBst, int expectedElement, int expectedSize) {
        assertThat(expectedBst.remove(expectedElement)).isEqualTo(expectedElement);
        assertThat(expectedBst.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> remove() {
        int root = random();
        int e1 = random();
        int e2 = random();

        BinarySearchTree<Integer> bst1 = new BinarySearchTreeImpl<>(root);
        bst1.insert(e1);

        BinarySearchTree<Integer> bst2 = new BinarySearchTreeImpl<>(root);
        bst2.insert(e2);
        bst2.insert(e1);

        return Stream.of(
                Arguments.arguments(bst1, e1, 1),
                Arguments.arguments(bst2, e2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "insert")
    void insertOk(BinarySearchTree<Integer> expectedBst, int expectedElement, int expectedSize) {
        assertThat(expectedBst.exists(expectedElement)).isTrue();
        assertThat(expectedBst.retrieve(expectedElement).getData()).isEqualTo(expectedElement);
        assertThat(expectedBst.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> insert() {
        int root = random();
        int e1 = random();
        int e2 = random();

        BinarySearchTree<Integer> bst1 = new BinarySearchTreeImpl<>(root);
        bst1.insert(e1);

        BinarySearchTree<Integer> bst2 = new BinarySearchTreeImpl<>(root);
        bst2.insert(e2);
        bst2.insert(e1);

        return Stream.of(
                Arguments.arguments(bst1, e1, 2),
                Arguments.arguments(bst2, e2, 3)
        );
    }

    @Test
    void removeFailedCannotRemoveLastElement() {
        int root = random();

        BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>(root);

        assertThatThrownBy(() -> bst.remove(root)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void removeFailedCannotFindElement() {
        int root = random();
        int element = random();

        BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>(root);
        bst.insert(element);

        assertThatThrownBy(() -> bst.remove(random())).isInstanceOf(RuntimeException.class);
    }

    @Test
    void insertFailedCannotInsertDuplicatedElements() {
        int root = random();

        BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>(root);

        assertThatThrownBy(() -> bst.insert(root)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> bst.insertRecursive(root)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void verifyBst() {
        int root = random();
        int e1 = random();
        int e2 = random();
        int e3 = random();
        int e4 = random();

        BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>(root);
        assertThat(bst.size()).isEqualTo(1);
        assertThat(bst.exists(root)).isTrue();
        assertThat(bst.retrieve(root).getData()).isEqualTo(root);

        bst.insert(e1);
        assertThat(bst.size()).isEqualTo(2);
        assertThat(bst.exists(e1)).isTrue();
        assertThat(bst.retrieve(e1).getData()).isEqualTo(e1);

        bst.insert(e2);
        assertThat(bst.size()).isEqualTo(3);
        assertThat(bst.exists(e2)).isTrue();
        assertThat(bst.retrieve(e2).getData()).isEqualTo(e2);

        bst.insertRecursive(e3);
        assertThat(bst.size()).isEqualTo(4);
        assertThat(bst.exists(e3)).isTrue();
        assertThat(bst.retrieve(e3).getData()).isEqualTo(e3);

        bst.insertRecursive(e4);
        assertThat(bst.size()).isEqualTo(5);
        assertThat(bst.exists(e1)).isTrue();
        assertThat(bst.retrieve(e1).getData()).isEqualTo(e1);

        assertThat(bst.remove(e2)).isEqualTo(e2);
        assertThat(bst.size()).isEqualTo(4);
        assertThat(bst.exists(e2)).isFalse();
        assertThat(bst.retrieve(e2)).isNull();

        assertThat(bst.remove(e3)).isEqualTo(e3);
        assertThat(bst.size()).isEqualTo(3);
        assertThat(bst.exists(e3)).isFalse();
        assertThat(bst.retrieve(e3)).isNull();

        assertThat(bst.remove(e4)).isEqualTo(e4);
        assertThat(bst.size()).isEqualTo(2);
        assertThat(bst.exists(e4)).isFalse();
        assertThat(bst.retrieve(e4)).isNull();

        assertThat(bst.remove(root)).isEqualTo(root);
        assertThat(bst.size()).isEqualTo(1);
        assertThat(bst.exists(root)).isFalse();
        assertThat(bst.retrieve(root)).isNull();
    }

    private static int random() {
        return new Random().nextInt();
    }

}
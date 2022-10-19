package br.com.itau.letscode.ialmeida.datastructure.stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class UnlimitedStackTest {

    @Test
    void initializationOk() {
        UnlimitedStack<Integer> stack = new UnlimitedStackImpl<>();

        assertThat(stack.size()).isEqualTo(0);
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.top()).isNull();
    }

    @ParameterizedTest
    @MethodSource("pushArguments")
    void pushOK(int[] elements, int expectedTop) {
        UnlimitedStack<Integer> stack = new UnlimitedStackImpl<>();

        for(int element: elements) {
            stack.push(element);
        }

        assertThat(stack.top()).isEqualTo(expectedTop);
        assertThat(stack.isEmpty()).isFalse();
    }

    static Stream<Arguments> pushArguments() {
        int elementOne = random();
        int elementTwo = random();

        return Stream.of(
                arguments(new int[] {elementOne, elementTwo}, elementTwo),
                arguments(new int[] {elementOne, elementTwo}, elementTwo),
                arguments(new int[] {elementOne}, elementOne),
                arguments(new int[] {elementOne}, elementOne)
        );
    }

    @ParameterizedTest
    @MethodSource("popArguments")
    void popOk(UnlimitedStack<Integer> stackToBeUsed, int expectedElement, boolean expectedEmpty) {
        int actualElement = stackToBeUsed.pop();

        assertThat(actualElement).isEqualTo(expectedElement);
        assertThat(stackToBeUsed.isEmpty()).isEqualTo(expectedEmpty);
    }

    static Stream<Arguments> popArguments() {
        int elementOne = random();
        int elementTwo = random();

        UnlimitedStack<Integer> stack1 = new UnlimitedStackImpl<>();
        stack1.push(elementOne);

        UnlimitedStack<Integer> stack2 = new UnlimitedStackImpl<>();
        stack2.push(elementOne);
        stack2.push(elementTwo);

        UnlimitedStack<Integer> stack3 = new UnlimitedStackImpl<>();
        stack3.push(elementOne);

        return Stream.of(
                arguments(stack1, elementOne, true),
                arguments(stack2, elementTwo, false),
                arguments(stack3, elementOne, true)
        );
    }

    @Test
    void popFailedEmptyStack() {
        UnlimitedStack<Integer> stack = new UnlimitedStackImpl<>();

        assertThatThrownBy(stack::pop)
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void verifyStack() {
        int elementOne = random();
        int elementTwo = random();
        int elementThree = random();

        UnlimitedStack<Integer> stack = new UnlimitedStackImpl<>();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.top()).isNull();

        stack.push(elementOne);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.top()).isEqualTo(elementOne);

        stack.push(elementTwo);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.top()).isEqualTo(elementTwo);

        stack.push(elementThree);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.top()).isEqualTo(elementThree);

        assertThat(stack.pop()).isEqualTo(elementThree);
        assertThat(stack.isEmpty()).isFalse();

        assertThat(stack.pop()).isEqualTo(elementTwo);
        assertThat(stack.isEmpty()).isFalse();

        assertThat(stack.pop()).isEqualTo(elementOne);
        assertThat(stack.isEmpty()).isTrue();
    }

    private static int random() {
        return new Random().nextInt();
    }

}
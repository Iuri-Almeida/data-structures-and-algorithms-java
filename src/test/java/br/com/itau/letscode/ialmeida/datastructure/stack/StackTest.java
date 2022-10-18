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

class StackTest {

    @Test
    void initializationOk() {
        //given
        int size = random(10);

        //when
        Stack<Integer> stack = new StackImpl<>(size);

        //then
        assertThat(stack.size()).isEqualTo(size);
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.top()).isNull();
    }

    @Test
    void initializationZeroSizeNotAllowed() {
        //given when then
        assertThatThrownBy(() -> new StackImpl<>(0))
                .isInstanceOf(IllegalArgumentException.class) ;
    }

    @Test
    void initializationNegativeSizeNotAllowed() {
        //given
        final int size = -random(10);

        //when then
        assertThatThrownBy(() -> new StackImpl<>(size))
                .isInstanceOf(IllegalArgumentException.class) ;
    }

    @ParameterizedTest
    @MethodSource("pushArguments")
    void pushOK(int size, int [] elements, int expectedTop, boolean expectedFull) {
        //given
        Stack<Integer> stack = new StackImpl<>(size);

        //when
        for(int element: elements) stack.push(element);

        //then
        assertThat(stack.top()).isEqualTo(expectedTop);
        assertThat(stack.isFull()).isEqualTo(expectedFull);
        assertThat(stack.isEmpty()).isFalse();
    }

    static Stream<Arguments> pushArguments() {
        int elementOne = random();
        int elementTwo = random();

        return Stream.of(
                arguments(2, new int[] {elementOne, elementTwo}, elementTwo, true),
                arguments(3, new int[] {elementOne, elementTwo}, elementTwo, false),
                arguments(1, new int[] {elementOne}, elementOne, true),
                arguments(2, new int[] {elementOne}, elementOne, false)
        );
    }

    @Test
    void pushFailedFullStack() {
        //given
        int elementOne = random();
        int elementTwo = random();
        Stack<Integer> stack = new StackImpl<>(1);
        stack.push(elementOne);

        //when then
        assertThatThrownBy(() -> stack.push(elementTwo))
                .isInstanceOf(RuntimeException.class) ;
    }

    @ParameterizedTest
    @MethodSource("popArguments")
    void popOk(Stack<Integer> stackToBeUsed, int expectedElement, boolean expectedEmpty) {
        //given when
        var actualElement = stackToBeUsed.pop();

        //then
        assertThat(actualElement).isEqualTo(expectedElement);
        assertThat(stackToBeUsed.isEmpty()).isEqualTo(expectedEmpty);
        assertThat(stackToBeUsed.isFull()).isFalse();
    }

    static Stream<Arguments> popArguments() {
        int elementOne = random();
        int elementTwo = random();

        Stack<Integer> stackOneElementFull = new StackImpl<>(1);
        stackOneElementFull.push(elementOne);

        Stack<Integer> stackTwoElementsFull = new StackImpl<>(2);
        stackTwoElementsFull.push(elementOne);
        stackTwoElementsFull.push(elementTwo);

        Stack<Integer> stackTwoElementsNotFull = new StackImpl<>(2);
        stackTwoElementsNotFull.push(elementOne);

        return Stream.of(
                arguments(stackOneElementFull, elementOne, true),
                arguments(stackTwoElementsFull, elementTwo, false),
                arguments(stackTwoElementsNotFull, elementOne, true)
        );
    }

    @Test
    void popFailedEmptyStack() {
        //given
        int size = random(10);
        Stack<Integer> stack = new StackImpl<>(size);

        //when then
        assertThatThrownBy(stack::pop)
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void verifyStack() {
        //given
        int elementOne = random();
        int elementTwo = random();
        int elementThree = random();

        //when then
        Stack<Integer> stack = new StackImpl<>(3);
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.isFull()).isFalse();
        assertThat(stack.top()).isNull();

        stack.push(elementOne);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.isFull()).isFalse();
        assertThat(stack.top()).isEqualTo(elementOne);

        stack.push(elementTwo);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.isFull()).isFalse();
        assertThat(stack.top()).isEqualTo(elementTwo);

        stack.push(elementThree);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.isFull()).isTrue();
        assertThat(stack.top()).isEqualTo(elementThree);

        assertThat(stack.pop()).isEqualTo(elementThree);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.isFull()).isFalse();

        assertThat(stack.pop()).isEqualTo(elementTwo);
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.isFull()).isFalse();

        assertThat(stack.pop()).isEqualTo(elementOne);
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.isFull()).isFalse();
    }

    private static int random() {
        return new Random().nextInt();
    }

    private static int random(int limit) {
        var number = 0;
        while (number == 0) {
            number = new Random().nextInt(limit+1);
        }
        return number;
    }
}
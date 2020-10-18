package com.inaction.practise.sorting;

import java.util.Arrays;
import java.util.function.BiPredicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SortArrayService {

    public <E extends Comparable<? super E>> E[] bubbleSort(E[] unsortedArray,
                                                            Direction direction) {
        E[] newElements = Arrays.copyOf(unsortedArray, unsortedArray.length);
        BiPredicate<E, E> predicate = getPredicateByDirection(direction);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < newElements.length - 1; i++) {
                if (predicate.test(newElements[i], newElements[i + 1])) {
                    swapped = swap(newElements, i);
                }
            }
        } while (swapped);
        return newElements;
    }

    public <E extends Comparable<? super E>> E[] cocktailSort(E[] unsortedArray,
                                                              Direction direction) {
        E[] newElements = Arrays.copyOf(unsortedArray, unsortedArray.length);
        BiPredicate<E, E> predicate = getPredicateByDirection(direction);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i <= newElements.length - 2; i++) {
                if (predicate.test(newElements[i], newElements[i + 1])) {
                    swapped = swap(newElements, i);
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            for (int i = newElements.length - 2; i >= 0; i--) {
                if (predicate.test(newElements[i], newElements[i + 1])) {
                    swapped = swap(newElements, i);
                }
            }
        } while (swapped);
        return newElements;
    }

    public <E extends Comparable<? super E>> E[] selectionSort(E[] unsortedArray,
                                                               Direction direction) {
        E[] newArr = Arrays.copyOf(unsortedArray, unsortedArray.length);
        final int arrLength = newArr.length;
        BiPredicate<E, E> predicate = getPredicateByDirection(direction);
        for (int i = 0; i < arrLength; i++) {
            E comparisonValue = newArr[i];
            int comparisonIndex = i;
            for (int j = i + 1; j < arrLength; j++) {
                if (predicate.test(comparisonValue, newArr[j])) {
                    comparisonIndex = j;
                    comparisonValue = newArr[j];
                }
            }
            if (i != comparisonIndex) {
                E temp = newArr[i];
                newArr[i] = comparisonValue;
                newArr[comparisonIndex] = temp;
            }
        }
        return newArr;
    }

    public <E extends Comparable<? super E>> E[] insertionSort(E[] unsortedArray,
                                                               Direction direction) {
        E[] newArr = Arrays.copyOf(unsortedArray, unsortedArray.length);
        BiPredicate<E, E> predicate = getPredicateByDirection(direction);
        for (int i = 1; i < newArr.length; i++) {
            E value = newArr[i];
            int j = i - 1;
            while (j >= 0 && predicate.test(newArr[j], value)) {
                newArr[j + 1] = newArr[j];
                j = j - 1;
            }
            newArr[j + 1] = value;
        }
        return newArr;
    }

    private <E extends Comparable<? super E>> BiPredicate<E, E> getPredicateByDirection(
            Direction direction) {
        return (first, second) -> direction.equals(Direction.ASC)
                ? first.compareTo(second) > 0
                : first.compareTo(second) < 0;
    }

    private <E> boolean swap(E[] elements, int firstIndex) {
        E temp = elements[firstIndex];
        elements[firstIndex] = elements[firstIndex + 1];
        elements[firstIndex + 1] = temp;
        return true;
    }

}

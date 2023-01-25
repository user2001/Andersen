package com.example.andersen.Task3;

public class MergeSort {

    // First subarray is arr[startIndex..middleIndex]
    // Second subarray is arr[middleIndex+1..endIndex]
    void merge(int arr[], int startIndex, int middleIndex, int endIndex)
    {

        int tempLengthFirst = middleIndex - startIndex + 1;
        int tempLengthSecond = endIndex - middleIndex;


        int tempArrayFirst[] = new int[tempLengthFirst];
        int tempArraySecond[] = new int[tempLengthSecond];


        for (int i = 0; i < tempLengthFirst; ++i)
            tempArrayFirst[i] = arr[startIndex + i];
        for (int j = 0; j < tempLengthSecond; ++j)
            tempArraySecond[j] = arr[middleIndex + 1 + j];

        int i = 0, j = 0;

        int merged_index = startIndex;
        while (i < tempLengthFirst && j < tempLengthSecond) {
            if (tempArrayFirst[i] <= tempArraySecond[j]) {
                arr[merged_index] = tempArrayFirst[i];
                i++;
            }
            else {
                arr[merged_index] = tempArraySecond[j];
                j++;
            }
            merged_index++;
        }

        while (i < tempLengthFirst) {
            arr[merged_index] = tempArrayFirst[i];
            i++;
            merged_index++;
        }

        while (j < tempLengthSecond) {
            arr[merged_index] = tempArraySecond[j];
            j++;
            merged_index++;
        }
    }

    void sort(int arr[], int startIndex, int endIndex)
    {
        if (startIndex < endIndex) {

            int middle = startIndex + (endIndex - startIndex) / 2;


            sort(arr, startIndex, middle);
            sort(arr, middle + 1, endIndex);

            merge(arr, startIndex, middle, endIndex);
        }
    }
}

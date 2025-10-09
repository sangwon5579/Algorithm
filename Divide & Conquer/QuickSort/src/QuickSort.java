public class QuickSort {

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);

    }



    private static int partition(int[] arr, int left, int right){
        int pivot = arr[left];
        int i = left;
        for (int j = left+1; j <= right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, left, i);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {5, 3, 9, 4, 8, 2, 6, 1, 7};
        System.out.println("Before Quick Sort");
        printArray(arr);

        quickSort(arr, 0 , arr.length-1);
        System.out.println("After Quick Sort");
        printArray(arr);

    }

    private static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
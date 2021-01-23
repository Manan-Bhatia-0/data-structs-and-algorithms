public class SortMerge {

    private static Queue<int[]> sortedArrays(int[] arr) {
        Queue<int[]> queue = new Queue<>(arr.length);
        int start = 0;
        int end = 0;
        int count = 0;
        int i = 0;
        for (i = 0; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                count++;
            } else {
                end = count;
                count++;
                queue.enqueue(new int[]{start, end});
                start = i + 1;
            }
        }
        end = count;
        queue.enqueue(new int[]{start, end});
        return queue;
    }

    public static void sort(int[] arr) {

        Queue<int[]> queue = sortedArrays(arr);
        //System.out.println(queue.size());
        if (queue.size() == 1) {
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        if (queue.size() == arr.length) {
            merge(arr, start, end);
            return;
        }

        if ((queue.size() % 2) != 0) {
            while (queue.size() != 1) {
                if (queue.size() >= 2) {
                    int[] index1 = queue.dequeue();
                    int[] index2 = queue.dequeue();
                    start = index1[0];
                    end = index2[1];
                    if (index1[1] > index2[0]) {
                        queue.enqueue(index1);
                        index1 = queue.dequeue();
                        start = index2[0];
                        end = index1[1];
                    }
                }
                merge(arr, start, end);
                queue.enqueue(new int[]{start, end});
            }
            return;
        }
        while (queue.size() != 1) {
            if (queue.size() >= 2) {
                int[] index1 = queue.dequeue();
                int[] index2 = queue.dequeue();
                start = index1[0];
                end = index2[1];
            }
            queue.enqueue(new int[]{start, end});
            merge(arr, start, end);
        }
    }

    public static void merge(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (end + start) / 2;
            merge(arr, start, mid);
            merge(arr, (mid + 1), end);

            int newStart = mid + 1;

            while ((start <= mid) && (newStart <= end)) {

                if (arr[start] <= arr[newStart]) {
                    start++;
                } else {
                    int value = arr[newStart];
                    int index = newStart;

                    while (index > start) {
                        arr[index] = arr[(index - 1)];
                        index--;
                    }
                    arr[start] = value;

                    start++;
                    mid++;
                    newStart++;
                }
            }

        }
    }
}
	
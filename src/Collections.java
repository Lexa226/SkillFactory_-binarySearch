import java.util.Comparator;
import java.util.List;

public class Collections {

    public static <T extends Comparable<? super T>> int binarySearch(List<? extends T> list, T key) {
        return binarySearch(list, key, null);
    }

    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        if (c == null) {
            @SuppressWarnings("unchecked")
            Comparable<? super T> cmpKey = (Comparable<? super T>) key;
            return binarySearchComparable(list, cmpKey);
        } else {
            return binarySearchComparator(list, key, c);
        }
    }

    private static <T> int binarySearchComparable(List<? extends T> list, Comparable<? super T> key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            @SuppressWarnings("unchecked")
            Comparable<? super T> midVal = (Comparable<? super T>) list.get(mid);
            int cmp = midVal.compareTo((T) key);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }

    private static <T> int binarySearchComparator(List<? extends T> list, T key, Comparator<? super T> c) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = list.get(mid);
            int cmp = c.compare(midVal, key);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }
}

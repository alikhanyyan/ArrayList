import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> intArray = new ArrayList<>(4, 1);

        intArray.add(8);
        intArray.add(0, 5);
        intArray.add(8, 6);
        intArray.add(4, 7);
        System.out.println("Array as string: " + intArray);
        System.out.println("Array contains 8: " + intArray.contains(8));
        System.out.println();

        intArray.remove(0);
        intArray.remove(Integer.valueOf(8));
        System.out.println("Array as string: " + intArray);
        System.out.println("Array contains 5: " + intArray.contains(5));
        System.out.println("Array contains 8: " + intArray.contains(8));
        System.out.println();

        intArray.set(1, 9);
        intArray.set(5, 8);
        System.out.println("Element at index 1: " + intArray.get(1));
        System.out.println("Element at index 6: " + intArray.get(6));
        System.out.println();

        System.out.println("Array size: " + intArray.size());
        System.out.println("Array capacity: " + intArray.capacity());
        System.out.println();

        intArray.add(7);
        System.out.println("Index of 7: " + intArray.indexOf(7));
        System.out.println("Index of 10: " + intArray.indexOf(10));
        System.out.println("Last index of 7: " + intArray.lastIndexOf(7));
        System.out.println();

        System.out.println("Array as string: " + intArray);
        System.out.println("SubList[1, 3]: " + intArray.subList(1, 3));
        System.out.println();

        System.out.println("Is array empty: " + intArray.isEmpty());
        intArray.clear();
        System.out.println("Array as string: " + intArray);
        System.out.println("Array size after clear: " + intArray.size());
        System.out.println("Array capacity after clear: " + intArray.capacity());
        System.out.println("Is array empty: " + intArray.isEmpty());
        System.out.println();

        intArray.add(9);
        System.out.println("Array before addAll: " + intArray);
        List<Integer> list1 = new ArrayList<>(1, 5);
        intArray.addAll(list1);
        System.out.println("Array after addAll: " + intArray);
        List<Integer> list2 = new ArrayList<>(2, 0, 3);
        intArray.addAll(1, list2);
        System.out.println("Array after addAll in index 1: " + intArray);
        System.out.println();

        String[] str = new String[] {"ab", "bc"};
        Integer[] intArr1 = new Integer[] {1, 2};
        Integer[] intArr2 = new Integer[] {1, 2, 5, 1, 2 , 3, 6, 9};

        System.out.println("toArray in intArr1: " + Arrays.toString(intArray.toArray(intArr1)));
        System.out.println("toArray in intArr2: " + Arrays.toString(intArray.toArray(intArr2)));
        System.out.println();

        System.out.println("Array before removeAll: " + intArray);
        List<Integer> intList = new ArrayList<>(0, 1);
        List<String> strList = new ArrayList<>("a", "9");
        intArray.removeAll(intList);
        System.out.println("Array after removeAll with intList [0, 1]: " + intArray);
        intArray.removeAll(strList);
        System.out.println("Array after removeAll with strList [\"a\", \"9\"]: " + intArray);
        System.out.println();

        System.out.println("Array before retainAll: " + intArray);
        List<Integer> intList2 = new ArrayList<>(9, 5, 8, 3);
        intArray.retainAll(intList2);
        System.out.println("Array after retainAll with intList [9, 5, 8, 3]: " + intArray);
        System.out.println();

        intArray.add(0, 7);
        intArray.set(2, 0);
        System.out.println("Array: " + intArray);
        List<Integer> intList3 = new ArrayList<>(1, 2, 3);
        System.out.println("Is array containsAll of intList [1, 2, 3]: " + intArray.containsAll(intList3));
        List<Integer> intList4 = new ArrayList<>(7, 5);
        System.out.println("Is array containsAll of intList [7, 5]: " + intArray.containsAll(intList4));

    }
}
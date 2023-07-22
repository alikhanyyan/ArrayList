import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private E[] arrayList;
    private int capacity = 10;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() { arrayList = (E[])new Object[capacity]; }

    @SuppressWarnings("unchecked")
    @SafeVarargs
    public ArrayList(E... args) {
        if (args.length > capacity) {
            capacity += args.length;
        }

        arrayList = (E[])new Object[capacity];
        for(E arg: args) {
            arrayList[size++] = arg;
        }
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        if(size == capacity) {
            extendArray();
        }

        arrayList[size++] = element;
        return true;
    }

    @Override
    public void add(int index, E element) {
        try {
            indexValidationForAdd(index);
        } catch (WrongIndexException ex) {
            System.out.println(ex.getMessage() + " (at method add)");
            return;
        }

        if(size == capacity) {
            extendArray();
        }

        System.arraycopy(arrayList, index, arrayList, index + 1, size);
        arrayList[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        try {
            indexValidation(index);
        } catch (WrongIndexException ex) {
            System.out.println(ex.getMessage() + " (at method get)");
            return null;
        }

        return arrayList[index];
    }

    @Override
    public E set(int index, E element) {
        try {
            indexValidation(index);
        } catch (WrongIndexException ex) {
            System.out.println(ex.getMessage() + " (at method set)");
            return null;
        }

        E temp = arrayList[index];
        arrayList[index] = element;
        return temp;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);
        if (index != -1)
        {
            System.arraycopy(arrayList, index + 1, arrayList, index, size);
            arrayList[--size] = null;
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        try {
            indexValidation(index);
        } catch (WrongIndexException ex) {
            System.out.println(ex.getMessage() + " (at method remove)");
            return null;
        }

        E temp = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size);
        arrayList[--size] = null;
        return temp;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = size - 1; i >= 0; i--) {
            if (arrayList[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        arrayList = (E[])new Object[capacity];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayList, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] array) {
        if (isEmpty()) {
            return array;
        }

        try {
            array[0] = (T)arrayList[0];
        } catch (ArrayStoreException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        if (array.length < size) {
            array = (T[])new Object[size];
        }

        for (int i = 0; i < size; i++) {
            array[i] = (T)arrayList[i];
        }

       return array;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        try {
            indexValidation(fromIndex);
            indexValidation(toIndex);

            if (fromIndex >= toIndex) {
                throw new WrongIndexException("Wrong index interval: " + fromIndex + " - " + toIndex);
            }
        } catch (WrongIndexException ex) {
            System.out.println(ex.getMessage() + " (at method subList)");
            return null;
        }

        List<E> list = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            list.add(arrayList[i]);
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeAll(List<?> otherList) {
        List<E> tempList;

        try {
            tempList = (List<E>)otherList;
        } catch (ClassCastException ex) {
            System.out.println(ex.getMessage() + " (at method removeAll)");
            return false;
        }

        int prevSize = size;
        for (int i = 0; i < tempList.size(); i++) {
            if (this.contains(tempList.get(i))) {
                remove(tempList.get(i));
            }
        }

        return prevSize != size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean retainAll(List<?> otherList) {
        List<E> tempList;

        try {
            tempList = (List<E>)otherList;
        } catch (ClassCastException ex) {
            System.out.println(ex.getMessage() + " (at method retainAll)");
            return false;
        }

        int prevSize = size;

        for (int i = 0; i < size; i++) {
            if (!tempList.contains(arrayList[i])) {
                remove(i);
            }
        }

        return prevSize != size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsAll(List<?> otherList) {
        List<E> tempList;

        try {
            tempList = (List<E>)otherList;
        } catch (ClassCastException ex) {
            System.out.println(ex.getMessage() + " (at method containsAll)");
            return false;
        }

        for (int i = 0; i < tempList.size(); i++) {
            if (!contains(tempList.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(List<? extends E> otherList) {
        int otherListSize = otherList.size();
        if (capacity <= size + otherListSize) {
            extendArray(otherListSize);
        }

        for (int i = 0; i < otherListSize; i++) {
            arrayList[size++] = otherList.get(i);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, List<? extends E> otherList) {
        try {
            indexValidationForAdd(index);
        } catch (WrongIndexException ex) {
            System.out.println(ex.getMessage() + " (at method addAll)");
            return false;
        }

        int otherListSize = otherList.size();
        if (capacity <= size + otherListSize) {
            extendArray(otherListSize);
        }

        for (int i = size - 1; i >= index; i--) {
            arrayList[i + otherListSize] = arrayList[i];
        }
        for (int i = 0; i < otherListSize; i++) {
            arrayList[index + i] = otherList.get(i);
        }
        size += otherListSize;
        return true;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        if (size == 0)
        {
            return "[]";
        }

        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            str.append(arrayList[i]);
            str.append(", ");
        }
        str.append(arrayList[size - 1]);
        str.append("]");
        return str.toString();
    }


    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new WrongIndexException("Wrong index: " + index);
        }
    }
    private void indexValidationForAdd(int index) {
        if (index < 0 || index > size + 1) {
            throw new WrongIndexException("Wrong index: " + index);
        }
    }
    private void extendArray(){
        extendArray(0);
    }
    @SuppressWarnings("unchecked")
    private void extendArray(int size) {
        capacity = size < 10 ? capacity + 10 : capacity + 10 + size;
        E[] temp = arrayList;
        arrayList = (E[])new Object[capacity];
        System.arraycopy(temp, 0, arrayList, 0, size);
    }
}
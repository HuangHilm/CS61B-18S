public class ArrayDeque<T> {

    private int capacity = 8;
    private int size;
    private int nextfirst;
    private int nextlast;
    private T[] items;

    private void grow() {
        capacity *= 2;
        T[] a = (T[]) new Object[capacity];
        int ptr1 = addOne(nextfirst);
        int ptr2 = minusOne(nextlast);
        int i = 0;
        while (ptr1 != ptr2) {
            a[i] = items[ptr1];
            ptr1 = addOne(ptr1);
            i += 1;
        }
        a[size - 1] = items[ptr2];
        items = a;
        nextfirst = items.length - 1;
        nextlast = size;
    }

    private void shrink() {
        capacity /= 2;
        T[] a = (T[]) new Object[capacity];
        int ptr1 = addOne(nextfirst);
        int ptr2 = minusOne(nextlast);
        int i = 0;
        while (ptr1 != ptr2) {
            a[i] = items[ptr1];
            ptr1 = addOne(ptr1);
            i += 1;
        }
        a[size - 1] = items[ptr2];
        items = a;
        nextfirst = items.length - 1;
        nextlast = size + 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }
    private int addOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    /**Invariants
     * 1.Number of items in ADeque is size
     * 2.The position of the last item in the deque is always
     * **/
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        nextfirst = 3;
        nextlast = 4;
        size = 0;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            grow();
        }
        items[nextfirst] = item;
        nextfirst = minusOne(nextfirst);
        size += 1;
    }
    public void addLast(T item) {
        if (size == items.length) {
            grow();
        }
        items[nextlast] = item;
        nextlast = addOne(nextlast);
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int p1, p2;
        p1 = addOne(nextfirst);
        p2 = nextlast;
        while (p1 != p2) {
            System.out.print(items[p1]);
            p1 = addOne(p1);
        }
    }
    public T removeFirst() {
        if (size <= items.length * 0.25 && size >= 16) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        nextfirst = addOne(nextfirst);
        T retVal = items[nextfirst];
        items[nextfirst] = null;
        size -= 1;
        return retVal;
    }
    public T removeLast() {
        if (size <= items.length * 0.25 && size >= 16) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        nextlast = minusOne(nextlast);
        T retVal = items[nextlast];
        items[nextlast] = null;
        size -= 1;
        return retVal;
    }
    public T get(int index) {
        int count = 0;
        int p = addOne(nextfirst);
        while (count < index) {
            p = addOne(p);
            count++;
        }
        return items[p];
    }
}

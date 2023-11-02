public class LinkedListDeque<T> {
    private class DataNode {
        DataNode prev;
        T item;
        DataNode next;
        public DataNode(DataNode pre, T ite, DataNode nex) {
            prev = pre;
            item = ite;
            next = nex;
        }
        /** DataNode Constructor specific to sentinel **/
        public DataNode(DataNode pre, DataNode nex) {
            prev = pre;
            next = nex;
        }
    }

    private int size;
    /** The first item (if it exists) is at sentinel. next **/
    private final DataNode sentinel;
//    private LinkedListDeque(T x) {
//        sentinel = new DataNode(null, null);
//        sentinel.next = new DataNode(sentinel, x, sentinel);
//        sentinel.prev = sentinel.next;
//        size = 1;
//    }
    public LinkedListDeque() {
        sentinel = new DataNode(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        sentinel.next = new DataNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    public void addLast(T item) {
        sentinel.prev.next = new DataNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DataNode ptr = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            T retVal = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return retVal;
        }
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            T retVal = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return retVal;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            DataNode ptr = sentinel;
            for (int i = 0; i <= index; i++) {
                ptr = ptr.next;
            }
            return ptr.item;
        }
    }

    private T getRecursiveHelper(int index, DataNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
}

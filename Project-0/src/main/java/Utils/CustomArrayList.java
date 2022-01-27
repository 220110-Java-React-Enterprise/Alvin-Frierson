package Utils;
import java.util.Iterator;

/**
 * A fairly simple arraylist implementation extending custom list interface.
 * Default size is 2, grows by size * 2 when needed.
 * When an element is added or removed at an index other elements are not re-arranged.
 *
 * @param <E>
 */
public class CustomArrayList<E> implements CustomListInterface<E>, Iterable<E> {
    private Object[] array;
    private int size;
    private int maxSize;

    /**
     * Default constructor, creates an empty underlying array with maxSize 2
     */
    public CustomArrayList() {
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }

    /**
     * Sized constructor, creates an empty object with maxSize size
     * @param size the initial size of the underlying array
     */
    public CustomArrayList(int size) {
        maxSize = size;
        size = 0;
        array = new Object[size];
    }

    /**
     * Element list constructor, takes in variable number of objects and creates an underlying
     * array large enough to fit them.
     * @param e
     */
    public CustomArrayList(E ...e) {
        maxSize = size = e.length;
        array = new Object[size];

        for (int i = 0; i < size; ++i) {
            array[i] = e[i];
        }
    }


    /**
     * Adds an object to the underlying array after all previously added objects.
     * If array needs to grow, it invokes grow method.
     * @param o object to be added
     */
    @Override
    public void add(Object o) {
        //Implement this method
        // NOTE: if size >= maxSize we need to grow array
        // Incrementing size after the element is added, size should never be > maxSize, growArray() will run first
        checkSize();

        for (int i = 0; i < maxSize; i++) {
            if (array[i] == null) {
                array[i] = o;
                size++;
                break;  // we don't want to fill the remaining null array indexes
            }
        }
    }

    /**
     * Adds object at specified index, advancing the size of the underlying array. This will
     * require us to shift all later elements further down the index order.
     * @param index index location where object will be inserted
     * @param e element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(E e, int index) throws IndexOutOfBoundsException {
        //Implement this method
        checkSize();

        Object temp;

        try {
            if (array[index] == null) {
                array[index] = e;
                size++;
            }
            else {
                // shift first
                // then insert e at array[index], replacing the value now duplicated at index + 1
                for (int i = size; i > index; i--) {
                    array[i] = array[i - 1];
                }

                array[index] = e;
                size++;
            }
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets the object located at supplied index
     * @param index index of object to get
     * @return object located at index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        //Implement this method
        try {
            return (E)array[index];
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    /**
     * Empties the underlying array by setting its private reference to null and allowing
     * the old array to be garbage collected.
     */
    @Override
    public void clear() {
        //Implement this method
        this.array = null;
        maxSize = 2;
        size = 0;
        array = new Object[maxSize];
    }

    /**
     * Check if object o is found within underlying array, using Object.equals() method
     * @param o object to search for
     * @return index location of first instance of matching object. -1 if not found.
     */
    @Override
    public int contains(Object o) {
        //Implement this method
        boolean found = false;
        int location = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                found = true;
                location = i;
            }
        }

        return location;
    }

    /**
     * Removes object at specified index from underlying array, we will then
     * need to shift the remaining elements up the index order filling in the gap.
     * @param index index of object to remove from array
     */
    @Override
    public void remove(int index) {
        //Implement this method
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null;
        size--;
    }

    /**
     * returns size of array. This is the one greater than the index of the most advanced stored object,
     * not the maxSize which controls growth of the underlying array.
     * @return one greater than index of most advanced stored object
     */
    @Override
    public int size() {
        //Implement this method
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            // cursor
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor <= size - 1;
            }

            @Override
            public E next() {
                return (E)array[cursor++];
            }
        };
    }

    /**
     * Doubles the size of the underlying array by creating a new array and copying the
     * contents of the previous array into it.
     */
    private void growArray(){
        //System.out.println("Growing Array from " + maxSize + " to " + maxSize * 2);
        //set up new array
        maxSize = maxSize * 2;
        Object[] tempArray = array;
        array = new Object[maxSize];

        //copy to new array
        for (int i = 0; i < size; i++) {
            array[i] = tempArray[i];
        }
    }

    /**
     * Helper method to ensure array grows if necessary.
     */
    private void checkSize(){
        if (size == maxSize) {
            growArray();
        }
    }

    /**
     * Tester method to ensure proper behavior.
     * Prints the contents of the current array.
     */
    public void print() {
        for (Object item :
                array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}

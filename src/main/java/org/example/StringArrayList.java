package org.example;

import java.sql.Array;
import java.util.Arrays;
import java.util.Objects;

public class StringArrayList implements StringList {
    private String[] data;
    private int currentSize;


    public StringArrayList(int size) {
        data = new String[size];
        currentSize = 0;
    }

    public StringArrayList(String... args){
        data = new String[args.length];
        System.arraycopy(args, 0, data,0, args.length);
        currentSize = data.length;
    }



    @Override
    public String add(String item) {
        if (currentSize == data.length){
            resize(currentSize + 1);
        }
        data[currentSize] = item;
        currentSize++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkBounds(index);
        if (currentSize == data.length){
            resize(currentSize + 1);
        }
        for (int i = currentSize; i> index; i--){
            data[i] =data[i-1];
        }
        data[index] = item;
        currentSize++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkBounds(index);
        data[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        checkBounds(index);
        String result = data[index];
        for (int i = index + 1; i < currentSize; i++) {
            data[i - 1] = data[i];
        }
        currentSize--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = currentSize - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkBounds(index);
        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new InvalidArgumentException();
        }
        if (currentSize != otherList.size()) {
            return false;
        }
        for (int i = 0; i < currentSize; i++) {
            if (!Objects.equals(data[i], otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize ==0;
    }

    @Override
    public void clear() {
        Arrays.fill(data,0,currentSize,null);
        currentSize = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(data, currentSize);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < currentSize; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(data[i]);
        }
        result.append("]");
        return result.toString();
    }

    private  void  checkBounds(int index) {
        if (index < 0 || index >= currentSize) {
            throw new StringListIndexOutBoundsException();
        }
    }
    private void resize(int newSize) {
        int size = (currentSize * 3/2) + 1;
        size = Math.max(size, newSize);
        String[] newData = new String[size];
        System.arraycopy(data,0,newData,0,currentSize);
        data = newData;
    }

}
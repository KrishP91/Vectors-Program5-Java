import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Vector<E> extends AbstractListADT<E> implements Iterable<E>{
	protected E[] array;
	protected int capacity;
	
	ArrayList<E> list = new ArrayList<E>();
	
	@SuppressWarnings("unchecked")
    public Vector() {
        array = (E[]) new Object[10]; 
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }
	
	@SuppressWarnings("unchecked")
	public Vector(int initialCap) {
		 array = (E[]) new Object[initialCap]; 
	     size = 0;
	     capacity = initialCap;
	}
	
	@SuppressWarnings("unchecked")
	public Vector(Vector<E> copyVector) {
		 array = (E[]) new Object[copyVector.size]; 
		 for (int i = 0; i < copyVector.size; i++) {
			 array[i] = copyVector.at(i);
		 }
		 size = copyVector.size;
		 capacity = copyVector.size;

	}
	
	@Override
	public int size() {
		return this.size;
	}
	@Override
	public int capacity() {
		capacity = array.length;
		return capacity;
	}
	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public E front() {
		if (size == 0) {
			return null;
		}
		return array[0];
	}
	@Override
	public E back() {
		if (size == 0) {
			return null;
		}	
		return array[size - 1];
	}
	
	private void verifyCapacity(int need) {
		if (need >= array.length) {
			resize();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		Object[] temp = null;
		try {
			temp = new Object[array.length * 2];
			temp = Arrays.copyOf(array, temp.length);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}
		this.array = (E[]) temp;
	}
	@SuppressWarnings("unchecked")
	@Override
	public E[] data() {
		return (E[]) array;
	}

	@Override
	public void pushback(E element) {
		// TODO Auto-generated method stub
		verifyCapacity(size + 1);
		array[size++] = element;
	}

	@Override
	public E popback() {
		// TODO Auto-generated method stub
		E temp = at(size() - 1);
		this.erase(size - 1);
		return temp;
	}

	@Override
	public void insert(int insertPosition, E element) {
		// TODO Auto-generated method stub
		if (size == array.length) {
			verifyCapacity(size + 1);
		}
		System.arraycopy(array, insertPosition, array, insertPosition + 1, size - insertPosition);
		size++;
		array[insertPosition] = element;
	}

	@Override
	public void erase(int position) {
		// TODO Auto-generated method stub
		size = size - 1;
		System.arraycopy(array, position + 1, array, position, size - position);
	}

	@Override
	public void erase(int startRangePosition, int endRangePosition) {
		// TODO Auto-generated method stub
		int diff = endRangePosition - startRangePosition;
		if (diff > 0) {
			System.arraycopy(array, endRangePosition, array, startRangePosition, size - endRangePosition);
			int sizeStore = size;
			size = size - diff;
			Arrays.fill(array, size, sizeStore, null);
		}
		else if (diff < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void swap(Vector<E> other) {
		// TODO Auto-generated method stub
		E[] temp = array;
		this.clear();
		this.resize(other.size());
		for (E e : other) {
			this.pushback(e);
		}
		other.clear();
		for (E e : temp) {
			other.pushback(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void shrinkToFit() {
		// TODO Auto-generated method stub
		capacity = size;
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = (E) array[i];
		}
		array = temp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newSize) {
		// TODO Auto-generated method stub
		Object[] temp = null;
		try {
			temp = new Object[newSize];
			temp = Arrays.copyOf(array, newSize);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}
		capacity = newSize;
		this.array = (E[]) temp;
	}

	@Override
	public Iterator<E> begin() {
		// TODO Auto-generated method stub
		return new VectorIteratorHelper();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new VectorIteratorHelper();
	}

	@Override
	public E at(int index) {
		// TODO Auto-generated method stub
		try {
			return array[index];
		}
		catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	class VectorIteratorHelper implements Iterator<E>{
		private int counter;
		@SuppressWarnings("unchecked")
		private E[] list = (E[]) array;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return counter < size;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (hasNext()) {
				return list[counter++];
			}
			return null;
		}
		
	}
}

/*
 * HashSetNew.java 
 * 
 * Version: 1.0 12/07/2015
 * 
 * @author: Ashwini Singh
 * @author: Prajesh Jhumkhawala
 *
 *Contains the implementation of the HasSetNew class.
 *The HashSetNew class is basically an array-based implementation
 *This class will run along with the Driver class and calculate the time required to implement the various methods
 *This also contains HashIterator class
 */

import java.util.*;

class HashIterator implements Iterator {
	/*
	 * Implementation of the HashIterator which is used for the execution of the IteratorTest.
	 * This class, implements the Iterator and over-rides the methods
	 * 
	 */
	
	static int count = 0;
	static int count1 = 0;
	static Object NewArray[];

	public HashIterator(Object[] o) //Parameterized constructor 
	{
		NewArray = (Object[]) o;
	}

	public boolean hasNext() { //Checks if there is any next element present or not
		if (NewArray[count] != null) {
			count++;
			return true;
		} else
			return false;
	}

	@Override
	public Object next() { //Returns the next element
		Object a = NewArray[count1];
		count1++;
		return a;
	}

	@Override
	public void remove() {

	}

}

public class HashSetNew extends HashSet {
/*
 * Implementation of the methods required to run the Driver class
 * 
 * 
 */
	final static int MAX_ELEMENTS = 20000;
	static Object newArray[] = new Object[MAX_ELEMENTS];
	static Object allObjects[] = new Object[MAX_ELEMENTS];
	static HashSetNew aHashSetNew;
	boolean passed;
	static int count = 0;

	public boolean add(Object o) {
		/*
		 * Adding the elements into the array. 
		 * If the array is full, each time it increases the size by 1.
		 * And adds the Object o
		 * 
		 */
		boolean flag = false;
		boolean flag1 = false;
		if (count < MAX_ELEMENTS) {
			newArray[count] = o;
			count++;
			return true;
		} else {
			for (int i = 0; i < newArray.length; i++) {
				if (newArray[i] != null) {
					if (newArray[i].equals(o)) {
						flag = true;
					}
				}
				{
					flag1=true;
				}
			}
			if (!flag) {
				if(!flag1)
				{
				for(int i=0;i< newArray.length; i++)
				{
					if(newArray[i]==null)
					{	newArray[i]=0;
						break;
					}	
				}
				}
				else
				{
				count = 0;
				Object TempArray[] = new Object[newArray.length + 1];
				for (int i = 0; i < newArray.length; i++) {
					TempArray[i] = newArray[i];
					count++;
				}
				newArray = TempArray;
				newArray[count] = o;
				count++;
				}
				return true;
			}

			return false;
		}
	}

	public int size() {
		/*
		 * Counts the size of the elements.
		 * The size of the array is the number of non-null elements it has 
		 * 
		 */
		
		int size = 0;
		for (int i = 0; i < newArray.length; i++) {
			if (newArray[i] != null) {
				size++;
			}
		}
		count = size;
		return size;
	}

	public void clear() {
		/*
		 * Clears the entire array
		 * Thus, it sets the value of the entire array to null
		 * 
		 */
	
		for (int i = 0; i < newArray.length; i++)
			if(newArray[i]!=null)
				newArray[i] = null;
		count = 0;
	}

	public boolean contains(Object o) {
		/*
		 * Checks whether the array contains the given object or not.
		 * 
		 * 
		 */
		
		for(int i=0;i<newArray.length;i++)
			if(newArray[i]!=null)
			{
				if(newArray[i]==o)
					return true;
			}
			else
				break;
		return false;
	}

	public boolean remove(Object o) {
		/*
		 * If the contains test returns true, then the remove method is called 
		 * This method will remove the element and replaces it with null
		 * 
		 */
		
		int temp = 0;
		for (int i = 0; i < newArray.length; i++) {
			if (newArray[i] != null) {
				if (newArray[i].equals(o)) {
					newArray[i]=null;
					temp=1;
					break;
				}
			}
		}
		return temp == 1;
	}

	public boolean isEmpty() {
		/*
		 * Checks whether the array is empty or not
		 */

		return count == 0;
	}

	public Iterator iterator() {
		/*
		 * Contains the iterator method which in turn returns an object of HashIterator class
		 */
		
		Iterator a = new HashIterator(newArray);
		return a;
	}

}
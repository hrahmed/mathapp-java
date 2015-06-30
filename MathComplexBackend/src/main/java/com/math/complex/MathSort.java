package com.math.complex;

public class MathSort{


	private SortCache vSort;
	private SortedValuesList sortedValueList;
	private SortedValuesList sortList;

	public MathSort() {
		// TODO Auto-generated constructor stub
	}


	public long[] quickSort(long toSort[]) 
	// pre: array is full, all elements are non-null integers
	// post: the array is sorted in ascending order
	{
		long[] sortedArray = null;

		sortedArray = quickSort(toSort, 0, toSort.length - 1);

		// cause leak by adding to static cache

		if (toSort.length == 7){
			vSort = new SortCache();

			for (int i = 0; i < MathProperties.getLeakhighcount(); i++) {

				//vSort = new SortCache();
				//vSort.add(new SortedValuesList(sortedArray));
				
				sortList = new SortedValuesList(sortedArray);
				vSort.add(sortList);
				sortList.getStatus();
			}
			//System.out.println("Array size is: " + SortCache.getSize());

		} else if (toSort.length == 5) {
			vSort = new SortCache();
			for (int i = 0; i < MathProperties.getLeaklowcount(); i++) {
				//vSort.add(sortedArray);
				
				sortList = new SortedValuesList(sortedArray);
				vSort.add(sortList);
				sortList.getStatus();
			}
			//System.out.println("Array size is: " + SortCache.getSize());

		} else {
			vSort = new SortCache();
			sortList = new SortedValuesList(sortedArray);
			vSort.add(sortList);
			sortList.getStatus();
			vSort.remove(sortList);
		}

		return  sortedArray;            // quicksort all the elements in the array
	}


	public long[] quickSort(long toSort[], int start, int end)
	{
		int i = start;                          // index of left-to-right scan
		int k = end;                            // index of right-to-left scan
		long[] array = toSort;

		if (end - start >= 1)                   // check that there are at least two elements to sort
		{
			long pivot = array[start];       // set the pivot as the first element in the partition

			while (k > i)                   // while the scan indices from left and right have not met,
			{
				while (array[i] <= pivot && i <= end && k > i)  // from the left, look for the first
					i++;                                    // element greater than the pivot
				while (array[k] > pivot && k >= start && k >= i) // from the right, look for the first
					k--;                                        // element not greater than the pivot
				if (k > i)                                       // if the left seekindex is still smaller than
					swap(array, i, k);                      // the right index, swap the corresponding elements
			}
			swap(array, start, k);          // after the indices have crossed, swap the last element in
			// the left partition with the pivot 
			quickSort(array, start, k - 1); // quicksort the left partition
			quickSort(array, k + 1, end);   // quicksort the right partition
		}
		else    // if there is only one element in the partition, do not do any sorting
		{
			return array;                     // the array is sorted, so exit
		}
		return array;
	}

	public void swap(long array[], int index1, int index2) 
	// pre: array is full and index1, index2 < array.length
	// post: the values at indices 1 and 2 have been swapped
	{
		long temp = array[index1];           // store the first value in a temp
		array[index1] = array[index2];      // copy the value of the second into the first
		array[index2] = temp;               // copy the value of the temp into the second
	}
}
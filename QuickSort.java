/*
Mia Kelley-Lanser
03/10/2024

The following class contains two different versions of QuickSort, an algorithm used to sort an array.
*/

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSort {
    // Partition_Separation separates elements within one ArrayList into two separate ArrayLists to partition them
    // around a pivot point, then combines the arrays back into one with the pivot in sorted position.
    // The code is based on the pseudocode for Partition given in my Homework 2 solution
    public static int Partition_Separation(ArrayList<Integer> A, int p) {
        int l = 0, r = 0; // this line corresponds directly to the second line in the pseudocode
        ArrayList<Integer> A_L = new ArrayList<Integer>(); // this line and the following one are not in the pseudocode directly but simply create the two empty arrays A_L and A_R that are used in the pseudocode
        ArrayList<Integer> A_R = new ArrayList<Integer>();
        for (int i = 0; i < A.size(); ++i) { // this is the loop in the third line of the pseudocode, here from 0 to n exclusive rather than from 1 to n inclusive since the index of the first element is 0 instead of 1
            if (i != p) { // this line corresponds directly to the fourth line in the pseudocode
                if (A.get(i) < A.get(p)) { // this line corresponds directly to the fifth line in the pseudocode
                    l = l + 1; // corresponds directly to the sixth line in pseudocode
                    A_L.add(A.get(i)); // corresponds to the seventh line in pseudocode; here A[i] is added to the end of A_L instead of explicitly placing it at the lth index. However, since A_L starts as an empty array and l starts at 0, the new element will always be placed in the (l-1)th position of A_L (not lth since l tracks how many elements are in A_L, but the first index is 0, e.g., if there is one element in A_L, l will be 1 but the first index will be 0)
                } else { // corresponds directly to the eighth line in pseudocode
                    r = r + 1; // corresponds directly to the ninth line in pseudocode
                    A_R.add(A.get(i)); // corresponds to the 10th line in pseudocode; here A[i] is added to the end of A_R instead of explicitly placing it at the rth index. However, since A_R starts as an empty array and r starts at 0, the new element will always be placed in the (r-1)th position of A_R (not rth since r tracks how many elements are in A_R, but the first index is 0, e.g., if there is one element in A_R, r will be 1 but the first index will be 0)
                }
            }
        }
        A.set(l, A.get(p)); // corresponds to the 11th line in pseudocode; here l is used instead of l+1 since the first index is 0 while l keeps track of how many elements are in A_L (one greater than the index of the last element in the ArrayList)
        for (int j = 0; j < l; ++j) { // corresponds to the 12th line in pseudocode; loop is from 0 to l exclusive rather than 1 to l inclusive since the first index is 0
            A.set(j, A_L.get(j)); // directly corresponds to the 13th line in pseudocode
        }
        int rIndex = 0; // corresponds to the 14th line in pseudocode; set to 0 instead of 1 since 0 is the first index of the ArrayList, not 1
        for (int k = l + 1; k < A.size(); ++k) { // corresponds to the 15th line in pseudocode; loop is from l+1 to n exclusive instead of l+2 to n inclusive since l was the index of the pivot instead of l+1
            A.set(k, A_R.get(rIndex)); // directly corresponds to the 16th line in pseudocode
            rIndex = rIndex + 1; // directly corresponds to the 17th line in pseudocode
        }
        return l; // corresponds to the 18th line in pseudocode; l is returned instead of l+1 since l was the pivot point index here, not l+1
    }

    // This is a simple swap function that swaps the values of the elements at the given indices in the given ArrayList.
    // While this function is not explicitly written out in the pseudocode in Erickson's book, the function is mentioned
    // and called in the Partition pseudocode, assumed to be a helper function. Here, the code is written out and looks
    // similar to a standard swap function, but specifically works with elements in an ArrayList
    public static void swapElements(ArrayList<Integer> A, int index1, int index2) {
        if (index1 >= 0 && index1 < A.size() && index2 >= 0 && index2 < A.size()) { // bounds checking
            int temp = A.get(index1);
            A.set(index1, A.get(index2));
            A.set(index2, temp);
        }
        else {
            System.out.println("At least one of the given indices is out of range of the given ArrayList.");
        }
    }

    // Partition_Swap swaps elements within one ArrayList to partition them around a pivot point.
    // The code is based on the pseudocode for Partition on page 29 of Jeff Erickson's book, Algorithms
    // http://jeffe.cs.illinois.edu/teaching/algorithms/book/Algorithms-JeffE.pdf
    public static int Partition_Swap(ArrayList<Integer> A, int p) {
        swapElements(A, p, A.size() - 1); // this line corresponds to the first line in the pseudocode: a swap function is called on the pth and last elements in A (here, last is A.size() - 1 since the first index is 0 instead of 1)
        int l = 0; // corresponds directly to the second line of the pseudocode: l is set to 0
        for (int i = 0; i < (A.size() - 1); ++i) { // this loop is from 0 to n - 1 exclusive instead of from 1 to n - 1 inclusive as in the pseudocode, since the first index here is 0
            if (A.get(i) < A.get(A.size() - 1)) { // as with the fourth line in the pseudocode, this line checks if the ith element of A is less than the last element
                swapElements(A, l, i); // this directly corresponds to the sixth line in the pseudocode (swapped order with next line so that the first index is 0, not 1-- i.e., the value of l is not changed from 0 till after an element has been swapped with the lth one)
                l = l + 1; // this directly corresponds to the fifth line in the pseudocode (swapped order with next line so that the first index is 0, not 1-- i.e., the value of l is not changed from 0 till after an element has been swapped with the lth one)
            }
        }
        swapElements(A, A.size() - 1, l); // corresponds to the seventh line in the pseudocode, but with l instead of l+1 since l is earlier increased after element swapping rather than before, so it is increased one more time and does not need to be increased here again
        return l; // corresponds to the eighth and final line in the pseudocode, but with l instead of l+1 for the same reason as given in the comment for the line above
    }

    // QuickSort_Separation function uses the Partition_Separation method to partition elements in an ArrayList around a pivot point.
    // The code is based on the pseudocode for QuickSort on page 29 of Jeff Erickson's book, Algorithms
    // http://jeffe.cs.illinois.edu/teaching/algorithms/book/Algorithms-JeffE.pdf
    public static void QuickSort_Separation(ArrayList<Integer> A) {
        if (A.size() > 1) { // this line directly corresponds to line 1 of QuickSort in Erickson's book
            int p = 0; // we choose the pivot, p, to be the first element of A (it can be any valid index of A) like in line 2
            int r = Partition_Separation(A, p); // corresponds to line 3, calls the Partition method on A and p and assigns the return value to r

            // This section of code is not directly in the pseudocode, but simply copies over the elements less than A[p]
            // to A_left and the elements greater than or equal to A[p] (except A[p] itself) to A_right. This is done so that
            // Quicksort_Separation can be recursively called in the following lines on the proper segments of A.
            ArrayList<Integer> A_left = new ArrayList<Integer>();
            for (int i = 0; i < r; ++i) { // 0 is used as a starting value instead of 1 (as in the book) since the lowest index here is 0 instead of 1; r is used as an exclusive bound rather than using r-1 as an inclusive bound
                A_left.add(A.get(i));
            }
            ArrayList<Integer> A_right = new ArrayList<Integer>();
            for (int i = r + 1; i < A.size(); ++i) { // here, r+1 is the starting value, just as in the book, but the size of A (n) is used as an exlclusive bound rather than an inclusive bound since we started at 0 and not 1 (so we end one element sooner-- the size of the array is one greater than the index of the last element)
                A_right.add(A.get(i));
            }

            // These two lines correspond directly to the last two lines of the Quicksort algorithm in Erickson's book, with
            // the recursive call consecutively occurring on the elements in A_left, then A_right
            QuickSort_Separation(A_left);
            QuickSort_Separation(A_right);

            // This section of code is not directly in the pseudocode, but simply copies over the elements in A_left and
            // A_right back into A in the newly changed order. This is done since the recursive calls are not done on
            // segments of A itself, but on separate arrays containing copies of elements in A. Therefore, the changes
            // made to the elements in A_left and A_right are not recorded back into A without the following code (similar
            // code can be found at the end of the Partition_Separation function and in its pseudocode). Note that A[p] is
            // not copied back into A, since A itself was passed into the Partition function and therefore A[p] already
            // contains the pivot element and copying it into itself would be redundant
            for (int i = 0; i < A_left.size(); ++i) {
                A.set(i, A_left.get(i));
            }
            int rIndex = 0;
            for (int i = r + 1; i < A.size(); ++i) {
                A.set(i, A_right.get(rIndex));
                rIndex = rIndex + 1;
            }
        }
    }

    // QuickSort_Swap function uses the Partition_Swap method to partition elements in an ArrayList around a pivot point.
    // The code is based on the pseudocode for QuickSort on page 29 of Jeff Erickson's book, Algorithms
    // http://jeffe.cs.illinois.edu/teaching/algorithms/book/Algorithms-JeffE.pdf
    public static void QuickSort_Swap(ArrayList<Integer> A) {
        if (A.size() > 1) { // this line directly corresponds to line 1 of QuickSort in Erickson's book
            int p = 0; // we choose the pivot, p, to be the first element of A (it can be any valid index of A) like in line 2
            int r = Partition_Swap(A, p); // corresponds to line 3, calls the Partition method on A and p and assigns the return value to r

            // This section of code is not directly in the pseudocode, but simply copies over the elements less than A[p]
            // to A_left and the elements greater than or equal to A[p] (except A[p] itself) to A_right. This is done so that
            // Quicksort_Swap can be recursively called in the following lines on the proper segments of A.
            ArrayList<Integer> A_left = new ArrayList<Integer>();
            for (int i = 0; i < r; ++i) { // 0 is used as a starting value instead of 1 (as in the book) since the lowest index here is 0 instead of 1; r is used as an exclusive bound rather than using r-1 as an inclusive bound
                A_left.add(A.get(i));
            }
            ArrayList<Integer> A_right = new ArrayList<Integer>();
            for (int i = r + 1; i < A.size(); ++i) { // here, r+1 is the starting value, just as in the book, but the size of A (n) is used as an exlclusive bound rather than an inclusive bound since we started at 0 and not 1 (so we end one element sooner-- the size of the array is one greater than the index of the last element)
                A_right.add(A.get(i));
            }

            // These two lines correspond directly to the last two lines of the Quicksort algorithm in Erickson's book, with
            // the recursive call consecutively occurring on the elements in A_left, then A_right
            QuickSort_Swap(A_left);
            QuickSort_Swap(A_right);

            // This section of code is not directly in the pseudocode, but simply copies over the elements in A_left and
            // A_right back into A in the newly changed order. This is done since the recursive calls are not done on
            // segments of A itself, but on separate arrays containing copies of elements in A. Therefore, the changes
            // made to the elements in A_left and A_right are not recorded back into A without the following code (similar
            // code can be found at the end of the Partition_Separation function and in its pseudocode). Note that A[p] is
            // not copied back into A, since A itself was passed into the Partition function and therefore A[p] already
            // contains the pivot element and copying it into itself would be redundant
            for (int i = 0; i < A_left.size(); ++i) {
                A.set(i, A_left.get(i));
            }
            int rIndex = 0;
            for (int i = r + 1; i < A.size(); ++i) {
                A.set(i, A_right.get(rIndex));
                rIndex = rIndex + 1;
            }
        }
    }

    public static int genRandInt(int lower, int upper) {
        Random r = new Random();
        int val = r.nextInt(Math.abs(upper - lower)) + lower;
        return val;
    }

    public static ArrayList<Integer> genRandArrayList(int size) {
        ArrayList<Integer> A = new ArrayList<Integer>(size);
        for (int i = 0; i < size; ++i) {
            A.add(genRandInt(-99, 100));
        }
        return A;
    }

        public static void main (String[]args){
            int NUM_TESTS = 1000;

            ArrayList<Long> swapTimes = new ArrayList<Long>(NUM_TESTS);
            ArrayList<Long> separateTimes = new ArrayList<Long>(NUM_TESTS);

            long startTime, endTime;

            for (int i = 1; i <= NUM_TESTS; ++i) {
                ArrayList<Integer> A = genRandArrayList(i);

                startTime = System.nanoTime();
                QuickSort_Separation(A);
                endTime = System.nanoTime();
                separateTimes.add(endTime - startTime);

                ArrayList<Integer> B = genRandArrayList(i);
                startTime = System.nanoTime();
                QuickSort_Swap(B);
                endTime = System.nanoTime();
                swapTimes.add(endTime - startTime);
            }

            try {
                File output = new File("separationTimes.txt");
                output.createNewFile();
                FileWriter writer = new FileWriter("separationTimes.txt");
                PrintWriter printer = new PrintWriter(writer);
                for (int i = 0; i < NUM_TESTS; ++i) {
                    printer.print((i+1) + "," + separateTimes.get(i).toString());
                    if (i != NUM_TESTS - 1) {
                        printer.print("\n");
                    }
                }
                writer.close();
                printer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File output = new File("swapTimes.txt");
                output.createNewFile();
                FileWriter writer = new FileWriter("swapTimes.txt");
                PrintWriter printer = new PrintWriter(writer);
                for (int i = 0; i < NUM_TESTS; ++i) {
                    printer.print((i+1) + "," + swapTimes.get(i).toString());
                    if (i != NUM_TESTS - 1) {
                        printer.print("\n");
                    }
                }
                writer.close();
                printer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*
Mia Kelley-Lanser
03/10/2024

The following class is a unit test for QuickSort class. The QuickSort_Separation and QuickSort_Swap
functions are tested 1000 times each, on randomly generated arrays from size 1 to 1000. The tests check
if each array is in ascending order after the appropriate QuickSort function is called on it. If no
element in an array is less than any of the preceding elements in that array, the score for that function's
test is increased by 1. The maximum score for each method testing is 1000 (1 point for each test case). The
scores are printed after the tests are run. Given the relatively large set of test cases (which can be increased
if desired but have been left at 1000 to avoid long wait times), since the tester scores 1000 out of 1000 points
for each method (with different-sized arrays containing different elements), the methods are likely correct in
their sorting. While this is not proof in itself, it shows a reasonable likelihood that the algorithms were correctly
implemented. The algorithms themselves, however, were proven correct in the previous homework (The Swap version was
given in the Algorithms textbook by Jeff Erickson:
http://jeffe.cs.illinois.edu/teaching/algorithms/book/Algorithms-JeffE.pdf. The Separation version was proven in the
homework to be equivalent in functionality to the Swap algorithm, and therefore must also be correct).
*/

import static java.lang.System.out;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class QuickSortTester {
    private static final int maximumScore = 1000;
    private static int totalScoreSwap;
    private static int totalScoreSeparate;

    @BeforeClass
    public static void beforeTesting() throws IOException {
        totalScoreSeparate = 0;
        totalScoreSwap = 0;
    }

    @AfterClass
    public static void afterTesting() {
        out.printf("%d out of %d test cases were correctly sorted using the QuickSort_Separation method.\n", totalScoreSeparate, maximumScore);
        out.printf("%d out of %d test cases were correctly sorted using the QuickSort_Swap method.\n\n", totalScoreSwap, maximumScore);

    }

    @Test
    public void testSeparateOrder() throws Exception {
        ArrayList<Integer> A = new ArrayList<Integer>(1);
        for (int i = 1; i <= 1000; ++i) {
            A.clear();
            A = QuickSort.genRandArrayList(i);

            QuickSort.QuickSort_Separation(A);

            boolean correctOrder = true;
            for (int j = 1; j < A.size(); ++j) {
                if (A.get(j) < A.get(j - 1)) {
                    correctOrder = false;
                    break;
                }
            }

            if (correctOrder) {
                totalScoreSeparate += 1;
            }
        }
    }

    @Test
    public void testSwapOrder() throws Exception {
        ArrayList<Integer> A = new ArrayList<Integer>(1);
        for (int i = 1; i <= 1000; ++i) {
            A.clear();
            A = QuickSort.genRandArrayList(i);

            QuickSort.QuickSort_Swap(A);

            boolean correctOrder = true;
            for (int j = 1; j < A.size(); ++j) {
                if (A.get(j) < A.get(j - 1)) {
                    correctOrder = false;
                    break;
                }
            }

            if (correctOrder) {
                totalScoreSwap += 1;
            }
        }
    }
}

/*
* This class is to find maxsum of rectangle in 2d array
*   for(left col) {
    for(right col=left col; right col<end
    for((找上面的row到下面的row)
      tmp[i]+=matrix[i][right column]<-- 巧妙的把columns merged再一起,然後利用1d kadane 去找subarray 的最大sum
}

*
 */
public class MaxSumIn2DArray {
    public static void main (String[] args) throws java.lang.Exception
    {
        findMaxSubMatrix(new int[][] {
                {1, 8, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 2, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        });
    }

    /**
     * To find maxSum in 1d array
     *
     * return {maxSum, left, right}
     */
    public static int[] kadane(int[] a) {
        int[] result = new int[3];
        int runningMax = a[0];
        int max = a[0];
        int localStart = 0;

        for(int i=1; i<a.length; i++) {
            if(runningMax+a[i]<0) {
                runningMax = a[i];
                localStart = i;
            }
            else {
                runningMax+=a[i];
            }
            if(runningMax>max) {
                max = runningMax;
                result[0] = max;
                result[1] = localStart;
                result[2] = i;
            }
        }
        return result;
    }

    /**
     * To find and print maxSum, (left, top),(right, bottom)
     */
    public static void findMaxSubMatrix(int[][] a) {
        int cols = a[0].length;
        int rows = a.length;
        int[] currentResult;
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        for(int leftCol =0; leftCol< cols; leftCol++) {
            int[] temp = new int[rows];

            for(int rightCol = leftCol; rightCol<cols; rightCol++) {
                for(int i=0; i<rows; i++)
                    temp[i]+=a[i][rightCol];

                int[] result = kadane(temp);

                if(result[0]>maxSum) {
                    maxSum = result[0];
                    left = leftCol;
                    top = result[1];
                    right = rightCol;
                    bottom = result[2];
                }

            }
        }
        System.out.println("MaxSum: " + maxSum +
                ", range: [(" + top + ", " + left +
                ")(" + bottom + ", " + right + ")]");
    }
}

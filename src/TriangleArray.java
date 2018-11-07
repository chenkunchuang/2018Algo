import java.util.*;

/*
This class is to determine if the array is triangle sorted.
triangle sorted is defined as increasing and then decreasing.
 */
public class TriangleArray {

    public boolean isTriangleSorted(int[] arr) {
        if(arr.length<=2)
            return false;

        int idx=0;
        int maxIdx=-1;
        //先增加
        while(idx<arr.length-1) {
            if (arr[idx] > arr[idx + 1]) {
                if (maxIdx == -1) {
                    maxIdx = idx;
                    break;
                }
            }
                idx++;
        }
        idx++;
        //在減少
        while(idx<arr.length-1) {
            if(arr[idx]<arr[idx+1]) {
                maxIdx = -1;
                break;
            }
            idx++;
        }

        return maxIdx!=-1;
    }

    public int getMin(int[] arr) {
        return Math.min(arr[0], arr[arr.length-1]);
    }

    public int[] getMax(int[] arr) {
        int l = 0, r=arr.length-1;
        int mid = l+(r-l)/2;
        while(l<r) {
            mid = l+(r-l)/2;

            if(arr[mid]>arr[mid+1])
                r = mid;
            else
                l=mid+1;
        }
        return new int[]{l, arr[l]};
    }
    public int get(int[] arr, int target) {
        int[] max = getMax(arr);

        int l=0, r=max[0];
        //左半部遞增
        while(l<=r) {
            int mid = l+(r-l)/2;
            if(arr[mid]==target)
                return mid;
            else if(arr[mid]>target)
                r=mid-1;
            else
                l=mid+1;
        }
        //右半部遞減
        l=max[0]+1;
        r=arr.length-1;
        while(l<=r) {
            int mid = l+(r-l)/2;
            if(arr[mid]==target)
                return mid;
            else if(arr[mid]>target)
                l=mid+1;
            else
                r=mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        TriangleArray sol = new TriangleArray();
        int[] a;
        a = new int[]{4,5,6,3,2,1};
        int[][] b = {{3,4,5,6,7},{4,5,7}};
        int[] c = {7,8,9,6,5,10,4,3,2};
//        deepToString是用在2d array
//        System.out.println("matrix b:"+Arrays.deepToString(b));
        String a_id = Arrays.toString(a);
        System.out.println(a_id +" is "+(sol.isTriangleSorted(a)?"":"not ") +"triangle sorted "+"min:"+sol.getMin(a) +
                " max:"+Arrays.toString(sol.getMax(a)));
        System.out.println("target:10 is "+ (sol.get(a,10)==-1?"not ":"")+"in array");
        System.out.println("target:6 is "+ (sol.get(a, 6)==-1?"not ":"")+"in array");
        System.out.println("target:4 is "+ (sol.get(a, 4)==-1?"not ":"")+"in array");
        System.out.println("target:1 is "+ (sol.get(a, 1)==-1?"not ":"")+"in array");

        System.out.println(Arrays.toString(c) +" is "+(sol.isTriangleSorted(c)?"":"not ") +"triangle sorted");
    }
}

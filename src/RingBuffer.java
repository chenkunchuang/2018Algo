/*
*  This class is implementing ring buffer which is non-blocking queue
*  Put to the tail, take from head so for consumer/producer will not need to
*   be blocked when either one is touch queue
*   NOTE: need to keep the last location as empty so it can determine
*   直接加一個size來判斷空或是滿,比較簡單
 */
import java.util.*;
import java.util.concurrent.locks.*;
public class RingBuffer {
    private int write, read;
    private char[] buf;
    private int count, cap;
    private Lock mutex;
    public RingBuffer(int size) {
        write = -1;
        read = 0;
        buf = new char[size];
        cap = size;
        count=0;
        mutex = new ReentrantLock();
    }

    public int read(int numOfChar, char[] buffer) {
        int cnt=0;
        int idx=0;
        mutex.lock();
        while(!isEmpty()&&numOfChar>0) {
            buffer[idx++] = buf[read++%cap];
            numOfChar--;
            cnt++;
            count--;
        }
        mutex.unlock();
        return cnt;
    }
    public int write(char[] data) {
        int size=data.length;
        int idx=0;
        int cnt=0;
        mutex.lock();
        while(!isFull()&&size>0) {
            buf[++write%cap]=data[idx++];
            size--;
            cnt++;
            count++;
        }
        mutex.unlock();
        return cnt;
    }
    public boolean isEmpty(){return count==0;}
    public boolean isFull(){return count==cap;}

    public static void main(String[] args) {
        RingBuffer sol = new RingBuffer(5);

        System.out.println("write 'abc': " + sol.write(new char[]{'a','b','c'}));
        System.out.println("write 'de': " + sol.write(new char[]{'d', 'e'}));
        System.out.println("read 4: "+sol.read(4, new char[4]));
        System.out.println("read 8: "+sol.read(8, new char[8]));
        System.out.println("write 'fgh': " + sol.write(new char[]{'f','g','h'}));
        System.out.println("read 1: "+sol.read(1, new char[4]));
        System.out.println("write 'ijklmn': " + sol.write(new char[]{'i','j','k','l','m','n'}));
        char[] temp = new char[10];
        sol.read(10, temp);
        System.out.println("temp:"+Arrays.toString(temp));
    }

}

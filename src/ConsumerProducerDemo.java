/*
*  Producer and consumer.  Producer produce 1 to 100
*  Consumer: two threads Thread 1 print odd number Thread 2 print even number
*  // extra constrains
*  1)Only 10 number can be added at most to queue
*  2)Total number is 100.
*  3)Once producer is done with all number, inform consumer threads to gracefully exit.
 */

import java.util.concurrent.ArrayBlockingQueue;

class Producer implements Runnable {
    private ArrayBlockingQueue<Integer> queue;
    public Producer(ArrayBlockingQueue<Integer> queue) {
     this.queue = queue;
    }
    public void run() {
//        try {
            for (int i = 1; i <= 100; i++) {
                queue.offer(i);
            }
            System.out.println("Producer is done. Inform consumer to stop");
            notifyAll();

//        }
// catch (InterruptedException e) {
//            System.out.print("Producer thread is interrupted");
//        }
    }
    @Override
    public String toString() {
         return "This is producer thread";
    }
}

class Consumer implements Runnable {
    private int id;
    private ArrayBlockingQueue<Integer> queue;
    public Consumer(int id, ArrayBlockingQueue<Integer> queue) {
        this.id = id;
        this.queue = queue;
    }
    public void run(){
        // Thread ct
        try {
            if(getName()%2==0 && queue.peek()%2==0){
                System.out.print("Consumer"+getName()+": " + queue.poll());
            }
            else if(getName()%2==1 && queue.peek()%2==1) {
                System.out.print("Consumer"+getName()+": " + queue.poll());
            }
        }
        catch (InterruptedException e) {
            System.out.println("No more items to consume from Producer. Terminate Consumer" + this.toString());
        }

    }

    @Override
    public String toString() {
        return " This is Consumer" + this.getName();
    }
    public int getName() {
        return this.id;
    }

}


public class ConsumerProducerDemo {

    public ArrayBlockingQueue<Integer> queue;

    public ConsumerProducerDemo() {
        queue = new ArrayBlockingQueue<Integer>(10);
    }


    static void main(String[] args) {

        ConsumerProducerDemo demo = new ConsumerProducerDemo();

        Thread p = new Thread (new Producer(demo.queue));
        Thread c1 = new Thread (new Consumer(1, demo.queue));
        Thread c2 = new Thread (new Consumer(2, demo.queue));

        p.start();
        c1.start();
        c2.start();

    }


}

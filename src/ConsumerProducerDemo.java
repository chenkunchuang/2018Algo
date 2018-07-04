/*
*  Producer and consumer.  Producer produce 1 to 100
*  Consumer: two threads Thread 1 print odd number Thread 2 print even number
*  // extra constrains
*  1)Only 10 number can be added at most to queue
*  2)Total number is 100.
*  3)Once producer is done with all number, inform consumer threads to gracefully exit.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.*;

public class ConsumerProducerDemo {
    final static int TOTAL = 100;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    private AtomicInteger count = new AtomicInteger();
    // write to file
//    PrintWriter out = new PrintWriter(
//                      new BufferedWriter(
//                              new FileWriter("")
//                      )
//    )

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 1; i <= TOTAL; i++) {
                    queue.put(i);
                    System.out.println(getName()+" produce order#"+i );
                }
                System.out.println("Producer is done. Inform consumer to stop");

            }
            catch (InterruptedException e) {
                System.out.print("Producer thread is interrupted");
            }
        }

        private String getName() {
            return Thread.currentThread().getName();
        }

        @Override
        public String toString() {
            return "This is producer thread";
        }
    }

    class Consumer implements Runnable {

//        public Consumer(int id, ArrayBlockingQueue<Integer> queue) {
//            this.id = id;
//            this.queue = queue;
//        }
        @Override
        public void run(){

            while(!finished()) {
                try {
                    if(!queue.isEmpty()) {
                        System.out.println(getName() + " consumes " + queue.take());
                        count.incrementAndGet();
//                        int orderNo = queue.peek();

//                        if (this.getName().equals("Consumer1") && orderNo % 2 == 0) {
//                            System.out.println(getName() + " consumes " + queue.take());
//                            count.incrementAndGet();
//                        } else if (this.getName().equals("Consumer2") && orderNo % 2 == 1) {
//                            System.out.println(getName() + " consumes " + queue.take());
//                            count.incrementAndGet();
//                        }
                    }
                }
                catch (InterruptedException e) {
                    System.out.println("No more items to consume from Producer. Terminate Consumer" + this.toString());
                    break;
                }
                finally {

                }
            }

        }

        private String getName() {
            return Thread.currentThread().getName();
        }

        private boolean finished() {
            if(count.get()>=TOTAL)
                System.out.println("all items are consumed");
            return count.get()>=TOTAL;
        }

        @Override
        public String toString() {
            return " This is Consumer" + this.getName();
        }
    }



    public static void main(String[] args) {

        ConsumerProducerDemo demo = new ConsumerProducerDemo();

        Thread p = new Thread (demo.new Producer());
        Consumer c = demo.new Consumer();
        Thread c1 = new Thread (demo.new Consumer(), "Consumer1");
        Thread c2 = new Thread (demo.new Consumer(), "Consumer2");
//        Thread c1 = demo. new Consumer("Consumer1");
//        Thread c2 = demo. new Consumer();
        p.start();

        c1.start();
        c2.start();
//        try {
//            c1.join();
//            c2.join();
//        }
//        catch(InterruptedException e) {
//            e.printStackTrace();
//        }
    }


}

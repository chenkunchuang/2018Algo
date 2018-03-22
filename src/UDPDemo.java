import java.net.*;
import java.io.*;

    class Send implements Runnable {//实现Runnable接口，多线程的方式之一。
        private DatagramSocket ds;
        public Send(DatagramSocket ds){
            this.ds = ds;
        }
        public void run(){
            try{
                BufferedReader bufr =
                        new BufferedReader(new InputStreamReader(System.in)); //读取键盘录入

                String line = null;
                while((line=bufr.readLine())!=null){ //readLine()阻塞式方法
                    if(line.equals("886"))
                        break;
                    byte[] buf = line.getBytes();

                    //创建一个发送的数据包，其中定义了数据、数据长度、目的ip、目的端口。
                    DatagramPacket dp =
                            new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),10002);

                    ds.send(dp); //Socket服务把数据包dp发送出去，udp传输方式。
                }
            }
            catch(Exception e){
                throw new RuntimeException("发送端失败");
            }
        }
    }

    class Receive implements Runnable{
        private DatagramSocket ds;
        public Receive(DatagramSocket ds){
            this.ds = ds;
        }
        public void run(){
            try{
                while(true){
                    byte[] buf = new byte[1024*64];
                    DatagramPacket dp = new DatagramPacket(buf,buf.length);//创建一个存储接收数据的数据包

                    ds.receive(dp); //阻塞式方法，Socket接收数据并存入dp数据包中。

                    String ip = dp.getAddress().getHostAddress();
                    String data = new String(dp.getData(),0,dp.getLength());

                    System.out.println(ip+"::"+data);
                }
            }
            catch(Exception e){
                throw new RuntimeException("接收端失败");
            }
        }
    }
public class UDPDemo {
    public static void main(String[] args) throws Exception {//抛出监听端口时的Socket异常
        DatagramSocket sendSocket = new DatagramSocket();
        DatagramSocket receSocket = new DatagramSocket(10002);

        new Thread(new Send(sendSocket)).start(); //启动线程
        new Thread(new Receive(receSocket)).start();
    }
}

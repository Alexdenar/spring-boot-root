import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by hotdog on 2017/4/14.
 */
public class ChannelClient {

    Selector selector; //选择器

    SocketChannel socketChannel; //与服务器通信的管道

    String hostIp; //服务器IP

    int hostListenningPort; //服务器监听的端口

    public ChannelClient (String hostIp, int hostListenningPort) throws IOException {
        this.hostIp = hostIp;
        this.hostListenningPort = hostListenningPort;

        //打开监听信道并设置为非阻塞模式
        socketChannel = SocketChannel.open(new InetSocketAddress(hostIp,hostListenningPort));
        socketChannel.configureBlocking(false);

        // 打开并注册选择器(监听读)到信道
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 启动读取线程
        ChannelClientReadThread ccrt = new ChannelClientReadThread(selector);
        ccrt.start();
    }

    // 发送字符串到服务器
    public void sendMsg(String message) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        socketChannel.write(writeBuffer);
    }

    /**
     * 客户端处理读的线程
     */
    public class ChannelClientReadThread extends Thread {
        private Selector selector;

        public ChannelClientReadThread(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    // 遍历每个有可用IO操作Channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {
                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            // 使用NIO读取Channel中的数据
                            SocketChannel sc = (SocketChannel) sk.channel();// 获取通道信息
                            ByteBuffer buffer = ByteBuffer.allocate(1024);// 分配缓冲区大小
                            sc.read(buffer);// 读取通道里面的数据放在缓冲区内
                            buffer.flip();// 调用此方法为一系列通道写入或相对获取 操作做好准备
                            // 将字节转化为为UTF-16的字符串
                            String receivedString = Charset.forName("UTF-8").newDecoder().decode(buffer)
                                    .toString();
                            // 控制台打印返回信息
                            System.out.println("服务器:" + sc.socket().getRemoteSocketAddress());
                            System.out.println("信息内容:" + receivedString);
                            // 为下一次读取作准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                        // 删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChannelClient client = new ChannelClient("localhost", 7456);
        try {
            client.sendMsg("我是客户端");
            while (true) {
                Scanner scan = new Scanner(System.in);// 等待键盘输入数据
                String string = scan.nextLine();
                client.sendMsg(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

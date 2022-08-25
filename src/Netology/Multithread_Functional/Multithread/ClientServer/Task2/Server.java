package Netology.Multithread_Functional.Multithread.ClientServer.Task2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server implements Runnable {
    public void run() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 22122);

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
             SocketChannel socketChannel = serverSocketChannel.
                     bind(inetSocketAddress).
                     accept()) {
            final ByteBuffer serverByteBuffer = ByteBuffer.allocate(2 << 10);
            while (socketChannel.isConnected()) {
                int byteCount = socketChannel.read(serverByteBuffer);
                if (byteCount == -1) break;
                final String outputString = new String(serverByteBuffer.array(), 0, byteCount, StandardCharsets.UTF_8).
                        replaceAll(" ", "");
                serverByteBuffer.clear();
                socketChannel.write(ByteBuffer.wrap(outputString.getBytes(StandardCharsets.UTF_8)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

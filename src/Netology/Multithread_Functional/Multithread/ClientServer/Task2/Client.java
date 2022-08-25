package Netology.Multithread_Functional.Multithread.ClientServer.Task2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client implements Runnable {
    public void run() {
        String poem = "Где обедал, воробей? \n" +
                "В зоопарке у зверей. \n" +
                "Пообедал я сперва\n" +
                "За решеткою у льва.\n" +
                "Подкрепился у лисицы.\n" +
                "У моржа попил водицы.\n" +
                "Ел морковку у слона.\n" +
                "С журавлем поел пшена.\n" +
                "Погостил у носорога,\n" +
                "Отрубей поел немного.\n" +
                "Побывал я на пиру\n" +
                "У хвостатых кенгуру.\n" +
                "Был на праздничном обеде\n" +
                "У мохнатого медведя.\n" +
                "А зубастый крокодил\n" +
                "Чуть меня не проглотил.\n" +
                "Автор: С. Я. Маршак ***\n" +
                "end\n";

        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 22122);
        try (
                final SocketChannel socketChannel = SocketChannel.open();
                Scanner scanner = new Scanner(poem);
        ) {
            socketChannel.connect(socketAddress);
            final ByteBuffer clientByteBuffer = ByteBuffer.allocate(2 << 10);
            final StringBuilder result = new StringBuilder();
            String line;
            System.out.println("\nОтправляю строки на сервер:\n");
            while (!(line = scanner.nextLine()).equals("end")) {
                socketChannel.write(ByteBuffer.wrap((line + "\n").getBytes(StandardCharsets.UTF_8)));
                System.out.println(line);
                Thread.sleep(200);
                int byteCount = socketChannel.read(clientByteBuffer);
                result.append(new String(clientByteBuffer.array(), 0, byteCount, StandardCharsets.UTF_8));
                clientByteBuffer.clear();
            }
            System.out.println("\nПолучил от сервера:\n" + result);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

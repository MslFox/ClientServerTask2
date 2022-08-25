package Netology.Multithread_Functional.Multithread.ClientServer.Task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Thread(new Server()).start();
        new Thread(new Client()).start();

    }
}

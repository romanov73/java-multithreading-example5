package ru.ulstu.java;

public class DataManager implements Runnable {

    private static boolean ready = false;

    private static final Object monitor = new Object();

    public void sendData() {
        synchronized (monitor) {
            try {
                while (!ready) {
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sending data...");
        }
    }

    public void prepareData() {
        synchronized (monitor) {
            System.out.println("Data prepared");
            ready = true;
            monitor.notifyAll();
        }

    }

    @Override
    public void run() {
        sendData();
    }
}

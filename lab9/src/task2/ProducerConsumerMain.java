package task2;

import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerMain {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer<String> buf1 = new RingBuffer<>(50);
        RingBuffer<String> buf2 = new RingBuffer<>(50);

        AtomicInteger msgId = new AtomicInteger(1);

        for (int i = 1; i <= 5; i++) {
            final int producerNo = i;
            Thread p = new Thread(() -> {
                while (true) {
                    try {
                        int id = msgId.getAndIncrement();
                        String s = "Потік No " + producerNo + " згенерував повідомлення " + id;
                        buf1.put(s);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            p.setDaemon(true);
            p.start();
        }


        for (int i = 1; i <= 2; i++) {
            final int translatorNo = i;
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        String original = buf1.take();
                        String translated = "Потік No " + translatorNo + " переклав повідомлення: " + original;
                        buf2.put(translated);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        }

        for (int i = 1; i <= 100; i++) {
            String s = buf2.take();
            System.out.println(i + ") " + s);
        }
    }
}

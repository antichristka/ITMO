package Kate.lab6;

public class Main {
    static final int iterations = 5;
    public static void main(String[] args) {
        QueueHandler queue = new QueueHandler();
        Creator creator1 = new Creator(queue, "creator1");
        Creator creator2 = new Creator(queue, "creator2");
        Receiver receiver1 = new Receiver(queue, 1,5, "receiver1");
        Receiver receiver2 = new Receiver(queue, 6,10, "receiver2");
    }
}
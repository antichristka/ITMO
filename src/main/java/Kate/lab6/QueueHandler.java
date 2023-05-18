package Kate.lab6;

import Kate.lab6.exceptions.ShiftImpossibleException;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/***
 * поток управления очередью
 */
public class QueueHandler implements PriorityQueue{

    private static final int queueMaxSize = 10; // максимальный размер очереди
    private int[] queue = new int[queueMaxSize]; //Очередь для связи создатель-получатель

    private int size = 0; // фактическая длина очереди

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition getter = lock.newCondition(); //состояние блокировки потока на чтение
    private final Condition setter = lock.newCondition(); //состояние блокировки потока на запись


    /***
     * Расчет фактической длины очереди
     * (где 0 = null)
     */
    private void updateLen(){
        size = 0;
        for (byte i=0; i<queueMaxSize; i++)
            if(queue[i]!=0)
                size++;
    }

    /***
     * абсолютный сдвиг очереди влево
     */
    private void leftShift() throws ShiftImpossibleException {
        leftShift(0);
    }

    /***
     * сдвиг очереди влево начиная с idx
     * (значение из idx будет потеряно)
     * @param idx начало сдвига
     * @throws ShiftImpossibleException
     */
    private void leftShift(int idx) throws ShiftImpossibleException {
        if(full()) throw new ShiftImpossibleException("очередь полна!");
        for (int i = idx; i < queue.length-1; i++) {
            queue[i] = queue[i+1];
        }
        queue[queue.length-1]=0;
    }


    @Override
    public void insert(int val) throws InterruptedException {
        lock.lock();
        try {
            while(full()) setter.await();
            size++;
            for (int i=0; i<queueMaxSize; i++){
                if(queue[i] == 0){
                    queue[i] = val;
                    break;
                }
            }

            System.out.println("В очередь добавлено новое значение " + val);
            System.out.println("Состояние очереди: " + Arrays.toString(queue));


            setter.signalAll();
        }finally {
            lock.unlock();
        }
    }

    /***
     * получить значение из очереди
     * @return значение
     * @throws InterruptedException
     */
    public int get() throws InterruptedException {
        lock.lock();
        int out = 0;
        try {
            while(empty()) getter.await();
            size--;
            out = queue[0];
            leftShift();
            System.out.println("Из очереди считано значение " + out);
            System.out.println("Состояние очереди: " + Arrays.toString(queue));

            getter.signalAll();
        } catch (ShiftImpossibleException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        return out;
    }


    @Override
    public int deleteMax() throws InterruptedException {
        lock.lock();
        try{
            while(empty()) getter.await();
            int max = 0;
            int maxIdx = 0;
            for (int i=0; i<queueMaxSize; i++){
                if(queue[i]>max){
                    max = queue[i];
                    maxIdx = i;
                }
            }
            leftShift(maxIdx);
            getter.signalAll();
            return max;
        } catch (ShiftImpossibleException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        return 0;
    }



    @Override
    public boolean full() {
        return (size >= queueMaxSize);
    }


    @Override
    public boolean empty() {
        return (size==0);
    }


    @Override
    public String toString() {
        return "QueueHandler{" +
                "queue=" + Arrays.toString(queue) +
                ", size=" + size +
                ", lock=" + lock +
                ", getter=" + getter +
                ", setter=" + setter +
                '}';
    }
}

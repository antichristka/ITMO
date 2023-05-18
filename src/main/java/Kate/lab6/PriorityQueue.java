package Kate.lab6;

public interface PriorityQueue {

    /***
     * Поместить новое значение в очередь
     * @param val значение
     */
    void insert(int val) throws InterruptedException;

    /***
     * удалить максимальное значение из очереди
     * @return удаленное значение
     */
    int deleteMax() throws InterruptedException;

    /***
     * Заполнена ли очередь?
     * @return boolean
     */
    boolean full();

    /***
     * Пуста ли очередь?
     * @return boolean
     */
    boolean empty();

}

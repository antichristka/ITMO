package Kate.lab6;

import static Kate.lab6.Main.iterations;

public class Creator implements Runnable{
    private QueueHandler queue;

    private String name;




    public Creator(QueueHandler queue) {
        this(queue, "creator");
    }

    public Creator(QueueHandler queue, String name) {
        this.name = name;
        this.queue = queue;
        new Thread(this).start(); //Запуск из конструктора
    }





    @Override
    public void run() {
        for(int j=0; j<iterations; j++){
            try{
                //System.out.println("----- " + name + ":");
                for(int i=0; i<(int)(Math.random()*2)+1; i++){
                    int newValue = (int)(Math.random()*9) + 1;
                    queue.insert(newValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

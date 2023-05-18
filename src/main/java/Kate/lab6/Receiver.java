package Kate.lab6;
import Kate.lab6.binTree.BinaryTree;

import static Kate.lab6.Main.iterations;

public class Receiver implements Runnable{

    private String name;
    BinaryTree tree = new BinaryTree(); //дерево, которое строит получатель
    private QueueHandler queue;
    private int minV;
    private int maxV;

    public Receiver(QueueHandler queue){
        this(queue, 1, 10, "receiver");
    }

    public Receiver(QueueHandler queue, int minV, int maxV, String name){
        this.name = name;
        this.queue = queue;
        this.minV = minV;
        this.maxV = maxV;
        new Thread(this).start();
    }


    @Override
    public void run() {
        for(int j=0; j<iterations; j++){
            try{
                //System.out.println("----- " + name + ":");
                int newV = queue.get();
                if(newV<minV || newV > maxV) {
                    continue;
                }
                tree.add(newV);
                //tree.printTree(tree.root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package Kate.lab6.binTree;

/***
 * Запись о блоке бин. дерева
 * вида: лист-корень-лист
 */
public class Node {
    int value; //приоритет корня сегмента дерева
    Node left; //приоритет левого листа
    Node right; //приоритет правого листа

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
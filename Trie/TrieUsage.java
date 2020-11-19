import java.util.TreeMap;

/**
 * 实现字典树
 *
 * @Author: zzStar
 * @Date: 11-05-2020 22:28
 */
public class TrieUsage {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public TrieUsage() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if (!cur.isWord) cur.isWord = true;
        size++;
    }


    // 查询单词是否在Trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

}

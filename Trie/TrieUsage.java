import java.util.Stack;
import java.util.TreeMap;

/**
 * 实现字典树  又叫前缀树
 *
 * @Author: zzStar
 * @Date: 11-05-2020 22:28

 * Trie 的删除操作
 *
 * Trie 的局限性：next 指针是 TreeMap 数据类型，key 值总数最多可以达到26种。
 * 改进方式：使用压缩字典树 Compressed Trie
 *
 * 三分搜索树（Ternary Search Tree）：时间换空间。
 *
 * 后缀树
 *
 * 子串查询算法：KMP、Boyer-Moore、Rabin-Karp。
 *
 * 文件压缩实际上也算是一种字符串压缩。
 *
 * 模式匹配：实现一个正则表达式引擎。
 *
 * 编译原理：字符串应用很多。
 *
 * DNA：超长字符串。
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
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }


    // 查询单词是否在Trie中
    public boolean contains(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            // 每遍历到的都转成一个char
            char c = word.charAt(i);
            // 没有该word这整个字符串
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 最终要判断是否匹配这个单词，而非直接return true
        return cur.isWord;
    }


    // 是否有与指定前缀匹配的单词
    public boolean inPrefix(String prefix) {

        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            // 每遍历到的都转成一个char
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 这里就直接return true
        return true;
    }

    // 删除word，返回是否删除成功
    public boolean remove(String word) {

        // 将搜索沿路的节点放入栈中
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        for (int i = 0; i < word.length(); i++) {
            if (!stack.peek().next.containsKey(word.charAt(i))) {
                return false;
            }
            stack.push(stack.peek().next.get(word.charAt(i)));
        }

        if (!stack.peek().isWord) {
            return false;
        }

        // 将该单词结尾isWord置空
        stack.peek().isWord = false;
        size--;

        // 如果单词最后一个字母的节点的next非空
        // 说明trie中还存储了其他以该单词为前缀的单词，直接返回
        if (stack.peek().next.size() > 0) {
            return true;
        } else {
            stack.pop();
        }

        // 自底向上删除
        for (int i = word.length() - 1; i >= 0; i--) {
            stack.peek().next.remove(word.charAt(i));
            // 如果一个节点的isWord为true，或者是其他单词的前缀，则直接返回
            if (stack.peek().isWord || stack.peek().next.size() > 0) {
                return true;
            }
            stack.pop();
        }
        return true;
    }

}

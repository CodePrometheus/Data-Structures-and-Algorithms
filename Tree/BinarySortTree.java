//二叉排序
//树
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BST bst = new BST();
//        循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            bst.add(new Node(arr[i]));
        }

//        中序遍历二叉树
        System.out.println("中序遍历二叉树");
        bst.infixOrder();

        System.out.println("bst.getRoot() = " + bst.getRoot());

//        删除叶子结点
        bst.delNode(7);
        bst.delNode(9);
        bst.delNode(1);
        bst.delNode(12);
        bst.delNode(3);
        bst.delNode(5);
        bst.delNode(10);
        bst.delNode(0);

        System.out.println("删除后");
        bst.infixOrder();
        System.out.println("bst.getRoot() = " + bst.getRoot());
    }
}

//创建二叉排序树
class BST {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //    查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //    查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //    两个子树的结点删除
    public int delRightTreeMin(Node node) {
        Node target = node;
//        循环查找左节点，找到最小值
        while (target.left != null) {
            target = target.left;
        }
//        这时target指向了最小结点
        delNode(target.value);
        return target.value;
    }


    //    删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
//            先找到需要删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
//            只有一个结点，
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

//            去找到targetNode的父节点
            Node parent = searchParent(value);

//            删除的是非叶子结点
            if (targetNode.left == null && targetNode.right == null) {
//                判断targetNode是父结点的左右子节点
                if (parent.left != null && parent.left.value == value) {//左
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//右
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的结点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            } else {//只有一颗子树
//                存在左子结点
                if (targetNode.left != null) {

                    if (parent != null) {
                        if (parent.left.value == value) {
                            //如果targetNode是parent的左子结点
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }

                } else {//如果要删除的结点有右子结点

                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }


    //    添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //    中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空");
        }
    }
}


//创建结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //    查找要删除的结点
    public Node search(int value) {
        if (value == this.value) {//一步找到
            return this;
        } else if (value < this.value) {//如果查找的值小于当前结点，向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //    查找要删除的结点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;//当前就是目标找到直接返回
        } else {
//            查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//无父节点
            }
        }
    }


    //    添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }

//        判断传入的结点的值，和当前子树的根结点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {//当前结点左子节点为null
                this.left = node;
            } else {
//                递归地向左子树添加
                this.left.add(node);
            }
        } else {//添加的结点的值大于当前结点值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //    中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}



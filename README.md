# Data-Structures-and-Algorithms
All Data Structures and Algorithms implemented in Java

## 数据结构篇

### 数组
* 封装自定义数组
* 增删改查
* 使用泛型
* 动态数组

### 栈
* 基本使用
* 基本应用

### 队列
* 数组队列
* 循环队列


### 链表
* 增删改查
* 链表实现栈
* 链表实现队列


### 二叉树
* 增删改查
* 递归实现
* 非递归实现


## 堆
* 增删改查
* 优先队列

### 集合与映射
* set
* map

### Tree
树结构本身是一种天然的的组织结构，用树存储数据能更加高效地搜索。

二叉树：和链表一样，动态数据结构。二叉树天然的递归结构，空也是一颗二叉树。

* 1）、对于每一个节点，最多能分成2个节点，即左孩子和右孩子。
* 2）、没有孩子的节点称为叶子节点。
* 3）、每一个孩子节点最多只能有一个父亲节点。
* 4）、二叉树具有天然的递归结构，即每个节点的左右子树都是二叉树。
注意：一个节点也是二叉树、空也是二叉树。

满二叉树：除了叶子节点外，每个节点都有两个子节点。

二分搜索树：

* 1）、二分搜索树是一个二叉树，且其每一颗子树也是二分搜索树。
* 2）、二分搜索树的每个节点的值大于其左子树的所有节点的值，小于其右子树的所有节点的值。
* 3）、存储的元素必须有可比较性。
* 4）、通常来说，二分搜索树不包含重复元素。如果想包含重复元素的话，只需定义： 左子树小于等于节点；或者右子树大于等于节点。注意：数组和链表可以有重复元素。
什么是遍历操作？

* 1）、遍历就是把所有的节点都访问一遍。
* 2）、访问的原因和业务相关。
* 3）、在线性结构下，遍历是极其容易的，但是在树结构中，遍历会稍微有点难度。
如何对二叉树进行遍历？

对于遍历操作，两颗子树都要顾及。

* 前序遍历：最自然和常用的遍历方式。规律：根左右
* 中序遍历：规律：左根右
* 后序遍历：中序遍历的结果就是我们在二叉搜索树中所有数据排序后的结果。规律：左右根。应用：为二分搜索树释放内存。
心算出二叉搜索树的前中后序遍历：每一个二叉树都会被访问三次，从根节点出发，

* 前序遍历：当一个节点被访问第一次就记录它。
* 中序遍历：当一个节点被访问第二次的时候记录它。
* 后序遍历：当一个节点被访问第三次的时候才记录它。
* 前序遍历的非递归实现（深度优先遍历）：需要使用栈记录下一步被访问的元素。

对于二叉搜索树的非递归实现一般有两种写法：

* 1）、经典教科书写法。
* 2）、完全模拟系统调用栈的写法。
层序遍历（广度优先遍历）：需要使用队列记录当前出队元素的左右子节点。

广度优先遍历的意义：

* 1）、在于快速地查询要搜索的元素。
* 2）、更快地找到问题的解。
* 3）、常用于算法设计中——无权图最短路径。
* 4）、联想对比图的深度优先遍历与广度优先遍历。
从二分搜索树中删除最小值与最大值：往左走的最后一个节点即是存有最小值的节点，往右走的最后一个节点即是存有最大值的节点。

删除二分搜索树种的任意元素：

* 1）、删除只有左孩子的节点。
* 2）、删除只有右孩子的节点。
* 3）、删除具有左右孩子的节点：
1、找到 s = min(d->right), s 是 d 的后继(successor)节点，也即 d 的右子树中的最小节点。 s->right = delMin(d->right), s->left = d->left, 删除 d，s 是新的子树的根。

2、找到 p = max(d->left), p 是 d 的前驱(predecessor)节点。

如何高效实现 rank（E 是排名第几的元素）？ 如何高效实现 select（查找排名第10的元素）？

最好的方式是实现一个维护 size 的二分搜索树：给 Node 节点添加新的成员变量 size。

### UnionFind

### 哈希表

### 时间复杂度

#### 1、到底什么是大 O？
n 表示数据规模，O(f(n)) 表示运行算法所需要执行的指令数，和 f(n) 成正比。

#### 2、数据规模的概念——如果想在1s之内解决问题
* 1）、O(n^2) 的算法可以处理大约10^4级别的数据。
* 2）、O(n) 的算法可以处理大约10^8级别的数据。
* 3）、O(nlogn) 的算法可以处理大约10^7级别的数据。

#### 3、空间复杂度
* 1）、多开一个辅助的数组：O(n)。
* 2）、多开一个辅助的二维数组：O(n^2)。
* 3）、多开常数空间：O(1)。
* 4）、递归是有空间代价的，递归 n 次，空间复杂度就为 O(n)。
#### 4、简单的时间复杂度分析
为什么要用大O，叫做大O(n)？
忽略了常数，实际时间 T = c1 * n + c2。

为甚不加上其中每一个常数项，而是要忽略它？
比如说把一个数组中的元素取出来这个操作，很有可能不同的语音基于不同的实现，它实际运行的时间是不等的。 就算转换成机器码，它对应的那个机器码的指令数也有可能是不同的。就算指令数是相同的，同样一个指令在 CPU 的底层，你使用的 CPU 不同，很有可能执行的操作也是不同的。所以，在实际上我们大概能说出来这个 c1 是多少，但是很难准确判断其具体的值。

大O的表示的是渐进时间复杂度，当 n 趋近于无穷大的时候，其算法谁快谁慢。

#### 5、亲自试验自己算法的时间复杂度
O(log(n)) 与 O(n) 有着本质的差别。

#### 6、递归算法的复杂度分析
* 1）、不是有递归的函数就是一定是 O(nlogn) 的。
* 2）、递归中进行一次递归调用：递归函数的时间复杂度为 O(T * depth)。
* 3）、递归中进行多次递归调用：画出递归树，计算调用次数。例如2次递归调用：2^0 + ... 2^n = 2^(n+1) - 1 = O(2^n)
* 4）、主定理：归纳了递归函数所计算时间复杂度的所有情况。

#### 7、均摊时间复杂度分析（Amortized Time Analysis）与 避免复杂度的震荡
##### 均摊时间复杂度分析
假设 capacity = n，n + 1 次 addLast/removeLast，触发 resize，总共进行 2n + 1 次基本操作平均来看，每次 addLast/removeLast 操作，进行 2 次基本操作均摊计算，时间复杂度为 O（1）。

##### 复杂度震荡
当反复先后调用 addLast/removeLast 进行操作时，会不断地进行 扩容/缩容，此时时间复杂度为 O（n）出现问题的原因：removeLast 时 resize 过于着急。 解决方案：Lazy （在这里是 Lazy 缩容）等到 size == capacity / 4 时，才将 capacity 减半。


## 算法篇
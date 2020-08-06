package com.walor.javatest;

import android.os.Build;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import androidx.annotation.RequiresApi;

/**
 * <pre>
 *     author : walorwang
 *     time   : 2020/07/14
 *     desc   : 真题案例（二）：数据结构训练
 * </pre>
 */
class DataStruct {

    /**
     * 例题 1：反转字符串中的单词
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * 关于数据顺序敏感的结构有栈和队列
     * 有逆序操作用栈，没有逆序操作用队列
     * 用空格把句子分割成单词。
     */
//    public static void main(String[] args) {
//        String ss = "This is a   good example";
//        System.out.println(reverseWords(ss));
//    }
    private static String reverseWords(String s) {
        Stack stack = new Stack();
        String temp = "";
        for (int i = 0, len = s.length(); i < len; i++) {
            if (s.charAt(i) != ' ') {
                temp += s.charAt(i);
            } else if (temp != "") {
                stack.push(temp);
                temp = "";
            } else {
                continue;
            }
        }
        // 最后一个单词入栈
        if (temp != "") {
            stack.push(temp);
        }

        String result = "";
        while (!stack.empty()) {
            result += stack.pop() + " ";
        }
        // 去掉最后一个空格
        return result.substring(0, result.length() - 1);
    }

    /**
     * 例题 2：树的层序遍历
     * 给定一棵树，按照层次顺序遍历并打印这棵树。
     * 结果从后往前看，结果对上下级关系的顺序非常敏感。
     * 根结点入队列，左孩子入队列，右孩子入队列。直到队列为空。
     * 时空复杂度都是O(n)。
     */
    public static void levelTraverse(Node root) {
        // LinkedList就是个队列
        LinkedList<Node> queue = new LinkedList<>();
        Node current;
        queue.offer(root); // 根结点入队
        while (!queue.isEmpty()) {
            // 出队队头元素
            current = queue.poll();
            System.out.print(current.data);
            // 左子树不为空，入队
            if (current.leftChild != null)
                queue.offer(current.leftChild);
            // 右子树不为空，入队
            if (current.rightChild != null)
                queue.offer(current.rightChild);
        }
    }

    class Node {
        String data;
        Node leftChild;
        Node rightChild;
    }

    /**
     * 例题 3：查找数据流中的中位数
     * 在一个流式数据中，查找中位数。如果是奇数个，则返回偏左边的那个元素。
     * 一组数字按照从小到大排列后，位于中间位置的那个数字。
     * 大顶堆是一棵完全二叉树，父结点的数值比子结点的数值大。
     * 小顶堆正好相反，父结点的数值比子结点的数值小。
     * 中位数左边的数据都保存在大顶堆中。
     * 中位数右边的数据都保存在小顶堆中。
     * 最后保证两个堆保存的数据个数相等或只差一个。
     * 插入数据的时间复杂度是O(logn)。
     * 插入后的中位数肯定在大顶堆的堆顶元素上。
     * 所以找到中位数的时间复杂度就是 O(1)。
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    static class Test {
        int count = 0;
        // 小顶堆
        static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 大顶堆
        static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        public static void main(String[] args) {
            Test t = new Test();
            t.insert(1);
            t.insert(2);
            t.insert(0);
            t.insert(20);
            t.insert(10);
            t.insert(22);
        }

        public void insert(Integer num) {
            if (count % 2 == 0) {
                // 加入到小顶堆
                minHeap.offer(num);
                // 弹出小顶堆的堆顶
                maxHeap.offer(minHeap.poll());
            } else {
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            }
            count++;
            System.out.println(Test.getMedian());
        }

        public static int getMedian() {
            // 返回大顶堆的堆顶
            return maxHeap.peek();
        }
    }
}

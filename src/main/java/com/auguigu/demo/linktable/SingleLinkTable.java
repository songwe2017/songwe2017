package com.auguigu.demo.linktable;/**
 * @author Songwe
 * @date 2022/5/7 19:22
 */
public class SingleLinkTable<E> {
    
    private final Node head = new Node(null);

    /**
     * 链表的删除如果没有虚拟头节点首先应该设置一个虚拟头节点，因为如果删除的是第一个节点就要单独写逻辑
     * 设置了虚拟头节点可以统一操作
     * 另外，删除过程中最重要的就是及时更新 pre 节点和 cur 节点两个节点的值
     */
    public boolean remove(E e) {
        if (head.next == null) {
            return false;
        }
        Node pre = head;
        Node cur = pre.next;
        while (cur != null) {
            if (cur.item == e) {
                // cur 节点就是要删除的节点，将 cur 节点的前一个节点 pre 的下一个节点指针指向 cur 节点的下一个节点
                pre.next = cur.next;
                // 此时 cur 节点指向下一个节点的指针还存在，所以把他指向 null，垃圾回收该节点
                cur.next = null; //help GC
                return true;
            }
            else {
                // cur 节点不是要删除的节点，这里一定要更新 pre 节点成 cur 节点。
                // 否则：1 -> 2 -> 3 -> 4，假如 pre 节点是 1，cur 节点是 2 不是待删除节点，那么下面 cur 节点移动到 3 是待删除节点，而 pre 节点还是 1，
                // 则下次循环进入上面分支将 pre 的 next 指向 4，中间的 2 就无效了，变成 1 -> 4,而 2 的下一个节点 3 已经被删了。
                pre = cur;                                                          
            }
            // 把 cur 节点往后移一个节点
            cur = cur.next;
        }
        return false;
    }
    
    
    static class Node<E> {
        E item;
        
        Node<E> next;
        
        Node(E e) {
            item = e;
        }
        
        Node(E e, Node next) {
            item = e;
            next = next;
        }
    }
}

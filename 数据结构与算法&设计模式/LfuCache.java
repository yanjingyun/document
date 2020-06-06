package com.yjy;

import java.util.HashMap;
import java.util.Map;

/**
 * LFU(Least frequently used,最不经常使用算法)实现：双向链表+HashMap
 * 双向链表：插入数据，最新的放前面，最后面的为就数据。插入数据情况：未满-直接将数据插入头部；已满-将数据插入头部，去掉最后一个数据
 * HashMap：记录链表的每个节点。插入元素时，先从HashMap中获取节点，无-则直接将数据插入链表头部，然后将链表尾节点删除；有-则将该节点移到头部，调整链表相关
 */
public class LfuCache {

	ListNode head;
	ListNode last;
	int limit = 4;

	Map<String, ListNode> hashMap = new HashMap<>();

	// 双向链表
	public static class ListNode {
		String key;// 这里存储key便于元素满时，删除尾节点时可以快速从HashMap删除键值对
		Integer value;
		ListNode pre = null;
		ListNode next = null;

		ListNode(String key, Integer value) {
			this.key = key;
			this.value = value;
		}
	}

	public void add(String key, Integer val) {
		ListNode existNode = hashMap.get(key);
		if (existNode != null) {
			// 从链表中删除这个元素
			ListNode pre = existNode.pre;
			ListNode next = existNode.next;
			if (pre != null) {
				pre.next = next;
			}
			if (next != null) {
				next.pre = pre;
			}
			// 更新尾节点
			if (last == existNode) {
				last = existNode.pre;
			}
			// 移动到最前面
			head.pre = existNode;
			existNode.next = head;
			head = existNode;
			// 更新值
			existNode.value = val;
		} else {
			// 达到限制，先删除尾节点
			if (hashMap.size() == limit) {
				ListNode deleteNode = last;
				hashMap.remove(deleteNode.key);
				// 正是因为需要删除，所以才需要每个ListNode保存key
				last = deleteNode.pre;
				deleteNode.pre = null;
				last.next = null;
			}
			ListNode node = new ListNode(key, val);
			hashMap.put(key, node);
			if (head == null) {
				head = node;
				last = node;
			} else {
				// 插入头结点
				node.next = head;
				head.pre = node;
				head = node;
			}
		}

	}

	public ListNode get(String key) {
		return hashMap.get(key);
	}

	public void remove(String key) {
		ListNode deleteNode = hashMap.get(key);
		ListNode preNode = deleteNode.pre;
		ListNode nextNode = deleteNode.next;
		if (preNode != null) {
			preNode.next = nextNode;
		}
		if (nextNode != null) {
			nextNode.pre = preNode;
		}
		if (head == deleteNode) {
			head = nextNode;
		}
		if (last == deleteNode) {
			last = preNode;
		}
		hashMap.remove(key);
	}

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		LfuCache aa = new LfuCache();
		aa.add("1", 1);
		aa.add("2", 2);
		aa.add("3", 3);
		aa.add("4", 4);
		System.out.println(aa.get("1").value);
		aa.add("5", 5);
		System.out.println(aa.get("2").value);
		System.out.println(aa.get("4").value);
	}
}

package com.yjy.tree;

import java.util.LinkedList;
import java.util.Stack;

public class TreeTest {

	/**
	 * 二叉树类
	 */
	public static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		TreeNode[] nodes = buildTree();
		preOrder(nodes[0]);

		// 前序遍历
		System.out.println();
		preOrder2(nodes[0]);
		System.out.println();

		// 中序遍历
		midOrder(nodes[0]);
		System.out.println();
		midOrder2(nodes[0]);
		System.out.println();

		// 后序遍历
		postOrder(nodes[0]);
		System.out.println();
		postOrder2(nodes[0]);
		System.out.println();
		
		// 层次遍历
		levelOrder(nodes[0]);
	}

	/**
	 * 以数组形式生成一棵完全二叉树，结构如下： 0 / \ 1 2 / \ /\ 3 4 5 6 /\ / 7 8 9
	 */
	private static TreeNode[] buildTree() {
		TreeNode[] nodes = new TreeNode[10];
		for (int i = 0; i < 10; i++) {
			nodes[i] = new TreeNode(i);
		}
		for (int i = 0; i < 10; i++) {
			if (i * 2 + 1 < 10)
				nodes[i].left = nodes[i * 2 + 1];
			if (i * 2 + 2 < 10)
				nodes[i].right = nodes[i * 2 + 2];
		}
		return nodes;
	}

	// 前序遍历-递归实现
	private static void preOrder(TreeNode node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	// 前序遍历-非递归实现
	private static void preOrder2(TreeNode node) {
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				System.out.print(node.value + " ");
				stack.push(node.right);
				node = node.left;
			}

			if (!stack.isEmpty()) {
				node = stack.pop();
			}
		}
	}

	// 中序遍历-递归实现
	private static void midOrder(TreeNode node) {
		if (node != null) {
			midOrder(node.left);
			System.out.print(node.value + " ");
			midOrder(node.right);
		}
	}

	// 中序遍历-非递归实现
	private static void midOrder2(TreeNode node) {
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			if (!stack.isEmpty()) {
				node = stack.pop();
				System.out.print(node.value + " ");
				node = node.right;
			}
		}
	}

	// 后序遍历-递归实现
	private static void postOrder(TreeNode node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.value + " ");
		}
	}

	// 后序遍历-非递归实现
	private static void postOrder2(TreeNode node) {
		int left = 1;// 在辅助栈里表示左节点
		int right = 2;// 在辅助栈里表示右节点
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();// 辅助栈，用来判断子节点返回父节点时处于左节点还是右节点。

		while (node != null || !stack.empty()) {
			while (node != null) {// 将节点压入栈1，并在栈2将节点标记为左节点
				stack.push(node);
				stack2.push(left);
				node = node.left;
			}
			while (!stack.empty() && stack2.peek() == right) {// 如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
				stack2.pop();
				System.out.print(stack.pop().value + " ");
			}
			if (!stack.empty() && stack2.peek() == left) {// 如果是从左子节点返回父节点，则将标记改为右子节点
				stack2.pop();
				stack2.push(right);
				node = stack.peek().right;
			}

		}
	}

	// 层次遍历
	public static void levelOrder(TreeNode node) {
		if (node == null)
			return;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(node);
		TreeNode currentNode;
		while (!list.isEmpty()) {
			currentNode = list.poll();
			System.out.print(currentNode.value + " ");
			if (currentNode.left != null)
				list.add(currentNode.left);
			if (currentNode.right != null)
				list.add(currentNode.right);
		}
	}
}

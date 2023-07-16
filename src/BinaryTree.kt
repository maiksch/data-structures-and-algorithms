import java.util.LinkedList

data class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
	var parent: TreeNode? = null

	fun isRoot(): Boolean {
		return parent == null
	}

	fun isLeaf(): Boolean {
		return left == null && right == null
	}
}

class BinaryTree(private val root: TreeNode) {
	fun preOrderSearch(): Array<Int> {
		fun traverse(node: TreeNode, list: LinkedList<Int>): LinkedList<Int> {
			list.add(node.value)
			node.left?.let { traverse(it, list) }
			node.right?.let { traverse(it, list) }
			return list
		}

		return traverse(root, LinkedList<Int>()).toTypedArray()
	}

	fun inOrderSearch(): Array<Int> {
		fun traverse(node: TreeNode, list: LinkedList<Int>): LinkedList<Int> {
			node.left?.let { traverse(it, list) }
			list.add(node.value)
			node.right?.let { traverse(it, list) }
			return list
		}

		return traverse(root, LinkedList<Int>()).toTypedArray()
	}

	fun postOrderSearch(): Array<Int> {
		fun traverse(node: TreeNode, list: LinkedList<Int>): LinkedList<Int> {
			node.left?.let { traverse(it, list) }
			node.right?.let { traverse(it, list) }
			list.add(node.value)
			return list
		}

		return traverse(root, LinkedList<Int>()).toTypedArray()
	}

	fun depthFirstSearch(needle: Int): Boolean {
		fun traverse(node: TreeNode?): Boolean {
			if (node == null) {
				return false
			}
			if (node.value == needle) {
				return true
			}
			if (node.value >= needle) {
				return traverse(node.left)
			}
			return traverse(node.right)
		}
		return traverse(root)
	}

	fun breadthFirstSearch(needle: Int): Boolean {
		val queue = Queue<TreeNode>()
		var node: TreeNode? = root

		while (node != null) {
			if (node.value == needle) {
				return true
			}
			node.left?.let(queue::enqueue)
			node.right?.let(queue::enqueue)
			node = queue.deque()
		}

		return false
	}


	fun compare(other: BinaryTree): Boolean {
		fun traverse(a: TreeNode?, b: TreeNode?): Boolean {
			if (a == null && b == null) {
				return true
			}
			if (a == null || b == null) {
				return false
			}
			if (a.value != b.value) {
				return false
			}

			return traverse(a.left, b.left) && traverse(a.right, b.right)
		}

		return traverse(root, other.root)
	}
}
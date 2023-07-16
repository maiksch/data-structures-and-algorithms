import com.sun.source.tree.Tree

class BinarySearchTree(private var root: TreeNode? = null) {

	fun add(value: Int) {
		val newNode = TreeNode(value)
		if (root == null) {
			root = newNode
			return
		}

		fun traverse(node: TreeNode) {
			if (value > node.value) {
				node.right?.let(::traverse) ?: let {
					node.right = newNode
					newNode.parent = node
				}
			} else {
				node.left?.let(::traverse) ?: let {
					node.left = newNode
					newNode.parent = node
				}
			}
		}

		root?.let(::traverse)
	}

	fun remove(value: Int) {
		val node = find(value) ?: return

		// 1st case: leaf node

		if (node.isLeaf()) {
			if (node.isRoot()) {
				root = null
			}
			if (node.parent?.left == node) {
				node.parent?.left = null
			} else {
				node.parent?.right = null
			}
			return
		}

		// 2nd case: one child

		if (node.left != null && node.right == null) {
			node.parent?.let { it.left = node.left } ?: let { root = node.left }
			return
		}

		if (node.right != null && node.left == null) {
			node.parent?.let { it.right = node.right } ?: let { root = node.right }
			return
		}

		// 3rd case: both children

		val replacerNode = node.left?.let(::findHighestLow) ?: TODO()
		replacerNode.right = node.right
		replacerNode.parent?.right = replacerNode.left
		replacerNode.left = replacerNode.parent

		if (node.parent?.left == node) {
			node.parent?.left = replacerNode
		} else {
			node.parent?.right = replacerNode
		}

		if (node.isRoot()) {
			root = replacerNode
		}
	}

	private fun findHighestLow(node: TreeNode): TreeNode {
		return node.right?.let(::findHighestLow) ?: node
	}

	private fun findLowestHigh(node: TreeNode): TreeNode {
		return node.left?.let(::findLowestHigh) ?: node
	}

	fun find(needle: Int): TreeNode? {
		fun traverse(node: TreeNode?): TreeNode? {
			if (node == null) {
				return null
			}
			if (node.value == needle) {
				return node
			}
			if (node.value >= needle) {
				return traverse(node.left)
			}
			return traverse(node.right)
		}
		return traverse(root)
	}

	fun compare(other: BinarySearchTree): Boolean {
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

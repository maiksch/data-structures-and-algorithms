import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BinaryTreeTest {
	@Test
	fun test() {
		val tree = BinaryTree(
			TreeNode(
				20,
				left = TreeNode(
					10,
					left = TreeNode(
						5,
						left = null,
						right = TreeNode(7),
					),
					right = TreeNode(15)
				),
				right = TreeNode(
					50,
					left = TreeNode(
						30,
						left = TreeNode(29),
						right = TreeNode(45),
					),
					right = TreeNode(100)
				)
			)
		)

		val other = BinaryTree(TreeNode(
			value = 20,
			left = TreeNode(
				value = 10,
				left = TreeNode(
					value = 5,
					left = null,
					right = TreeNode(7),
				),
				right = TreeNode(15)
			),
			right = TreeNode(
				value = 50,
				left = TreeNode(
					value = 30,
					left = TreeNode(
						value = 29,
						left = TreeNode(21),
						right = null
					),
					right = TreeNode(
						value = 45,
						left = null,
						right = TreeNode(49)
					),
				),
				right = null
			)
		))

		assertContentEquals(
			arrayOf(20, 10, 5, 7, 15, 50, 30, 29, 45, 100), tree.preOrderSearch()
		)
		assertContentEquals(
			arrayOf(5, 7, 10, 15, 20, 29, 30, 45, 50, 100), tree.inOrderSearch()
		)
		assertContentEquals(
			arrayOf(7, 5, 15, 10, 29, 45, 30, 100, 50, 20), tree.postOrderSearch()
		)
		assertTrue(tree.breadthFirstSearch(45))
		assertTrue(tree.breadthFirstSearch(7))
		assertFalse(tree.breadthFirstSearch(69))
		assertTrue(tree.compare(tree))
		assertFalse(tree.compare(other))
		assertTrue(tree.depthFirstSearch(45))
		assertTrue(tree.depthFirstSearch(7))
		assertFalse(tree.depthFirstSearch(69))
	}
}
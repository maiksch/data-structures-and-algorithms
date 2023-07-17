import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BinarySearchTreeTest {
	@Test
	fun test() {
		val other = BinarySearchTree(
			TreeNode(
				10,
				left = TreeNode(
					5,
					left = TreeNode(1, right = TreeNode(2)),
					right = TreeNode(8, left = TreeNode(6), right = TreeNode(9))
				),
				right = TreeNode(20)
			)
		)
		val newTree = BinarySearchTree()

		newTree.add(10)
		newTree.add(20)
		newTree.add(5)

		assertEquals(newTree.find(5), TreeNode(5))
		assertNull(newTree.find(1))

		newTree.add(1)
		newTree.add(2)
		newTree.add(8)
		newTree.add(9)
		newTree.add(6)

		assertTrue(newTree.compare(other))

		newTree.remove(20)

		assertNull(newTree.find(20))

		newTree.remove(5)

		assertNull(newTree.find(5))
	}
}
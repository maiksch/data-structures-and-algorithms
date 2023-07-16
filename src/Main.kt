import kotlin.test.*

interface List<T> {
	var size: Int
	fun prepend(item: T)
	fun insertAt(item: T, idx: Int)
	fun append(item: T)
	fun remove(item: T): T?
	fun get(idx: Int): T?
	fun removeAt(idx: Int): T?
}

fun main() {
	quickSortTest()

	queueTest()

	listTest(DoublyLinkedList())

	treeTest {
		val tree = BinaryTree(it)
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
		assertFalse(tree.compare(BinaryTree(getFakeTree())))
		assertTrue(tree.depthFirstSearch(45))
		assertTrue(tree.depthFirstSearch(7))
		assertFalse(tree.depthFirstSearch(69))
	}

	bstTest()

	minHeapTest()
}

fun quickSortTest() {
	assertContentEquals(
		arrayOf(3, 4, 7, 9, 42, 69, 420), QuickSort(arrayOf(9, 3, 7, 4, 69, 420, 42))
	)
}

fun bstTest() {
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

fun treeTest(test: (TreeNode) -> Unit) {
	val tree = TreeNode(
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
	test(tree)
}

fun getFakeTree(): TreeNode {
	return TreeNode(
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
	)
}

fun listTest(list: List<Int>) {
	list.append(5)
	list.append(7)
	list.append(9)

	assertEquals(9, list.get(2))
	assertEquals(7, list.removeAt(1))
	assertEquals(2, list.size)

	list.append(11)

	assertEquals(9, list.removeAt(1))
	assertEquals(null, list.remove(9))
	assertEquals(5, list.removeAt(0))
	assertEquals(11, list.removeAt(0))
	assertEquals(0, list.size)

	list.prepend(5)
	list.prepend(7)
	list.prepend(9)

	assertEquals(5, list.get(2))
	assertEquals(9, list.get(0))
	assertEquals(9, list.remove(9))
	assertEquals(2, list.size)
	assertEquals(7, list.get(0))
}

fun queueTest() {
	val queue = Queue<Int>()
	queue.enqueue(5)
	queue.enqueue(7)
	queue.enqueue(9)
	assertEquals(3, queue.size)
	assertEquals(5, queue.deque())
	assertEquals(2, queue.size)
	queue.enqueue(11)
	assertEquals(7, queue.deque())
	assertEquals(9, queue.deque())
	assertEquals(11, queue.deque())
	assertEquals(0, queue.size)
}

fun minHeapTest() {
	val heap = MinHeap()

	assertEquals(heap.size, 0)

	heap.insert(5)
	heap.insert(3)
	heap.insert(69)
	heap.insert(420)
	heap.insert(4)
	heap.insert(1)
	heap.insert(8)
	heap.insert(7)

	assertEquals(8, heap.size)
	assertEquals(1, heap.delete())
	assertEquals(3, heap.delete())
	assertEquals(4, heap.delete())
	assertEquals(5, heap.delete())
	assertEquals(4, heap.size)
	assertEquals(7, heap.delete())
	assertEquals(8, heap.delete())
	assertEquals(69, heap.delete())
	assertEquals(420, heap.delete())
	assertEquals(0, heap.size)
}
import kotlin.test.Test
import kotlin.test.assertEquals

class MinHeapTest {
	@Test
	fun test() {
		val heap = MinHeap<Int>()

		assertEquals( 0, heap.size)

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
}
import kotlin.test.Test
import kotlin.test.assertEquals

class QueueTest {
	@Test
	fun test() {
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
}
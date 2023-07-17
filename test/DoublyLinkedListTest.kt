import kotlin.test.Test
import kotlin.test.assertEquals

class DoublyLinkedListTest {
	@Test
	fun test() {
		val list = DoublyLinkedList<Int>()

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
}
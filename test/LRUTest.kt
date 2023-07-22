import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LRUTest {
	@Test
	fun test() {
		val lru = LRU<String, Int>(3)

		assertNull(lru.get("foo"))

		lru.update("foo", 69)
		assertEquals(69, lru.get("foo"))

		lru.update("bar", 420)
		assertEquals(420, lru.get("bar"))

		lru.update("baz", 1337)
		assertEquals(1337, lru.get("baz"))

		lru.update("ball", 69420)
		assertEquals(69420, lru.get("ball"))

		assertNull(lru.get("foo"))
		assertEquals(420, lru.get("bar"))

		lru.update("foo", 69)
		assertEquals(420, lru.get("bar"))
		assertEquals(69, lru.get("foo"))
		assertNull(lru.get("baz"))
	}
}
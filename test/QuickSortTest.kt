import kotlin.test.Test
import kotlin.test.assertContentEquals

class QuickSortTest {
	@Test
	fun test() {
		val expect = arrayOf(3, 4, 7, 9, 42, 69, 420)
		val actual = arrayOf(9, 3, 7, 4, 69, 420, 42).quickSort()

		assertContentEquals(expect, actual)
	}
}
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNull

class GraphTest {
	@Test
	fun testDepthFirstSearch() {
		//     >(1)<--->(4) ---->(5)
		//    /          |       /|
		// (0)     ------|------- |
		//    \   v      v        v
		//     >(2) --> (3) <----(6)
		val list: WeightedAdjacencyList = arrayOf(
			arrayOf(GraphEdge(1, 3), GraphEdge(2, 1)),
			arrayOf(GraphEdge(4, 1)),
			arrayOf(GraphEdge(3, 7)),
			arrayOf(),
			arrayOf(GraphEdge(1, 1), GraphEdge(3, 5), GraphEdge(5, 2)),
			arrayOf(GraphEdge(2, 18), GraphEdge(6, 1)),
			arrayOf(GraphEdge(3, 1)),
		)

		assertContentEquals(arrayOf(0, 1, 4, 5, 6), list.depthFirstSearch(0, 6))
		assertNull(list.depthFirstSearch(6, 0))
	}

	@Test
	fun testBreadthFirstSearch() {
		//     >(1)<--->(4) ---->(5)
		//    /          |       /|
		// (0)     ------|------- |
		//    \   v      v        v
		//     >(2) --> (3) <----(6)
		val matrix: WeightedAdjacencyMatrix = arrayOf(
			arrayOf(0, 3, 1, 0, 0, 0, 0),
			arrayOf(0, 0, 0, 0, 1, 0, 0),
			arrayOf(0, 0, 7, 0, 0, 0, 0),
			arrayOf(0, 0, 0, 0, 0, 0, 0),
			arrayOf(0, 1, 0, 5, 0, 2, 0),
			arrayOf(0, 0, 18, 0, 0, 0, 1),
			arrayOf(0, 0, 0, 1, 0, 0, 1),
		)

		assertContentEquals(arrayOf(0, 1, 4, 5, 6), matrix.breadthFirstSearch(0, 6))
		assertNull(matrix.breadthFirstSearch(6, 0))
	}

	@Test
	fun testDijkstraShortestPath() {
		//      (1) --- (4) ---- (5)
		//    /  |       |       /|
		// (0)   | ------|------- |
		//    \  |/      |        |
		//      (2) --- (3) ---- (6)

		val list: WeightedAdjacencyList = arrayOf(
			arrayOf(GraphEdge(1, 3), GraphEdge(2, 1)),
			arrayOf(GraphEdge(0, 3), GraphEdge(2, 4), GraphEdge(4, 1)),
			arrayOf(GraphEdge(1, 4), GraphEdge(3, 7), GraphEdge(0, 1)),
			arrayOf(GraphEdge(2, 7), GraphEdge(4, 5), GraphEdge(6, 1)),
			arrayOf(GraphEdge(1, 1), GraphEdge(3, 5), GraphEdge(5, 2)),
			arrayOf(GraphEdge(6, 1), GraphEdge(4, 2), GraphEdge(2, 18)),
			arrayOf(GraphEdge(3, 1), GraphEdge(5, 1)),
		)

		assertContentEquals(arrayOf(0, 1, 4, 5, 6), list.dijkstraShortestPath(0, 6))
	}
}
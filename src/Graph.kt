data class GraphEdge(val to: Int, val weight: Int)
typealias WeightedAdjacencyList = Array<Array<GraphEdge>>
typealias WeightedAdjacencyMatrix = Array<Array<Int>>

fun WeightedAdjacencyList.depthFirstSearch(source: Int, needle: Int): Array<Int>? {
	fun walk(node: Int, needle: Int, seen: Array<Boolean>, path: Array<Int>): Array<Int>? {
		if (node == needle) {
			return path + node
		}

		if (seen[node]) {
			return null
		}

		seen[node] = true

		return this[node].firstNotNullOfOrNull { walk(it.to, needle, seen, path + node) }
	}

	return walk(source, needle, Array(size) { false }, arrayOf())
}

fun WeightedAdjacencyMatrix.breadthFirstSearch(source: Int, needle: Int): Array<Int>? {
	val queue = Queue<Pair<Int, Array<Int>>>()
	val seen = Array(size) { false }

	var node: Pair<Int, Array<Int>>? = Pair(source, arrayOf())
	while (node != null) {
		seen[node.first] = true
		val path = node.second + node.first

		if (node.first == needle) {
			return node.second + node.first
		}

		val adjacentNodes = this[node.first]
		for (i in adjacentNodes.indices) {
			if (adjacentNodes[i] == 0) {
				continue
			}
			if (seen[i]) {
				continue
			}
			queue.enqueue(Pair(i, path))
		}

		node = queue.deque()
	}

	return null
}
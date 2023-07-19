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

fun WeightedAdjacencyList.dijkstraShortestPath(source: Int, sink: Int): Array<Int> {
	val visited = Array(size) { false }
	val distance = Array(size) { Int.MAX_VALUE }
	val previous = Array(size) { -1 }
	distance[source] = 0

	var current: Int? = 0
	while (current != null) {
		val edges = this[current]
		for (edge in edges) {
			val cost = distance[current] + edge.weight
			if (cost < distance[edge.to]) {
				distance[edge.to] = cost
				previous[edge.to] = current
			}
		}
		visited[current] = true
		current = getLowestUnvisited(visited, distance)
	}

	val path = mutableListOf(sink)
	var prev = previous[sink]
	while (prev != -1) {
		path.add(prev)
		prev = previous[prev]
	}

	return path.reversed().toTypedArray()
}

fun getLowestUnvisited(visited: Array<Boolean>, distance: Array<Int>): Int? {
	var lowestIndex = -1
	var lowestDistance = Int.MAX_VALUE

	for (i in distance.indices) {
		if (distance[i] < lowestDistance && !visited[i]) {
			lowestDistance = distance[i]
			lowestIndex = i
		}
	}

	return if (lowestDistance != Int.MAX_VALUE) lowestIndex else null
}
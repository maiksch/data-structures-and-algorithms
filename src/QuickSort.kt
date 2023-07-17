import java.util.*

fun Array<Int>.quickSort(): Array<Int> {
	val stack = Stack<Pair<Int, Int>>()
	stack.push(Pair(0, size))

	while (stack.isNotEmpty()) {
		val current = stack.pop()

		if (current.second - current.first > 1) {
			val pivotIdx = current.first + ((current.second - current.first) / 2)
			var pivotPosition = 0
			val pivot = this[pivotIdx]
			var counter = current.first - 1

			for (i in current.first until current.second) {
				if (this[i] <= pivot) {
					counter += 1
					if (i != counter) {
						val temp = this[i]
						this[i] = this[counter]
						this[counter] = temp
					}
					if (i == pivotIdx) {
						pivotPosition = counter
					}
				}
			}

			this[pivotPosition] = this[counter]
			this[counter] = pivot

			stack.push(Pair(current.first, counter))
			stack.push(Pair(counter + 1, current.second))
		}
	}
	return this
}

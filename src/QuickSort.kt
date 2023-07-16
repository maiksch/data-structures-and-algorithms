import java.util.*

fun QuickSort(arr: Array<Int>): Array<Int> {
	val stack = Stack<Pair<Int, Int>>()
	stack.push(Pair(0, arr.size))

	while (stack.isNotEmpty()) {
		val current = stack.pop()

		if (current.second - current.first > 1) {
			val pivotIdx = current.first + ((current.second - current.first) / 2)
			var pivotPosition = 0
			val pivot = arr[pivotIdx]
			var counter = current.first - 1

			for (i in current.first until current.second) {
				if (arr[i] <= pivot) {
					counter += 1
					if (i != counter) {
						val temp = arr[i]
						arr[i] = arr[counter]
						arr[counter] = temp
					}
					if (i == pivotIdx) {
						pivotPosition = counter
					}
				}
			}

			arr[pivotPosition] = arr[counter]
			arr[counter] = pivot

			stack.push(Pair(current.first, counter))
			stack.push(Pair(counter + 1, current.second))
		}
	}

	return arr
}

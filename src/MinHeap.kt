class MinHeap {
	var size: Int = 0
	private var data = MyArrayList<Int>(8)

	fun insert(value: Int) {
		data += value
		heapifyUp(size)
		size += 1
	}

	private fun heapifyUp(idx: Int) {
		if (idx == 0) {
			return
		}

		val parentIdx = (idx - 1) / 2
		val parent = data[parentIdx]!!
		val value = data[idx]!!

		if (value < parent) {
			data[idx] = parent
			data[parentIdx] = value
			heapifyUp(parentIdx)
		}
	}

	fun delete(): Int? {
		val value = data[0] ?: return null

		data[0] = data[size - 1]
		data[size - 1] = null
		size -= 1
		heapifyDown(0)

		return value
	}

	private fun heapifyDown(idx: Int) {
		val rightIdx = (idx * 2) + 2
		val leftIdx = (idx * 2) + 1

		if (leftIdx >= size) {
			return
		}

		val value = data[idx] ?: return
		val right = data[rightIdx] ?: Int.MAX_VALUE
		val left = data[leftIdx] ?: return

		if (listOf(value, right, left).min() == value) {
			return
		}

		if (left <= right) {
			data[idx] = left
			data[leftIdx] = value
			heapifyDown(leftIdx)
		}

		if (right < left) {
			data[idx] = right
			data[rightIdx] = value
			heapifyDown(rightIdx)
		}
	}
}
class MyArrayList<T>(initialCapacity: Int) {
	private var data: Array<Any?>
	var size: Int = 0

	init {
		data = Array(initialCapacity) { null }
	}

	@Suppress("UNCHECKED_CAST")
	operator fun get(i: Int): T? {
		if (i >= size) {
			return null
		}

		return data[i] as T?
	}

	operator fun set(i: Int, value: T?) {
		if (i >= size) {
			return
		}

		data[i] = value
	}

	fun remove(item: T): T? {
		var index = -1

		for (i in 0 until size) {
			val value = data[i]
			if (item == value) {
				index = i
				break
			}
		}

		if (index == -1) {
			return null
		}

		return removeAt(index)
	}

	@Suppress("UNCHECKED_CAST")
	fun removeAt(index: Int): T? {
		if (index >= size) {
			return null
		}

		val value = data[index] as T?
		for (i in index + 1 until size) {
			data[i - 1] = data[i]
		}

		size -= 1
		data[size] = null

		return value
	}

	fun prepend(item: T) {
		if (size == data.size) {
			increaseSize()
		}

		for (i in size downTo 1) {
			data[i] = data[i - 1]
		}

		data[0] = item
		size += 1
	}

	operator fun plusAssign(item: T) {
		if (data.size == size) {
			increaseSize()
		}

		data[size] = item
		size += 1
	}

	private fun increaseSize() {
		data = data.copyInto(Array(size * 2) { null })
	}
}
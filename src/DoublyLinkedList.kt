class ListNode<T>(val value: T) {
	var next: ListNode<T>? = null
	var prev: ListNode<T>? = null
}

class DoublyLinkedList<T> {
	var size: Int = 0
	private var head: ListNode<T>? = null
	private var tail: ListNode<T>? = null

	fun prepend(item: T) {
		val node = ListNode(item)

		size += 1

		head?.let {
			it.prev = node
			node.next = it
			head = node
		}

		if (head == null) {
			head = node
			tail = node
		}

	}

	fun append(item: T) {
		val node = ListNode(item)

		size += 1

		tail?.let {
			it.next = node
			node.prev = it
			tail = node
		}

		if (tail == null) {
			head = node
			tail = node
		}
	}

	fun insertAt(item: T, idx: Int) {
		assert(idx in (0 until size))

		if (idx == 0) {
			return prepend(item)
		}

		if (idx == size - 1) {
			return append(item)
		}

		var current = head
		repeat(idx) {
			current = current?.next
		}

		val node = ListNode(item)
		current?.prev?.next = node
		node.prev = current?.prev
		current?.prev = node
		node.next = current

		size += 1
	}

	fun remove(item: T): T? {
		var current = head
		var idx = 0

		while (current != null && idx < size) {
			if (current.value == item) {
				current.prev?.next = current.next
				current.next?.prev = current.prev
				if (idx == 0) {
					head = current.next
				}
				if (idx == size - 1) {
					tail = current.prev
				}
				current.next = null
				current.prev = null

				size -= 1
				return current.value
			}
			idx += 1
			current = current.next
		}

		return null
	}

	fun removeAt(idx: Int): T? {
		assert(idx in (0 until size))

		if (idx == 0 && size == 1) {
			val value = head!!.value
			size = 0
			tail = null
			head = null
			return value
		}

		var current = head
		repeat(idx) {
			current = current!!.next
		}

		current?.prev?.next = current?.next
		current?.next?.prev = current?.prev
		if (idx == 0) {
			head = current?.next
		}
		if (idx == size - 1) {
			tail = current?.prev
		}

		size -= 1
		return current?.value
	}

	fun get(idx: Int): T? {
		assert(idx in (0 until size))

		var current = head
		repeat(idx) {
			current = current?.next
		}

		return current?.value
	}

}
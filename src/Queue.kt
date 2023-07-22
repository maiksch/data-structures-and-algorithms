class QueueNode<T>(var value: T, var next: QueueNode<T>? = null)

class Queue<T>() {
	var head: QueueNode<T>? = null
	var tail: QueueNode<T>? = null
	var size: Int = 0

	fun enqueue(item: T) {
		val node = QueueNode(item)

		if (tail == null) {
			size = 1
			head = node
			tail = node
			return
		}

		tail?.let {
			it.next = node
			size += 1
			tail = node
		}
	}

	fun deque(): T? {
		if (head == tail) {
			val node = head
			size = 0
			head = null
			tail = null
			return node?.value
		}

		return head?.let {
			head = it.next
			size -= 1
			it.value
		}
	}
}
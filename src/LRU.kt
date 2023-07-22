class LRU<K, V>(private var capacity: Int) {
	private var size: Int = 0
	private var data = DoublyLinkedList<V>()
	private var lookup = HashMap<K, ListNode<V>>(capacity)
	private var reverseLookup = HashMap<ListNode<V>, K>(capacity)

	fun update(key: K, value: V) {
		val entry = lookup[key]
		entry?.also { node ->
			node.value = value
			moveToFront(node)
		} ?: insert(key, value)
	}

	private fun insert(key: K, value: V) {
		val node = ListNode(value)

		prepend(node)
		lookup[key] = node
		reverseLookup[node] = key

		if (size == capacity) {
			evict()
		} else {
			size += 1
		}
	}

	fun get(key: K): V? {
		val node = lookup[key]?.also(::moveToFront)
		return node?.value
	}

	private fun prepend(node: ListNode<V>) {
		if (data.head == null) {
			data.head = node
			data.tail = node
			return
		}
		
		node.next = data.head
		data.head?.also { it.prev = node }
		data.head = node
	}

	private fun evict() {
		data.tail?.also {
			val key = reverseLookup[it]!!
			lookup.remove(key)
			reverseLookup.remove(it)
			it.prev?.next = null
			data.tail = it.prev
		}
	}

	private fun moveToFront(node: ListNode<V>) {
		if (data.head == node) {
			return
		}
		if (data.tail == node) {
			data.tail = node.prev
		}
		node.next?.also { it.prev = node.prev }
		node.prev?.also { it.next = node.next }
		node.prev = null
		node.next = null

		prepend(node)
	}
}
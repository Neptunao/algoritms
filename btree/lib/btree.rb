require 'pp'

class BTreeNode
	attr_accessor :keys
	attr_accessor :children
	attr_accessor :parent

	def initialize() 
		@keys = []
		@children = []
	end

public

	def insert_key(key)
		for i in 0..@keys.length() - 1
			if key < @keys[i] then
				@keys.insert(i, key)
				return
			end
		end
		@keys << key
	end

	def full?(factor)
		return @keys.length >= 2 * factor - 1
	end

	def dispose()
		@parent.children.delete(self) if @parent
		@keys = nil
		@children = nil
		@parent = nil
	end

end

class BTree
	attr_reader :height

	private

	def initialize(factor)
		@root = BTreeNode.new()
		@factor = factor
		@height = 1
	end

	def child_insertion_index(node, key)
		for i in 0..(node.keys.length() - 1)
			if node.keys[i] > key then
				return i
			end
		end
		return node.keys.length() - 1
	end

	def find_insert_position(node, key)
		if !node.full?(@factor) then
			return node
		end

		i = child_insertion_index(node, key)
		if node.children.length <= i then
			return node
		end
		return find_insert_position(node.children[i], key)
	end

	def assert_invariant(node)
		len = node.keys.length()
		raise "#{node} is fucked up" if len < @factor - 1 || len > 2 * @factor - 1 
	end

	def insert_to_node(node, key)
		if !node.full?(@factor) then
			node.insert_key(key)	
			assert_invariant(node)
			return	
		end

		node.insert_key(key)
		median_idx = node.keys.length() / 2

		parent = node.parent 
		if not node.parent then
			parent = BTreeNode.new()
			@height = @height + 1
			@root = parent
		end

		left_node = BTreeNode.new()
		left_node.parent = parent
		left_node.keys = node.keys.take(median_idx)
		parent.children << left_node

		right_node = BTreeNode.new()
		right_node.parent = parent
		right_node.keys = node.keys[(median_idx + 1)..-1]
		parent.children << right_node

		insert_to_node(parent, node.keys[median_idx])

		node.dispose()

		assert_invariant(left_node)
		assert_invariant(right_node)
		assert_invariant(parent)
	end

	def find_key(node, key)
		idx = node.keys.index(key)
		return node, idx if idx

		for child in node.children
			n, i = find_key(child, key)
			return n, i if i
		end

		return nil, nil
	end

	public

	def insert(key)
		node = find_insert_position(@root, key)
		insert_to_node(node, key)
	end

	def get_node(key) #for testing
		return find_key(@root, key)
	end
end
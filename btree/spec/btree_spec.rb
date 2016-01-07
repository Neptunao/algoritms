require "btree"

RSpec.describe BTree do
	before :each do
		@tree = BTree.new(2)
	end

	it "inserts 3 elements" do
		@tree.insert(2)
		@tree.insert(1)
		@tree.insert(20)
		expect(@tree.height).to eq 1
	end

	it "inserts 4 elements" do
		@tree.insert(2)
		@tree.insert(1)
		@tree.insert(20)
		@tree.insert(5)
		expect(@tree.height).to eq 2
	end

	it "gets nothing from empty btree" do
		expect(@tree.get_node(0)).to eq([nil, nil])
	end

	it "gets 3 elements" do
		@tree.insert(2)
		@tree.insert(1)
		@tree.insert(20)

		node_1, idx1 = @tree.get_node(1)
		node_2, idx2 = @tree.get_node(2)
		node_3, idx3 = @tree.get_node(20)
	
		expect(node_1).to eq node_2
		expect(node_2).to eq node_3
		expect(idx1).to eq 0
		expect(idx2).to eq 1
		expect(idx3).to eq 2
	end

	it "gets element from 2nd level" do
		@tree.insert(2)
		@tree.insert(1)
		@tree.insert(20)
		@tree.insert(5)

		root, _ = @tree.get_node(5)

		node, idx = @tree.get_node(20)
		expect(node.children.length).to eq 0
		expect(node.keys.length).to eq 1
		expect(node.parent).to eq root
		expect(root.parent).to be nil
		expect(root.children.length).to eq 2
		expect(root.children.index(node)). to be 1
		expect(idx).to be 0
	end
end
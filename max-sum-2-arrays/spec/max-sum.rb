require "findmaxsum"

RSpec.describe MaxSumFinder do
	it "ok if n = 1" do
		@finder = MaxSumFinder.new([1], [1000])
		expect(@finder.find_max_sum()).to eq([0, 0, 1001])
	end

	it "ok if n = 2" do
		@finder = MaxSumFinder.new([2, 999], [1000, 1])
		expect(@finder.find_max_sum()).to eq([0, 0, 1002])
	end

	it "ok if n = 4" do
		@finder = MaxSumFinder.new([4, -8, 6, 0], [-10, 3, 1, 1])
		expect(@finder.find_max_sum()).to eq([0, 1, 7])
	end

	it "ok if n = 5" do
		@finder = MaxSumFinder.new([1, 56, 87, 3, 1000], [299, 300, 199, 1, -999])
		expect(@finder.find_max_sum()).to eq([1, 1, 356])
	end

	it "ok if n = 5 (2)" do
		@finder = MaxSumFinder.new([1, 1000, 87, 3, 56], [299, 199, 300, 0, 1])
		expect(@finder.find_max_sum()).to eq([1, 2, 1300])
	end

	it "ok if n = 5 (3)" do
		@finder = MaxSumFinder.new([1, 1000, 87, 3, 5600], [299, 199, 300, 6, 1])
		expect(@finder.find_max_sum()).to eq([4, 4, 5601])
	end

	it "ok if n = 6" do
		@finder = MaxSumFinder.new([1, 1000, 87, 3, 5600, 0], [299, 199, 300, 6, 1, 2])
		expect(@finder.find_max_sum()).to eq([4, 5, 5602])
	end

	it "ok if n = 10" do
		@finder = MaxSumFinder.new(
			[655, 193, 220, 32, 694, 985, 991, 945, 332, 590], 
			[270, 478, 265, 889, 961, 381, 290, 914, 982, 807]
		)
		expect(@finder.find_max_sum()).to eq([6, 8, 1973])
	end

	it "ok if n = 10 (2)" do
		@finder = MaxSumFinder.new(
			[431, 240, 493, 925, 151, 297, 183, 179, 681, 480], 
			[962, 318, 545, 152, 141, 520, 219, 750, 620, 717]
		)
		expect(@finder.find_max_sum()).to eq([3, 7, 1675])
	end

	it "ok if n = 10000 #YOLO :----DDDDDD" do
		@arr1 = Array.new(10000)
		@arr2 = Array.new(10000)

		r = Random.new()
		for i in 0..@arr1.length - 1
			@arr1[i] = r.rand(1000)
			@arr2[i] = r.rand(1000)
		end

		# puts("#{@arr1}")
		# puts("#{@arr2}")

		@finder = MaxSumFinder.new(@arr1, @arr2)
		max = max()
		result = @finder.find_max_sum()
		# puts("expected: #{max} el_i=#{@arr1[max[0]]} el_j=#{@arr2[max[1]]}")
		# puts("got: #{result} el_i=#{@arr1[result[0]]} el_j=#{@arr2[result[1]]}")
		expect(result.last).to eq(max.last)
	end

	def max()	#bruteforce
		sum = @arr1[0] + @arr2[0]
		i0 = 0
		j0 = 0

		for j in 0..@arr2.length-1
			for i in 0..j
				if @arr1[i] + @arr2[j] > sum then
					i0 = i
					j0 = j
					sum = @arr1[i] + @arr2[j]
				end
			end
		end

		return i0, j0, sum
	end
end
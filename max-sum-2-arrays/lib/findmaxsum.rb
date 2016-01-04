#invariant: i0 <= j0 

class MaxSumFinder
	def initialize(arr1, arr2)
		@arr1 = arr1
		@arr2 = arr2
	end

	def find_max_sum()
		i0 = j0 = j_max = @arr1.length - 1
		max_sum = @arr1.last() + @arr2.last()
		for i in (@arr2.length - 1).downto(0)
			if @arr2[i] >= @arr2[j_max] then
				j_max = i
			end
			if @arr1[i] + @arr2[j_max] >= max_sum then
				i0 = i
				j0 = j_max
			end
			max_sum = @arr1[i0] + @arr2[j0]
		end

		return i0, j0, max_sum
	end

end
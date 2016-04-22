public class TopSortTester {
	public static void main(String[] args) 
	{ 
		// Check that args has only has only 1 String 
		if (args.length != 1) 
			System.err.println("Invalid number of arguments"); 
		else 
		{
			// Create instance of Huffman and run encode on given file name
			TopSort h = new TopSort();
			h.sort(args[0]);
		}
	}
}

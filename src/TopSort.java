import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TopSort {
	private static int NUM_VERTICES;
	private HashMap<String, TopVert> hm;
	
	public void sort(String fileName)
	{
		try { 
			String line = null;
			FileReader f = new FileReader(fileName); 
			BufferedReader br = new BufferedReader(f);
			hm = new HashMap<>();
			
			// Store items in hashmap
			while((line = br.readLine()) != null)
			{
				String[] in = line.split("\\s+");
				String id = in[0];
				TopVert tv = new TopVert(id);
				
				// For all the prerequisites
				for(int i = 1; i < in.length; i++)
				{
					if(!hm.containsKey(in[i]))
						hm.put(in[i], new TopVert(id));

					hm.get(in[i]).addVertex(tv);
					tv.incrementIndegree();
				}
				hm.put(id, tv);
			}
			
			NUM_VERTICES = hm.size();
			topsort();
			
			br.close();
		} catch(FileNotFoundException e) {
			System.err.println("File not found");
		} catch(IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	private void topsort() throws CycleFoundException
	{
		Queue<TopVert> q = new LinkedList<>();
		int counter = 0;
		
		// then we need to sort
		for(String i : hm.keySet())
		{
			if(hm.get(i).getIndegree() == 0)
				q.add(hm.get(i));
		}
		
		while(!q.isEmpty())
		{
			TopVert tv = q.remove();
			tv.setTopNum(++counter);
			System.out.println(tv.getTopNum()+ ". "+ tv);
			
			for(TopVert adj : tv.getAdjacencyList())
			{
				adj.decrementIndegree();
				if(adj.getIndegree() == 0)
					q.add(adj);
			}
		}
		
		if(counter != NUM_VERTICES)
			throw new CycleFoundException();
	}
}

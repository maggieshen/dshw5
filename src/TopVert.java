import java.util.LinkedList;

public class TopVert {
	private LinkedList<TopVert> adj;
	private String name;
	private int indegree;
	
	public TopVert(String name)
	{
		this.name = name;
		adj = new LinkedList<TopVert>();
		indegree = 0;
	}

	public String returnName()
	{
		return name;
	}
	
	public void addVertex(TopVert v)
	{
		adj.add(v);
	}
	
	public void incrementIndegree()
	{
		indegree++;
	}
	
	public void decrementIndegree()
	{
		indegree--;
	}
	
	public int getIndegree()
	{
		return indegree;
	}
}

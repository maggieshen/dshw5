import java.util.LinkedList;

public class TopVert{
	private LinkedList<TopVert> adj;
	private String name;
	private int indegree;
	private int topNum;
	
	public TopVert(String name)
	{
		this.name = name;
		adj = new LinkedList<TopVert>();
		indegree = 0;
	}

	public LinkedList<TopVert> getAdjacencyList()
	{
		return adj;
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
	
	public void setTopNum(int topNum)
	{
		this.topNum = topNum;
	}
	
	public int getTopNum()
	{
		return topNum;
	}
	
	public String toString()
	{
		return name;
	}
}

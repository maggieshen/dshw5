import java.util.HashMap;

// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Vertex deleteMin( )--> Return and remove smallest item
// Vertex findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap
{
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;      // Number of elements in heap
	private Vertex [] array; // The heap array
	private HashMap<Vertex, Integer> hm = new HashMap<>();

	/**
	 * Construct the binary heap.
	 */
	public BinaryHeap()
	{
		this( DEFAULT_CAPACITY );
	}

	/**
	 * Construct the binary heap.
	 * @param capacity the capacity of the binary heap.
	 */
	public BinaryHeap( int capacity )
	{
		currentSize = 0;
		array = new Vertex[ capacity + 1 ];
	}

	/**
	 * Construct the binary heap given an array of items.
	 */
	public BinaryHeap( Vertex [ ] items )
	{
		currentSize = items.length;
		array = new Vertex[ ( currentSize + 2 ) * 11 / 10 ];

		int i = 1;
		for( Vertex item : items )
		{
			hm.put(item, i);
			array[ i++ ] = item;
		}
		buildHeap( );
	}

	/**
	 * Insert into the priority queue, maintaining heap order.
	 * Duplicates are allowed.
	 * @param x the item to insert.
	 */
	public void insert( Vertex x )
	{
		if( currentSize == array.length - 1 )
			enlargeArray( array.length * 2 + 1 );

		// Percolate up
		int hole = ++currentSize;
		for( array[ 0 ] = x; ((Double)(x.distance))
				.compareTo( array[ hole / 2 ].distance ) < 0; hole /= 2 )
		{
			array[ hole ] = array[ hole / 2 ];
			hm.put(array[hole], hole);
		}
		array[ hole ] = x;
		hm.put(x, hole);
	}


	private void enlargeArray( int newSize )
	{
		Vertex [] old = array;
		array = new Vertex[ newSize ];
		for( int i = 0; i < old.length; i++ )
			array[ i ] = old[ i ];        
	}

	/**
	 * Find the smallest item in the priority queue.
	 * @return the smallest item, or throw an UnderflowException if empty.
	 */
	public Vertex findMin( )
	{
		if( isEmpty( ) )
			throw new UnderflowException( );
		return array[ 1 ];
	}

	/**
	 * Remove the smallest item from the priority queue.
	 * @return the smallest item, or throw an UnderflowException if empty.
	 */
	public Vertex deleteMin( )
	{
		if( isEmpty( ) )
			throw new UnderflowException( );

		Vertex minItem = findMin( );
		array[ 1 ] = array[ currentSize-- ];
		hm.put(array[1], 1);
		percolateDown( 1 );

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary
	 * arrangement of items. Runs in linear time.
	 */
	private void buildHeap( )
	{
		for( int i = currentSize / 2; i > 0; i-- )
			percolateDown( i );
	}

	/**
	 * Test if the priority queue is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return currentSize == 0;
	}

	/**
	 * Make the priority queue logically empty.
	 */
	public void makeEmpty( )
	{
		currentSize = 0;
	}

	public int find(Vertex v)
	{
		return hm.get(v);
	}

	/**
	 * Decreases key (the distance) of item at p for specified amount d
	 * @param p index of item to decrease the distance of 
	 * @param d the amount to reduce the distance
	 */
	public void decreaseKey(int p, double d)
	{
		Vertex x = array[p];
		x.distance -= d;
		
		if( currentSize == array.length - 1 )
			enlargeArray( array.length * 2 + 1 );
		
		int hole = ++currentSize;
		for( array[ 0 ] = x; ((Double)(x.distance)).compareTo
				( array[ hole / 2 ].distance ) < 0; hole /= 2 )
		{	
			array[ hole ] = array[ hole / 2 ];
			hm.put(array[hole], hole);
		}
		array[ hole ] = x;
		hm.put(x, hole);
	}

	/**
	 * Internal method to percolate down in the heap.
	 * @param hole the index at which the percolate begins.
	 */
	private void percolateDown( int hole )
	{
		int child;
		Vertex tmp = array[ hole ];

		for( ; hole * 2 <= currentSize; hole = child )
		{
			child = hole * 2;
			if( child != currentSize &&
					((Double)(array[ child + 1 ].distance))
					.compareTo(array[child].distance) < 0 )
				child++;
			if( ((Double)(array[ child ].distance))
					.compareTo( tmp.distance ) < 0 )
			{
				array[ hole ] = array[ child ];
				hm.put(array[hole], hole);
			}
			else
				break;
		}
		array[ hole ] = tmp;
		hm.put(array[hole], hole);
	}
}

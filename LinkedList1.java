import java.io.*;
import java.util.*;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList1<T extends Comparable<T>> 
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList1()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList1( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int size = 1;
    	Node<T> cur = head;
    	while(cur.next!= null)
    {
        cur = cur.next;
        size++;     
    }
        return size; // YOUR CODE HERE
	}

	public boolean empty()
	{
		return(head == null);
         // YOUR CODE HERE
	}

	public boolean contains( T key )
	{
		if(key == null){
			return false;
		}
		if(search(key) == null)
			return false;
		else
			return true;

		  // YOUR CODE HERE
	}

	public Node<T> search( T key )
	{
		Node<T> cur = head;
		while(cur.next != null){
			if(key.equals(cur.data))
				return cur;
				break;
			}
		return null;  // YOUR CODE HERE
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		if(head == null){
			insertAtFront(data);
			return;
		}
		Node<T> cur = head;
		while(cur.next != null){
			cur = cur.next;
		}
		Node<T> tail= new Node<T>(data,null);
		cur.next = tail;// YOUR CODE HERE
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS 
	public void insertInOrder(T  data)
	{
		// YOUR CODE HERE
        if(data.compareTo(head.data) < 0 || head == null){
            insertAtFront(data);
            return;
        }
        Node<T> cur = head;
        while(cur.next != null && data.compareTo(cur.next.data)<0){
            cur = cur.next;
        }
        Node<T> enter = new Node<T>(data);
        
        
        enter.next = cur.next;
        cur = enter;
        
	}

	public boolean remove(T key)
	{
		 //  REPLACE WITH YOUR CODE 
        if(head == null){
            return false;
        }
        if(head == key){
            return removeAtFront();
        }
        Node<T> cur = head;
        while(cur.next != null && cur.next != key){
            cur = cur.next;
        }
        if(cur.next == null){
            return false;
        }else{
            cur.next = cur.next.next;
            return true;
        }

        
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		 // YOUR CODE HERE
        if(head == null){
            return false;
        }
        if(head.next == null){
            return removeAtFront();
        }
        Node<T> cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = null;
        return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head == null){
            return false;
        }
        head = head.next;
        return true;
        
        // YOUR CODE HERE
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();

		// YOUR CODE HERE
        Node<T> cur = this.head;
        while(cur.next != null){
            cur = cur.next;
            insertInOrder(cur.data);
        Node<T> cur1 = other.head;
        }

		return union;
	}
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();

		// YOUR CODE HERE

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();

		// YOUR CODE HERE

		return diff;
	}
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return  null;  // REPLACE WITH YOUR CODE 

	}

} //END LINKEDLIST CLASS 

// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C
class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable
{
    T data;
    Node<T> next;

    Node()
    {
     this( null, null );
    }

    Node(T data)
    {
        this( data, null );
    }

    Node(T data, Node<T> next)
    {
        this.data = data;
        this.next = next;
    }
    public String toString()
    {
        return ""+ data;
    } 
        
} //EOF
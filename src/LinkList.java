class LinkList {
	private Link first; // ref to first link on list
	private int numLinks; // keeps track of the number of

	// links in thte linked list

	// -------------------------------------------------------------
	public LinkList() // constructor
	{
		first = null; // no links on list yet
		numLinks = 0;
	}

	// -------------------------------------------------------------
	public void insertFirst(int id, double dd) { // make new link
		Link newLink = new Link(id, dd);
		newLink.next = first; // it points to old first link
		first = newLink; // now first points to this
		numLinks++;
	}

	// -------------------------------------------------------------
	// Function is modified to handle empty linked lists
	public Link find(int key) // find link with given key
	{
		// If list is empty return null
		if (first == null)
			return null;

		Link current = first; // start at 'first'
		while (current.iData != key) // while no match,
		{
			if (current.next == null) // if end of list,
				return null; // didn't find it
			else
				// not end of list,
				current = current.next; // go to next link
		}
		return current; // found it
	}


	// -------------------------------------------------------------
	// New function: returns true iff linked list is empty
	public boolean isEmpty() {
		return numLinks == 0;
	}

	// -------------------------------------------------------------
	// New function: returns the size of the linked list
	public int size() {
		return numLinks;
	}

	public int[] toArray() {
		int[] temp = new int[numLinks];

		Link current = first;
		int i = 0;
		while (current != null) {
			temp[i++] = current.iData;
			current = current.next;
		}

		return temp;

	}

} // end class LinkList
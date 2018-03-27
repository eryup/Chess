public class LinkedList {
	Node head;
	public int size;

	LinkedList() {
		head = null;
		size = 0;
	}

	public void insert(ChessPiece newPiece) {
		Node latest = new Node(newPiece);
		latest.next = head;
		head = latest;
		size++;
	}

	public King findKing(boolean isBlack){ 
		Node curr = head;
		while(curr != null){
			if(curr.c.isKing){
				if(curr.c.black == isBlack)
					return (King) curr.c;
			}
				curr = curr.next;
		}
		return null;
	} // returns king of specific color true = black, false = white
	
	public void delete(ChessPiece d){
		Node prev = null;
		Node curr = head;
		while(curr.c != d){
			prev = curr;
			curr = curr.next;
		}
		if (prev == null)
			head = head.next;
		else
			prev.next = curr.next;
	}
	
	public void traverse() {
		Node current = head;
		while (current != null) {
			System.out.print(current.c.id());// print
			current = current.next;
		}
		System.out.println();
	}

	public ChessPiece get(int index) {
		int count = 0;
		Node current = head;
		while (current != null) {
			if (count == index)
				return current.c;
			current = current.next;
			count++;
		}
		return null;
	}

	public ChessPiece find(int row, int col) {
		Node current = head;
		while (current != null) {
			if (current.c.row == row && current.c.col == col)
				return current.c;
			if (current.next == null)
				return null;
			else
				current = current.next;
		}
		return null;
	}
}

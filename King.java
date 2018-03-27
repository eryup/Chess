public class King extends ChessPiece {
	public King(int x, int y, boolean isBlack) {
		super(x, y, isBlack);
		isKing = true; // for checking king validity
	}

	public boolean isAttacking(ChessPiece a) {
		if (Math.abs(row - a.row) == 1 && col == a.col)
			return true;
		if (Math.abs(col - a.col) == 1 && row == a.row)
			return true;
		if (Math.abs(row - a.row) == 1 && Math.abs(col - a.col) == 1)
			return true;
		return false;
	}
	
	public boolean isBlocked(int ncol, int nrow, LinkedList b){
		if(b.find(nrow, ncol) != null){
			if(b.find(nrow, ncol).black == black)
				return true;
		}
		//System.out.println("YYY");
		return false;
		}
	
	public boolean isValidMove(int ncol, int nrow){
		if (Math.abs(row - nrow) == 1 && col == ncol)
			return true;
		if (Math.abs(col - ncol) == 1 && row == nrow)
			return true;
		if (Math.abs(row - nrow) == 1 && Math.abs(col - ncol) == 1)
			return true;
		return false;
	}

	public String id() {
		if (black)
			return "K";
		else
			return "k";
	}
}
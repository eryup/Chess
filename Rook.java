public class Rook extends ChessPiece {
	public Rook(int x, int y, boolean isBlack) {
		super(x, y, isBlack);
	}

	public boolean isAttacking(ChessPiece a) {
		if (a.row == row)
			return true;
		if (a.col == col)
			return true;
		return false;
	}

	public boolean isBlocked(int ncol, int nrow, LinkedList b){
		if (col == ncol){
			if(row < nrow){
				for (int i=row+1; i < nrow; i++){
					if(b.find(i, col) != null)
						return true;
				}
			}
			else{
				for (int i=row-1; i > nrow; i--){
					if(b.find(i, col) != null)
						return true;
				}
			}
		}
		else if (row == nrow){
			if(col < ncol){
				for (int i=col+1; i < ncol; i++){
					if(b.find(row, i) != null)
						return true;
				}
			}
			else{
				for (int i=col-1; i > ncol; i--){
					if(b.find(row, i) != null)
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean isValidMove(int ncol, int nrow){
		if (nrow == row) // row check
			return true;
		if (ncol == col) // column check
			return true;
		// if move fails tests return false
		return false;
	}
	
	public String id() {
		if (black)
			return "R";
		else
			return "r";
	}
}
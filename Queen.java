public class Queen extends ChessPiece {
	public Queen(int x, int y, boolean isBlack) {
		super(x, y, isBlack);
	}

	public boolean isAttacking(ChessPiece a) {
		if (a.row == row) // row check
			return true;
		if (a.col == col) // column check
			return true;
		if (Math.abs(row - a.row) == Math.abs(col - a.col)) // diagonal check
			return true;
		// if no pieces found, return false
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
		else{
			if(col < ncol){
				if(row < nrow){
					for(int i=1; i+col < ncol; i++){
						if(b.find(row+i, col+i) != null)
							return true;
					}
				}
				if(row > nrow){
					for(int i=1; i+col > ncol; i++){
						if(b.find(row-i, col+i) != null)
							return true;
					}
				}
			}
			if(col > ncol){
				if(row < nrow){
					for(int i=1; i+col < ncol; i++){
						if(b.find(row+i, col-i) != null)
							return true;
					}
				}
				if(row > nrow){
					for(int i=1; i+col > ncol; i++){
						if(b.find(row-i, col-i) != null)
							return true;
					}
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
		if (Math.abs(row - nrow) == Math.abs(col - ncol)) // diagonal check
			return true;
		// if move fails tests return false
		return false;
	}
	
	public String id() {
		if (black)
			return "Q";
		else
			return "q";
	}
}
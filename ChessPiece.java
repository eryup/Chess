// abstract super class for chess pieces
abstract public class ChessPiece{
		int row;
		int col;
		boolean black; // stores color of piece, true=black false=white
		boolean isKing; // used for validity check of 1 king of each color
		public ChessPiece(int x, int y,boolean isBlack){
			col=x;
			row=y;
			black=isBlack;
			isKing=false; // will be set to true only for kings
		}
		abstract public boolean isAttacking (ChessPiece a);
		abstract public boolean isBlocked(int ncol, int nrow, LinkedList b);
		abstract public boolean isValidMove(int ncol,int nrow);
		abstract public String id(); // will return identifying letter			
	}
import java.io.*;
import java.util.Scanner;

public class Chessmoves {
	public static int boardSize; // used for checking attacks

	public Chessmoves() {
	}

	public static void main(String[] args) {
		parseInput();
	}

	public boolean isValid(LinkedList n) {
		int bKing = 0; // black king count
		int wKing = 0; // white king count
		for (int i = 0; i < n.size; i++) {
			for (int j = i + 1; j < n.size; j++) {
				if (n.get(i).row == n.get(j).row && n.get(i).col == n.get(j).col) // checks
																					// for
																					// row/col
																					// conflicts,
																					// except
																					// against
																					// itself
					return false;
			}
		} // nested for loops check every piece against the entire linked list
			// for pieces occupying a single space
		for (int i = 0; i < n.size; i++) {
			if (n.get(i).isKing == true) {
				if (n.get(i).black == true)
					bKing++;
				else
					wKing++;
			}
		}
		if (bKing == 1 && wKing == 1) // checks for one king of each color
			return true;
		else
			return false;
	}

	public boolean inCheck(King k, LinkedList d) {
		for (int i = 0; i < d.size; i++)
			if (d.get(i).black != k.black && d.get(i).isValidMove(k.col, k.row)
					&& d.get(i).isBlocked(k.col, k.row, d) == false) {
				return true;
			}
		return false;
	}

	public boolean isAttacked(int col, int row, boolean black, LinkedList d) {
		for (int i = 0; i < d.size; i++) {
			if (d.get(i).isValidMove(col, row) && d.get(i).isBlocked(col, row, d) == false && d.get(i).black != black)
				return true;
		}
		return false;
	}

	public boolean inCheckmate(King k, LinkedList d) {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (k.isValidMove(i, j) && k.isBlocked(i, j, d) == false) {
					if (!isAttacked(i, j, k.black, d))
						return false;
				}
			}
		}

		return true;
	}

	public boolean[] validMoves(int[] mcol, int[] mrow, LinkedList d) {
		boolean[] temp = new boolean[mcol.length / 2];
		for (int i = 0; i < mcol.length; i += 2) {

			if (d.find(mrow[i], mcol[i]).isValidMove(mcol[i + 1], mrow[i + 1])
					&& d.find(mrow[i], mcol[i]).isBlocked(mcol[i + 1], mrow[i + 1], d) == false) {
				if (d.find(mrow[i + 1], mcol[i + 1]) != null) {
					ChessPiece a = d.find(mrow[i], mcol[i]);
					ChessPiece b = d.find(mrow[i + 1], mcol[i + 1]);
					if (a.black == b.black)
						temp[i / 2] = false;
					else {
						int ucol = b.col;
						int urow = b.row;
						d.delete(b);
						a.col = ucol;
						a.row = urow;
						temp[i / 2] = true;
					}
				} else {
					d.find(mrow[i], mcol[i]).col = mcol[i + 1];
					d.find(mrow[i], mcol[i + 1]).row = mrow[i + 1];
					temp[i / 2] = true;
				}
			} else {
				temp[i / 2] = false;
			}
			// move is invalid
		}
		return temp;
	}

	public boolean mutualAttack(ChessPiece a, ChessPiece b) {

		if (a.isAttacking(b) == true && b.isAttacking(a) == true)
			return true;
		else
			return false;
	}

	public static void parseInput() {
		// int lineNumber = 0;
		Scanner in = null;
		PrintWriter writer = null;
		try {
			in = new Scanner(new File("input.txt"));
			writer = new PrintWriter("analysis.txt", "UTF-8");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		// Scanner in = new Scanner(new File("input.txt"));
		// PrintWriter writer = new PrintWriter("analysis.txt", "UTF-8");
		while (in.hasNextLine()) {
			int[] qcol = new int[] {};
			int[] qrow = new int[] {};
			boolean checkMated = false;
			String status = "";
			boolean[] moveVal;
			Chessmoves board = new Chessmoves();
			LinkedList list = new LinkedList();
			// boolean attackFound=false;
			// ChessPiece attacker = null;
			// ChessPiece attacked = null;
			String line = in.nextLine();
			String line2 = in.nextLine(); // proceeds to second line of input /
											// query line
			String[] token = line.split("\\s+");
			/*
			 * for(int i=0; i<token.length; i++){
			 * System.out.println("  "+token[i]); }
			 */

			// System.out.println(line2);
			boardSize = Integer.parseInt(token[0]);
			for (int i = 1; i < token.length - 2; i += 3) {

				int ncol = Integer.parseInt(token[i + 1]);// token[i+1] is the
															// column
				int nrow = Integer.parseInt(token[i + 2]);// token[i+2] is the
															// row
				boolean color = (Character.isUpperCase(token[i].charAt(0)));
				if (token[i].equalsIgnoreCase("q"))
					list.insert(new Queen(ncol, nrow, color));
				// if(token[i].equalsIgnoreCase("b"))
				// list.insert(new Bishop(ncol,nrow,color));
				if (token[i].equalsIgnoreCase("r"))
					list.insert(new Rook(ncol, nrow, color));
				// if(token[i].equalsIgnoreCase("n"))
				// list.insert(new Knight(ncol,nrow,color));
				if (token[i].equalsIgnoreCase("k"))
					list.insert(new King(ncol, nrow, color));
			} // inserts pieces into linked list
				// list.traverse();
			String[] moves = line2.split("\\s+");

			for (int i = 0; i < moves.length; i++) {
				System.out.print("  " + moves[i]);
			}
			System.out.println();
			qcol = new int[moves.length / 2];
			qrow = new int[moves.length / 2];
			for (int i = 0; i < moves.length; i += 2) {
				qcol[i / 2] = Integer.parseInt(moves[i]);
				qrow[i / 2] = Integer.parseInt(moves[i + 1]);
			} // parses moves
			System.out.print("qcol: ");
			for (int i = 0; i < qcol.length; i++) {
				System.out.print(qcol[i]);
			}
			System.out.println();
			System.out.print("qrow: ");
			for (int i = 0; i < qrow.length; i++) {
				System.out.print(qrow[i]);
			}
			System.out.println();
			if (board.inCheck(list.findKing(false), list)){
				if (board.inCheckmate(list.findKing(false), list)){
					status = new String("White checkmated");
					checkMated = true;
				}
				else
					status = new String("White in check");
			}
			if (board.inCheck(list.findKing(true), list) && checkMated != true){
				if (board.inCheckmate(list.findKing(true), list))
					status = new String("Black checkmated");
				else status = new String("Black in check");

			}

			if(board.inCheck(list.findKing(true), list) == false && board.inCheck(list.findKing(false), list) == false)
				status = new String("All kings safe"); // String status gives
														// check status of
														// initial chessboard

			moveVal = board.validMoves(qcol, qrow, list);
			for (int i = 0; i < moveVal.length; i++) {
				if (moveVal[i] && i == moveVal.length - 1)
					writer.println("Valid ");
				else if (moveVal[i])
					writer.print("Valid ");
				else {
					writer.println("Invalid ");
					break;
				}
			}

			writer.println(status);

			/*
			 * else{ if (list.find(qrow,qcol)==null) writer.print("- "); else
			 * writer.print(list.find(qrow,qcol).id() + " "); mainLoop: for(int
			 * i=0; i<list.size;i++){ for(int j=0; j<list.size; j++){ if (i !=
			 * j){ if(list.get(i).isAttacking(list.get(j)) == true){
			 * attackFound=true; attacker = list.get(i); attacked = list.get(j);
			 * break mainLoop; } } } } if(attackFound){
			 * writer.println(attacker.id() + " " + attacker.col + " " +
			 * attacker.row + " " + attacked.id() + " " + attacked.col + " " +
			 * attacked.row + " "); } else writer.println("- "); }
			 */
		}
		in.close();
		writer.close();
	}
}
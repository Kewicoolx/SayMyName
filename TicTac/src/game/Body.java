package game;

import exceptions.*;

public class Body {
	private int[][] cells;
	private int size;
	int[] xWinner;
	int[] yWinner;
	char winner = 'n';
	int diagonalZero_vertHorOne = 404;
	boolean evenTrue = false;

	public Body(int size) {
		if (size <= 2)
			size = 3;
		if(size%2==0)
			evenTrue = true;
		cells = new int[size][size];
		this.size = size;
		xWinner = new int[size];
		yWinner = new int[size];
		show();
	}

	public void mark(int x_axis, int y_axis, int mark) throws WrongInputException, CellOccupiedException {
		// x is -1, O is 1
		if (cells[x_axis][y_axis] == 0)
			if (mark == -1) {
				cells[x_axis][y_axis] = -1;
				xWinner[x_axis] += -1;
				yWinner[y_axis] += -1;

			} else if (mark == 1) {
				cells[x_axis][y_axis] = 1;
				xWinner[x_axis] += 1;
				yWinner[y_axis] += 1;
			} else
				throw new CellOccupiedException();
		show();
	}

	public void show() {
		boolean conditionToPrintWinner = isWinner();
		System.out.print(this);
		System.out.println("is there a winner? " + (conditionToPrintWinner?"yes":"no"));
		if (conditionToPrintWinner)
			System.out.println(winner + "\n");
		else
			System.out.println();
	}

	@Override
	public String toString() {
		String result = "";
		for (int j = size - 1; j >= 0; j--) {
			for (int i = 0; i < size; i++) {

				if (cells[i][j] == -1)
					result += "X" + " ";
				else if (cells[i][j] == 1)
					result += "O" + " ";
				else
					result += "@" + " ";
			}
			result += "\n";
		}
		return result;
	}

	public boolean isWinner() {
		boolean flag = true;
		//odd size diagonal check
		if (size % 2 == 1 && cells[size / 2][size / 2] != 0) {
			// "/" diagonal
			for (int i = 0; i < size - 1;) {
				for (int j = 0; j < size - 1; j++, i++) {
					if (cells[i][j] == cells[i + 1][j + 1] && (cells[i][j] == 1 || cells[i][j] == -1)) {
						flag = flag & true;
						winner = (cells[i][j] == -1 ? 'x' : 'o');
					} else {
						flag = false;
						winner = 'n';
					}
				}
				if (flag) {
//					diagonalZero_vertHorOne = 0;
					return flag;
				}
			}
			flag = true;
			// "\" diagonal check
			for (int i = 0; i < size - 1;) {
				for (int j = size - 1; j > 0; j--, i++) {
					if (cells[i][j] == cells[i + 1][j - 1] && (cells[i][j] == 1 || cells[i][j] == -1)) {
						flag = flag & true;
						winner = (cells[i][j] == -1 ? 'x' : 'o');
					} else {
						winner = 'n';
						flag = false;
					}
				}
				if (flag) {
//					diagonalZero_vertHorOne =0;
					return flag;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (Math.abs(xWinner[i]) == 3) {
//					diagonalZero_vertHorOne =1;
					winner = (cells[i][0] == -1 ? 'x' : 'o');
					return true;
				} else if (Math.abs(yWinner[i]) == 3) {
//					diagonalZero_vertHorOne =1;
					winner = (cells[0][i] == -1 ? 'x' : 'o');
					return true;
				}
			}
		}
		return false;
	}

	public void display(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + ",");
		}
		System.out.println();
	}

	public int[][] getCells() {
		return cells;
	}

	public void setCells(int[][] cells) {
		this.cells = cells;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

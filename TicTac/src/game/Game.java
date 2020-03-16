package game;

import exceptions.CellOccupiedException;
import exceptions.WrongInputException;

public class Game {
	private Body body;
	private int size;
	private int counterForTurn;

	public Game(int size) {
		body = new Body(size);
		this.size = size;
		turn();

	}

	public void mark(int x, int y, String mark) throws WrongInputException, CellOccupiedException {
		x--;
		y--;
		if (mark.length() == 1) {
			if (mark.charAt(0) == 'x' || mark.charAt(0) == 'X')
				body.mark(x, y, -1);
			else if (mark.charAt(0) == 'o' || mark.charAt(0) == 'O')
				body.mark(x, y, 1);
			else
				throw new WrongInputException();
		} else {
			throw new WrongInputException("incorrect input, type x or o");
		}
		turn();
	}

	public void turn() {
		if (counterForTurn % 2 == 1)
			System.out.println("O's player turn");
		else
			System.out.println("X's player turn");
		counterForTurn++;
	}

	public boolean isWinner() {
		return body.isWinner();
	}

	public void show() {
		body.show();
	}

	public static void main(String[] args) throws WrongInputException, CellOccupiedException {
		Game a = new Game(5);

		a.mark(1, 5, "x");
		a.mark(2, 4, "x");
		a.mark(3, 3, "x");
		a.mark(4, 2, "x");
		a.mark(5, 1, "x");
	}

}

package com.aurionpro.test;

import java.util.Scanner;

public class TicTacToe {
	public static final int crossPlayer = 0;
	public static final int zeroPlayer = 1;
	public static final int emptyArray = 2;
	public static final int PLAYING = 0;
	public static final int DRAW = 1;
	public static final int crossWon = 2;
	public static final int zeroWon = 3;
	public static final int ROWS = 3, COLS = 3;
	public static int[][] board = new int[ROWS][COLS];
	public static int currentPlayer;
	public static int currentState;
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		startGame();
		do {
			inputGame();
			gameBoard();
			if (currentState == crossWon) {
				System.out.println("'X' won!");
			} else if (currentState == zeroWon) {
				System.out.println("'O' won!");
			} else if (currentState == DRAW) {
				System.out.println("It's a Draw!!");
			}
			if(currentPlayer ==crossPlayer)
				currentPlayer=zeroPlayer;
			else
				currentPlayer=crossPlayer;
		} while (currentState == PLAYING);
	}

	public static void startGame() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				board[row][col] = emptyArray;
			}
		}
		currentPlayer = crossPlayer;
		currentState = PLAYING;
	}

	public static void inputGame() {
		boolean validInput = false;
		do {
			if (currentPlayer == crossPlayer) {
				System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
			} else {
				System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
			}
			int row = sc.nextInt() - 1;
			int col = sc.nextInt() - 1;
			if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == emptyArray) {
				currentState = stepGameUpdate(currentPlayer, row, col);
				validInput = true;
			} else {
				System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
			}
		} while (!validInput);
	}

	public static int stepGameUpdate(int player, int selectedRow, int selectedCol) {
		board[selectedRow][selectedCol] = player;
		if (board[selectedRow][0] == player && board[selectedRow][1] == player && board[selectedRow][2] == player
				|| board[0][selectedCol] == player && board[1][selectedCol] == player && board[2][selectedCol] == player
				|| selectedRow == selectedCol && board[0][0] == player && board[1][1] == player && board[2][2] == player
				|| selectedRow + selectedCol == 2 & board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			if (player ==crossPlayer)
				return crossWon;
			else
				return zeroWon;

		} else {
			for (int row = 0; row < ROWS; ++row) {
				for (int col = 0; col < COLS; ++col) {
					if (board[row][col] == emptyArray) {
						return PLAYING;
					}
				}
			}
			return DRAW;
		}
	}

	public static void gameBoard() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				paintCell(board[row][col]);
				if (col != COLS - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----------");
			}
		}
		System.out.println();
	}

	public static void paintCell(int content) {
		switch (content) {
		case crossPlayer:
			System.out.print(" X ");
			break;
		case zeroPlayer:
			System.out.print(" O ");
			break;
		case emptyArray:
			System.out.print("   ");
			break;
		}
	}
}

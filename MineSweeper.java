import java.util.Arrays;
import java.util.Scanner;

public class MineSweeper {

	int row;
	int column;
	char[][] field1;
	char[][] field2;
	char[][] field3;

	MineSweeper(int row, int column) {
		this.row = row;
		this.column = column;
		field1 = new char[row][column];
		field2 = new char[row][column];
		field3 = new char[row][column];
		int numOfMine = row * column / 4, r, c;
		for (int i = 0; i < numOfMine; i++) {
			while (true) {
				r = (int)(Math.random() * (row));
				c = (int)(Math.random() * (column));
				if (!hasMine(r, c)) {
					field1[r][c] = '*';
					field2[r][c] = '*';
					break;
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (field1[i][j] != '*') { 
					field2[i][j] = '-';
				}
				field3[i][j] = '-';
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (field1[i][j] != '*') { 
					field1[i][j] = findNum(i, j);
				}
			}
		}
	}

	private char findNum(int i, int j) {
		int count = 0;
		if (hasMine(i - 1 , j - 1)) count++;
		if (hasMine(i - 1 , j)) count++;
		if (hasMine(i - 1 , j + 1)) count++;
		if (hasMine(i , j + 1)) count++;
		if (hasMine(i , j - 1)) count++;
		if (hasMine(i + 1 , j - 1)) count++;
		if (hasMine(i + 1 , j)) count++;
		if (hasMine(i + 1 , j + 1)) count++;
		return (char)(count + 48);
	}

	private boolean hasMine(int r, int c) {
		if (r == -1 || c == -1 || r == row || c == column) return false;
		if (field1[r][c] == '*') return true;
		return false;
	}

	void run() {
		System.out.println("===========================\nMayınların Konumu");
		printField(field2);
		System.out.println("===========================\nMayın Tarlası Oyuna Hoşgeldiniz !");
		printField(field3);
		Scanner input = new Scanner(System.in);
		int r, c;
		boolean isEnd = false;
		while(!isEnd ) {
			System.out.print("Satır Giriniz : ");
			r = input.nextInt();
			System.out.print("Sütun Giriniz : ");
			c = input.nextInt();
			if (r < 0 || r >= row || c < 0 || c >= column) {
				System.out.println("Girdiğiniz sayılar hatalı, tekrar deneyiniz !");
				continue;
			}
			System.out.println("===========================");
			if (hasMine(r, c)) {
				System.out.println("Game Over!!\n===========================");
				break;
			}
			field2[r][c] = field3[r][c] = field1[r][c];
			if (isEnd()) {
				isEnd = true;
				System.out.println("Oyunu Kazandınız !");
			}
			printField(field3);
		}
	}

	private boolean isEnd() {
		for (char[] i : field2) {
			for (char j : i) {
				if (j == '-') return false;
			}
		}
		return true;
	}

	private void printField(char[][] field) {
		for (char[] i : field) {
			for (char j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

}

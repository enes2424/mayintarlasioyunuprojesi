import java.util.Scanner;

public class PatikaProje {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Mayın tarlasının satır sayısını giriniz : ");
		int row = input.nextInt();
		System.out.print("Mayın tarlasının sütun sayısını giriniz : ");
		int column = input.nextInt();
		MineSweeper mine = new MineSweeper(row, column);
		mine.run();
	}

}

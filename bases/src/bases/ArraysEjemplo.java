package bases;

import java.util.Arrays;

public class ArraysEjemplo {
	public static void main(String[] args) {
		var tamano = 3;
		var arr = new int[tamano];
		
		arr[0] = 5;
		arr[1] = 6;
		arr[2] = 7;
//		arr[3] = 8;
		
		System.out.println(Arrays.toString(arr));
		
		System.out.println(arr.length);
		
		System.out.println(arr[2]);
	}
}

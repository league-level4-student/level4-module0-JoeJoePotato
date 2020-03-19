//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {
	static int x;
	static int y;

	public static int[] findWrongWayCow(final char[][] field) {
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
		for (int i = 2; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == 'c') {
					if (searchforo(field, i, j) == true)
						;
				}
			}
		}
		return null;
	}

	public static boolean searchforo(char[][] f, int i, int j) {
		if (i - 1 > -1) {
			if (f[i - 1][j] == 'o') {
				x = i - 1;
				y = j;
				return true;
			}else if (f[i - 1][j] == '.') {
					if (i - 2 > -1) {
						return searchforo(f, i - 2, j);
					} else {
						return searchforo(f, f.length-1,j-1);
					}
			}else {
				return false;	
			else {
				return searchforo(f, f.length-1, j-1)
			
			}

			}	
		
	
	}
}

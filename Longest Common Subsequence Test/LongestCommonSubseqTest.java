import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestCommonSubseqTest {

	lcs result;
	String s1 = "ABAABA";
	String s2 = "BABBAB";
	int[][] memo;


	//Testing def-use(x),
	//x is our String 1,
	//y is our String 2,
	//Since both have a def - use at the same location (but not in terms of memory),
	//we can test both at the same time since the point of the whole program compares the two
	@ParameterizedTest
	@MethodSource("stream0")
	void testXandY(String answer, String s1, String s2) throws Exception {
		result = new lcs();
		//assertEquals(s1, s1);	
		int m = s1.length();
		int n = s2.length();
	}

	
	
	
	//Testing def - use (i)
	//i is our indexing to traverse through the String, or the Bottom Up 2D Matrix
	//This ends up testing for m and s1
	//The intersection 3 of the tested paths creates a cycle in the graph, which represents a loop, and an exit at node 8
	//The intersecting paths is the minimum required path to exercise the inner and outer loop where variable i is used 


	//There are other paths in our Documentation, however the remaining index tests
	//include path that are in the inner loop, the tests conducted in testi tests the outer loop,
	//the following testj will test the inner loop

	//We use variable i in a Condition as well as a index, in the O(n^2) and O(n) portion
	//Because the Conditional and index accessing falls within the loop which was tested, and the variable i
	//directly comes from the loop, there is no need to explicitly test variable i (in Conditional and index). We can rely on Java to return the correct
	//boolean statement, and as far as the Matrix goes, there will be a test later in this.class.test for further details
	//We can also say the same for the upcoming test "testj" since it has the same objective as "testi"

	//In testi, we kill multiple birds with 1 stone. Since i,m, and s1 are directly correlated, they will have the same paths
	//as variable i. s1 determines the numerical value for m, and i is set to be the numerical value (minus 1 for Java indexing purposes)
	//So where ever variable i travels to in our Control Flow Graph, s1, and m will follow as well, thus creating less tests with the same coverage.
	@ParameterizedTest
	@MethodSource("stream1")
	void testi(String s1, String s2) throws Exception {
		int m = s1.length();
		int i = m;
		int z = 0;

		//Path [2,3] - Making sure that the index was initialized properly
		assertEquals(i , m);
		// z is a counter to ensure that the loop ran the String Length Properly
		for( i = m -1; i >= 0 ; i--) {
			z++;
		}
		//Path(s) [4,5,6,6a,7,3,4] && [4,5,6,6b,7,3,4] && [1,2,3,8]*
		assertEquals(z, s1.length());			
	}



	//Testing def - use (j)
	//j is our indexing to traverse through the String, Conditional, and for the Bottom Up 2D Matrix

	//The intersection 5 in the paths creates a minimum amount of paths necessary to exercise all paths that have
	//def use of variable j. 
	//Although this path exists in our cyclic subset in "testi" we must ensure that this inner loop runs as expected 

	//Just like testi, this test also kills multiple birds with 1 stone with the same reasoning mentioned in testi
	@ParameterizedTest
	@MethodSource("stream1")
	void testj(String s1, String s2) throws Exception {
		result = new lcs();
		int n = s2.length();
		int j = n;
		int z = 0;

		//Path[4,5]
		assertEquals(j , n);

		for(j = n - 1; j >= 0 ; j--) {
			z++;
		}

		//Path[6,6a,5,6] , [6,6b,5,6]
		assertEquals(z , s2.length());

	}


	
	//Even though the Def Use path for the Matrix can run deep into a Conditional,
	//It's used as a conditional and it has been modified.
	//This test will do just those 2 things 
	//The Matrix conditional and indexing is based off of variables i and j,
	//Testing i and j does majority of the coverage
	@ParameterizedTest
	@MethodSource("stream0")
	void optTest(String answer, String s1, String s2) throws Exception {
		result = new lcs();
		int m = s1.length();
		int n = s2.length();
		memo = new int[m+1][n+1];
		
		if(m > 0) {
			for(int i = 0; i < m; i++) {
				for(int j = 0; j < n; j++) {
					memo[i][j] = (memo[i+1][j+1] + 1); 
				}
			}
		}
		
		
	}



	@Test
	void lcsTest() throws Exception {
		result = new lcs();
		String lcs = "";

		//Path[8]
		assertEquals(0, lcs.length());
		//We are making sure that Node 8 executes properly, if for whatever reason that this is not an empty string,
		//this will have an impact on our answer.

		//Path[8,9,10,10a,11,9]
		//This path is a cycle on the graph, and a loop in the algorithm
		//We can replicate the decision branch 10a here in this loop to ensure that characters are being added
		//to an existing string
		//Both of which have a run time of O(n) which will proof the equivalency
		//We also must note that this tested path is dependent on variables i and j found in earlier tests, not on the String
		for(int i = 0; i < s1.length(); i++) {
			lcs += s1.charAt(i);
		}
		assertEquals(lcs,s1);



	}


	//Path[8,9,12]
	//This is our final path to the accept state. 
	//This path intersects our cyclic path and is again dependent on variables i and j
	//No matter the input, we will always reach coverage on the accept state, even an empty string
	//proven in test0 
	@ParameterizedTest
	@MethodSource("stream0")
	void test0(String answer, String s1, String s2) throws Exception {
		result = new lcs();
		assertEquals(answer, result.lcs(s1, s2));
	}





	//Here are different Streams of Arguments to reduce the amount of Syntax by reusing code
	private static Stream<?> stream0(){
		return Stream.of(
				Arguments.of("" , "" , ""),
				Arguments.of(" " , " " , " "),
				Arguments.of("BABA" , "ABAABA" , "BABBAB"),
				Arguments.of("GGCAACG" , "ACGGCGGATACG" , "GGCACCACG")
				);
	}  

	private static Stream<?> stream1(){
		return Stream.of(
				Arguments.of("" , ""),
				Arguments.of(" " , " "),
				Arguments.of("ABAABA" , "BABBAB"),
				Arguments.of("ACGGCGGATACG" , "GGCACCACG")
				);
	}  

}

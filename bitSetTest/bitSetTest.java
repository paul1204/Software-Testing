import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

	  //V.2
class bitSetTest {

	BitSet dummy;
	BitSet dummy2;
	
	//@BeforeEach will handle the first 2 Characteristics being True unless testing False
	@BeforeEach
	private void testConstructor() {
		dummy = new BitSet();
		dummy.set(0);
		dummy.set(1);
		dummy.set(2);
		dummy.set(3);
	}
	
	
	
	@Test
	//Base Choice Coverage
	//T,T,T,T
	void test0() {
		assertTrue(dummy.get(1));
		dummy.flip(1);
		assertFalse(dummy.get(1));

	}

	@Test
	//T,T,T,T
	//See Testing Documents ***
	 void subTest0() {
		dummy.set(4);
		
		dummy.get(4);
		assertTrue(dummy.get(4));		
		
		dummy.flip(4);
		assertFalse(dummy.get(4));
		
		dummy.get(5);
		assertFalse(dummy.get(5));
	
		dummy.flip(5);
		assertTrue(dummy.get(5));
		
	}
	
	
	@Test
	//T,T,T,F
	void test1() {
		assertTrue(dummy.get(1));
		assertThrows(IndexOutOfBoundsException.class, () ->{
			dummy.flip(-5);	
		});
	}
	
	@Test
	//T,T,F,T
	void test2() {
		assertThrows(IndexOutOfBoundsException.class, () ->{
			dummy.get(-5);	
		});
		dummy.flip(1);
	}
	
	
	@Test
	//T,F,T,T
	void test3() {
		assertThrows(IndexOutOfBoundsException.class, () ->{
			dummy.set(-1);	
		});
		assertTrue(dummy.get(1));
		dummy.flip(1);
		assertFalse(dummy.get(1));
	}
	
	
	//Unfesiable Test, There will be Sequential Exceptions
	@Test
	//F,T,T,T
	void test4() {
		dummy.clear();
		assertThrows(NegativeArraySizeException.class, () ->{
			dummy2 = new BitSet(-5);
		});
		
		assertThrows(NullPointerException.class, () ->{
			dummy2.set(2);
		});
		
		assertThrows(AssertionError.class, () ->{
			assertTrue(dummy.get(2));
		});
		
		assertThrows(NullPointerException.class, () ->{
			dummy2.flip(2);
		});
	}
	
	
	@Test
	//F,F,F,F
	void test5() {
		dummy.clear();
	
		assertThrows(NegativeArraySizeException.class,() ->{
			dummy = new BitSet(-5);
		});
		assertThrows(IndexOutOfBoundsException.class, () ->{
			dummy.set(-1);	
		});
		
		assertThrows(IndexOutOfBoundsException.class, () ->{
			dummy.get(-5);	
		});
		
		assertThrows(IndexOutOfBoundsException.class, () ->{
			dummy.flip(-2);	
		});
	}
}
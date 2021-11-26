package test;

import mathTree.MathTree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests for the MathTree.java class in the mathTree package.

class MathTreeTest
{
   public static MathTree calcTree = new MathTree();

   @Test
   public void testSimpleExpr()
   {
      calcTree.init("4 + 4");
      assertEquals(8, calcTree.solve());
      
      calcTree.init("5 - 6");
      assertEquals(-1, calcTree.solve());
      
      calcTree.init("8 * 9");
      assertEquals(72, calcTree.solve());
      
      calcTree.init("5 / 2");
      assertEquals(2.5, calcTree.solve());
      
      calcTree.init("5 ^ 2");
      assertEquals(25.0, calcTree.solve());
      
      assertEquals(false, calcTree.init("5 ^ *"));
      
      assertEquals(false, calcTree.init("5 7 2"));
   }
   
   @Test
   public void testComplexExpr()
   {
      calcTree.init("4 + 4 - 6 * 2");
      assertEquals(-4, calcTree.solve());
      
      calcTree.init("4 - 6 * 2^3");
      assertEquals(-44.0, calcTree.solve());
      
      calcTree.init("4*4 - 6/2");
      assertEquals(13.0, calcTree.solve());
      
      calcTree.init("8^2 - 6 * 4");
      assertEquals(40.0, calcTree.solve());
      
      calcTree.init("7*8*5- 12");
      assertEquals(268, calcTree.solve());
      
      calcTree.init("12+4*7-14");
      assertEquals(26, calcTree.solve());
      
      calcTree.init("7-4+5/2");
      assertEquals(5.5, calcTree.solve());
   }
   
   @Test
   public void testDifficultExpr()
   {
      calcTree.init("5(6)");
      assertEquals(30, calcTree.solve());
      
      calcTree.init("-5(-6)");
      assertEquals(30, calcTree.solve());
      
      calcTree.init("7 - -7");
      assertEquals(14, calcTree.solve());
      
      calcTree.init("(6 * 8) + 9");
      assertEquals(57, calcTree.solve());
      
      calcTree.init("(6 + 8) 9");
      assertEquals(126, calcTree.solve());
      
      assertEquals(false, calcTree.init(")5+6"));
      
      assertEquals(false, calcTree.init("--6 + 7"));
      
      assertEquals(false, calcTree.init("(6 + 7"));
      
      assertEquals(false, calcTree.init("6 ( + 7"));
      
      assertEquals(false, calcTree.init("- 6 + 7"));
   }
   
   @Test
   public void testLongExpr()
   {
      calcTree.init("(5+5*2) 8*0.5 + 6^2(4)");
      assertEquals(1679676.0, calcTree.solve());
      
      calcTree.init("2*3^2+(9+5-14-2)4");
      assertEquals(10.0, calcTree.solve());
      
      calcTree.init("45/5(7.5)/2.5+2^3");
      assertEquals(35.0, calcTree.solve());
      
      calcTree.init("7^(8-2-4)+4--5*3");
      assertEquals(68.0, calcTree.solve());
   }


   @Test
   public void testInit(){
      boolean t1=true;
      boolean t2=false;
      //Test path : [1,2,3,4,8]
      boolean out1=calcTree.init("4 + 4");
      assertEquals(out1, t1);

      //Test path : [1,2,3,4,5,6,7]
      boolean out2=calcTree.init("4 + ");
      assertEquals(out2, t2);

      //Test path : [1,2,3,6,7]
      boolean out3=calcTree.init("");
      assertEquals(out3, t2);

   }


   @Test
   public void testneg2_1()
   {
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList("3","-","-","4"));
      calcTree.remove_neg2(lst);
      assertEquals(lst.pollFirst(), "3");
      assertEquals(lst.pollFirst(), "-");
      assertEquals(lst.pollFirst(), "-4");
   }
   @Test
   public void testneg2_2()
   {
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList("3","-","+","4"));
      calcTree.remove_neg2(lst);
      assertEquals(lst.pollFirst(), "3");
      assertEquals(lst.pollFirst(), "-");
      assertEquals(lst.pollFirst(), "+");
      assertEquals(lst.pollFirst(), "4");
   }

   @Test
   public void test_cleanStrList_sample()
   {
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList("(","3","-","2",")","3","+","4"));
      calcTree.cleanStrList(lst);
      assertEquals("(",lst.pollFirst());
      assertEquals("3",lst.pollFirst());
      assertEquals("-",lst.pollFirst());
      assertEquals("2",lst.pollFirst());
      assertEquals(")",lst.pollFirst());
      assertEquals("*",lst.pollFirst());
      assertEquals("3",lst.pollFirst());
      assertEquals("+",lst.pollFirst());
      assertEquals("4",lst.pollFirst());
   }

   @Test
   public void test_cleanStrList_sample2()
   {
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList("2","(","-","3","4",")","4"));
      calcTree.cleanStrList(lst);
      assertEquals("2",lst.pollFirst());
      assertEquals("*",lst.pollFirst());
      assertEquals("(",lst.pollFirst());
      assertEquals("-",lst.pollFirst());
      assertEquals("3",lst.pollFirst());
      assertEquals("4",lst.pollFirst());
      assertEquals(")",lst.pollFirst());
      assertEquals("*",lst.pollFirst());
      assertEquals("4",lst.pollFirst());
   }

   @Test
   public void test_cleanStrList_sample3()
   {
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList("2"));
      calcTree.cleanStrList(lst);
      assertEquals("2",lst.pollFirst());
   }

   @Test
   public void test_cleanStrList_sample4(){
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList("2","(","3",")"));
      calcTree.cleanStrList(lst);
      assertEquals("2", lst.pollFirst());
      assertEquals("*", lst.pollFirst());
      assertEquals("(", lst.pollFirst());
      assertEquals("3", lst.pollFirst());
      assertEquals(")", lst.pollFirst());
   }

   @Test
   public void test_cleanStrList_sample5()
   {
      LinkedList<String> lst = new LinkedList<String>(Arrays.asList());
   }
}
package test;

import mathTree.StringScanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests for the StringScanner.java class in the mathTree package.

class StringScannerTest
{
   public static StringScanner scanner;
   
   LinkedList<String> output;
   
   String sentence1 = "    This   is a    sentence  ";
   String sentence2 = "Welcome, home.";
   String sentence3 = "Just`another`sentence ";
   String sentence4 = "(if   the   world`does`end at all?)";









   @BeforeAll
   public static void setUp() 
   {  
      scanner = new StringScanner();
      
      //Set up scanner
      scanner.skipWhitespace();
      char[] specialChars = {'.', ',', '?', ';', '(', ')','+','-'};
      scanner.addSpecialChar(specialChars);
      scanner.addDelimiter('`');
   }

   @Test
   public void testSkipWhitespace()
   {
      output = scanner.scan(sentence1);
      
      assertEquals("This", output.pollFirst());
      assertEquals("is", output.pollFirst());
      assertEquals("a", output.pollFirst());
      assertEquals("sentence", output.pollFirst());
   }
   
   @Test
   public void testSpecialChar()
   {
      output = scanner.scan(sentence2);
      
      assertEquals("Welcome", output.pollFirst());
      assertEquals(",", output.pollFirst());
      assertEquals("home", output.pollFirst());
      assertEquals(".", output.pollFirst());
   }
   
   @Test
   public void testDelimeter()
   {
      output = scanner.scan(sentence3);
      
      assertEquals("Just", output.pollFirst());
      assertEquals("another", output.pollFirst());
      assertEquals("sentence", output.pollFirst());
   }

   @Test
   public void testAll()
   {
      output = scanner.scan(sentence4);

      assertEquals("(", output.pollFirst());
      assertEquals("if", output.pollFirst());
      assertEquals("the", output.pollFirst());
      assertEquals("world", output.pollFirst());
      assertEquals("does", output.pollFirst());
      assertEquals("end", output.pollFirst());
      assertEquals("at", output.pollFirst());
      assertEquals("all", output.pollFirst());
      assertEquals("?", output.pollFirst());
      assertEquals(")", output.pollFirst());
   }

   @Test
   public void test5()
   {
      String sentence5 = " .a";
      output = scanner.scan(sentence5);
      assertEquals(".", output.pollFirst());
      assertEquals("a", output.pollFirst());
   }

   @Test
   public void test6()
   {
      String sentence6 = "a.. ..";
      output = scanner.scan(sentence6);
      assertEquals("a", output.pollFirst());
      assertEquals(".", output.pollFirst());
      assertEquals(".", output.pollFirst());
      assertEquals(".", output.pollFirst());
      assertEquals(".", output.pollFirst());

   }

   @Test
   public void test7()
   {
      String sentence7 = "";
      output = scanner.scan(sentence7);

   }

   @Test
   public void test8()
   {
      String sentence8 = "aa";
      output = scanner.scan(sentence8);
      assertEquals("aa", output.pollFirst());
   }

   @Test
   public void test9()
   {
      String sentence9 = " ";
      output = scanner.scan(sentence9);
   }

   @Test
   public void test10()
   {
      String sentence10 = " a";
      output = scanner.scan(sentence10);
      assertEquals("a", output.pollFirst());
   }

   @Test
   public void test11()
   {
      String sentence11 = "a ";
      output = scanner.scan(sentence11);
      assertEquals("a", output.pollFirst());
   }

   @Test
   public void test12()
   {
      String sentence12 = ".";
      output = scanner.scan(sentence12);
      assertEquals(".", output.pollFirst());
   }

   @Test
   public void test_test()
   {
      String sentence12 = "2+-23";
      output = scanner.scan(sentence12);
      System.out.println(output);
   }

}

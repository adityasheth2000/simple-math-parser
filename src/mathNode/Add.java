package mathNode;

public class Add extends Operator
{
   public Add() { precedence = 3; }
   
   public Number calculate()
   {
      Number leftNum = leftNode.calculate();
      Number rightNum = rightNode.calculate();
      
      if(leftNum instanceof Integer && rightNum instanceof Integer)
         return leftNum.intValue() + rightNum.intValue();
      
      return leftNum.doubleValue() + rightNum.doubleValue();
   }
   
   public String toString()
   {
      String str = leftNode.toString() + " + " + rightNode.toString();
      
      if(isParens())
         return '\"' + str + '\"';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Add clone = (Add) super.clone();
      clone.leftNode = (Expression) this.leftNode.clone();
      clone.rightNode = (Expression) this.rightNode.clone();
      return clone;
   }
}

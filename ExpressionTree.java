import java.lang.Math.*;

class ExpressionTree 
{
    private String value;
    private ExpressionTree leftChild, rightChild, parent;
    
    ExpressionTree() 
    {
        value = null; 
        leftChild = rightChild = parent = null;
    }
    
    // Constructor
    /* Arguments: String s: Value to be stored in the node
                  ExpressionTree l, r, p: the left child, right child, and parent of the node to created      
       Returns: the newly created ExpressionTree               
    */
    ExpressionTree(String s, ExpressionTree l, ExpressionTree r, ExpressionTree p) 
    {
        value = s; 
        leftChild = l; 
        rightChild = r;
        parent = p;
    }
    
    /* Basic access methods */
    String getValue() 
    { 
        return value; 
    }

    ExpressionTree getLeftChild() 
    { 
        return leftChild; 
    }

    ExpressionTree getRightChild() 
    { 
        return rightChild; 
    }

    ExpressionTree getParent() 
    { 
        return parent; 
    }


    /* Basic setting methods */ 
    void setValue(String o) 
    { 
        value = o; 
    }
    
    // sets the left child of this node to n
    void setLeftChild(ExpressionTree n) 
    { 
        leftChild = n; 
        n.parent = this; 
    }
    
    // sets the right child of this node to n
    void setRightChild(ExpressionTree n) 
    { 
        rightChild = n; 
        n.parent=this; 
    }
    

    // Returns the root of the tree describing the expression s
    // Watch out: it makes no validity checks whatsoever!
    ExpressionTree(String s) 
    {
        // check if s contains parentheses. If it doesn't, then it's a leaf
        if (s.indexOf("(")==-1)
        {
            setValue(s);
        }
        else 
        {  
            // it's not a leaf

            /* break the string into three parts: the operator, the left operand,
               and the right operand. ***/
            setValue( s.substring( 0 , s.indexOf( "(" ) ) );
            // delimit the left operand 2008
            int left = s.indexOf("(")+1;
            int i = left;
            int parCount = 0;
            // find the comma separating the two operands
            while (parCount>=0 && !(s.charAt(i)==',' && parCount==0)) 
            {
                if ( s.charAt(i) == '(' ) parCount++;
                if ( s.charAt(i) == ')' ) parCount--;
                i++;
            }
            int mid=i;
            if (parCount<0) mid--;

            // recursively build the left subtree
            setLeftChild(new ExpressionTree(s.substring(left,mid)));
    
            if (parCount==0) 
            {
                // it is a binary operator
                // find the end of the second operand.F13
                while ( ! (s.charAt(i) == ')' && parCount == 0 ) )  
                {
                    if ( s.charAt(i) == '(' ) parCount++;
                    if ( s.charAt(i) == ')' ) parCount--;
                    i++;
                }
                int right=i;
                setRightChild( new ExpressionTree( s.substring( mid + 1, right)));
            }
        }
    }


    // Returns a copy of the subtree rooted at this node... 2014
    ExpressionTree deepCopy() 
    {
        ExpressionTree n = new ExpressionTree();
        n.setValue( getValue() );
        if ( getLeftChild()!=null ) 
        {
            n.setLeftChild( getLeftChild().deepCopy() );
        }
        if ( getRightChild()!=null ) 
        {
            n.setRightChild( getRightChild().deepCopy() );
        }
        return n;
    }
    
    // Returns a String describing the subtree rooted at a certain node.
    public String toString() 
    {
        String ret = value;
        if ( getLeftChild() == null ) 
        {
            return ret;
        }
        else 
        {
            ret = ret + "(" + getLeftChild().toString();
        }
        if ( getRightChild() == null ) 
        {
            return ret + ")";
        }
        else 
        {
            ret = ret + "," + getRightChild().toString();
        }
        ret = ret + ")";
        return ret;
    } 


    // Returns the value of the expression rooted at a given node
    // when x has a certain value
    double evaluate(double x) 
    {
     double leftOperand = 0;
     double rightOperand = 0;
     
     // store value of left child if it has one
     if (this.getLeftChild() != null)
     {
         leftOperand = this.getLeftChild().evaluate(x);
     }
     // store value of the right child if it has one
     if (this.getRightChild() != null)
     {
         rightOperand = this.getRightChild().evaluate(x);
     }
     // return value the children of a node
     if (this.getLeftChild() == null && this.getRightChild() == null)
     {
         if (this.getValue().equals("x"))
         {
             return x;
         }
         else
         {
             return Double.parseDouble(this.getValue());
         }
     }
     
     // calculate the expression
     if (this.getValue().equals("add"))
     {
         // calculate the addition of the operands
         return leftOperand + rightOperand;
     }
     else if (this.getValue().equals("mult"))
     {
         // calculate the multiplication of the operands
         return leftOperand * rightOperand;
     }
     else if (this.getValue().equals("minus"))
     {
         // calculate the subtraction of the operands
         return leftOperand - rightOperand;
     }
     else if (this.getValue().equals("sin"))
     {
         // calculate the sin of the operands
         return Math.sin(leftOperand + rightOperand);
     }
     else if (this.getValue().equals("cos"))
     {
         // calculate the cosine of the operands
         return Math.cos(leftOperand + rightOperand);
     }
     else
     {
         // calculate the exponential of the operands
         return Math.exp(leftOperand);
     }
    }                                                 

    /* returns the root of a new expression tree representing the derivative of the
       original expression */
    ExpressionTree differentiate() 
    {
     ExpressionTree differentialTree = new ExpressionTree();
     
     if (this.getValue().equals("x"))
     {
         differentialTree.setValue("1");
     }
     else if (this.getValue().equals("add"))
     {
         differentialTree.setValue("add");
         differentialTree.setLeftChild(this.getLeftChild().deepCopy().differentiate());
         differentialTree.setRightChild(this.getRightChild().deepCopy().differentiate());
         
     }
     else if (this.getValue().equals("mult"))
     {
         differentialTree.setValue("add");
         ExpressionTree leftTree = new ExpressionTree();
         ExpressionTree rightTree = new ExpressionTree();
         differentialTree.setLeftChild(leftTree);
         differentialTree.setRightChild(rightTree);
         leftTree.setValue("mult");
         rightTree.setValue("mult");
         leftTree.setLeftChild(this.getLeftChild().deepCopy().differentiate());
         leftTree.setRightChild(this.getRightChild().deepCopy());
         rightTree.setLeftChild(this.getLeftChild().deepCopy());
         rightTree.setRightChild(this.getRightChild().deepCopy().differentiate());
     }
     else if (this.getValue().equals("minus"))
     {
         differentialTree.setValue("minus");
         differentialTree.setLeftChild(this.getLeftChild().deepCopy().differentiate());
         differentialTree.setRightChild(this.getRightChild().deepCopy().differentiate());
     }
     else if (this.getValue().equals("sin"))
     {
         differentialTree.setValue("mult");
         ExpressionTree leftTree = new ExpressionTree();
         differentialTree.setLeftChild(leftTree);
         leftTree.setValue("cos");
         leftTree.setLeftChild(this.getLeftChild().deepCopy());
         differentialTree.setRightChild(this.getLeftChild().deepCopy().differentiate());
     }
     else if (this.getValue().equals("cos"))
     {
         differentialTree.setValue("minus");
         ExpressionTree leftTree = new ExpressionTree();
         ExpressionTree rightTree = new ExpressionTree();
         differentialTree.setLeftChild(leftTree);
         differentialTree.setRightChild(rightTree);
         leftTree.setValue("0");
         rightTree.setValue("mult");
         ExpressionTree subLeftTree = new ExpressionTree();
         rightTree.setLeftChild(subLeftTree);
         subLeftTree.setValue("sin");
         subLeftTree.setLeftChild(this.getLeftChild().deepCopy());
         rightTree.setRightChild(this.getLeftChild().deepCopy().differentiate());
     }
     else if (this.getValue().equals("exp"))
     {
         differentialTree.setValue("mult");
         ExpressionTree leftTree = new ExpressionTree();
         differentialTree.setLeftChild(leftTree);
         leftTree.setValue("exp");
         leftTree.setLeftChild(this.getLeftChild().deepCopy());
         differentialTree.setRightChild(this.getLeftChild().deepCopy().differentiate());
     }
     else
     {
         differentialTree.setValue("0");
     }
     
     return differentialTree;
    }
        
    
    public static void main(String args[]) 
    {
        ExpressionTree e = new ExpressionTree("mult(exp(x),cos(x))");
        //System.out.println(e);
        System.out.println(e.toString());
        System.out.println(e.evaluate(1));
        System.out.println(e.differentiate());
   
    }
}
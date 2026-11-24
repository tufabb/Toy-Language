package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyHeap;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationalExpTest {
    private IDictionary<String, IValue> symTable;
    private IHeap heap;

    @BeforeEach
    public void setUp() {
        this.symTable = new MyDictionary<>();
        this.heap=new MyHeap();

    }
    private static IExp toInt(int n){return new ValueExp(new IntValue(n));}

    @Test
    void firstOperandNotInt(){
        IExp e=new RelationalExp(RelOp.GT,new ValueExp(new BoolValue(false)),toInt(7));
        var ex=assertThrows(ExpressionException.class,()->e.eval(symTable,heap));
        assertEquals("first operand is not an integer",ex.getMessage());
    }
    @Test
    void secondOperandNotInt(){
        IExp e=new RelationalExp(RelOp.GT,toInt(7),new ValueExp(new BoolValue(false)));
        var ex=assertThrows(ExpressionException.class,()->e.eval(symTable,heap));
        assertEquals("second operand is not an integer",ex.getMessage());
    }
    @Test
    void lessThenOperandTest() throws MyException {
        IExp e=new RelationalExp(RelOp.LT,toInt(7),toInt(19));
        assertEquals(new BoolValue(true),e.eval(symTable,heap));
    }

    @Test
    void lessEqualOperandTest() throws MyException{
        IExp e=new RelationalExp(RelOp.LE,toInt(10),toInt(10));
        assertEquals(new BoolValue(true),e.eval(symTable,heap));
    }
    @Test
    void equalOperandTest() throws MyException{
        IExp e=new RelationalExp(RelOp.EQ,toInt(12),toInt(14));
        assertEquals(new BoolValue(false),e.eval(symTable,heap));
    }
    @Test
    void notEqualOperandTest() throws MyException{
        IExp e=new RelationalExp(RelOp.NE,toInt(22),toInt(0));
        assertEquals(new BoolValue(true),e.eval(symTable,heap));
    }
    @Test
    void moreThenOperandTest() throws MyException{
        IExp e=new RelationalExp(RelOp.GT,toInt(333),toInt(1));
        assertEquals(new BoolValue(true),e.eval(symTable,heap));
    }
    @Test
    void moreEqualOperandTest() throws MyException{
        IExp e=new RelationalExp(RelOp.GE,toInt(19),toInt(19));
        assertEquals(new BoolValue(true),e.eval(symTable,heap));
    }

}
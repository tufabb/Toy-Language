package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExpTest {
    private IDictionary<String, IValue> symTable;

    private static IExp bul(boolean n) {
        return new ValueExp(new BoolValue(n));
    }

    @BeforeEach
    public void setUp() {
        this.symTable = new MyDictionary<>();

    }

    @Test
    void respectOperatorsOrder() throws MyException {
        IExp e = new LogicExp(LogicOp.AND, bul(true), new LogicExp(LogicOp.OR, bul(false), bul(true)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }
    @Test
    void firstOperandNotBool() throws MyException{
        IExp e=new LogicExp(LogicOp.AND,new ValueExp(new IntValue(2)),bul(true));
        var ex=assertThrows(ExpressionException.class,()->e.eval(symTable));
        assertEquals("first operand is not a boolean",ex.getMessage());
    }
    @Test
    void secondOperandNotBool() throws MyException{
        IExp e=new LogicExp(LogicOp.AND,bul(true),new ValueExp(new IntValue(2)));
        var ex=assertThrows(ExpressionException.class,()->e.eval(symTable));
        assertEquals("second operand is not a boolean",ex.getMessage());
    }
    @Test
    void checkToString(){
        IExp e = new LogicExp(LogicOp.AND, bul(true),bul(false));
        assertEquals("(true and false)",e.toString());
    }
}
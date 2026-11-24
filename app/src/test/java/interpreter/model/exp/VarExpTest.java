package interpreter.model.exp;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyHeap;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarExpTest {
    private IDictionary<String, IValue> symTable;
    private IHeap heap;

    @BeforeEach
    public void setUp() {
        this.symTable = new MyDictionary<>();
        this.heap=new MyHeap();

    }

    @Test
    void correctVarDeclarated() throws MyException {
        symTable.put("a",new BoolValue(true));
        VarExp a=new VarExp("a");
        assertEquals(new BoolValue(true),a.eval(symTable,heap));
    }
    @Test
    void incorrectVarDeclarated() throws MyException{
        VarExp a=new VarExp("a");
        var ex=assertThrows(ADTException.class,()->a.eval(symTable,heap));
        assertEquals("missing key",ex.getMessage());
    }
    @Test
    void varExpToString() {
        symTable.put("a",new BoolValue(true));
        VarExp a=new VarExp("a");
        assertEquals("a",a.toString());
    }
}
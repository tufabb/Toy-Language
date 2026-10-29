package interpreter.model.exp;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarExpTest {
    private final IDictionary<String, IValue> symTable = new MyDictionary<>();

    @Test
    void correctVarDeclarated() throws MyException {
        symTable.put("a",new BoolValue(true));
        VarExp a=new VarExp("a");
        assertEquals(new BoolValue(true),a.eval(symTable));
    }
    @Test
    void incorrectVarDeclarated() throws MyException{
        VarExp a=new VarExp("a");
        var ex=assertThrows(ADTException.class,()->a.eval(symTable));
        assertEquals("missing key",ex.getMessage());
    }
    @Test
    void varExpToString() {
        symTable.put("a",new BoolValue(true));
        VarExp a=new VarExp("a");
        assertEquals("a",a.toString());
    }
}
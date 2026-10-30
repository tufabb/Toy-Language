package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDictionaryTest {
    IDictionary<String, IValue> symTable;

    @BeforeEach
    public void setUp() {
        this.symTable = new MyDictionary<>();
    }

    @Test
    void correctAddedElement()throws MyException {
        symTable.put("a",new IntValue(32));
        symTable.put("b",new BoolValue(true));
        symTable.update("a",new IntValue(1));
        assertEquals(true,symTable.isDefined("a"));

    }
    @Test
    void searchForNonExistingKey(){
        assertEquals(false,symTable.isDefined("b"));
    }

    @Test
    void lookUpMissingKey(){
        var ex=assertThrows(ADTException.class,()->symTable.lookup("a"));
        assertEquals("missing key",ex.getMessage());
    }
    @Test
    void lookUpExistingKey()throws MyException{
        symTable.put("a",new IntValue(32));
        symTable.update("a",new IntValue(1));
        assertEquals(new IntValue(1),symTable.lookup("a"));
    }

}
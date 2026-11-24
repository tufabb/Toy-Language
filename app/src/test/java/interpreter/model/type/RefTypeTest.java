package interpreter.model.type;

import interpreter.model.value.RefValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefTypeTest {
    @Test
    void defaulfValue()
    {
        var a=new RefType(new IntType());
        assertEquals(new RefValue(0,new IntType()),a.defaultValue());
    }
    @Test
    void refRefEqual(){
        RefValue a1=new RefValue(1,new RefType(new IntType()));
        RefValue a2=new RefValue(1,new RefType(new IntType()));
        assertEquals(a1,a2);
    }
    @Test
    void refNotEqual(){
        RefValue a1=new RefValue(1,new RefType(new IntType()));
        RefValue a2=new RefValue(1,new IntType());
        assertNotEquals(a1,a2);
    }
}
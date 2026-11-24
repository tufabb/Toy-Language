package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {
    IHeap heap;

    @BeforeEach
    public void setUp(){this.heap=new MyHeap();}

    @Test
    void correctAllocate(){
        assertEquals(1,heap.allocate(new IntValue(5)));
        assertEquals(2,heap.allocate(new BoolValue(true)));
        assertEquals(3,heap.allocate(new StringValue("hello")));
    }

    @Test
    void getMissingKey()
    {
        var ex=assertThrows(ADTException.class,()->heap.get(17));
        assertEquals("missing address",ex.getMessage());
    }
    @Test
    void updateMissinfKey()
    {
        var ex=assertThrows(ADTException.class,()->heap.update(12,new IntValue(3)));
        assertEquals("missing address",ex.getMessage());
    }

}
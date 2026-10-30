package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    private IStack<IValue> exeStack;

    @BeforeEach
    public void setUp(){
        this.exeStack=new MyStack<>();
    }

    @Test
    void LastInFirstOutCheck() throws MyException {
        exeStack.push(new IntValue(2));
        exeStack.push(new IntValue(22));
        exeStack.push(new IntValue(6));
        exeStack.push(new IntValue(32));
        exeStack.push(new IntValue(99));
        exeStack.push(new IntValue(0));
        assertEquals(new IntValue(0),exeStack.pop());
        assertEquals(new IntValue(99),exeStack.pop());
        assertEquals(new IntValue(32),exeStack.pop());
        assertEquals(new IntValue(6),exeStack.pop());

    }

    @Test
    void popEmptyStack() throws MyException{
        exeStack.push(new IntValue(2));
        exeStack.push(new IntValue(22));
        IValue a=exeStack.pop();
        a=exeStack.pop();
        var ex=assertThrows(ADTException.class,()-> exeStack.pop());
        assertEquals("pop from an empty stack",ex.getMessage());
        assertTrue(exeStack.isEmpty());

    }

}
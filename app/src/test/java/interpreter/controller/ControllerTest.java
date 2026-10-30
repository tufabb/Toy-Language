package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.IStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private static PrgState run(IStmt program) throws MyException {
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(),
                new MyList<>(), program);
        new Controller(new Repository(state), false).allStep(); // display off
        return state;
    }

    @Test
    void example2PrintsEighteen() throws MyException {
        PrgState state = run(Examples.exemple2());
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(18), state.getOut().get(0));
    }

    @Test
    void example3PrintsEighteen() throws MyException {
        PrgState state = run(Examples.exemple3());
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void example1PrintsEighteen() throws MyException {
        PrgState state = run(Examples.exemple1());
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void oneStepThrowsEmptyStack() throws MyException {
        IStack<IStmt> exeStack = new MyStack<>();
        PrgState emptyState = new PrgState(exeStack, new MyDictionary<>(), new MyList<>(), new NopStmt());
        exeStack.pop();
        Controller controller = new Controller(new Repository(emptyState), false);
        assertThrows(StatementException.class, () -> controller.oneStep(emptyState));
    }


}
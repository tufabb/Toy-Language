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
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @TempDir
    Path dir;

    private PrgState run(IStmt program) throws MyException {
        PrgState state =PrgState.of(program);
        var repo=new Repository(state, dir.resolve("log.txt").toString());
        new Controller(repo, false).allStep(); // display off
        return state;
    }

    @Test
    void example2PrintsEighteen() throws MyException {
        PrgState state = run(Examples.EX2);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(18), state.getOut().get(0));
    }

    @Test
    void example3PrintsEighteen() throws MyException {
        PrgState state = run(Examples.EX3);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void example1PrintsEighteen() throws MyException {
        PrgState state = run(Examples.EX1);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void example5PrintsFive() throws MyException{
        PrgState state=run(Examples.EX5);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(5), state.getOut().get(0));

    }
    @Test
    void example4Prints() throws MyException{
        PrgState state=run(Examples.EX4);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(4), state.getOut().get(0));
        assertEquals(new IntValue(10), state.getOut().get(1));

    }
    @Test
    void example6() throws MyException{
        PrgState state=run(Examples.EX6);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new RefValue(1,new IntType()), state.getOut().get(0));
        assertEquals(new RefValue(2,new RefType(new IntType())), state.getOut().get(1));
    }
    @Test
    void example7() throws MyException{
        PrgState state=run(Examples.EX7);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(20), state.getOut().get(0));
        assertEquals(new IntValue(25), state.getOut().get(1));
    }
    @Test
    void example8() throws MyException{
        PrgState state=run(Examples.EX8);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(20), state.getOut().get(0));
        assertEquals(new IntValue(35), state.getOut().get(1));
    }
    @Test
    void example9() throws MyException{
        PrgState state=run(Examples.EX9);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(20), state.getOut().get(0));

    }
    @Test
    void example10() throws MyException{
        PrgState state=run(Examples.EX10);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(4), state.getOut().get(0));
        assertEquals(new IntValue(3), state.getOut().get(1));
        assertEquals(new IntValue(2), state.getOut().get(2));
        assertEquals(new IntValue(1), state.getOut().get(3));
        assertEquals(new IntValue(0), state.getOut().get(4));
    }

    @Test
    void oneStepThrowsEmptyStack() throws MyException {

        PrgState emptyState = PrgState.of(new NopStmt());
        emptyState.getExeStack().pop();
        Controller controller = new Controller(new Repository(emptyState, dir.resolve("log.txt").toString()), false);
        assertThrows(StatementException.class, () -> controller.oneStep(emptyState));
    }
    @Test
    void fileExampleReadsBothNumbersAndClosesTheFile() throws Exception {
        Path in = dir.resolve("test.in");
        Files.writeString(in, "15\n50\n");
        PrgState s = run(Examples.fileExample(in.toString()));
        assertEquals(new IntValue(15), s.getOut().get(0));
        assertEquals(new IntValue(50), s.getOut().get(1));
        assertTrue(s.getFileTable().toMap().isEmpty());
    }
    @Test
    void logFileContainsTheFourSectionsInOrder() throws Exception {
        run(Examples.EX1);
        String log = Files.readString(dir.resolve("log.txt"));
        int a = log.indexOf("ExeStack:"), b = log.indexOf("SymTable:"),
                c = log.indexOf("Out:"), d = log.indexOf("FileTable:");
        assertTrue(0 <= a && a < b && b < c && c < d);
    }
    @Test
    void fileExampleOnEmptyFilePrintsZeroZero() throws Exception {
        Path in = dir.resolve("empty.in");
        Files.writeString(in, "");                 // empty file
        PrgState s = run(Examples.fileExample(in.toString()));
        assertEquals(new IntValue(0), s.getOut().get(0));
        assertEquals(new IntValue(0), s.getOut().get(1));
        assertTrue(s.getFileTable().toMap().isEmpty());
    }


}
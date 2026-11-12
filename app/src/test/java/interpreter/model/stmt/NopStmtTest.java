package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.value.IValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NopStmtTest {
//    private IStack<IStmt> exeStack;
//    private IDictionary<String, IValue> symTable;
//    private IList<IValue> out;
//
//    @BeforeEach
//    public void setUp(){
//        exeStack=new MyStack<>();
//        symTable=new MyDictionary<>();
//        out=new MyList<>();
//
//    }
    @Test
    void runNoStmt(){
        NopStmt nopStmt=new NopStmt();
        PrgState state= PrgState.of(nopStmt);
        IStack<IStmt>firstStack=state.getExeStack();
        nopStmt.execute(state);
        assertSame(firstStack,state.getExeStack());
        assertEquals(0,state.getOut().size());

    }


}
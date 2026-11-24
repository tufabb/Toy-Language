package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WriteHeapStmtTest {
    private PrgState state;
    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
    }

    @Test
    void changeHeapLocation() throws MyException{
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);
        var ref = (RefValue) state.getSymTable().lookup("v");
        int addr = ref.address();

        assertEquals(new IntValue(20), state.getHeap().get(addr));

        new WriteHeapStmt("v", new ValueExp(new IntValue(30))).execute(state);

        assertEquals(new IntValue(30), state.getHeap().get(addr));
    }

    @Test
    void varNotRef()throws MyException{
        new VarDeclStmt("a",new IntType()).execute(state);
        assertThrows(StatementException.class,()->new WriteHeapStmt("a",new ValueExp(new IntValue(2))).execute(state));
    }

    @Test
    void addrNotInHeap() throws MyException{
        state.getSymTable().put("v", new RefValue(12, new IntType()));
        assertThrows(StatementException.class,()->new WriteHeapStmt("v",new ValueExp(new IntValue(10))).execute(state));
    }
    @Test
    void valueWrongType() throws MyException{
        var exp = new ValueExp(new BoolValue(true));
        assertThrows(StatementException.class,()->new WriteHeapStmt("v",exp).execute(state));
    }

}
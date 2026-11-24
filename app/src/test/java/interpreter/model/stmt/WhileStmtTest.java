package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.*;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhileStmtTest {
    private PrgState state;
    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());

    }

    @Test
    void correctWhile() throws MyException{
        new VarDeclStmt("a",new IntType()).execute(state);
        new AssignStmt("a",new ValueExp(new IntValue(0))).execute(state);
        var cond=new RelationalExp(RelOp.LT,new VarExp("a"),new ValueExp(new IntValue(4)));
        var body=new AssignStmt("a", new ArithExp(ArithOp.ADD,new VarExp("a"),new ValueExp(new IntValue(1))));
        state.getExeStack().push(new WhileStmt(cond, body));
        while (!state.getExeStack().isEmpty()) {
            IStmt s = (IStmt) state.getExeStack().pop();
            s.execute(state);
        }
        assertEquals(new IntValue(4),state.getSymTable().lookup("a"));
    }
    @Test
    void condNonBool() throws MyException{
        new VarDeclStmt("a",new IntType()).execute(state);
        new AssignStmt("a",new ValueExp(new IntValue(0))).execute(state);
        var cond=new ValueExp(new IntValue(13));
        var body=new AssignStmt("a", new ArithExp(ArithOp.ADD,new VarExp("a"),new ValueExp(new IntValue(1))));
        assertThrows(StatementException.class,()->new WhileStmt(cond,body).execute(state));
    }

}
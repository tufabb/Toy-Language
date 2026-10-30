package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompStmtTest {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;

    @BeforeEach
    public void setUp(){
        exeStack=new MyStack<>();
        symTable=new MyDictionary<>();
        out=new MyList<>();

    }
    @Test
    void correctOrder()throws MyException {
        IStmt stmt1 = new VarDeclStmt("a", new IntType());
        IStmt stmt2 = new AssignStmt("a", new ValueExp(new IntValue(10)));
        CompStmt compStmt = new CompStmt(stmt1, stmt2);
        PrgState state = new PrgState(exeStack, symTable, out, compStmt);
        compStmt.execute(state);
        assertFalse(exeStack.isEmpty());
        IStmt top = exeStack.pop();
        assertEquals(stmt1, top);

    }

}
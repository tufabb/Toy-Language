package interpreter.model.stmt;

import com.sun.jdi.Value;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignStmtTest {
    private PrgState state;

    @BeforeEach
    public void setUp() {
        // Initialize a clean program state using the factory method with a NopStmt placeholder
        state = PrgState.of(new NopStmt());
    }

    @Test
    void correctAss() throws MyException {
        VarDeclStmt decl = new VarDeclStmt("v", new IntType());
        decl.execute(state); // Execute declaration on the shared state

        AssignStmt asg = new AssignStmt("v", new ValueExp(new IntValue(12)));
        asg.execute(state); // Execute assignment on the SAME state

        assertTrue(state.getSymTable().isDefined("v"));
        assertEquals(new IntValue(12), state.getSymTable().lookup("v"));
    }
    @Test
    void variableNotDeclaredThrow() {
        AssignStmt asg = new AssignStmt("v", new ValueExp(new IntValue(12)));

        // Attempting to assign to 'v' without declaring it first
        var ex = assertThrows(StatementException.class, () -> asg.execute(state));
        assertEquals("variable v was not declared", ex.getMessage());
    }
    @Test
    void declaredTypeNotMatch() throws MyException {
        VarDeclStmt decl = new VarDeclStmt("v", new IntType());
        decl.execute(state); // Declare 'v' as int

        AssignStmt asg = new AssignStmt("v", new ValueExp(new BoolValue(true))); // Try assigning a bool

        var ex = assertThrows(StatementException.class, () -> asg.execute(state));
        assertEquals("declared type of variable v and type of the assigned expression do not match", ex.getMessage());
    }


}
//
//class AssignStmtTest {
//    private IStack<IStmt> exeStack;
//    private IDictionary<String, IValue> symTable;
//    private IList<IValue>out;
//
//    @BeforeEach
//    public void setUp(){
//        exeStack=new MyStack<>();
//        symTable=new MyDictionary<>();
//        out=new MyList<>();
//
//    }
//    @Test
//    void correctAss() throws MyException {
//        VarDeclStmt decl=new VarDeclStmt("v",new IntType());
//        PrgState initialState=PrgState.of(decl);
//        decl.execute(initialState);
//        AssignStmt asg=new AssignStmt("v",new ValueExp(new IntValue(12)));
//        PrgState state=PrgState.of(asg);
//        asg.execute(state);
//        assertTrue(symTable.isDefined("v"));
//        assertEquals(new IntValue(12),symTable.lookup("v"));
//
//    }
//    @Test
//    void variableNotDeclaredThrow() throws MyException{
//        AssignStmt asg=new AssignStmt("v",new ValueExp(new IntValue(12)));
//        PrgState state=PrgState.of(asg);
//        var ex=assertThrows(StatementException.class,()-> asg.execute(state));
//        assertEquals("variable v was not declared",ex.getMessage());
//    }
//    @Test
//    void declaredTypeNotMatch()throws MyException{
//        VarDeclStmt decl=new VarDeclStmt("v",new IntType());
//        PrgState initialState=PrgState.of(decl);
//        decl.execute(initialState);
//        AssignStmt asg=new AssignStmt("v",new ValueExp(new BoolValue(true)));
//        PrgState state= PrgState.of(asg);
//        var ex=assertThrows(StatementException.class,()-> asg.execute(state));
//        assertEquals("declared type of variable v and type of the assigned expression do not match",ex.getMessage());
//
//
//    }
//
//
//}
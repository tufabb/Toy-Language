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
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue>out;

    @BeforeEach
    public void setUp(){
        exeStack=new MyStack<>();
        symTable=new MyDictionary<>();
        out=new MyList<>();

    }
    @Test
    void correctAss() throws MyException {
        VarDeclStmt decl=new VarDeclStmt("v",new IntType());
        PrgState initialState=new PrgState(exeStack,symTable,out,decl);
        decl.execute(initialState);
        AssignStmt asg=new AssignStmt("v",new ValueExp(new IntValue(12)));
        PrgState state=new PrgState(exeStack,symTable,out,asg);
        asg.execute(state);
        assertTrue(symTable.isDefined("v"));
        assertEquals(new IntValue(12),symTable.lookup("v"));

    }
    @Test
    void variableNotDeclaredThrow() throws MyException{
        AssignStmt asg=new AssignStmt("v",new ValueExp(new IntValue(12)));
        PrgState state=new PrgState(exeStack,symTable,out,asg);
        var ex=assertThrows(StatementException.class,()-> asg.execute(state));
        assertEquals("variable v was not declared",ex.getMessage());
    }
    @Test
    void declaredTypeNotMatch()throws MyException{
        VarDeclStmt decl=new VarDeclStmt("v",new IntType());
        PrgState initialState=new PrgState(exeStack,symTable,out,decl);
        decl.execute(initialState);
        AssignStmt asg=new AssignStmt("v",new ValueExp(new BoolValue(true)));
        PrgState state=new PrgState(exeStack,symTable,out,asg);
        var ex=assertThrows(StatementException.class,()-> asg.execute(state));
        assertEquals("declared type of variable v and type of the assigned expression do not match",ex.getMessage());


    }

}
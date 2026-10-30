package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.type.IntType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarDeclStmtTest {
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
    void correctDeclared()throws MyException {
        VarDeclStmt decl=new VarDeclStmt("v",new IntType());
        PrgState initialState=new PrgState(exeStack,symTable,out,decl);
        decl.execute(initialState);
        assertEquals(new IntValue(0),symTable.lookup("v"));
    }
    @Test
    void varAlreadyDecl()throws MyException{
        VarDeclStmt decl=new VarDeclStmt("v",new IntType());
        PrgState initialState=new PrgState(exeStack,symTable,out,decl);
        decl.execute(initialState);
        var ex=assertThrows(StatementException.class,()->decl.execute(initialState));
        assertEquals("variable already declared",ex.getMessage());
    }

}
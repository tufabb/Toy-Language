package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintStmtTest {
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
    void addsValueToOut() throws MyException {
        PrintStmt print=new PrintStmt(new ValueExp(new IntValue(5)));
        PrgState state=new PrgState(exeStack,symTable,out,print);
        print.execute(state);
        assertEquals(1,out.size());
        assertEquals(new IntValue(5),out.get(0));
    }
    @Test
    void executeInvalid(){
        PrintStmt print=new PrintStmt(new VarExp("x"));
        PrgState state=new PrgState(exeStack,symTable,out,print);
        var ex=assertThrows(MyException.class,()-> print.execute(state));
        assertEquals(0,out.size());
    }
    

}
package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyHeap;
import interpreter.model.stmt.NewStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadHeapExpTest {
    private PrgState state;
    private IDictionary<String, IValue> symTable;
    private IHeap heap;
    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
        symTable= state.getSymTable();
        heap=state.getHeap();
        }

    @Test
    void readsCorrectly() throws MyException{
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);
        IExp e=new ReadHeapExp(new VarExp("v"));
        assertEquals(new IntValue(20),e.eval(state.getSymTable(),state.getHeap()));
    }
    @Test
    void notReferenceType(){
        symTable.put("a",new IntValue(20));
        IExp e=new ReadHeapExp(new VarExp("a"));
        assertThrows(ExpressionException.class,()->e.eval(symTable,heap));
    }
    @Test
    void addressNotInHeap(){
       symTable.put("a",new RefValue(12,new IntType()));
       IExp e=new ReadHeapExp(new VarExp("a"));
        assertThrows(ExpressionException.class,()->e.eval(symTable,heap));
    }
}
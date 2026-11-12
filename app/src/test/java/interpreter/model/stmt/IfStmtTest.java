package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfStmtTest {
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
    private PrgState state;

    @BeforeEach
    public void setUp() {

        state = PrgState.of(new NopStmt());
    }
    @Test
    void ifStmtFirstCondWorks()throws MyException {
        VarDeclStmt decl1=new VarDeclStmt("a",new BoolType());

        decl1.execute(state);

        VarDeclStmt decl2=new VarDeclStmt("v",new IntType());
        decl2.execute(state);
        AssignStmt asg=new AssignStmt("a",new ValueExp(new BoolValue(true)));
        asg.execute(state);

        IfStmt if1=new IfStmt(new VarExp("a"),
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3))));

        if1.execute(state);
        IStmt pushedStmt = (IStmt) state.getExeStack().pop();
        pushedStmt.execute(state);
        assertEquals(new IntValue(2),state.getSymTable().lookup("v"));


    }
    @Test
    void ifStmtSecondCondWorks()throws MyException {
        VarDeclStmt decl1=new VarDeclStmt("a",new BoolType());

        decl1.execute(state);

        VarDeclStmt decl2=new VarDeclStmt("v",new IntType());

        decl2.execute(state);
        AssignStmt asg=new AssignStmt("a",new ValueExp(new BoolValue(false)));

        asg.execute(state);

        IfStmt if1=new IfStmt(new VarExp("a"),
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3))));

        if1.execute(state);
        IStmt pushedStmt = (IStmt) state.getExeStack().pop();
        pushedStmt.execute(state);
        assertEquals(new IntValue(3),state.getSymTable().lookup("v"));


    }

    @Test
    void ifStmtCondNotBool() throws MyException{
        VarDeclStmt decl1=new VarDeclStmt("a",new IntType());

        decl1.execute(state);
        AssignStmt asg=new AssignStmt("a",new ValueExp(new IntValue(22)));
        asg.execute(state);
        VarDeclStmt decl2=new VarDeclStmt("v",new IntType());

        decl2.execute(state);
        IfStmt if1=new IfStmt(new VarExp("a"),
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3))));

        var ex=assertThrows(MyException.class,()->if1.execute(state));
        assertEquals("if statement is wrong",ex.getMessage());

    }



}
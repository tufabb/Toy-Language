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
    void ifStmtFirstCondWorks()throws MyException {
        VarDeclStmt decl1=new VarDeclStmt("a",new BoolType());
        PrgState initial1State=new PrgState(exeStack,symTable,out,decl1);
        decl1.execute(initial1State);

        VarDeclStmt decl2=new VarDeclStmt("v",new IntType());
        PrgState initial2State=new PrgState(exeStack,symTable,out,decl2);
        decl2.execute(initial2State);
        AssignStmt asg=new AssignStmt("a",new ValueExp(new BoolValue(true)));
        PrgState state=new PrgState(exeStack,symTable,out,asg);
        asg.execute(state);

        IfStmt if1=new IfStmt(new VarExp("a"),
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3))));
        PrgState if1State=new PrgState(exeStack,symTable,out,if1);
        if1.execute(if1State);
        IStmt pushedStmt = exeStack.pop();
        pushedStmt.execute(if1State);
        assertEquals(new IntValue(2),symTable.lookup("v"));


    }
    @Test
    void ifStmtSecondCondWorks()throws MyException {
        VarDeclStmt decl1=new VarDeclStmt("a",new BoolType());
        PrgState initial1State=new PrgState(exeStack,symTable,out,decl1);
        decl1.execute(initial1State);

        VarDeclStmt decl2=new VarDeclStmt("v",new IntType());
        PrgState initial2State=new PrgState(exeStack,symTable,out,decl2);
        decl2.execute(initial2State);
        AssignStmt asg=new AssignStmt("a",new ValueExp(new BoolValue(false)));
        PrgState state=new PrgState(exeStack,symTable,out,asg);
        asg.execute(state);

        IfStmt if1=new IfStmt(new VarExp("a"),
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3))));
        PrgState if1State=new PrgState(exeStack,symTable,out,if1);
        if1.execute(if1State);
        IStmt pushedStmt = exeStack.pop();
        pushedStmt.execute(if1State);
        assertEquals(new IntValue(3),symTable.lookup("v"));


    }

    @Test
    void ifStmtCondNotBool() throws MyException{
        VarDeclStmt decl1=new VarDeclStmt("a",new IntType());
        PrgState initial1State=new PrgState(exeStack,symTable,out,decl1);
        decl1.execute(initial1State);
        AssignStmt asg=new AssignStmt("a",new ValueExp(new IntValue(22)));
        PrgState state=new PrgState(exeStack,symTable,out,asg);
        asg.execute(state);
        VarDeclStmt decl2=new VarDeclStmt("v",new IntType());
        PrgState initial2State=new PrgState(exeStack,symTable,out,decl2);
        decl2.execute(initial2State);
        IfStmt if1=new IfStmt(new VarExp("a"),
                new AssignStmt("v", new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3))));
        PrgState if1State=new PrgState(exeStack,symTable,out,if1);
        var ex=assertThrows(MyException.class,()->if1.execute(if1State));
        assertEquals("if statement is wrong",ex.getMessage());

    }



}
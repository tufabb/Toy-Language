package interpreter.model;

import interpreter.model.adt.*;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private final IStmt originalProgram;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out, IStmt program) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = program;
        exeStack.push(program);
    }

    //getters
    public IStack getExeStack() {
        return exeStack;
    }

    public IDictionary getSymTable() {
        return symTable;
    }

    public IList getOut() {
        return out;
    }

    //toString
    public String toString(){
        return exeStack+ "         "+symTable+"           "+out;
    }

    //setters
    public void setExeStack(IStack newStack) {
        this.exeStack = newStack;
    }

    public void setSymTable(IDictionary newTable) {
        this.symTable = newTable;
    }

    public void setOut(IList newOut) {
        this.out = newOut;
    }

}
//PrgState state=new PrgState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),program);

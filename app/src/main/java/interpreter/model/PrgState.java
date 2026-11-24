package interpreter.model;

import interpreter.model.adt.*;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.util.TreeMap;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private final IStmt originalProgram;
    private IHeap heap;

    public PrgState(IStack<IStmt> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out, IDictionary<StringValue,BufferedReader> fileTable, IStmt program,IHeap heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable=fileTable;
        this.originalProgram = program;
        this.heap=heap;
        exeStack.push(program);
    }

    public static PrgState of(IStmt program){
        return new PrgState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),new MyDictionary<>(),program,new MyHeap());
    }

    //getters
    public IStack getExeStack() {
        return exeStack;
    }

    public IHeap getHeap(){return heap;}

    public IDictionary getSymTable() {
        return symTable;
    }

    public IList getOut() {
        return out;
    }

    public IDictionary getFileTable(){return fileTable;}

    //toString
    public String toString(){
        return "ExeStack: "+exeStack+ "\nSymTable: "+symTable+"\nOut: "+out+"\nHeap: "+heap;
    }

    public String toLogString() {
        var sb = new StringBuilder();
        sb.append("ExeStack:\n");
        exeStack.toList().forEach(s -> sb.append(s).append('\n'));
        sb.append("SymTable:\n");
        symTable.toMap().forEach((k, v) ->
                sb.append(k).append(" --> ").append(v).append('\n'));
        sb.append("Out:\n");
        out.toList().forEach(v -> sb.append(v).append('\n'));
        sb.append("FileTable:\n");
        fileTable.toMap().keySet().forEach(f -> sb.append(f.val()).append('\n'));
        sb.append("Heap:\n");
        new TreeMap<>(heap.getContent()).forEach((k,s)->sb.append(k).append(" --> ").append(s).append("\n"));
        return sb.toString();
    }
    //setters
    public void setHeap(IHeap newHeap){this.heap=newHeap;}

    public void setExeStack(IStack newStack) {
        this.exeStack = newStack;
    }

    public void setSymTable(IDictionary newTable) {
        this.symTable = newTable;
    }

    public void setOut(IList newOut) {
        this.out = newOut;
    }

    public void setFileTable(IDictionary newTable){this.fileTable=newTable;}

}
//PrgState state=new PrgState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),program);

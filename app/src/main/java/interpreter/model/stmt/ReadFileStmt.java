package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record ReadFileStmt(IExp exp,String varName) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        var symTable=state.getSymTable();
        if(!symTable.isDefined(varName))
            throw new StatementException("variable not declared");
        IValue v= (IValue) symTable.lookup(varName);
        if(!(v.getType() instanceof IntType))
            throw new StatementException("variable not of type int");
        if(!(exp.eval(symTable) instanceof StringValue fileName))
            throw new StatementException("read file: "+exp+ "is not a string ");
        var fileTable=state.getFileTable();
        if(!fileTable.isDefined(fileName))
            throw new StatementException("read file: "+exp+" is not open to read from");
        try{
            int n;
            BufferedReader reader=(BufferedReader) fileTable.lookup(fileName);
            String line=reader.readLine();
            if(line==null)
                n=0;
            else
                n=Integer.parseInt(line.strip());
            symTable.update(varName,new IntValue(n));

        }catch(IOException e)
        {
            throw new StatementException("read file: "+exp+" "+e.getMessage());
        }
        catch(NumberFormatException e){
            throw new StatementException("read file: "+exp+" "+e.getMessage());
        }
        return state;
    }
}

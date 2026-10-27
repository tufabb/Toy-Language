package interpreter.model.type;

public record IntType()implements IType {
    @Override
    public IValue defaultValue(){
        return new IntValue(0);
    }
    @Override
    public String toString(){
        return "int";
    }
}

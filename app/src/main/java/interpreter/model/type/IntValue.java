package interpreter.model.type;

public record IntValue(int val) implements IValue {
    @Override
    public IType getType(){
        return new IntType();
    }
    @Override
    public String toString(){
        return Integer.toString(val);
    }
}

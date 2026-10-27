package interpreter.model.type;

public record BoolType() implements IType {
    @Override
    public IValue defaultValue(){
        return new BoolValue(false);
    }
    @Override
    public String toString(){
        return "boolean";
    }
}

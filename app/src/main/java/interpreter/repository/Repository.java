package interpreter.repository;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private final List<PrgState> repo;

    public Repository(PrgState initialState){
        this.repo=new ArrayList<>();
        repo.add(initialState);
    }

    public Repository(){
        this.repo=new ArrayList<>();
    }

    @Override
    public PrgState getCrtPrg() throws MyException {
        if (repo.isEmpty())
            throw new MyException("Repo is empty");
        return repo.get(repo.size()-1);

    }
}

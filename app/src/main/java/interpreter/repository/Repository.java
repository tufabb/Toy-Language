package interpreter.repository;

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
    public PrgState getCrtPrg() {
        return repo.get(repo.size()-1);
    }
}

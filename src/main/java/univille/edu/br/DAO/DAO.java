package univille.edu.br.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> listarPorId(long id);
    List<T> listarTodos();
    void inserir(T t);
    void alterar(T t);
    void remover(long id);
}

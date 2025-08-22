package univille.edu.br.Service;

public interface Service<T> {

    void listarTodos();
    void listarPorId(long id);
    void inserir(T t);
    void alterar(T t);
    void remover(long id);
}

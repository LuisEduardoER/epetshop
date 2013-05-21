package util;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HibernateDAO<T> implements InterfaceHibernateDAO<T> {

    private Session sessao;
    private Class<T> classe;

    // construtor passando a classe como parametro
    public HibernateDAO(Class<T> classe) {
        this.classe = classe;
    }  
    // seta uma sessao
    public void setSession(Session session){
        this.sessao = session;
    }
    // metodos CRUD
    @Override
    public void salvar(T entidade) {
        this.sessao.save(entidade);
    }
    @Override
    public void atualizar(T endidade) {
        this.sessao.update(endidade);
    }
    @Override
    public void excluir(T entidade) {
        this.sessao.delete(entidade);
    }
    @Override
    public T carregar(Integer codigo) {
        return (T) this.sessao.get(classe, codigo);
    }
    @Override
    public List<T> listar() {
        return this.sessao.createCriteria(classe).list();
    }
    ////////////////////////////////////////////////////////////////////////////
    //Consultas personalizadas
    
    @Override
    public List<T> buscaPersonalizada(String atributo,String nomeBuscado) {
        return this.sessao.createCriteria(classe)
                .add(Restrictions.eq(atributo,nomeBuscado))
                .list();
    }
}
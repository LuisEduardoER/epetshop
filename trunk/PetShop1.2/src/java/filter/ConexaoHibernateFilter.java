package filter;
import javax.servlet.*;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {
    private SessionFactory sf;
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
    FilterChain chain) throws ServletException {
        try {
            this.sf.getCurrentSession().beginTransaction();
            chain.doFilter(servletRequest, servletResponse);
            this.sf.getCurrentSession().getTransaction().commit();
        } catch (Throwable ex) {
            try {
                if (this.sf.getCurrentSession().getTransaction().isActive()) {
                    this.sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable t) {
                System.out.println("ERRO: " + t.getMessage());
            }
            throw new ServletException(ex);
        } finally {
            this.sf.getCurrentSession().close();
        }
    }
}

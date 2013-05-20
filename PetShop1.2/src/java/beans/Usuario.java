package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;


@ManagedBean(name="usuario")
@SessionScoped

@Entity
@SequenceGenerator(allocationSize= 1, name = "idgen", sequenceName = "usuario_seq")
@Table(name = "usuario")
public class Usuario implements Serializable {
    
        @Id
        @Column(name="id")
        private Integer id;
    
        @Column(name="login",length=20,unique=true)
        private String login;
        
        @Column(name="senha",length=35)
        private String senha;
        
        @Transient
        private String senha2;
        
        @Column(name="nome")
        private String nome;
        
        @Column(name="contato")
        private String contato;
        
        @Column(name="endereco")
        private String endereco;
        
        @Column(name="numero")
        private Integer numero;
        
        @Column(name="complemento")
        private String complemento;
        
        @Column(name="bairro")
        private String bairro;
        
        @Column(name="cidade")
        private String cidade;
        
        @Column(name="estado")
        private String estato;
        
        @Column(name="ativo")
        private Boolean ativo=false;
        
        @Column(name="permissao")
        private String permissao;
        
        @Column(name="email",length=65)
        private String email;
               
        @Column(name="data_cadastro")
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date data_cadastro;
        
        @OneToMany(mappedBy="usuario")
        Set<UsuarioPermissao> permissoes;
        
        @OneToMany(mappedBy="usuario")
        Set<Agenda> agendas;
              
        
        ////////////////////////////////////////////////////////////////////////
        // para inserir no banco o MD5 use a sequinte query:
        // insert into usuario(id,login,senha,ativo,email,apelido)
        // values (1,'teste',MD5('123'),true,'bla@gm','Nome');
        // colocar tbm a permissao:
        // insert into usuario_permissao(id,permissao,id_usuario)
        // values (1,'ROLE_ADMINISTRADOR',1);
        
        public static String MD5(String senha) {
            PasswordEncoder encoder = new Md5PasswordEncoder();
            senha = encoder.encodePassword(senha, null);
            return senha;
        }

        ////////////////////////////////////////////////////////////////////////

    public Set<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = MD5(senha);
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = MD5(senha2);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstato() {
        return estato;
    }

    public void setEstato(String estato) {
        this.estato = estato;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Set<UsuarioPermissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<UsuarioPermissao> permissoes) {
        this.permissoes = permissoes;
    }

}
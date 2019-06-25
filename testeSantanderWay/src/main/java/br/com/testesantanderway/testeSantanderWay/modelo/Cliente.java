package br.com.testesantanderway.testeSantanderWay.modelo;

public class Cliente {
    private Long codigoUsuario;
    private String nome;
    private String email;
    private String senha;


    public Cliente(Long codigoUsuario, String nome, String email, String senha){
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;

        result = prime * result + ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());

        return result;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
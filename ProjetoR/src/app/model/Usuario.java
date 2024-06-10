package app.model;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private int RGM;

    public Usuario(int id,int RGM,String nome,String senha){
        this.id=id;
        this.RGM=RGM;
        this.nome=nome;
        this.senha=senha;
    }

    public Usuario(String nome,String senha, int RGM) {
        this.nome = nome;
        this.RGM = RGM;
        this.senha = senha;
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setUsuario(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getRGM(){
        return RGM;
    }
    public void setRGM(int RGM){
        this.RGM = RGM;
    }

}

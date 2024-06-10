package app.model;

public class Prova {
    private int id;
    private String nome;
    private int nota;
    
    public Prova(int id,String nome,int nota){
        this.id=id;
        this.nome = nome;
        this.nota=nota;
    }
    public Prova(String nome){
        this.nome=nome;
    }
    public Prova(String nome,int nota){
        this.nome=nome;
        this.nota=nota;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
}
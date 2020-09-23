package Model;

public class Prduit
{
    int id,prix ;
    String name ,qte ;

    public Prduit(int id, String name,int prix,  String qte) {
        this.id = id;
        this.prix = prix;
        this.name = name;
        this.qte = qte;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

package it.unisannio.ingsw24.entities;

/**
 * This class represents the UnPackedMySQL entity.
 */
public class UnPackedMySQL {

    private int id;
    private String name;
    private int averageExipireDays;
    private Category category;


    /**
     * Costruttore per la classe UnPackedMySQL.
     * @param id ID dell'alimento.
     * @param name Nome dell'alimento.
     * @param averageExipireDays Media dei giorni di scadenza dell'alimento.
     * @param category Categoria dell'alimento.
     */
    public UnPackedMySQL(int id, String name, int averageExipireDays, Category category){
        this.id = id;
        this.name = name;
        this.averageExipireDays = averageExipireDays;
        this.category = category;
    }

    /**
     * Ottieni l'ID dell'alimento.
     * @return l'ID dell'alimento.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Ottieni il nome dell'alimento.
     * @return il nome dell'alimento.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Ottieni la media dei giorni di scadenza dell'alimento.
     * @return la media dei giorni di scadenza dell'alimento.
     */
    public int getAverageExipireDays() {
        return this.averageExipireDays;
    }

    /**
     * Ottieni la categoria dell'alimento.
     * @return la categoria dell'alimento.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Rappresentazione in stringa della classe UnPackedMySQL.
     */
    public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nAverage Exipire Days: "+ this.averageExipireDays + "\nCategory: " + this.category ;
    }
}

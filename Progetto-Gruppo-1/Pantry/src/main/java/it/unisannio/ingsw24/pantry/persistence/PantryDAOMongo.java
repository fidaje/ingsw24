package it.unisannio.ingsw24.pantry.persistence;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.ingsw24.entities.Food;
import it.unisannio.ingsw24.entities.Pantry;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PantryDAOMongo implements PantryDAO {

    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> collection;

    public PantryDAOMongo(){
        if (host == null){
            host = "127.0.0.1";
        }
        if (port == null){
            port = "27017";
        }

        String URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
        this.collection = mongoDatabase.getCollection(COLLECTION);
        this.createDB();

    }
    @Override
    public boolean dropDB() {
        mongoDatabase.drop();
        return true;
    }

    @Override
    public boolean createDB() {
        try{
            IndexOptions indexOptions = new IndexOptions();
            String resultCreateIndex = this.collection.createIndex(Indexes.ascending(PANTRY_ID), indexOptions);
        }
        catch (DuplicateKeyException e){
            System.out.printf("Duplicate field values encountered, couldn't create index: \t%s\n",e);
            return false;
        }
        return true;
    }

    private Pantry pantryFromDocument(Document d){
        return new Pantry(d.getInteger(OWNER_ID), (List<Food>) d.get(FOODS), (List<Integer>) d.get(GUESTS));
    }

    public Pantry getPantry(int id){
        List<Pantry> pantries = new ArrayList<>();
        for(Document current : this.collection.find(eq(PANTRY_ID,id))){
            Pantry p = pantryFromDocument(current);
            pantries.add(p);
        }
        if (pantries.isEmpty()) return null;

        assert pantries.size() == 1;
        return pantries.get(0);
    }

    @Override
    public boolean closeConnection() {
        return false;
    }

    public int getNextId(){
        Document result = collection.find().sort(new Document(PANTRY_ID, -1)).first();
        if (result == null) return 1;
        else return result.getInteger(PANTRY_ID) + 1;
    }


    private Document pantryToDocument(Pantry pantry) {
        return new Document(PANTRY_ID, getNextId())
                .append(OWNER_ID, pantry.getIdOwner())
                .append(FOODS, new ArrayList<>())
                .append(GUESTS, new ArrayList<>());
    }

    @Override
    public int createPantry(Pantry p) {
        try {
            Document pantryDocument = pantryToDocument(p);
            this.collection.insertOne(pantryDocument);
            return p.getIdOwner();
        } catch (MongoWriteException e){
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public List<Pantry> getPantries(int id) {
        return null;
    }

    @Override
    public boolean updateFoods(Food f) {
        return false;
    }

    @Override
    public boolean updateGuests(int idGuest) {
        return false;
    }

    @Override
    public boolean deleteFoodByName(String name) {
        return false;
    }

    @Override
    public boolean deleteGuestByID(int id) {
        return false;
    }

    @Override
    public boolean deletePantry(int id) {
        return false;
    }
}
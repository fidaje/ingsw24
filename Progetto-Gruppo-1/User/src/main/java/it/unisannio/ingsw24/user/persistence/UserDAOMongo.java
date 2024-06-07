package it.unisannio.ingsw24.user.persistence;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import it.unisannio.ingsw24.entities.MyUser;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class UserDAOMongo implements UserDAO {

    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");
    private static String URI;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public UserDAOMongo(){
        if (host == null) {
            host = "localhost";
        }
        if (port == null){
            port = "27017";
        }

        URI = "mongodb://" + host + ":" + port;

        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.collection = database.getCollection(COLLECTION);
        this.createDB();
    }



    @Override
    public boolean dropDB() {
        database.drop();
        return true;
    }

    @Override
    public boolean createDB() {
        System.out.println("Connecting to mongol with URI: " + URI);

        try{
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.collection.createIndex(Indexes.ascending(ELEMENT_USERNAME), indexOptions);
        }
        catch (DuplicateKeyException e){
            System.out.printf("Duplicate Field Values Encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;
    }


    private static MyUser userFromDocument(Document d){
        List<String> list = d.getList(ELEMENT_ROLES, String.class);
        return new MyUser(d.getString(ELEMENT_USERNAME), d.getString(ELEMENT_PASSWORD), new ArrayList<>(list));
    }

    private static Document userToDocument(MyUser u){
        return new Document(ELEMENT_USERNAME, u.getUsername())
                .append(ELEMENT_PASSWORD, u.getPassword())
                .append(ELEMENT_ROLES, u.getRoles());
    }

    @Override
    public String createUser(MyUser user) {

        try{
            Document userDocument = userToDocument(user);
            this.collection.insertOne(userDocument);
            return user.getUsername();
        }
        catch(MongoWriteException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MyUser getUser(String username) {
        List<MyUser> users = new ArrayList<>();
        for(Document current : this.collection.find(eq(ELEMENT_USERNAME, username))){
            MyUser u = userFromDocument(current);
            users.add(u);
        }
        if (users.isEmpty()) return null;
        assert users.size() == 1;
        return users.get(0);
    }

    @Override
    public ArrayList<MyUser> getAllUsers() {
        ArrayList<MyUser> users = new ArrayList<>();
        for(Document current : this.collection.find()){
            MyUser u = userFromDocument(current);
            users.add(u);
        }
        return users;
    }

    @Override
    public boolean killUser(String username) {

        try{
            if(getUser(username) != null){
                collection.findOneAndDelete(Filters.eq(ELEMENT_USERNAME, username));
                return true;
            }
        }
        catch (MongoException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean closeConnection() {
        mongoClient.close();
        return true;
    }
}

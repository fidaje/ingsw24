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
import com.mongodb.client.result.UpdateResult;
import it.unisannio.ingsw24.entities.MyUser;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

/**
 * This class is used to interact with the Mongo database and perform operations on the Users collection.
 */
public class UserDAOMongo implements UserDAO {

    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");
    private static String URI;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;


    /**
     * This constructor initializes the connection to the database.
     */
    public UserDAOMongo(){
        if (host == null) {
            host = "172.31.6.1";
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

    /**
     * This method is used to drop the database.
     * @return True if the database was dropped, false otherwise.
     */
    @Override
    public boolean dropDB() {
        database.drop();
        return true;
    }

    /**
     * This method is used to create the database.
     * @return True if the database was created, false otherwise.
     */
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

    /**
     * This method is used to convert a Document object to a MyUser object.
     * @param d The Document object to be converted.
     * @return The MyUser object.
     */
    private static MyUser userFromDocument(Document d){
        List<String> list = d.getList(ELEMENT_ROLES, String.class);
        return new MyUser(d.getInteger(ELEMENT_ID), d.getString(ELEMENT_USERNAME), d.getString(ELEMENT_PASSWORD), new ArrayList<>(list));
    }

    /**
     * This method is used to convert a MyUser object to a Document object.
     * @param u The MyUser object to be converted.
     * @return The Document object.
     */
    private Document userToDocument(MyUser u){
        return new Document(ELEMENT_ID, getNextId())
                .append(ELEMENT_USERNAME, u.getUsername())
                .append(ELEMENT_PASSWORD, u.getPassword())
                .append(ELEMENT_ROLES, u.getRoles());
    }


    /**
     * This method is used to get the next ID to be used in the database.
     * @return The next ID to be used.
     */
    @Override
    public int getNextId(){
        Document result = collection.find().sort(new Document(ELEMENT_ID, -1)).first();
        if (result == null) return 1;
        else return result.getInteger(ELEMENT_ID) + 1;
    }

    /**
     * This method is used to create a new user in the database.
     * @param user The user to be created.
     * @return The username of the user created.
     */
    @Override
    public String createUser(MyUser user) {

        try{
            Document userDocument = userToDocument(user);
            this.collection.insertOne(userDocument);
            return user.getUsername();
        }
        catch(MongoWriteException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method is used to get a user from the database.
     * @param username
     * @return
     */
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

    /**
     * This method is used to get all the users from the database.
     * @return An ArrayList of MyUser objects.
     */
    @Override
    public ArrayList<MyUser> getAllUsers() {
        ArrayList<MyUser> users = new ArrayList<>();
        for(Document current : this.collection.find()){
            MyUser u = userFromDocument(current);
            users.add(u);
        }
        return users;
    }

    /**
     * This method is used to delete a user from the database.
     * @param username The username of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
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

    /**
     * This method is used to change user's password
     * @param username is the user that want change password
     * @param password is the new password
     * @return True if the password was changed, False otherwise.
     */
    @Override
    public boolean updatePassword(String username, String password){

        Document filter = new Document(ELEMENT_USERNAME, username);
        Document update = new Document("$set", new Document(ELEMENT_PASSWORD, password));
        UpdateResult result = this.collection.updateOne(filter, update);

        if (result.getModifiedCount() > 0)
            return true;

        return false;
    }

    /**
     * This method closes the connection to the database.
     * @return True if the connection was closed, false otherwise.
     */
    @Override
    public boolean closeConnection() {
        mongoClient.close();
        return true;
    }
}

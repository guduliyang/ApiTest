package com.crazy.db;

import com.crazy.demo.Project;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ProjectDB {
    private static Logger logger = Logger.getLogger(ProjectDB.class);
    private static ToolMongo toolMongo = ToolMongo.getToolMongo();
    private static final MongoCollection<Document> COLLECTION = toolMongo.getCollection("project");

    public static void addProject(Project project){
        COLLECTION.insertOne(Document.parse(project.toJson()));
        logger.info("add project success");
    }

    public static Project findOneByID(String _id){
        String name;
        String address_www;
        String address_uat;
        Document filter = new Document();
        filter.put("_id",new ObjectId(_id));
        FindIterable<Document> iterable = COLLECTION.find(filter);
        name = iterable.first().getString("name");
        address_www = iterable.first().getString("address_www");
        address_uat = iterable.first().getString("address_uat");
        return new Project(_id,name,address_www,address_uat);
    }

    public static Project findOneByName(String name){
        String _id;
        String address_www;
        String address_uat;
        Document filter = new Document();
        filter.put("name",new ObjectId(name));
        FindIterable<Document> iterable = COLLECTION.find(filter);
        _id = iterable.first().getObjectId("_id").toString();
        address_www = iterable.first().getString("address_www");
        address_uat = iterable.first().getString("address_uat");
        return new Project(_id,name,address_www,address_uat);
    }

    public static List<Project> findAll(){
        final List<Project> projects = new ArrayList<>();
        FindIterable<Document> iterable = COLLECTION.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                String _id = document.getObjectId("_id").toString();
                String name = document.getString("name");
                String address_www = document.getString("address_www");
                String address_uat = document.getString("address_uat");
                projects.add(new Project(_id,name,address_www,address_uat));
            }
        });
        return projects;
    }

    public static Document dropByName(String name){
        Document filter = new Document();
        filter.put("name",name);
        return COLLECTION.findOneAndDelete(filter);
    }

    public static Document dropByID(String _id){
        Document filter = new Document();
        filter.put("_id",_id);
        return COLLECTION.findOneAndDelete(filter);
    }

    public static UpdateResult updateByName(String name, Project update){
        Document filter = new Document();
        filter.put("name", name);
        return COLLECTION.replaceOne(filter,Document.parse(update.toJson()));
    }

    public static UpdateResult updateByID(String _id, Project update){
        Document filter = new Document();
        filter.put("_id", new ObjectId(_id));
        return COLLECTION.replaceOne(filter,Document.parse(update.toJson()));
    }
}

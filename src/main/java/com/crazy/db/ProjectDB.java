package com.crazy.db;

import com.crazy.demo.Project;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProjectDB {
    private static Logger logger = Logger.getLogger(ProjectDB.class);
    private static ToolMongo toolMongo = ToolMongo.getToolMongo();
    private static final MongoCollection<Document> COLLECTION = toolMongo.getCollection("project");

    /**
     * 保存数据到数据库
     * @param project
     */
    public static void addProject(Project project){
        COLLECTION.insertOne(toDocument(project));
        logger.info("add project success");
    }

    /**
     * 根据ID查找一条数据
     * @param _id
     * @return
     */
    public static Project findOneByID(String _id){
        Document filter = new Document();
        filter.put("_id",new ObjectId(_id));
        FindIterable<Document> iterable = COLLECTION.find(filter);
        return toProject(iterable.first());
    }

    /**
     * 根据项目名称查询项目
     * @param name
     * @return
     */
    public static Project findOneByName(String name){
        Document filter = new Document();
        filter.put("name",new ObjectId(name));
        FindIterable<Document> iterable = COLLECTION.find(filter);
        return toProject(iterable.first());
    }

    /**
     * 返回所有数据
     * @return
     */
    public static List<Project> find(){
        final List<Project> projects = new ArrayList<>();
        FindIterable<Document> iterable = COLLECTION.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                projects.add(toProject(document));
            }
        });
        return projects;
    }

    /**
     * 删除一条数据
     * @param _id
     * @return
     */
    public static boolean dropByID(String _id){
        Document filter = new Document();
        filter.put("_id",new ObjectId(_id));
        return COLLECTION.deleteOne(filter).wasAcknowledged();
    }

    public static UpdateResult updateByName(String name, Project update){
        Document filter = new Document();
        filter.put("name", name);
        return COLLECTION.replaceOne(filter,toDocument(update));
    }

    /**
     * 通过ID更新数据
     * @param _id
     * @param update
     * @return
     */
    public static UpdateResult updateByID(String _id, Project update){
        Document filter = new Document();
        filter.put("_id", new ObjectId(_id));
        return COLLECTION.replaceOne(filter,toDocument(update));
    }

    private static Project toProject(Document document){
        String _id = document.getObjectId("_id").toString();
        String name = document.getString("name");
        String host_wuxia = document.getString("host_wuxia");
        String host_wuxib = document.getString("host_wuxib");
        String host_uat = document.getString("host_uat");
        return new Project(_id,name,host_wuxia,host_wuxib,host_uat);
    }

    private static Document toDocument(Project project){
        JSONObject object =project.toJson();
        if(object.has("_id"))object.remove("_id");
        return Document.parse(object.toString());
    }
}

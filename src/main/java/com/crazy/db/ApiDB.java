package com.crazy.db;

import com.crazy.demo.ApiInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiDB {
    private static ToolMongo toolMongo = ToolMongo.getToolMongo();
    private static MongoCollection<Document> projectCollection = toolMongo.getCollection("Api");;

    public static void addApi(ApiInfo apiInfo){
        projectCollection.insertOne(Document.parse(apiInfo.toJson()));
    }

    public static ApiInfo findByID(String _id){
        Document filter = new Document();
        filter.put("_id",new ObjectId(_id));
        FindIterable<Document> iterable = projectCollection.find(filter);
        return toApi(iterable.first());
    }

    public static ApiInfo findByName(String name,String project_name){
        Document filter = new Document();
        filter.put("name",name);
        filter.put("project_name",project_name);
        FindIterable<Document> iterable = projectCollection.find(filter);
        return toApi(iterable.first());
    }

    public static List<ApiInfo> findByProject(String project_name){
        final List<ApiInfo> apis = new ArrayList<>();
        Document filter = new Document();
        filter.put("project_name",project_name);
        FindIterable<Document> iterable = projectCollection.find(filter);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                apis.add(toApi(document));
            }
        });
        return apis;
    }

    public static void dropByID(String _id) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("_id", new ObjectId(_id));
        projectCollection.findOneAndDelete(dbObject);
    }

    public static boolean updateByID(String _id, ApiInfo update){
        BasicDBObject filter = new BasicDBObject();
        filter.put("_id", new ObjectId("_id"));
        return projectCollection.replaceOne(filter,Document.parse(update.toJson())).wasAcknowledged();
    }

    private static ApiInfo toApi(Document document){
         String _id = null;
         String name = null;
         String project_name = null;
         String requstUrl = null;
         List<String> params = new ArrayList<>();
         List<String> bodys = new ArrayList<>();
         String method = null;
         Map headers = new HashMap();
        JSONObject object = new JSONObject(document.toJson());
        _id = document.getObjectId("_id").toString();
        if(object.has("name")) name = document.getString("name");
        if(object.has("project_name")) project_name = document.getString("project_name");
        if(object.has("requstUrl")) requstUrl = document.getString("requstUrl");
        if(object.has("method")) method = document.getString("method");
        if(object.has("params")&&!object.get("params").equals(null)) {
            for (Object s : object.getJSONArray("params")){
                params.add(s.toString());
            }
        }

        if(object.has("bodys")&&!object.get("bodys").equals(null)) {
            for (Object s : object.getJSONArray("bodys")){
                bodys.add((String) s);
            }
        }
        if(object.has("headers")&&!object.get("headers").equals(null)) {
            JSONObject temp = null;
            for (String key : (temp = object.getJSONObject("headers")).keySet() ){
                headers.put(key,temp.getString(key));
            }
        }
        return new ApiInfo(_id,name,project_name,requstUrl,params,bodys,method,headers);
    }

}

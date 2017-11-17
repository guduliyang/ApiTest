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

    /**
     * 添加一条数据
     * @param apiInfo
     */
    public static void add(ApiInfo apiInfo){
        projectCollection.insertOne(toDocument(apiInfo));
    }


    /**
     * 根据ID查找一条数据
     * @param _id
     * @return
     */
    public static ApiInfo findByID(String _id){
        Document filter = new Document();
        filter.put("_id",new ObjectId(_id));
        FindIterable<Document> iterable = projectCollection.find(filter);
        return toApi(iterable.first());
    }

    /**
     * 查询所有数据
     * @return
     */
    public static List<ApiInfo> findAll(){
        final List<ApiInfo> apis = new ArrayList<>();
        FindIterable<Document> iterable = projectCollection.find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                apis.add(toApi(document));
            }
        });
        return apis;
    }

    /**
     * 根据项目名查询数据
     * @param project_name
     * @return
     */
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

    /**
     * 通过ID删除一条数据
     * @param _id
     */
    public static void dropByID(String _id) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("_id", new ObjectId(_id));
        projectCollection.findOneAndDelete(dbObject);
    }

    /**
     * 通过ID更新数据
     * @param _id
     * @param update
     * @return
     */
    public static boolean updateByID(String _id, ApiInfo update){
        BasicDBObject filter = new BasicDBObject();
        filter.put("_id", new ObjectId("_id"));
        return projectCollection.replaceOne(filter,toDocument(update)).wasAcknowledged();
    }

    public static boolean updateByProject(String old_name,String new_name){
        BasicDBObject filter = new BasicDBObject();
        filter.put("project_name", old_name);
        Document temp = new Document();
        temp.put("project_name",new_name);
        Document updata = new Document();
        updata.put("$set",temp);
        return projectCollection.updateMany(filter, updata).wasAcknowledged();
    }

    private static ApiInfo toApi(Document document){
         String _id = null;
         String name = null;
         String project_name = null;
         String requstUrl = null;
         List<String> params = new ArrayList<>();
         List<String> bodys = new ArrayList<>();
         String method = null;
         Map<String, String> headers = new HashMap<>();
        JSONObject object = new JSONObject(document.toJson());
        _id = document.getObjectId("_id").toString();
        if(object.has("name")) name = document.getString("name");
        if(object.has("project_name")) project_name = document.getString("project_name");
        if(object.has("url")) requstUrl = document.getString("url");
        if(object.has("method")) method = document.getString("method");
        if(object.has("params")&&!object.get("params").equals(null)) {
            for (Object s : object.getJSONArray("params")){
                params.add((String) s);
            }
        }
        if(object.has("bodys")&&!object.get("bodys").equals(null)) {
            for (Object s : object.getJSONArray("bodys")){
                bodys.add((String) s);
            }
        }
        if(object.has("headers")&&!object.get("headers").equals(null)) {
           JSONObject temp = object.getJSONObject("headers");
           for (String key:temp.keySet()){
               headers.put(key,temp.getString(key));
           }
        }
        return new ApiInfo(_id,name,project_name,requstUrl,params,bodys,method,headers);
    }

    private static Document toDocument(ApiInfo apiInfo){
        JSONObject object = new JSONObject(apiInfo.toJson());
        if(object.has("_id"))object.remove("_id");
        return Document.parse(object.toString());
    }
}

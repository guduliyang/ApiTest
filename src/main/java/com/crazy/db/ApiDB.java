package com.crazy.db;

import com.crazy.demo.ApiInfo;
import com.crazy.demo.Param;
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
     * 根据项目名接口名查询接口
     * @param name
     * @param project_name
     * @return
     */
    public static ApiInfo findByNames(String name,String project_name){
        Document filter = new Document();
//        filter.put("name",name);
        filter.put("project_name",project_name);
        FindIterable<Document> iterable = projectCollection.find(filter);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document);
            }
        });
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
        Document filter = new Document();
        filter.put("_id", new ObjectId(_id));
        projectCollection.findOneAndDelete(filter);
    }

    /**
     * 通过ID更新数据
     * @param _id
     * @param update
     * @return
     */
    public static boolean updateByID(String _id, ApiInfo update){
        Document filter = new Document();
        filter.put("_id", new ObjectId(_id));
        return projectCollection.replaceOne(filter,toDocument(update)).wasAcknowledged();
    }

    public static boolean updateByProject(String old_name,String new_name){
        Document filter = new Document();
        filter.put("project_name", old_name);
        Document temp = new Document();
        temp.put("project_name",new_name);
        Document updata = new Document();
        updata.put("$set",temp);
        return projectCollection.updateMany(filter, updata).wasAcknowledged();
    }

    private static ApiInfo toApi(Document document){
        if(document!=null){
            String _id = null;
            String name = null;
            String project_name = null;
            String requstUrl = null;
            List<Param> params = new ArrayList<>();
            List<Param> bodys = new ArrayList<>();
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
                    JSONObject temp = new JSONObject(s.toString());
                    params.add(new Param(temp.getString("name"),temp.getBoolean("necessary"),temp.getInt("type")));

                }
            }
            if(object.has("bodys")&&!object.get("bodys").equals(null)) {
                for (Object s : object.getJSONArray("bodys")){
                    JSONObject temp = new JSONObject(s.toString());
                    bodys.add(new Param(temp.getString("name"),temp.getBoolean("necessary"),temp.getInt("type")));

                }
            }
            if(object.has("headers")&&!object.get("headers").equals(null)) {
                JSONObject temp = object.getJSONObject("headers");
                for (String key:temp.keySet()){
                    headers.put(key,temp.getString(key));
                }
            }
            return new ApiInfo(_id,name,project_name,requstUrl,params,bodys,method,headers);
        }else {
            return null;
        }

    }

    private static Document toDocument(ApiInfo apiInfo){
        JSONObject object = new JSONObject(apiInfo.toJson());
        if(object.has("_id"))object.remove("_id");
        return Document.parse(object.toString());
    }

    public static void main(String[] args) {
        System.out.println(ApiDB.findByNames("access_report_token","DataApi"));
//        System.out.println(ApiDB.findByID("5a178ba7de30b64f88d444d5"));
        System.out.println(ApiDB.findByProject("DataApi"));
    }
}

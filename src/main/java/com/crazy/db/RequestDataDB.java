package com.crazy.db;

import com.crazy.demo.ApiInfo;
import com.crazy.demo.Project;
import com.crazy.demo.RequestData;
import com.mongodb.BasicDBObject;
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
import java.util.Map;

public class RequestDataDB {
    private static Logger logger = Logger.getLogger(RequestDataDB.class);
    private static ToolMongo toolMongo = ToolMongo.getToolMongo();
    private static MongoCollection<Document> COLLECTION = null;

    public static void addData(RequestData dataDB){
        COLLECTION = toolMongo.getCollection(dataDB.getProject_name());
        COLLECTION.insertOne(Document.parse(dataDB.toJson()));
    }

    public static List<RequestData> findByApi(ApiInfo apiInfo){
        COLLECTION = toolMongo.getCollection(apiInfo.getProject_name());
        final List<RequestData> requestDataList = new ArrayList<>();
        BasicDBObject filter = new BasicDBObject();
        filter.put("api_name",apiInfo.getName());
        filter.put("project_name",apiInfo.getProject_name());
        FindIterable<Document> iterable = COLLECTION.find(filter);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                requestDataList.add(toRequestData(document));
            }
        });
        return requestDataList;
    }

    public static void dropByID(String project_name,String _id) {
        COLLECTION = toolMongo.getCollection(project_name);
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("_id", new ObjectId(_id));
        COLLECTION.findOneAndDelete(dbObject);
    }

    public static UpdateResult updateByID(String project_name,String _id, RequestData update){
        COLLECTION = toolMongo.getCollection(project_name);
        Document filter = new Document();
        filter.put("_id", new ObjectId(_id));
        return COLLECTION.replaceOne(filter,Document.parse(update.toJson()));
    }

    private static RequestData toRequestData(Document document){
        JSONObject object = new JSONObject(document.toJson());
        String _id = document.getObjectId("_id").toString();
        String api_name = null;
        String project_name = null;
        String env = null;
        JSONObject bodys = null;
        Map<String,Object> params = null;
        if(object.has("api_name")) api_name = document.getString("api_name");
        if(object.has("project_name")) project_name = document.getString("project_name");
        if(object.has("env")) env = document.getString("env");
        if(object.has("bodys")){
            bodys = object.getJSONObject("bodys");
        }
        if(object.has("params")&&!object.get("params").equals(null)){
            params = object.getJSONObject("params").toMap();
        }
        return new RequestData(_id,api_name,project_name,env,bodys,params);
    }

    public static void main(String[] args) {
        RequestData requestData = new RequestData("access_report_token","DataApi");
        requestData.setEnv(RequestData.TESTING);
        requestData.addParam("client_secret","4j332l0dc0b43fgef2e32bf853e6ca0");
        requestData.addParam("access_token","cb50777091b641eaa23063fac667c984");
        requestData.addParam("name","马健锋");
        requestData.addParam("idCard","352225198811151010");
        RequestDataDB.addData(requestData);
    }
}

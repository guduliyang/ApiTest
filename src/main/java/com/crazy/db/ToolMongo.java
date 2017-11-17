package com.crazy.db;

import com.crazy.tools.NativePath;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

@Service("phoneBlacklistCache")
public class ToolMongo {
    private Logger logger = Logger.getLogger(ToolMongo.class);
    private static ToolMongo toolMongo = null;
    private final Properties PROPERTIES = new Properties();
    private String host;
    private int port;
    private String databaseName;
    private MongoClient client;
    private MongoDatabase database;


    private ToolMongo() {
        loadConf("conf/mongodb.properties");
        getClient(host,port);
        getDatabase(databaseName);
    }

    @PostConstruct
    public static ToolMongo getToolMongo() {
        if (toolMongo == null) {
            synchronized (ToolMongo.class) {
                if (toolMongo == null) {
                    toolMongo = new ToolMongo();
                }
            }
        }
        return toolMongo;
    }

    /**
     * 加载配置文件
     */
    private void loadConf(String name) {
        logger.info("加载mongodb配置文件");
        try {
            Path path = NativePath.get(name);
            InputStream inputStream = Files.newInputStream(NativePath.get(name), StandardOpenOption.READ);
            PROPERTIES.load(inputStream);
            host = PROPERTIES.getProperty("mongo.apiTest.host");
            port = Integer.parseInt(PROPERTIES.getProperty("mongo.apiTest.port"));
            databaseName = PROPERTIES.getProperty("mongo.apiTest.databaseName");
            logger.info("mongo.apiTest.host:" + host);
            logger.info("mongo.apiTest.port:" + port);
            logger.info("mongo.apiTest.databaseName:" + databaseName);
        } catch (IOException e) {
            logger.error("加载mongo数据库配置信息异常：" + e.toString());
        }
    }

    /**
     * 获取客户端
     * @param ip
     * @param port
     * @return
     */
    private void getClient(String ip,int port){
        logger.info("创建 mongodb 连接池" + ip + ":" + port);
        try {
            client = new MongoClient(ip, port);
        } catch (NumberFormatException e) {
            logger.error("获取 mongo db异常" + e.getMessage());
        }
    }

    /**
     * 连接数据库
     * @param databaseName
     */
    private void getDatabase(String databaseName){
        logger.info("连接数据库：".concat(databaseName));
        database = client.getDatabase(databaseName);
    }

    /**
     * 获取数据表
     * @param collectionName
     * @return
     */
    public MongoCollection<Document> getCollection(String collectionName){
        return database.getCollection(collectionName);
    }

}

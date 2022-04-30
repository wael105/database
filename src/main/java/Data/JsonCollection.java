package Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.*;

public class JsonCollection implements Serializable {
    private final Map<String, JsonNode> map;
    private final ObjectMapper mapper;
    private final String name;
    private int num;
    private final IndexCreator indexCreator;

    JsonCollection(String name){
        this.name = name;
        map = new HashMap<>();
        mapper = new ObjectMapper();
        num = 0;
        indexCreator = new IndexCreator();
    }

    public void insert(String json) throws JsonProcessingException {
        String wrappedJson = wrapID(json);
        JsonNode jsonNode = mapper.readTree(wrappedJson);
        map.putIfAbsent(String.valueOf(num), jsonNode);
        indexCreator.indexJson(jsonNode);
        num++;
    }

    public List<JsonNode> get(String property, String attribute){
        return indexCreator.getIndexes().get(property).get(attribute);
    }

    public void makeIndex(String property){
        Collection<JsonNode> nodes = map.values();
        indexCreator.makeIndex(property, nodes);
    }

    private String wrapID(String unwrappedJson){
        return "{\"_id\":\"" + num + "\"," + unwrappedJson.substring(1);
    }

    public String getName(){
        return name;
    }
}

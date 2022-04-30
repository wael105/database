package Data;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.*;

public class IndexCreator implements Serializable {
    private final Map<String, Map<String, ArrayList<JsonNode>>> indexes;

    public IndexCreator(){
        indexes = new HashMap<>();
    }

    public void makeIndex(String property, Collection<JsonNode> nodes){
        indexes.putIfAbsent(property, new HashMap<>());

        for(JsonNode node: nodes){
            addIndex(property, node);
        }
    }

    public void addIndex(String property, JsonNode node){
        if (node.has(property)) {
            indexes.get(property).put(node.get(property).asText(), new ArrayList<>());
            indexes.get(property).get(node.get(property).asText()).add(node);
        }
    }

    public void indexJson(JsonNode node){
        Set<String> properties = getProperties();
        for (String property : properties) {
              addIndex(property, node);
        }
    }

    private Set<String> getProperties(){
        return indexes.keySet();
    }

    protected Map<String, Map<String, ArrayList<JsonNode>>> getIndexes(){
        return indexes;
    }

}

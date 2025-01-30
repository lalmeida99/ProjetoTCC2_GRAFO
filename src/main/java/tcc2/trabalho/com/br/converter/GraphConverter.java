package tcc2.trabalho.com.br.converter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import tcc2.trabalho.com.br.node.Node;

public class GraphConverter {

    public static JsonObject graphToJson(Graph<Node, DefaultEdge> graph) {
        // Cria um mapa para organizar a hierarquia
        Map<String, Object> root = new HashMap<>();
        root.put("name", "root");
        List<Map<String, Object>> children = new ArrayList<>();

        graph.vertexSet().forEach(node -> {
            Map<String, Object> child = new HashMap<>();
            child.put("name", node.getId());
            child.put("category", node.getCategory());
            child.put("children", new ArrayList<>()); // Inicializa sem filhos
            children.add(child);
        });

        root.put("children", children);

        // Converte o mapa em JSON
        return new Gson().toJsonTree(root).getAsJsonObject();
    }
}
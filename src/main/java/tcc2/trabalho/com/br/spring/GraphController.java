package tcc2.trabalho.com.br.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tcc2.trabalho.com.br.converter.GraphConverter;
import tcc2.trabalho.com.br.node.Node;

import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

@RestController
@RequestMapping("/api")
public class GraphController {

    /*
     * 
     * Endpoint para retornar dados.

     */
    @GetMapping("/hierarchy")
    public Map<String, Object> getHierarchicalData() {
        return DataScraper.createHierarchicalData();
    }
}
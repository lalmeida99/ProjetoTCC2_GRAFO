package tcc2.trabalho.com.br.view;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import com.mxgraph.layout.mxOrganicLayout;

import javax.swing.*;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

public class GraphVisualization {

    public static void createAndShowGui(Graph<String, DefaultEdge> graph) {
        // Diagnóstico básico
    	if (graph.vertexSet().isEmpty() || graph.edgeSet().isEmpty()) {
    	    System.err.println("Grafo está vazio! Verifique os dados.");
    	    return;
    	}
    	
        if (graph == null || graph.vertexSet().isEmpty()) {
            System.err.println("O grafo está vazio ou nulo. Certifique-se de que os dados estão sendo carregados corretamente.");
            return;
        }

        System.out.println("Número de vértices: " + graph.vertexSet().size());
        System.out.println("Número de arestas: " + graph.edgeSet().size());

        // Configuração da janela principal
        JFrame frame = new JFrame("Knowledge Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria o adaptador do grafo para o JGraphX
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        frame.add(graphComponent);

        // Estilos dos vertices
        graphAdapter.getStylesheet().getDefaultVertexStyle().put("fontSize", 12);
        graphAdapter.getStylesheet().getDefaultVertexStyle().put("shape", "ellipse");
        graphAdapter.getStylesheet().getDefaultVertexStyle().put("strokeColor", "#000");
        graphAdapter.getStylesheet().getDefaultVertexStyle().put("fillColor", "#9ACD32");

        // Aplica layout cicular
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // Ajust o zoom 
        graphComponent.zoomAndCenter();

        // Exibe a janela
        SwingUtilities.invokeLater(() -> {
            frame.setSize(new Dimension(800, 600));
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
    }

    // Mteodo principal para tefste
    public static void main(String[] args) {
        Graph<String, DefaultEdge> testGraph = new SimpleGraph<>(DefaultEdge.class);
        testGraph.addVertex("A");
        testGraph.addVertex("B");
        testGraph.addVertex("C");
        testGraph.addEdge("A", "B");
        testGraph.addEdge("B", "C");
        testGraph.addEdge("C", "A");

        createAndShowGui(testGraph);
    }
}
package tcc2.trabalho.com.br.spring;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tcc2.trabalho.com.br.node.Node;
import tcc2.trabalho.com.br.view.GraphVisualization;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import javax.swing.SwingUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DataScraper {

    /**
     * M�todo principal para criar a hierarquia de doen�as e sintomas.
     * @return Um mapa representando a hierarquia no formato esperado pelo frontend.
     */
    public static Map<String, Object> createHierarchicalData() {
        String url = "https://people.dbmi.columbia.edu/~friedma/Projects/DiseaseSymptomKB/index.html";
        List<Map<String, Object>> diseases = new ArrayList<>(); // Lista de doen�as com sintomas

        try {
            // Realiza o scraping do site com User-Agent
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                    .get();

            // Seleciona todas as linhas da tabela
            Elements rows = doc.select("table tr");

            // Ignora a primeira linha (cabe�alho), se necess�rio
            String currentDisease = null;
            List<String> currentSymptoms = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() >= 3) { // Garante que a linha tem ao menos 3 colunas
                    String disease = formatName(cols.get(0).text().trim()); // Nome da doen�a com identificador
                    String symptoms = cols.get(2).text().trim(); // Sintomas associados com identificador

                    // Verifica e processa m�ltiplas doen�as
                    String[] diseaseParts = disease.split("\\^");
                    for (String singleDisease : diseaseParts) {
                        singleDisease = formatName(singleDisease.trim());
                        if (singleDisease.isEmpty() || singleDisease.equals("UMLS:")) {
                            continue; // Ignora doen�as inv�lidas
                        }

                        // Se a doen�a mudou, salva a anterior e reinicia a lista de sintomas
                        if (!singleDisease.equals(currentDisease)) {
                            if (currentDisease != null && !currentSymptoms.isEmpty()) {
                                diseases.add(createDiseaseNode(currentDisease, currentSymptoms)); // Adiciona a doen�a anterior
                            }
                            currentDisease = singleDisease; // Atualiza para a nova doen�a
                            currentSymptoms = new ArrayList<>();
                        }
                    }

                    // Adiciona os sintomas � lista de sintomas da doen�a atual
                    if (!symptoms.isEmpty()) {
                        for (String symptom : symptoms.split("\\^")) { // Sintomas separados por "^"
                            symptom = formatName(symptom.trim());
                            if (!symptom.isEmpty() && !symptom.equals("UMLS:")) {
                                currentSymptoms.add(symptom); // Adiciona o sintoma
                            }
                        }
                    }
                }
            }

            // Adiciona a �ltima doen�a ap�s o loop
            if (currentDisease != null && !currentSymptoms.isEmpty()) {
                diseases.add(createDiseaseNode(currentDisease, currentSymptoms));
            }

        } catch (IOException e) {
            System.err.println("Erro ao conectar ou processar o site: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

        // Estrutura final com a raiz
        Map<String, Object> root = new HashMap<>();
        root.put("diseases", diseases); // Lista de doen�as

        return root;
    }

    /**
     * M�todo para criar o n� da doen�a com seus sintomas.
     * @param disease Nome da doen�a.
     * @param symptoms Lista de sintomas.
     * @return Um mapa representando a doen�a e seus sintomas.
     */
    private static Map<String, Object> createDiseaseNode(String disease, List<String> symptoms) {
        Map<String, Object> diseaseNode = new HashMap<>();
        diseaseNode.put("disease", disease); // Nome da doen�a
        diseaseNode.put("symptoms", symptoms); // Lista de sintomas
        return diseaseNode;
    }

    /**
     * M�todo para formatar o nome da doen�a e sintoma com o prefixo UMLS.
     * @param original O nome original a ser formatado.
     * @return O nome formatado com "UMLS:" ou uma string vazia caso seja inv�lido.
     */
    private static String formatName(String original) {
        if (original == null || original.isEmpty() || original.equals("UMLS:")) {
            return ""; // Retorna vazio para entradas inv�lidas
        }

        // Certifique-se de que o nome come�a com "UMLS:"
        if (original.startsWith("UMLS:")) {
            return original.trim();
        }

        // Se o nome n�o tem o prefixo, tenta adicionar "UMLS:" corretamente
        String[] parts = original.split("_", 2);
        if (parts.length == 2) {
            return "UMLS:" + parts[0].trim() + "_" + parts[1].trim();
        }

        return "UMLS:" + original.trim(); // Se n�o tiver "_", adiciona o prefixo de forma simples
    }

    public static void main(String[] args) {
        Map<String, Object> hierarchicalData = createHierarchicalData();

        // Imprime a estrutura hier�rquica no console para depura��o
        hierarchicalData.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }
}
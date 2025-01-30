package tcc2.trabalho.com.br.node;

import java.util.Objects;

public class Node {
	
    private String id;
    private String category;

    public Node(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return id.equals(node.id) && category.equals(node.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}
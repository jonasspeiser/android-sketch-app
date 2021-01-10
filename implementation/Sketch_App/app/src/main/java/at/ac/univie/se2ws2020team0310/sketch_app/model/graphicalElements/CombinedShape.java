package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

/**
 * Implementation of the Composite Pattern to create a new Shape consisting of a List of Graphical Elements
 */
public class CombinedShape extends GraphicalElement {

    private final List<GraphicalElement> elements;
    private String name;
    private int id;

    public CombinedShape(DrawStrategy drawStrategy, int id) {
        this(drawStrategy, "CombiShape", id);
    }

    public CombinedShape(DrawStrategy drawStrategy, String name, int id) {
        super(drawStrategy);
        this.elements = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean isWithinElement(float x, float y) {
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(GraphicalElement element) {
        if (!this.elements.contains(element)) {
            this.elements.add(element);
        }
    }

    public void remove(GraphicalElement element) {
        this.elements.remove(element);
    }

    public List<GraphicalElement> getElements() {
        return this.elements;
    }

    @Override
    public String toString() {
        return "Combined Shape: " + this.getName();
    }

    public int getElementsCount() {
        return this.elements.size();
    }

    public int getId() {
        return id;
    }
}

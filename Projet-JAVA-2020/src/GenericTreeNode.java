import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericTreeNode<E> {

    private E data;
    private int key;
    private List<GenericTreeNode<E>> children;
    private GenericTreeNode<E> parent;

    public GenericTreeNode() {
        super();
        children = new ArrayList<>();
    }

    public GenericTreeNode(E data) {
        this();
        setData(data);
    }

    public GenericTreeNode<E> getParent() {
        return this.parent;
    }

    public List<GenericTreeNode<E>> getChildren() {
        return this.children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(List<GenericTreeNode<E>> children) {
        for(GenericTreeNode<E> child : children) {
            child.parent = this;
        }

        this.children = children;
    }

    public void addChild(GenericTreeNode<E> child) {
        child.parent = this;
        children.add(child);
    }

    public void addChildAt(int index, GenericTreeNode<E> child) throws IndexOutOfBoundsException {
        child.parent = this;
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public GenericTreeNode<E> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String toString() {
        return getData().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GenericTreeNode<?> other = (GenericTreeNode<?>) obj;
        if (data == null) {
            return other.data == null;
        } else return data.equals(other.data);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    public String toStringVerbose() {
        StringBuilder stringRepresentation = new StringBuilder(getData().toString() + ":[");

        for (GenericTreeNode<E> node : getChildren()) {
            stringRepresentation.append(node.getData().toString()).append(", ");
        }

        //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation.toString());

        stringRepresentation = new StringBuilder(matcher.replaceFirst(""));
        stringRepresentation.append("]");

        return stringRepresentation.toString();
    }
}
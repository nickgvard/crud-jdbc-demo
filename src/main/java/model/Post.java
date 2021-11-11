package model;

import java.sql.Timestamp;
import java.util.List;

public class Post extends BaseEntity {

    private final String content;
    private final Timestamp created;
    private Timestamp updated;
    private List<Label> labels;

    public Post(String content, Timestamp created) {
        this.content = content;
        this.created = created;
    }

    public Post(long id, String content, Timestamp created, Timestamp updated) {
        super(id);
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public String content() {
        return content;
    }

    public Timestamp created() {
        return created;
    }

    public Timestamp updated() {
        return updated;
    }

    public List<Label> labels() {
        return labels;
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

    @Override
    public String toString() {
        return "Post {" +
                "content='" + content + '\'' +
                ", created=" + created + '\'' +
                ", updated=" + updated + '\'' +
                '}';
    }
}
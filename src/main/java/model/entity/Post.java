package model.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class Post extends BaseEntity {

    private final String content;
    private final Timestamp created;
    private final Timestamp updated;
    private final List<Label> labels;

    public Post(long id, String content, Timestamp created, Timestamp updated, List<Label> labels) {
        super(id);
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
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
}

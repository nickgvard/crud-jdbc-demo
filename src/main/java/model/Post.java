package model;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

@ToString
@SuperBuilder
public class Post extends BaseEntity {

    @Getter private final String content;
    @Getter private Timestamp created;
    @Getter private Timestamp updated;
    @Getter private List<Label> labels;

    public void addLabel(Label label) {
        labels.add(label);
    }
}
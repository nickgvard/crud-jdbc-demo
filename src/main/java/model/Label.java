package model;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

@ToString
@Getter
@SuperBuilder
public class Label extends BaseEntity {
    private final String name;
}
package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class BaseEntity {
    private long id;
}
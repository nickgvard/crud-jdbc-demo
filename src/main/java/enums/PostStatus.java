package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */
@AllArgsConstructor
@Getter
public enum PostStatus {
    ACTIVE(1), UNDER_REVIEW(2), DELETED(3);

    private final long statusId;
}

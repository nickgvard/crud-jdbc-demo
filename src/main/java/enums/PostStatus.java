package enums;

/**
 * @author Nikita Gvardeev
 * 13.11.2021
 */

public enum PostStatus {
    ACTIVE(1), UNDER_REVIEW(2), DELETED(3);

    private final long statusId;

    PostStatus(long statusId) {
        this.statusId = statusId;
    }

    public long statusId() {
        return statusId;
    }
}

package model.enums;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
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

package view;

import enums.PostStatus;

import java.util.List;

public class PostStatusView {

    public void showAllPostStatus(List<PostStatus> postStatuses) {
        System.out.println("----------------------------------------------------");
        System.out.println("ALL POST STATUS FROM DATA BASE");
        if(!postStatuses.isEmpty()) {
            for (PostStatus postStatus : postStatuses) {
                System.out.println(postStatus.name().toLowerCase());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
            }
            System.out.println("----------------------------------------------------");
        }else
            System.out.println("Post statuses is empty");
    }
}

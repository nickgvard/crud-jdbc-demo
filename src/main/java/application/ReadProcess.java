package application;

import controller.LabelController;
import controller.PostController;
import controller.PostStatusController;
import controller.WriterController;

public class ReadProcess extends Process {

    public void read(String action) {
        switch (action) {
            case "5":
                new WriterController().updateWriterView();
                break;
            case "6":
                new PostController().updatePostView();
                break;
            case "7":
                new LabelController().updateLabelView();
                break;
            case "8":
                new PostStatusController().updatePostStatusView();
                break;
            default:
                throw new RuntimeException("No such action");
        }
    }
}

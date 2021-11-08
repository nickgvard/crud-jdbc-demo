package application;

import controller.LabelStatement;
import controller.PostStatement;
import controller.PostStatusStatement;
import controller.WriterStatement;

public class ReadProcess extends Process {

    public void read(String action) {
        switch (action) {
            case "5":
                new WriterStatement().updateWriterView();
                break;
            case "6":
                new PostStatement().updatePostView();
                break;
            case "7":
                new LabelStatement().updateLabelView();
                break;
            case "8":
                new PostStatusStatement().updatePostStatusView();
                break;
            default:
                throw new RuntimeException("No such action");
        }
    }
}

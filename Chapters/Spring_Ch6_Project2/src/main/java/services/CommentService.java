package services;

import aspects.ToLog;
import aspects.ToLog2;
import model.Comment;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {

    private Logger logger = Logger.getLogger(CommentService.class.getName());

    @ToLog2
    public String publishComment(Comment comment) {
        logger.info("Publishing comment:" + comment.getText() + "\n");
        return "Success";
    }

    @ToLog
    public void deleteComment(Comment comment) {
        logger.info("Deleting comment:" + comment.getText());
    }

    public void editComment(Comment comment) {
        logger.info("Editing comment:" + comment.getText());
    }
}

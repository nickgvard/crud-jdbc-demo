package repository;

import model.entity.Writer;
import utils.database.DataBaseSource;

/**
 * @author Nikita Gvardeev 01.11.2021
 * email gvardeev@po-korf.ru
 */
public class WriterRepo {

    private final Writer writer;
    private final DataBaseSource dataSource;

    public WriterRepo(Writer writer) {
        this.writer = writer;
        dataSource = new DataBaseSource();
    }

    public void createWriter() {
        dataSource.createData();
    }

    public Writer writer() {
        return (Writer) dataSource.data();
    }

    public void updateWriter() {
        dataSource.updateData();
    }

    public void deleteWriter() {
        dataSource.deleteData();
    }
}

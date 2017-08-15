package duplicate_results;

import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Set {
    @Id
    long id;

    @Backlink
    public List<Element> elements;
}

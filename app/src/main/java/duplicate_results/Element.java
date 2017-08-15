package duplicate_results;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Element {

    @Id
    public long id;

    public ToOne<Set> set;
    public ToOne<ElementAttributes> attributes;
}

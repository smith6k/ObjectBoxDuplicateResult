package duplicate_results;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "testing";

    BoxStore boxStore;
    Box<Set> setBox;
    Box<Element> elementBox;
    Box<ElementAttributes> attributesBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boxStore = MyObjectBox.builder().androidContext(this).build();
        setBox = boxStore.boxFor(Set.class);
        elementBox = boxStore.boxFor(Element.class);
        attributesBox = boxStore.boxFor(ElementAttributes.class);

        for (Class aClass : boxStore.getAllEntityClasses()) {
            boxStore.boxFor(aClass).removeAll();
        }

        long setId = createEntities();
        logEntities();
        logSet(setId);

        createEntities();

        logEntities();
        logSet(setId);
    }

    private long createEntities() {
        Log.i(TAG, "===============================");
        Log.i(TAG, "create one of each entity");

        Set set = new Set();
        Element element = new Element();
        ElementAttributes attributes = new ElementAttributes();

        element.set.setTarget(set);
        element.attributes.setTarget(attributes);
        elementBox.put(element);
        return element.set.getTargetId();
    }

    private void logEntities() {
        Log.i(TAG, "total Set entities: " + setBox.count());
        Log.i(TAG, "total Element entities: " + elementBox.count());
        Log.i(TAG, "total ElementAttributes entities: " + attributesBox.count());
    }

    private void logSet(long setId) {
        Log.i(TAG, "load set with id: " + setId);
        Set set = setBox.get(setId);

        Log.i(TAG, "set has #elements: " + set.elements.size());
        for (Element element1 : setBox.get(setId).elements) {
            Log.i(TAG, "set.elementId: " + element1.id);
        }
    }

    @Override
    protected void onDestroy() {
        boxStore.close();
        super.onDestroy();
    }
}

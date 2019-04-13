package tests;

import main.model.Model;

// Do not want this class instantiated.
public abstract class AbstractTestSetup {
    
    // Common file paths and model
    protected static final String LOAD_ATTRIBUTE_TEXT_PATH = "res/testdata/testattributes.txt";
    protected static final String LOAD_SEARCHABLE_TEXT_PATH = "res/testdata/testanimals.txt";
    protected static final String SAVE_FILE_BIN_PATH = "res/testdata/test.bin";
    protected static final String UNKNOWN = "UNKNOWN";
    protected static Model model;
    
    // Create an empty model
    protected static void setupModelNoData() {
        model = new Model();
    }
    
    // Create a model and import data
    protected static void setupModelWithData() {
        model = new Model();
        model.loadAttributes(LOAD_ATTRIBUTE_TEXT_PATH);
        model.loadSearchables(LOAD_SEARCHABLE_TEXT_PATH);
    }
}

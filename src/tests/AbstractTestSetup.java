package tests;

import main.model.Model;

/**
 * Abstract class for setting up the model before running tests
 */
public abstract class AbstractTestSetup {
    
    // Constant file names
    protected static final String RES_PATH = "res";
    protected static final String LOAD_ATTRIBUTE_TEXT_PATH = RES_PATH + "/testdata/testattributes.txt";
    protected static final String LOAD_SEARCHABLE_TEXT_PATH = RES_PATH + "/testdata/testanimals.txt";
    protected static final String SAVE_FILE_BIN_PATH = RES_PATH + "/testdata/test.bin";
    
    protected static Model model;
    
    /**
     * Create an empty model
     */
    protected static void setupModelNoData() {
        model = new Model();
        model.clearSearchables();
        model.clearAttributes();
    }
    
    /**
     * Create a model and import Data
     */
    protected static void setupModelWithData() {
        model = new Model();
        model.loadAttributes(LOAD_ATTRIBUTE_TEXT_PATH);
        model.loadSearchables(LOAD_SEARCHABLE_TEXT_PATH);
    }
}

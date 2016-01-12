package org.fxpart.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by metairie on 13.08.2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AutosuggestFxIsAcceptFreeValueTest.class
        , AutosuggestFxNoItemTest.class
        , AutosuggestFxWithItemTest.class
        // TODO remove free value test, AutosuggestFxIsNOTAcceptFreeValueTest.class
})
public class AllTest {

}


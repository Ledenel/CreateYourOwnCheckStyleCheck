package com.blundell.checks;


import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeckardCheckTest extends CheckerTest {

    private static final int AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS = 6;

    @Test
    public void testIfs() throws Exception {
        Checker checker = prepareCheckStyleChecker(Collections.emptyMap());
        List<File> files = prepareFilesToBeChecked();
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS));
    }

}

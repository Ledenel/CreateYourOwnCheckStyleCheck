package com.blundell.checks;


import cn.beihangsoft.checks.DeckardCheck;
import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeckardCheckTest extends CheckerTest<DeckardCheck> {

    private static final int AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS = 6;

    public DeckardCheckTest() {
        super(DeckardCheck.class);
    }

    @Test
    public void testIfs() throws Exception {
        Checker checker = prepareCheckStyleChecker(Collections.emptyMap());
        List<File> files = prepareFilesToBeChecked();
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS));
    }

}

package com.blundell.checks;


import com.puppycrawl.tools.checkstyle.Checker;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AntiHungarianCheckShould extends CheckerTest<AntiHungarianCheck> {

    private static final int AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS = 6;

    public AntiHungarianCheckShould() {
        super(AntiHungarianCheck.class);
    }

    @Test
    public void ignore_local_variables() throws Exception {
        Checker checker = prepareCheckStyleChecker(Map.of(
                "regex","m[A-Z0-9].*"
        ));
        List<File> files = prepareFilesToBeChecked();
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS));
    }

}

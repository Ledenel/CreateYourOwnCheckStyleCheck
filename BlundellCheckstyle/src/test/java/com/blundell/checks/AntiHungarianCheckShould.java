package com.blundell.checks;


import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AntiHungarianCheckShould extends CheckerTest {

    private static final int AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS = 6;

    @Test
    public void ignore_local_variables() throws Exception {
        Checker checker = prepareCheckStyleChecker();
        List<File> files = prepareFilesToBeChecked();
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS));
    }

}

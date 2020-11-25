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

public class AntiHungarianCheckShould {

    private static final int AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS = 6;

    @Test
    public void ignore_local_variables() throws Exception {
        Checker checker = prepareCheckStyleChecker();
        List<File> files = prepareFilesToBeChecked();
        int numberOfErrors = checker.process(files);
        assertThat(numberOfErrors, is(AMOUNT_OF_HUNGARIAN_MEMBER_ERRORS));
    }

    private Checker prepareCheckStyleChecker() throws CheckstyleException {
        Checker checker = new Checker();
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        checker.configure(prepareConfiguration());
        return checker;
    }

    private DefaultConfiguration prepareConfiguration() {
        DefaultConfiguration checks = new DefaultConfiguration("Checks");
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        DefaultConfiguration antiHungarian = new DefaultConfiguration(AntiHungarianCheck.class.getCanonicalName());
        checks.addChild(treeWalker);
        treeWalker.addChild(antiHungarian);
        return checks;
    }

    private List<File> prepareFilesToBeChecked() {
        // FIXED do not use fixed testFileName, add flexible method to load resources (automatically read .java files under resources/com.blundell.checks)
        URL all = getClass().getResource(".");
        // there will be .class files (Test.class) in it, so get rid of it
        File[] files = new File(all.getFile()).listFiles((dir, name) -> name.endsWith(".java"));
        return Arrays.asList(Optional.ofNullable(files).orElse(new File[0]));
    }

}

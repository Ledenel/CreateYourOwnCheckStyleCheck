package com.blundell.checks;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckerTest {
    protected Checker prepareCheckStyleChecker() throws CheckstyleException {
        Checker checker = new Checker();
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        checker.configure(prepareConfiguration());
        return checker;
    }

    private DefaultConfiguration prepareConfiguration() {
        DefaultConfiguration checks = new DefaultConfiguration("Checks");
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        DefaultConfiguration checkerConfig = new DefaultConfiguration(AntiHungarianCheck.class.getCanonicalName());
        checks.addChild(treeWalker);
        treeWalker.addChild(checkerConfig);
        // TODO add config (maybe via checkerConfig.addChild or addProperty)
        return checks;
    }

    protected List<File> prepareFilesToBeChecked() {
        // FIXED do not use fixed testFileName, add flexible method to load resources (automatically read .java files under resources/com.blundell.checks)
        // FIXME: WARNING! getClass() is now CheckerTest.class, getResources(".") may vary
        URL all = getClass().getResource(".");
        // there will be .class files (Test.class) in it, so get rid of it
        File[] files = new File(all.getFile()).listFiles((dir, name) -> name.endsWith(".java"));
        return Arrays.asList(Optional.ofNullable(files).orElse(new File[0]));
    }
}

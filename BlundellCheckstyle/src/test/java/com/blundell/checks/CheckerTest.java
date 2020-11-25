package com.blundell.checks;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.io.File;
import java.net.URL;
import java.util.*;

public abstract class CheckerTest<Chk> {

    private Class<Chk> checkerClass;

    protected CheckerTest(Class<Chk> checkerClass) {
        this.checkerClass = checkerClass;
    }

    protected Checker prepareCheckStyleChecker(Map<String, String> attrs) throws CheckstyleException {
        Checker checker = new Checker();
        checker.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        checker.configure(prepareConfiguration(attrs));
        return checker;
    }

    private DefaultConfiguration prepareConfiguration(Map<String, String> attrs) {
        DefaultConfiguration checks = new DefaultConfiguration("Checks");
        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        DefaultConfiguration checkerConfig = new DefaultConfiguration(checkerClass.getCanonicalName());
        checks.addChild(treeWalker);
        treeWalker.addChild(checkerConfig);
        for (Map.Entry<String, String> attr : attrs.entrySet()) {
            checkerConfig.addAttribute(attr.getKey(), attr.getValue());
        }
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

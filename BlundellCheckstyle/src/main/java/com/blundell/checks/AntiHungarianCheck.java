package com.blundell.checks;

import com.puppycrawl.tools.checkstyle.api.*;

import java.util.regex.Pattern;

public class AntiHungarianCheck extends AbstractCheck {

    public String getRegex() {
        return regex;
    }

    /**
     * used by Checkstyle. customize test
     * @param regex the regex for var name to test
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    private String regex;

    @Override
    public int[] getDefaultTokens() {
        return new int[] {TokenTypes.VARIABLE_DEF};
    }

    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public void visitToken(DetailAST aAST) {
        DetailAST identifier = aAST.findFirstToken(TokenTypes.IDENT);
        String variableName = identifier.toString();
        if (aAST.getParent().getType() == TokenTypes.OBJBLOCK && Pattern.compile(regex).matcher(variableName).matches()) {
            this.log(aAST.getLineNo(), ("Hungarian notation belongs in the 90's. " +
                    "Don't prefix member variables with 'm'. " +
                    "Use your IDE's shiny colors. Culprit was: ") + variableName);
        }
    }

}

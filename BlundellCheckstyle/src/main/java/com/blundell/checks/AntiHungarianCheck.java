package com.blundell.checks;

import com.puppycrawl.tools.checkstyle.api.*;

import java.util.regex.Pattern;

public class AntiHungarianCheck extends AbstractCheck {

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
        if (aAST.getParent().getType() == TokenTypes.OBJBLOCK && Pattern.compile("m[A-Z0-9].*").matcher(variableName).matches()) {
            this.log(aAST.getLineNo(), ("Hungarian notation belongs in the 90's. " +
                    "Don't prefix member variables with 'm'. " +
                    "Use your IDE's shiny colors. Culprit was: ") + variableName);
        }
    }

}

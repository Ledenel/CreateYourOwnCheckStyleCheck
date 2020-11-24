package com.blundell.checks;

import com.puppycrawl.tools.checkstyle.api.*;

public class AntiHungarianCheck extends AbstractCheck {

    private final HungarianNotationMemberDetector detector = new HungarianNotationMemberDetector();

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
        if (aAST.getParent().getType() == TokenTypes.OBJBLOCK && detector.detectsNotation(variableName)) {
            this.log(aAST.getLineNo(), ("Hungarian notation belongs in the 90's. " +
                    "Don't prefix member variables with 'm'. " +
                    "Use your IDE's shiny colors. Culprit was: ") + variableName);
        }
    }

}

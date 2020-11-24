package cn.beihangsoft.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DeckardCheck extends AbstractCheck {
    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getAcceptableTokens();
    }

    private HashMap<String, Integer> AST_ID_MAP;

    @Override
    public void init() {
        AST_ID_MAP = new HashMap<>();
        for (Field field:
             Arrays.stream(TokenTypes.class.getDeclaredFields())
                .filter(field -> {
                    int modifiers = field.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && field.getType().isAssignableFrom(int.class);
                }).collect(Collectors.toList())) {
            try {
                AST_ID_MAP.put(field.getName(), field.getInt(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(AST_ID_MAP);
    }

    @Override
    public void visitToken(DetailAST ast) {

    }
}

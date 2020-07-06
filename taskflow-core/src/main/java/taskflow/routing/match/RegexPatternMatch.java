package taskflow.routing.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author steven.zhu 2020/7/4 12:31.
 * @类描述：
 */
public class RegexPatternMatch implements PatternMatch {
    @Override
    public boolean isMatched(String pattern, String value) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(value);
        return matcher.matches();
    }
}

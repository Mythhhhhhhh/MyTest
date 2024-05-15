package cn.myth.guava.string;

import com.google.common.base.CaseFormat;
import org.junit.Test;

public class CaseFormatTest {

    /**
     * LOWER_CAMEL	lowerCamel
     * LOWER_HYPHEN	lower-hyphen
     * LOWER_UNDERSCORE	lower_underscore
     * UPPER_CAMEL	UpperCamel
     * UPPER_UNDERSCORE	UPPER_UNDERSCORE
     */

    @Test
    public void case_format() {
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"FIRSTNAME"));// firstname
    }
}

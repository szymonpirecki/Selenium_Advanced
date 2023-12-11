package configuration.assertJConfig;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import java.math.BigDecimal;
import java.util.Comparator;

public class AssertJConfigHelper {

    public static RecursiveComparisonConfiguration getBigDecimalComparisonConfig() {
        return RecursiveComparisonConfiguration.builder()
                .withComparatorForType(new Comparator<BigDecimal>() {
                    @Override
                    public int compare(BigDecimal bd1, BigDecimal bd2) {
                        return bd1.stripTrailingZeros().compareTo(bd2.stripTrailingZeros());
                    }
                }, BigDecimal.class)
                .build();
    }

    public static Comparator<BigDecimal> bigDecimalComparator() {
        return new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal bd1, BigDecimal bd2) {
                return bd1.stripTrailingZeros().compareTo(bd2.stripTrailingZeros());
            }
        };
    }
}

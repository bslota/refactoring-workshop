package com.bslota.refactoring.library.fixture;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author bslota on 05/06/2020
 */
class NumericFixture {

    public static int randomInt() {
        return RandomUtils.nextInt(1, 1000);
    }
}

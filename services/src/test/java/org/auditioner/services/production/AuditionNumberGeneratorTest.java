package org.auditioner.services.production;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuditionNumberGeneratorTest {

    private AuditionNumberGenerator generator;
    private ProductionDAO productionDAO;

    @Before
    public void setUp() throws Exception {
        productionDAO = mock(ProductionDAO.class);

        when(productionDAO.lastAuditionNumberFor(0L,16)).thenReturn("04");

        generator = new AuditionNumberGenerator(productionDAO);
    }

    @Test
    public void numbersStartWithMembersAge() {

        String auditionNumber = generator.generate(0,16);

        assertThat("1605",equalTo(auditionNumber));
    }

    @Test
    public void singleDigitAgesAreNotPaddedWithLeadingZeros() {

        String auditionNumber = generator.generate(0,5);

        assertThat("501",equalTo(auditionNumber));
    }

    @Test
    public void numbersAreFourDigitsLong() {

        String auditionNumber = generator.generate(0,16);

        assertEquals(4, auditionNumber.length());
    }

    @Test
    public void numbersAreUniqueWithinAProduction() {
        generator.generate(0L, 24);

        verify(productionDAO).lastAuditionNumberFor(0L,24);
    }

    @Test
    public void numbersAreIncrementedForEachProduction() {
        when(productionDAO.lastAuditionNumberFor(175L, 12)).thenReturn("3");
        String auditionNumber = generator.generate(175L,12);
        assertTrue(auditionNumber.endsWith("04"));
    }

    @Test
    public void numbersStartAtOne() {
        when(productionDAO.lastAuditionNumberFor(175L,12)).thenReturn(null);
        String auditionNumber = generator.generate(175L,12);
        assertTrue(auditionNumber.endsWith("01"));
    }
}

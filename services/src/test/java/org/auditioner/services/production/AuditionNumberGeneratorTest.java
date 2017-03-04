package org.auditioner.services.production;

import org.junit.Before;
import org.junit.Test;

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
        when(productionDAO.lastAuditionNumberFor(0L)).thenReturn("0");

        generator = new AuditionNumberGenerator(productionDAO);
    }

    @Test
    public void numbersStartWithMembersAge() {

        String auditionNumber = generator.generate("16", 0);

        assertTrue(auditionNumber.startsWith("16"));
    }

    @Test
    public void singleDigitAgesArePaddedWithLeadingZeros() {

        String auditionNumber = generator.generate("5", 0);

        assertTrue(auditionNumber.startsWith("05"));
    }

    @Test
    public void numbersAreFourDigitsLong() {
        assertEquals(4, generator.generate("16", 0).length());
    }

    @Test
    public void numbersAreUniqueWithinAProduction() {
        generator.generate("24", 0L);

        verify(productionDAO).lastAuditionNumberFor(0L);
    }

    @Test
    public void numbersAreIncrementedForEachProduction() {
        when(productionDAO.lastAuditionNumberFor(175L)).thenReturn("3");
        String auditionNumber = generator.generate("12", 175L);
        assertTrue(auditionNumber.endsWith("04"));
    }
}

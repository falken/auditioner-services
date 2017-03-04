package org.auditioner.services.production;

import org.apache.commons.lang3.StringUtils;

public class AuditionNumberGenerator {
    private ProductionDAO productionDAO;

    public AuditionNumberGenerator(ProductionDAO productionDAO) {
        this.productionDAO = productionDAO;
    }

    public String generate(String age, long productionId) {
        Integer auditionNumber = nextAuditionNumberFor(productionId);
        return zeroPadded(age) + zeroPadded(auditionNumber);
    }

    private String zeroPadded(Object str) {
        return StringUtils.leftPad(str.toString(), 2, "0");
    }

    private Integer nextAuditionNumberFor(long productionId) {
        String lastNumber = productionDAO.lastAuditionNumberFor(productionId);
        return Integer.parseInt(lastNumber) + 1;
    }
}

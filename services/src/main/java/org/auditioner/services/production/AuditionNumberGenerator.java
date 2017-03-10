package org.auditioner.services.production;

import org.apache.commons.lang3.StringUtils;

public class AuditionNumberGenerator {
    private ProductionDAO productionDAO;

    public AuditionNumberGenerator(ProductionDAO productionDAO) {
        this.productionDAO = productionDAO;
    }

    public String generate(long productionId,int age) {
        Integer auditionNumber = nextAuditionNumberFor(productionId,age);
        return age + zeroPadded(auditionNumber);
    }

    private String zeroPadded(Object str) {
        return StringUtils.leftPad(str.toString(), 2, "0");
    }

    private Integer nextAuditionNumberFor(long productionId,int age) {
        String lastNumber = productionDAO.lastAuditionNumberFor(productionId, age);

        if(lastNumber == null) {
            lastNumber = "0";
        }

        return Integer.parseInt(lastNumber) + 1;
    }
}

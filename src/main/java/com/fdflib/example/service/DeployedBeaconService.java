package com.fdflib.example.service;

import com.fdflib.example.model.DeployedBeacon;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class DeployedBeaconService extends FdfCommonServices {

    public DeployedBeacon saveDeployedBeacon(DeployedBeacon deployedbeacon) {
        if(deployedbeacon != null) {
            return this.save(DeployedBeacon.class, deployedbeacon).current;
        }
        return null;
    }

    public DeployedBeacon getDeployedBeaconById(long id) {
        return getDeployedBeaconWithHistoryById(id).current;

    }

    public FdfEntity<DeployedBeacon> getDeployedBeaconWithHistoryById(long id) {
        FdfEntity<DeployedBeacon> deployedbeacon = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            deployedbeacon = this.getEntityById(DeployedBeacon.class, id);
        }

        return deployedbeacon;
    }
}
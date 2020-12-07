package com.fdflib.example.service;

import com.fdflib.example.model.Device;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;

public class DeviceService extends FdfCommonServices {

    public Device saveDevice(Device device) {
        if(device != null) {
            return this.save(Device.class, device).current;
        }
        return null;
    }

    public Device getDeviceById(long id) {
        return getDeviceWithHistoryById(id).current;

    }

    public FdfEntity<Device> getDeviceWithHistoryById(long id) {
        FdfEntity<Device> device = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            device = this.getEntityById(Device.class, id);
        }

        return device;
    }
}
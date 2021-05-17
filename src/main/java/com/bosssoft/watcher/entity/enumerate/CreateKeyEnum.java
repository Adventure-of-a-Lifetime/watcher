package com.bosssoft.watcher.entity.enumerate;

import com.bosssoft.watcher.utils.CreateKeyUtils;

public enum CreateKeyEnum {
    GenerateKey;

    private CreateKeyUtils utils;

    private CreateKeyEnum() {
        utils = new CreateKeyUtils(1, 1);
    }

    public static CreateKeyEnum getInstance() {
        return GenerateKey;
    }

    public CreateKeyUtils getUtils() {
        return utils;
    }
}

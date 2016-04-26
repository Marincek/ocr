package com.rockacode.ocr.communication;

import android.app.Application;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.memory.LruCacheStringObjectPersister;

/**
 * Created by Marincek on 28-Mar-16.
 */

public class InMemorySpiceService extends SpiceService {

    @Override
    public CacheManager createCacheManager(Application application) {
        CacheManager manager = new CacheManager();

        LruCacheStringObjectPersister memoryPersister = new LruCacheStringObjectPersister(1024 * 1024);
        manager.addPersister(memoryPersister);

        return manager;

    }
}

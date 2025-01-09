package org.shenqy.storagecommon.utils;

public interface Convert<R,T>{
    void convert(R origin, T target);
}
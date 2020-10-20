package com.notronix.mws.api.method;

import com.notronix.mws.api.AmazonAPIException;

public interface AmazonAPIMethod<R>
{
    R buildRequest() throws AmazonAPIException;
}

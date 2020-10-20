package com.notronix.mws.api.method;

import com.notronix.mws.api.service.AmazonAPIException;

public interface AmazonAPIMethod<R>
{
    R buildRequest() throws AmazonAPIException;
}

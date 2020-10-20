package com.notronix.mws.impl.method.products;

import com.amazonservices.mws.products.model.GetMatchingProductForIdRequest;
import com.amazonservices.mws.products.model.IdListType;
import com.notronix.mws.api.model.IdType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.numberOf;

public class GetMatchingProductForIdMethod extends ProductsAPIMethod<GetMatchingProductForIdRequest>
{
    private IdType idType;
    private Set<String> ids;

    public GetMatchingProductForIdMethod(IdType idType, List<String> ids) throws IllegalArgumentException {
        Set<String> validIds = ids.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        if (numberOf(validIds) > 5) {
            throw new IllegalArgumentException("A maximum of 5 ids are allowed.");
        }

        this.idType = idType;
        this.ids = validIds;
    }

    @Override
    public GetMatchingProductForIdRequest buildRequest() {
        IdListType idList = new IdListType();
        idList.setId(new ArrayList<>(ids));

        return new GetMatchingProductForIdRequest().withIdType(idType.name()).withIdList(idList);
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public Set<String> getIds() {
        return ids;
    }

    public void setIds(Set<String> ids) {
        this.ids = ids;
    }
}

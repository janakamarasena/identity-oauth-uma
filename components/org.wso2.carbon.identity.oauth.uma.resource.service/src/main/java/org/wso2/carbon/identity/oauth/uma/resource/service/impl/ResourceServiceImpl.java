/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.oauth.uma.resource.service.impl;

import org.wso2.carbon.identity.oauth.uma.common.exception.UMAClientException;
import org.wso2.carbon.identity.oauth.uma.common.exception.UMAServerException;
import org.wso2.carbon.identity.oauth.uma.resource.service.ResourceService;
import org.wso2.carbon.identity.oauth.uma.resource.service.dao.ResourceDAO;
import org.wso2.carbon.identity.oauth.uma.resource.service.model.Resource;

import java.util.List;
import java.util.UUID;

/**
 * ResourceService use for resource management.
 */
public class ResourceServiceImpl implements ResourceService {

    @Override
    public Resource registerResource(Resource resourceRegistration, String resourceOwnerName, int tenantId,
                                     String consumerKey, String userDomain) throws UMAServerException,
            UMAClientException {

        String resourceId = UUID.randomUUID().toString();
        resourceRegistration.setResourceId(resourceId);
        return ResourceDAO.registerResource(resourceRegistration, resourceOwnerName, tenantId, consumerKey, userDomain);
    }

    /**
     * Retrieve the available Resource list
     *
     * @param resourceOwnerName To ientify resources belongs to same owner
     * @return resource list
     * @throws UMAServerException
     */
    @Override
    public List<String> getResourceIds(String resourceOwnerName, String userDomain, String consumerKey) throws
            UMAServerException {

        return ResourceDAO.retrieveResourceIDs(resourceOwnerName, userDomain, consumerKey);
    }

    /**
     * @param resourceId resource ID of the resource which need to get retrieved
     * @return Retrieved resource using resource ID
     * @throws UMAServerException
     */
    @Override
    public Resource getResourceById(String resourceId) throws UMAServerException, UMAClientException {

        return ResourceDAO.retrieveResource(resourceId);

    }

    /**
     * Update the resource of the given resource ID
     *
     * @param resourceRegistration details of updated resource
     * @return updated resource
     * @throws UMAServerException
     */
    @Override
    public boolean updateResource(String resourceId, Resource resourceRegistration)
            throws UMAServerException, UMAClientException {

        return ResourceDAO.updateResource(resourceId, resourceRegistration);
    }

    /**
     * Delete the resource for the given resource ID
     *
     * @param resourceId Resource ID of the resource which need to get deleted
     * @throws UMAServerException
     */
    @Override
    public boolean deleteResource(String resourceId) throws UMAServerException, UMAClientException {

        return ResourceDAO.deleteResource(resourceId);

    }
}

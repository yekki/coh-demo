/*
 * File: StaticResource.java
 *
 * Copyright (c) 2015, 2016 Oracle and/or its affiliates.
 *
 * You may not use this file except in compliance with the Universal Permissive
 * License (UPL), Version 1.0 (the "License.")
 *
 * You may obtain a copy of the License at https://opensource.org/licenses/UPL.
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */

package com.oracle.coherence.demo.application;

import com.tangosol.util.Base;
import com.tangosol.util.Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;

/**
 * Serves static resources for an application from the class-path.
 *
 * @author Brian Oliver
 */
@Path("{resource: .*}")
public class StaticResource
{
    /**
     * The base folder containing static resources on the class path.
     */
    private static String BASE_FOLDER = "web";


    @GET
    public Response getResource(@PathParam("resource") String resource)
    {
        // construct the resource path relative to the base folder
        String resourcePath = BASE_FOLDER + '/' + resource;

        // construct a URL to the resource (using the class loader)
        URL url = Resources.findFileOrResource(resourcePath, Base.ensureClassLoader(null));

        try
        {
            return url == null
                   ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(url.openStream()).build();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}

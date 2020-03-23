/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.am.management.handlers.management.api.resources.organizations.environments.domains;

import io.gravitee.am.management.handlers.management.api.model.AuditParam;
import io.gravitee.am.management.handlers.management.api.resources.AbstractResource;
import io.gravitee.am.management.service.AuditService;
import io.gravitee.am.model.Acl;
import io.gravitee.am.model.ReferenceType;
import io.gravitee.am.model.permissions.Permission;
import io.gravitee.am.reporter.api.audit.AuditReportableCriteria;
import io.gravitee.am.reporter.api.audit.model.Audit;
import io.gravitee.common.http.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Collections;

import static io.gravitee.am.management.service.permissions.Permissions.of;
import static io.gravitee.am.management.service.permissions.Permissions.or;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
@Api(tags = {"audit"})
public class AuditsResource extends AbstractResource {

    @Context
    private ResourceContext resourceContext;

    @Autowired
    private AuditService auditService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "List audit logs for a security domain",
            notes = "User must have the DOMAIN_AUDIT[READ] permission on the specified domain " +
                    "or DOMAIN_AUDIT[READ] permission on the specified environment " +
                    "or DOMAIN_AUDIT[READ] permission on the specified organization")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List audit logs for a security domain", response = Audit.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public void list(
            @PathParam("organizationId") String organizationId,
            @PathParam("environmentId") String environmentId,
            @PathParam("domain") String domain,
            @BeanParam AuditParam param,
            @Suspended final AsyncResponse response) {

        AuditReportableCriteria.Builder queryBuilder = new AuditReportableCriteria.Builder()
                .from(param.getFrom())
                .to(param.getTo())
                .status(param.getStatus())
                .user(param.getUser());

        if (param.getType() != null) {
            queryBuilder.types(Collections.singletonList(param.getType()));
        }

        checkPermissions(or(of(ReferenceType.DOMAIN, domain, Permission.DOMAIN_AUDIT, Acl.READ),
                of(ReferenceType.ENVIRONMENT, environmentId, Permission.DOMAIN_AUDIT, Acl.READ),
                of(ReferenceType.ORGANIZATION, organizationId, Permission.DOMAIN_AUDIT, Acl.READ)))
                .andThen(auditService.search(domain, queryBuilder.build(), param.getPage(), param.getSize()))
                .subscribe(response::resume, response::resume);
    }

    @Path("{audit}")
    public AuditResource getAuditResource() {
        return resourceContext.getResource(AuditResource.class);
    }
}
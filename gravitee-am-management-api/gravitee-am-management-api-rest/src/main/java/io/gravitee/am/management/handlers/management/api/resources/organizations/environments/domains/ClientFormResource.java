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

import io.gravitee.am.identityprovider.api.User;
import io.gravitee.am.management.handlers.management.api.resources.AbstractResource;
import io.gravitee.am.model.Acl;
import io.gravitee.am.model.Form;
import io.gravitee.am.model.ReferenceType;
import io.gravitee.am.model.permissions.Permission;
import io.gravitee.am.service.ClientService;
import io.gravitee.am.service.DomainService;
import io.gravitee.am.service.FormService;
import io.gravitee.am.service.exception.ClientNotFoundException;
import io.gravitee.am.service.exception.DomainNotFoundException;
import io.gravitee.am.service.model.UpdateForm;
import io.gravitee.common.http.MediaType;
import io.reactivex.Maybe;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

import static io.gravitee.am.management.service.permissions.Permissions.of;
import static io.gravitee.am.management.service.permissions.Permissions.or;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
@Api(tags = {"form"})
@Deprecated
public class ClientFormResource extends AbstractResource {

    @Autowired
    private FormService formService;

    @Autowired
    private DomainService domainService;

    @Autowired
    private ClientService clientService;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update a form for a client",
            notes = "User must have APPLICATION_FORM[UPDATE] permission on the specified client " +
                    "or APPLICATION_FORM[UPDATE] permission on the specified domain " +
                    "or APPLICATION_FORM[UPDATE] permission on the specified environment " +
                    "or APPLICATION_FORM[UPDATE] permission on the specified organization")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Form successfully updated", response = Form.class),
            @ApiResponse(code = 500, message = "Internal server error")})
    public void update(
            @PathParam("organizationId") String organizationId,
            @PathParam("environmentId") String environmentId,
            @PathParam("domain") String domain,
            @PathParam("client") String client,
            @PathParam("form") String form,
            @ApiParam(name = "form", required = true) @Valid @NotNull UpdateForm updateForm,
            @Suspended final AsyncResponse response) {
        final User authenticatedUser = getAuthenticatedUser();

        checkPermissions(or(of(ReferenceType.APPLICATION, client, Permission.APPLICATION_FORM, Acl.UPDATE),
                of(ReferenceType.DOMAIN, domain, Permission.APPLICATION_FORM, Acl.UPDATE),
                of(ReferenceType.ENVIRONMENT, environmentId, Permission.APPLICATION_FORM, Acl.UPDATE),
                of(ReferenceType.ORGANIZATION, organizationId, Permission.APPLICATION_FORM, Acl.UPDATE)))
                .andThen(domainService.findById(domain)
                        .switchIfEmpty(Maybe.error(new DomainNotFoundException(domain)))
                        .flatMap(irrelevant -> clientService.findById(client))
                        .switchIfEmpty(Maybe.error(new ClientNotFoundException(client)))
                        .flatMapSingle(irrelevant -> formService.update(domain, client, form, updateForm, authenticatedUser)))
                .subscribe(response::resume, response::resume);
    }

    @DELETE
    @ApiOperation(value = "Delete a form for a client",
            notes = "User must have APPLICATION_FORM[DELETE] permission on the specified client " +
                    "or APPLICATION_FORM[DELETE] permission on the specified domain " +
                    "or APPLICATION_FORM[DELETE] permission on the specified environment " +
                    "or APPLICATION_FORM[DELETE] permission on the specified organization")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Form successfully deleted"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public void delete(
            @PathParam("organizationId") String organizationId,
            @PathParam("environmentId") String environmentId,
            @PathParam("domain") String domain,
            @PathParam("client") String client,
            @PathParam("form") String form,
            @Suspended final AsyncResponse response) {
        final User authenticatedUser = getAuthenticatedUser();

        checkPermissions(or(of(ReferenceType.APPLICATION, client, Permission.APPLICATION_FORM, Acl.DELETE),
                of(ReferenceType.DOMAIN, domain, Permission.APPLICATION_FORM, Acl.DELETE),
                of(ReferenceType.ENVIRONMENT, environmentId, Permission.APPLICATION_FORM, Acl.DELETE),
                of(ReferenceType.ORGANIZATION, organizationId, Permission.APPLICATION_FORM, Acl.DELETE)))
                .andThen(formService.delete(domain, form, authenticatedUser))
                .subscribe(() -> response.resume(Response.noContent().build()), response::resume);
    }
}
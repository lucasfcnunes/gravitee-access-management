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
package io.gravitee.am.service;

import io.gravitee.am.model.Domain;
import io.gravitee.am.model.ReferenceType;
import io.gravitee.am.model.User;
import io.gravitee.am.model.VerifyAttempt;
import io.gravitee.am.model.oidc.Client;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

import java.util.Optional;

/**
 * @author Ashraful Hasan (ashraful.hasan at graviteesource.com)
 * @author GraviteeSource Team
 */
public interface VerifyAttemptService {
    Maybe<VerifyAttempt> checkVerifyAttempt(User user, String factorId, Client client, Domain domain);

    Completable incrementAttempt(String userId, String factorId, Client client, Domain domain, Optional<VerifyAttempt> optionalVerifyAttempt);

    Completable delete(String id);

    Completable deleteByUser(User user);

    Completable deleteByDomain(Domain domain, ReferenceType referenceType);

    boolean shouldSendEmail(Client client, Domain domain);
}
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
package io.gravitee.am.factor.api;

import io.gravitee.am.model.User;
import io.gravitee.am.model.factor.EnrolledFactor;
import io.gravitee.am.model.factor.EnrolledFactorSecurity;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
public interface FactorProvider {

    Completable verify(FactorContext context);

    Single<Enrollment> enroll(String account);

    boolean checkSecurityFactor(EnrolledFactor securityFactor);

    default boolean needChallengeSending() { return true; }

    default Completable sendChallenge(FactorContext context) {
        return Completable.complete();
    }

    default boolean useVariableFactorSecurity() {
        return false;
    }

    default Single<EnrolledFactor> changeVariableFactorSecurity(EnrolledFactor factor) {
        return Single.just(factor);
    }

    default Maybe<String> generateQrCode(User user, EnrolledFactor enrolledFactor) {
        return Maybe.empty();
    }
}

/* tslint:disable */
/* eslint-disable */
/**
 * Gravitee.io - Access Management API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface PasswordSettings
 */
export interface PasswordSettings {
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    inherited?: boolean;
    /**
     * 
     * @type {number}
     * @memberof PasswordSettings
     */
    minLength?: number;
    /**
     * 
     * @type {number}
     * @memberof PasswordSettings
     */
    maxLength?: number;
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    includeNumbers?: boolean;
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    includeSpecialCharacters?: boolean;
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    lettersInMixedCase?: boolean;
    /**
     * 
     * @type {number}
     * @memberof PasswordSettings
     */
    maxConsecutiveLetters?: number;
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    excludePasswordsInDictionary?: boolean;
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    excludeUserProfileInfoInPassword?: boolean;
    /**
     * 
     * @type {number}
     * @memberof PasswordSettings
     */
    expiryDuration?: number;
    /**
     * 
     * @type {boolean}
     * @memberof PasswordSettings
     */
    passwordHistoryEnabled?: boolean;
    /**
     * 
     * @type {number}
     * @memberof PasswordSettings
     */
    oldPasswords?: number;
}

export function PasswordSettingsFromJSON(json: any): PasswordSettings {
    return PasswordSettingsFromJSONTyped(json, false);
}

export function PasswordSettingsFromJSONTyped(json: any, ignoreDiscriminator: boolean): PasswordSettings {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'inherited': !exists(json, 'inherited') ? undefined : json['inherited'],
        'minLength': !exists(json, 'minLength') ? undefined : json['minLength'],
        'maxLength': !exists(json, 'maxLength') ? undefined : json['maxLength'],
        'includeNumbers': !exists(json, 'includeNumbers') ? undefined : json['includeNumbers'],
        'includeSpecialCharacters': !exists(json, 'includeSpecialCharacters') ? undefined : json['includeSpecialCharacters'],
        'lettersInMixedCase': !exists(json, 'lettersInMixedCase') ? undefined : json['lettersInMixedCase'],
        'maxConsecutiveLetters': !exists(json, 'maxConsecutiveLetters') ? undefined : json['maxConsecutiveLetters'],
        'excludePasswordsInDictionary': !exists(json, 'excludePasswordsInDictionary') ? undefined : json['excludePasswordsInDictionary'],
        'excludeUserProfileInfoInPassword': !exists(json, 'excludeUserProfileInfoInPassword') ? undefined : json['excludeUserProfileInfoInPassword'],
        'expiryDuration': !exists(json, 'expiryDuration') ? undefined : json['expiryDuration'],
        'passwordHistoryEnabled': !exists(json, 'passwordHistoryEnabled') ? undefined : json['passwordHistoryEnabled'],
        'oldPasswords': !exists(json, 'oldPasswords') ? undefined : json['oldPasswords'],
    };
}

export function PasswordSettingsToJSON(value?: PasswordSettings | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'inherited': value.inherited,
        'minLength': value.minLength,
        'maxLength': value.maxLength,
        'includeNumbers': value.includeNumbers,
        'includeSpecialCharacters': value.includeSpecialCharacters,
        'lettersInMixedCase': value.lettersInMixedCase,
        'maxConsecutiveLetters': value.maxConsecutiveLetters,
        'excludePasswordsInDictionary': value.excludePasswordsInDictionary,
        'excludeUserProfileInfoInPassword': value.excludeUserProfileInfoInPassword,
        'expiryDuration': value.expiryDuration,
        'passwordHistoryEnabled': value.passwordHistoryEnabled,
        'oldPasswords': value.oldPasswords,
    };
}

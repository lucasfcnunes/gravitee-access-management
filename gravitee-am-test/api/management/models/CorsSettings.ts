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
 * @interface CorsSettings
 */
export interface CorsSettings {
    /**
     * 
     * @type {boolean}
     * @memberof CorsSettings
     */
    enabled?: boolean;
    /**
     * 
     * @type {Set<string>}
     * @memberof CorsSettings
     */
    allowedOrigins?: Set<string>;
    /**
     * 
     * @type {Set<string>}
     * @memberof CorsSettings
     */
    allowedMethods?: Set<string>;
    /**
     * 
     * @type {Set<string>}
     * @memberof CorsSettings
     */
    allowedHeaders?: Set<string>;
    /**
     * 
     * @type {number}
     * @memberof CorsSettings
     */
    maxAge?: number;
    /**
     * 
     * @type {boolean}
     * @memberof CorsSettings
     */
    allowCredentials?: boolean;
}

export function CorsSettingsFromJSON(json: any): CorsSettings {
    return CorsSettingsFromJSONTyped(json, false);
}

export function CorsSettingsFromJSONTyped(json: any, ignoreDiscriminator: boolean): CorsSettings {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'enabled': !exists(json, 'enabled') ? undefined : json['enabled'],
        'allowedOrigins': !exists(json, 'allowedOrigins') ? undefined : json['allowedOrigins'],
        'allowedMethods': !exists(json, 'allowedMethods') ? undefined : json['allowedMethods'],
        'allowedHeaders': !exists(json, 'allowedHeaders') ? undefined : json['allowedHeaders'],
        'maxAge': !exists(json, 'maxAge') ? undefined : json['maxAge'],
        'allowCredentials': !exists(json, 'allowCredentials') ? undefined : json['allowCredentials'],
    };
}

export function CorsSettingsToJSON(value?: CorsSettings | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'enabled': value.enabled,
        'allowedOrigins': value.allowedOrigins,
        'allowedMethods': value.allowedMethods,
        'allowedHeaders': value.allowedHeaders,
        'maxAge': value.maxAge,
        'allowCredentials': value.allowCredentials,
    };
}


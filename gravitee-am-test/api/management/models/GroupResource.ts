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
 * @interface GroupResource
 */
export interface GroupResource {
    /**
     * 
     * @type {any}
     * @memberof GroupResource
     */
    groupMembersResource?: any;
}

export function GroupResourceFromJSON(json: any): GroupResource {
    return GroupResourceFromJSONTyped(json, false);
}

export function GroupResourceFromJSONTyped(json: any, ignoreDiscriminator: boolean): GroupResource {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'groupMembersResource': !exists(json, 'groupMembersResource') ? undefined : json['groupMembersResource'],
    };
}

export function GroupResourceToJSON(value?: GroupResource | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'groupMembersResource': value.groupMembersResource,
    };
}


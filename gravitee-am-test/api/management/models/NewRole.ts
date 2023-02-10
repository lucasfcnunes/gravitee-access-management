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
 * @interface NewRole
 */
export interface NewRole {
    /**
     * 
     * @type {string}
     * @memberof NewRole
     */
    name: string;
    /**
     * 
     * @type {string}
     * @memberof NewRole
     */
    assignableType?: NewRoleAssignableTypeEnum;
    /**
     * 
     * @type {string}
     * @memberof NewRole
     */
    description?: string;
}


/**
 * @export
 */
export const NewRoleAssignableTypeEnum = {
    Platform: 'PLATFORM',
    Domain: 'DOMAIN',
    Application: 'APPLICATION',
    Organization: 'ORGANIZATION',
    Environment: 'ENVIRONMENT'
} as const;
export type NewRoleAssignableTypeEnum = typeof NewRoleAssignableTypeEnum[keyof typeof NewRoleAssignableTypeEnum];


export function NewRoleFromJSON(json: any): NewRole {
    return NewRoleFromJSONTyped(json, false);
}

export function NewRoleFromJSONTyped(json: any, ignoreDiscriminator: boolean): NewRole {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'name': json['name'],
        'assignableType': !exists(json, 'assignableType') ? undefined : json['assignableType'],
        'description': !exists(json, 'description') ? undefined : json['description'],
    };
}

export function NewRoleToJSON(value?: NewRole | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'name': value.name,
        'assignableType': value.assignableType,
        'description': value.description,
    };
}

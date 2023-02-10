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
 * @interface CertificateEntity
 */
export interface CertificateEntity {
    /**
     * 
     * @type {string}
     * @memberof CertificateEntity
     */
    id?: string;
    /**
     * 
     * @type {string}
     * @memberof CertificateEntity
     */
    name?: string;
    /**
     * 
     * @type {string}
     * @memberof CertificateEntity
     */
    type?: string;
    /**
     * 
     * @type {string}
     * @memberof CertificateEntity
     */
    configuration?: string;
    /**
     * 
     * @type {string}
     * @memberof CertificateEntity
     */
    domain?: string;
    /**
     * 
     * @type {{ [key: string]: any; }}
     * @memberof CertificateEntity
     */
    metadata?: { [key: string]: any; };
    /**
     * 
     * @type {Date}
     * @memberof CertificateEntity
     */
    createdAt?: Date;
    /**
     * 
     * @type {Date}
     * @memberof CertificateEntity
     */
    updatedAt?: Date;
    /**
     * 
     * @type {Date}
     * @memberof CertificateEntity
     */
    expiresAt?: Date;
    /**
     * 
     * @type {boolean}
     * @memberof CertificateEntity
     */
    system?: boolean;
    /**
     * 
     * @type {boolean}
     * @memberof CertificateEntity
     */
    deprecated?: boolean;
    /**
     * 
     * @type {string}
     * @memberof CertificateEntity
     */
    status?: CertificateEntityStatusEnum;
}


/**
 * @export
 */
export const CertificateEntityStatusEnum = {
    Valid: 'VALID',
    WillExpire: 'WILL_EXPIRE',
    Expired: 'EXPIRED',
    Renewed: 'RENEWED'
} as const;
export type CertificateEntityStatusEnum = typeof CertificateEntityStatusEnum[keyof typeof CertificateEntityStatusEnum];


export function CertificateEntityFromJSON(json: any): CertificateEntity {
    return CertificateEntityFromJSONTyped(json, false);
}

export function CertificateEntityFromJSONTyped(json: any, ignoreDiscriminator: boolean): CertificateEntity {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'id': !exists(json, 'id') ? undefined : json['id'],
        'name': !exists(json, 'name') ? undefined : json['name'],
        'type': !exists(json, 'type') ? undefined : json['type'],
        'configuration': !exists(json, 'configuration') ? undefined : json['configuration'],
        'domain': !exists(json, 'domain') ? undefined : json['domain'],
        'metadata': !exists(json, 'metadata') ? undefined : json['metadata'],
        'createdAt': !exists(json, 'createdAt') ? undefined : (new Date(json['createdAt'])),
        'updatedAt': !exists(json, 'updatedAt') ? undefined : (new Date(json['updatedAt'])),
        'expiresAt': !exists(json, 'expiresAt') ? undefined : (new Date(json['expiresAt'])),
        'system': !exists(json, 'system') ? undefined : json['system'],
        'deprecated': !exists(json, 'deprecated') ? undefined : json['deprecated'],
        'status': !exists(json, 'status') ? undefined : json['status'],
    };
}

export function CertificateEntityToJSON(value?: CertificateEntity | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'id': value.id,
        'name': value.name,
        'type': value.type,
        'configuration': value.configuration,
        'domain': value.domain,
        'metadata': value.metadata,
        'createdAt': value.createdAt === undefined ? undefined : (value.createdAt.toISOString()),
        'updatedAt': value.updatedAt === undefined ? undefined : (value.updatedAt.toISOString()),
        'expiresAt': value.expiresAt === undefined ? undefined : (value.expiresAt.toISOString()),
        'system': value.system,
        'deprecated': value.deprecated,
        'status': value.status,
    };
}

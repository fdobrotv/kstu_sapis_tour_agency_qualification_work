/* tslint:disable */
/* eslint-disable */
/**
 * Tourist agency
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
import type { ServiceClass } from './ServiceClass';
import {
    ServiceClassFromJSON,
    ServiceClassFromJSONTyped,
    ServiceClassToJSON,
} from './ServiceClass';

/**
 * 
 * @export
 * @interface Hotel
 */
export interface Hotel {
    /**
     * 
     * @type {string}
     * @memberof Hotel
     */
    name: string;
    /**
     * 
     * @type {string}
     * @memberof Hotel
     */
    address: string;
    /**
     * 
     * @type {ServiceClass}
     * @memberof Hotel
     */
    _class: ServiceClass;
    /**
     * 
     * @type {boolean}
     * @memberof Hotel
     */
    isGuideIncluded: boolean;
    /**
     * 
     * @type {string}
     * @memberof Hotel
     */
    id: string;
}

/**
 * Check if a given object implements the Hotel interface.
 */
export function instanceOfHotel(value: object): boolean {
    let isInstance = true;
    isInstance = isInstance && "name" in value;
    isInstance = isInstance && "address" in value;
    isInstance = isInstance && "_class" in value;
    isInstance = isInstance && "isGuideIncluded" in value;
    isInstance = isInstance && "id" in value;

    return isInstance;
}

export function HotelFromJSON(json: any): Hotel {
    return HotelFromJSONTyped(json, false);
}

export function HotelFromJSONTyped(json: any, ignoreDiscriminator: boolean): Hotel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'name': json['name'],
        'address': json['address'],
        '_class': ServiceClassFromJSON(json['class']),
        'isGuideIncluded': json['isGuideIncluded'],
        'id': json['id'],
    };
}

export function HotelToJSON(value?: Hotel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'name': value.name,
        'address': value.address,
        'class': ServiceClassToJSON(value._class),
        'isGuideIncluded': value.isGuideIncluded,
        'id': value.id,
    };
}


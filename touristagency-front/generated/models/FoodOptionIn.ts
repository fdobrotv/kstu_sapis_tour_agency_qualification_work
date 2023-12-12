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
/**
 * 
 * @export
 * @interface FoodOptionIn
 */
export interface FoodOptionIn {
    /**
     * 
     * @type {string}
     * @memberof FoodOptionIn
     */
    name: string;
    /**
     * 
     * @type {number}
     * @memberof FoodOptionIn
     */
    price: number;
}

/**
 * Check if a given object implements the FoodOptionIn interface.
 */
export function instanceOfFoodOptionIn(value: object): boolean {
    let isInstance = true;
    isInstance = isInstance && "name" in value;
    isInstance = isInstance && "price" in value;

    return isInstance;
}

export function FoodOptionInFromJSON(json: any): FoodOptionIn {
    return FoodOptionInFromJSONTyped(json, false);
}

export function FoodOptionInFromJSONTyped(json: any, ignoreDiscriminator: boolean): FoodOptionIn {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'name': json['name'],
        'price': json['price'],
    };
}

export function FoodOptionInToJSON(value?: FoodOptionIn | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'name': value.name,
        'price': value.price,
    };
}


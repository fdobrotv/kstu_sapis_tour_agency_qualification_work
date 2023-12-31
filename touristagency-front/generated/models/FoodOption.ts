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
 * @interface FoodOption
 */
export interface FoodOption {
    /**
     * 
     * @type {string}
     * @memberof FoodOption
     */
    name: string;
    /**
     * 
     * @type {number}
     * @memberof FoodOption
     */
    price: number;
    /**
     * 
     * @type {string}
     * @memberof FoodOption
     */
    id: string;
}

/**
 * Check if a given object implements the FoodOption interface.
 */
export function instanceOfFoodOption(value: object): boolean {
    let isInstance = true;
    isInstance = isInstance && "name" in value;
    isInstance = isInstance && "price" in value;
    isInstance = isInstance && "id" in value;

    return isInstance;
}

export function FoodOptionFromJSON(json: any): FoodOption {
    return FoodOptionFromJSONTyped(json, false);
}

export function FoodOptionFromJSONTyped(json: any, ignoreDiscriminator: boolean): FoodOption {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'name': json['name'],
        'price': json['price'],
        'id': json['id'],
    };
}

export function FoodOptionToJSON(value?: FoodOption | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'name': value.name,
        'price': value.price,
        'id': value.id,
    };
}


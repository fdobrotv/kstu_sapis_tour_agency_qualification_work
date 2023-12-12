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


import * as runtime from '../runtime';
import type {
  FoodOption,
  FoodOptionIn,
} from '../models/index';
import {
    FoodOptionFromJSON,
    FoodOptionToJSON,
    FoodOptionInFromJSON,
    FoodOptionInToJSON,
} from '../models/index';

export interface CreateFoodOptionRequest {
    foodOptionIn?: FoodOptionIn;
}

export interface DeleteFoodOptionByIdRequest {
    id: string;
}

export interface ListFoodOptionsRequest {
    limit?: number;
}

export interface ShowFoodOptionByIdRequest {
    id: string;
}

/**
 * 
 */
export class FoodOptionsApi extends runtime.BaseAPI {

    /**
     * Create a foodOption
     */
    async createFoodOptionRaw(requestParameters: CreateFoodOptionRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<FoodOption>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/foodOptions`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: FoodOptionInToJSON(requestParameters.foodOptionIn),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => FoodOptionFromJSON(jsonValue));
    }

    /**
     * Create a foodOption
     */
    async createFoodOption(requestParameters: CreateFoodOptionRequest = {}, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<FoodOption> {
        const response = await this.createFoodOptionRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Delete specific food option by id
     */
    async deleteFoodOptionByIdRaw(requestParameters: DeleteFoodOptionByIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling deleteFoodOptionById.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/foodOptions/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'DELETE',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Delete specific food option by id
     */
    async deleteFoodOptionById(requestParameters: DeleteFoodOptionByIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.deleteFoodOptionByIdRaw(requestParameters, initOverrides);
    }

    /**
     * List all foodOptions
     */
    async listFoodOptionsRaw(requestParameters: ListFoodOptionsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<FoodOption>>> {
        const queryParameters: any = {};

        if (requestParameters.limit !== undefined) {
            queryParameters['limit'] = requestParameters.limit;
        }

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/foodOptions`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(FoodOptionFromJSON));
    }

    /**
     * List all foodOptions
     */
    async listFoodOptions(requestParameters: ListFoodOptionsRequest = {}, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<FoodOption>> {
        const response = await this.listFoodOptionsRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Info for a specific food option
     */
    async showFoodOptionByIdRaw(requestParameters: ShowFoodOptionByIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<FoodOption>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling showFoodOptionById.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/foodOptions/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => FoodOptionFromJSON(jsonValue));
    }

    /**
     * Info for a specific food option
     */
    async showFoodOptionById(requestParameters: ShowFoodOptionByIdRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<FoodOption> {
        const response = await this.showFoodOptionByIdRaw(requestParameters, initOverrides);
        return await response.value();
    }

}

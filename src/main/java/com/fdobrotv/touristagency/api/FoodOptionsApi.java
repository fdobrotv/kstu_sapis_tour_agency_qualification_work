/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.fdobrotv.touristagency.api;

import com.fdobrotv.touristagency.dto.Error;
import com.fdobrotv.touristagency.dto.FoodOption;
import com.fdobrotv.touristagency.dto.FoodOptionIn;
import java.util.UUID;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-01T13:44:34.542449+02:00[Europe/Sofia]")
@Validated
@Tag(name = "foodOptions", description = "the foodOptions API")
public interface FoodOptionsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /foodOptions : Create a foodOption
     *
     * @param foodOptionIn  (optional)
     * @return Ok (status code 201)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createFoodOption",
        summary = "Create a foodOption",
        tags = { "foodOptions" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Ok", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = FoodOption.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/foodOptions",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<FoodOption> createFoodOption(
        @Parameter(name = "FoodOptionIn", description = "") @Valid @RequestBody(required = false) FoodOptionIn foodOptionIn
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /foodOptions/{id} : Delete specific food option by id
     *
     * @param id The id of the resource to delete (required)
     * @return Resource deleted successfully (status code 204)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "deleteFoodOptionById",
        summary = "Delete specific food option by id",
        tags = { "foodOptions" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/foodOptions/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Void> deleteFoodOptionById(
        @Parameter(name = "id", description = "The id of the resource to delete", required = true, in = ParameterIn.PATH) @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /foodOptions : List all foodOptions
     *
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of foodOptions (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listFoodOptions",
        summary = "List all foodOptions",
        tags = { "foodOptions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of foodOptions", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FoodOption.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/foodOptions",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<FoodOption>> listFoodOptions(
        @Max(100) @Parameter(name = "limit", description = "How many items to return at one time (max 100)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null, null, null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /foodOptions/{id} : Info for a specific food option
     *
     * @param id The id of the resource to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "showFoodOptionById",
        summary = "Info for a specific food option",
        tags = { "foodOptions" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = FoodOption.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/foodOptions/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<FoodOption> showFoodOptionById(
        @Parameter(name = "id", description = "The id of the resource to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("id") UUID id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}

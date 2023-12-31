/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.fdobrotv.touristagency.api;

import com.fdobrotv.touristagency.dto.Error;
import com.fdobrotv.touristagency.dto.Flight;
import com.fdobrotv.touristagency.dto.FlightIn;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-12T13:06:52.189220+02:00[Europe/Sofia]")
@Validated
@Tag(name = "flights", description = "the flights API")
public interface FlightsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /flights : Create a flight
     *
     * @param flightIn  (optional)
     * @return Ok (status code 201)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createFlight",
        summary = "Create a flight",
        tags = { "flights" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Ok", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/flights",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Flight> createFlight(
        @Parameter(name = "FlightIn", description = "") @Valid @RequestBody(required = false) FlightIn flightIn
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
     * DELETE /flights/{id} : Delete specific flight by id
     *
     * @param id The id of the resource to delete (required)
     * @return Resource deleted successfully (status code 204)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "deleteFlightById",
        summary = "Delete specific flight by id",
        tags = { "flights" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/flights/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Void> deleteFlightById(
        @Parameter(name = "id", description = "The id of the resource to delete", required = true, in = ParameterIn.PATH) @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /flights : List all flights
     *
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of flights (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listFlights",
        summary = "List all flights",
        tags = { "flights" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of flights", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Flight.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/flights",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Flight>> listFlights(
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
     * GET /flights/{id} : Info for a specific flight
     *
     * @param id The id of the resource to retrieve (required)
     * @return Expected response to a valid request (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "showFlightById",
        summary = "Info for a specific flight",
        tags = { "flights" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/flights/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Flight> showFlightById(
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

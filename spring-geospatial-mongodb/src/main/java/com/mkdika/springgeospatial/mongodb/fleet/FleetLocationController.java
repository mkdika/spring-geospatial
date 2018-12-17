/*
 * The MIT License
 *
 * Copyright 2018 Maikel Chandika <mkdika@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mkdika.springgeospatial.mongodb.fleet;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
@RestController
public class FleetLocationController {

    private final FleetLocationRepository fleetRepository;

    @Autowired
    public FleetLocationController(FleetLocationRepository placeRepository) {
        this.fleetRepository = placeRepository;
    }

    @PostMapping(value = "/fleets/location",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertFleetLocation(@RequestBody FleetLocation floc) {
        floc.setUpdateTime(new Date().getTime());
        floc = fleetRepository.insert(floc);
        return new ResponseEntity(floc, HttpStatus.OK);
    }
    
    @GetMapping(value = "/fleets/location/count",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFleetLocationCount() {
        long count = fleetRepository.count();
        return new ResponseEntity(count, HttpStatus.OK);
    }
    
    @GetMapping(value = "/fleets",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNearFleetByCoordinate(@RequestParam(name = "lat", required = true) double latitude,
                                                   @RequestParam(name = "lng", required = true) double longitude,
                                                   @RequestParam(name = "rad", required = false, defaultValue = "700") int radius,
                                                   @RequestParam(name = "limit", required = false, defaultValue = "5") int limit) {
        return new ResponseEntity( HttpStatus.OK);
    }
}

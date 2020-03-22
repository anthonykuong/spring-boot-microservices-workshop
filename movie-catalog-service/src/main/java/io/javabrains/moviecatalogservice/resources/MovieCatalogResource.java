package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Rest Controller
 * http://localhost:8081/catalogResource/anthony
 * This will return [{"name":"Onward","desc":"Pixar movie that cameout 2020. Anthony Ku Ong gives it a rating of 4","rating":4}]
 */
@RestController
@RequestMapping("/catalogResource")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        return Collections.singletonList(
                new CatalogItem("Onward", "Pixar movie that cameout 2020. Anthony Ku Ong gives it a rating of 4", 4)
        );
    }
}
